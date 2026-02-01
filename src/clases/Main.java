
package clases;


public class Main {

    public static void main(String[] args) {
    
        //Instanciamos la clase DAO
        DAO dao = new DAO();
        //Creamos un Instituto
        dao.
        Instituto instituto = new Instituto("Instituto Terciario");
        
        //Grabamos el instituto en la base de datos
        try{
            dao.insertInstituto(instituto);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Creamos una asignatura
        
        Asignatura pl = new Asignatura("Programación Lógica I", "Diagramas de flujo y pseudocodigo");
        //asignamos el instituto a la asignatura
        pl.setInstituto(instituto);
        
        //Grabamos la asignatura en la base de datos
        try{
            dao.insertAsignatura(pl);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        //creamos un docente
        Docente docente = new Docente("ABC-88", "Alfredo", "Nuñez", "80");
        docente.setInstituto(instituto);
        docente.agregarAsignatura(pl);
        try{
            dao.insertDocente(docente);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
