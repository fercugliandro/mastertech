package br.com.fercugliandro.mastertech.apis.pontoeletronico.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

public class PontoEletronicoVO implements Serializable {

	private String dataMarcacao;
	private List<MarcacaoVO> marcacoes;
	private LocalTime horasTrabalhadas;
	
	public PontoEletronicoVO(String dataMarcacao, List<MarcacaoVO> marcacoes) {
		super();
		this.dataMarcacao = dataMarcacao;
		this.marcacoes = marcacoes;
	}
	
	
	public String getDataMarcacao() {
		return dataMarcacao;
	}
	public List<MarcacaoVO> getMarcacoes() {
		return marcacoes;
	}


	public LocalTime getHorasTrabalhadas() {
		return horasTrabalhadas;
	}


	public void setHorasTrabalhadas(LocalTime horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}


	public void setDataMarcacao(String dataMarcacao) {
		this.dataMarcacao = dataMarcacao;
	}


	public void setMarcacoes(List<MarcacaoVO> marcacoes) {
		this.marcacoes = marcacoes;
	}
	
}
