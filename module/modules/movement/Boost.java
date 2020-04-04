/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.movement;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.utils.TimerUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.Timer;

public class Boost
extends Module {
    private TimerUtil timer = new TimerUtil();

    public Boost() {
        super("Boost", new String[]{"boost"}, ModuleType.Movement);
        this.setColor(new Color(216, 253, 100).getRGB());
    }

    @EventHandler
    public void onUpdate(EventPreUpdate event) {
        this.mc.timer.timerSpeed = 3.0f;
        if (this.mc.thePlayer.ticksExisted % 15 == 0) {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        this.timer.reset();
        this.mc.timer.timerSpeed = 1.0f;
    }
}

