
package clases;


public class Main {

    public static void main(String[] args) {
        DAO dao = new DAO();
        
        Docente docente = new Docente("AE8","Pepe","Gonzales");
        try{
            dao.insertDocente(docente);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
           

        //Asignatura asignatura = new Asignatura("Programación 1", "Lógica y pseudocodigos");
        
        //Instituto instituto = new Instituto("Instituto de educación secundaria");
        
        //docente.agregarAsignatura(asignatura);
        //instituto.agregarDocente(docente);
        
        
        //ver el nombre del docente en el instituto
        //System.out.println("Nombre del docente" + instituto.getAllDocentes().get(0).getNombre());
    }
    
}
