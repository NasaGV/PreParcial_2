package umg.progra2.DataBase.DAO;

import umg.progra2.DataBase.Connection.ConnectionBD;
import umg.progra2.DataBase.Model.tb_datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDao {
    // Metodo para insertar un nuevo registro
    public boolean insertar(tb_datos dato) {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dato.getNombre());
            pstmt.setString(2, dato.getApellido());
            pstmt.setString(3, dato.getDepartamento());
            pstmt.setDate(4, dato.getFechaNacimiento());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para obtener todos los registros de la tabla tb_datos
    public List<tb_datos> obtenerTodos() {
        List<tb_datos> datos = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        try (Connection conn = ConnectionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tb_datos dato = new tb_datos();
                dato.setCodigo(rs.getInt("codigo"));
                dato.setNombre(rs.getString("nombre"));
                dato.setApellido(rs.getString("apellido"));
                dato.setDepartamento(rs.getString("departamento"));
                dato.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                datos.add(dato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    // Metodo para obtener un registro por su ID
    public tb_datos obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new tb_datos(
                            rs.getInt("codigo"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("departamento"),
                            rs.getDate("fecha_nacimiento")
                    );
                }
            }
        }
        return null;
    }

    // Metodo para actualizar un registro existente
    public boolean actualizar(tb_datos dato) {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dato.getNombre());
            pstmt.setString(2, dato.getApellido());
            pstmt.setString(3, dato.getDepartamento());
            pstmt.setDate(4, dato.getFechaNacimiento());
            pstmt.setInt(5, dato.getCodigo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para eliminar un registro por su ID
    public boolean eliminar(int codigo) {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        try (Connection conn = ConnectionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
