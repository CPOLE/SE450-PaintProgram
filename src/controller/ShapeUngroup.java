package controller;

import model.ShapeComposite;
import model.interfaces.IApplicationState;
import model.interfaces.Shapeable;
import model.persistence.ShapeList;

import java.util.List;

public class ShapeUngroup implements ICommand, IUndoRedo  {
    private IApplicationState appState;
    private ShapeList shapeList;
    private List<Shapeable> selectedShapeComponents;
    private List<Shapeable> undoRedoComponents;
    private ShapeComposite ungroupedShapeComponents;
    private ShapeDraw shapeDraw;

    public ShapeUngroup(IApplicationState appState, ShapeList shapeList, ShapeDraw shapeDraw) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.selectedShapeComponents = shapeList.getSelectedShapeList();
        this.shapeDraw = shapeDraw;
    }

    public void run() {
        this.ungroupedShapeComponents = new ShapeComposite(selectedShapeComponents);
        for(Shapeable selectedComponent : selectedShapeComponents) {
            shapeList.removeShape(selectedComponent);
            for(Shapeable children : selectedComponent.getChildren()) {
                ungroupedShapeComponents.removeChild(children);
                shapeList.addShape(children);
            }
        }
        this.undoRedoComponents = ungroupedShapeComponents.getChildren();
        //shapeList.clearSelectedShapeComponents();
        CommandHistory.add(this);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void undo() {
        for(Shapeable regroupedComponent : selectedShapeComponents) {
            ungroupedShapeComponents.addChild(regroupedComponent);
            shapeList.removeShape(regroupedComponent);
        }
        for(Shapeable undoneComponent : selectedShapeComponents) {
            shapeList.addShape(undoneComponent);
        }
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void redo() {
        for(Shapeable reUngroupedComponent : ungroupedShapeComponents.getChildren()) {
            shapeList.addShape(reUngroupedComponent);
        }
        shapeList.removeShape(ungroupedShapeComponents);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }
}
