package vistas;

import clases.Asignatura;
import clases.DAO;
import clases.Docente;
import clases.Instituto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrincipalController implements Initializable {
    
    DAO dao = new DAO();
    //instanciamos aqui el docente porque necesitamos el objeto en mas de un evento(para agregar asignatura e instituto)
    Docente docente = new Docente();
    
    Asignatura asignatura;
    
    //se crea pero no se instancia, eso se hará al elegirlo desde el combobox
    Instituto instituto;
    
    @FXML
    private Button btn_alta_instituto, btn_modificar_instituto; 
    
    @FXML
    private Button btn_agregar_asignatura_en_docente, btn_quitar_asignatura_en_docente;
    //campos asignatura
    @FXML
    private TextField txt_nombre_asignatura, txt_descripcion, txt_nombre_asignatura_modif, txt_descripcion_modif; 
    //campo instituto
    @FXML
    private TextField txt_denominacion,txt_denominacion_modif;
    
    
    
    @FXML private TextField txt_legajo, txt_documento,txt_nombre_docente,txt_apellido_docente,txt_fecha_nacimiento,txt_dir_notificaciones,txt_carga_horaria,txt_contador_asignaturas;
    
    @FXML private TextField txt_modif_legajo, txt_modif_documento,txt_nombre_modif_docente,txt_apellido_modif_docente,txt_modif_fecha_nacimiento,txt_modif_dir_notificaciones,txt_modif_carga_horaria,txt_modif_contador_asignaturas;
    
    
    @FXML 
    private Label txt_instituto_seleccionado;
    
    @FXML
    ComboBox<Instituto> cbb_institutos;
    
    @FXML 
    ComboBox<Asignatura> cbb_asignaturas_en_docente_alta,cbb_asignaturas_en_docente_modif, cbb_add_asignatura, cbb_quitar_asignatura,cbb_add_asignatura_edicion;
    
    //@FXML
    //ComboBox<Instituto> cbb_institutos_docente;
    
    //@FXML
    //ComboBox<Instituto> cbb_institutos_asignatura;
    
    @FXML
    private TableView<Instituto> tabla_institutos;

    @FXML
    private TableColumn<Instituto, String> col_denominacion;  // una sola columna
    
    @FXML
    private TableColumn<Instituto,Void> columna_accion;
    
    @FXML
    private TableColumn<Instituto,Void> col_editar_instituto;
    
    @FXML
    private TableColumn<Instituto,Void> col_eliminar_instituto;
    
    //lista docente
    @FXML
    private TableView<Docente> tabla_docentes;
    
    @FXML
    private TableColumn<Docente, String> col_legajo, col_documento, col_nombre_docente, col_apellido,col_fecha_nacimiento,col_dir_notificaciones,col_carga_horaria;
    
    @FXML
    private TableColumn<Docente,Void> col_ver_asignaturas;
    
    @FXML
    private TableColumn<Docente,Void> col_editar_docente, col_eliminar_docente;
    
    
    //lista asignatura
    @FXML
    private TableView<Asignatura> tabla_asignaturas;
    
    @FXML
    private TableColumn<Asignatura, String> col_nombre_asignatura, col_descripcion;
    
    @FXML
    private TableColumn<Asignatura,Void> col_editar_asignatura, col_eliminar_asignatura;
    
    @FXML
    private VBox bloque_alta_instituto, bloque_editar_instituto, bloque_alta_docente, bloque_editar_docente,bloque_alta_asignatura,bloque_editar_asignatura;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCamposTablaInstituto();
        configurarCamposTablaDocente();
        configurarCamposAsignatura();
        listarInstitutos();
        //System.out.println("hola");
        cargarComboBoxInstitutos();
        txt_contador_asignaturas.setText("0");
        txt_modif_contador_asignaturas.setText("0");
    }    
    
    
    private void configurarCamposTablaInstituto(){
        
        //Configurar las columnas(se hace una sola vez)
        
        //Guardiola acá, el callback recien se ejecuta cuando la lista es cargada(en el setAll)
        col_denominacion.setCellValueFactory(cellData -> {
                // cellData.getValue() devuelve un objeto Instituto
                String denominacion = cellData.getValue().getDenominacion();
                //System.out.println(denominacion);
                return new SimpleStringProperty(denominacion);
            });
        
        columna_accion.setCellFactory(param -> new TableCell<Instituto, Void>() {
            private final Button btnVer = new Button("Ver Docentes");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    Instituto instituto = getTableView().getItems().get(getIndex());
                    //System.out.println("Ver instituto: " + instituto.getDenominacion());
                    
                    // Acá cargás los docentes y asignaturas en las tablas de abajo
                    listarDocentes(instituto);
                    //cargarAsignaturas(instituto);
                });
            }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : btnVer);
        }
        });
        
    
        col_editar_instituto.setCellFactory(param -> new TableCell<Instituto, Void>() {
            private final Button btnVer = new Button("Editar");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    instituto = getTableView().getItems().get(getIndex());
                    System.out.println("Ver instituto: " + instituto.getDenominacion());
                    
                    // Acá cargás los docentes y asignaturas en las tablas de abajo
                    abrirInterfazEdicionInstituto(instituto);
                    //cargarDocentes(instituto);
                    //cargarAsignaturas(instituto);
                });
            }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : btnVer);
        }
        });
        
        col_eliminar_instituto.setCellFactory(param -> new TableCell<Instituto, Void>() {
            private final Button btnVer = new Button("Eliminar");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    Instituto instituto = getTableView().getItems().get(getIndex());
                    System.out.println("Eliminar instituto: " + instituto.getDenominacion());
                    
                    eliminarInstituto(instituto);
                    cargarComboBoxInstitutos();
                    listarInstitutos();
                    //cargarDocentes(instituto);
                    //cargarAsignaturas(instituto);
                });
            }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : btnVer);
        }

        });
    }
  
    private void configurarCamposTablaDocente(){
        
        col_legajo.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String legajo = cellData.getValue().getLegajo();
            System.out.println(legajo);
            return new SimpleStringProperty(legajo);
        });
        col_documento.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String documento = cellData.getValue().getDocumento();
            System.out.println(documento);
            return new SimpleStringProperty(documento);
        });
        col_nombre_docente.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String nombre_docente = cellData.getValue().getNombre();
            System.out.println(nombre_docente);
            return new SimpleStringProperty(nombre_docente);
        });
        col_apellido.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String apellido_docente = cellData.getValue().getApellido();
            System.out.println(apellido_docente);
            return new SimpleStringProperty(apellido_docente);
        });
        col_fecha_nacimiento.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String fecha_nacimiento = cellData.getValue().getFecha_nacimiento();
            System.out.println(fecha_nacimiento);
            return new SimpleStringProperty(fecha_nacimiento);
        });
        col_dir_notificaciones.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String direccion_notificaciones = cellData.getValue().getDireccion_notificaciones();
            System.out.println(direccion_notificaciones);
            return new SimpleStringProperty(direccion_notificaciones);
        });
        col_carga_horaria.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String carga_horaria = cellData.getValue().getCargaHoraria();
            System.out.println(carga_horaria);
            return new SimpleStringProperty(carga_horaria);
        });
    
        col_ver_asignaturas.setCellFactory(param -> new TableCell<Docente, Void>() {
            private final Button btnVer = new Button("Ver Asignaturas");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    Docente docente = getTableView().getItems().get(getIndex());
                    
                    System.out.println("Asignaturas del docente: " + docente.getApellido() + "N°: " + docente.getAllAsignaturas().size());
                    
                    // Acá se invoca la carga de asignaturas basadas en el docente seleccionado
                    listarAsignaturas(docente);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnVer);
            }
        });
        
    
        col_editar_docente.setCellFactory(param -> new TableCell<Docente, Void>() {
            private final Button btnVer = new Button("Editar");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    docente = getTableView().getItems().get(getIndex());
                    System.out.println("Ver docente: " + docente.getApellido());
                    abrirInterfazEdicionDocente(docente);
                    cargarComboBoxAsignaturasEdicion(docente);
                  
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnVer);
            }
        });
        
        col_eliminar_docente.setCellFactory(param -> new TableCell<Docente, Void>() {
            private final Button btnVer = new Button("Eliminar");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    Docente docente = getTableView().getItems().get(getIndex());
                    System.out.println("Ver docente: " + docente.getApellido());
                 
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnVer);
            }
        });
    
    }
    
    private void configurarCamposAsignatura(){
        col_nombre_asignatura.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String nombre_asignatura = cellData.getValue().getNombre();
            System.out.println(nombre_asignatura);
            return new SimpleStringProperty(nombre_asignatura);
        });
        col_descripcion.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String descripcion = cellData.getValue().getDescripcion();
            System.out.println(descripcion);
            return new SimpleStringProperty(descripcion);
        });
        
        col_editar_asignatura.setCellFactory(param -> new TableCell<Asignatura, Void>() {
            private final Button btnVer = new Button("Editar");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    asignatura = getTableView().getItems().get(getIndex());
                    System.out.println("Editar asignatura : " + asignatura.getDescripcion());
                    abrirInterfazEdicionAsignatura(asignatura);
                    
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnVer);
            }
        });
        
        col_eliminar_asignatura.setCellFactory(param -> new TableCell<Asignatura, Void>() {
            private final Button btnVer = new Button("Eliminar");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    Asignatura asignatura = getTableView().getItems().get(getIndex());
                    System.out.println("Eliminar asignatura : " + asignatura.getDescripcion());
                 
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnVer);
            }
        });
    }
    
    @FXML
    public void agregarInstituto(){
        try {
            dao.beginTransaction();

            String denominacion = txt_denominacion.getText();
            System.out.println("denominación :" + denominacion);

            Instituto i = new Instituto(denominacion);


            //System.out.println("apretá y ganá");

            dao.insertInstituto(i);
            cargarComboBoxInstitutos();
            listarInstitutos();
            dao.commitTransaction();
        }
             catch(Exception e){
            dao.rollbackTransaction();
            e.printStackTrace(); // Muestra el error
            // Opcional: mostrar mensaje al usuario
            System.out.println("Error: " + e.getMessage());
        }/*
        finally{
            dao.close();
        }*/
        
    }
    
    @FXML
    public void abrirInterfazEdicionInstituto(Instituto instituto){
        try{
            bloque_alta_instituto.setVisible(false);
            bloque_editar_instituto.setVisible(true);
            txt_denominacion_modif.setText(instituto.getDenominacion());
        }
        
        catch(Exception e){
            dao.rollbackTransaction();
            e.printStackTrace(); // Muestra el error
            // Opcional: mostrar mensaje al usuario
            System.out.println("Error: " + e.getMessage());
        }
    
    }
    
    private List<Instituto> obtenerTodosLosInstitutos(){
        try {
            return dao.getAllInstitutos();
        }
        catch (Exception e) {
            System.err.println("Error al cargar institutos: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }
        return null;
    }
    
    
    private void cargarComboBoxInstitutos(){
        try {
            //List<Instituto> institutos = dao.getAllInstitutos();
            System.out.println("Cantidad institutos en el sistema : " + obtenerTodosLosInstitutos().size());
            cbb_institutos.getItems().setAll(obtenerTodosLosInstitutos());
            
            //cbb_institutos_docente.getItems().setAll(institutos);
            //cbb_institutos_asignatura.getItems().setAll(institutos);

            // Opcional: seleccionar el primero por defecto
            //if (!institutos.isEmpty()) {
            //    cbb_institutos.getSelectionModel().selectFirst();
            //}
        } catch (Exception e) {
            System.err.println("Error al cargar institutos: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }
    }
    
    private void cargarComboBoxAsignaturasAlta() {
        try {
            List<Asignatura> asignaturas = instituto.getAllAsignaturas();
            System.out.println("asignaturas : " + asignaturas.size());
            cbb_asignaturas_en_docente_alta.getItems().setAll(asignaturas);
            //cbb_institutos_docente.getItems().setAll(institutos);
            //cbb_institutos_asignatura.getItems().setAll(institutos);

            // Opcional: seleccionar el primero por defecto
            //if (!institutos.isEmpty()) {
            //    cbb_institutos.getSelectionModel().selectFirst();
            //}
        } catch (Exception e) {
            System.err.println("Error al cargar asignaturas: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }
    }
    
    private void cargarComboBoxAsignaturasEdicion(Docente docente) {
        try {
            //obtengo las asignaturas del docente elegido en edición
            List<Asignatura> asignaturas = docente.getAllAsignaturas();
            
            System.out.println("asignaturas : " + asignaturas.size());
            
            
            //cargamos el cbb de las asignaturas en docente
            cbb_asignaturas_en_docente_modif.getItems().setAll(asignaturas);
            
            //con la misma lista cargamos el cbb donde para quitar la asignatura en caso que desee hacerlo
            cbb_quitar_asignatura.getItems().setAll(asignaturas);
            
            //cargamos el cbb para agregar, este lo cargamos con las asignaturas del instituto seleccionado
            List<Asignatura> asignaturas_i = instituto.getAllAsignaturas();
            
            cbb_add_asignatura_edicion.getItems().setAll(asignaturas_i);
            
            //cbb_institutos_docente.getItems().setAll(institutos);
            //cbb_institutos_asignatura.getItems().setAll(institutos);

            // Opcional: seleccionar el primero por defecto
            //if (!institutos.isEmpty()) {
            //    cbb_institutos.getSelectionModel().selectFirst();
            //}
        } catch (Exception e) {
            System.err.println("Error al cargar asignaturas: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }
    }
    
    @FXML
    private void seleccionarInstituto() {
        // El ComboBox ya tiene el objeto seleccionado
        instituto = cbb_institutos.getValue();
        txt_instituto_seleccionado.setText(instituto.getDenominacion());
        if (instituto != null) {
            System.out.println("Seleccionaste: " + instituto.getDenominacion());

            // Acá cargás los docentes, mostrás en tabla, etc.
            cargarDocentesDelInstituto(instituto);
            
            //al tener el instituto elegido, se cargaran sus asignaturas
            cargarComboBoxAsignaturasAlta();
        }
    }
    
    @FXML
    private void seleccionarAsignaturaEnDocenteAlta() {
        // El ComboBox ya tiene el objeto seleccionado
        asignatura = cbb_asignaturas_en_docente_alta.getValue();
        if (asignatura != null) {
            System.out.println("Seleccionaste: " + asignatura.getNombre());

            //limpiar el combobox asignatura
           
        }
    }
    
       @FXML
    private void seleccionarAsignaturaEnDocenteEdicion() {
        // El ComboBox ya tiene el objeto seleccionado
        asignatura = cbb_asignaturas_en_docente_modif.getValue();
        if (asignatura != null) {
            System.out.println("Seleccionaste: " + asignatura.getNombre());

            //limpiar el combobox asignatura
             
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

    @FXML
    public void agregarDocente(){
        try{
            dao.beginTransaction();
            String legajo = txt_legajo.getText();
            String documento = txt_documento.getText();
            String nombre = txt_nombre_docente.getText();
            String apellido = txt_apellido_docente.getText();
            String fecha_nacimiento = txt_fecha_nacimiento.getText();
            String dir_notificaciones = txt_dir_notificaciones.getText();
            String carga_horaria = txt_carga_horaria.getText();
            
            
            
            
            docente.setLegajo(legajo);
            docente.setDocumento(documento);
            docente.setNombre(nombre);
            docente.setApellido(apellido);
            docente.setFecha_nacimiento(fecha_nacimiento);
            docente.setDireccion_notificaciones(dir_notificaciones);
            docente.setCargaHoraria(carga_horaria);
            
            //asigno el instituto a docente
            docente.setInstituto(instituto);
            
            dao.insertDocente(docente);
            
            //con el helper establecemos la bidireccionalidad asi en tiempo de ejecución puedo ver los docentes del instituto
            instituto.addDocente(docente);
            
            dao.commitTransaction();
            //reseteamos el objeto docente para en caso de querer agregar más en tiempo de ejecución se reinicie a cero
            docente = new Docente();
        } catch (Exception e) {
            System.err.println("Error al cargar institutos: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }
        /*finally{
            dao.close();
        }*/
        
    }
    
    @FXML
    public void agregarAsignatura(){
        
        try{
            
            dao.beginTransaction();
            
            String nombre = txt_nombre_asignatura.getText();
            
            String descripcion = txt_descripcion.getText();
            
            Asignatura asignatura = new Asignatura(nombre,descripcion);
            System.out.println(instituto.getDenominacion());
            asignatura.setInstituto(instituto);
            
            dao.insertAsignatura(asignatura);
            
            //usamos el helper para actualizar la lista en memoria, sin esto no podría ver la asignatura recientemente guardada en pantalla
            instituto.addAsignatura(asignatura);
            
            //al crear la asignatura cargamos el combobox de asignaturas en docente para que se actualice
            cargarComboBoxAsignaturasAlta();
            
            dao.commitTransaction();
        } catch (Exception e) {
            System.err.println("Error al grabar asignatura: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }/*
        finally{
            dao.close();
        }*/
    }
    
    @FXML
    public void asignarAsignaturaADocenteEnAlta(){
        //asignatura = cbb_asignaturas_en_docente_alta.getValue();
        System.out.println(asignatura);
        if(asignatura != null){
            
            docente.addAsignaturas(asignatura);
            incrementarAsignaturaEnDocenteAlta(docente.getAllAsignaturas().size());
        
        }
    }
    
    
    //esta función es para actualizar el cbb de asignaturas en docente en la interfaz modificanción en tiempo de ejecución
    private void refrescarCbbAsignaturas(){
        //obtengo las asignaturas del docente elegido en edición
        List<Asignatura> asignaturas = docente.getAllAsignaturas();
            
        System.out.println("asignaturas : " + asignaturas.size());
            
            
        //cargamos el cbb de las asignaturas en docente
        cbb_asignaturas_en_docente_modif.getItems().setAll(asignaturas);
        cbb_quitar_asignatura.getItems().setAll(asignaturas);
    }
    
    @FXML
    public void asignarAsignaturaADocenteEdicion(){
        asignatura = cbb_add_asignatura_edicion.getValue();
        System.out.println(asignatura);
        if(asignatura != null){
            
            docente.addAsignaturas(asignatura);
            refrescarCbbAsignaturas();
            incrementarAsignaturaEnDocenteEdicion(docente.getAllAsignaturas().size());
        
        }
    }
    
    private void incrementarAsignaturaEnDocenteAlta(int cantidad_asignaturas) {
        //casteamos el int a string para colocarlo en pantalla
        txt_contador_asignaturas.setText(String.valueOf(cantidad_asignaturas));
    }
    
    private void incrementarAsignaturaEnDocenteEdicion(int cantidad_asignaturas) {
        //casteamos el int a string para colocarlo en pantalla
        txt_modif_contador_asignaturas.setText(String.valueOf(cantidad_asignaturas));
    }
    
    @FXML
    public void quitarAsignaturaADocente(){
        asignatura = cbb_quitar_asignatura.getValue();
        System.out.println(asignatura);
        if(asignatura != null){
            docente.quitarAsignatura(asignatura);
            refrescarCbbAsignaturas();
            decrementarAsignaturaEnDocente(docente.getAllAsignaturas().size());
        
        }
    }
    
    private void decrementarAsignaturaEnDocente(int cantidad_asignaturas) {
        //casteamos el int a string para colocarlo en pantalla
        if(cantidad_asignaturas != 0){
            cantidad_asignaturas = cantidad_asignaturas - 1;
        }
        txt_modif_contador_asignaturas.setText(String.valueOf(cantidad_asignaturas));
    }
    
    //Listas
    @FXML
    public void listarInstitutos(){
        //System.out.println("listar institutos");
        try{
            tabla_institutos.setVisible(true);
            List<Instituto> institutos = obtenerTodosLosInstitutos();
            //Asignar la lista a la tabla
            tabla_institutos.getItems().setAll(institutos);
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
        }
    }
    
    public void listarDocentes(Instituto instituto){
        try{
            System.out.println("lista ver docentes, del instituto : " + instituto.getDenominacion() + "docentes : " + instituto.getAllDocentes().size());
            List<Docente> docentes = instituto.getAllDocentes();
            tabla_docentes.getItems().setAll(docentes);
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
        }
    }
    
    public void listarAsignaturas(Docente docente){
        try{
            List<Asignatura> asignaturas = docente.getAllAsignaturas();
            tabla_asignaturas.getItems().setAll(asignaturas);
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
        }
    }
    
    private void eliminarInstituto(Instituto instituto) {
        try{
            dao.beginTransaction();
            dao.deleteInstituto(instituto.getId());
            dao.commitTransaction();
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
        }
    }
    
    @FXML 
    public void modificarInstituto(){
        System.out.println("Instituto a modificar : " + instituto);
        System.out.println("campo del textfield :" + txt_denominacion_modif.getText());
        try{
            dao.beginTransaction();
            instituto.setDenominacion(txt_denominacion_modif.getText());
            dao.commitTransaction();
            bloque_alta_instituto.setVisible(true);
            bloque_editar_instituto.setVisible(false);
            listarInstitutos();
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
            }
    }
    
    private void abrirInterfazEdicionDocente(Docente docente) {
        bloque_alta_docente.setVisible(false);
        bloque_editar_docente.setVisible(true);
        
        //cargo los campos en la interfaz edición
        cargarCamposEdicionDocente(docente);
    }

    private void cargarCamposEdicionDocente(Docente docente) {
        txt_modif_legajo.setText(docente.getLegajo());
        txt_modif_documento.setText(docente.getDocumento());
        txt_nombre_modif_docente.setText(docente.getNombre());
        txt_apellido_modif_docente.setText(docente.getApellido());
        txt_modif_fecha_nacimiento.setText(docente.getFecha_nacimiento());
        txt_modif_dir_notificaciones.setText(docente.getDireccion_notificaciones());
        txt_modif_carga_horaria.setText(docente.getCargaHoraria());
    }
    
    @FXML
    public void modificarDocente(){
       try {
           System.out.println("docente a editar: " + docente); 
           
           dao.beginTransaction();

            docente.setLegajo(txt_modif_legajo.getText());
            docente.setDocumento(txt_modif_documento.getText());
            docente.setNombre(txt_nombre_modif_docente.getText());
            docente.setApellido(txt_apellido_modif_docente.getText());
            docente.setFecha_nacimiento(txt_modif_fecha_nacimiento.getText());
            docente.setDireccion_notificaciones(txt_modif_dir_notificaciones.getText());
            docente.setCargaHoraria(txt_modif_carga_horaria.getText());
            
            dao.commitTransaction();
        }
        catch(Exception e){
            dao.rollbackTransaction();
            e.printStackTrace(); // Muestra el error
            // Opcional: mostrar mensaje al usuario
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @FXML
    public void modificarAsignatura(){

         try {
            System.out.println("Asignatura a editar : " + asignatura); 
           
            dao.beginTransaction();
            
                asignatura.setNombre(txt_nombre_asignatura_modif.getText());
                asignatura.setDescripcion(txt_descripcion_modif.getText());
                            
            dao.commitTransaction();
        }
        catch(Exception e){
            dao.rollbackTransaction();
            e.printStackTrace(); // Muestra el error
            // Opcional: mostrar mensaje al usuario
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void abrirInterfazEdicionAsignatura(Asignatura asignatura) {
        bloque_alta_asignatura.setVisible(false);
        bloque_editar_asignatura.setVisible(true);
        
        //cargo los campos en la interfaz edición
        cargarCamposEdicionAsignatura(asignatura);
    }
    
    private void cargarCamposEdicionAsignatura(Asignatura asignatura){
        txt_nombre_asignatura_modif.setText(asignatura.getNombre());
        txt_descripcion_modif.setText(asignatura.getDescripcion());
    }
    
}
