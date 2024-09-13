package umg.progra2.Formularios;

import umg.progra2.DataBase.Model.UsuarioModel;
import umg.progra2.DataBase.Model.tb_datos;
import umg.progra2.DataBase.Service.DatosService;
import umg.progra2.DataBase.Service.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datos extends JFrame {
    private JPanel Form2;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblDepartamento;
    private JLabel lblFecha;
    private JTextField textFieldID;
    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldDepartamento;
    private JTextField textFieldFecha;
    private JButton buttonAgregar;
    private JButton buttonActualizar;
    private JButton buttonBuscar;
    private JButton buttonEliminar;

    public Datos() {
        setContentPane(Form2);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buttonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los datos de los campos de texto
                    String nombre = textFieldNombre.getText();
                    String apellido = textFieldApellido.getText();
                    String departamento = textFieldDepartamento.getText();
                    String fechaTexto = textFieldFecha.getText();

                    // Formatear la fecha a java.sql.Date
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date fechaUtil = formato.parse(fechaTexto);
                    java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

                    // Crear un nuevo objeto tb_datos con los datos
                    tb_datos dato = new tb_datos(0, nombre, apellido, departamento, fechaSql);

                    // Insertar el dato en la base de datos
                    DatosService datosService = new DatosService();
                    datosService.insertarDato(dato);

                    JOptionPane.showMessageDialog(null, "Dato agregado con exitosamente 😎");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de la fecha. por favor utilice dd/MM/yyyy");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar dato: " + ex.getMessage());
                }

            }
        });

        buttonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los datos de los campos de texto
                    int codigo = Integer.parseInt(textFieldID.getText());
                    String nombre = textFieldNombre.getText();
                    String apellido = textFieldApellido.getText();
                    String departamento = textFieldDepartamento.getText();
                    String fechaTexto = textFieldFecha.getText();

                    // Formatear la fecha a java.sql.Date
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date fechaUtil = formato.parse(fechaTexto);
                    java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

                    // Crear un objeto tb_datos con los nuevos datos
                    tb_datos dato = new tb_datos(codigo, nombre, apellido, departamento, fechaSql);

                    // Actualizar el dato en la base de datos
                    DatosService datosService = new DatosService();
                    boolean actualizado = datosService.actualizarDato(dato);

                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "Dato actualizado con exito 😉");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo actualizar el dato🤨");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un numero valido. Por favor intente de nuevo");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de la fecha utilice el formato dd/MM/yyyy");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar dato: 😵‍💫 " + ex.getMessage());
                }
            }
        });
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el ID desde el campo de texto
                    int codigo = Integer.parseInt(textFieldID.getText());

                    // Buscar el dato en la base de datos
                    DatosService datosService = new DatosService();
                    tb_datos dato = datosService.obtenerPorId(codigo);

                    if (dato != null) {
                        // Rellenar los campos con los datos encontrados
                        textFieldNombre.setText(dato.getNombre());
                        textFieldApellido.setText(dato.getApellido());
                        textFieldDepartamento.setText(dato.getDepartamento());
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        textFieldFecha.setText(sdf.format(dato.getFechaNacimiento()));
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro el Dato");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un numero valido Por favor intente de nuevo");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar dato: " + ex.getMessage());
                }

            }
        });

        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String idText = textFieldID.getText();

                    if (idText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese un ID para eliminar de la Base de Datos.");
                        return;
                    }

                    int codigo = Integer.parseInt(idText);

                    // Confirmacion antes de eliminar
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "¿Está seguro de que desea eliminar el registro con ID " + codigo + "?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        DatosService tbDatosService = new DatosService();
                        boolean eliminado = tbDatosService.eliminarDato(codigo);

                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Registro eliminado con exito :)");

                            limpiarCampos();
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro. intente nuevamente");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El ID debe ser un numero valido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                }
            }

            // Método para limpiar los campos despuess de eliminar
            private void limpiarCampos() {
                textFieldID.setText("");
                textFieldNombre.setText("");
                textFieldApellido.setText("");
                textFieldDepartamento.setText("");
                textFieldFecha.setText("");
            }

        });
    }
}
