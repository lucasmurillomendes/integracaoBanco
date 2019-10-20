/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.dao;

import br.edu.ifpr.bean.Marca;
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
public class MarcaDAO implements Dao<Integer, Marca> {

    protected Connection con;

    public MarcaDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void create(Marca entity) {
        String sql = "INSERT INTO Marca (descricao) VALUES (?)";

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getDescricao());

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
    public Marca retrieve(Integer pk) {
        // Cria novo objeto
        Marca marca = null;

        // Define SQL
        String sql = "SELECT id, descricao FROM Marca WHERE id = ?";

        try {
            // Associa conexão
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            // Executa SQL
            ResultSet rs = query.executeQuery();

            // Recupera dados do conjunto
            while (rs.next()) {
                marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setDescricao(rs.getString("descricao"));
            }
            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return marca;
    }

    @Override
    public void update(Marca entity) {
        String sql = "UPDATE Marca SET descricao = ? WHERE id = ?";
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
        String sql = "DELETE FROM Marca WHERE id = ?";

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
    public List<Marca> findAll() {
        List<Marca> marcas = new LinkedList<>();

        String sql = "SELECT * FROM Marca";

        try {
            Statement query = con.createStatement();
            ResultSet result = query.executeQuery(sql);

            while (result.next()) {
                Marca marca = new Marca();
                marca.setId(result.getInt("id"));
                marca.setDescricao(result.getString("descricao"));

                marcas.add(marca);
            }

            query.close();
        } catch (SQLException e) {
            System.out.println("SQL exception ocorred " + e);
        }
        String conv = String.valueOf(marcas);

        for (int i = 0; i < marcas.size(); i++) {
            System.out.println("");
        }

        return marcas;
    }



}
