package com.k1rard.tiendamusicalweb.paypal;

import com.google.gson.Gson;
import com.k1rard.tiendamusicalweb.session.SessionBean;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author k1rard
 * Servlet que configura el boton de compra de paypal
 */
@WebServlet("/PaypalServlet")
public class PaypalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private SessionBean sessionBean;

    /**
     * Objeto que permite mostrar los mensajes de log en la consola del servidor o en un archivo externo.
     */
    private static final Logger LOGGER = LogManager.getLogger(PaypalServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        LOGGER.info("Generando orden de compra.");
        PaypalCreateOrder paypalCreateOrder = new PaypalCreateOrder();

        LOGGER.info("Entro al objeto de sesion");
        HttpResponse<Order> order = paypalCreateOrder.crearOrden(sessionBean);
        Gson gson = new Gson();
        out.write(gson.toJson(order));
    }
}
