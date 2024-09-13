package umg.progra2.DataBase.DAO;

import umg.progra2.DataBase.Connection.ConnectionBD;
import umg.progra2.DataBase.Model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    public boolean deleteUserById(int id) throws SQLException {
        String query = "DELETE FROM tb_usuarios WHERE id = ?";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean updateUser(UsuarioModel user) throws SQLException {
        String query = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE id = ?";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setString(6, user.getActivo());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean isEmailDuplicated(String email) {
        String query = "SELECT COUNT(*) FROM tb_usuarios WHERE correo = ?";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Devuelve true si el correo ya existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertUser(UsuarioModel user) throws SQLException {
        String query = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getCarne());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getCorreo());
            statement.setString(4, user.getSeccion());
            statement.setLong(5, user.getTelegramid());
            statement.setString(6, user.getActivo());
            statement.executeUpdate();
        }
    }

    public UsuarioModel getUserByCarne(String carne) throws SQLException {
        String query = "SELECT * FROM tb_usuarios WHERE carne = ?";
        try (Connection connection = ConnectionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, carne);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new UsuarioModel(
                            resultSet.getInt("id"),
                            resultSet.getString("carne"),
                            resultSet.getString("nombre"),
                            resultSet.getString("correo"),
                            resultSet.getString("seccion"),
                            resultSet.getLong("telegramid"),
                            resultSet.getString("activo")
                    );
                }
            }
        }
        return null;
    }
}
