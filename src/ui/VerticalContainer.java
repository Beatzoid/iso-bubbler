package ui;

import core.Position;
import core.Size;

public class VerticalContainer extends UIContainer {

    /**
     * The VerticalContainer manages different UI elements on the vertical axis
     *
     * @param windowSize The window size
     */
    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for(UIComponent uiComponent : children) {
            combinedChildHeight += uiComponent.getSize().getHeight() + uiComponent.getMargin().getVertical();

            if (uiComponent.getSize().getWidth() > widestChildWidth) {
                widestChildWidth = uiComponent.getSize().getWidth();
            }
        }

        return new Size(widestChildWidth, combinedChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentY = padding.getTop();
        int currentX = padding.getLeft();

        for (UIComponent uiComponent : children) {
            if (centerChildren) {
                currentX = getSize().getWidth() / 2 - uiComponent.getSize().getWidth() / 2;
            }

            currentY += uiComponent.getMargin().getTop();
            uiComponent.setRelativePosition(new Position(currentX, currentY));
            uiComponent.setAbsolutePosition(new Position(currentX + absolutePosition.intX(), currentY + absolutePosition.intY()));
            currentY += uiComponent.getSize().getHeight();
            currentY += uiComponent.getMargin().getBottom();
        }
    }
}
