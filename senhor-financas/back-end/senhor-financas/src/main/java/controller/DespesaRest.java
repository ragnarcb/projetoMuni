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
import model.bo.DespesaBO;
import model.vo.DespesaVO;

@Path("/despesa")
public class DespesaRest {

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DespesaVO cadastrarDespesaController(DespesaVO despesaVO) {
        DespesaBO despesaBO = new DespesaBO();
        despesaBO.cadastrarDespesaBO(despesaVO);
        return despesaVO;
    }

    @GET
    @Path("/listar/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DespesaVO> consultarTodasDespesasController(@PathParam("idusuario") int idUsuario) {
        DespesaBO despesaBO = new DespesaBO();
        return despesaBO.consultarTodasDespesasBO(idUsuario);
    }

    @GET
    @Path("/pesquisar/{iddespesa}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DespesaVO consultarDespesaController(@PathParam("iddespesa") int idDespesa) {
        DespesaBO despesaBO = new DespesaBO();
        return despesaBO.consultarDespesaBO(idDespesa);
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarDespesaController(DespesaVO despesaVO) {
        DespesaBO despesaBO = new DespesaBO();

        try {
            Boolean resultado = despesaBO.atualizarDespesaBO(despesaVO);

            if (resultado != null && resultado) {
                return Response.ok().build();
            } else {
                return Response.serverError().entity("Erro ao atualizar a despesa.").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar a despesa: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirDespesaController(DespesaVO despesaVO) {
        DespesaBO despesaBO = new DespesaBO();

        try {
            Boolean resultado = despesaBO.excluirDespesaBO(despesaVO);

            if (resultado != null && resultado) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir a despesa.").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir a despesa: " + e.getMessage()).build();
        }
    }
}
