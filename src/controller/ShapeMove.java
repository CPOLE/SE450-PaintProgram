package controller;

import model.ShapeSpecs;
import model.interfaces.Shapeable;
import model.persistence.ShapeList;

public class ShapeMove implements ICommand, IUndoRedo {
    private ShapeSpecs shapeSpecs;
    private ShapeList shapeList;
    private ShapeDraw shapeDraw;
    private int right, left, top, bottom;
    private int xShift, yShift;

    public ShapeMove(ShapeSpecs shapeSpecs, ShapeList shapeList, ShapeDraw shapeDraw) {
        this.shapeSpecs = shapeSpecs;
        this.shapeList = shapeList;
        this.shapeDraw = shapeDraw;
        this.right = shapeSpecs.getEndPoint().getX();
        this.left = shapeSpecs.getStartPoint().getX();
        this.top = shapeSpecs.getEndPoint().getY();
        this.bottom = shapeSpecs.getStartPoint().getY();
    }

    public void run() {
        this.xShift = right - left;
        this.yShift = top - bottom;
        for(Shapeable shapeStrategy : shapeList.getSelectedShapeList()) {
            shapeList.removeShape(shapeStrategy);
            shapeStrategy.moveX(xShift);
            shapeStrategy.moveY(yShift);
            shapeList.addShape(shapeStrategy);
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
        CommandHistory.add(this);
    }

    public void undo() {
        for(Shapeable shape : shapeList.getSelectedShapeList()) {
            shapeList.removeShape(shape);
            shape.moveX(-xShift);
            shape.moveY(-yShift);
            shapeList.addShape(shape);
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void redo() {
        for(Shapeable shape : shapeList.getSelectedShapeList()) {
            shapeList.removeShape(shape);
            shape.moveX(xShift);
            shape.moveY(yShift);
            shapeList.addShape(shape);
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }
}
