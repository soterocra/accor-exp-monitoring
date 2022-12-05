package dev.soterocra.http;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@RegisterProvider(Filter.class)
@RegisterRestClient(baseUri = "https://api.telegram.org")
public interface TelegramClient {

    @POST
    @Path("/bot{botToken}/sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    void sendMessage(@PathParam String botToken, SendMessageObject message);

}
