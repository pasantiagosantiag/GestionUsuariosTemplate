package ies.pedro.usuario.view;


import ies.pedro.usuario.viewmodel.UsuarioViewModel;
import ies.pedro.viewlayer.AWindows;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.validation.Constraint;
import io.github.palexdev.materialfx.validation.Severity;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.github.palexdev.materialfx.utils.StringUtils.containsAny;

public class FormularioUsuarioView extends AWindows {
    @FXML
    private Label titulo;
    @FXML
    private MFXTextField usuario;
    @FXML
    private MFXTextField correo;
    private static final PseudoClass INVALID_PSEUDO_CLASS = PseudoClass.getPseudoClass("invalid");
    private static final String[] specialCharacters = "! @ # & ( ) – [ { } ]: ; ' , ? / * ~ $ ^ + = < > -".split(" ");

    @FXML
    private MFXPasswordField password;
    @FXML
    private MFXPasswordField password2;
    @FXML
    private MFXToggleButton activo;

    @FXML
    private MFXButton aceptar;
    @FXML
    private MFXButton cancelar;
    @FXML
    private Label validationUsuario;
    @FXML
    private Label validationCorreo;
    @FXML
    private Label validationClave;
    @FXML
    private Label validationContrasenya;
    @FXML
    private Label validationContrasenya2;



    private UsuarioViewModel viewModel;

    public FormularioUsuarioView() {



    }

    public void setViewModel(UsuarioViewModel viewModel) {
        this.viewModel = viewModel;
        //enlazado
        //el texto
        this.titulo.textProperty().bind(Bindings.when(this.viewModel.getSelected().idProperty().isEqualTo(-1)).then("Alta Usuario").otherwise(
                Bindings.concat("Modificar usuario:",this.viewModel.getSelected().nombreProperty())
        ));
        //el nombre
        this.usuario.textProperty().bindBidirectional(this.viewModel.getSelected().nombreProperty());
        //clave
        this.password.textProperty().bindBidirectional(this.viewModel.getSelected().claveProperty());
        //fichero
        this.correo.textProperty().bindBidirectional(this.viewModel.getSelected().correoProperty());
        //activo
        this.activo.selectedProperty().bindBidirectional(this.viewModel.getSelected().activoProperty());

    }

    private void configField(MFXTextField field, Label label, ArrayList<Pair<String, BooleanBinding>> conditions) {
        conditions.forEach(stringBooleanBindingPair -> field.getValidator().constraint(
                Constraint.Builder.build()
                        .setSeverity(Severity.ERROR)
                        .setMessage(stringBooleanBindingPair.getKey())
                        .setCondition(
                                stringBooleanBindingPair.getValue()
                        )
                        .get()
        ));
        field.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                label.setVisible(false);
                field.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
            }
        });

        field.delegateFocusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                List<Constraint> constraints = field.validate();
                if (!constraints.isEmpty()) {
                    field.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, true);
                    label.setText(constraints.getFirst().getMessage());
                    label.setVisible(true);
                }
            }
        });
    }

    @FXML
    public void initialize() {
        //el texto


        //se configuran los botones con las condiciones que ha de tener
        //usuario
        ArrayList<Pair<String, BooleanBinding>> conditions = new ArrayList<>();
        conditions.add(new Pair<>("Longitud mínima 8 caracteres", usuario.textProperty().length().greaterThanOrEqualTo(8)));
        BooleanBinding nospecial = Bindings.createBooleanBinding(() -> !containsAny(usuario.getText(), "", specialCharacters), usuario.textProperty());
        conditions.add(new Pair<>("No se permiten caracteres especiales", nospecial));
        this.configField(usuario, validationUsuario, conditions);

        //correo
        conditions = new ArrayList<>();
        conditions.add(new Pair<>("Longitud mínima 8 caracteres", correo.textProperty().length().greaterThanOrEqualTo(8)));
        nospecial = Bindings.createBooleanBinding(() -> !containsAny(correo.getText(), "", specialCharacters), correo.textProperty());
        conditions.add(new Pair<>("No se permiten caracteres especiales", nospecial));
        this.configField(correo, validationCorreo, conditions);

        //contraseñya
        conditions = new ArrayList<>();
        conditions.add(new Pair<>("Longitud mínima 8 caracteres", password.textProperty().length().greaterThanOrEqualTo(8)));
        nospecial = Bindings.createBooleanBinding(() -> !containsAny(password.getText(), "", specialCharacters), password.textProperty());
        conditions.add(new Pair<>("No se permiten caracteres especiales", nospecial));
        this.configField(password, validationContrasenya, conditions);

        //contraseñya2
        conditions = new ArrayList<>();
        conditions.add(new Pair<>("Longitud mínima 8 caracteres", password2.textProperty().length().greaterThanOrEqualTo(3)));
        conditions.add(new Pair<>("No coinciden las contraseñas", password2.textProperty().isEqualTo(password.textProperty())));
        this.configField(password2, validationContrasenya2, conditions);

        //solo se activa si todos los campos son correctos
        aceptar.disableProperty().bind(this.usuario.getValidator().validProperty().not().or(
                this.correo.getValidator().validProperty().not()
        ).or(this.password.getValidator().validProperty().not().or(this.password2.getValidator().validProperty().not())));


        aceptar.setOnMouseClicked(event -> {
            //se actualizan los datos
            this.viewModel.getSelected().update();
            if (this.viewModel.getSelected().idProperty().get() == -1) {
                //se actualizan los datos
                this.viewModel.getSelected().update();
                this.viewModel.add(this.viewModel.getSelected().get());
            } else {
                //se actualizan los datos
                this.viewModel.getSelected().update();
                //se almacenan
                this.viewModel.update(this.viewModel.getSelected().get());
            }
            this.password2.setText("");
            if (this.router != null)
                this.getRouter().pop();

        });

        cancelar.setOnMouseClicked(event -> {
            this.password2.setText("");
            this.getRouter().pop();

        });

    }


    @Override
    public void refresh() {

    }
}
