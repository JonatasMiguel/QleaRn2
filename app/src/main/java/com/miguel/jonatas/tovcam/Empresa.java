package com.miguel.jonatas.tovcam;


public class Empresa {
    private String Nome;
    private String Cnpj;
    private String Localizacao;

    public Empresa(String nome, String cnpj, String localizacao) {
        Nome = nome;
        Cnpj = cnpj;
        Localizacao = localizacao;
    }

    public Empresa() {
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public String getLocalizacao() {
        return Localizacao;
    }

    public void setLocalizacao(String localizacao) {
        Localizacao = localizacao;
    }

    public String toString(){
        return "Nome: "+getNome()+"  CNPJ: "+getCnpj();
    }
}
