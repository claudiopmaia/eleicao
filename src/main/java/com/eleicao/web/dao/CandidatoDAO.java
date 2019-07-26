package com.eleicao.web.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.eleicao.web.modelo.Candidato;
import com.eleicao.web.service.NegocioException;
import com.eleicao.web.util.jpa.Transactional;

public class CandidatoDAO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em ;
	
	
	public void salvar(Candidato candidato) {
		em.merge(candidato);
	}


	@Transactional
	public void excluir(Candidato candidato) throws NegocioException {
		Candidato candidatoTemp = em.find(Candidato.class,candidato.getCodigo());
		em.remove(candidatoTemp);
		em.flush();
		
	}

	@SuppressWarnings("unchecked")
	public List<Candidato> buscarTodos() {
		return em.createQuery("from Candidato").getResultList();
	}


	public Candidato buscarPeloCodigo(Long codigo) {
		return em.find(Candidato.class, codigo);
	}

}
