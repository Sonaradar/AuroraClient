/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.world;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.utils.TimerUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.AxisAlignedBB;

public class Deathclip
extends Module {
    private TimerUtil timer = new TimerUtil();

    public Deathclip() {
        super("DeathClip", new String[]{"deathc", "dc"}, ModuleType.World);
        this.setColor(new Color(157, 58, 157).getRGB());
    }

    @EventHandler
    private void onUpdate(EventPreUpdate e) {
        if (this.mc.thePlayer.getHealth() == 0.0f && this.mc.thePlayer.onGround) {
            this.mc.thePlayer.boundingBox.offsetAndUpdate(this.mc.thePlayer.posX, -10.0, this.mc.thePlayer.posZ);
            if (this.timer.hasReached(500.0)) {
                this.mc.thePlayer.sendChatMessage("/home");
            }
        }
    }
}

