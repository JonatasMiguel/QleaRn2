package com.miguel.jonatas.tovcam;
import java.util.List;
import java.util.Map;

/**
 * Created by Jonatas on 18/03/2017.
 */
import java.io.Serializable;
public class Funcionario implements Serializable{
    private String Cargo;
    private String Cpf;
    private String Nome;
    private String Senha;
    private Map<String,TreinamentosRealizados> TreinamentosRealizados;//codigo do treinamento e seu respectivo objeto

    public Funcionario(String cargo, String cpf, String nome, String senha, Map<String,TreinamentosRealizados> treinamentosRealizados) {
        Cargo = cargo;
        Cpf = cpf;
        Nome = nome;
        Senha = senha;
        TreinamentosRealizados = treinamentosRealizados;
    }



    public Map<String, TreinamentosRealizados> getTreinamentosRealizados() {
        return TreinamentosRealizados;
    }

    public void setTreinamentosRealizados(Map<String,TreinamentosRealizados> treinamentosRealizados) {
        TreinamentosRealizados = treinamentosRealizados;
    }

    public Funcionario() {
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }



}
