package ies.pedro.datalayer;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface  IRepository<T> {
    public void add(T item);
    public void remove(T item);
    public ObservableList<T> getItems();
    public void update(T item);
}
