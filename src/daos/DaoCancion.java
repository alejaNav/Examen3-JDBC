package daos;

import conexionBD.ConexionBD;
import entidades.Album;
import entidades.Cancion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCancion implements Dao<Cancion> {
    @Override
    public List<Cancion> getall() {
        List<Cancion> lista = new ArrayList<>();

        String query = "SELECT * FROM cancion";

        try(Connection con = ConexionBD.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Cancion cancion = createCancion(rs);
                lista.add(cancion);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public Cancion getById(long id) {
        String query = "SELECT * FROM cancion WHERE id = ?";
        Cancion cancion = null;
        ResultSet rs = null;
        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                cancion = createCancion(rs);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }

        return cancion;
    }

    @Override
    public void insert(Cancion cancion) {

        String query = "INSERT INTO cancion (id, titulo, duracion, id_album)  VALUES (?,?,?,?)";

        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1, cancion.getId());
            ps.setString(2, cancion.getTitulo());
            ps.setInt(3, cancion.getDuracion());
            ps.setLong(4,cancion.getId_album());
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(long id) {

        String query = "DELETE FROM cancion WHERE id = ?";

        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Cancion cancion) {

        String query =  "UPDATE cancion SET titulo=?, " +
                "duracion = ?, id_album = ? WHERE id = ? ";


        try(Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, cancion.getTitulo());
            ps.setInt(2, cancion.getDuracion());
            ps.setLong(3, cancion.getId_album());
            ps.setLong(4, cancion.getId());
            ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Cancion createCancion(ResultSet rs) throws SQLException {

        return  new Cancion(rs.getLong("id"),
                rs.getString("titulo"),
                rs.getInt("duracion"),
                rs.getLong("id_album"));


    }

}
