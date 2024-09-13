package umg.progra2.Formularios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Principal extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(new Principal().JPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel JPanelPrincipal;
    private JLabel lblPreParcial;
    private JButton buttonEje1;
    private JButton buttonEje2;
    private JButton buttonEje3;
    private JFrame frame;

    public Principal() {
        buttonEje1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datos datos = new Datos();
                datos.setVisible(true);
                setVisible(false);

            }
        });
        buttonEje2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuarios = new Usuario();
                usuarios.setVisible(true);
                setVisible(false);

            }
        });
        buttonEje3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Champions champions = new Champions();
                champions.setVisible(true);
                setVisible(false);

            }
        });
    }
}
