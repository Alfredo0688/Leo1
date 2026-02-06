
package clases;


public class Main {

    public static void main(String[] args) {
        
        Docente docente = new Docente();
        docente.setApellido("Lopez");
        Asignatura asignatura = new Asignatura("Programación 1", "Lógica y pseudocodigos");
        
        Instituto instituto = new Instituto("Instituto de educación secundaria");
        
        docente.addAsignaturas(asignatura);
        instituto.addDocente(docente);
        
        
        //ver el nombre del docente en el instituto
        System.out.println("Nombre del docente" + instituto.getAllDocentes().get(0).getNombre());
    }
    
}
