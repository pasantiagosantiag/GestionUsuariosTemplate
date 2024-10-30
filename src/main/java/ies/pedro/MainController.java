package ies.pedro;



import ies.pedro.usuario.repository.UsuarioRepository;
import ies.pedro.usuario.view.FormularioUsuarioView;
import ies.pedro.usuario.view.ListadoUsuariosView;
import ies.pedro.usuario.viewmodel.UsuarioViewModel;
import ies.pedro.viewlayer.Router;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final Stage stage;
    private double xOffset;
    private double yOffset;
    private final ToggleGroup toggleGroup;
    private Router router;


    private UsuarioRepository repository;
    private UsuarioViewModel viewModel;



    @FXML
    private AnchorPane rootPane;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private VBox navBar;

    @FXML
    private StackPane contentPane;

    @FXML
    private StackPane logoContainer;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

        this.router = new Router();
        this.router.setMain(contentPane);



        ScrollUtils.addSmoothScrolling(scrollPane);


        Image image = new Image(String.valueOf(getClass().getResource("/logo.png")), 16, 16, true, true);
        ImageView logo = new ImageView(image);
        Circle clip = new Circle(30);
        clip.centerXProperty().bind(logo.layoutBoundsProperty().map(Bounds::getCenterX));
        clip.centerYProperty().bind(logo.layoutBoundsProperty().map(Bounds::getCenterY));
        logo.setClip(clip);
        logoContainer.getChildren().add(logo);
        this.configUsuarios();



    }

    @FXML
    public void inicialize() throws IOException {

    }



    private void configUsuarios() {
        this.repository = new UsuarioRepository();
        this.viewModel = new UsuarioViewModel();
        this.viewModel.setRepository(this.repository);

        //se carga el de las categorias
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/usuarios/listado.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        ListadoUsuariosView lcv = loader.getController();
        //se le pasa el router, el viewmodel y se añade al enrutador
        lcv.setRouter(this.router);
        lcv.setViewModel(this.viewModel);
        this.router.add("usuarios", loader);
        //configurar el menuitem
        ToggleButton toggle = createToggle("fas-list", "Usuarios");
        //se cambia el elemento
        toggle.setOnAction(event -> {
            router.push("usuarios");
            toggle.setSelected(true);

        });
        //añadir
        navBar.getChildren().add(toggle);

        //para la edición
        loader = new FXMLLoader(getClass().getResource("/fxml/usuarios/formulario.fxml"));

        this.router.add("usuario", loader);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        FormularioUsuarioView fcv = loader.getController();

        fcv.setViewModel(this.viewModel);
        fcv.setRouter(this.router);



    }


    public MainController(Stage stage) {
        this.stage = stage;
        this.toggleGroup = new ToggleGroup();
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);
    }

    private ToggleButton createToggle(String icon, String text) {
        return createToggle(icon, text, 0);
    }

    private ToggleButton createToggle(String icon, String text, double rotate) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
        toggleNode.setToggleGroup(toggleGroup);
        if (rotate != 0) wrapper.getIcon().setRotate(rotate);
        return toggleNode;
    }
}
