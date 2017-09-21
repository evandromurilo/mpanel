package examples;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mpanel.MPanel;

/**
 * Created by Evandro Murilo on 9/20/17.
 */
public class Triangle extends JFrame implements ActionListener {
    JLabel lblL1 = new JLabel("Lado 1:"),
            lblL2 = new JLabel("Lado 2:"),
            lblL3 = new JLabel("Lado 3:"),
            lblVerif = new JLabel("Tipo:"),
            lblVerifR = new JLabel("?"),
            lblCalcR = new JLabel("?"),
            lblCalc = new JLabel("Área:");

    JTextField txtL1 = new JTextField(),
            txtL2 = new JTextField(),
            txtL3 = new JTextField(),
            txtVerif = new JTextField(),
            txtCalc = new JTextField();

    JButton btnCalc = new JButton("Calcular"),
            btnFechar = new JButton("Fechar");

    public Triangle() {
        setTitle("Triângulo");
        setBounds(200, 300, 300, 200);
        setResizable(false);

        txtCalc.setEditable(false);
        txtVerif.setEditable(false);

        btnFechar.addActionListener(this);
        btnCalc.addActionListener(this);

        MPanel panel = new MPanel();

        panel.defaultHGap = 15;
        panel.defaultVGap = 5;
        panel.setMargin(15, 15);

        panel.add(0, lblL1, txtL1);
        panel.add(1, lblL2, txtL2);
        panel.add(2, lblL3, txtL3);
        panel.add(3, 0, 0, 5, lblVerif);
        panel.add(3, lblVerifR);
        panel.add(3, 0, 0, 5, lblCalc);
        panel.add(3, 0, 15, 0, lblCalcR);
        panel.add(4, btnCalc);
        panel.add(4, btnFechar);

        panel.render();
        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFechar) {
            dispose();
        } else {
            try {
                double l1 = Double.parseDouble(txtL1.getText());
                double l2 = Double.parseDouble(txtL2.getText());
                double l3 = Double.parseDouble(txtL3.getText());
                double s = (l1 + l2 + l3) / 2;
                double area = Math.sqrt(s * (s - l1) * (s - l2) * (s - l3));

                lblCalcR.setText(String.format("%.2f", area));

                if (l1 == l2 && l2 == l3) {
                    lblVerifR.setText("Equilátero");
                } else if (l1 == l2 || l1 == l3 || l2 == l3) {
                    lblVerifR.setText("Isósceles");
                } else {
                    lblVerifR.setText("Escaleno");
                }
            } catch (Exception error) {
                lblCalcR.setText("Error");
                lblVerifR.setText("Error");
            }
        }
    }
}

