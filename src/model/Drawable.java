package model;

import model.interfaces.Shapeable;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Drawable implements Shapeable {
    protected Point startPoint, endPoint;
    protected Point modStart, modEnd;
    protected int height, width;
    protected ShapeShadingType shadingType;
    protected ShapeColor primaryColor, secondaryColor;
    protected ShapeType shapeType;
    //
    protected Graphics2D graphics2D;

    public Drawable(ShapeSpecs shapeSpecs) {
        this.startPoint = shapeSpecs.getStartPoint();
        this.endPoint = shapeSpecs.getEndPoint();
        this.modStart = shapeSpecs.getModStart();
        this.modEnd = shapeSpecs.getModEnd();
        this.height = shapeSpecs.getHeight();
        this.width = shapeSpecs.getWidth();
        this.shapeType = shapeSpecs.getShapeType();
        this.primaryColor = shapeSpecs.getPrimaryColor();
        this.secondaryColor = shapeSpecs.getSecondaryColor();
        this.shadingType = shapeSpecs.getShadingType();
        this.graphics2D = shapeSpecs.getGraphics2D();
    }

    public void draw() {
        if(shadingType.equals(ShapeShadingType.OUTLINE)) {
            drawOutlinedShape();
        }
        else if(shadingType.equals(ShapeShadingType.FILLED_IN)) {
            drawFilledInShape();
        }
        else if(shadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            drawOutlineAndFilledInShape();
        }
    }

    // From Drawable - required for ShapeDraw's drawAllShapes implementation to highlight selected shapes
    public void drawOutline () {
        this.drawOutlinedShape();
    }

    //  Draw Strategies
    public abstract void drawOutlinedShape();
    public abstract void drawFilledInShape();
    public abstract void drawOutlineAndFilledInShape();

    //  Move
    public void moveX(int shiftX) {
        this.modStart.setX((modStart.getX() + shiftX));
        this.modEnd.setX((modEnd.getX() + shiftX));
    }

    public void moveY(int shiftY) {
        this.modStart.setY((modStart.getY() + shiftY));
        this.modEnd.setY((modEnd.getY() + shiftY));
    }

    public Point getModStart() { return modStart; }
    public Point getModEnd() { return modEnd; }
    public int getHeight() { return height; }
    public int getWidth() { return width; }
    public ShapeColor getPrimaryColor() { return primaryColor; }
    public ShapeColor getSecondaryColor() { return secondaryColor; }
    public ShapeShadingType getShadingType() { return shadingType; }
    public ShapeType getShapeType() { return shapeType; }
    public Graphics2D getGraphics2D() { return graphics2D; }

    public static HashMap<ShapeColor, Color> colorMap = new HashMap<ShapeColor, Color>() {{
        put(ShapeColor.BLACK, Color.BLACK);
        put(ShapeColor.BLUE, Color.BLUE);
        put(ShapeColor.CYAN, Color.CYAN);
        put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        put(ShapeColor.GREEN, Color.GREEN);
        put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        put(ShapeColor.MAGENTA, Color.MAGENTA);
        put(ShapeColor.ORANGE, Color.ORANGE);
        put(ShapeColor.PINK, Color.PINK);
        put(ShapeColor.RED, Color.RED);
        put(ShapeColor.WHITE, Color.WHITE);
        put(ShapeColor.YELLOW, Color.YELLOW);
    }};

    //
    public List<Shapeable> getChildren() {
        List<Shapeable> child = new ArrayList<Shapeable>();
        child.add(this);
        return child;
    }

    public Object clone() throws CloneNotSupportedException {
        ShapeSpecs clonedSpecs = new ShapeSpecs((Point) modStart.clone(), (Point) modEnd.clone(), shapeType, primaryColor, secondaryColor, shadingType, graphics2D);
        return ShapeFactory.constructShape(clonedSpecs);
    }
}
