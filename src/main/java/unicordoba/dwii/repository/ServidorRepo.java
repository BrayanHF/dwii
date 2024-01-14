package unicordoba.dwii.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import unicordoba.dwii.repository.model.Servidor;

public interface ServidorRepo extends CrudRepository<Servidor, Integer>, JpaSpecificationExecutor<Servidor>{}