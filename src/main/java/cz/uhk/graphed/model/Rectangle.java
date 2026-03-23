package cz.uhk.graphed.model;

import java.awt.*;

public class Rectangle extends AbstractGraphicObject{
    protected int width, height;

    public Rectangle(Point position, Color color, int width, int height) {
        super(position, color);
        this.width = width;
        this.height = height;
    }

    public Rectangle() {}

    @Override
    public void draw(Graphics g) {
        var g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.drawRect(position.x, position.y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        return (p.x >= position.x && p.x <= position.x + width)
                && (p.y >= position.y && p.y <= position.y + height);

    }
}
