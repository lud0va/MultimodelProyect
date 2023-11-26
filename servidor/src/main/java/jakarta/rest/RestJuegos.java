package jakarta.rest;

import errores.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Juego;
import services.JuegosServices;

import java.time.LocalDateTime;
import java.util.List;

@Path("/juegos")
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
    @Path("/{jugadorId}")
    public List<Juego> getJuegosById(@PathParam("jugadorId") String id) {
        return serv.getJuegosDeJug(id).get();
    }

    @GET
    @Path("/juegosDeCompany")
    public List<Juego> getJuegosDeCompany( @QueryParam ("company") String companyName) {
        return serv.getJuegosDeCompany(companyName).get();
    }
    @DELETE
    @Path("/juegosDeCompany")
    public Response deleteJuegosDeCompany(@QueryParam("company")String company){
        if (Boolean.TRUE.equals(serv.deleteJuegosDeCompany(company).get())){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiError.builder()
                            .message("juego no encontrado")
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
    @Path("/{juegoId}")
    public Response deleteJuego(@PathParam("juegoId") String id) {
       if (Boolean.TRUE.equals(serv.delete(id).get())){
           return Response.status(Response.Status.NO_CONTENT).build();
       }else {
           return Response.status(Response.Status.NOT_FOUND)
                   .entity(ApiError.builder()
                           .message("juego no encontrado")
                           .fecha(LocalDateTime.now())
                           .build())
                   .build();
       }
    }

    @DELETE
    @Path("/deleteMultiple")
    public Response deleteJuego(@QueryParam("juegoIds")List<String> j) {
        if ((serv.deleteListaJuegos(j).get())>0){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiError.builder()
                            .message("juego no encontrado")
                            .fecha(LocalDateTime.now())
                            .build())
                    .build();
        }
    }
}

