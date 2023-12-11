package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Banco {

	private static final String NAME_DATASOURCE = "SenacDS";

	public static Connection getConnection() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup(NAME_DATASOURCE);
			return ds.getConnection();
		} catch (Exception e) {
			System.out.println("Erro ao obter a Connection.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento da conexão.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static Statement getStatement(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				return conn.createStatement();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter o Statement.");
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do Statement.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
		try {
			if (conn != null && !conn.isClosed()) {
				return conn.prepareStatement(sql);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

	public static PreparedStatement getPreparedStatementWithPk(Connection conn, String sql) {
		try {
			if (conn != null && !conn.isClosed()) {
				return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
		}
		return null;
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do PreparedStatement.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static void closeResultSet(ResultSet result) {
		try {
			if (result != null && !result.isClosed()) {
				result.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do ResultSet");
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
