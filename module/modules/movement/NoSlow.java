/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.movement;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.utils.Helper;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlow
extends Module {
    public NoSlow() {
        super("NoSlow", new String[]{"noslowdown"}, ModuleType.Movement);
        this.setColor(new Color(216, 253, 100).getRGB());
    }

    @EventHandler
    private void onUpdate(EventPreUpdate e) {
        if (!(!this.mc.thePlayer.isBlocking() || Helper.onServer("invaded") || Helper.onServer("hypixel") || Helper.onServer("faithful") || Helper.onServer("mineman"))) {
            this.mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        }
    }
}

