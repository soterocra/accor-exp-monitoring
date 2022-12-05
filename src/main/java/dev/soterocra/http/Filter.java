package dev.soterocra.http;

import io.quarkus.logging.Log;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

public class Filter {

    public static class RequestFilter implements ClientRequestFilter {

        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            Log.info("Realizando a chamada HTTP para URL: " + requestContext.getUri());
        }
    }

    public static class ResponseFilter implements ClientResponseFilter {

        @Override
        public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
            Log.info("Resposta da chamada HTTP: " + responseContext.getStatus());
        }
    }

}
