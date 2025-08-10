package com.example.npcmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.player.EntityPlayerMP;

@Mod(modid = NpcMod.MODID, name = NpcMod.NAME, version = NpcMod.VERSION)
public class NpcMod {
    public static final String MODID = "npcmod";
    public static final String NAME = "NPC Follower Mod";
    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // register entity (numeric ID chosen arbitrarily; on real projects pick unique ID)
        EntityRegistry.registerModEntity(EntityFollower.class, "follower", 120, this, 80, 3, true);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // nothing here for now
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        // register commands if desired
    }
}
