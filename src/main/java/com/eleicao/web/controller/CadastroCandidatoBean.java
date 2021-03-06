package com.eleicao.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.DatatypeConverter;

import org.primefaces.event.FileUploadEvent;

import com.eleicao.web.modelo.Candidato;
import com.eleicao.web.service.CadastroCandidatoService;
import com.eleicao.web.service.NegocioException;
import com.eleicao.web.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCandidatoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCandidatoService cadastroCandidatoService;
	
	
	private Candidato candidato;
	
	@PostConstruct
	public void init() {
		this.limpar();
		
	}
	 
	public void limpar() {
		this.candidato = new Candidato();
	}

	public void salvar( ) {
		try {
			this.cadastroCandidatoService.salvar(candidato);
			FacesUtil.addSuccessMessage("Candidato salvo com sucesso");
			
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	
	public void upload(FileUploadEvent image) {
		String imagem = "data:image/png;base64," + DatatypeConverter.printBase64Binary(image.getFile().getContents());
		candidato.setImagem(imagem);
	}
	
	
	public boolean isEditando() {
		return this.candidato.getCodigo() != null;
	} 
	
	
	

}
