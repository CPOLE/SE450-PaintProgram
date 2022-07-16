package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseHandler;
import controller.ShapeDraw;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

//  Chris Pole #1941713
//  SE450 Final Submission

public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule, paintCanvas.getGraphics2D());
        ShapeList shapeList = new ShapeList();
        ShapeDraw shapeDraw = new ShapeDraw(paintCanvas.getGraphics2D());
        IJPaintController controller = new JPaintController(uiModule, appState, shapeList, shapeDraw);
        controller.setup();

        MouseHandler clickHandler = new MouseHandler(appState, shapeDraw, shapeList);
        paintCanvas.addMouseListener(clickHandler);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
