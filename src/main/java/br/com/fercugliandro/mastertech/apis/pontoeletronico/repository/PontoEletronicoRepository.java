package br.com.fercugliandro.mastertech.apis.pontoeletronico.repository;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoEletronicoRepository extends JpaRepository<Usuario, Long> {

}
