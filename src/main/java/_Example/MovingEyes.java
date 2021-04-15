package _Example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MovingEyes implements Runnable {

    private static final int drawingWidth = 400;
    private static final int drawingHeight = 400;
    private static final int eyeballHeight = 150;
    private static final int eyeballWidthMargin = 125;

    private DrawingPanel drawingPanel;

    private Eye[] eyes;

    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MovingEyes());
    }

    public MovingEyes() {
        this.eyes = new Eye[2];
        this.eyes[0] = new Eye(new Point(eyeballWidthMargin, eyeballHeight));
        this.eyes[1] = new Eye(new Point(drawingWidth - eyeballWidthMargin,
                eyeballHeight));
    }

    @Override
    public void run() {
        frame = new JFrame("Moving Eyes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new DrawingPanel(drawingWidth, drawingHeight);
        frame.add(drawingPanel);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public class DrawingPanel extends JPanel {

        private static final long serialVersionUID = -2977860217912678180L;

        private static final int eyeballOuterRadius = 50;
        private static final int eyeballInnerRadius = 20;

        public DrawingPanel(int width, int height) {
            this.addMouseMotionListener(new EyeballListener(this,
                    eyeballOuterRadius - eyeballInnerRadius - 5));
            this.setBackground(Color.WHITE);
            this.setPreferredSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);

            for (Eye eye : eyes) {
                drawCircle(g, eye.getOrigin(), eyeballOuterRadius);
                fillCircle(g, eye.getEyeballOrigin(), eyeballInnerRadius);
            }
        }

        private void drawCircle(Graphics g, Point origin, int radius) {
            g.drawOval(origin.x - radius, origin.y - radius, radius + radius,
                    radius + radius);
        }

        private void fillCircle(Graphics g, Point origin, int radius) {
            g.fillOval(origin.x - radius, origin.y - radius, radius + radius,
                    radius + radius);
        }

    }

    public class Eye {
        private final Point origin;
        private Point eyeballOrigin;

        public Eye(Point origin) {
            this.origin = origin;
            this.eyeballOrigin = origin;
        }

        public Point getEyeballOrigin() {
            return eyeballOrigin;
        }

        public void setEyeballOrigin(Point eyeballOrigin) {
            this.eyeballOrigin = eyeballOrigin;
        }

        public Point getOrigin() {
            return origin;
        }

    }

    public class EyeballListener extends MouseMotionAdapter {

        private final double eyeballDistance;

        private final DrawingPanel drawingPanel;

        public EyeballListener(DrawingPanel drawingPanel, double eyeballDistance) {
            this.drawingPanel = drawingPanel;
            this.eyeballDistance = eyeballDistance;
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            Point p = event.getPoint();
            for (Eye eye : eyes) {
                Point origin = eye.getOrigin();
                double theta = Math.atan2((double) (p.y - origin.y),
                        (double) (p.x - origin.x));
                int x = (int) Math.round(Math.cos(theta) * eyeballDistance)
                        + origin.x;
                int y = (int) Math.round(Math.sin(theta) * eyeballDistance)
                        + origin.y;
                eye.setEyeballOrigin(new Point(x, y));
            }

            drawingPanel.repaint();
        }

    }

}
