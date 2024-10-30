package ies.pedro.usuario.viewmodel;


import ies.pedro.usuario.model.Usuario;
import ies.pedro.usuario.repository.UsuarioRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.Optional;

public class UsuarioViewModel {
    UsuarioRepository repository;
    UsuarioProperty selected;

    public UsuarioViewModel() {
        this.selected=new UsuarioProperty();
    }


    public UsuarioRepository getRepository() {
        return repository;
    }

    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }


    public void setSelected(Usuario selected) {

        this.selected.set(selected);
    }

    public void setSelected(int index) {
    if(index>=0 && index<this.repository.getItems().size())
        this.selected.set(this.repository.getItems().get(index));
    }

    public UsuarioProperty getSelected() {
        return this.selected;
    }

    public void unSelect() {
        this.selected.set(new Usuario());
    }


    public void add(Usuario item) {
       this.repository.add(item);
        this.selected.set(item);

    }

    public void update(Usuario item) {
            this.repository.update(item);
    }

    public void remove(Usuario item) {
        this.repository.remove(item);
        this.unSelect();
    }

    public void removeSelected() {
        if (this.selected.get()!=null && this.selected.get().getId()!=-1) {
            this.remove(this.selected.get());
            this.unSelect();
        }
    }

    public Optional<Usuario> getById(int id) {
        return this.repository.getItems().stream().filter(e -> e.getId() == id).findFirst();
    }
    public void reload(){
        if(this.repository!=null)
            this.repository.reload();
    }
    public ObservableList<Usuario> getItems() {
        return this.repository.getItems();
    }

    private void refresh() {
        this.repository.refresh();
    }


}
