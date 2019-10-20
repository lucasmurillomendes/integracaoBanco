/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.dao;

import br.edu.ifpr.bean.Estado;
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
public class EstadoDAO implements Dao<Integer, Estado> {

    protected Connection con;

    public EstadoDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void create(Estado entity) {
        String sql = "INSERT INTO Estado (nome, sigla) VALUES (?,?)";

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getNome());
            query.setString(2, entity.getSigla());

            query.executeUpdate();
            ResultSet rs = query.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                entity.setId(id);
            }

            query.close();

        } catch (SQLException e) {
            System.out.println("SQL exception ocorred " + e);
        }

    }

    @Override
    public Estado retrieve(Integer pk) {
        Estado estado = null;

        String sql = "SELECT id, nome, sigla FROM Estado WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                estado = new Estado();
                estado.setId(rs.getInt("id"));
                estado.setNome(rs.getString("nome"));
                estado.setSigla(rs.getString("sigla"));
            }
            query.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occured " + e);
        }

        return estado;
    }

    @Override
    public void update(Estado entity) {
        String sql = "UPDATE Estado SET nome = ?, sigla = ? WHERE id = ?";
        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getNome());
            query.setString(2, entity.getSigla());
            query.setInt(3, entity.getId());

            query.executeUpdate();

            query.close();
        } catch (SQLException e) {
            System.out.println("SQL exception ocorred " + e);
        }
    }

    @Override
    public void delete(Integer pk) {
         String sql = "DELETE FROM Estado WHERE id = ?";
        
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
    public List<Estado> findAll() {
           List<Estado> estados = new LinkedList<>();
        
        String sql = "SELECT * FROM Estado";
        
        try {
            Statement query = con.createStatement();
            ResultSet result = query.executeQuery(sql);
            
            while (result.next()) {                
                Estado estado = new Estado();
                estado.setId(result.getInt("id"));
                estado.setNome(result.getString("nome"));
                estado.setSigla(result.getString("sigla"));
                
                estados.add(estado);
            }
            
            query.close();
        } catch (SQLException e) {
             System.out.println("SQL exception ocorred " + e);
        }
        
        return estados;
    }

}
