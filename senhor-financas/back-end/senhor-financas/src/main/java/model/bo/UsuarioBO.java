package model.bo;

import java.util.ArrayList;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

    public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
    }

    public ArrayList<UsuarioVO> consultarTodosUsuariosBO(int idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.consultarTodosUsuariosDAO(idUsuario);
    }

    public UsuarioVO consultarUsuarioBO(int idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.consultarUsuarioDAO(idUsuario);
    }

    // Atualize o método atualizarUsuarioBO
    public boolean atualizarUsuarioBO(UsuarioVO usuarioVO) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        System.out.println("Tentando atualizar o usuário com ID: " + usuarioVO.getIdUsuario());
        return usuarioDAO.atualizarUsuarioDAO(usuarioVO);
    }

    // Atualize o método excluirUsuarioBO
    public boolean excluirUsuarioBO(UsuarioVO usuarioVO) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        System.out.println("Tentando excluir o usuário com ID:" + usuarioVO.getIdUsuario());
        return usuarioDAO.excluirUsuarioDAO(usuarioVO.getIdUsuario());
    }

    public UsuarioVO realizarLoginBo(UsuarioVO usuarioVo) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.realizarLoginDao(usuarioVo);
    }

}
