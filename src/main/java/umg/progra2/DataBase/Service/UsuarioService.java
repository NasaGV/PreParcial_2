package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.Connection.ConnectionBD;
import umg.progra2.DataBase.Connection.TransactionManager;
import umg.progra2.DataBase.DAO.UsuarioDao;
import umg.progra2.DataBase.Model.UsuarioModel;

import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioService {
    private UsuarioDao userDao = new UsuarioDao();

    public boolean eliminarUser(int id) throws SQLException {
        return userDao.deleteUserById(id);
    }

    public boolean checkEmailDuplicated(String email) {
        return userDao.isEmailDuplicated(email);
    }

    public void createUser(UsuarioModel user) throws SQLException {
        try (Connection connection = ConnectionBD.getConnection()) {
            TransactionManager tm = new TransactionManager(connection);
            tm.beginTransaction();
            try {
                userDao.insertUser(user);
                tm.commit();
            } catch (SQLException e) {
                tm.rollback();
                throw e;
            }
        }
    }

    public boolean actualizarUser(UsuarioModel user) throws SQLException {
        return userDao.updateUser(user);
    }

    public UsuarioModel getUserByCarne(String carne) throws SQLException {
        return userDao.getUserByCarne(carne);
    }
}
