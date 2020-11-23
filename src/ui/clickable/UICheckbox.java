package ui.clickable;

import core.Size;
import game.settings.Setting;
import gfx.ImageUtils;
import state.State;
import ui.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UICheckbox extends UIComponent {

    private UIContainer container;

    /**
     * The UICheckbox class manages checkbox's on the UI
     *
     * @param label The label for the checkbox
     * @param setting The setting for the checkbox
     */
    public UICheckbox(String label, Setting<Boolean> setting) {
        this.container = new HorizontalContainer(new Size(0, 0));
        container.addUIComponent(new Checkbox(setting));
        container.addUIComponent(new UIText(label));
    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }

    @Override
    public void update(State state) {
        container.update(state);
        size = container.getSize();
        container.setParent(parent);
        container.setAbsolutePosition(absolutePosition);
    }

    private static class Checkbox extends UIClickable {

        private Setting<Boolean> setting;
        private Color color;

        /**
         * The Checkbox class
         * @param setting The setting
         */
        private Checkbox(Setting<Boolean> setting) {
            this.setting = setting;
            size = new Size(20, 20);
            color = Color.GRAY;
            margin = new Spacing(0, 5, 0, 0);
        }

        @Override
        public void update(State state) {
            super.update(state);

            color = setting.getValue() ? Color.WHITE : Color.GRAY;

            if (hasFocus) {
                color = Color.LIGHT_GRAY;

                if (isPressed) {
                    color = Color.DARK_GRAY;
                }
            }
        }

        @Override
        protected void onFocus(State state) {}

        @Override
        public void onDrag(State state) {}
        @Override
        public void onClick(State state) {
            if (hasFocus) {
                setting.setValue(!setting.getValue());
            }
        }

        @Override
        public Image getSprite() {
            BufferedImage sprite  = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
            Graphics2D graphics = sprite.createGraphics();

            graphics.setColor(color);
            if (setting.getValue()) {
                graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
            } else {
                graphics.drawRect(0, 0, size.getWidth() - 1, size.getHeight() - 1);
            }

            graphics.dispose();
            return sprite;
        }
    }
}
