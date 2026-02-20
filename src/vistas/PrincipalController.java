package vistas;

import clases.DAO;
import clases.Instituto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PrincipalController implements Initializable {
    
    DAO dao = new DAO();
    
    
    @FXML
    private Button btn_alta_instituto; 
    
    @FXML
    private TextField txt_denominacion;
    
    @FXML
    ComboBox<Instituto> cbb_institutos;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hola");
        cargarComboBoxInstitutos();
    }    
    
    @FXML
    public void agregarInstituto(){
        try {
        dao.beginTransaction();
        
        String denominacion = txt_denominacion.getText();
        
        Instituto i = new Instituto(denominacion);
        
        
        //System.out.println("apretá y ganá");
        
        dao.insertInstituto(i);
        
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
    private void cargarComboBoxInstitutos() {
        try {
            List<Instituto> institutos = dao.getAllInstitutos();
            cbb_institutos.getItems().setAll(institutos);

            // Opcional: seleccionar el primero por defecto
            if (!institutos.isEmpty()) {
                cbb_institutos.getSelectionModel().selectFirst();
            }
        } catch (Exception e) {
            System.err.println("Error al cargar institutos: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }
    }
    
    @FXML
    private void seleccionarInstituto() {
        // El ComboBox ya tiene el objeto seleccionado
        Instituto institutoSeleccionado = cbb_institutos.getValue();

        if (institutoSeleccionado != null) {
            System.out.println("Seleccionaste: " + institutoSeleccionado.getDenominacion());

            // Acá cargás los docentes, mostrás en tabla, etc.
            cargarDocentesDelInstituto(institutoSeleccionado);
            
            cargarAsignaturasDelInstituto(institutoSeleccionado);
        }
    }

    private void cargarDocentesDelInstituto(Instituto instituto) {
        // TODO: implementar después
        System.out.println("Docentes del instituto: " + instituto.getAllDocentes().size());
    }
    
    private void cargarAsignaturasDelInstituto(Instituto instituto) {
        // TODO: implementar después
        System.out.println("Asignaturas del instituto: " + instituto.getAllAsignaturas().size());
    }


    
}
