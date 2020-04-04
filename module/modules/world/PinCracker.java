/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.world;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.api.value.Numbers;
import cn.Aurora.api.value.Option;
import cn.Aurora.api.value.Value;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.utils.TimerUtil;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class PinCracker
extends Module {
    private TimerUtil time = new TimerUtil();
    int num;
    private Option<Boolean> login = new Option<Boolean>("/login?", "login", false);
    private Numbers<Double> delay = new Numbers<Double>("Delay", "Delay", 1.0, 0.0, 20.0, 1.0);

    public PinCracker() {
        super("PinCracker", new String[]{"pincracker"}, ModuleType.World);
        this.addValues(this.login, this.delay);
    }

    @EventHandler
    public void onUpdate(EventPreUpdate event) {
        this.setColor(new Color(200, 200, 100).getRGB());
        if (this.login.getValue().booleanValue()) {
            if (this.time.delay((float)(this.delay.getValue() * 100.0))) {
                this.mc.thePlayer.sendChatMessage("/login " + this.numbers());
                this.time.reset();
            }
        } else if (this.time.delay((float)(this.delay.getValue() * 100.0))) {
            this.mc.thePlayer.sendChatMessage("/pin " + this.numbers());
            this.time.reset();
        }
    }

    private int numbers() {
        if (this.num <= 10000) {
            ++this.num;
        }
        return this.num;
    }

    @Override
    public void onDisable() {
        this.num = 0;
        super.onDisable();
    }
}

