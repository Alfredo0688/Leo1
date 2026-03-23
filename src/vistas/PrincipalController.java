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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrincipalController implements Initializable {
    
    DAO dao = new DAO();
    //instanciamos aqui el docente porque necesitamos el objeto en mas de un evento(para agregar asignatura e instituto)
    Docente docente = new Docente();
    
    Asignatura asignatura;
    
    //se crea pero no se instancia, eso se hará al elegirlo desde el combobox
    Instituto instituto;
    
    @FXML
    private Button btnAgregarInstituto, btnModificarInstituto; 
    
    @FXML
    private Button btnIngresar, btnCerrarEdicionAsignatura, btnCerrarEdicionDocente;
    
    @FXML
    private Button btnAgregarAsignaturaEnDocente, btnQuitarAsignaturaEnDocente;
    //campos asignatura
    @FXML
    private TextField txtNombreAsignatura, txtDescripcion, txtNombreAsignaturaModif, txtDescripcionModif; 
    //campo instituto
    @FXML
    private TextField txtDenominacion,txtDenominacionModif;
    
    
    
    @FXML private TextField txtLegajo, txtDocumento,txtNombreDocente,txtApellidoDocente,txtFechaNacimiento,txtDirNotificaciones,txtCargaHoraria,txtContadorAsignaturas;
    
    @FXML private TextField txtModifLegajo, txtModifDocumento,txtNombreModifDocente,txtApellidoModifDocente,txtModifFechaNacimiento,txtModifDirNotificaciones,txtModifCargaHoraria,txtModifContadorAsignaturas;
    
    
    @FXML 
    private Label txtInstitutoSeleccionado;
    
    @FXML
    ComboBox<Instituto> cbbInstitutos;
    
    @FXML 
    ComboBox<Asignatura> cbbAsignaturasEnDocenteAlta,cbbAsignaturasEnDocenteModif, cbb_add_asignatura, cbbQuitarAsignatura,cbbAddAsignaturaEdicion;
    
    //@FXML
    //ComboBox<Instituto> cbb_institutos_docente;
    
    //@FXML
    //ComboBox<Instituto> cbb_institutos_asignatura;
    
    @FXML
    private TableView<Instituto> tablaInstitutos;

    @FXML
    private TableColumn<Instituto, String> colDenominacion;  // una sola columna
    
    @FXML
    private TableColumn<Instituto,Void> columnaAccion;
    
    @FXML
    private TableColumn<Instituto,Void> colEditarInstituto;
    
    @FXML
    private TableColumn<Instituto,Void> colEliminarInstituto;
    
    //lista docente
    @FXML
    private TableView<Docente> tablaDocentes;
    
    @FXML
    private TableColumn<Docente, String> colLegajo, colDocumento, colNombreDocente, colApellido,colFechaNacimiento,colDirNotificaciones,colCargaHoraria;
    
    @FXML
    private TableColumn<Docente,Void> colVerAsignaturas;
    
    @FXML
    private TableColumn<Docente,Void> colEditarDocente, colEliminarDocente;
    
    
    //lista asignatura
    @FXML
    private TableView<Asignatura> tablaAsignaturas;
    
    @FXML
    private TableColumn<Asignatura, String> colNombreAsignatura, colDescripcion;
    
    @FXML
    private TableColumn<Asignatura,Void> colEditarAsignatura, colEliminarAsignatura;
    
    @FXML
    private VBox bloqueAltaInstituto, bloqueEditarInstituto, bloqueAltaDocente, bloqueEditarDocente,bloqueAltaAsignatura,bloqueEditarAsignatura,bloqueBuscarInstituto;
    
    @FXML
    private HBox bloqueInstitutoElegido, bloqueMensajeInicial;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCamposTablaInstituto();
        configurarCamposTablaDocente();
        configurarCamposAsignatura();
        listarInstitutos();
        //System.out.println("hola");
        cargarComboBoxInstitutos();
        txtContadorAsignaturas.setText("0");
        txtModifContadorAsignaturas.setText("0");
    }    
    
    
    private void configurarCamposTablaInstituto(){
        
        //Configurar las columnas(se hace una sola vez)
        
        //Guardiola acá, el callback recien se ejecuta cuando la lista es cargada(en el setAll)
        colDenominacion.setCellValueFactory(cellData -> {
                // cellData.getValue() devuelve un objeto Instituto
                String denominacion = cellData.getValue().getDenominacion();
                //System.out.println(denominacion);
                return new SimpleStringProperty(denominacion);
            });
        
        columnaAccion.setCellFactory(param -> new TableCell<Instituto, Void>() {
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
        
    
        colEditarInstituto.setCellFactory(param -> new TableCell<Instituto, Void>() {
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
        
        colEliminarInstituto.setCellFactory(param -> new TableCell<Instituto, Void>() {
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
        
        colLegajo.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String legajo = cellData.getValue().getLegajo();
            System.out.println(legajo);
            return new SimpleStringProperty(legajo);
        });
        colDocumento.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String documento = cellData.getValue().getDocumento();
            System.out.println(documento);
            return new SimpleStringProperty(documento);
        });
        colNombreDocente.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String nombre_docente = cellData.getValue().getNombre();
            System.out.println(nombre_docente);
            return new SimpleStringProperty(nombre_docente);
        });
        colApellido.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String apellido_docente = cellData.getValue().getApellido();
            System.out.println(apellido_docente);
            return new SimpleStringProperty(apellido_docente);
        });
        colFechaNacimiento.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String fecha_nacimiento = cellData.getValue().getFechaNacimiento();
            System.out.println(fecha_nacimiento);
            return new SimpleStringProperty(fecha_nacimiento);
        });
        colDirNotificaciones.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String direccion_notificaciones = cellData.getValue().getDireccionNotificaciones();
            System.out.println(direccion_notificaciones);
            return new SimpleStringProperty(direccion_notificaciones);
        });
        colCargaHoraria.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String carga_horaria = cellData.getValue().getCargaHoraria();
            System.out.println(carga_horaria);
            return new SimpleStringProperty(carga_horaria);
        });
    
        colVerAsignaturas.setCellFactory(param -> new TableCell<Docente, Void>() {
            private final Button btnVer = new Button("Ver Asignaturas");

            {
                // Acción del botón
                btnVer.setOnAction(event -> {
                    Docente docente = getTableView().getItems().get(getIndex());
                    
                    System.out.println("Asignaturas del docente: " + docente.getApellido() + "N°: " + docente.obtenerTodasAsignaturas().size());
                    
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
        
    
        colEditarDocente.setCellFactory(param -> new TableCell<Docente, Void>() {
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
        
        colEliminarDocente.setCellFactory(param -> new TableCell<Docente, Void>() {
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
        colNombreAsignatura.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String nombre_asignatura = cellData.getValue().getNombre();
            System.out.println(nombre_asignatura);
            return new SimpleStringProperty(nombre_asignatura);
        });
        colDescripcion.setCellValueFactory(cellData -> {
            // cellData.getValue() devuelve un objeto Instituto
            String descripcion = cellData.getValue().getDescripcion();
            System.out.println(descripcion);
            return new SimpleStringProperty(descripcion);
        });
        
        colEditarAsignatura.setCellFactory(param -> new TableCell<Asignatura, Void>() {
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
        
        colEliminarAsignatura.setCellFactory(param -> new TableCell<Asignatura, Void>() {
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

            String denominacion = txtDenominacion.getText();
            System.out.println("denominación :" + denominacion);

            Instituto i = new Instituto(denominacion);


            //System.out.println("apretá y ganá");

            dao.agregarInstituto(i);
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
            bloqueAltaInstituto.setVisible(false);
            bloqueEditarInstituto.setVisible(true);
            txtDenominacionModif.setText(instituto.getDenominacion());
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
            return dao.obtenerTodosInstitutos();
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
            cbbInstitutos.getItems().setAll(obtenerTodosLosInstitutos());
            
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
            List<Asignatura> asignaturas = instituto.obtenerTodasAsignaturas();
            System.out.println("asignaturas : " + asignaturas.size());
            cbbAsignaturasEnDocenteAlta.getItems().setAll(asignaturas);
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
            List<Asignatura> asignaturas = docente.obtenerTodasAsignaturas();
            
            System.out.println("asignaturas : " + asignaturas.size());
            
            
            //cargamos el cbb de las asignaturas en docente
            cbbAsignaturasEnDocenteModif.getItems().setAll(asignaturas);
            
            //con la misma lista cargamos el cbb donde para quitar la asignatura en caso que desee hacerlo
            cbbQuitarAsignatura.getItems().setAll(asignaturas);
            
            //cargamos el cbb para agregar, este lo cargamos con las asignaturas del instituto seleccionado
            List<Asignatura> asignaturas_i = instituto.obtenerTodasAsignaturas();
            
            cbbAddAsignaturaEdicion.getItems().setAll(asignaturas_i);
            
            txtModifContadorAsignaturas.setText(String.valueOf(asignaturas.size()));
            
        } catch (Exception e) {
            System.err.println("Error al cargar asignaturas: " + e.getMessage());
            e.printStackTrace(); // Solo para desarrollo
        }
    }
    
    @FXML
    private void seleccionarInstituto() {
        // El ComboBox ya tiene el objeto seleccionado
        instituto = cbbInstitutos.getValue();
        txtInstitutoSeleccionado.setText(instituto.getDenominacion());
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
        asignatura = cbbAsignaturasEnDocenteAlta.getValue();
        if (asignatura != null) {
            System.out.println("Seleccionaste: " + asignatura.getNombre());

            //limpiar el combobox asignatura
           
        }
    }
    
       @FXML
    private void seleccionarAsignaturaEnDocenteEdicion() {
        // El ComboBox ya tiene el objeto seleccionado
        asignatura = cbbAsignaturasEnDocenteModif.getValue();
        if (asignatura != null) {
            System.out.println("Seleccionaste: " + asignatura.getNombre());

            //limpiar el combobox asignatura
             
        }
    }

    private void cargarDocentesDelInstituto(Instituto instituto) {
        // TODO: implementar después
        System.out.println("Docentes del instituto: " + instituto.obtenerTodosDocentes().size());
    }
    
    private void cargarAsignaturasDelInstituto(Instituto instituto) {
        // TODO: implementar después
        System.out.println("Asignaturas del instituto: " + instituto.obtenerTodasAsignaturas().size());
    }

    @FXML
    public void agregarDocente(){
        try{
            dao.beginTransaction();
            String legajo = txtLegajo.getText();
            String documento = txtDocumento.getText();
            String nombre = txtNombreDocente.getText();
            String apellido = txtApellidoDocente.getText();
            String fecha_nacimiento = txtFechaNacimiento.getText();
            String dir_notificaciones = txtDirNotificaciones.getText();
            String carga_horaria = txtCargaHoraria.getText();
            
            
            
            
            docente.setLegajo(legajo);
            docente.setDocumento(documento);
            docente.setNombre(nombre);
            docente.setApellido(apellido);
            docente.setFechaNacimiento(fecha_nacimiento);
            docente.setDireccionNotificaciones(dir_notificaciones);
            docente.setCargaHoraria(carga_horaria);
            
            //asigno el instituto a docente
            docente.setInstituto(instituto);
            
            dao.agregarDocente(docente);
            
            //con el helper establecemos la bidireccionalidad asi en tiempo de ejecución puedo ver los docentes del instituto
            instituto.agregarDocente(docente);
            
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
            
            String nombre = txtNombreAsignatura.getText();
            
            String descripcion = txtDescripcion.getText();
            
            Asignatura asignatura = new Asignatura(nombre,descripcion);
            System.out.println(instituto.getDenominacion());
            asignatura.setInstituto(instituto);
            
            dao.agregarAsignatura(asignatura);
            
            //usamos el helper para actualizar la lista en memoria, sin esto no podría ver la asignatura recientemente guardada en pantalla
            instituto.agregarAsignatura(asignatura);
            
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
        //asignatura = cbbAsignaturasEnDocenteAlta.getValue();
        System.out.println(asignatura);
        if(asignatura != null){
            
            docente.agregarAsignatura(asignatura);
            incrementarAsignaturaEnDocenteAlta(docente.obtenerTodasAsignaturas().size());
        
        }
    }
    
    
    //esta función es para actualizar el cbb de asignaturas en docente en la interfaz modificanción en tiempo de ejecución
    private void refrescarCbbAsignaturas(){
        //obtengo las asignaturas del docente elegido en edición
        List<Asignatura> asignaturas = docente.obtenerTodasAsignaturas();
            
        System.out.println("asignaturas : " + asignaturas.size());
            
            
        //cargamos el cbb de las asignaturas en docente
        cbbAsignaturasEnDocenteModif.getItems().setAll(asignaturas);
        cbbQuitarAsignatura.getItems().setAll(asignaturas);
    }
    
    @FXML
    public void asignarAsignaturaADocenteEdicion(){
        asignatura = cbbAddAsignaturaEdicion.getValue();
        System.out.println(asignatura);
        if(asignatura != null){
            
            docente.agregarAsignatura(asignatura);
            refrescarCbbAsignaturas();
            incrementarAsignaturaEnDocenteEdicion(docente.obtenerTodasAsignaturas().size());
        
        }
    }
    
    private void incrementarAsignaturaEnDocenteAlta(int cantidad_asignaturas) {
        //casteamos el int a string para colocarlo en pantalla
        txtContadorAsignaturas.setText(String.valueOf(cantidad_asignaturas));
    }
    
    private void incrementarAsignaturaEnDocenteEdicion(int cantidad_asignaturas) {
        //casteamos el int a string para colocarlo en pantalla
        txtModifContadorAsignaturas.setText(String.valueOf(cantidad_asignaturas));
    }
    
    @FXML
    public void quitarAsignaturaADocente(){
        asignatura = cbbQuitarAsignatura.getValue();
        System.out.println(asignatura);
        if(asignatura != null){
            docente.quitarAsignatura(asignatura);
            refrescarCbbAsignaturas();
            decrementarAsignaturaEnDocente(docente.obtenerTodasAsignaturas().size());
            
        }
    }
    
    private void decrementarAsignaturaEnDocente(int cantidad_asignaturas) {
        //casteamos el int a string para colocarlo en pantalla
        System.out.println("funcion decrementar, cantidad de asignaturas en docente :" + cantidad_asignaturas);
        /*if(cantidad_asignaturas != 0){
            cantidad_asignaturas = cantidad_asignaturas - 1;
        }*/
        txtModifContadorAsignaturas.setText(String.valueOf(cantidad_asignaturas));
    }
    
    //Listas
    @FXML
    public void listarInstitutos(){
        //System.out.println("listar institutos");
        try{
            List<Instituto> institutos = obtenerTodosLosInstitutos();
            //Asignar la lista a la tabla
            tablaInstitutos.getItems().setAll(institutos);
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
        }
    }
    
    public void listarDocentes(Instituto instituto){
        try{
            System.out.println("lista ver docentes, del instituto : " + instituto.getDenominacion() + "docentes : " + instituto.obtenerTodosDocentes().size());
            List<Docente> docentes = instituto.obtenerTodosDocentes();
            tablaDocentes.getItems().setAll(docentes);
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
        }
    }
    
    public void listarAsignaturas(Docente docente){
        try{
            List<Asignatura> asignaturas = docente.obtenerTodasAsignaturas();
            tablaAsignaturas.getItems().setAll(asignaturas);
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
        }
    }
    
    private void eliminarInstituto(Instituto instituto) {
        try{
            dao.beginTransaction();
            dao.borrarInstituto(instituto.getId());
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
        System.out.println("campo del textfield :" + txtDenominacionModif.getText());
        try{
            dao.beginTransaction();
            instituto.setDenominacion(txtDenominacionModif.getText());
            dao.commitTransaction();
            bloqueEditarInstituto.setVisible(false);
            listarInstitutos();
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();  // para ver más detalles
            }
    }
    
    private void abrirInterfazEdicionDocente(Docente docente) {
        bloqueAltaDocente.setVisible(false);
        bloqueEditarDocente.setVisible(true);
        
        //cargo los campos en la interfaz edición
        cargarCamposEdicionDocente(docente);
    }

    private void cargarCamposEdicionDocente(Docente docente) {
        txtModifLegajo.setText(docente.getLegajo());
        txtModifDocumento.setText(docente.getDocumento());
        txtNombreModifDocente.setText(docente.getNombre());
        txtApellidoModifDocente.setText(docente.getApellido());
        txtModifFechaNacimiento.setText(docente.getFechaNacimiento());
        txtModifDirNotificaciones.setText(docente.getDireccionNotificaciones());
        txtModifCargaHoraria.setText(docente.getCargaHoraria());
    }
    
    @FXML
    public void modificarDocente(){
       try {
           System.out.println("docente a editar: " + docente); 
           
           dao.beginTransaction();

            docente.setLegajo(txtModifLegajo.getText());
            docente.setDocumento(txtModifDocumento.getText());
            docente.setNombre(txtNombreModifDocente.getText());
            docente.setApellido(txtApellidoModifDocente.getText());
            docente.setFechaNacimiento(txtModifFechaNacimiento.getText());
            docente.setDireccionNotificaciones(txtModifDirNotificaciones.getText());
            docente.setCargaHoraria(txtModifCargaHoraria.getText());
            
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
            
                asignatura.setNombre(txtNombreAsignaturaModif.getText());
                asignatura.setDescripcion(txtDescripcionModif.getText());
                            
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
        bloqueAltaAsignatura.setVisible(false);
        bloqueEditarAsignatura.setVisible(true);
        
        //cargo los campos en la interfaz edición
        cargarCamposEdicionAsignatura(asignatura);
    }
    
    private void cargarCamposEdicionAsignatura(Asignatura asignatura){
        txtNombreAsignaturaModif.setText(asignatura.getNombre());
        txtDescripcionModif.setText(asignatura.getDescripcion());
    }
    
    @FXML
    public void ingresarAlSistema(){
        
        mostrarBloquesOcultos();
        ocultarBloquesIniciales();
    
    }

    private void mostrarBloquesOcultos() {
        bloqueAltaDocente.setVisible(true);
        bloqueAltaAsignatura.setVisible(true);
        bloqueInstitutoElegido.setVisible(true);
        tablaInstitutos.setVisible(true);
        tablaDocentes.setVisible(true);
        tablaAsignaturas.setVisible(true);
    }

    private void ocultarBloquesIniciales() {
        bloqueMensajeInicial.setVisible(false);
        bloqueBuscarInstituto.setVisible(false);
        bloqueAltaInstituto.setVisible(false);
        btnIngresar.setVisible(false);


    }
 
    
    @FXML
    public void cerrarEdicionAsignatura(){
        bloqueAltaAsignatura.setVisible(true);
        bloqueEditarAsignatura.setVisible(false);
    }
    
    @FXML
    public void cerrarEdicionDocente(){
        bloqueAltaDocente.setVisible(true);
        bloqueEditarDocente.setVisible(false);
    }
}
