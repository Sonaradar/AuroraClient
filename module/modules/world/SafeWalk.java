/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.world;

import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;

public class SafeWalk
extends Module {
    public SafeWalk() {
        super("SafeWalk", new String[]{"eagle", "parkour"}, ModuleType.World);
        this.setColor(new Color(198, 253, 191).getRGB());
    }
}

