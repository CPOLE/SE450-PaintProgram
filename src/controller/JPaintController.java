package controller;

import model.interfaces.IApplicationState;
import model.persistence.ShapeList;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeList shapeList;
    private ShapeDraw shapeDraw;

    public JPaintController(IUiModule uiModule,
                            IApplicationState applicationState,
                            ShapeList shapeList,
                            ShapeDraw shapeDraw) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
        this.shapeDraw = shapeDraw;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> new ShapeCopy(shapeList).run());
        uiModule.addEvent(EventName.PASTE, () -> new ShapePaste(shapeList, shapeDraw).run());
        uiModule.addEvent(EventName.DELETE, () -> new ShapeDelete(shapeList, shapeDraw).run());
        uiModule.addEvent(EventName.UNDO, () -> new UndoAction().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoAction().run());
        uiModule.addEvent(EventName.GROUP, () -> new ShapeGroup(applicationState, shapeList, shapeDraw).run());
        uiModule.addEvent(EventName.UNGROUP, () -> new ShapeUngroup(applicationState, shapeList, shapeDraw).run());
    }
}
