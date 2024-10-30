package ies.pedro.datalayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class ARepository<T>  implements IRepository<T>{
    protected ObservableList<T> data;
    public ARepository() {
        data= FXCollections.<T>observableArrayList();
    }
    public ObservableList<T> getData() {
        return data;
    }
    public void setData(ObservableList<T> data) {
        this.data.clear();
        this.data.addAll(data);
    }
    public ObservableList<T> getItems(){
        return this.data;
    }
}
