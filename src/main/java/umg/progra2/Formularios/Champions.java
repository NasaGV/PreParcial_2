package umg.progra2.Formularios;

import umg.progra2.DataBase.Model.ChampionsModel;
import umg.progra2.DataBase.Service.ChampionsService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Champions extends JFrame {
    private JPanel Form1;
    private JTextField textFieldID;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblPais;
    private JLabel lblCiudad;
    private JLabel lblEstadio;
    private JLabel lblFundacion;
    private JLabel lblEntrenador;
    private JLabel lblWeb;
    private JLabel lblFacebook;
    private JLabel lblTwitter;
    private JLabel lblInstragram;
    private JLabel lblPatrocinador;
    private JTextField textFieldNombre;
    private JTextField textFieldPais;
    private JTextField textFieldCiudad;
    private JTextField textFieldEstadio;
    private JTextField textFieldFundacion;
    private JTextField textFieldEntrenador;
    private JTextField textFieldWeb;
    private JTextField textFieldFacebook;
    private JTextField textFieldTwitter;
    private JTextField textFieldInstragram;
    private JTextField textFieldPatrocinador;
    private JButton buttonBuscar;
    private JButton buttonAgregar;
    private JButton ButtonActualizar;
    private JButton buttonEliminar;
    private ChampionsService service = new ChampionsService();
    private ChampionsModel equipo = new ChampionsModel();

    public Champions() {
        setContentPane(Form1);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        buttonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    equipo.setNombre(textFieldNombre.getText());
                    equipo.setPais(textFieldPais.getText());
                    equipo.setCiudad(textFieldCiudad.getText());
                    equipo.setEstadio(textFieldEstadio.getText());
                    equipo.setFundacion(Integer.parseInt(textFieldFundacion.getText()));
                    equipo.setEntrenador(textFieldEntrenador.getText());
                    equipo.setWebOficial(textFieldWeb.getText());
                    equipo.setFacebook(textFieldFacebook.getText());
                    equipo.setTwitter(textFieldTwitter.getText());
                    equipo.setInstagram(textFieldInstragram.getText());
                    equipo.setPatrocinadorPrincipal(textFieldPatrocinador.getText());

                    // Call service to insert the team
                    service.insertarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "El equipo a sido agregado exitosamente ");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, " Ha habido un error en los datos numericos: " + ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el equipo: " + ex.getMessage());
                }

            }
        });

        ButtonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    equipo.setIdEquipo(Integer.parseInt(textFieldID.getText()));
                    equipo.setNombre(textFieldNombre.getText());
                    equipo.setPais(textFieldPais.getText());
                    equipo.setCiudad(textFieldCiudad.getText());
                    equipo.setEstadio(textFieldEstadio.getText());
                    equipo.setFundacion(Integer.parseInt(textFieldFundacion.getText()));
                    equipo.setEntrenador(textFieldEntrenador.getText());
                    equipo.setWebOficial(textFieldWeb.getText());
                    equipo.setFacebook(textFieldFacebook.getText());
                    equipo.setTwitter(textFieldTwitter.getText());
                    equipo.setInstagram(textFieldInstragram.getText());
                    equipo.setPatrocinadorPrincipal(textFieldPatrocinador.getText());


                    service.actualizarEquipo(equipo);
                    JOptionPane.showMessageDialog(null, "El equipo se a actualizado exitosamente😎");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ha habido un error en los datos numericos: " + ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el equipo: " + ex.getMessage());
                }

            }
        });

        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idEquipo = Integer.parseInt(textFieldID.getText());


                    service.eliminarEquipo(idEquipo);
                    JOptionPane.showMessageDialog(null, "El equipo a sido eliminado exitosamente😶‍🌫️");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el ID: " + ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ha sirgido un error al eliminar el equipo: " + ex.getMessage());
                }

            }
        });

        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBusqueda = JOptionPane.showInputDialog("Ingrese el nombre del equipo que desea buscar");
                if (nombreBusqueda != null && !nombreBusqueda.isEmpty()) {
                    try {
                        List<ChampionsModel> equipos = service.obtenerTodosLosEquipos();

                        ChampionsModel equipoEncontrado = equipos.stream()
                                .filter(eq -> eq.getNombre().toLowerCase().contains(nombreBusqueda.toLowerCase()))
                                .findFirst()
                                .orElse(null);

                        if (equipoEncontrado != null) {
                            mostrarEquipoEnFormulario(equipoEncontrado);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontro ningun equipo con ese nombre", "Equipo no a sido encontrado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al buscar el equipo: " + ex.getMessage());
                    }
                }
            }

            private void mostrarEquipoEnFormulario(ChampionsModel equipo) {
                textFieldID.setText(String.valueOf(equipo.getIdEquipo()));
                textFieldNombre.setText(equipo.getNombre());
                textFieldPais.setText(equipo.getPais());
                textFieldCiudad.setText(equipo.getCiudad());
                textFieldEstadio.setText(equipo.getEstadio());
                textFieldFundacion.setText(String.valueOf(equipo.getFundacion()));
                textFieldEntrenador.setText(equipo.getEntrenador());
                textFieldWeb.setText(equipo.getWebOficial());
                textFieldFacebook.setText(equipo.getFacebook());
                textFieldTwitter.setText(equipo.getTwitter());
                textFieldInstragram.setText(equipo.getInstagram());
                textFieldPatrocinador.setText(equipo.getPatrocinadorPrincipal());
            }


        });
    }
}
