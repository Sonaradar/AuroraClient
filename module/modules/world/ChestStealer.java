/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.world;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventTick;
import cn.Aurora.api.value.Numbers;
import cn.Aurora.api.value.Value;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.utils.TimerUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChestStealer
extends Module {
    private Numbers<Double> delay = new Numbers<Double>("Delay", "delay", 50.0, 0.0, 1000.0, 10.0);
    private TimerUtil timer = new TimerUtil();

    public ChestStealer() {
        super("ChestStealer", new String[]{"cheststeal", "chests", "stealer"}, ModuleType.World);
        this.addValues(this.delay);
        this.setColor(new Color(218, 97, 127).getRGB());
    }

    @EventHandler
    private void onUpdate(EventTick event) {
        if (this.mc.thePlayer.openContainer != null && this.mc.thePlayer.openContainer instanceof ContainerChest) {
            ContainerChest container = (ContainerChest)this.mc.thePlayer.openContainer;
            int i = 0;
            while (i < container.getLowerChestInventory().getSizeInventory()) {
                if (container.getLowerChestInventory().getStackInSlot(i) != null && this.timer.hasReached(this.delay.getValue())) {
                    this.mc.playerController.windowClick(container.windowId, i, 0, 1, this.mc.thePlayer);
                    this.timer.reset();
                }
                ++i;
            }
            if (this.isEmpty()) {
                this.mc.thePlayer.closeScreen();
            }
        }
    }

    private boolean isEmpty() {
        if (this.mc.thePlayer.openContainer != null && this.mc.thePlayer.openContainer instanceof ContainerChest) {
            ContainerChest container = (ContainerChest)this.mc.thePlayer.openContainer;
            int i = 0;
            while (i < container.getLowerChestInventory().getSizeInventory()) {
                ItemStack itemStack = container.getLowerChestInventory().getStackInSlot(i);
                if (itemStack != null && itemStack.getItem() != null) {
                    return false;
                }
                ++i;
            }
        }
        return true;
    }
}

