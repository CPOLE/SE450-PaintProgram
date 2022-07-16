package controller;

import model.interfaces.Shapeable;
import model.persistence.ShapeList;

public class ShapeCopy {
    private ShapeList shapeList;

    public ShapeCopy(ShapeList shapeList) { this.shapeList = shapeList; }

    public void run() {
        //shapeList.clearCopiedShapeList();
        for (Shapeable selectedShape : shapeList.getSelectedShapeList()) {
            shapeList.addCopiedShape(selectedShape);
        }
        shapeList.clearSelectedShapeList();
    }
}
