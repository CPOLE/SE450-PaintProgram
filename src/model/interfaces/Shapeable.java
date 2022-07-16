package model.interfaces;

import model.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import java.util.List;
import java.awt.Graphics2D;

public interface Shapeable extends Cloneable {
    Point getModStart();
    Point getModEnd();

    int getHeight();
    int getWidth();

    ShapeType getShapeType();
    ShapeShadingType getShadingType();
    ShapeColor getPrimaryColor();
    ShapeColor getSecondaryColor();
    Graphics2D getGraphics2D();

    void moveX(int shiftX);
    void moveY(int shiftY);

    void draw();
    void drawOutline();

    List<Shapeable> getChildren();
    Object clone() throws CloneNotSupportedException;
}
