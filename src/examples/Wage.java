package examples;

import mpanel.MPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Evandro Murilo on 9/20/17.
 */
public class Wage extends JFrame implements ActionListener {
    JLabel lblNome = new JLabel("Nome do funcionário: "),
            lblTotalV = new JLabel("Total de vendas: "),
            lblC = new JLabel("Comissão: "),
            lblValorC = new JLabel("Valor da comissão: "),
            lblS = new JLabel("Salário: "),
            lblSC = new JLabel("Salário + Comissão: ");
    JTextField txtNome = new JTextField(),
            txtTotalV = new JTextField(),
            txtValorC = new JTextField(),
            txtS = new JTextField(),
            txtSC = new JTextField();
    JRadioButton rb1 = new JRadioButton("1%"),
            rb2 = new JRadioButton("2%"),
            rb3 = new JRadioButton("3%"),
            rb4 = new JRadioButton("4%");
    JButton btnCalc = new JButton("Calcular");
    ButtonGroup btnGroup = new ButtonGroup();

    public Wage() {
        setTitle("Comissão");
        setBounds(200, 300, 400, 300);
        setResizable(false);

        txtSC.setEditable(false);
        txtValorC.setEditable(false);

        btnGroup.add(rb1);
        btnGroup.add(rb2);
        btnGroup.add(rb3);
        btnGroup.add(rb4);

        btnCalc.addActionListener(this);

        MPanel panel = new MPanel();
        panel.setMargin(15, 15);
        panel.defaultVGap = 5;
        panel.defaultHGap = 15;
        panel.lblSize = new Dimension(170, 30);

        panel.add(0, lblNome, txtNome);
        panel.add(1, 0, 15, 15, lblTotalV, txtTotalV);
        panel.add(2, lblC);
        panel.add(3, 0, 15, 15, rb1, rb2, rb3, rb4);
        panel.add(4, lblValorC, txtValorC);
        panel.add(5, lblS, txtS);
        panel.add(6, 0, 15, 15, lblSC, txtSC);
        panel.add(7, btnCalc);

        panel.render();
        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        double totalV, c, s, sc, p;

        try {
            totalV = Double.parseDouble(txtTotalV.getText());
            s = Double.parseDouble(txtS.getText());

            if (rb1.isSelected()) p = 0.01;
            else if (rb2.isSelected()) p = 0.02;
            else if (rb3.isSelected()) p = 0.03;
            else p = 0.04;

            c = totalV * p;
            sc = s + c;

            txtValorC.setText(c + "");
            txtSC.setText(sc + "");
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Valores em formato inválido!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
        }
    }
}

