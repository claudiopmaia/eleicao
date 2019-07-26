package com.eleicao.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.eleicao.web.dao.CandidatoDAO;
import com.eleicao.web.modelo.Candidato;
import com.eleicao.web.service.NegocioException;
import com.eleicao.web.util.ExecutorRelatorio;
import com.eleicao.web.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCandidatoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	CandidatoDAO candidatoDAO;
	
	private Candidato candidatoSelecionado;
	
	private List<Candidato> candidatos = new ArrayList<>();
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletResponse response;
	
	public List<Candidato> getCandidatos(){
		return candidatos;		
	}	
	
	public void excluir() {
		try {
			candidatoDAO.excluir(candidatoSelecionado);
			this.candidatos.remove(candidatoSelecionado);
			FacesUtil.addSuccessMessage("Candidato " + candidatoSelecionado.getNome() + " excluido com sucesso");
		}catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Candidato getCandidatoSelecionado() {
		return candidatoSelecionado;
	}

	public void setFabricanteSelecionado(Candidato candidatoSelecionado) {
		this.candidatoSelecionado = candidatoSelecionado;
	}
	
	@PostConstruct
	public void inicializar() {
		candidatos = candidatoDAO.buscarTodos();
	}
	
	
	public void emitit() {
		Map<String,Object> parametros = new HashMap<>();
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/primeiro_relatorio.jasper",
											this.response, parametros, "PrimeiroRelatorio.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
			
		}else {
			FacesUtil.addErrorMessage("A execução do relatorio não retornou dados");
		}
	}
	
	
	

}
