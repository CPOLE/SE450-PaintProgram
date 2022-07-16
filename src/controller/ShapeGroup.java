package controller;

import model.ShapeComposite;
import model.ShapeCompositeBuilder;
import model.interfaces.IApplicationState;
import model.interfaces.Shapeable;
import model.persistence.ShapeList;
import java.util.List;

public class ShapeGroup implements ICommand, IUndoRedo {
    private IApplicationState appState;
    private ShapeList shapeList;
    private List<Shapeable> selectedShapeComponents;
    private ShapeComposite groupedShapes;
    private ShapeCompositeBuilder groupBuilder = new ShapeCompositeBuilder();
    private ShapeDraw shapeDraw;

    public ShapeGroup(IApplicationState appState, ShapeList shapeList, ShapeDraw shapeDraw) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.selectedShapeComponents = shapeList.getSelectedShapeList();
        this.shapeDraw = shapeDraw;
    }

    public void run() {
        if(!(selectedShapeComponents.isEmpty())) {
            for(Shapeable selectedComponent : selectedShapeComponents) {
                groupBuilder.addComponent(selectedComponent);
                shapeList.removeShape(selectedComponent);
            }
            shapeList.clearSelectedShapeList();
            shapeList.addShape(groupBuilder.build());
        }
        CommandHistory.add(this);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void undo() {
        for(Shapeable groupedComponent : selectedShapeComponents) {
            shapeList.addShape(groupedComponent);
            groupedShapes.removeChild(groupedComponent);
        }
        this.groupedShapes = groupBuilder.build();
        shapeList.removeShape(groupedShapes);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
    }

    public void redo() {
        for(Shapeable groupedComponent : selectedShapeComponents) {
            groupBuilder.addComponent(groupedComponent);
            shapeList.removeShape(groupedComponent);
        }
        this.groupedShapes = groupBuilder.build();
        shapeList.addShape(groupedShapes);
        //  Re-draw canvas
        shapeDraw.drawAllShapes(shapeList);
        //shapeList.clearSelectedShapeList();
    }
}
