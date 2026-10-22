package br.dev.nicolas.dukemarket.dao;

import br.dev.nicolas.dukemarket.bean.Produto;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutosDAO {

    // 4.6.1 Strings DML (Data Manipulation Language)
    private static final String SQL_INSERT = "INSERT INTO produto(codBarras, "
            + "descricao, qtd, valorCompra, valorVenda) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL = "SELECT * FROM produto";
    private static final String SQL_SELECT_ID = "SELECT * FROM produto "
            + "WHERE id = ?";

    private static final String SQL_UPDATE = "UPDATE produto SET codBarras = ?,"
            + "descricao = ?, qtd = ?, valorCompra = ?, valorVenda = ? "
            + "WHERE id = ?";

    private static final String SQL_DELETE = "DELETE FROM produto WHERE id = ?";

    /**
     * Insere um usuario na base de dados Produto
     * 
     * @param p
     */
    public void create(Produto p) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());

            // Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Retorna todos os registros da tabela produto
     * 
     * @return
     */
    public List<Produto> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;
        List<Produto> listaProdutos = null;

        try {
            // Prepara a string de SELECT e executa a query.
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            // Carrega os dados do ResultSet rs, converte em Produto e
            // adiciona na lista de retorno.
            listaProdutos = new ArrayList<>();

            while (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("valorCompra"));
                p.setValorVenda(rs.getDouble("valorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
                listaProdutos.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Encerra a conexão com o banco, o statement e o resultset
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return listaProdutos;
    }

    /**
     * Retorna um produto da tabela produto.
     * 
     * @param id Identificação do Produto
     * @return
     */
    public Produto getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("valorCompra"));
                p.setValorVenda(rs.getDouble("valorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
            }

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt, rs);
        }

        return p;
    }

    /**
     * Atualiza um registro na tabela produto.
     * 
     * @param p Produto a ser atualizado.
     */
    public void update(Produto p) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());
            stmt.setInt(6, p.getId());

            // Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.INFO, null,
                    "Update: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Exclui um produto com base do ID fornecido.
     * 
     * @param id Identificação do produto.
     */
    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            // Executa a query
            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.INFO, null,
                    "Delete: " + auxRetorno);

        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            // Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
}