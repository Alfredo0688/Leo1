
package clases;


public class Main {

    public static void main(String[] args) {
    
        //Instanciamos la clase DAO
        DAO dao = new DAO();
        try{
            
            dao.beginTransaction();
            
            //Busqueda de instituto
            Instituto instituto = dao.buscarInstituto(1);
            
            //ver los docentes del instituto
            
            Docente docente = instituto.getAllDocentes().get(0);
            
            
            System.out.println("El nombre del unico docente en instituto es : " + docente.getNombre() );
            
         
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
