package jakarta.rest;

import config.ConstantServer;
import errores.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Juego;
import services.JuegosServices;

import java.time.LocalDateTime;
import java.util.List;

@Path(ConstantServer.JUEGOS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestJuegos {
    private final JuegosServices serv;

    @Inject
    public RestJuegos(JuegosServices serv) {
        this.serv = serv;
    }

    @GET
    public List<Juego> getAllJuegos() {
        return serv.getAllJuego().get();
    }



    @GET
    @Path(ConstantServer.JUEGOS_DE_COMPANY)
    public List<Juego> getJuegosDeCompany( @QueryParam (ConstantServer.COMPANY) String companyName) {
        return serv.getJuegosDeCompany(companyName).get();
    }
    @DELETE
    @Path(ConstantServer.JUEGOS_DE_COMPANY)
    public Response deleteJuegosDeCompany(@QueryParam(ConstantServer.COMPANY)String company){
        if (Boolean.TRUE.equals(serv.deleteJuegosDeCompany(company).get())){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiError.builder()
                            .message(ConstantServer.JUEGO_NO_ENCONTRADO)
                            .fecha(LocalDateTime.now())
                            .build())
                    .build();
        }
        }




    @POST
    public Juego addJuego(Juego juego) {
        return serv.addJuego(juego).get();
    }

    @PUT
    public Juego updateJuego( Juego updatedJuego) {
        return serv.updateJuego(updatedJuego).get();
    }

    @DELETE
    @Path(ConstantServer.JUEGO_ID_PATH)
    public Response deleteJuego(@PathParam(ConstantServer.JUEGO_ID) String id) {
       if (Boolean.TRUE.equals(serv.delete(id).get())){
           return Response.status(Response.Status.NO_CONTENT).build();
       }else {
           return Response.status(Response.Status.NOT_FOUND)
                   .entity(ApiError.builder()
                           .message(ConstantServer.JUEGO_NO_ENCONTRADO)
                           .fecha(LocalDateTime.now())
                           .build())
                   .build();
       }
    }

    @DELETE
    @Path(ConstantServer.DELETE_MULTIPLE)
    public Response deleteJuego(@QueryParam(ConstantServer.JUEGO_IDS)List<String> j) {
        if ((serv.deleteListaJuegos(j).get())>0){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiError.builder()
                            .message(ConstantServer.JUEGO_NO_ENCONTRADO)
                            .fecha(LocalDateTime.now())
                            .build())
                    .build();
        }
    }
}

