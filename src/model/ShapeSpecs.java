package model;

import model.interfaces.IApplicationState;
import java.awt.*;

public class ShapeSpecs {
    private Graphics2D graphics2D;
    private Point startPoint, endPoint;
    private Point modStart, modEnd;
    private int height, width;
    private ShapeType shapeType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;

    public ShapeSpecs (Point startPoint,
                       Point endPoint,
                       ShapeType shapeType,
                       ShapeColor primaryColor,
                       ShapeColor secondaryColor,
                       ShapeShadingType shadingType,
                       Graphics2D graphics2D) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.shapeType = shapeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shadingType = shadingType;
        this.graphics2D = graphics2D;
        this.modEnd = new Point(Math.max(startPoint.getX(), endPoint.getX()), Math.max(startPoint.getY(), endPoint.getY()));
        this.modStart = new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));
        this.height = this.modEnd.getY() - this.modStart.getY();
        this.width = this.modEnd.getX() - this.modStart.getX();
    }
    //
    public Point getStartPoint() { return startPoint; }
    public void setStartPoint(Point startPoint) { this.startPoint = startPoint; }
    //
    public Point getEndPoint() { return endPoint; }
    public void setEndPoint(Point endPoint) { this.endPoint = endPoint; }
    //
    public Point getModStart() { return modStart; }
    public void setModStart(Point modStart) { this.modStart = modStart; }
    //
    public Point getModEnd() { return modEnd; }
    public void setModEnd(Point modEnd) { this.modEnd = modEnd; }
    //
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    //
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    //
    public ShapeType getShapeType() {return shapeType; }
    public void setShapeType(ShapeType shapeType) { this.shapeType = shapeType; }
    //
    public ShapeColor getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(ShapeColor primaryColor) { this.primaryColor = primaryColor; }
    //
    public ShapeColor getSecondaryColor() { return secondaryColor; }
    public void setSecondaryColor(ShapeColor secondaryColor) { this.secondaryColor = secondaryColor; }
    //
    public ShapeShadingType getShadingType() { return shadingType; }
    public void setShadingType(ShapeShadingType shadingType) { this.shadingType = shadingType; }
    //
    public Graphics2D getGraphics2D() { return graphics2D; }
}
