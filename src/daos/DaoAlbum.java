package daos;

import conexionBD.ConexionBD;
import entidades.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoAlbum implements Dao<Album> {
    @Override
    public List<Album> getall() {

        List<Album> lista = new ArrayList<>();

        String query = "SELECT * FROM album";

        try(Connection con = ConexionBD.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Album album = createAlbum(rs);
                lista.add(album);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;


    }

    @Override
    public Album getById(long id) {
        String query = "SELECT * FROM album WHERE id = ?";
        Album album = null;
        ResultSet rs = null;
        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                album = createAlbum(rs);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return album;
    }

    @Override
    public void insert(Album album) {

        String query = "INSERT INTO album (id, titulo, artista, genero, año) VALUES (?,?,?,?,?)";

        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1, album.getId());
            ps.setString(2, album.getTitulo());
            ps.setString(3, album.getArtista());
            ps.setString(4, album.getGenero());
            ps.setInt(5,album.getAno());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(long id) {

        String query = "DELETE FROM album WHERE id = ?";

        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Album album) {
        String query =  "UPDATE album SET titulo=?, " +
                "artista = ?, genero = ?, año = ? WHERE id = ? ";


        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, album.getTitulo());
            ps.setString(2, album.getArtista());
            ps.setString(3, album.getGenero());
            ps.setInt(4, album.getAno());
            ps.setLong(5, album.getId());
            ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public List<Album> gelallOfGenere(String genere) {
        List<Album> lista = new ArrayList<>();
        String query = "SELECT * FROM album WHERE genero = ?";
        Album album = null;
        ResultSet rs = null;
        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, genere);

            rs = ps.executeQuery();
            while (rs.next()) {
                album = createAlbum(rs);
                lista.add(album);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return lista;

    }



    private Album createAlbum(ResultSet rs) throws SQLException {

        return new Album(rs.getLong("id"),
                rs.getString("titulo"),
                rs.getString("artista"),
                rs.getString("genero"),
                rs.getInt("año"));
    }



}
