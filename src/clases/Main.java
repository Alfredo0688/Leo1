
package clases;


public class Main {

    public static void main(String[] args) {
    
        //Instanciamos la clase DAO
        DAO dao = new DAO();
        
        
        try{
            
            dao.beginTransaction();
            
            Instituto i = dao.buscarInstituto(1);
            
            Docente d = dao.buscarDocente(4);
            
            i.removeDocente(d);
            
            //probar esto : docente solo está en un instituto, por lo que borrar todas sus asignaturas ligadas a ese instituto deberia ser valido 
            d.getAllAsignaturas().clear();
            
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
