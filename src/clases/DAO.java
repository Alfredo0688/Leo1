
package clases;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Unidad_Persistencia");
            
    private EntityManager em;
    
    public DAO(){
        this.em = emf.createEntityManager();
    }
    
    public void insertDocente(Docente docente)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(docente);
            em.getTransaction().commit();
        
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("No se pudo insertar al docente");
        }
    }
    
    public void insertAsignatura(Asignatura asignatura)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(asignatura);
            em.getTransaction().commit();
        
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("No se pudo insertar la asignatura");
        }
    }   
    
    
      public void insertInstituto(Instituto instituto)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(instituto);
            em.getTransaction().commit();
        
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("No se pudo insertar la asignatura");
        }
    }   
    
    
    
    
    /*
    public void insertInstituto(Instituto instituto)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(instituto);
            em.getTransaction().commit();
        
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("No se pudo insertar al docente");
        }
    }
    
    public void insertDocente(Docente docente)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(docente);
            em.getTransaction().commit();
        
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("No se pudo insertar al docente");
        }
    }

    public void insertAsignatura(Asignatura asignatura)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(asignatura);
            em.getTransaction().commit();
        
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("No se pudo insertar al docente");
        }
    }
    
    public void insertCargoDocente(Cargo_Docente cargo_docente)throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(cargo_docente);
            em.getTransaction().commit();
        
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("No se pudo insertar al docente");
        }
    }
    
    public Instituto searchInstituto(Integer codigo)throws Exception {
        try{
            Instituto instituto = em.find(Instituto.class, codigo);
            return instituto;
        }catch(Exception e){
            throw new Exception("No se encontró al docente: " + e.getMessage());
        }
    }
    
    public Docente searchDocente(Integer codigo)throws Exception {
        try{
            Docente docente = em.find(Docente.class, codigo);
            return docente;
        }catch(Exception e){
            throw new Exception("No se encontró al docente: " + e.getMessage());
        }
    }
    
    public Cargo_Docente searchCargoDocente(Integer codigo)throws Exception {
        try{
            Cargo_Docente cargo_docente = em.find(Cargo_Docente.class, codigo);
            return cargo_docente;
        }catch(Exception e){
            throw new Exception("No se encontró al docente: " + e.getMessage());
        }
    }
    
    public Asignatura searchAsignatura(Integer codigo)throws Exception {
        try{
            Asignatura asignatura = em.find(Asignatura.class, codigo);
            return asignatura;
        }catch(Exception e){
            throw new Exception("No se encontró al docente: " + e.getMessage());
        }
    }
    
    //traer una lista de docentes
    public List<Instituto>getAllInstitutos()throws Exception{
        
        try{                                        //consulta                  el tipo que va a ser lo que buscamos
            List<Instituto> instituto = em.createQuery("SELECT d FROM Instituto d", Instituto.class)
                    .getResultList();//obtiene la lista
            return instituto;
        }catch(Exception e){
            throw new Exception("No se pudieron obtener los docentes " + e.getMessage());
        }
    
    }*/
}
