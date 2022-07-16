package controller;

import model.Point;
import model.ShapeSpecs;
import model.interfaces.IApplicationState;
import model.interfaces.Shapeable;
import model.persistence.ShapeList;

public class ShapeSelect implements ICommand, IUndoRedo {
    private ShapeList shapeList;
    private ShapeDraw shapeDraw;
    private int height, width;
    private Point selection;
    private ShapeSpecs shapeSpecs;

    public ShapeSelect(ShapeSpecs shapeSpecs, ShapeList shapeList, ShapeDraw shapeDraw) {
        this.shapeSpecs = shapeSpecs;
        this.selection = new Point(shapeSpecs.getModStart().getX(), shapeSpecs.getModStart().getY());
        this.shapeList = shapeList;
        this.shapeDraw = shapeDraw;
        this.height = shapeSpecs.getHeight();
        this.width = shapeSpecs.getWidth();
    }

    public void run() {
        shapeList.clearSelectedShapeList();
        for(Shapeable shape : shapeList.getShapeList()) {
            if (shape.getModStart().getX() < selection.getX() + width &&
                    shape.getModStart().getX() + shape.getWidth() > selection.getX() &&
                    shape.getModStart().getY() < selection.getY() + height &&
                    shape.getModStart().getY() + shape.getHeight() > selection.getY())
            {
                shapeList.addSelectedShape(shape);
            }
        }
        CommandHistory.add(this);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void undo() {
        shapeList.clearSelectedShapeList();
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void redo() {
        for(Shapeable shape : shapeList.getSelectedShapeList()) {
            if (shape.getModStart().getX() < selection.getX() + width &&
                    shape.getModStart().getX() + shape.getWidth() > selection.getX() &&
                    shape.getModStart().getY() < selection.getY() + height &&
                    shape.getModStart().getY() + shape.getHeight() > selection.getY())
            {
                shapeList.addSelectedShape(shape);
            }
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }
}
