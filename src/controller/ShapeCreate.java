package controller;

import model.ShapeFactory;
import model.ShapeSpecs;
import model.interfaces.Shapeable;
import model.persistence.ShapeList;

public class ShapeCreate implements ICommand, IUndoRedo {
    private ShapeSpecs shapeSpecs;
    private ShapeList shapeList;
    private Shapeable newShape;
    private ShapeDraw shapeDraw;
    //private ShapeFactory shapeFactory = new ShapeFactory();

    public ShapeCreate(ShapeSpecs shapeSpecs, ShapeList shapeList, ShapeDraw shapeDraw) {
        this.shapeSpecs = shapeSpecs;
        this.shapeList = shapeList;
        this.shapeDraw = shapeDraw;
    }

    public void run() {
        //newShape = shapeFactory.constructShape(shapeSpecs);
        newShape = ShapeFactory.constructShape(shapeSpecs);
        shapeList.addShape(newShape);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
        CommandHistory.add(this);
    }

    public void undo() {
        shapeList.removeShape(newShape);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void redo() {
        shapeList.addShape(newShape);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }
}
