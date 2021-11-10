package br.com.igrejaidef.Idef.security;

import br.com.igrejaidef.Idef.data.DetalheUsuarioData;
import br.com.igrejaidef.Idef.model.Token;
import br.com.igrejaidef.Idef.model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    public static final int TOKEN_EXPIRACAO = 600_000;
    public static final String TOKEN_SENHA = "40c4c4f1-248d-4141-8e0b-c27707ba5513";

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UsuarioModel usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getLogin(),
                    usuario.getPassword(),
                    Arrays.stream(usuario.getRole().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
//                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuário ", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usuarioData.getUsername())
//                .withClaim("role", Arrays.asList(usuarioData.getAuthorities().toArray()))
                .withExpiresAt(new Date(System.currentTimeMillis()+(TOKEN_EXPIRACAO*2)))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));
        Token tk = new Token(token);
        response.setContentType("application/json");
        response.getWriter().print(tk);
        response.getWriter().flush();
    }
}
