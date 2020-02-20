package br.com.fercugliandro.mastertech.apis.pontoeletronico.dto;

import java.io.Serializable;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.TipoPonto;

public class MarcacaoVO implements Serializable {

	private String marcacao;
	private TipoPonto tipoPonto;

	
	public MarcacaoVO(String marcacao, TipoPonto tipoPonto) {
		super();
		this.marcacao = marcacao;
		this.tipoPonto = tipoPonto;
	}
	
	
	public String getMarcacao() {
		return marcacao;
	}
	
	public TipoPonto getTipoPonto() {
		return tipoPonto;
	}
	
}
