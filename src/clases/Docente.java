
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
    private String documento;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String direccion_notificaciones;
    private String carga_horaria; // Antes era CargoDocente
    @ManyToMany
    @JoinTable(
        //creacion tabla intermedia
        name = "docentes_asignaturas",
        joinColumns = @JoinColumn(name = "docente_id"), //nombre campo en tabla intermedia
        inverseJoinColumns = @JoinColumn(name = "asignatura_id") // nombre campo en tabla intermedia
    )
    private List<Asignatura> asignaturas = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "instituto_id")
    private Instituto instituto;
    
    public Docente(){
        this.legajo = "";
        this.documento = "";
        this.nombre = "";
        this.apellido = "";
        this.fecha_nacimiento = "";
        this.direccion_notificaciones = "";
        this.carga_horaria = "";
    }
    
    
    public Docente(String legajo,String documento, String nombre, String apellido, String fecha_nacimiento, String direccion_notificaciones, String carga_horaria) {
        this.legajo = legajo;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion_notificaciones = direccion_notificaciones;
        this.carga_horaria = carga_horaria;
    }
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
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion_notificaciones() {
        return direccion_notificaciones;
    }

    public void setDireccion_notificaciones(String direccion_notificaciones) {
        this.direccion_notificaciones = direccion_notificaciones;
    }

    public String getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(String carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public List<Asignatura> getAllAsignaturas() {
        return asignaturas;
    }

    public void addAsignaturas(Asignatura asignaturas) {
        this.asignaturas.add(asignaturas);
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    
    public String getCargaHoraria() {
        return carga_horaria;
    }

    public void setCargaHoraria(String carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
    /*
    public void quitarAsignatura(Asignatura asignatura){
         asignaturas.removeIf(asig -> asig.getId().equals(asignatura.getId()));
    }
    */
 

    @Override
    public String toString() {
        return "Docente{" +
                "id=" + id +
                ", legajo='" + legajo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cargaHoraria='" + carga_horaria + '\'' +
                ", asignaturas=" + asignaturas +
                '}';
    }
}
