/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.world;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventTick;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import net.minecraft.client.Minecraft;

public class FastPlace
extends Module {
    public FastPlace() {
        super("FastPlace", new String[]{"fplace", "fc"}, ModuleType.World);
        this.setColor(new Color(226, 197, 78).getRGB());
    }

    @EventHandler
    private void onTick(EventTick e) {
        this.mc.rightClickDelayTimer = 0;
    }
}

