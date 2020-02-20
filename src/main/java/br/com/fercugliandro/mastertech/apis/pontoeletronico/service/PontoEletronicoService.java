package br.com.fercugliandro.mastertech.apis.pontoeletronico.service;

import java.util.List;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.dto.PontoEletronicoDTO;
import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.PontoEletronico;

public interface PontoEletronicoService {

    PontoEletronico save(PontoEletronico pontoEletronico);
    
    List<PontoEletronicoDTO> findByUser(Long idUsuario);
    
}
