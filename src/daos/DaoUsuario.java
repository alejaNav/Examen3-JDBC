package daos;

import conexionBD.ConexionBD;
import entidades.Cancion;
import entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario implements Dao<Usuario> {
    @Override
    public List<Usuario> getall() {
        List<Usuario> lista = new ArrayList<>();

        String query = "SELECT * FROM usuario";

        try(Connection con = ConexionBD.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Usuario usuario = createUsuario(rs);
                lista.add(usuario);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Usuario getById(long id) {
        String query = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;
        ResultSet rs = null;
        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = createUsuario(rs);

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

        return usuario;
    }

    @Override
    public void insert(Usuario usuario) {
        String query = "INSERT INTO usuario (id, nombre, email, favorito_id_album) VALUES (?,?,?,?)";

        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getEmail());
            ps.setLong(4, usuario.getFavorito_id_album());
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM usuario WHERE id = ?";

        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Usuario usuario) {

        String query =  "UPDATE usuario SET nombre=?, " +
                "email = ?, favorito_id_album = ? WHERE id = ? ";


        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setLong(3, usuario.getFavorito_id_album());
            ps.setLong(4, usuario.getId());
            ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Usuario> usariosCancionFavorita(Long idcancion){
        List<Usuario> lista = new ArrayList<>();
        String query = "SELECT *\n" +
                "FROM usuario\n" +
                "JOIN tiendamusica.album a on usuario.favorito_id_album = a.id\n" +
                "JOIN tiendamusica.cancion c on a.id = c.id_album\n" +
                "WHERE c.id_album = ?";
        ResultSet rs = null;

        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, idcancion);

            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = createUsuario(rs);
                lista.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return lista;

    }



    private Usuario createUsuario(ResultSet rs) throws SQLException {

        return new Usuario(rs.getLong("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getLong("favorito_id_album"));
    }
}
