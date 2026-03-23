
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
    
    
    public void beginTransaction() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }
    
    public void commitTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }
    
    public void rollbackTransaction() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
    
    public void close() {
        if (em.isOpen()) {
            em.close();
        }
    }
    
    
    public void agregarDocente(Docente docente) {
        em.persist(docente);
    }

    public void agregarAsignatura(Asignatura asignatura) {
        em.persist(asignatura);
    }

    public void agregarInstituto(Instituto instituto) {
        em.persist(instituto);
    }
    
    public void borrarInstituto(Integer id) throws Exception{
        System.out.println("entró");
        Instituto instituto = em.find(Instituto.class, id);
        
        if (instituto == null){
            throw new Exception ("No existe el instituto: " + id + " en la base de datos");
        }
        em.remove(instituto);
    }
    
    public void borrarDocente(Integer id) throws Exception{
        
        Docente docente = em.find(Docente.class, id);
        
        if (docente == null){
            throw new Exception ("No existe el docente: " + id + " en la base de datos");
        }
        em.remove(docente);
    }
    
    public void borrarAsignatura(Integer id) throws Exception{
        
        Asignatura asignatura = em.find(Asignatura.class, id);
        
        if (asignatura == null){
            throw new Exception ("No existe la asignatura: " + id + " en la base de datos");
        }
        em.remove(asignatura);
    }
    
    public Asignatura buscarAsignatura(Integer id) throws Exception{
           Asignatura asignatura = em.find(Asignatura.class, id);
           return asignatura;
    }
    
    public Instituto buscarInstituto(Integer id) throws Exception{
           Instituto instituto = em.find(Instituto.class, id);
           return instituto;
    }
       
    public Docente buscarDocente(Integer id) throws Exception{
           Docente docente = em.find(Docente.class, id);
           return docente;
    }
       
    public void agregarAsignaturaADocente(int docenteId, int asignaturaId) throws Exception {
        Docente docente = em.find(Docente.class, docenteId);
        Asignatura asignatura = em.find(Asignatura.class, asignaturaId);

        if (docente == null || asignatura == null) {
            throw new Exception("Docente o Asignatura no encontrados");
        }
        
        docente.agregarAsignatura(asignatura);
        // Añadir (JPA manejará la tabla intermedia)
        //docente.agregarAsignatura(asignatura);

        // Si es bidireccional:
        // asignatura.getDocentes().add(docente);
    }
    
    
   
    
    public List<Instituto> obtenerTodosInstitutos() throws Exception {
        return em.createQuery("SELECT i FROM Instituto i", Instituto.class)
             .getResultList();
    }
    
}
