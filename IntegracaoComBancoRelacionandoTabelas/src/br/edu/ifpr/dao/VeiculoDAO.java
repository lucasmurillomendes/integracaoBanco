/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.dao;

import br.edu.ifpr.bean.Categoria;
import br.edu.ifpr.bean.Marca;
import br.edu.ifpr.bean.Municipio;
import br.edu.ifpr.bean.Proprietario;
import br.edu.ifpr.bean.Veiculo;
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
public class VeiculoDAO implements Dao<Integer, Veiculo> {

    protected Connection con;

    public VeiculoDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void create(Veiculo entity) {
        String sql = "INSERT INTO Veiculo (placa, ano, Categoria_id, "
                + "Proprietario_id, Marca_id, Municipio_id) VALUES (?,?,?,?,?,?)";

        if (entity.getCategoria().getId() == 0
                || entity.getPropietario().getId() == 0
                || entity.getMarca().getId() == 0
                || entity.getMunicipio().getId() == 0) {

            //Verifica e obriga a ter as fks para ter um Veiculo
            CategoriaDAO catDao = new CategoriaDAO(con);
            catDao.create(entity.getCategoria());

            ProprietarioDAO propDao = new ProprietarioDAO(con);
            propDao.create(entity.getPropietario());

            MarcaDAO marcDao = new MarcaDAO(con);
            marcDao.create(entity.getMarca());

            MunicipioDAO munDao = new MunicipioDAO(con);
            munDao.create(entity.getMunicipio());

        }

        try {
            PreparedStatement query = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, entity.getPlaca());
            query.setDate(2, entity.getAno());
            query.setInt(3, entity.getCategoria().getId());
            query.setInt(4, entity.getPropietario().getId());
            query.setInt(5, entity.getMarca().getId());
            query.setInt(6, entity.getMunicipio().getId());

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
    public Veiculo retrieve(Integer pk) {
        Veiculo veiculo = null;

        String sql = "SELECT id, placa, ano, Categoria_id, Proprietario_id,"
                + "Marca_id, Municipio_id FROM Veiculo WHERE id = ?";

        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, pk);

            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setAno(rs.getDate("ano"));

                CategoriaDAO catDao = new CategoriaDAO(con);
                Categoria categoria = catDao.retrieve(rs.getInt("Categoria_id"));
                veiculo.setCategoria(categoria);

                ProprietarioDAO propDao = new ProprietarioDAO(con);
                Proprietario proprietario = propDao.retrieve(rs.getInt("Proprietario_id"));
                veiculo.setPropietario(proprietario);

                MarcaDAO marcDao = new MarcaDAO(con);
                Marca marca = marcDao.retrieve(rs.getInt("Marca_id"));
                veiculo.setMarca(marca);

                MunicipioDAO munDao = new MunicipioDAO(con);
                Municipio municipio = munDao.retrieve(rs.getInt("Municipio_id"));
                veiculo.setMunicipio(municipio);
            }

            query.close();

        } catch (SQLException e) {
            System.out.println("SQL execption occured " + e);
        }

        return veiculo;
    }

    @Override
    public void update(Veiculo entity) {
           String sql = "UPDATE Veiculo SET placa = ?, ano = ?, Categoria_id = ?,"
                   + "Proprietario_id = ?, Marca_id = ?, Municipio_id = ? WHERE id = ?";
        
        try {
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, entity.getPlaca());
            query.setDate(2, entity.getAno());
            query.setInt(3, entity.getCategoria().getId());
            query.setInt(4, entity.getPropietario().getId());
            query.setInt(5, entity.getMarca().getId());
            query.setInt(6, entity.getMunicipio().getId());
            query.setInt(7, entity.getId());
            
            query.executeUpdate();  

            query.close();
            
        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
    }

    @Override
    public void delete(Integer pk) {
        String sql = "DELETE FROM Veiculo WHERE id = ?";

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
    public List<Veiculo> findAll() {
        List<Veiculo> veiculos = new LinkedList<>();

        // Define SQL
        String sql = "SELECT id, placa, ano, Categoria_id, Proprietario_id, Marca_id,"
                + "Municipio_id FROM Veiculo";

        try {
            // Associa conexão
            Statement query = con.createStatement();
            // Executa SQL
            ResultSet rs = query.executeQuery(sql);

            // Recupera dados do conjunto
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setAno(rs.getDate("ano"));

                CategoriaDAO catDao = new CategoriaDAO(con);
                Categoria categoria = catDao.retrieve(rs.getInt("Categoria_id"));
                veiculo.setCategoria(categoria);

                ProprietarioDAO propDao = new ProprietarioDAO(con);
                Proprietario proprietario = propDao.retrieve(rs.getInt("Proprietario_id"));
                veiculo.setPropietario(proprietario);

                MarcaDAO marcDao = new MarcaDAO(con);
                Marca marca = marcDao.retrieve(rs.getInt("Marca_id"));
                veiculo.setMarca(marca);

                MunicipioDAO munDao = new MunicipioDAO(con);
                Municipio municipio = munDao.retrieve(rs.getInt("Municipio_id"));
                veiculo.setMunicipio(municipio);

                veiculos.add(veiculo);
            }

            query.close();

        } catch (SQLException ex) {
            System.out.println("SQL exception occured" + ex);
        }
        return veiculos;
    }

}