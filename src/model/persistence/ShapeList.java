package model.persistence;

import model.interfaces.Shapeable;

import java.util.ArrayList;

public class ShapeList {
    private ArrayList<Shapeable> shapeList;
    private ArrayList<Shapeable> selectedShapeList;
    private ArrayList<Shapeable> copiedShapeList;
    private ArrayList<Shapeable> pastedShapeList;

    public ShapeList() {
        this.shapeList = new ArrayList<>();
        this.selectedShapeList = new ArrayList<>();
        this.copiedShapeList = new ArrayList<>();
        this.pastedShapeList = new ArrayList<>();
    }

    //  Shape methods
    public void addShape(Shapeable shapeStrategy) {
        shapeList.add(shapeStrategy);
    }
    public void removeShape(Shapeable shapeStrategy) {
        shapeList.remove(shapeStrategy);
    }
    public ArrayList<Shapeable> getShapeList() {
        return shapeList;
    }

    //  Selected shape methods
    public void addSelectedShape(Shapeable shapeStrategy) { selectedShapeList.add(shapeStrategy); }
    public void removeSelectedShape(Shapeable shapeStrategy) {
        selectedShapeList.remove(shapeStrategy);
    }
    public ArrayList<Shapeable> getSelectedShapeList() { return selectedShapeList; }
    public void clearSelectedShapeList() { selectedShapeList.clear(); }

    //  Copied shape methods
    public void addCopiedShape(Shapeable shapeStrategy) { copiedShapeList.add(shapeStrategy); }
    public void removeCopiedShape(Shapeable shapeStrategy) {
        copiedShapeList.remove(shapeStrategy);
    }
    public ArrayList<Shapeable> getCopiedShapeList() {
        return copiedShapeList;
    }
    public void clearCopiedShapeList() {
        copiedShapeList.clear();
    }

    public StringBuilder toString(ArrayList<Shapeable> shapeList) {
        StringBuilder sb = new StringBuilder();
        for (Shapeable shapeStrategy : shapeList) {
            sb.append(shapeStrategy.getPrimaryColor()+" ");
            sb.append(shapeStrategy.getShapeType()+": ");
            sb.append("(X: "+shapeStrategy.getModStart().getX()+", ");
            sb.append("Y: "+shapeStrategy.getModStart().getY()+"), ");
        }
        return sb;
    }
}