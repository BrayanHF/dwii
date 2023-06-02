package unicordoba.dwii.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import unicordoba.dwii.model.Aplicacion;
import unicordoba.dwii.model.Servidor;

@Service
public class ConsultasSpecs {

    public Specification<Servidor> masUnaAplicacion() {
        return new Specification<Servidor>() {
            @Override
            public Predicate toPredicate(Root<Servidor> r, CriteriaQuery<?> q, CriteriaBuilder b) {
                Join<Servidor, Aplicacion> join = r.join("aplicaciones", JoinType.INNER);
                q.groupBy(r);
                q.having(b.greaterThan(b.count(join), 1L));
                return q.getRestriction();
            }
        };
    }

    public Specification<Aplicacion> aplicacionesEnSO(String so) {
        return new Specification<Aplicacion>() {
            @Override
            public Predicate toPredicate(Root<Aplicacion> r, CriteriaQuery<?> q, CriteriaBuilder b) {
                Join<Aplicacion, Servidor> join = r.join("servidor", JoinType.INNER);
                return b.equal(join.get("sistema"), so);
            }
        };
    }

    public Specification<Aplicacion> aplicacionPorNombre(String nombre) {
        return new Specification<Aplicacion>() {
            @Override
            public Predicate toPredicate(Root<Aplicacion> r, CriteriaQuery<?> q, CriteriaBuilder b) {
                return b.equal(r.get("nombre"), nombre);
            }
        };
    }

}