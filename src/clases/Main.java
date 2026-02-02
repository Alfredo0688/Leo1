
package clases;


public class Main {

    public static void main(String[] args) {
    
        //Instanciamos la clase DAO
        DAO dao = new DAO();
        try{
            
            dao.beginTransaction();
            
            //Asignatura asig1 = new Asignatura("Matematicas 1", "Preposiciones");
            //dao.insertAsignatura(asig1);
            
            dao.deleteInstituto(1);
            
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
