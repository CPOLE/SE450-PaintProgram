package view.gui;

import model.*;

import java.awt.*;

public class TriangleDrawStrategy extends Drawable {
    protected int[] x = new int[3];
    protected int[] y = new int[3];

    public TriangleDrawStrategy(ShapeSpecs shapeSpecs) {
        super(shapeSpecs);
        x[0] = this.modStart.getX(); y[0] = this.modEnd.getY();
        x[1] = this.modEnd.getX(); y[1] = this.modEnd.getY();
        x[2] = this.modEnd.getX(); y[2] = this.modStart.getY();
    }

    @Override
    public void drawOutlinedShape() {
        graphics2D.setColor(colorMap.get(secondaryColor));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawPolygon(x, y, 3);
    }

    @Override
    public void drawOutlineAndFilledInShape() {
        graphics2D.setColor(colorMap.get(primaryColor));
        graphics2D.fillPolygon(x, y, 3);
        graphics2D.setColor(colorMap.get(secondaryColor));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawPolygon(x, y, 3);
    }

    @Override
    public void drawFilledInShape() {
        graphics2D.setColor(colorMap.get(primaryColor));
        graphics2D.fillPolygon(x, y, 3);
    }

    @Override
    public void moveX(int shiftX) {
        this.modStart.setX((modStart.getX() + shiftX));
        this.modEnd.setX((modEnd.getX() + shiftX));
        //
        x[0] = this.modStart.getX(); y[0] = this.modEnd.getY();
        x[1] = this.modEnd.getX(); y[1] = this.modEnd.getY();
        x[2] = this.modEnd.getX(); y[2] = this.modStart.getY();
    }

    @Override
    public void moveY(int shiftY) {
        this.modStart.setY((modStart.getY() + shiftY));
        this.modEnd.setY((modEnd.getY() + shiftY));
        //
        x[0] = this.modStart.getX(); y[0] = this.modEnd.getY();
        x[1] = this.modEnd.getX(); y[1] = this.modEnd.getY();
        x[2] = this.modEnd.getX(); y[2] = this.modStart.getY();
    }
}
