/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.render;

import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;

public class NoRender
extends Module {
    public NoRender() {
        super("NoRender", new String[]{"noitems"}, ModuleType.Render);
        this.setColor(new Color(166, 185, 123).getRGB());
    }
}

