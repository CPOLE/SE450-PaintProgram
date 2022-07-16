package model.persistence;

import model.*;
import model.Point;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

import java.awt.*;
import java.io.Serializable;

public class ApplicationState implements IApplicationState, Serializable {
    private static final long serialVersionUID = -5545483996576839009L;
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    //  Default variables
    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;
    //  Specification, graphics variables
    private Point startPoint, endPoint;
    private ShapeSpecs shapeSpecs;
    private Graphics2D graphics2D;

    public ApplicationState(IUiModule uiModule, Graphics2D graphics2D) {
        this.uiModule = uiModule;
        this.graphics2D = graphics2D;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    //  Default setters
    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }
    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }
    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }
    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }
    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    //  Set shape specifications
    public void setActiveShapeSpecs(IApplicationState appState, Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        shapeSpecs = new ShapeSpecs(startPoint, endPoint, activeShapeType, activePrimaryColor, activeSecondaryColor, activeShapeShadingType, graphics2D);
    }

    //  Default getters
    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }
    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }
    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }
    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }
    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    //  Get shape specifications, graphics
    public ShapeSpecs getActiveShapeSpecs() { return shapeSpecs; }
    public Graphics2D getGraphics2D() { return graphics2D; }

    private void setDefaults() {
        activeShapeType = ShapeType.ELLIPSE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }
}
