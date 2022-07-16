package controller;

import model.Point;
import model.ShapeSpecs;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import model.persistence.ShapeList;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private IApplicationState appState;
    private Point startPoint, endPoint;
    private ShapeList shapeList;
    private ShapeDraw shapeDraw;

    public MouseHandler(IApplicationState appState, ShapeDraw shapeDraw, ShapeList shapeList) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeDraw = shapeDraw;
    }

    public void mousePressed(MouseEvent e) { startPoint = new Point(e.getX(), e.getY()); }

    public void mouseReleased(MouseEvent e) {
        endPoint = new Point(e.getX(), e.getY());
        appState.setActiveShapeSpecs(appState, startPoint, endPoint);
        ShapeSpecs activeShapeSpecs = appState.getActiveShapeSpecs();
        StartAndEndPointMode activeMode = appState.getActiveStartAndEndPointMode();

        if (activeMode == StartAndEndPointMode.DRAW) {
            shapeList.clearSelectedShapeList();
            ShapeCreate shapeCreate = new ShapeCreate(activeShapeSpecs, shapeList, shapeDraw);
            shapeCreate.run();
        }
        else if (activeMode == StartAndEndPointMode.SELECT)  {
            ShapeSelect shapeSelect = new ShapeSelect(activeShapeSpecs, shapeList, shapeDraw);
            shapeSelect.run();
        }
        else if (activeMode == StartAndEndPointMode.MOVE) {
            ShapeMove shapeMove = new ShapeMove(activeShapeSpecs, shapeList, shapeDraw);
            shapeMove.run();
        }
    }
}
