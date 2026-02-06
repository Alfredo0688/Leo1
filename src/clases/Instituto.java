
package clases;

import java.util.ArrayList;
import java.util.List;

public class Instituto {
    private Integer id;
    private String denominacion;
    private final List<Docente> docentes = new ArrayList<>();;
    private final List<Asignatura> asignaturas = new ArrayList<>();;

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

    public void addDocente(Docente docente) {
        this.docentes.add(docente);
        docente.setInstituto(this); //establecemos la bidireccionalidad
    }

    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.add(asignatura);
        asignatura.setInstituto(this); //establecemos la bidireccionalidad
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
                "codigo=" + id +
                ", denominacion='" + denominacion + '\'' +
                ", Cantidad de asignaturas=" + asignaturas.size() +
                ", Cantidad de docentes=" + docentes.size() + 
                '}';
    }
}
