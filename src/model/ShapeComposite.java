package model;

import model.interfaces.Shapeable;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class ShapeComposite implements Shapeable {
    private List<Shapeable> components;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;
    private ShapeColor primaryColor, secondaryColor;
    private Graphics2D graphics2D;

    //  Add protect no arg constructor to ShapeStrategy
    public ShapeComposite(List<Shapeable> components) {
        this.components = components;
    }

    // Add, Remove Components
    public void addChild(Shapeable shapeComponent) {
        components.add(shapeComponent);
    }
    public void removeChild(Shapeable shapeComponent) {
        components.remove(shapeComponent);
    }
    public List<Shapeable> getChildren() {
        return components;
    }

    // Move Components
    public void moveX(int shiftX) {
        for (Shapeable shapeComponent : components) {
            shapeComponent.moveX(shiftX);
        }
    }

    public void moveY(int shiftY) {
        for (Shapeable shapeComponent : components) {
            shapeComponent.moveY(shiftY);
        }
    }

    // Draw Components
    public void draw() {
        for(Shapeable shapeComponent : components) {
            shapeComponent.draw();
        }
    }

    public void drawOutline() {
        for(Shapeable shapeComponent : components) {
            shapeComponent.drawOutline();
        }
    }

    // Return Component Dimensions
    public int getHeight() {
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for(Shapeable shapeComponent : components) {
            if(shapeComponent.getModEnd().getY() > maxY) {
                maxY = shapeComponent.getModEnd().getY();
            }
            if(shapeComponent.getModStart().getY() < minY) {
                minY = shapeComponent.getModStart().getY();
            }
        }
        return maxY - minY;
    }

    public int getWidth() {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;

        for(Shapeable shapeComponent : components) {
            if(shapeComponent.getModEnd().getX() > maxX) {
                maxX = shapeComponent.getModEnd().getX();
            }
            if(shapeComponent.getModStart().getX() < minX) {
                minX = shapeComponent.getModStart().getX();
            }
        }
        return maxX - minX;
    }

    // Return Component Bounding Points
    public Point getModStart() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for(Shapeable shapeComponent : components) {
            if(shapeComponent.getModStart().getX() < minX) {
                minX = shapeComponent.getModStart().getX();
            }
            if(shapeComponent.getModStart().getY() < minY) {
                minY = shapeComponent.getModStart().getY();
            }
        }
        return new Point(minX, minY);
    }

    public Point getModEnd() {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for(Shapeable shapeComponent : components) {
            if(shapeComponent.getModEnd().getX() > maxX) {
                maxX = shapeComponent.getModEnd().getX();
            }
            if(shapeComponent.getModEnd().getY() > maxY) {
                maxY = shapeComponent.getModEnd().getY();
            }
        }
        return new Point(maxX, maxY);
    }

    //  Not implemented
    public ShapeType getShapeType() { return shapeType; }
    public ShapeShadingType getShadingType() { return shadingType; }
    public ShapeColor getPrimaryColor() { return primaryColor; }
    public ShapeColor getSecondaryColor() { return secondaryColor; }
    public Graphics2D getGraphics2D() { return graphics2D; }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<Shapeable> copiedComponents = new ArrayList<>();
        for (Shapeable shapeComponent : components) {
            copiedComponents.add((Shapeable)shapeComponent.clone());
        }
        return new ShapeComposite(copiedComponents);
    }
}
