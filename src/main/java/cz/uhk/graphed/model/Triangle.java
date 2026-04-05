package cz.uhk.graphed.model;

import java.awt.*;

public class Triangle extends AbstractGraphicObject {
    protected int a;
    private int cx, cy;  // souradnice vrcholu C
    private int h;       // výška trojúhelníku

    public Triangle(Point position, Color color, int a) {
        super(position, color);
        this.a = a;
        computeC();
    }

    private void computeC() {
        h = (int)Math.round(a * Math.sin(Math.PI / 3));
        // Vrchol C je po úpravě nahoře
        cx = position.x + (int)Math.round(a / 2.0);
        cy = position.y;
    }

    public Triangle() {}

    @Override
    public void draw(Graphics g) {
        var g2 = (Graphics2D) g;

        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int bottomY = position.y + h;

        g2.drawLine(position.x, bottomY, position.x + a, bottomY);
        g2.drawLine(position.x, bottomY, cx, cy);
        g2.drawLine(position.x + a, bottomY, cx, cy);
    }

    @Override
    public boolean contains(Point p) {
        int bottomY = position.y + h;
        if (p.y < cy || p.y > bottomY) {
            return false;
        }
        int dx = (int)Math.round((p.y - cy) / Math.tan(Math.PI / 3));
        return p.x >= cx - dx && p.x <= cx + dx;
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        computeC();
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        cx += dx;
        cy += dy;
    }
}