package br.com.igrejaidef.Idef.repository;

import br.com.igrejaidef.Idef.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {

    public UsuarioModel findByLogin(String login);

    public UsuarioModel findById(long id);
}
