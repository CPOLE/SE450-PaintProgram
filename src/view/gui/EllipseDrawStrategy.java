package view.gui;

import model.*;
import model.interfaces.Shapeable;

import java.awt.*;

public class EllipseDrawStrategy extends Drawable {

    public EllipseDrawStrategy(ShapeSpecs shapeSpecs) {
        super(shapeSpecs);
    }

    @Override
    public void drawOutlinedShape() {
        graphics2D.setColor(colorMap.get(secondaryColor));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawOval(modStart.getX(), modStart.getY(), width, height);
    }

    @Override
    public void drawOutlineAndFilledInShape() {
        graphics2D.setColor(colorMap.get(primaryColor));
        graphics2D.fillOval(modStart.getX(), modStart.getY(), width, height);
        graphics2D.setColor(colorMap.get(secondaryColor));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawOval(modStart.getX(), modStart.getY(), width, height);
    }

    @Override
    public void drawFilledInShape() {
        graphics2D.setColor(colorMap.get(primaryColor));
        graphics2D.fillOval(modStart.getX(), modStart.getY(), width, height);
    }
}
