package io;

import gfx.SpriteLibrary;
import map.GameMap;

import java.io.*;
import java.net.URL;

public class MapIO {

    /**
     * Save a GameMap
     *
     * @param map The GameMap to save
     */
    public static void save(GameMap map) {
        final URL urlToResourcesFolder = MapIO.class.getResource("/");
        File mapsFolder = new File(urlToResourcesFolder.getFile() + "/maps/");

        if (!mapsFolder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            mapsFolder.mkdir();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(mapsFolder + "/map.isomap"))) {
            bufferedWriter.write(map.serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a map
     *
     * @param spriteLibrary The SpriteLibrary
     */
    public static GameMap load(SpriteLibrary spriteLibrary) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(MapIO.class.getResource("/maps/map.isomap").getFile()))) {
            GameMap map = new GameMap();

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append(line);
            }

            map.applySerializedData(stringBuilder.toString());
            map.reloadGraphics(spriteLibrary);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
