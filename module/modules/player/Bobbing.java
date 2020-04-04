/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.player;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.api.value.Numbers;
import cn.Aurora.api.value.Value;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Bobbing
extends Module {
    private Numbers<Double> boob = new Numbers<Double>("Amount", "Amount", 1.0, 0.1, 5.0, 0.5);

    public Bobbing() {
        super("Bobbing+", new String[]{"bobbing+"}, ModuleType.Player);
        this.addValues(this.boob);
    }

    @EventHandler
    public void onUpdate(EventPreUpdate event) {
        this.setColor(new Color(20, 200, 100).getRGB());
        if (this.mc.thePlayer.onGround) {
            this.mc.thePlayer.cameraYaw = (float)(0.09090908616781235 * this.boob.getValue());
        }
    }
}

