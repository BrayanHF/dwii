package unicordoba.dwii.repository;

import org.springframework.data.repository.CrudRepository;
import unicordoba.dwii.repository.model.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {}