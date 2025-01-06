package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DtoDatosAutenticacionUsuario;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.security.TokenService;
import med.voll.api.infra.security.dtoDatoJWTToken;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DtoDatosAutenticacionUsuario dtoDatosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoDatosAutenticacionUsuario.login() , dtoDatosAutenticacionUsuario.clave());
        var usuarioAutenticado =  authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new dtoDatoJWTToken(JWTToken));
    }
}
