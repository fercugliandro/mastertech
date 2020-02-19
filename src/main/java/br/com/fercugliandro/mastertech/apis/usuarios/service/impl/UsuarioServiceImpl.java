package br.com.fercugliandro.mastertech.apis.usuarios.service.impl;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;
import br.com.fercugliandro.mastertech.apis.usuarios.repository.UsuarioRepository;
import br.com.fercugliandro.mastertech.apis.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(Long idUsuario) {
        return repository.findById(idUsuario).get();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }
}
