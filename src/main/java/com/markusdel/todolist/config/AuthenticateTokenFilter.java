package com.markusdel.todolist.config;

import com.markusdel.todolist.exception.UnauthorizedException;
import com.markusdel.todolist.model.User;
import com.markusdel.todolist.repository.UserRepository;
import com.markusdel.todolist.service.TokenService;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticateTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public AuthenticateTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        Boolean tokenValido = tokenService.isTokenValido(token);

        Long idUsuarioRequisitado = returnIdUser(request);

        if(tokenValido) autenticarUsuario(token, idUsuarioRequisitado);
        filterChain.doFilter(request, response);
    }

    private Long returnIdUser(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] uriQuebrada = uri.split("/");
        String idUsuarioRequisitado = uriQuebrada[2];

        if(idUsuarioRequisitado.equals("register") || idUsuarioRequisitado.equals("login") || idUsuarioRequisitado.equals("tasks") ) {
            return null;
        }

        return Long.parseLong(idUsuarioRequisitado);
    }

    private void autenticarUsuario(String token, Long idUsuarioRequisitado) throws UsernameNotFoundException, UnauthorizedException {
        Long idUsuario = tokenService.getIdUsuario(token);
        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("not found " + idUsuario));

        Boolean isNull = idUsuarioRequisitado == null;
        Boolean hasPermission = idUsuarioRequisitado.equals(idUsuario);

        if(isNull || !hasPermission) {
            throw new UnauthorizedException();
        }

        UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
