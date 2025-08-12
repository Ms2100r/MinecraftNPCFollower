#!binbash
set -e

echo === Forge 1.8 MDK + NPC Follower MOD セットアップ開始 ===

# Java 8 インストール（Codespaces）
echo [16] Java 8 インストール中...
sudo apt-get update -y
sudo apt-get install -y openjdk-8-jdk unzip wget

# Forge 1.8 MDK ダウンロード
echo [26] Forge MDK ダウンロード中...
FORGE_URL=httpsmaven.minecraftforge.netnetminecraftforgeforge1.8-11.14.4.1577forge-1.8-11.14.4.1577-mdk.zip
wget -O forge_mdk.zip $FORGE_URL

# 展開
echo [36] Forge MDK 展開中...
unzip -o forge_mdk.zip -d forge_mdk
rm forge_mdk.zip
cp -r forge_mdk .

# NPC MOD ソース配置
echo [46] MODソース配置中...
mkdir -p srcmainjavacomexamplenpcfollower
cat  srcmainjavacomexamplenpcfollowerNPCFollowerMod.java 'EOF'
package com.example.npcfollower;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

@Mod(modid = npcfollower, name = NPC Follower, version = 1.0)
public class NPCFollowerMod {
    private EntityOtherPlayerMP npc;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;
        if (world != null && mc.thePlayer != null) {
            npc = new EntityOtherPlayerMP(world, mc.thePlayer.getGameProfile());
            npc.copyLocationAndAnglesFrom(mc.thePlayer);
            world.addEntityToWorld(-1234, npc);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        if (npc != null && Minecraft.getMinecraft().thePlayer != null) {
            npc.setPosition(
                Minecraft.getMinecraft().thePlayer.posX - 1,
                Minecraft.getMinecraft().thePlayer.posY,
                Minecraft.getMinecraft().thePlayer.posZ - 1
            );
        }
    }
}
EOF

# build.gradle をMOD情報に合わせて修正
echo [56] build.gradle 修正中...
sed -i 'sgroup=.group=com.example.npcfollower' build.gradle
sed -i 'sversion=.version=1.0' build.gradle
sed -i 'sarchivesBaseName=.archivesBaseName=npcfollower' build.gradle

# ビルド
echo [66] ビルド中...
.gradlew setupDecompWorkspace
.gradlew build

echo === 完了！ JARファイルは buildlibs にあります ===
