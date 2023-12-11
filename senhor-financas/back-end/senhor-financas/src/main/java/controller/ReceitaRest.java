package controller;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.ReceitaBO;
import model.vo.ReceitaVO;

@Path("/receita")
public class ReceitaRest {

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReceitaVO cadastrarReceitaController(ReceitaVO receitaVO) {
        ReceitaBO receitaBO = new ReceitaBO();
        return receitaBO.cadastrarReceitaBO(receitaVO);
    }

    @GET
    @Path("/listar/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceitaVO> consultarTodasReceitasController(@PathParam("idusuario") int idUsuario) {
        ReceitaBO receitaBO = new ReceitaBO();
        return receitaBO.consultarTodasReceitasBO(idUsuario);
    }

    @GET
    @Path("/pesquisar/{idreceita}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ReceitaVO consultarReceitaController(@PathParam("idreceita") int idReceita) {
        ReceitaBO receitaBO = new ReceitaBO();
        return receitaBO.consultarReceitaBO(idReceita);
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarReceitaController(ReceitaVO receitaVO) {
        ReceitaBO receitaBO = new ReceitaBO();

        try {
            Boolean resultado = receitaBO.atualizarReceitaBO(receitaVO);

            if (resultado != null && resultado) {
                return Response.ok().build();
            } else {
                return Response.serverError().entity("Erro ao atualizar a receita.").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar a receita: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirReceitaController(ReceitaVO receitaVO) {
        ReceitaBO receitaBO = new ReceitaBO();

        try {
            Boolean resultado = receitaBO.excluirReceitaBO(receitaVO);

            if (resultado != null && resultado) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir a receita.").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir a receita: " + e.getMessage()).build();
        }
    }
}