
package clases;

import java.util.ArrayList;
import java.util.List;

public class Docente {
    private Integer id;
    private String legajo;
    private String nombre;
    private String apellido;
    private String cargaHoraria; // Antes era CargoDocente
    private List<Asignatura> asignaturas;

    public Docente(String legajo, String nombre, String apellido) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.asignaturas = new ArrayList<>();
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
