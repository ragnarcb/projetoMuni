package model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.vo.UsuarioVO;

public class UsuarioDAO {

    public UsuarioVO cadastrarUsuarioDAO(UsuarioVO usuarioVO) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsuarioVO novoUsuario = null;

        String query = "INSERT INTO usuario (nome, cpf, email, datanascimento, login, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, usuarioVO.getNome());
            pstmt.setString(2, usuarioVO.getCpf());
            pstmt.setString(3, usuarioVO.getEmail());
            pstmt.setObject(4, usuarioVO.getDatanascimento());
            pstmt.setString(5, usuarioVO.getLogin());
            pstmt.setString(6, usuarioVO.getSenha());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);
                    novoUsuario = consultarUsuarioDAO(idUsuario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o usuário: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return novoUsuario;
    }

    public ArrayList<UsuarioVO> consultarTodosUsuariosDAO(int idUsuario) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<UsuarioVO> listaUsuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";

        try {
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                UsuarioVO usuario = new UsuarioVO();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDatanascimento(rs.getObject("datanascimento", LocalDateTime.class));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar todos os usuários: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return listaUsuarios;
    }

    public UsuarioVO consultarUsuarioDAO(int idUsuario) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsuarioVO usuario = null;

        String query = "SELECT * FROM usuario WHERE idusuario = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioVO();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDatanascimento(rs.getObject("datanascimento", LocalDateTime.class));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o usuário: " + e.getMessage());
        } finally {
            Banco.closeResultSet(rs);
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return usuario;
    }

    public boolean atualizarUsuarioDAO(UsuarioVO usuarioVO) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        boolean atualizado = false;

        String query = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, datanascimento = ?, login = ?, senha = ? WHERE idusuario = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, usuarioVO.getNome());
            pstmt.setString(2, usuarioVO.getCpf());
            pstmt.setString(3, usuarioVO.getEmail());
            pstmt.setObject(4, usuarioVO.getDatanascimento());
            pstmt.setString(5, usuarioVO.getLogin());
            pstmt.setString(6, usuarioVO.getSenha());
            pstmt.setInt(7, usuarioVO.getIdUsuario());

            int affectedRows = pstmt.executeUpdate();
            atualizado = affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o usuário: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return atualizado;
    }

    public boolean excluirUsuarioDAO(int idUsuario) {
        Connection conn = Banco.getConnection();
        PreparedStatement pstmt = null;
        boolean excluido = false;

        String query = "DELETE FROM usuario WHERE idusuario = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idUsuario);

            int affectedRows = pstmt.executeUpdate();
            excluido = affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o usuário: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(pstmt);
            Banco.closeConnection(conn);
        }

        return excluido;
    }

    public UsuarioVO realizarLoginDao(UsuarioVO usuarioVo) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        String query = "SELECT idUsuario, nome, cpf, email, datanascimento FROM usuario"
                + " WHERE login like '" + usuarioVo.getLogin() + "'" + " AND senha like '" +  usuarioVo.getSenha()+ "'";

        try {
            resultado = stmt.executeQuery(query);
            if (resultado.next()) {
                usuarioVo.setIdUsuario(Integer.parseInt(resultado.getString(1)));
                usuarioVo.setNome(resultado.getString(2));
                usuarioVo.setCpf(resultado.getString(3));
                usuarioVo.setEmail(resultado.getString(4));

                String dataNascimentoStr = resultado.getString(5);
                if (dataNascimentoStr != null) {
                    LocalDateTime dataNascimento = LocalDateTime.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    usuarioVo.setDatanascimento(dataNascimento);
                } else {
                    // Lógica para lidar com data de nascimento nula, se necessário
                }
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar a query no método realizarLoginUsuarioDAO!");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeStatement(stmt);
            Banco.closeConnection(conn);
        }
        return usuarioVo;
    }


}
