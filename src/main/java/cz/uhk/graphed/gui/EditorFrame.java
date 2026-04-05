package cz.uhk.graphed.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.*;

import cz.uhk.graphed.model.*;
import cz.uhk.graphed.model.Rectangle;

public class EditorFrame extends JFrame {

    private JToolBar createToolbar() {
        JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
        Random random = new Random();

        Action actSquare = new AbstractAction("Square") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = canvas.getWidth() > 0 ? canvas.getWidth() : 800;
                int height = canvas.getHeight() > 0 ? canvas.getHeight() : 600;

                int a = random.nextInt(81) + 20;
                int x = random.nextInt(Math.max(1, width - a));
                int y = random.nextInt(Math.max(1, height - a));

                Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

                canvas.add(new Square(new Point(x, y), color, a));
            }
        };
        Action actRectangle = new AbstractAction("Rectangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cw = canvas.getWidth() > 0 ? canvas.getWidth() : 800;
                int ch = canvas.getHeight() > 0 ? canvas.getHeight() : 600;

                int width = random.nextInt(101) + 20;
                int height = random.nextInt(101) + 20;
                int x = random.nextInt(Math.max(1, cw - width));
                int y = random.nextInt(Math.max(1, ch - height));

                Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

                canvas.add(new Rectangle(new Point(x, y), color, width, height));
            }
        };
        Action actCircle = new AbstractAction("Circle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cw = canvas.getWidth() > 0 ? canvas.getWidth() : 800;
                int ch = canvas.getHeight() > 0 ? canvas.getHeight() : 600;

                int r = random.nextInt(41) + 10;
                int d = 2 * r;
                int x = random.nextInt(Math.max(1, cw - d));
                int y = random.nextInt(Math.max(1, ch - d));

                Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

                canvas.add(new Circle(new Point(x, y), color, r));
            }
        };
        Action actTriangle = new AbstractAction("Triangle") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cw = canvas.getWidth() > 0 ? canvas.getWidth() : 800;
                int ch = canvas.getHeight() > 0 ? canvas.getHeight() : 600;

                int a = random.nextInt(81) + 20;
                int height = (int) Math.round(a * Math.sin(Math.PI / 3));

                int x = random.nextInt(Math.max(1, cw - a));
                int y = random.nextInt(Math.max(1, ch - height));

                Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

                canvas.add(new Triangle(new Point(x, y), color, a));
            }
        };

        actTriangle.putValue(Action.SHORT_DESCRIPTION, "Namaluje se trojúhelník");
        actCircle.putValue(Action.SHORT_DESCRIPTION, "Namaluje se kruh");
        actRectangle.putValue(Action.SHORT_DESCRIPTION, "Namaluje se obdelník");
        actSquare.putValue(Action.SHORT_DESCRIPTION, "Namaluje ctverec");
        tb.add(actSquare);
        tb.add(actRectangle);
        tb.add(actCircle);
        tb.add(actTriangle);

        JButton btCircle = new JButton("Bring circle");
        btCircle.addActionListener(e -> canvas.add(
                new Circle(new Point(0, 0), Color.BLACK, 50)
        ));
        return tb;
    }

    private Canvas canvas = new Canvas();

    public EditorFrame() throws HeadlessException {
        super("FIM Graphic Editor");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(canvas, BorderLayout.CENTER);

        add(createToolbar(), BorderLayout.NORTH);

        initSampleData();

        pack();
    }

    private void initSampleData() {
        canvas.add(new Square(new Point(100,100), Color.BLACK, 50));
        canvas.add(new Circle(new Point(100,200), Color.BLUE, 50));
        canvas.add(new Triangle(new Point(200,100), Color.RED, 50));
        canvas.add(new Rectangle(new Point(500,500), new Color(0x20c020), 90, 60));

        GraphicGroup group = new GraphicGroup();
        canvas.add(group);
        group.add(new Square(new Point(200, 200), Color.RED, 100));
        group.add(new Circle(new Point(200, 200), Color.RED, 100));
    }
}
