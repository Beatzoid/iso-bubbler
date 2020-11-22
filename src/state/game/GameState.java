package state.game;

import controller.NPCController;
import controller.PlayerController;
import core.Condition;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.Game;
import game.settings.GameSettings;
import state.game.ui.UIGameTime;
import state.game.ui.UISicknessStats;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;
import java.util.List;

public class GameState extends State {

    private List<Condition> victoryConditions;
    private List<Condition> defeatConditions;
    private boolean playing;

    /**
     * The GameState handles the game state
     * @param windowSize The windowSize
     * @param input The Input
     * @param gameSettings The game settings
     *
     * @see GameSettings
     * @see Size
     * @see Input
     */
    public GameState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(15, 10), spriteLibrary);
        playing = true;
        initializeCharacters();
        initializeUI(windowSize);
        initializeConditions();

        audioPlayer.playMusic("isobubbler.wav");
    }

    private void initializeConditions() {
        victoryConditions = List.of(() -> getNumberOfSick() == 0);
        // If the number of sick NPC's is greater than 25% of the total number of NPC's, the player loses
        defeatConditions = List.of(() -> (float) getNumberOfSick() / getNumberOfNPCs() > 0.25);
    }

    private void initializeUI(Size windowSize) {
       uiContainers.add(new UIGameTime(windowSize));
       uiContainers.add(new UISicknessStats(windowSize));
    }

    private void initializeCharacters() {
        SelectionCircle circle = new SelectionCircle();
        final Player player = new Player(new PlayerController(input), spriteLibrary, circle);
        gameObjects.add(player);
        camera.focusOn(player);
        gameObjects.add(circle);

        initializeNPCs(50);
        // 0 = Instant win
        // 25% or more of the total number of NPC's = Instant lose
        makeNumberOfNPCsSick(5);
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

    @Override
    public void update(Game game) {
        super.update(game);

        if (playing) {
            if (victoryConditions.stream().allMatch(Condition::isMet)) {
                win();
            }
            if (defeatConditions.stream().allMatch(Condition::isMet)) {
                lose();
            }
        }
    }

    private void lose() {
        playing = false;

        VerticalContainer loseContainer = new VerticalContainer(camera.getSize());
        loseContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        loseContainer.addUIComponent(new UIText("DEFEAT!"));
        uiContainers.add(loseContainer);
    }

    private void win() {
        playing = false;

        VerticalContainer winContainer = new VerticalContainer(camera.getSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        winContainer.setBackgroundColor(Color.DARK_GRAY);
        winContainer.addUIComponent(new UIButton("Menu", (state) -> state.setNextState(new MenuState(windowSize, input, gameSettings))));
        winContainer.addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
        uiContainers.add(winContainer);
    }

    /**
     * Get the number of sick people
     */
    public long getNumberOfSick() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && !npc.isAffectedBy(Isolated.class))
                .count();
    }

    /**
     * Get the number of isolated people
     */
    public long getNumberOfIsolated() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && npc.isAffectedBy(Isolated.class))
                .count();
    }

    /**
     * Get the number of healthy people
     */
    public long getNumberOfHealthy() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> !npc.isAffectedBy(Sick.class))
                .count();
    }

    /**
     * Get the number of NPC's
     */
    public long getNumberOfNPCs() {
        return getGameObjectsOfClass(NPC.class).size();
    }
}
