package com.k1rard.tiendamusicalweb.controllers;

import com.k1rard.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.k1rard.tiendamusicalentities.dto.ArtistaAlbumDTO;
import com.k1rard.tiendamusicalentities.entities.CarritoAlbum;
import com.k1rard.tiendamusicalservices.service.AlbumService;
import com.k1rard.tiendamusicalservices.service.CarritoService;
import com.k1rard.tiendamusicalservices.service.HomeService;
import com.k1rard.tiendamusicalweb.enums.ColorEnum;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author k1rard Clase que controla el flujo de informacion para la pantalla de
 *         home de cualquier tipo de usuario.
 */
@Named
@ViewScoped
public class HomeController {

	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o
	 * en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

	/**
	 * Texto ingresado por el cliente en el buscador
	 */
	private String filtro;

	/**
	 * Lista obtenida a partir del filtro ingresado en el buscador
	 */
	private List<ArtistaAlbumDTO> artistasAlbumDTO;

	/**
	 * Se inyecta el objeto de Spring para obtener los metodos de logica de negocio
	 */
	@Autowired
	private HomeService homeServiceImpl;

	/**
	 * Objeto que almacena informacion en sesion
	 */
	@Inject
	private SessionBean sessionBean;

	/**
	 * Se inyecta el objeto de Spring para obtener los metodos de logica de negocio
	 * del carrito.
	 */
	@Autowired
	private CarritoService carritoService;

	/**
	 * Objeto que permitira generar el modelo para mostrar la grafica de albums top
	 * ten vendidos.
	 */
	private BarChartModel barChartModel;

	/**
	 * Objeto que contien los metodos de logica de negocio para los albums.
	 */
	@Autowired
	private AlbumService albumService;

	/**
	 * Metodo que inicializa la pantalla
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("INFO");
		LOGGER.warn("WARN");
		LOGGER.error("ERROR");
		LOGGER.fatal("FATAL");
		
	}
	
	public void initialize() {
		if(this.sessionBean.getPersona().getRol().getIdRol() == 3) {
			this.crearGraficaTopTenAlbumsVendidos();
		}
	}

	/**
	 * Metodo que permite obtener los albums de los artistas encontrados en la base
	 * de datos con respecto al filtro ingresado por el cliente.
	 */
	public void consultarAlbumsArtistasPorFiltro() {
		this.artistasAlbumDTO = this.homeServiceImpl.consultarAlbumsFiltro(this.filtro);

		if (this.artistasAlbumDTO != null) {
			this.artistasAlbumDTO.forEach(a -> {
				LOGGER.info("Artista: " + a.getArtista().getNombre());
			});
		}
	}

	/**
	 * Metodo que permite ver el detalle del album seleccionado por el cliente
	 * 
	 * @param artistaAlbumDTO {@link ArtistaAlbumDTO} Objeto con el album
	 *                        seleccionado.
	 */
	public void verDetalleAlbum(ArtistaAlbumDTO artistaAlbumDTO) {
		this.sessionBean.setArtistaAlbumDTO(artistaAlbumDTO);
		try {
			CommonUtils.redireccionar("/pages/cliente/detalle.xhtml");
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "UPS!",
					"Hubo un error al intentar ingresar a la pagina solicitada, Favor de contactar con soporte.");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que permite agregar un album en el carrito de compra.
	 * 
	 * @param albumDTO {@link ArtistaAlbumDTO} album a agregar al carrito.
	 */
	public void agregarAlbumCarrito(ArtistaAlbumDTO artistaAlbumDTO) {
		LOGGER.info("Agregando album: " + artistaAlbumDTO.getAlbum().getNombre());

		CarritoAlbum carritoAlbum = this.carritoService.guardarAlbumsCarrito(artistaAlbumDTO,
				this.sessionBean.getPersona().getCarrito(), 1);

		this.sessionBean.getPersona().getCarrito().getCarritosAlbum().add(carritoAlbum);
	}

	/**
	 * Metodo que permite generar la grafica de top ten de albums vendidos para el
	 * administrador
	 */
	public void crearGraficaTopTenAlbumsVendidos() {
		this.barChartModel = new BarChartModel();
		ChartData chartData = new ChartData();

		// Se consulta la informacion de los albums mas vendidos
		List<AlbumTopTenDTO> albumsTopTen = this.albumService.consultarAlbumsTopTen();

		String[] meses = new DateFormatSymbols().getMonths();

		// Se itera la lista de albums topten y se integran a la grafica.
		for (int i = 0; i < albumsTopTen.size(); i++) {

			BarChartDataSet barChartDataSet = new BarChartDataSet();
			barChartDataSet.setLabel(albumsTopTen.get(i).getCarritoAlbum().getAlbum().getNombre());
			barChartDataSet.setBackgroundColor(ColorEnum.values()[i].getDescripcion());
			barChartDataSet.setBorderWidth(1);

			List<Number> numeros = new ArrayList<Number>();

			// Se obtiene el mes en el que se realizo la compra del album
			String mesCompra = albumsTopTen.get(i).getCarritoAlbum().getFechaCompra().getMonth()
					.getDisplayName(TextStyle.FULL, new Locale("es"));

			for (int j = 0; j < meses.length; j++) {
				String mes = meses[j];

				if (mes.equals(mesCompra)) {
					numeros.add(albumsTopTen.get(i).getCantidadSuma());
				} else {
					numeros.add(0);
				}
			}
			
			barChartDataSet.setData(numeros);
			chartData.addChartDataSet(barChartDataSet);
		}
		
		List<String> etiquetaMeses = new ArrayList<String>();
		etiquetaMeses.add("Enero");
		etiquetaMeses.add("Febrero");
		etiquetaMeses.add("Marzo");
		etiquetaMeses.add("Abril");
		etiquetaMeses.add("Mayo");
		etiquetaMeses.add("Junio");
		etiquetaMeses.add("Julio");
		etiquetaMeses.add("Agosto");
		etiquetaMeses.add("Septiembre");
		etiquetaMeses.add("Octubre");
		etiquetaMeses.add("Noviembre");
		etiquetaMeses.add("Diciembre");
		
		chartData.setLabels(etiquetaMeses);
		this.barChartModel.setData(chartData);
		
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
		
		Title title = new Title();
		title.setDisplay(true);
		title.setText("Top 10 Albums mas vendidos por mes");
		
		options.setTitle(title);
		
		this.barChartModel.setOptions(options);
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<ArtistaAlbumDTO> getArtistasAlbumDTO() {
		return artistasAlbumDTO;
	}

	public void setArtistasAlbumDTO(List<ArtistaAlbumDTO> artistasAlbumDTO) {
		this.artistasAlbumDTO = artistasAlbumDTO;
	}

	public HomeService getHomeServiceImpl() {
		return homeServiceImpl;
	}

	public void setHomeServiceImpl(HomeService homeServiceImpl) {
		this.homeServiceImpl = homeServiceImpl;
	}

	/**
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * @return the carritoService
	 */
	public CarritoService getCarritoService() {
		return carritoService;
	}

	/**
	 * @param carritoService the carritoService to set
	 */
	public void setCarritoService(CarritoService carritoService) {
		this.carritoService = carritoService;
	}

	/**
	 * @return the barChartModel
	 */
	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	/**
	 * @param barChartModel the barChartModel to set
	 */
	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	/**
	 * @return the albumService
	 */
	public AlbumService getAlbumService() {
		return albumService;
	}

	/**
	 * @param albumService the albumService to set
	 */
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

}
