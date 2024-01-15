package unicordoba.dwii.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import unicordoba.dwii.repository.model.Aplicacion;
import unicordoba.dwii.repository.model.Servidor;
import unicordoba.dwii.service.AplicacionServ;
import unicordoba.dwii.service.ServidorServ;

@Controller
@RequestMapping("/SoftwareSA")
@ResponseBody
@Api(tags = { "Controlador SoftwareSA" }, description = "Controlador para dar solución a la actividad de DWII")
public class Controlador {

    @Autowired
    private ServidorServ servidorServ;

    @Autowired
    private AplicacionServ aplicacionServ;

    @ApiOperation("Obtener todos los servidores")
    @GetMapping("/servidores")
    public List<Servidor> servidores() {
        return servidorServ.servidores();
    }

    @ApiOperation("Agregar un servidor")
    @PostMapping("/servidor-agregar")
    public Map<String, Boolean> servidor_agregar(@RequestBody @NonNull Servidor server) {
        return Map.of("Servidor agregado", servidorServ.agregar_actualizar(server));
    }

    @ApiOperation("Actualizar un servirdor")
    @PutMapping("/servidor-actualizar")
    public Map<String, Boolean> servidor_actualizar(@RequestBody @NonNull Servidor server) {
        return Map.of("Servidor actualizado", servidorServ.agregar_actualizar(server));
    }

    @ApiOperation("Eliminar un servidor")
    @DeleteMapping("/servidor-eliminar/{id}")
    public Map<String, Boolean> servidor_eliminar(@PathVariable int id) {
        return Map.of("Servidor eliminado", servidorServ.eliminar(id));
    }

    @ApiOperation("Obtener todas las aplicaciones")
    @GetMapping("/aplicaciones")
    public List<Aplicacion> aplicaciones() {
        return aplicacionServ.aplicaciones();
    }

    @ApiOperation("Agregar una aplicación")
    @PostMapping("/aplicacion-agregar")
    public Map<String, Boolean> aplicacion_agregar(@RequestBody @NonNull Aplicacion aplic) {
        return Map.of("Aplicacion agregada", aplicacionServ.agregar_actualizar(aplic));
    }

    @ApiOperation("Actualizar una aplicación")
    @PutMapping("/aplicacion-actualizar")
    public Map<String, Boolean> aplicacion_actualizar(@RequestBody @NonNull Aplicacion aplic) {
        return Map.of("Aplicacion actualizada", aplicacionServ.agregar_actualizar(aplic));
    }

    @ApiOperation("Eliminar una aplicación")
    @DeleteMapping("/aplicacion-eliminar/{id}")
    public Map<String, Boolean> aplicacion_eliminar(@PathVariable int id) {
        return Map.of("Aplicacion eliminada", aplicacionServ.eliminar(id));
    }

    @ApiOperation("Obtener las aplicacines que estan en un servidor")
    @GetMapping("/aplicaciones-en-servidor/{id}")
    public Map<String, Object> aplicacoines_en_servidor(@PathVariable int id) {
        return servidorServ.aplicacoines_en_servidor(id);
    }

    @ApiOperation("Obtener los servidores con mas de una aplicación")
    @GetMapping("/servidores-con-mas-de-una-aplicacion")
    public Map<String, Object> masUnaAplicacion() {
        return servidorServ.masUnaAplicacion();
    }

    @ApiOperation("Obtener las aplicaciones que estan en un sistema operativo especifico")
    @GetMapping("/aplicacion-so/{so}")
    public Map<String, Object> aplicacionEnSO(@PathVariable String so) {
        return aplicacionServ.aplicacionEnSO(so);
    }

    @ApiOperation("Obtener una aplicación por su nombre")
    @GetMapping("/aplicacion-nombre/{nombre}")
    public Map<String, Object> aplicacionPorNombre(@PathVariable String nombre) {
        return aplicacionServ.aplicacionPorNombre(nombre);
    }

}