package util;

import daos.DaoAlbum;
import daos.DaoCancion;
import daos.DaoUsuario;
import entidades.Album;
import entidades.Cancion;
import entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Util {

    public static List<Usuario> usuarioListCancion(Long idcacion){

        List<Usuario> usuariosFiltrados = new ArrayList<>();

        DaoAlbum daoAlbum = new DaoAlbum();
        DaoCancion daoCancion = new DaoCancion();
        DaoUsuario daoUsuario = new DaoUsuario();

        List<Usuario> usuarioList = daoUsuario.getall();
        List<Cancion> cancionList = daoCancion.getall();
        List<Album> albumList = daoAlbum.getall();

        Long idAlbum = 0L;

        for (Cancion cancion : cancionList) {
            if (Objects.equals(cancion.getId(), idcacion)) {
                    idAlbum = cancion.getId_album();
            }
        }

        for (Usuario usuario : usuarioList) {
            if (Objects.equals(usuario.getFavorito_id_album(), idAlbum)) {
                usuariosFiltrados.add(usuario);
            }
        }
    
        return usuariosFiltrados;



    }

}
