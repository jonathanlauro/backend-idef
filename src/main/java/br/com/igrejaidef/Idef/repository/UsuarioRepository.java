package br.com.igrejaidef.Idef.repository;

import br.com.igrejaidef.Idef.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {

    UsuarioModel findByLogin(String login);

    UsuarioModel findById(long id);

    @Query("SELECT u FROM UsuarioModel u ORDER BY u.id DESC ")
    List<UsuarioModel> listarTodosDescId();
}
