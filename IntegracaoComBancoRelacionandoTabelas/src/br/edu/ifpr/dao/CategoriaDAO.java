
package br.edu.ifpr.dao;

import br.edu.ifpr.bean.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class CategoriaDAO implements Dao<Integer, Categoria> {

    protected Connection con;

    public CategoriaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void create(Categoria entity) {
        String sql = "INSERT INTO Categoria (descricao) VALUES (?)";

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getDescricao());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }

            query.close();

        } catch (SQLException e) {
            System.out.println("SQL exception ocorred " + e);
        }

    }

    @Override
    public Categoria retrieve(Integer pk) {
        Categoria categoria = null;

        String sql = "SELECT id, descricao FROM Categoria WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            query.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occured " + e);
        }

        return categoria;
    }

    @Override
    public void update(Categoria entity) {
        String sql = "UPDATE Categoria SET descricao = ? WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getDescricao());
            query.setInt(2, entity.getId());

            query.executeUpdate();

            query.close();
        } catch (SQLException e) {
           System.out.println("SQL exception ocorred " + e);
        }
    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM Categoria WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            query.executeUpdate();

            query.close();
            
        } catch (SQLException e) {
           System.out.println("SQL exception ocorred " + e);
        }
    }

    @Override
    public List<Categoria> findAll() {
        List<Categoria> categorias = new LinkedList<>();
        
        String sql = "SELECT * FROM Categoria";
        
        try {
            Statement query = con.createStatement();
            ResultSet result = query.executeQuery(sql);
            
            while (result.next()) {                
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id"));
                categoria.setDescricao(result.getString("descricao"));
                
                categorias.add(categoria);
            }
            
            query.close();
        } catch (SQLException e) {
             System.out.println("SQL exception ocorred " + e);
        }
        
        return categorias;
    }
    
     public Integer retornaQTD() throws SQLException {
        String sql = "SELECT id, descricao FROM Categoria";

        // Associa conexão
        Statement query = con.createStatement();
        // Executa SQL
        ResultSet rs = query.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
            i = rs.getRow();
        }

        return i + 1;
    }
}
