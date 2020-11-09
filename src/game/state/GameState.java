package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.effect.Sick;
import game.ui.UIGameTime;
import game.ui.UISicknessStats;
import input.Input;
import map.GameMap;
import ui.*;

import java.awt.*;

public class GameState extends State {

    /**
     * The GameState handles the game state
     * @param windowSize The windowSize
     * @param input The Input
     *
     * @see Size
     * @see Input
     */
    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        initializeCharacters();
        initializeUI(windowSize);
    }

    private void initializeUI(Size windowSize) {
       uiContainers.add(new UIGameTime(windowSize));
       uiContainers.add(new UISicknessStats(windowSize));
    }

    private void initializeCharacters() {
        final Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);

        initializeNPCs(200);
        makeNumberOfNPCsSick(10);
    }

    private void makeNumberOfNPCsSick(int number) {
        getGameObjectsOfClass(NPC.class).stream()
                .limit(number)
                .forEach(npc -> npc.addEffect(new Sick()));
    }

    @SuppressWarnings("SameParameterValue")
    private void initializeNPCs(int numberOfNPcs) {
        for (int i = 0; i < numberOfNPcs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            gameObjects.add(npc);
        }
    }
}
