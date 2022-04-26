package ui;

import core.Position;
import core.Size;

public class HorizontalContainer extends UIContainer {

    /**
     * The HorizontalContainer manages different UI elements on the horizontal axis
     *
     * @param windowSize The window size
     */
    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;

        for (UIComponent uiComponent : children) {
            combinedChildWidth += uiComponent.getSize().getWidth() + uiComponent.getMargin().getHorizontal();

            if (uiComponent.getSize().getHeight() > tallestChildHeight) {
                tallestChildHeight = uiComponent.getSize().getHeight();
            }
        }

        return new Size(combinedChildWidth, tallestChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentX = padding.getLeft();

        for (UIComponent uiComponent : children) {
            currentX += uiComponent.getMargin().getLeft();
            uiComponent.setRelativePosition(new Position(currentX, padding.getTop()));
            uiComponent.setAbsolutePosition(new Position(currentX + absolutePosition.intX(), padding.getTop() + absolutePosition.intY()));
            currentX += uiComponent.getSize().getWidth();
            currentX += uiComponent.getMargin().getRight();
        }
    }
}
