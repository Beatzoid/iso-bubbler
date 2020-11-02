package gfx;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {

    private final SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    private final int updatesPerFrame;
    private int currentFrameTime;
    private int frameIndex;

    public AnimationManager(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 20;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        playAnimation("stand");
    }

    public Image getSprites() {
        return currentAnimationSheet.getSubimage(
                frameIndex + Game.SPRITE_SIZE,
                0,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    public void update() {
        currentFrameTime++;

        if (currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            if (frameIndex >= currentAnimationSheet.getWidth() / Game.SPRITE_SIZE - 1) {
                frameIndex = 0;
            }
        }
    }

    public void playAnimation(String name) {
        this.currentAnimationSheet = (BufferedImage) spriteSet.get(name);
    }

}
