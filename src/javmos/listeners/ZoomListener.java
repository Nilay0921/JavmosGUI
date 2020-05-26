package javmos.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javmos.JavmosGUI;
import javmos.components.JavmosPanel;

public class ZoomListener implements ActionListener {
    
    private final int ZOOM_FACTOR = 10;
    private final JavmosGUI gui;
    private final JavmosPanel panel;

    public ZoomListener(JavmosGUI gui, JavmosPanel panel) {
        this.gui = gui;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("+")) {
            gui.setZoom(gui.getZoom() + ZOOM_FACTOR);
        } else if (gui.getZoom() - ZOOM_FACTOR > 0) {
            gui.setZoom(gui.getZoom() - ZOOM_FACTOR);
        }
        panel.repaint();
    }
}
