
package clases;


public class Main {

    public static void main(String[] args) {
    
        //Instanciamos la clase DAO
        DAO dao = new DAO();
        
        
        try{
            
            dao.beginTransaction();
            
            Instituto i = new Instituto("Primario");
            
            dao.agregarInstituto(i);
            
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
