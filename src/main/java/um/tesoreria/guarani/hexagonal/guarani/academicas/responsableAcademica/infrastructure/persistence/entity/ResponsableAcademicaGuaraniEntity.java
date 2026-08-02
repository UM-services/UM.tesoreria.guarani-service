package um.tesoreria.guarani.hexagonal.guarani.academicas.responsableAcademica.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import um.tesoreria.guarani.hexagonal.guarani.propuestas.propuestaResponsableAcademica.infrastructure.persistence.entity.PropuestaResponsableAcademicaGuaraniEntity;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sga_responsables_academicas", schema = "negocio")
public class ResponsableAcademicaGuaraniEntity {

    @Id
    @Column(name = "responsable_academica")
    private Integer responsableAcademica;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(nullable = false, length = 10)
    private String codigo;

    @Column(name = "responsable_academica_tipo", nullable = false)
    private Integer responsableAcademicaTipo;

    @Column(nullable = false)
    private Integer institucion;

    @OneToMany(mappedBy = "responsableAcademicaRel")
    private List<PropuestaResponsableAcademicaGuaraniEntity> propuestas;

    @Column(name = "institucion_araucano")
    private Integer institucionAraucano;

    @Column(length = 100)
    private String calle;

    @Column(length = 20)
    private String numero;

    private Integer localidad;

    @Column(name = "codigo_postal", length = 15)
    private String codigoPostal;

    @Column(length = 50)
    private String telefono;

    @Column(length = 50)
    private String fax;

    @Column(length = 100)
    private String email;

    private Integer entidad;

}
