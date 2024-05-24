package lanzador;

import conexionBD.ConexionBD;
import daos.Dao;
import daos.DaoAlbum;
import daos.DaoCancion;
import daos.DaoUsuario;
import entidades.Album;
import entidades.Cancion;
import entidades.Usuario;
import util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Lanzador {

    public static void main(String[] args)  {



        DaoAlbum daoAlbum = new DaoAlbum();
        DaoCancion daoCancion = new DaoCancion();
        DaoUsuario daoUsuario = new DaoUsuario();

        //Pruebas con DaoAlbum

//      Album album2 = new Album(1L,"PRUEBA","PRUEBA","POP",2020);
//        daoAlbum.insert(album2);
//            daoAlbum.delete(4);
//        daoAlbum.update(album2);

//        List<Album> albumList = daoAlbum.getall();
//        Album album = daoAlbum.getById(1);
//
//        List<Album> albumporGenero = daoAlbum.gelallOfGenere("ROCK");
//
//
//        System.out.println(albumList);
//        System.out.println(album);
//        System.out.println(albumporGenero);
//
//
//        System.out.println(" ");

        //Pruebas con usuario

        Usuario usuario = new Usuario(2L,"PRUEBA","PRUEBA2",2L);



//        daoUsuario.insert(usuario);
       Usuario usarioBuscado = daoUsuario.getById(2);



//        daoUsuario.update(usuario);
//        daoUsuario.delete(2L);
        List<Usuario> usuarioList = daoUsuario.getall();
        System.out.println(usuarioList);
        System.out.println(usarioBuscado);

        List<Usuario> usuarioList2 = daoUsuario.usariosCancionFavorita(2L);
        System.out.println(usuarioList2);

    //PRUEBAS CON CANCION

        Cancion cancion = new Cancion(9L,"prueba2",3322,2L);

//        daoCancion.insert(cancion);
//        daoCancion.update(cancion);
        Cancion cancion1 = daoCancion.getById(9L);
        List<Cancion> cancionList = daoCancion.getall();
        System.out.println(cancion1);
//        daoCancion.delete(9L);




        System.out.println(cancionList);



        List<Usuario> usuriosCancionesFav = Util.usuarioListCancion(2L);
        System.out.println(usuriosCancionesFav);











    }

}
