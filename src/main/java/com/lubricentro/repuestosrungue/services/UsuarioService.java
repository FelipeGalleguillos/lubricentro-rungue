package com.lubricentro.repuestosrungue.services;

import com.lubricentro.repuestosrungue.classes.Usuario;

public interface UsuarioService {

    Usuario usuarioActual(String username);

    void guardarUsuario(Usuario usuario);

    void eliminarUsuario(Usuario usuario);

    void registrarUsuario(String username, String password);
}
