package mpanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A subclass of JPanel that acts like a layout manager.
 *
 * github.com/evandromurilo/MPanel
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

    /**
     * Add a JLabel and a JTextField to the panel (after resizing then, using lblSize and txtSize, for alignment
     * purposes).
     * @param line to what line the components will be added
     * @param priority components appear in order of lowest priority
     * @param vGap vertical gap between this and the next line
     * @param hGap horizontal gap between components
     * @param lbl label related to the JTextField
     * @param txt text field related to the JLabel
     */
    public void add(int line, int priority, int vGap, int hGap, JLabel lbl, JTextField txt) {
        txt.setMaximumSize(txtSize);
        txt.setPreferredSize(txtSize);

        lbl.setMaximumSize(lblSize);
        lbl.setPreferredSize(lblSize);
        lbl.setAlignmentX(Box.LEFT_ALIGNMENT);

        add(line, priority, vGap, hGap, (JComponent) lbl, (JComponent) txt);
    }

    /**
     * Add one or more components to the panel.
     *
     * @param line to what line the components will be added
     * @param priority components appear in order of lowest priority
     * @param vGap vertical gap between this and the next line
     * @param hGap horizontal gap between components
     * @param components list of components to be added
     */
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

    /**
     * Set the spacing before first line and after last line
     *
     * @param top spacing before first line
     * @param bottom spacing after last line
     */
    public void setMargin(int top, int bottom) {
        this.topMargin = top;
        this.bottomMargin = bottom;
    }

    /**
     * Adds the components to the panel. This method must be called after the last call to add().
     */
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
