/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.player;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall
extends Module {
    public NoFall() {
        super("NoFall", new String[]{"Nofalldamage"}, ModuleType.Player);
        this.setColor(new Color(242, 137, 73).getRGB());
    }

    @EventHandler
    private void onUpdate(EventPreUpdate e) {
        if (this.mc.thePlayer.fallDistance > 3.0f) {
            this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ, this.mc.thePlayer.rotationYaw, this.mc.thePlayer.rotationPitch, true));
            this.mc.thePlayer.fallDistance = 0.0f;
        }
    }
}

