package cz.uhk.graphed.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cz.uhk.graphed.model.AbstractGraphicObject;

public class Canvas extends JPanel {
    private List<AbstractGraphicObject> graphicObjects = new ArrayList<>();
    private AbstractGraphicObject selectedObject;
    private int dx;
    private int dy;

    public Canvas() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedObject = findObjectContaining(e.getPoint());
                if (selectedObject != null) {
                    dx = e.getX() - selectedObject.getPosition().x;
                    dy = e.getY() - selectedObject.getPosition().y;
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedObject != null) {
                    var deltaX = e.getX() - selectedObject.getPosition().x - dx;
                    var deltaY = e.getY() - selectedObject.getPosition().y - dy;
                    selectedObject.move(deltaX, deltaY);
                    repaint();
                }
            }
        });
    }

    private AbstractGraphicObject findObjectContaining(Point point) {
        AbstractGraphicObject result = null;
        for (var object : graphicObjects) {
            if(object.contains(point)) {
                result = object;
            }
        }
        return result;
    }


    public void add(AbstractGraphicObject object) {
        graphicObjects.add(object);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (var o : graphicObjects) {
            o.draw(g);
        }
    }
}
