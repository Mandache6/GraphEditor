package cz.uhk.graphed.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Seskupeni grafickych objektu
 */
public class GraphicGroup extends AbstractGraphicObject {
    List<AbstractGraphicObject> items = new ArrayList<>();

    public GraphicGroup(){
        position = new Point(0, 0);
    }

    @Override
    public void draw(Graphics g) {
        items.forEach(it -> it.draw(g));
    }

    @Override
    public boolean contains(Point p) {
        for (var it: items) {
            if (it.contains(p))
                return true;
        }
        return false;
    }

    public void add(AbstractGraphicObject obj) {
        items.add(obj);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        items.forEach(it -> it.move(dx, dy));
    }
}
