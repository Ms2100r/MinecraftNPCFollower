package com.example.npcmod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class FollowerAI extends EntityAIBase {
    private final EntityLiving follower;
    private EntityPlayer owner;
    private final double speed;
    private final float minDist;
    private final float maxDist;

    public FollowerAI(EntityLiving follower, double speed, float minDist, float maxDist) {
        this.follower = follower;
        this.speed = speed;
        this.minDist = minDist;
        this.maxDist = maxDist;
        if (follower instanceof EntityFollower) {
            this.owner = ((EntityFollower) follower).owner;
        }
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        if (owner == null) return false;
        double d = follower.getDistanceToEntity(owner);
        return d > minDist && d < 40.0D;
    }

    @Override
    public void updateTask() {
        if (owner != null) {
            follower.getNavigator().tryMoveToEntityLiving(owner, speed);
        }
    }
}
