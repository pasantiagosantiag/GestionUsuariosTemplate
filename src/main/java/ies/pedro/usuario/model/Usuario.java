package ies.pedro.usuario.model;

public class Usuario implements Cloneable, Comparable<Usuario> {
    private int id;
    private String nombre;
    private boolean activo;
    private String clave;
    private String correo;
    public Usuario(){
        this.id=-1;
        this.activo=false;
        this.clave="";
        this.correo="";
        this.nombre="";
    }
    public Usuario(int id, String nombre, boolean activo, String clave, String correo){
        this.id=id;
        this.nombre=nombre;
        this.activo=activo;
        this.clave=clave;
        this.correo=correo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Usuario clone(){
        Usuario usuario=new Usuario();
        usuario.setId(this.id);
        usuario.setNombre(this.nombre);
        usuario.setActivo(this.activo);
        usuario.setClave(this.clave);
        usuario.setCorreo(this.correo);
        return usuario;

    }
    public void copy(Usuario usuario){
        if(usuario!=null) {
            this.nombre = usuario.getNombre();
            this.activo = usuario.isActivo();
            this.clave = usuario.getClave();
            this.correo = usuario.getCorreo();
            this.id = usuario.getId();
        }
    }
    @Override
    public int compareTo(Usuario o) {
        if(o!=null)
            return this.nombre.compareTo(o.nombre);
        else
            return 1;
    }
}
