/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.movement;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.misc.EventCollideWithBlock;
import cn.Aurora.api.events.world.EventPacketSend;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class Jesus
extends Module {
    public Jesus() {
        super("Jesus", new String[]{"waterwalk", "float"}, ModuleType.Movement);
        this.setColor(new Color(188, 233, 248).getRGB());
    }

    private boolean canJeboos() {
        if (!(this.mc.thePlayer.fallDistance >= 3.0f || this.mc.gameSettings.keyBindJump.isPressed() || BlockHelper.isInLiquid() || this.mc.thePlayer.isSneaking())) {
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPre(EventPreUpdate e) {
        if (BlockHelper.isInLiquid() && !this.mc.thePlayer.isSneaking() && !this.mc.gameSettings.keyBindJump.isPressed()) {
            this.mc.thePlayer.motionY = 0.05;
            this.mc.thePlayer.onGround = true;
        }
    }

    @EventHandler
    public void onPacket(EventPacketSend e) {
        if (e.getPacket() instanceof C03PacketPlayer && this.canJeboos() && BlockHelper.isOnLiquid()) {
            C03PacketPlayer packet = (C03PacketPlayer)e.getPacket();
            packet.y = this.mc.thePlayer.ticksExisted % 2 == 0 ? packet.y + 0.01 : packet.y - 0.01;
        }
    }

    @EventHandler
    public void onBB(EventCollideWithBlock e) {
        if (e.getBlock() instanceof BlockLiquid && this.canJeboos()) {
            e.setBoundingBox(new AxisAlignedBB(e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), (double)e.getPos().getX() + 1.0, (double)e.getPos().getY() + 1.0, (double)e.getPos().getZ() + 1.0));
        }
    }
}

