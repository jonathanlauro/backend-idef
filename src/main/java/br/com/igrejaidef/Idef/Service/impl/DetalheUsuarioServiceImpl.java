package br.com.igrejaidef.Idef.Service.impl;

import br.com.igrejaidef.Idef.data.DetalheUsuarioData;
import br.com.igrejaidef.Idef.model.UsuarioModel;
import br.com.igrejaidef.Idef.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioModel usuario = repository.findByLogin(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário: "+ username + " não encontrado!");
        }

        return new DetalheUsuarioData(usuario);
    }
}
