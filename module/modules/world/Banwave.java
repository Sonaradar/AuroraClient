/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.world;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.api.value.Numbers;
import cn.Aurora.api.value.Option;
import cn.Aurora.api.value.Value;
import cn.Aurora.management.FriendManager;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.utils.TimerUtil;
import java.awt.Color;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;

public class Banwave
extends Module {
    private TimerUtil timer = new TimerUtil();
    public ArrayList<Entity> banned;
    private String banMessage = "twitter.com/CustomKKK";
    private Option<Boolean> tempBan = new Option<Boolean>("Temp Ban", "temp", false);
    private Numbers<Double> banDelay = new Numbers<Double>("Delay", "delay", 10.0, 1.0, 20.0, 1.0);

    public Banwave() {
        super("BanWave", new String[]{"dick", "banner"}, ModuleType.Player);
        this.setColor(new Color(255, 0, 0).getRGB());
        this.banned = new ArrayList();
        this.addValues(this.tempBan, this.banDelay);
    }

    @Override
    public void onEnable() {
        this.banned.clear();
        super.onEnable();
    }

    @EventHandler
    public void onUpdate(EventPreUpdate event) {
        for (Object o : this.mc.theWorld.getLoadedEntityList()) {
            if (!(o instanceof EntityOtherPlayerMP)) continue;
            EntityOtherPlayerMP e = (EntityOtherPlayerMP)o;
            if (!this.timer.hasReached(this.banDelay.getValue() * 100.0) || FriendManager.isFriend(e.getName()) || e.getName() == this.mc.thePlayer.getName() || this.banned.contains(e)) continue;
            if (this.tempBan.getValue().booleanValue()) {
                this.mc.thePlayer.sendChatMessage("/tempban " + e.getName() + " 7d" + " " + this.banMessage);
                System.out.println("/tempban " + e.getName() + " 7d" + " " + this.banMessage);
            } else {
                this.mc.thePlayer.sendChatMessage("/ban " + e.getName() + " " + this.banMessage);
                System.out.println("/ban " + e.getName() + " " + this.banMessage);
            }
            this.banned.add(e);
            this.timer.reset();
        }
    }
}

