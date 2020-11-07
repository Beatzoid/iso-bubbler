package gfx;

import core.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static final int ALPHA_OPAQUE = 1;
    public static final int ALPHA_BIT_MASKED = 2;
    public static final int ALPHA_BLEND = 3;

    /**
     * Load a image
     * @param filePath The file path to load from
     */
    public static Image loadImage(String filePath) {
        try {
            Image imageFromDisk = ImageIO.read(ImageUtils.class.getResource(filePath));
            BufferedImage compatibleImage = (BufferedImage) createCompatibleImage(
                    new Size(imageFromDisk.getWidth(null), imageFromDisk.getHeight(null)),
                    ALPHA_BIT_MASKED
            );

            Graphics2D graphics = compatibleImage.createGraphics();
            graphics.drawImage(imageFromDisk, 0, 0, null);

            graphics.dispose();
            return compatibleImage;
        } catch (IOException e) {
            System.out.println("Could not load image from path: " + filePath);
        }

        return null;
    }

    /**
     * Make a compatible image based on the system being used currently
     * @param size The size
     * @param transparency The transparency
     */
    public static Image createCompatibleImage(Size size, int transparency) {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        return graphicsConfiguration.createCompatibleImage(size.getWidth(), size.getHeight(), transparency);
    }
}
