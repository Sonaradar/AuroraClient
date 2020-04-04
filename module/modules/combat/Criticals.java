/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.combat;

import cn.Aurora.Client;
import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPacketSend;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.api.value.Mode;
import cn.Aurora.api.value.Value;
import cn.Aurora.management.ModuleManager;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.module.modules.movement.Speed;
import cn.Aurora.utils.Helper;
import cn.Aurora.utils.TimerUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Criticals
extends Module {
    private Mode mode = new Mode("Mode", "mode", (Enum[])CritMode.values(), (Enum)CritMode.Packet);
    private TimerUtil timer = new TimerUtil();

    public Criticals() {
        super("Criticals", new String[]{"crits", "crit"}, ModuleType.Combat);
        this.setColor(new Color(235, 194, 138).getRGB());
        this.addValues(this.mode);
    }

    @EventHandler
    private void onUpdate(EventPreUpdate e) {
        this.setSuffix(this.mode.getValue());
    }

    private boolean canCrit() {
        if (this.mc.thePlayer.onGround && !this.mc.thePlayer.isInWater() && !Client.instance.getModuleManager().getModuleByClass(Speed.class).isEnabled()) {
            return true;
        }
        return false;
    }

    @EventHandler
    private void onPacket(EventPacketSend e) {
        if (e.getPacket() instanceof C02PacketUseEntity && this.canCrit() && this.mode.getValue() == CritMode.Minijumps) {
            this.mc.thePlayer.motionY = 0.2;
        }
    }

    void packetCrit() {
        if (this.timer.hasReached(Helper.onServer("hypixel") ? 500 : 10) && this.mode.getValue() == CritMode.Packet && this.canCrit()) {
            double[] offsets = new double[]{0.0625, 0.0, 1.0E-4, 0.0};
            int i = 0;
            while (i < offsets.length) {
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + offsets[i], this.mc.thePlayer.posZ, false));
                ++i;
            }
            this.timer.reset();
        }
    }

    void offsetCrit() {
        if (this.canCrit() && !this.mc.getCurrentServerData().serverIP.contains("hypixel")) {
            double[] offsets = new double[]{0.0624, 0.0, 1.0E-4, 0.0};
            int i = 0;
            while (i < offsets.length) {
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + offsets[i], this.mc.thePlayer.posZ, false));
                ++i;
            }
        }
    }

    public void hypixelCrit() {
        if (this.mode.getValue() == CritMode.Hypixel && this.canCrit()) {
            double[] arrd = new double[]{0.06142999976873398, 0.0, 0.012511000037193298, 0.0};
            int n = arrd.length;
            int n2 = 0;
            while (n2 < n) {
                double offset = arrd[n2];
        		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0624, mc.thePlayer.posZ, true));
        		mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, false));
                ++n2;
            }
        }
    }

    static enum CritMode {
        Packet,
        Hypixel,
        Minijumps;
    }

}

