package br.com.fercugliandro.mastertech.apis.pontoeletronico.service.impl;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.PontoEletronico;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.repository.PontoEletronicoRepository;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.service.PontoEletronicoService;
import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoEletronicoServiceImpl implements PontoEletronicoService {

    @Autowired
    private PontoEletronicoRepository repository;


    @Override
    public Usuario findById(Long idUsuario) {
        return repository.findById(idUsuario).get();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }
}
