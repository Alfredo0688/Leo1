
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
    private String fechaNacimiento;
    private String direccionNotificaciones;
    private String cargaHoraria; // Antes era CargoDocente
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
        this.fechaNacimiento = "";
        this.direccionNotificaciones = "";
        this.cargaHoraria = "";
    }
    
    
    public Docente(String legajo,String documento, String nombre, String apellido, String fechaNacimiento, String direccionNotificaciones, String cargaHoraria) {
        this.legajo = legajo;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionNotificaciones = direccionNotificaciones;
        this.cargaHoraria = cargaHoraria;
    }
    public Integer getId() {
        return id;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionNotificaciones() {
        return direccionNotificaciones;
    }

    public void setDireccionNotificaciones(String direccionNotificaciones) {
        this.direccionNotificaciones = direccionNotificaciones;
    }

    public List<Asignatura> obtenerTodasAsignaturas() {
        return asignaturas;
    }

    public void agregarAsignatura(Asignatura asignaturas) {
        this.asignaturas.add(asignaturas);
    }

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    
    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
    
    public void quitarAsignatura(Asignatura asignatura){
         
        if (asignatura == null || asignatura.getId() == null) return;
            for (int i = 0; i < asignaturas.size(); i++) {
                if (asignaturas.get(i).getId().equals(asignatura.getId())) {
                    asignaturas.remove(i);
                    break;}
            }
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
