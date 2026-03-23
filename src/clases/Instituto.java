
package clases;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "institutos")
public class Instituto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String denominacion;
    //mappedBy apunta al atributo de tipo Instituto en las clases relacionadas (Docente.instituto y Asignatura.instituto).
    @OneToMany(mappedBy = "instituto", cascade = CascadeType.REMOVE)
    private List<Docente> docentes = new ArrayList<>();
    
    @OneToMany(mappedBy = "instituto", cascade = CascadeType.REMOVE)
    private List<Asignatura> asignaturas = new ArrayList<>();

    public Instituto() {
        this.denominacion = "";
    }
    
    public Instituto(String denominacion) {
        this.denominacion = denominacion;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public void setDenominacion(String denominacion){
        this.denominacion = denominacion;
    }
    
    public String getDenominacion(){
        return this.denominacion;
    }

    public void agregarDocente(Docente docente) {
        this.docentes.add(docente);
        docente.setInstituto(this); //establecemos la bidireccionalidad
    }

    public void eliminarDocente(Docente docente){
        docentes.remove(docente);
        docente.setInstituto(null);
    }
    
    public void agregarAsignatura(Asignatura asignatura) {
        this.asignaturas.add(asignatura);
        asignatura.setInstituto(this); //establecemos la bidireccionalidad
    }

    
    public void eliminarAsignatura(Asignatura asignatura){
        asignaturas.remove(asignatura);
        asignatura.setInstituto(null);
    }
    
    
    public List<Docente> obtenerTodosDocentes() {
        return docentes;
    }

    public List<Asignatura> obtenerTodasAsignaturas() {
        return asignaturas;
    }

    @Override
    public String toString() {
        return denominacion;
    }
}
