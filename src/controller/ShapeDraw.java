package controller;

import model.Drawable;
import model.interfaces.Shapeable;
import model.persistence.ShapeList;
import java.awt.Color;
import java.awt.Graphics2D;

public class ShapeDraw {
    private Graphics2D graphics2D;

    public ShapeDraw(Graphics2D graphics2D) { this.graphics2D = graphics2D; }

    public void drawAllShapes(ShapeList shapeList) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 10000, 10000);
        for (Shapeable shapeable : shapeList.getShapeList()) {
            shapeable.draw();
            if(shapeList.getSelectedShapeList().contains(shapeable)) {
                shapeable.drawOutline();
            }
        }
    }
}
