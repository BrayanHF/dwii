package unicordoba.dwii.service;

import org.springframework.lang.NonNull;
import unicordoba.dwii.repository.model.Usuario;

public interface UsuarioServ {

    boolean agregar(@NonNull Usuario user);
    
} 