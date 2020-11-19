package gfx;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

    private final Map<String, Image> animationSheets;

    /**
     * The SpriteSet class manages the SpriteSets
     */
    public SpriteSet() {
        this.animationSheets = new HashMap<>();
    }

    /**
     * The SpriteSet class manages the SpriteSets
     * @param image The image to use
     *
     * @see Image
     */
    public SpriteSet(Image image) {
        this.animationSheets = new HashMap<>();
        addSheet("default", image);
    }

    /**
     * Add a sheet
     * @param name The name of the sheet to add
     * @param animationSheet The animation sheet
     *
     * @see Image
     */
    public void addSheet(String name, Image animationSheet) {
        animationSheets.put(name, animationSheet);
    }

    /**
     * Get an animation sheet
     * @param name The name of the animation sheet to get
     */
    public Image getOrGetDefault(String name) {
        if (animationSheets.containsKey(name)) {
            return animationSheets.get(name);
        }
        return animationSheets.get("default");
    }
}
