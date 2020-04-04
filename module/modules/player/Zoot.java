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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Zoot
extends Module {
    public Zoot() {
        super("Zoot", new String[]{"Firion", "antipotion", "antifire"}, ModuleType.Player);
        this.setColor(new Color(208, 203, 229).getRGB());
    }

    @EventHandler
    private void onUpdate(EventPreUpdate e) {
        Potion[] arrpotion = Potion.potionTypes;
        int n = arrpotion.length;
        int n2 = 0;
        while (n2 < n) {
            PotionEffect effect;
            Potion potion = arrpotion[n2];
            if (e.getType() == 0 && potion != null && ((effect = this.mc.thePlayer.getActivePotionEffect(potion)) != null && potion.isBadEffect() || this.mc.thePlayer.isBurning() && !this.mc.thePlayer.isInWater() && this.mc.thePlayer.onGround)) {
                int i = 0;
                while (!(this.mc.thePlayer.isBurning() ? i >= 20 : i >= effect.getDuration() / 20)) {
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
                    ++i;
                }
            }
            ++n2;
        }
    }
}

