package model;

import model.interfaces.Shapeable;
import view.gui.EllipseDrawStrategy;
import view.gui.RectangleDrawStrategy;
import view.gui.TriangleDrawStrategy;

public class ShapeFactory {

    public static Shapeable constructShape (ShapeSpecs shapeSpecs) {
        ShapeType shapeType = shapeSpecs.getShapeType();
        Shapeable newShape = null;

        if (shapeType == null) {
            throw new IllegalStateException("ShapeType not set!");
        }
        else if (shapeType.equals(ShapeType.RECTANGLE)) {
            newShape = new RectangleDrawStrategy(shapeSpecs);
        }
        else if (shapeType.equals(ShapeType.ELLIPSE)) {
            newShape = new EllipseDrawStrategy(shapeSpecs);
        }
        else if (shapeType.equals(ShapeType.TRIANGLE)) {
            newShape = new TriangleDrawStrategy(shapeSpecs);
        }
        return newShape;
    }
}
