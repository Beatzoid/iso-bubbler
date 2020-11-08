package ui;

import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UIText extends UIComponent {

    private final String text;
    private final int fontSize;
    private final int fontStyle;
    private String fontFamily;
    private final Color color;

    private final boolean dropShadow;
    private final int dropShadowOffset;
    private final Color shadowColor;

    private Font font;

    /**
     * The UIText class displays text on the screen
     * @param text What text to display
     */
    public UIText(String text) {
        this.text = text;
        this.fontSize = 24;
        this.fontStyle = Font.PLAIN;
        this.color = Color.WHITE;
        loadFont();

        this.dropShadow = true;
        this.dropShadowOffset = 2;
        this.shadowColor = new Color(140, 140, 140);
    }

    // I don't fully understand how this works so please don't modify it unless you know how it works
    private void loadFont() {
        GraphicsEnvironment ge = null;
        try{
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, UIText.class.getResourceAsStream("/fonts/JoystixMonospace.ttf")));
        } catch(FontFormatException | IOException e){e.printStackTrace();}

        String targetName = "Joystix Monospace";
        String result = null;
        for (String c : ge.getAvailableFontFamilyNames()) {
            if (targetName.equals(c)) {
                result = c;
                break;
            }
        }
        this.fontFamily = result;
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();

        graphics.setFont(font);
        if (dropShadow) {
            graphics.setColor(shadowColor);
            graphics.drawString(text, padding.getLeft() + dropShadowOffset, fontSize + padding.getTop() + dropShadowOffset);
        }

        graphics.setColor(color);
        graphics.drawString(text, padding.getLeft(), fontSize + padding.getTop());

        graphics.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        createFont();
        calculateSize();
    }

    private void calculateSize() {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        size = new Size(
                fontMetrics.stringWidth(text) + padding.getHorizontal(),
                fontMetrics.getHeight() + padding.getVertical()
        );
    }

    private void createFont() {
        font = new Font(fontFamily, fontStyle, fontSize);
    }
}
