package controller;

import model.interfaces.Shapeable;
import model.persistence.ShapeList;

import java.util.ArrayList;
import java.util.Iterator;

public class ShapePaste implements ICommand, IUndoRedo {
    private ShapeList shapeList;
    private ShapeDraw shapeDraw;
    private ArrayList<Shapeable> pastedShapeList;

    public ShapePaste(ShapeList shapeList, ShapeDraw shapeDraw) {
        this.shapeList = shapeList;
        this.shapeDraw = shapeDraw;
        this.pastedShapeList = new ArrayList<>();
    }

    public void run() {
        try {
            for(Shapeable copiedShape : shapeList.getCopiedShapeList()) {
                Shapeable pastedShape = (Shapeable) copiedShape.clone();
                pastedShape.moveX(100);
                pastedShape.moveY(100);
                shapeList.addShape(pastedShape);
                pastedShapeList.add(pastedShape);
            }
            shapeList.clearSelectedShapeList();
            shapeList.clearCopiedShapeList();
            //  Re-draw canvas
            shapeDraw.drawAllShapes(shapeList);
            CommandHistory.add(this);
        } catch (CloneNotSupportedException e) { e.printStackTrace(); }
    }

    public void undo() {
        Iterator<Shapeable> pasted = pastedShapeList.iterator();
        while (pasted.hasNext()) {
            shapeList.removeShape(pasted.next());
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void redo() {
        Iterator<Shapeable> pasted = pastedShapeList.iterator();
        while (pasted.hasNext()) {
            shapeList.addShape(pasted.next());
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }
}

