/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.player;

import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import java.util.Random;

public class NoStrike
extends Module {
    public NoStrike() {
        super("NoStrike", new String[]{"antistrike"}, ModuleType.Player);
        this.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
    }
}

