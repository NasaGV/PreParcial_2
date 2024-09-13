package umg.progra2.DataBase.Service;

import umg.progra2.DataBase.DAO.ChampionsDao;
import umg.progra2.DataBase.Model.ChampionsModel;

import java.sql.SQLException;
import java.util.List;

public class ChampionsService {
    private ChampionsDao championsDao = new ChampionsDao();

    public boolean insertarEquipo(ChampionsModel equipo) throws SQLException {
        return championsDao.insertar(equipo);
    }

    public boolean actualizarEquipo(ChampionsModel equipo) throws SQLException {
        return championsDao.actualizar(equipo);
    }

    public boolean eliminarEquipo(int idEquipo) throws SQLException {
        return championsDao.eliminar(idEquipo);
    }

    public List<ChampionsModel> obtenerTodosLosEquipos() throws SQLException {
        return championsDao.obtenerTodos();
    }
}
