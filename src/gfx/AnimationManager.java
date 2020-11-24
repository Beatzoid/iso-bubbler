package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {

    private final SpriteSet spriteSet;
    private String currentAnimationName;
    private BufferedImage currentAnimationSheet;
    private final int updatesPerFrame;
    private int currentFrameTime;
    private int frameIndex;
    private int directionIndex;
    private final boolean looping;

    /**
     * The AnimationManager manages animations
     * @param spriteSet The SpriteSet to use
     *
     * @see SpriteSet
     */
    public AnimationManager(SpriteSet spriteSet) {
        this(spriteSet, true);
    }

    /**
     * The AnimationManager manages animations
     * @param spriteSet The SpriteSet to use
     * @param looping Whether or not to loop the animation
     *
     * @see SpriteSet
     */
    public AnimationManager(SpriteSet spriteSet, boolean looping) {
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 20;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        this.directionIndex = 0;
        this.looping = looping;

        currentAnimationName = "";
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

            final int animationSize = currentAnimationSheet.getWidth() / Game.SPRITE_SIZE;
            if (frameIndex >= animationSize) {
                frameIndex = looping ? 0 : animationSize - 1;
            }
        }
    }

    /**
     * Play a animation
     * @param name The name of the animation to play
     */
    public void playAnimation(String name) {
        if (!name.equals(currentAnimationName)) {
            this.currentAnimationSheet = (BufferedImage) spriteSet.getOrGetDefault(name);
            currentAnimationName = name;
            frameIndex = 0;
        }
    }

}
