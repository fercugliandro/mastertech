package br.com.fercugliandro.mastertech.apis.usuarios.service;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario findById(Long idUsuario);

    Usuario save(Usuario usuario);
}
