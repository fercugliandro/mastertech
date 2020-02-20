package br.com.fercugliandro.mastertech.apis.pontoeletronico.dto;

import java.util.List;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;

public class PontoEletronicoDTO {
	
	private Usuario usuario;
	private List<PontoEletronicoVO> pontoEletronico;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PontoEletronicoVO> getPontoEletronico() {
		return pontoEletronico;
	}

	public void setPontoEletronico(List<PontoEletronicoVO> pontoEletronico) {
		this.pontoEletronico = pontoEletronico;
	}
}
