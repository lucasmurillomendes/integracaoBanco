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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author lucas
 */
public class Teste {

    public static void main(String[] args) throws SQLException {
            java.util.Date data = new java.util.Date();
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
        
        Estado estado = new Estado(0, "São Paulo", "SP");
        Municipio municipio = new Municipio(0, "Osasco", estado);
       Marca marca = new Marca(0, "Ford");
      Categoria categoria = new Categoria(0, "Utilitários");
      Proprietario proprietario = new Proprietario(0, "Mario Mendes", "Rua Paraná, 638",
              "Centro", "10899736912", "988461691", "133355782", dataSql);
      Veiculo veiculo = new Veiculo(0, "DAW-9852", dataSql, categoria, proprietario, marca, municipio);

        Connection con = ConnectionFactory.createConnectionToMySQL();

       EstadoDAO estDAO = new EstadoDAO(con);
        MunicipioDAO munDAO = new MunicipioDAO(con);
        MarcaDAO marcDAO = new MarcaDAO(con);
        CategoriaDAO catDAO = new CategoriaDAO(con);
        ProprietarioDAO propDAO = new ProprietarioDAO(con);
        VeiculoDAO veicDAO = new VeiculoDAO(con);
      // estDAO.create(estado);
    //  munDAO.create(municipio);
     //  marcDAO.create(marca);
     //  catDAO.create(categoria);
     // propDAO.create(proprietario);
      veicDAO.create(veiculo);

        ///Municipio m = munDAO.retrieve(1);
        //  System.out.println("id : " + m.getId());
        //  System.out.println("Nome : " + m.getNome());
        //  System.out.println("Estado: " + m.getEstado().getNome());
    }
}
