package unicordoba.dwii.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import unicordoba.dwii.model.Aplicacion;

public interface AplicacionRepo extends CrudRepository<Aplicacion, Integer>, JpaSpecificationExecutor<Aplicacion>{}