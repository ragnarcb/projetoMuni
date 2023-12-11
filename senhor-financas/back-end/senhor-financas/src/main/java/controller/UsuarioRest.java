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
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

@Path("/usuario")
public class UsuarioRest {

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioVO cadastrarUsuarioController(UsuarioVO usuarioVO) {
        UsuarioBO usuarioBO = new UsuarioBO();
        return usuarioBO.cadastrarUsuarioBO(usuarioVO);
    }

    @GET
    @Path("/listar/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioVO> consultarTodosUsuariosController(@PathParam("idusuario") int idUsuario) {
        UsuarioBO usuarioBO = new UsuarioBO();
        return usuarioBO.consultarTodosUsuariosBO(idUsuario);
    }

    @GET
    @Path("/pesquisar/{idusuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioVO consultarUsuarioController(@PathParam("idusuario") int idUsuario) {
        UsuarioBO usuarioBO = new UsuarioBO();
        return usuarioBO.consultarUsuarioBO(idUsuario);
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuarioController(UsuarioVO usuarioVO) {
        UsuarioBO usuarioBO = new UsuarioBO();

        try {
            Boolean resultado = usuarioBO.atualizarUsuarioBO(usuarioVO);

            if (resultado != null && resultado) {
                return Response.ok().build();
            } else {
                return Response.serverError().entity("Erro ao atualizar o usuário.").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar o usuário: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirUsuarioController(UsuarioVO usuarioVO) {
        UsuarioBO usuarioBO = new UsuarioBO();

        try {
            Boolean resultado = usuarioBO.excluirUsuarioBO(usuarioVO);

            if (resultado != null && resultado) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir o usuário.").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir o usuário: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/logar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioVO realizarLogin(UsuarioVO usuarioVO) {
        UsuarioBO usuarioBO= new UsuarioBO();

        return usuarioBO.realizarLoginBo(usuarioVO);
    }

}
