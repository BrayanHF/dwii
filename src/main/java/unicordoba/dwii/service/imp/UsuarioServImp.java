package unicordoba.dwii.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import unicordoba.dwii.repository.UsuarioRepo;
import unicordoba.dwii.repository.model.Usuario;
import unicordoba.dwii.service.UsuarioServ;

@Service
public class UsuarioServImp implements UsuarioServ {

    @Autowired
    private UsuarioRepo usuario;

    @Override
    public boolean agregar(@NonNull Usuario user) {
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            usuario.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}