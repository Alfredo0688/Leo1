
package clases;


public class Main {

    public static void main(String[] args) {
    
        //Instanciamos la clase DAO
        DAO dao = new DAO();
        try{
            
            dao.beginTransaction();
            //Instituto instituto = new Instituto("Instituto Terciario");
            //dao.insertInstituto(instituto);
            
            //Buscar objeto instituto
            //Instituto i = dao.buscarInstituto(1);
            
            //Asignatura asig = new Asignatura("Programación Lógica I", "Diagramas de flujo y pseudocodigo");
            //asig.setInstituto(i);
            //dao.insertAsignatura(asig);
            
            
            Docente docente = new Docente("ABC-88", "Alfredo", "Nuñez", "80");
            //docente.setInstituto(i);
            //docente.agregarAsignatura(asig);
            dao.insertDocente(docente);
         
            dao.commitTransaction();
        }
        catch(Exception e){
            dao.rollbackTransaction();
            e.printStackTrace(); // Muestra el error
            // Opcional: mostrar mensaje al usuario
            System.out.println("Error: " + e.getMessage());
        }
        finally{
            dao.close();
        }
        
    }
    
}
