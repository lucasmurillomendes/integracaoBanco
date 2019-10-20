/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.dao;

import br.edu.ifpr.bean.Estado;
import br.edu.ifpr.bean.Municipio;
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
public class MunicipioDAO implements Dao<Integer, Municipio> {

    protected Connection con;

    public MunicipioDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void create(Municipio entity) {
        String sql = "INSERT INTO Municipio (nome, Estado_id) VALUES (?,?)";

        if (entity.getEstado().getId() == 0) {
            //Verifica e obriga a ter um estado para ter um municipio
            EstadoDAO dao = new EstadoDAO(con);
            dao.create(entity.getEstado());
        }

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getNome());
            query.setInt(2, entity.getEstado().getId());

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
    public Municipio retrieve(Integer pk) {
        Municipio municipio = null;

        String sql = "SELECT id, nome, Estado_id FROM Municipio WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                municipio = new Municipio();
                municipio.setId(rs.getInt("id"));
                municipio.setNome(rs.getString("nome"));
                
                EstadoDAO dao = new EstadoDAO(con);
                Estado estado = dao.retrieve(rs.getInt("Estado_id"));                
                municipio.setEstado(estado);    
            }
            query.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occured " + e);
        }

        return municipio;
    }

    @Override
    public void update(Municipio entity) {
          String sql = "UPDATE Municipio SET nome = ?, Estado_id = ? WHERE id = ?";
        
        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getNome());
            query.setInt(2, entity.getEstado().getId());
            query.setInt(3, entity.getId());
            
            query.executeUpdate();  

            query.close();
            
        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
    }

    @Override
    public void delete(Integer pk) {
         String sql = "DELETE FROM Municipio WHERE id = ?";
        
        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);
            
            query.executeUpdate();            
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
    }

    @Override
    public List<Municipio> findAll() {
         List<Municipio> municipios = new LinkedList<>();
        
        // Define SQL
        String sql = "SELECT id, nome, Estado_id FROM Municipio";
        
        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);
            
            // Recupera dados do conjunto
            while(rs.next()) {
                Municipio municipio = new Municipio();
                municipio.setId(rs.getInt("id"));
                municipio.setNome(rs.getString("nome"));
                 // Cada municipio tem um estado
                EstadoDAO dao = new EstadoDAO(con);
                Estado estado = dao.retrieve(rs.getInt("Estado_id"));
                municipio.setEstado(estado);
                municipios.add(municipio);
            }
            
            query.close();
            
        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return municipios;
    }

}
