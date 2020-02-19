package br.com.fercugliandro.mastertech.apis.pontoeletronico.service;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;

import java.util.List;

public interface PontoEletronicoService {

    Usuario findById(Long idUsuario);

    Usuario save(Usuario usuario);
}
