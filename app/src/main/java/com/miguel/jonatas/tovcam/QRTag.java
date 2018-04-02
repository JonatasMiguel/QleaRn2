package com.miguel.jonatas.tovcam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QRTag implements Serializable {
private String Cod;
private List<Questao> ListaDeQuestoes;
public String getCod() {
	return Cod;
}
public void setCod(String cod) {
	Cod = cod;
}
public List<Questao> getListaDeQuestoes() {
	return ListaDeQuestoes;
}
public void setListaDeQuestoes(List<Questao> listaDeQuestoes) {
	ListaDeQuestoes = listaDeQuestoes;
}
public QRTag() {
	super();
	this.ListaDeQuestoes = new ArrayList<>();

}

}
