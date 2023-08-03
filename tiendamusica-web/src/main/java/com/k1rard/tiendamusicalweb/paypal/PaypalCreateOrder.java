package com.k1rard.tiendamusicalweb.paypal;

import com.google.gson.Gson;
import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalweb.controllers.CarritoController;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author k1rard
 * Clase que se encarga de generar la estructura y la orden de la informacion
 * de la compra que desea realizar el usuario en PAYPAL.
 */
@Named
public class PaypalCreateOrder extends PaypalClient {

    /**
     * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
     */
    private static final Logger LOGGER = LogManager.getLogger(PaypalCreateOrder.class);

    @Inject
    private CarritoController carritoController;

    /**
     * Objeto que contiene la orden de pago de la compra.
     */
    private OrderRequest orderRequest;

    /**
     * Metodo que se encarga de generar la orden de compra de elos productos para el boton de paypal.
     * @param sessionBean {@link SessionBean} Objeto con la informacion del carrito del usuario en sesion.
     * @return {@link HttpResponse} Objeto que obtiene una respuesta HTTP del servicio de Paypal.
     * @throws IOException {@link IOException} Exception en caso de error al generar la transaction de compra con Paypal
     */
    public HttpResponse<Order> crearOrden(SessionBean sessionBean) throws IOException {
        LOGGER.info("Ingresando a la funcion de crearOrden...");
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest();
        ordersCreateRequest.prefer("return=representation");
        ordersCreateRequest.requestBody(this.generarCuerpoOrder(sessionBean));

        HttpResponse<Order> response = client().execute(ordersCreateRequest);
        sessionBean.setOrder(response);
        return response;
    }


    /**
     * Metodo que permite generar el cuerpo de la información del formulario de pago de Paypal con el detalle de toda la transaction.
     * @param sessionBean {@link SessionBean} Objeto con la información en session de los productos del carrito
     * @return {@link OrderRequest} Objeto con la solicitud de la orden de compra
     */
    private OrderRequest generarCuerpoOrder(SessionBean sessionBean) {
        // Se genera el objeto de solicituda de la orden a mostrar con la información del carrito en paypal.
        this.orderRequest = new OrderRequest();

        // Cliente que realiza la aprobacion de la compra.
        Payer payer = new Payer();
        this.orderRequest.checkoutPaymentIntent("CAPTURE");

        // Se configura la landinpage, el nombre de la empresa que vende el producto y la direccion de envio.
        ApplicationContext applicationContext = new ApplicationContext().brandName("K1rad INC...")
                .landingPage("BILLING").shippingPreference("SET_PROVIDER_ADDRESS");

        this.orderRequest.applicationContext(applicationContext);

        // Se obtienen los datos personales del comprador
        String nombre = sessionBean.getPersona().getNombre();
        String primerApellido = sessionBean.getPersona().getPrimerApellido();
        String segundoApellido = sessionBean.getPersona().getSegundoApellido();

        // Se crea la lista de unidades de compra a mostrarse en el formulario de Paypal.
        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();

        List<Item> items = new ArrayList<>();

        // Se obtienen los productos seleccionados en el carrito de la persona o usuario en sesion.
        List<CarritoAlbum> carritoAlbums = sessionBean.getPersona().getCarrito().getCarritosAlbum();

        // Se definen las variables que indican los impuestos de la copmra.
        Double impuestoPorProducto = 10.00;
        Double impuestoSunaTotalProductos = 0.0;

        // Se obtiene e itera cada producto del carrito y se agregan al objeto item de paypal para mpstrarse en el detalle de compra de Paypal.
        for (CarritoAlbum carritoAlbum : carritoAlbums) {
            Item item = new Item();
            item.name(carritoAlbum.getAlbum().getNombre());

            String descripcionCorta = carritoAlbum.getAlbum().getDescripcion().substring(0, 50);

            item.description(descripcionCorta);
            item.unitAmount(new Money().currencyCode("USD").value(String.valueOf(carritoAlbum.getAlbum().getValor())));
            item.tax(new Money().currencyCode("USD").value(String.valueOf(impuestoPorProducto)));
            item.quantity(String.valueOf(carritoAlbum.getCantidad()));
            item.category("PHYSICAL_GOODS");
            item.sku("sku1");

            items.add(item);

            impuestoSunaTotalProductos += (impuestoPorProducto * carritoAlbum.getCantidad());
        }

        // Se configura el detalle de envio de los productos.
        ShippingDetail shippingDetail = new ShippingDetail();
        shippingDetail.name(new Name().fullName(nombre + " " + primerApellido + " " + segundoApellido));
        shippingDetail.addressPortable(new AddressPortable()
                .addressLine1("Mi calle 9")
                .addressLine2("Mi Colonia")
                .adminArea1("Republic Dominicana")
                .adminArea2("Santo domingo")
                .postalCode("10207")
                .countryCode("DR"));

        // Se aplica formato a la cantidad del total de compra de los productos
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double totalConDecimales = Double.parseDouble(decimalFormat.format(carritoController.calcularTotal()));

        double envio = 20.00;
        double handling = 10.00;
        double descuentoEnvio = 20.00;

        double totalCompraConImpuesto = totalConDecimales + impuestoSunaTotalProductos + handling + (envio - descuentoEnvio);

        // Se agrega la informacion calculada con los items en la unidad de compra.
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
        purchaseUnitRequest.items(items);
        purchaseUnitRequest.shippingDetail(shippingDetail);
        purchaseUnitRequest.amountWithBreakdown(new AmountWithBreakdown()
                .currencyCode("USD")
                .value(String.valueOf(totalCompraConImpuesto))
                .amountBreakdown(new AmountBreakdown()
                        .itemTotal(new Money()
                                .currencyCode("USD")
                                .value(String.valueOf(totalConDecimales)))
                        .shipping(new Money()
                                .currencyCode("USD")
                                .value(String.valueOf(envio)))
                        .handling(new Money()
                                .currencyCode("USD")
                                .value(String.valueOf(handling)))
                        .taxTotal(new Money()
                                .currencyCode("USD")
                                .value(String.valueOf(impuestoSunaTotalProductos)))
                        .shippingDiscount(new Money()
                                .currencyCode("USD")
                                .value(String.valueOf(descuentoEnvio)))));

        // Se agrega la unidad de compra a la lista y a la orden final de compra.
        purchaseUnitRequests.add(purchaseUnitRequest);
        this.orderRequest.purchaseUnits(purchaseUnitRequests);

        payer.addressPortable(purchaseUnitRequest.shippingDetail().addressPortable());
        this.orderRequest.payer(payer);

        Gson gson = new Gson();
        LOGGER.info(gson.toJson(purchaseUnitRequests));

        return this.orderRequest;
    }


	/**
	 * @return the carritoController
	 */
	public CarritoController getCarritoController() {
		return carritoController;
	}


	/**
	 * @param carritoController the carritoController to set
	 */
	public void setCarritoController(CarritoController carritoController) {
		this.carritoController = carritoController;
	}

    
}
