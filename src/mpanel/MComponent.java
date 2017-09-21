package mpanel;

import javax.swing.*;

/**
 * Created by Evandro Murilo on 9/20/17.
 */

public class MComponent implements Comparable<MComponent> {
    JComponent component = null;
    int priority = 0;
    int vGap = 0;
    int hGap = 0;

    public MComponent(JComponent component, int priority, int vGap, int hGap) {
        this.component = component;
        this.priority = priority;
        this.vGap = vGap;
        this.hGap = hGap;
    }

    @Override
    public int compareTo(MComponent mComponent) {
        return this.priority - mComponent.priority;
    }
}
