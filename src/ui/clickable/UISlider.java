package ui.clickable;

import core.Size;
import gfx.ImageUtils;
import state.State;
import ui.Spacing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UISlider extends UIClickable {

    private double value;
    private double min;
    private double max;

    /**
     * The UISlider class manages UI Sliders
     * @param min The minimum value of the slider
     * @param max The maximum value of the slider
     */
    public UISlider(double min, double max) {
        this.min = min;
        this.max = max;
        this.value = max;
        this.size = new Size(360, 10);
        this.margin = new Spacing(0, 0, 15, 0);
    }

    @Override
    protected void onFocus(State state) {}

    @Override
    protected void onDrag(State state) {
        this.value = getValueAt(state.getInput().getMousePosition().getX());
    }

    @Override
    protected void onClick(State state) {}

    private double getValueAt(double xPosition) {
        double positionOnSlider = xPosition - absolutePosition.getX();
        double percentage = positionOnSlider / size.getWidth();
        double range = max - min;

        return min + range * percentage;
    }

    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics graphics = sprite.createGraphics();

        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getPixelsOfCurrentValue(), size.getHeight());

        graphics.dispose();
        return sprite;
    }

    private int getPixelsOfCurrentValue() {
        double range = max - min;
        double percentage = value - min;

        return (int) ((percentage / range) * size.getWidth());
    }

    /**
     * Get the value of the slider
     */
    public double getValue() {
        return value;
    }

    /**
     * Set the value of the slider
     * @param value The new value
     */
    public void setValue(double value) {
        this.value = value;
    }
}
