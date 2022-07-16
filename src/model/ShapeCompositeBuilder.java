package model;

import model.interfaces.Shapeable;

import java.util.ArrayList;
import java.util.List;

public class ShapeCompositeBuilder {
    private List<Shapeable> components = new ArrayList<>();

    public ShapeCompositeBuilder addComponent(Shapeable component) {
        this.components.add(component);
        return this;
    }

    public ShapeComposite build() {
        return new ShapeComposite(components);
    }
}
