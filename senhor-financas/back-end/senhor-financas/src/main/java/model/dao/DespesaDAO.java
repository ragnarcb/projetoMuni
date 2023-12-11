package model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.vo.DespesaVO;

public class DespesaDAO {

    public DespesaVO cadastrarDespesaDAO(DespesaVO despesaVO) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DespesaVO novaDespesa = null;

        String query = "INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (?, ?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, despesaVO.getIdUsuario());
            pstmt.setString(2, despesaVO.getDescricao());
            pstmt.setBigDecimal(3, despesaVO.getValor());
            pstmt.setObject(4, despesaVO.getDataVencimento());
            pstmt.setObject(5, despesaVO.getDataPagamento());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int idDespesa = rs.getInt(1);
                    novaDespesa = consultarDespesaDAO(idDespesa);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar a despesa: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return novaDespesa;
    }


    public ArrayList<DespesaVO> consultarTodasDespesasDAO(int idUsuario) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<DespesaVO> listaDespesas = new ArrayList<>();
        String query = "SELECT * FROM despesa WHERE idusuario = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                DespesaVO despesa = new DespesaVO();
                despesa.setIdDespesa(rs.getInt("iddespesa"));
                despesa.setIdUsuario(rs.getInt("idusuario"));
                despesa.setDescricao(rs.getString("descricao"));
                despesa.setValor(rs.getBigDecimal("valor"));
                despesa.setDataVencimento(rs.getObject("datavencimento", LocalDateTime.class));
                despesa.setDataPagamento(rs.getObject("datapagamento", LocalDateTime.class));

                listaDespesas.add(despesa);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar todas as despesas: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return listaDespesas;
    }

    public DespesaVO consultarDespesaDAO(int idDespesa) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DespesaVO despesa = new DespesaVO();

        String query = "SELECT * FROM despesa WHERE iddespesa = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idDespesa);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                despesa.setIdDespesa(rs.getInt("iddespesa"));
                despesa.setIdUsuario(rs.getInt("idusuario"));
                despesa.setDescricao(rs.getString("descricao"));
                despesa.setValor(rs.getBigDecimal("valor"));
                despesa.setDataVencimento(rs.getObject("datavencimento", LocalDateTime.class));
                despesa.setDataPagamento(rs.getObject("datapagamento", LocalDateTime.class));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a despesa: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return despesa;
    }

    public boolean atualizarDespesaDAO(DespesaVO despesaVO) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        boolean atualizado = false;

        String query = "UPDATE despesa SET descricao = ?, valor = ?, datavencimento = ?, datapagamento = ? WHERE iddespesa = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, despesaVO.getDescricao());
            pstmt.setBigDecimal(2, despesaVO.getValor());
            pstmt.setObject(3, despesaVO.getDataVencimento());
            pstmt.setObject(4, despesaVO.getDataPagamento());
            pstmt.setInt(5, despesaVO.getIdDespesa());

            int affectedRows = pstmt.executeUpdate();
            atualizado = affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a despesa: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return atualizado;
    }

    public boolean excluirDespesaDAO(int idDespesa) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        boolean excluido = false;

        String query = "DELETE FROM despesa WHERE iddespesa = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idDespesa);

            int affectedRows = pstmt.executeUpdate();
            excluido = affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir a despesa: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return excluido;
    }
}
