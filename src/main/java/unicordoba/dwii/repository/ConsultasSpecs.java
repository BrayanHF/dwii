package unicordoba.dwii.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import unicordoba.dwii.repository.model.Aplicacion;
import unicordoba.dwii.repository.model.Servidor;

@Repository
public class ConsultasSpecs {

    public Specification<Servidor> masUnaAplicacion() {
        return new Specification<Servidor>() {
            @Override
            public Predicate toPredicate(@NonNull Root<Servidor> r, @NonNull CriteriaQuery<?> q,
                    @NonNull CriteriaBuilder b) {
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
            public Predicate toPredicate(@NonNull Root<Aplicacion> r, @NonNull CriteriaQuery<?> q,
                    @NonNull CriteriaBuilder b) {
                Join<Aplicacion, Servidor> join = r.join("servidor", JoinType.INNER);
                return b.equal(join.get("sistema"), so);
            }
        };
    }

    public Specification<Aplicacion> aplicacionPorNombre(String nombre) {
        return new Specification<Aplicacion>() {
            @Override
            public Predicate toPredicate(@NonNull Root<Aplicacion> r, @NonNull CriteriaQuery<?> q,
                    @NonNull CriteriaBuilder b) {
                return b.equal(r.get("nombre"), nombre);
            }
        };
    }

}