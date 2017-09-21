package examples;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Evandro Murilo on 9/20/17.
 */
public class Examples extends JFrame implements ActionListener {
    JMenuBar menu = new JMenuBar();
    JMenu mnExtras = new JMenu("Exemplos");
    JMenuItem mnItemTri = new JMenuItem("Triângulo");
    JMenuItem mnItemCom = new JMenuItem("Comissão");

    public Examples() {
        menu.add(mnExtras);
        mnExtras.add(mnItemTri);
        mnExtras.add(mnItemCom);

        mnItemTri.addActionListener(this);
        mnItemCom.addActionListener(this);

        setJMenuBar(menu);
        setTitle("Test Menu");
        setBounds(200, 300, 300, 45);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mnItemTri) {
            new Triangle();
        } else if (e.getSource() == mnItemCom) {
            new Wage();
        }
    }

    public static void main(String[] args) {
        new Examples();
    }
}

