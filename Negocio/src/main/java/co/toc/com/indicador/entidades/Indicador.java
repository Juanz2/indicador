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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Indicador implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIndicador;

    /*
    @Column(nullable = false)
    private Proceso proceso;
    */
    @Column(length = 20, nullable = false)
    private String tendencia;
    @Column(nullable = false)
    private Integer limiteInferior;
    @Column(nullable = false)
    private Integer limiteSuperior;
    @Column(length = 1, nullable = false)
    private String estado;

}
