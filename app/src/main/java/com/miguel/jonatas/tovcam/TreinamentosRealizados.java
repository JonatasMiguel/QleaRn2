package com.miguel.jonatas.tovcam;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.Map;

/**
 * Created by Jonatas on 18/03/2017.
 */

public class TreinamentosRealizados implements Serializable {
    private Integer PontuacaoTotal;
    private Map<String,String> Questaoresposta;//id da questão associado a resposta dada pelo usuario
    private String CodTreinamento;

    public TreinamentosRealizados() {
    }

    public TreinamentosRealizados(Integer pontuacaoTotal, Map<String, String> questaoresposta) {
        PontuacaoTotal = pontuacaoTotal;
        Questaoresposta = questaoresposta;
    }

    public Integer getPontuacaoTotal() {
        return PontuacaoTotal;
    }

    public void setPontuacaoTotal(Integer pontuacaoTotal) {
        PontuacaoTotal = pontuacaoTotal;
    }

    public Map<String, String> getQuestaoresposta() {
        return Questaoresposta;
    }

    public void setQuestaoresposta(Map<String, String> questaoresposta) {
        Questaoresposta = questaoresposta;
    }


    public String getCodTreinamento() {
        return CodTreinamento;
    }

    public void setCodTreinamento(String codTreinamento) {
        CodTreinamento = codTreinamento;
    }
}
