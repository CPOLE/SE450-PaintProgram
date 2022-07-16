package view.gui;

import model.*;
import java.awt.*;

public class RectangleDrawStrategy extends Drawable {

    public RectangleDrawStrategy(ShapeSpecs shapeSpecs) {
        super(shapeSpecs);
    }

    @Override
    public void drawOutlinedShape() {
        graphics2D.setColor(colorMap.get(secondaryColor));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRect(modStart.getX(), modStart.getY(), width, height);
    }

    @Override
    public void drawOutlineAndFilledInShape() {
        graphics2D.setColor(colorMap.get(primaryColor));
        graphics2D.fillRect(modStart.getX(), modStart.getY(), width, height);
        graphics2D.setColor(colorMap.get(secondaryColor));
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRect(modStart.getX(), modStart.getY(), width, height);
    }

    @Override
    public void drawFilledInShape() {
        graphics2D.setColor(colorMap.get(primaryColor));
        graphics2D.fillRect(modStart.getX(), modStart.getY(), width, height);
    }
}
