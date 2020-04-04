/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.movement;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.api.value.Numbers;
import cn.Aurora.api.value.Option;
import cn.Aurora.api.value.Value;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.Timer;

public class Step
extends Module {
    private Numbers<Double> height = new Numbers<Double>("Height", "height", 1.0, 1.0, 10.0, 0.5);
    private Option<Boolean> ncp = new Option<Boolean>("NCP", "ncp", false);

    public Step() {
        super("Step", new String[]{"autojump"}, ModuleType.Movement);
        this.setColor(new Color(165, 238, 65).getRGB());
        this.addValues(this.ncp);
    }

    @Override
    public void onDisable() {
        this.mc.thePlayer.stepHeight = 0.6f;
    }

    @EventHandler
    private void onUpdate(EventPreUpdate e) {
        if (this.ncp.getValue().booleanValue()) {
            this.mc.thePlayer.stepHeight = 0.6f;
            if (this.mc.thePlayer.isCollidedHorizontally && this.mc.thePlayer.onGround && this.mc.thePlayer.isCollidedVertically && this.mc.thePlayer.isCollided && this.mc.thePlayer.isCollidedHorizontally && this.mc.thePlayer.onGround && this.mc.thePlayer.isCollidedVertically && this.mc.thePlayer.isCollided) {
                this.mc.thePlayer.setSprinting(true);
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 0.42, this.mc.thePlayer.posZ, this.mc.thePlayer.onGround));
                this.mc.thePlayer.setSprinting(true);
                this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 0.753, this.mc.thePlayer.posZ, this.mc.thePlayer.onGround));
                this.mc.thePlayer.setSprinting(true);
                this.mc.thePlayer.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 0.42, this.mc.thePlayer.posZ);
                this.mc.timer.timerSpeed = 0.5f;
                this.mc.thePlayer.setSprinting(true);
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(100L);
                        }
                        catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        Step.this.mc.timer.timerSpeed = 1.0f;
                    }
                }).start();
            }
        } else {
            this.mc.thePlayer.stepHeight = 1.0f;
        }
    }

}

