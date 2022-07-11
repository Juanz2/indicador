package co.toc.com.indicador.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Proceso implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProceso;

    @Column(nullable = false, length = 100)
    private String nombre;

    private String foto;

    @Column(nullable = false, length = 1)
    private String estado;


    /**
     * Obtiene la foto del proceso
     * @return nombre y extensión del archivo
     */
    public String getImagenPrincipal(){
        if(foto != null){
            if(!foto.isEmpty()){
                return foto;
            }
        }
        return "3616866.png";
    }
    @ManyToMany(mappedBy = "procesos")
    @ToString.Exclude
    private List<Usuario> usuarios;
}
