/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.util;

import br.edu.ifpr.bean.Categoria;
import br.edu.ifpr.bean.Estado;
import br.edu.ifpr.bean.Marca;
import br.edu.ifpr.bean.Municipio;
import br.edu.ifpr.bean.Proprietario;
import br.edu.ifpr.bean.Veiculo;
import br.edu.ifpr.dao.CategoriaDAO;
import br.edu.ifpr.dao.EstadoDAO;
import br.edu.ifpr.dao.MarcaDAO;
import br.edu.ifpr.dao.MunicipioDAO;
import br.edu.ifpr.dao.ProprietarioDAO;
import br.edu.ifpr.dao.VeiculoDAO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author lucas
 */
public class Teste {

    public static void main(String[] args) throws SQLException {
        Connection con = ConnectionFactory.createConnectionToMySQL();

        java.util.Date data = new java.util.Date();
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
       
        Estado estado = new Estado(0, "São Paulo", "SP");
        EstadoDAO estDAO = new EstadoDAO(con);
       // estDAO.create(estado);

        Municipio municipio = new Municipio(0, "Osasco", estado);
        MunicipioDAO munDAO = new MunicipioDAO(con);
      munDAO.create(municipio);

        Marca marca = new Marca(0, "Ford");
        MarcaDAO marcDAO = new MarcaDAO(con);
        //marcDAO.create(marca);

        Categoria categoria = new Categoria(0, "Utilitários");
        CategoriaDAO catDAO = new CategoriaDAO(con);
        //catDAO.create(categoria);

        Proprietario proprietario = new Proprietario(0, "Mario Mendes", "Rua Paraná, 638",
                "Centro", "10899736912", "988461691", "133355782", dataSql);
        ProprietarioDAO propDAO = new ProprietarioDAO(con);
        //propDAO.create(proprietario); 

        Veiculo veiculo = new Veiculo(0, "DAW-9852", dataSql, categoria, proprietario, marca, municipio);
        VeiculoDAO veicDAO = new VeiculoDAO(con);
      //veicDAO.create(veiculo);

        ///Municipio m = munDAO.retrieve(1);
        //  System.out.println("id : " + m.getId());
        //  System.out.println("Nome : " + m.getNome());
        //  System.out.println("Estado: " + m.getEstado().getNome());
    }
}
