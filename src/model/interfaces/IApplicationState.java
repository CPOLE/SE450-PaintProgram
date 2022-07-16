package model.interfaces;

import model.*;
import model.Point;

import java.awt.*;

public interface IApplicationState {


    void setActiveShape();
    void setActivePrimaryColor();
    void setActiveSecondaryColor();
    void setActiveShadingType();
    void setActiveStartAndEndPointMode();
    //
    public void setActiveShapeSpecs(IApplicationState appState, Point startPoint, Point endPoint);

    ShapeType getActiveShapeType();
    ShapeColor getActivePrimaryColor();
    ShapeColor getActiveSecondaryColor();
    ShapeShadingType getActiveShapeShadingType();
    StartAndEndPointMode getActiveStartAndEndPointMode();
    //
    ShapeSpecs getActiveShapeSpecs();
    //
    Graphics2D getGraphics2D();
}
