package ies.pedro.usuario.viewmodel;


import ies.pedro.usuario.model.Usuario;
import javafx.beans.property.*;

public class UsuarioProperty extends SimpleObjectProperty<Usuario>implements Cloneable{
    //private final ObjectProperty<Categoria> property = new SimpleObjectProperty<>();
    private IntegerProperty idProperty;
    private StringProperty nombreProperty;
    private StringProperty claveProperty;
    private StringProperty correoProperty;
    private BooleanProperty activoProperty;

    public UsuarioProperty() {
        this.idProperty = new SimpleIntegerProperty(-1);
        this.nombreProperty = new SimpleStringProperty("");
        this.claveProperty = new SimpleStringProperty("");
        this.activoProperty = new SimpleBooleanProperty(false);
        this.correoProperty = new SimpleStringProperty("");

    }



    public IntegerProperty idProperty() {
        return idProperty;
    }

    public String getNombreProperty() {
        return nombreProperty.get();
    }

    public StringProperty nombreProperty() {
        return nombreProperty;
    }

    public String getClaveProperty() {
        return claveProperty.get();
    }

    public StringProperty claveProperty() {
        return claveProperty;
    }

    public String getCorreoProperty() {
        return correoProperty.get();
    }

    public StringProperty correoProperty() {
        return correoProperty;
    }

    public boolean isActivoProperty() {
        return activoProperty.get();
    }

    public BooleanProperty activoProperty() {
        return activoProperty;
    }

    public void set(Usuario item) {
        //para que salte
        super.set(item);
        setProperties(item);
    }
    public void setValue(Usuario item) {
        super.setValue(item);
        setProperties(item);
    }

    private void setProperties(Usuario item) {

        if(item!=null) {
            this.idProperty.set(item.getId());
            this.nombreProperty.set(item.getNombre());
            this.claveProperty.set(item.getClave());
            this.correoProperty.set(item.getCorreo());
            this.activoProperty.set(item.isActivo());
        }
    }

    //se actualizan los valores a la propiedad
    public void update(Usuario item) {
        item.setId(this.idProperty.get());
        item.setNombre(this.nombreProperty.get());
        item.setClave(this.claveProperty.get());
        item.setCorreo(this.correoProperty.get());
        item.setActivo(this.activoProperty.get());
    }
    public void update(){
        this.get().setId(this.idProperty.get());
        this.get().setNombre(this.nombreProperty.get());
        this.get().setClave(this.claveProperty.get());
        this.get().setCorreo(this.correoProperty.get());
        this.get().setActivo(this.activoProperty.get());
    }

}