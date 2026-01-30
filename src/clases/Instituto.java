
package clases;

import java.util.ArrayList;
import java.util.List;

public class Instituto {
    private Integer codigo;
    private String denominacion;
    private final List<Docente> docentes;
    private final List<Asignatura> asignaturas;

    
    public Instituto(Integer codigo, String denominacion) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.docentes = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
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
