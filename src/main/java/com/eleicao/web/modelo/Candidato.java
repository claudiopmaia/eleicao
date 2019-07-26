package com.eleicao.web.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Candidato {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "nome_candidato")
	private String nome;
	private String partido;
	private Integer numero;
	private String coligacao;
	private String cidade;
	private String estado;
	
	@Column(name = "qtd_votos")
	private Integer qtdVotos;
	private String descricao;
	
	private String cargo;
	

	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Column(name = "ano_pleito")
	private Integer anoPleito;
	
	@Lob
	@Column(name="foto_politico")
	private byte[] fotoPolitico;
	
	@Column(columnDefinition = "longtext")
	private String imagem;
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getPartido() {
		return partido;
	}
	public void setPartido(String partido) {
		this.partido = partido;
	}
	public String getColigacao() {
		return coligacao;
	}
	public void setColigacao(String coligacao) {
		this.coligacao = coligacao;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getQtdVotos() {
		return qtdVotos;
	}
	public void setQtdVotos(Integer qtdVotos) {
		this.qtdVotos = qtdVotos;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Integer getAnoPleito() {
		return anoPleito;
	}
	public void setAnoPleito(Integer anoPleito) {
		this.anoPleito = anoPleito;
	}
	public byte[] getFotoPolitico() {
		return fotoPolitico;
	}
	public void setFotoPolitico(byte[] fotoPolitico) {
		this.fotoPolitico = fotoPolitico;
	}
	
	
	public String getImagem() {
		return imagem;
	}
	
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}
