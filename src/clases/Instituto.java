
package clases;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "docentes")
public class Instituto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String denominacion;
    private final List<Docente> docentes;
    private final List<Asignatura> asignaturas;

    public Instituto() {
        this.denominacion = "";
        this.docentes = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
    }
    
    
    
    public Instituto(String denominacion) {
        this.denominacion = denominacion;
        this.docentes = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
    }
    
    public void setDenominacion(String denominacion){
        this.denominacion = denominacion;
    }
    
    public String getDenominacion(){
        return this.denominacion;
    }

    public void agregarDocente(Docente docente) {
        this.docentes.add(docente);
    }

    public void agregarAsignatura(Asignatura asignatura) {
        this.asignaturas.add(asignatura);
    }

    public List<Docente> getAllDocentes() {
        return docentes;
    }

    public List<Asignatura> getAllAsignaturas() {
        return asignaturas;
    }

    @Override
    public String toString() {
        return "Instituto{" +
                "codigo=" + codigo +
                ", denominacion='" + denominacion + '\'' +
                ", docentes=" + docentes +
                ", asignaturas=" + asignaturas +
                '}';
    }
}
