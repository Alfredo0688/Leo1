
package clases;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String legajo;
    private String nombre;
    private String apellido;
    private String cargaHoraria; // Antes era CargoDocente
    @ManyToMany
    @JoinTable(
        //creacion tabla intermedia
        name = "docente_asignatura",
        joinColumns = @JoinColumn(name = "docente_id"), //nombre campo en tabla intermedia
        inverseJoinColumns = @JoinColumn(name = "asignatura_id") // nombre campo en tabla intermedia
    )
    private List<Asignatura> asignaturas;
    
    @ManyToOne
    @JoinColumn(name = "instituto_id")
    private Instituto instituto;
    
    public Docente(){
        this.legajo = "";
        this.nombre = "";
        this.apellido = "";
        this.cargaHoraria = "";
        this.asignaturas = new ArrayList<>();
    }
    
    
    public Docente(String legajo, String nombre, String apellido, String cargahoraria) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargaHoraria = cargahoraria;
        this.asignaturas = new ArrayList<>();
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    
    // Métodos para manejar carga horaria
    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    // Métodos para manejar asignaturas
    public void agregarAsignatura(Asignatura asignatura) {
        this.asignaturas.add(asignatura);
    }

    public List<Asignatura> getAllAsignaturas() {
        return this.asignaturas;
    }
    
    public void quitarAsignatura(Asignatura asignatura){
         asignaturas.removeIf(asig -> asig.getCodigo().equals(asignatura.getCodigo()));
    }
    
    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "id=" + id +
                ", legajo='" + legajo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cargaHoraria='" + cargaHoraria + '\'' +
                ", asignaturas=" + asignaturas +
                '}';
    }
}
