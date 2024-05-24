package entidades;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private Long favorito_id_album;

    public Usuario() {
    }


    public Usuario(Long id, String nombre, String email, Long favorito_id_album) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.favorito_id_album = favorito_id_album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getFavorito_id_album() {
        return favorito_id_album;
    }

    public void setFavorito_id_album(Long favorito_id_album) {
        this.favorito_id_album = favorito_id_album;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", favorito_id_album=" + favorito_id_album +
                '}';
    }
}
