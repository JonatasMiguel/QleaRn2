package com.miguel.jonatas.tovcam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonatas on 18/03/2017.
 */

public class Questao implements Serializable{
    private String Pontos;//pontuação desta questao (dados pelo sistema)
	private String RespostaBinaria;
    private String Texto;//texto da pergunta
    private String Titulo;
    private String Codigo;//codigo da questao
    private String Tipo;//0-binaria 1-multipla escolha(apenas uma correta) 2-multipla escolha(mais de uma resposta correta)
    private String Dificuldade;//1 ate 10
    private String Imagem;
    private String Som;
    private List<Alternativa> Alternativas;
    private String CodTreinamento;//nomedotreinament:cnpj
    
    
    
    public String getCodTreinamento() {
		return CodTreinamento;
	}

	public void setCodTreinamento(String codTreinamento) {
		CodTreinamento = codTreinamento;
	}

	public String getRespostaBinaria() {
 		return RespostaBinaria;
 	}

 	public void setRespostaBinaria(String respostaBinaria) {
 		RespostaBinaria = respostaBinaria;
 	}

    public String getTipo() {
		return Tipo;
	}

	public List<Alternativa> getAlternativas() {
		return Alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		Alternativas = alternativas;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getDificuldade() {
		return Dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		Dificuldade = dificuldade;
	}

	public String getImagem() {
		return Imagem;
	}

	public void setImagem(String imagem) {
		Imagem = imagem;
	}

	public String getSom() {
		return Som;
	}

	public void setSom(String som) {
		Som = som;
	}

	public Questao() {
		this.Alternativas = new ArrayList<Alternativa>();
    }

   



	public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getPontos() {
        return Pontos;
    }

    public void setPontos(String pontos) {
        Pontos = pontos;
    }

    

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String toString(){
        return "Titulo: "+getTitulo()+" ";
    }
}
