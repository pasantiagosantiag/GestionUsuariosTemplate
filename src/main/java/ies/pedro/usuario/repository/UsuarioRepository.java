package ies.pedro.usuario.repository;

import ies.pedro.datalayer.ARepository;

import ies.pedro.usuario.model.Usuario;


public class UsuarioRepository extends ARepository<Usuario> {
    private static int counter=0;
    public UsuarioRepository() {
        super();
        this.reload();
         }



    @Override
    public void add(Usuario item) {

            this.data.add(item);
            //simula la clave primaria
            item.setId(counter);
            counter++;
    }

    @Override
    public void remove(Usuario item) {
            this.data.remove(item);
    }



    @Override
    public void update(Usuario item) {

    }
    public void reload(){
        //el primero que aparece para añadir
        this.data.clear();
        this.data.add(new Usuario());

    }
    //se hace para que en las actualizaciones se repinte
    //o "avise" a quien esté escuchando
    public void refresh(){
        Usuario usuario = new Usuario();
        this.data.add(usuario);
        this.data.remove(usuario);
    }
}
