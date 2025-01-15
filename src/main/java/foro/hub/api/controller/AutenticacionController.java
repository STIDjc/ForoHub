package foro.hub.api.controller;

import jakarta.validation.Valid;
import foro.hub.api.infra.security.DatosJWTtoken;
import foro.hub.api.infra.security.TokenService;
import foro.hub.api.domain.usuario.DatosAutenticacionUsuario;
import foro.hub.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/login")
    public class AutenticacionController {

        @Autowired
        private AuthenticationManager autenticationManager;
        @Autowired
        private TokenService tokenService;

        @PostMapping
        public ResponseEntity autenticacionUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario ) {
            Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                    datosAutenticacionUsuario.clave());
            autenticationManager.authenticate(authToken);
            var usuarioAutenticado = autenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
        }
    }
