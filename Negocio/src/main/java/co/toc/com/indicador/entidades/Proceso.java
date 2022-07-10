package co.toc.com.indicador.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Proceso implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProceso;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 1)
    private String estado;
}
