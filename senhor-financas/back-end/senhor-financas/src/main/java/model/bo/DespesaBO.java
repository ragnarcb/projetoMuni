package model.bo;

import java.util.ArrayList;
import model.dao.DespesaDAO;
import model.dao.ReceitaDAO;
import model.vo.DespesaVO;
import model.vo.ReceitaVO;

public class DespesaBO {

    public void cadastrarDespesaBO(DespesaVO despesaVO) {
        DespesaDAO despesaDAO = new DespesaDAO();
        despesaDAO.cadastrarDespesaDAO(despesaVO);
    }

    public ArrayList<DespesaVO> consultarTodasDespesasBO(int idUsuario) {
        DespesaDAO despesaDAO = new DespesaDAO();
        return despesaDAO.consultarTodasDespesasDAO(idUsuario);
    }

    public DespesaVO consultarDespesaBO(int idDespesa) {
        DespesaDAO despesaDAO = new DespesaDAO();
        return despesaDAO.consultarDespesaDAO(idDespesa);
    }

    // Atualize o método atualizarDespesaBO
    public boolean atualizarDespesaBO(DespesaVO despesaVO) {
        DespesaDAO despesaDAO = new DespesaDAO();
        System.out.println("Tentando atualizar a despesa com ID: " + despesaVO.getIdDespesa());
        return despesaDAO.atualizarDespesaDAO(despesaVO);
    }

    // Atualize o método excluirDespesaBO
    public boolean excluirDespesaBO(DespesaVO despesaVO) {
        DespesaDAO despesaDAO = new DespesaDAO();
        System.out.println("Tentando excluir a despesa com ID:" + despesaVO.getIdDespesa());
        return despesaDAO.excluirDespesaDAO(despesaVO.getIdDespesa());
    }

}
