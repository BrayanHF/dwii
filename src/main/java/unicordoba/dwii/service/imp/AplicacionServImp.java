package unicordoba.dwii.service.imp;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unicordoba.dwii.repository.AplicacionRepo;
import unicordoba.dwii.repository.ConsultasSpecs;
import unicordoba.dwii.repository.model.Aplicacion;
import unicordoba.dwii.service.AplicacionServ;

@Service
public class AplicacionServImp implements AplicacionServ {

    @Autowired
    private AplicacionRepo aplicacion;

    @Autowired
    private ConsultasSpecs consultas;

    @Override
    public List<Aplicacion> aplicaciones() {
        return (List<Aplicacion>) aplicacion.findAll();
    }

    @Override
    public Boolean agregar_actualizar(Aplicacion aplic) {
        try {
            aplicacion.save(aplic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean eliminar(int id) {
        try {
            aplicacion.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, Object> aplicacionEnSO(String so) {
        List<Aplicacion> apl = aplicacion.findAll(consultas.aplicacionesEnSO(so));
        if (apl == null || apl.isEmpty()) {
            return Map.of("Aplicacion", "No hay aplicaciones en el sistema operativo " + so);
        } else {
            return Map.of("Aplicacion", apl);
        }
    }

    @Override
    public Map<String, Object> aplicacionPorNombre(String nombre) {
        List<Aplicacion> apl = aplicacion.findAll(consultas.aplicacionPorNombre(nombre));
        if (apl == null || apl.isEmpty()) {
            return Map.of("Aplicacion", "No hay aplicaciones con nombre " + nombre);
        } else {
            return Map.of("Aplicacion", apl);
        }
    }

}