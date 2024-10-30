package ies.pedro.usuario.view;


import ies.pedro.usuario.model.Usuario;
import ies.pedro.usuario.viewmodel.UsuarioViewModel;
import ies.pedro.viewlayer.AWindows;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.controlsfx.control.GridView;

public class ListadoUsuariosView extends AWindows {
    @FXML
    private MFXButton reload;
    @FXML
    private Label titulo;
    @FXML
    private MFXButton up;
    @FXML
    private MFXTextField searchField;

    @FXML
    private GridView<Usuario> grid;
    private UsuarioViewModel viewModel;
    private ContextMenu contextMenu;
    FilteredList<Usuario> listaFiltrada;// = new FilteredList<>(personas, p -> true);

    public ListadoUsuariosView() {
        super();

    }
    public void setViewModel(UsuarioViewModel viewModel) {

        if (viewModel != null) {
            this.viewModel = viewModel;
            this.configContexMenu();
                this.titulo.setText("Listado de usuarios");
            //lista filtrada, por defecto son todos
            //se enlaza a los items
            listaFiltrada = new FilteredList<>(viewModel.getItems(), p -> {
                    return true;
            });
            //se asigna la lista filtrada
            grid.setItems(this.listaFiltrada);

            this.refresh();

        }

    }
    public UsuarioViewModel getViewModel() {
        return viewModel;
    }
    private void configContexMenu() {
        this.contextMenu = new ContextMenu();

        // Crear elementos del menú
        MenuItem item1 = new MenuItem("Borrar");

        // Añadir los elementos al menú contextual
        contextMenu.getItems().addAll(item1);

        // Configurar las acciones al seleccionar un item
        item1.setOnAction(e ->
        {
            if (this.viewModel.getSelected() != null && this.viewModel.getSelected().get().getId()!=-1) {

                this.viewModel.removeSelected();


            }
        });

    }

    @FXML
    public void initialize() {
        //subir de nivel
        this.up.setOnMouseClicked(event -> {
            //se va hacia atras
            if (this.router != null) {
                this.router.pop();
            }
        });
        //recargar los items
        this.reload.setOnMouseClicked(event -> {
            if(this.viewModel!=null) {
                this.viewModel.reload();
            }
        });
this.searchField.textProperty().addListener((observable, oldValue, newValue) -> {
    this.listaFiltrada.setPredicate(usuario -> {
        // If filter text is empty, display all persons
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        // Compare name with the filter text
        String lowerCaseFilter = newValue.toLowerCase();
        //el -1 es para el de crear
        return usuario.getId()==-1 ||usuario.getNombre().toLowerCase().contains(lowerCaseFilter) || usuario.getCorreo().toLowerCase().contains(lowerCaseFilter);
    });
});

        this.initGrid();


    }

    private void initGrid() {
        grid.setCellFactory(gridViewCell -> {
            UsuarioCell c = new UsuarioCell();

            c.setOnDoubleClick(item -> {
                if (item.getId() == -1) {
                    this.viewModel.unSelect();
                } else {
                    this.viewModel.setSelected(item);
                }
                if (this.getRouter() != null) {
                    this.getRouter().push("usuario");
                }
            });
            c.setOnSecondaryClick((item, event) -> {
                if (item != null && item.getId() != -1) {
                    this.viewModel.setSelected(item);
                    contextMenu.show(this.grid, event.getScreenX(), event.getScreenY());
                }
            });
            return c;
        });

        grid.setCellWidth(160);  // Ancho de la celda
        grid.setCellHeight(160); // Alto de la celda
        grid.setHorizontalCellSpacing(10); // Espaciado horizontal entre celdas
        grid.setVerticalCellSpacing(10);   // Espaciado vertical entre celdas

    }

    @Override
    public void refresh() {

    }
}
