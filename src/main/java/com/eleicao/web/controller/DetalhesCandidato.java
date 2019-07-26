package com.eleicao.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.eleicao.web.modelo.Candidato;

@Named
@ViewScoped
public class DetalhesCandidato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Candidato candidatoSelecionado;
	private Candidato candidato;
	
	
	@PostConstruct
	public void init() {
		this.limpar();
		
	}
	
	public void limpar() {
		this.candidato = new Candidato();
	}

}
