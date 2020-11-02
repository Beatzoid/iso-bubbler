package gfx;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

    private final Map<String, Image> animationSheets;

    public SpriteSet() {
        this.animationSheets = new HashMap<>();
    }

    public void addSheet(String name, Image animationSheet) {
        animationSheets.put(name, animationSheet);
    }

    public Image get(String name) {
        return animationSheets.get(name);
    }
}
