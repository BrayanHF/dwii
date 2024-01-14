package unicordoba.dwii.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unicordoba.dwii.repository.ConsultasSpecs;
import unicordoba.dwii.repository.ServidorRepo;
import unicordoba.dwii.repository.model.Servidor;
import unicordoba.dwii.service.ServidorServ;

@Service
public class ServidorServImp implements ServidorServ {

    @Autowired
    private ServidorRepo servidor;

    @Autowired
    private ConsultasSpecs consultas;

    @Override
    public List<Servidor> servidores() {
        return (List<Servidor>) servidor.findAll();
    }

    @Override
    public Boolean agregar_actualizar(Servidor server) {
        try {
            servidor.save(server);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean eliminar(int id) {
        try {
            servidor.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, Object> aplicacoines_en_servidor(int id) {
        Servidor server = servidor.findById(id).orElse(null);
        if (server == null) {
            return Map.of("Servidor", "No encontrdo");
        }
        return Map.of(server.getNombre(), server.getAplicaciones());
    }

    @Override
    public Map<String, Object> masUnaAplicacion() {
        List<Servidor> ser = servidor.findAll(consultas.masUnaAplicacion());
        if (ser == null || ser.isEmpty()) {
            return Map.of("Servidor", "No hay servidor con mas de una aplicacion");
        } else {
            return Map.of("Servidor", ser);
        }
    }

}