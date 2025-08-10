package com.example.npcmod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class BreakerAI extends EntityAIBase {
    private final EntityLiving breaker;
    private int tickCounter = 0;

    public BreakerAI(EntityLiving breaker) {
        this.breaker = breaker;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void updateTask() {
        tickCounter++;
        if (tickCounter % 100 != 0) return;
        int dx = MathHelper.floor_double(breaker.posX) + (breaker.getRNG().nextInt(3) - 1);
        int dy = MathHelper.floor_double(breaker.posY);
        int dz = MathHelper.floor_double(breaker.posZ) + (breaker.getRNG().nextInt(3) - 1);
        BlockPos pos = new BlockPos(dx, dy, dz);
        if (!breaker.worldObj.isAirBlock(pos)) {
            breaker.swingItem();
            breaker.worldObj.playAuxSFX(2001, pos, net.minecraft.block.Block.getIdFromBlock(breaker.worldObj.getBlockState(pos).getBlock()));
            breaker.worldObj.setBlockToAir(pos);
        }
    }
}
