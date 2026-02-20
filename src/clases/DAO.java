
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
    
    
    public void insertDocente(Docente docente) {
        em.persist(docente);
    }

    public void insertAsignatura(Asignatura asignatura) {
        em.persist(asignatura);
    }

    public void insertInstituto(Instituto instituto) {
        em.persist(instituto);
    }
    
    public void deleteInstituto(Integer id) throws Exception{
        
        Instituto instituto = em.find(Instituto.class, id);
        
        if (instituto == null){
            throw new Exception ("No existe el instituto: " + id + " en la base de datos");
        }
        em.remove(instituto);
    }
    
    public void deleteDocente(Integer id) throws Exception{
        
        Docente docente = em.find(Docente.class, id);
        
        if (docente == null){
            throw new Exception ("No existe el docente: " + id + " en la base de datos");
        }
        em.remove(docente);
    }
    
    public void deleteAsignatura(Integer id) throws Exception{
        
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
        
        docente.addAsignaturas(asignatura);
        // Añadir (JPA manejará la tabla intermedia)
        //docente.agregarAsignatura(asignatura);

        // Si es bidireccional:
        // asignatura.getDocentes().add(docente);
    }
    /*
     public void quitarAsignaturaADocente(int docenteId, int asignaturaId) throws Exception {
        Docente docente = em.find(Docente.class, docenteId);
        Asignatura asignatura = em.find(Asignatura.class, asignaturaId);

        // Quitar //elimina de la lista y se borrará el registro de la tabla intermedia
        docente.quitarAsignatura(asignatura);

        // Si es bidireccional:
        // asignatura.getDocentes().add(docente);
    }
    */
    
    
    
    public List<Instituto> getAllInstitutos() throws Exception {
    return em.createQuery("SELECT i FROM Instituto i", Instituto.class)
             .getResultList();
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
