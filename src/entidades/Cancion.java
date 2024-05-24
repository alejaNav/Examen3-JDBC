package entidades;

public class Cancion {

    private Long id;
    private String titulo;
    private int duracion;
    private Long id_album;


    public Cancion() {
    }

    public Cancion(Long id, String titulo, int duracion, Long id_album) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.id_album = id_album;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Long getId_album() {
        return id_album;
    }

    public void setId_album(Long id_album) {
        this.id_album = id_album;
    }


    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", id_album=" + id_album +
                '}';
    }
}
