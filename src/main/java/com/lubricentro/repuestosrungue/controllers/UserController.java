package com.lubricentro.repuestosrungue.controllers;

import com.lubricentro.repuestosrungue.classes.Usuario;
import com.lubricentro.repuestosrungue.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario/registro")
    ResponseEntity<Object> nuevoUsuario(@RequestParam String username, @RequestParam String password){
        usuarioService.registrarUsuario(username,password);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/usuario/actual")
    public Usuario obtenerUsuarioActual(Authentication authentication) {
        return usuarioService.usuarioActual(authentication.getName());
    }
}
