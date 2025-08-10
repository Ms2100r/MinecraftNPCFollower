package com.example.npcmod;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.server.MinecraftServer;

public class EntityFollower extends EntityOtherPlayerMP {
    public EntityPlayer owner;

    public EntityFollower(World world, GameProfile profile, EntityPlayer owner) {
        super((MinecraftServer) null, world, profile, null);
        this.owner = owner;
        this.tasks.addTask(1, new FollowerAI(this, 1.0D, 2.0F, 6.0F));
        this.tasks.addTask(2, new BreakerAI(this));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (owner != null) {
            this.faceEntity(owner, 10.0F, 10.0F);
        }
    }
}
