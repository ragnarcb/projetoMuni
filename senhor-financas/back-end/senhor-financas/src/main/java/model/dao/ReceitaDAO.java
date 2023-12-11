package model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.vo.ReceitaVO;

public class ReceitaDAO {

    public ReceitaVO cadastrarReceitaDAO(ReceitaVO receitaVO) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReceitaVO novaReceita = null;

        String query = "INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, receitaVO.getIdUsuario());
            pstmt.setString(2, receitaVO.getDescricao());
            pstmt.setBigDecimal(3, receitaVO.getValor());
            pstmt.setObject(4, receitaVO.getDataReceita());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int idReceita = rs.getInt(1);
                    novaReceita = consultarReceitaDAO(idReceita);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar a receita: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return novaReceita;
    }

    public ArrayList<ReceitaVO> consultarTodasReceitasDAO(int idUsuario) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<ReceitaVO> listaReceitas = new ArrayList<>();
        String query = "SELECT * FROM receita WHERE idusuario = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ReceitaVO receita = new ReceitaVO();
                receita.setIdReceita(rs.getInt("idreceita"));
                receita.setIdUsuario(rs.getInt("idusuario"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setValor(rs.getBigDecimal("valor"));
                receita.setDataReceita(rs.getObject("datareceita", LocalDateTime.class));

                listaReceitas.add(receita);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar todas as receitas: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return listaReceitas;
    }

    public ReceitaVO consultarReceitaDAO(int idReceita) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ReceitaVO receita = null;

        String query = "SELECT * FROM receita WHERE idreceita = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idReceita);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                receita = new ReceitaVO();
                receita.setIdReceita(rs.getInt("idreceita"));
                receita.setIdUsuario(rs.getInt("idusuario"));
                receita.setDescricao(rs.getString("descricao"));
                receita.setValor(rs.getBigDecimal("valor"));
                receita.setDataReceita(rs.getObject("datareceita", LocalDateTime.class));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar a receita: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return receita;
    }

    public boolean atualizarReceitaDAO(ReceitaVO receitaVO) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        boolean atualizado = false;

        String query = "UPDATE receita SET descricao = ?, valor = ?, datareceita = ? WHERE idreceita = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, receitaVO.getDescricao());
            pstmt.setBigDecimal(2, receitaVO.getValor());
            pstmt.setObject(3, receitaVO.getDataReceita());
            pstmt.setInt(4, receitaVO.getIdReceita());

            int affectedRows = pstmt.executeUpdate();
            atualizado = affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar a receita. Detalhes: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return atualizado;
    }

    public boolean excluirReceitaDAO(int idReceita) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        boolean excluido = false;

        String query = "DELETE FROM receita WHERE idreceita = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idReceita);

            int affectedRows = pstmt.executeUpdate();
            excluido = affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir a receita: " + e.getMessage());
            e.printStackTrace(); // Adicione esta linha para imprimir o rastreamento da pilha
        } finally {
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return excluido;
    }
}
