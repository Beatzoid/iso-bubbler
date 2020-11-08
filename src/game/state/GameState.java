package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.action.Cough;
import entity.effect.Sick;
import input.Input;
import map.GameMap;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIContainer;
import ui.VerticalContainer;

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
        initializeUI();
    }

    private void initializeUI() {
        UIContainer container = new VerticalContainer();

        container.setPadding(new Spacing(50));
        container.setBackgroundColor(Color.GRAY);
        container.addUIComponent(new HorizontalContainer());
        container.addUIComponent(new HorizontalContainer());
        container.addUIComponent(new HorizontalContainer());

        uiContainers.add(container);
    }

    private void initializeCharacters() {
        final Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);

        initializeNPCs(200);
    }

    private void initializeNPCs(int numberOfNPcs) {
        for (int i = 0; i < numberOfNPcs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            npc.addEffect(new Sick());
            gameObjects.add(npc);
        }
    }
}
