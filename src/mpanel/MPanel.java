package mpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Evandro Murilo on 9/20/17.
 */

public class MPanel extends JPanel {
    public Dimension lblSize = new Dimension(100, 30);
    public Dimension txtSize = new Dimension(100, 30);
    public int defaultVGap = 0;
    public int defaultHGap = 0;
    public int topMargin = 0;
    public int bottomMargin = 0;
    private int maxLine = 0;
    private ArrayList<ArrayList<MComponent>> lines = new ArrayList<>();

    public MPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void add(int line, int priority, int vGap, int hGap, JLabel lbl, JTextField txt) {
        txt.setMaximumSize(txtSize);
        txt.setPreferredSize(txtSize);

        lbl.setMaximumSize(lblSize);
        lbl.setPreferredSize(lblSize);
        lbl.setAlignmentX(Box.LEFT_ALIGNMENT);

        add(line, priority, vGap, hGap, (JComponent) lbl, (JComponent) txt);
    }

    public void add(int line, int priority, int vGap, int hGap, JComponent ... components) {
        for (JComponent component : components) {
            if (line > maxLine) maxLine = line;

            while (lines.size() <= line) {
                lines.add(new ArrayList<MComponent>());
            }

            lines.get(line).add(new MComponent(component, priority, vGap, hGap));
        }
    }

    public void add(int line, int priority, JLabel lbl, JTextField txt) {
        add(line, priority, defaultVGap, defaultHGap, lbl, txt);
    }

    public void add(int line, int priority, JComponent ... components) {
        add(line, priority, defaultVGap, defaultHGap, components);
    }

    public void add(int line, JLabel lbl, JTextField txt) {
        add(line, 0, lbl, txt);
    }

    public void add(int line, JComponent ... components) {
        add(line, 0, components);
    }

    public void add(JComponent ... components) {
        add(maxLine, components);
    }

    public void setMargin(int top, int bottom) {
        this.topMargin = top;
        this.bottomMargin = bottom;
    }

    public void render() {
        this.removeAll();
        super.add(Box.createRigidArea(new Dimension(0, topMargin)));

        for (ArrayList<MComponent> line : lines) {
            Collections.sort(line);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

            int maxVGap = 0;
            for (MComponent mc : line) {
                panel.add(mc.component);
                if (mc != line.get(line.size()-1)) {
                    panel.add(Box.createRigidArea(new Dimension(mc.hGap, 0)));
                }

                if (mc.vGap > maxVGap) maxVGap = mc.vGap;
            }

            super.add(panel);
            super.add(Box.createRigidArea(new Dimension(0, maxVGap)));
        }

        super.add(Box.createRigidArea(new Dimension(0, bottomMargin)));
    }
}
