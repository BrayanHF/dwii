package unicordoba.dwii.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import unicordoba.dwii.model.Aplicacion;
import unicordoba.dwii.model.Servidor;
import unicordoba.dwii.repository.AplicacionRepo;
import unicordoba.dwii.repository.ConsultasSpecs;
import unicordoba.dwii.repository.ServidorRepo;

@Controller
@RequestMapping("/SoftwareSA")
@ResponseBody
public class Controlador {

    @Autowired
    public ServidorRepo servidor;

    @Autowired
    public AplicacionRepo aplicacion;

    @Autowired
    public ConsultasSpecs consultas;

    @GetMapping("/servidores")
    public List<Servidor> servidores() {
        return (List<Servidor>) servidor.findAll();
    }

    @PostMapping("/servidor-agregar")
    public Map<String, Boolean> servidor_agregar(@RequestBody Servidor server) {
        servidor.save(server);
        return Map.of("Servidor agregado", true);
    }

    @PutMapping("/servidor-actualizar")
    public Map<String, Boolean> servidor_actualizar(@RequestBody Servidor server) {
        servidor.save(server);
        return Map.of("Servidor actualizado", true);
    }

    @DeleteMapping("/servidor-eliminar/{id}")
    public Map<String, Boolean> servidor_eliminar(@PathVariable int id) {
        servidor.deleteById(id);
        return Map.of("Servidor eliminado", true);
    }

    @GetMapping("/aplicaciones")
    public List<Aplicacion> aplicaciones() {
        return (List<Aplicacion>) aplicacion.findAll();
    }

    @PostMapping("/aplicacion-agregar")
    public Map<String, Boolean> aplicacion_agregar(@RequestBody Aplicacion aplic) {
        aplicacion.save(aplic);
        return Map.of("Aplicacion agregada", true);
    }

    @PutMapping("/aplicacion-actualizar")
    public Map<String, Boolean> aplicacion_actualizar(@RequestBody Aplicacion aplic) {
        aplicacion.save(aplic);
        return Map.of("Aplicacion actualizada", true);
    }

    @DeleteMapping("/aplicacion-eliminar/{id}")
    public Map<String, Boolean> aplicacion_eliminar(@PathVariable int id) {
        aplicacion.deleteById(id);
        return Map.of("Aplicacion eliminada", true);
    }

    @GetMapping("/aplicaciones-en-servidor/{id}")
    public Map<String, Object> aplicacoines_en_servidor(@PathVariable int id) {
        Servidor server = servidor.findById(id).orElse(null);
        if (server == null) {
            return Map.of("Servidor", "No encontrdo");
        }
        return Map.of(server.getNombre(), server.getAplicaciones());
    }

    @GetMapping("/servidores-con-mas-de-una-aplicacion")
    public Map<String, Object> masUnaAplicacion() {
        List<Servidor> ser = servidor.findAll(consultas.masUnaAplicacion());
        if (ser == null || ser.isEmpty()) {
            return Map.of("Servidor", "No hay servidor con mas de una aplicacion");
        } else {
            return Map.of("Servidor", ser);
        }
    }

    @GetMapping("/aplicacion-so/{so}")
    public Map<String, Object> aplicacionEnSO(@PathVariable String so) {
        List<Aplicacion> apl = aplicacion.findAll(consultas.aplicacionesEnSO(so));
        if (apl == null || apl.isEmpty()) {
            return Map.of("Aplicacion", "No hay aplicaciones en el sistema operativo " + so);
        } else {
            return Map.of("Aplicacion", apl);
        }
    }

    @GetMapping("/aplicacion-nombre/{nombre}")
    public Map<String, Object> aplicacionPorNombre(@PathVariable String nombre) {
        List<Aplicacion> apl = aplicacion.findAll(consultas.aplicacionPorNombre(nombre));
        if (apl == null || apl.isEmpty()) {
            return Map.of("Aplicacion", "No hay aplicaciones con nombre " + nombre);
        } else {
            return Map.of("Aplicacion", apl);
        }
    }

}