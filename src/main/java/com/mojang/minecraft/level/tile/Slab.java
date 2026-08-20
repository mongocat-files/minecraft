package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.Tessellator;
import com.mojang.minecraft.phys.AABB;

import java.util.Random;

public class Slab extends Tile {

    /**
     * Create a grass tile with the id
     *
     * @param id The id of the grass tile
     */
    protected Slab(int id) {
        super(id);
        this.textureId = 19;
    }

    @Override
    public void render(Tessellator tessellator, Level level, int layer, int x, int y, int z) {
        // Render in correct layer
        if (level.isLit(x, y, z) ^ layer != 1) {
            return;
        }

        // Texture id
        int textureId = this.getTexture(this.textureId);

        // Texture mapping points
        float minU = textureId % 16 / 16.0F;
        float maxU = minU + 16 / 256F;
        float minV = (float) (textureId / 16) / 16.0F;
        float maxV = minV + 16 / 256F;



        // Color
        tessellator.color(1.0F, 1.0F, 1.0F);
        {


            float minX = x + 0.0f;
            float maxX = x + 1.0f;
            float minY = y + 0.0f;
            float maxY = y + 0.5f;
            float minZ = z + 0.0f;
            float maxZ = z + 1.0f;

                tessellator.vertexUV(maxX, maxY, maxZ, maxU, maxV);
                tessellator.vertexUV(maxX, maxY, minZ, maxU, minV);
                tessellator.vertexUV(minX, maxY, minZ, minU, minV);
                tessellator.vertexUV(minX, maxY, maxZ, minU, maxV);

                tessellator.vertexUV(minX, maxY, minZ, maxU, minV);
                tessellator.vertexUV(maxX, maxY, minZ, minU, minV);
                tessellator.vertexUV(maxX, minY, minZ, minU, maxV);
                tessellator.vertexUV(minX, minY, minZ, maxU, maxV);

                tessellator.vertexUV(minX, maxY, maxZ, minU, minV);
                tessellator.vertexUV(minX, minY, maxZ, minU, maxV);
                tessellator.vertexUV(maxX, minY, maxZ, maxU, maxV);
                tessellator.vertexUV(maxX, maxY, maxZ, maxU, minV);

                tessellator.vertexUV(minX, maxY, maxZ, maxU, minV);
                tessellator.vertexUV(minX, maxY, minZ, minU, minV);
                tessellator.vertexUV(minX, minY, minZ, minU, maxV);
                tessellator.vertexUV(minX, minY, maxZ, maxU, maxV);

                tessellator.vertexUV(maxX, minY, maxZ, minU, maxV);
                tessellator.vertexUV(maxX, minY, minZ, maxU, maxV);
                tessellator.vertexUV(maxX, maxY, minZ, maxU, minV);
                tessellator.vertexUV(maxX, maxY, maxZ, minU, minV);
        }
    }

    @Override
    public AABB getAABB(int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}