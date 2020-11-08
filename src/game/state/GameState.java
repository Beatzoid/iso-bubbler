package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.effect.Sick;
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
        UIContainer container = new VerticalContainer(windowSize);
        container.setPadding(new Spacing(5));
        container.setBackgroundColor(new Color(0, 0, 0, 0   ));

        UIContainer containerEnd = new VerticalContainer(windowSize);
        containerEnd.setPadding(new Spacing(5));
        containerEnd.setBackgroundColor(new Color(0, 0, 0, 0   ));
        containerEnd.setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));

        container.addUIComponent(new UIText("Left!"));
        containerEnd.addUIComponent(new UIText("Right!"));

        uiContainers.add(container);
        uiContainers.add(containerEnd);
    }

    private void initializeCharacters() {
        final Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);

        initializeNPCs(200);
    }

    @SuppressWarnings("SameParameterValue")
    private void initializeNPCs(int numberOfNPcs) {
        for (int i = 0; i < numberOfNPcs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            npc.addEffect(new Sick());
            gameObjects.add(npc);
        }
    }
}
