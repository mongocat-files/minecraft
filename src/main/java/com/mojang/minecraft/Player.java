package com.mojang.minecraft;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Tile;
import org.lwjgl.input.Keyboard;

public class Player extends Entity {
    public boolean flash;
    public int cooldown = 0;
    public int id = 0;
    public boolean resetId = false;
    /**
     * The player that is controlling the camera of the game
     *
     * @param level Level of the player
     */
    public Player(Level level) {
        super(level);

        this.heightOffset = 1.62f;
    }

    @Override
    public void onTick() {

        super.onTick();

        float forward = 0.0F;
        float vertical = 0.0F;

        if(resetId){
            id = 0;
        }
        if (y <= -50) {
            double v = this.motionY + 9.1D;
            this.motionY = v;
            flash = true;
        }
        if (y >= -30) {
            flash = false;
        }

        // Reset the position of the player
            if (Keyboard.isKeyDown(19)) { // R
                if (cooldown >= 30) {
                    moveRelative(forward--, forward--, 4);
                    this.motionY = 0.5F;
                    cooldown = 0;
                }
            }

        if(resetId)
            id = 0;

        if(goFast){
            moveRelative(forward--, forward--, 0.5F);
        }


            // Player movement
            if (Keyboard.isKeyDown(200) || Keyboard.isKeyDown(17)) { // Up, W
                forward--;
            }
            if (Keyboard.isKeyDown(208) || Keyboard.isKeyDown(31)) { // Down, S
                forward++;
            }
            if (Keyboard.isKeyDown(203) || Keyboard.isKeyDown(30)) { // Left, A
                vertical--;
            }
            if (Keyboard.isKeyDown(205) || Keyboard.isKeyDown(32)) {  // Right, D
                vertical++;
            }
            if (Keyboard.isKeyDown(205) || Keyboard.isKeyDown(27)) {  // Right, D
                vertical++;
            }
            if ((Keyboard.isKeyDown(57) || Keyboard.isKeyDown(219)) && this.onGround) { // Space, Windows Key
                this.motionY = 0.5F;
            }

            // Add motion to the player using keyboard input
            moveRelative(vertical, forward, this.onGround ? 0.1F : 0.02F);

            // Apply gravity motion
            this.motionY -= 0.08D;

            // Move the player using the motion
            move(this.motionX, this.motionY, this.motionZ);

            // Decrease motion
            this.motionX *= 0.91F;
            this.motionY *= 0.98F;
            this.motionZ *= 0.91F;

            // Decrease motion on ground
            if (this.onGround) {
                this.motionX *= 0.7F;
                this.motionZ *= 0.7F;
        }
    }
}
