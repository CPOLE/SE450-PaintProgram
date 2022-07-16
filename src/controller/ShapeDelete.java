package controller;

import model.interfaces.Shapeable;
import model.persistence.ShapeList;

public class ShapeDelete implements ICommand, IUndoRedo {
    private ShapeList shapeList;
    private ShapeDraw shapeDraw;

    public ShapeDelete(ShapeList shapeList, ShapeDraw shapeDraw) {
        this.shapeList = shapeList;
        this.shapeDraw = shapeDraw;
    }

    public void run() {
        for (Shapeable selectedShape : shapeList.getSelectedShapeList()) {
            shapeList.removeShape(selectedShape);
        }
        //shapeList.clearSelectedShapeList();
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
        CommandHistory.add(this);
    }

    public void undo() {
        for (Shapeable selectedShape : shapeList.getSelectedShapeList()) {
            shapeList.addShape(selectedShape);
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void redo() {
        for (Shapeable selectedShape : shapeList.getSelectedShapeList()) {
            shapeList.removeShape(selectedShape);
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }
}
