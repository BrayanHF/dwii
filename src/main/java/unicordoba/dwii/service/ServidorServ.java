package unicordoba.dwii.service;

import java.util.List;
import java.util.Map;
import org.springframework.lang.NonNull;
import unicordoba.dwii.repository.model.Servidor;

public interface ServidorServ {

    List<Servidor> servidores();

    Boolean agregar_actualizar(@NonNull Servidor server);

    Boolean eliminar(int id);

    Map<String, Object> aplicacoines_en_servidor(int id);

    Map<String, Object> masUnaAplicacion();

}
