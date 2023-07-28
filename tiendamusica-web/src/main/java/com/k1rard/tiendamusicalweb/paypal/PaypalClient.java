package com.k1rard.tiendamusicalweb.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

/**
 * @author k1rard
 * Clase client de paypal que configura las clases necesarias para realizar las transacciones hacia paypal
 * Y tambien el CLIENT ID y SECRET ID de acceso a la aplicacion de Sandbox.
 */
public class PaypalClient {

    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
            "ASnHV5NTRChbqVAiBnIvOxDjW3IvK0GbgyhFYSuf_slOl884NSnHzenAVYOVASQgHw-Auyx5DX-EHJ7R",
            "EKpV-vvXzZ2LFY4bjrqRD5U3ozy9KJw9lZJzDlO510aqlFLlj99MAv-eYQrLDKgEugrxuYYfp0XMFXj3"
    );

    PayPalHttpClient client = new PayPalHttpClient(environment);

    public PayPalHttpClient client() {
        return this.client;
    }
}
