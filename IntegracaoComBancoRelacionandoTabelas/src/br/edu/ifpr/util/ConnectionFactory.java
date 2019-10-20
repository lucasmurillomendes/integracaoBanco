/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aluno
 */
public class ConnectionFactory {
 
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/vendas?zeroDateTimeBehavior=convertToNull";
    
    public static Connection createConnectionToMySQL() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }
    
    
    public static void main(String[] args) throws SQLException {
        Connection con = createConnectionToMySQL();
        if (con != null) {
            System.out.println("Conexão obtida com sucesso! " + con);
            con.close();
        }
    }
}