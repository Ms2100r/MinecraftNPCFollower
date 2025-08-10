package com.example.npcmod;

import cpw.mods.fml.common.command.CommandBase;
import cpw.mods.fml.common.command.ICommandSender;
import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayerMP;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandSpawnFollower extends CommandBase {
    @Override
    public String getCommandName() { return "spawnfollower"; }
    @Override
    public String getCommandUsage(ICommandSender sender) { return "/spawnfollower - spawn a follower near you"; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) sender;
            MinecraftServer server = player.mcServer;
            WorldServer world = (WorldServer) player.worldObj;
            GameProfile profile = new GameProfile(UUID.randomUUID(), player.getCommandSenderName() + "_npc");
            EntityFollower f = new EntityFollower(world, profile, player);
            f.setPosition(player.posX + 1.5, player.posY, player.posZ + 1.5);
            world.spawnEntityInWorld(f);
            player.addChatMessage(new net.minecraft.util.ChatComponentText("Follower spawned."));
        } else {
            sender.addChatMessage(new net.minecraft.util.ChatComponentText("This must be run by a player."));
        }
    }
}
