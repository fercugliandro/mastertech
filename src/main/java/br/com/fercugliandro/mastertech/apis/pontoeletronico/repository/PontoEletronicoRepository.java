package br.com.fercugliandro.mastertech.apis.pontoeletronico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fercugliandro.mastertech.apis.pontoeletronico.model.PontoEletronico;
import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;

@Repository
public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {
	
	List<PontoEletronico> findByUsuario(Usuario usuario);

}
