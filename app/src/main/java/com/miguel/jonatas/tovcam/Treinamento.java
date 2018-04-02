package com.miguel.jonatas.tovcam;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Jonatas on 18/03/2017.
 */

public class Treinamento implements Serializable{
    private String Descricao;
    private String CnpjdaEmpresa;
    private String Nome;
    private Integer PontuacaoTotal;
    private ArrayList<String> Codigos;//ou lista com as questões

    public Integer getPontuacaoTotal() {
        return PontuacaoTotal;
    }

    public void setPontuacaoTotal(Integer pontuacaoTotal) {
        PontuacaoTotal = pontuacaoTotal;
    }

    public Treinamento(String descricao, String cnpjdaEmpresa, String nome, ArrayList<String> codigos) {
        Descricao = descricao;
        CnpjdaEmpresa = cnpjdaEmpresa;
        Nome = nome;
        Codigos = codigos;
    }

    public Treinamento() {

    }
   

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getCnpjdaEmpresa() {
        return CnpjdaEmpresa;
    }

    public void setCnpjdaEmpresa(String cnpjdaEmpresa) {
        CnpjdaEmpresa = cnpjdaEmpresa;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public ArrayList<String> getCodigos() {
        return Codigos;
    }

    public void setCodigos(ArrayList<String> codigos) {
        Codigos = codigos;
    }
}

