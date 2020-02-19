package br.com.fercugliandro.mastertech.apis.usuarios.repository;

import br.com.fercugliandro.mastertech.apis.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
