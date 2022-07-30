package co.toc.com.indicador.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroIndicador implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 4)
    private Integer anio;
    @Column(nullable = false, length = 2)
    private Integer mes;

    @Column(nullable = false)
    private double numerador;

    @Column(nullable = false)
    private double denominador;

    @Column(nullable = false)
    private double resultadoIndicador;

    @Lob
    @Column(nullable = false)
    private String analisis;

    @Lob
    @Column(nullable = false)
    private String causas;

    @Lob
    @Column(nullable = false)
    private String propuestas;

    @ManyToOne
    private Usuario usuarioCreacion;

    @ManyToOne
    private Indicador indicador;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 2)
    private String estado;

}
