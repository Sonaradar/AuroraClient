/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.render;

import cn.Aurora.Client;
import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.rendering.EventRenderCape;
import cn.Aurora.management.FriendManager;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class Capes
extends Module {
    public Capes() {
        super("Capes", new String[]{"kape"}, ModuleType.Render);
        this.setColor(new Color(159, 190, 192).getRGB());
        this.setEnabled(true);
        this.setRemoved(true);
    }

    @EventHandler
    public void onRender(EventRenderCape event) {
        if (this.mc.theWorld != null && FriendManager.isFriend(event.getPlayer().getName())) {
            event.setLocation(Client.CLIENT_CAPE);
            event.setCancelled(true);
        }
    }
}

