package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {

    private final SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    private final int updatesPerFrame;
    private int currentFrameTime;
    private int frameIndex;
    private int directionIndex;

    /**
     * The AnimationManager manages animations
     * @param spriteSet The SpriteSet to use
     *
     * @see SpriteSet
     */
    public AnimationManager(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 20;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        this.directionIndex = 0;
        playAnimation("stand");
    }

    /**
     * Get the sprites
     */
    public Image getSprites() {
        return currentAnimationSheet.getSubimage(
                frameIndex * Game.SPRITE_SIZE,
                directionIndex * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    /**
     * Update the animation
     * @param direction The direction
     *
     * @see Direction
     */
    public void update(Direction direction) {
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();

        if (currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            if (frameIndex >= currentAnimationSheet.getWidth() / Game.SPRITE_SIZE) {
                frameIndex = 0;
            }
        }
    }

    /**
     * Play a animation
     * @param name The name of the animation to play
     */
    public void playAnimation(String name) {
        this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
    }

}
