package foro.hub.api.infra.security;

import foro.hub.api.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token de header
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null){
            var token = authHeader.replace("Bearer ", "");
            var nombreUsuari = tokenService.getSubject(token); //extract username
            if (nombreUsuari != null){
                //token valido
                var usuario = usuarioRepository.findByLogin(nombreUsuari);
                var authenticatrion = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());//forsamos el inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authenticatrion);
            }
        }
        filterChain.doFilter(request, response);
    }
}
