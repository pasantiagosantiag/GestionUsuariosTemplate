package ies.pedro.viewlayer;

import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.function.BiConsumer;

public abstract class AWindows implements IWindows {
    protected HashMap<String,Object> parameters;
    protected HashMap<String,Object> results;
    protected Router router;
    protected Action oninicialice;
    protected Action onclose;
    protected BiConsumer<String,Object> onaction;



    public AWindows() {
        this.parameters = new HashMap<>();
        this.results = new HashMap<>();
        this.oninicialice = null;
        this.onclose = null;

    }
    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }
    @Override
    public void addParameter(String name, Object parameter) {
        this.parameters.put(name,parameter);
    }

    @Override
    public void addParameters(HashMap<String, Object> parameters) {
    this.parameters.putAll(parameters);
    }

    @Override
    public HashMap<String, Object> getResults() {
        return this.results;
    }


    @Override
    public void setOnInizialized(Action oninizialized) {
            this.oninicialice = oninizialized;
    }

    @Override
    public void setOnClosed(Action onclosed) {
        this.onclose=onclosed;
    }

    protected void showMessage(String message,String head, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(head);
        alert.setHeaderText(null);  // Puedes establecer un encabezado aquí si lo deseas
        alert.setContentText(message);

        alert.showAndWait();
    }
    public void setOnAction(BiConsumer<String,Object> onaction){
        this.onaction=onaction;
    }

}
