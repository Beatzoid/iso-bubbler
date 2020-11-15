package gfx;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {

    private final Map<String, SpriteSet> spriteSets;
    private final Map<String, Image> images;

    /**
     * The SpriteLibrary keeps track of all sprites
     */
    public SpriteLibrary() {
        spriteSets = new HashMap<>();
        images = new HashMap<>();
        loadSpritesFromDisk();
    }

    private void loadSpritesFromDisk() {
        loadSpriteSets("/sprites/units");
        loadImages("/sprites/tiles");
        loadImages("/sprites/effects");
    }

    @SuppressWarnings("SameParameterValue")
    private void loadImages(String path) {
        String[] imagesInFolder = getImagesInFolder(path);

        for(String filename: imagesInFolder) {
            images.put(
                    filename.substring(0, filename.length() - 4),
                    ImageUtils.loadImage(path + "/" + filename)
            );
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void loadSpriteSets(String path) {
        String[] folderNames = getFolderNames(path);

        for(String folderName: folderNames) {
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = path + "/" + folderName;
            String[] sheetsInFolder = getImagesInFolder(pathToFolder);

            for(String sheetName : sheetsInFolder) {
                spriteSet.addSheet(
                        sheetName.substring(0, sheetName.length() - 4),
                        ImageUtils.loadImage(pathToFolder + "/" + sheetName)
                );
            }

            spriteSets.put(folderName, spriteSet);
        }
    }

    private String[] getImagesInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    /**
     * Get a sprite set
     * @param name The name of the unit to get
     */
    public SpriteSet getSpriteSet(String name) {
        return spriteSets.get(name);
    }

    /**
     * Get a image
     * @param name The name of the tile to get
     */
    public Image getImage(String name) {
        return images.get(name);
    }
}
