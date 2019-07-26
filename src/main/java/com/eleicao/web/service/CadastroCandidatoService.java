package com.eleicao.web.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.eleicao.web.dao.CandidatoDAO;
import com.eleicao.web.modelo.Candidato;
import com.eleicao.web.util.jpa.Transactional;

public class CadastroCandidatoService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CandidatoDAO candidatoDAO;
	
	@Transactional
	public void salvar(Candidato candidato) throws NegocioException{
		
		if (candidato.getNome() == null || candidato.getNome().trim().equals("")) {
			throw new NegocioException("O nome do Candidato é obrigatorio");
		}
		
		this.candidatoDAO.salvar(candidato);
		
	}
}
