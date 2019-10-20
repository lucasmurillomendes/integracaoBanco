/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.bean;

import java.sql.Date;

/**
 *
 * @author lucas
 */
public class Veiculo {
    private int id;
    private String placa;
    private Date ano;
    private Categoria categoria;
    private Proprietario propietario;
    private Marca marca;
    private Municipio municipio;

    public Veiculo() {
    }

    public Veiculo(int id, String placa, Date ano, Categoria categoria,
            Proprietario propietario, Marca marca, Municipio municipio) {
        this.id = id;
        this.placa = placa;
        this.ano = ano;
        this.categoria = categoria;
        this.propietario = propietario;
        this.marca = marca;
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proprietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Proprietario propietario) {
        this.propietario = propietario;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    
}
