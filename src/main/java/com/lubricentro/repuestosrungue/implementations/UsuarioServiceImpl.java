package com.lubricentro.repuestosrungue.implementations;

import com.lubricentro.repuestosrungue.classes.Usuario;
import com.lubricentro.repuestosrungue.repositories.UsuarioRepository;
import com.lubricentro.repuestosrungue.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario usuarioActual(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public void registrarUsuario(String username, String password) {
        Usuario usuario = new Usuario(username,password);
        usuarioRepository.save(usuario);
    }
}
