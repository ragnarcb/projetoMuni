package model.bo;

import java.util.ArrayList;

import model.dao.ReceitaDAO;
import model.vo.ReceitaVO;

public class ReceitaBO {

    public ReceitaVO cadastrarReceitaBO(ReceitaVO receitaVO) {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.cadastrarReceitaDAO(receitaVO);
    }

    public ArrayList<ReceitaVO> consultarTodasReceitasBO(int idUsuario) {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.consultarTodasReceitasDAO(idUsuario);
    }

    public ReceitaVO consultarReceitaBO(int idReceita) {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        return receitaDAO.consultarReceitaDAO(idReceita);
    }

    // Atualize o método atualizarReceitaBO
    public boolean atualizarReceitaBO(ReceitaVO receitaVO) {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        System.out.println("Tentando atualizar a receita com ID: " + receitaVO.getIdReceita());
        return receitaDAO.atualizarReceitaDAO(receitaVO);
    }

    public boolean excluirReceitaBO(ReceitaVO receitaVO) {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        System.out.println("Tentando excluir a receita com ID:" + receitaVO.getIdReceita());
        return receitaDAO.excluirReceitaDAO(receitaVO.getIdReceita());
    }
}
