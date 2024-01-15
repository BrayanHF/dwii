package unicordoba.dwii.service;

import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;

import unicordoba.dwii.repository.model.Aplicacion;

public interface AplicacionServ {

    List<Aplicacion> aplicaciones();

    Boolean agregar_actualizar(@NonNull Aplicacion aplic);

    Boolean eliminar(int id);

    Map<String, Object> aplicacionEnSO(String so);

    Map<String, Object> aplicacionPorNombre(String nombre);

}
