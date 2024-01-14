package unicordoba.dwii.repository.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ip;
    private String nombre;
    private String memoria;
    private String disco;
    private String procesador;
    private String ubicacion;
    private String sistema;

    @OneToMany(mappedBy = "servidor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Aplicacion> aplicaciones;

}