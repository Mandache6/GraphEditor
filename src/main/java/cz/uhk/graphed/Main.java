package cz.uhk.graphed;

import javax.swing.*;

import cz.uhk.graphed.gui.EditorFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                ()-> new EditorFrame().setVisible(true)
        );
    }
}
