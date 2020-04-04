/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package cn.Aurora.management;

import cn.Aurora.api.EventBus;
import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.misc.EventKey;
import cn.Aurora.api.events.rendering.EventRender2D;
import cn.Aurora.api.events.rendering.EventRender3D;
import cn.Aurora.api.value.Mode;
import cn.Aurora.api.value.Numbers;
import cn.Aurora.api.value.Option;
import cn.Aurora.api.value.Value;
import cn.Aurora.management.FileManager;
import cn.Aurora.management.Manager;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.module.modules.combat.AntiBot;
import cn.Aurora.module.modules.combat.AutoHeal;
import cn.Aurora.module.modules.combat.AutoSword;
import cn.Aurora.module.modules.combat.BowAimBot;
import cn.Aurora.module.modules.combat.Criticals;
import cn.Aurora.module.modules.combat.FastBow;
import cn.Aurora.module.modules.combat.Killaura;
import cn.Aurora.module.modules.combat.Regen;
import cn.Aurora.module.modules.combat.TPAura;
import cn.Aurora.module.modules.movement.Boost;
import cn.Aurora.module.modules.movement.Flight;
import cn.Aurora.module.modules.movement.InvMove;
import cn.Aurora.module.modules.movement.Jesus;
import cn.Aurora.module.modules.movement.Longjump;
import cn.Aurora.module.modules.movement.NoSlow;
import cn.Aurora.module.modules.movement.Scaffold;
import cn.Aurora.module.modules.movement.Sneak;
import cn.Aurora.module.modules.movement.Speed;
import cn.Aurora.module.modules.movement.Sprint;
import cn.Aurora.module.modules.movement.Step;
import cn.Aurora.module.modules.movement.Teleport;
import cn.Aurora.module.modules.player.AntiVelocity;
import cn.Aurora.module.modules.player.Audio;
import cn.Aurora.module.modules.player.AutoAccept;
import cn.Aurora.module.modules.player.Bobbing;
import cn.Aurora.module.modules.player.Dab;
import cn.Aurora.module.modules.player.FastUse;
import cn.Aurora.module.modules.player.Freecam;
import cn.Aurora.module.modules.player.InvCleaner;
import cn.Aurora.module.modules.player.Invplus;
import cn.Aurora.module.modules.player.MCF;
import cn.Aurora.module.modules.player.NoFall;
import cn.Aurora.module.modules.player.NoStrike;
import cn.Aurora.module.modules.player.SkinFlash;
import cn.Aurora.module.modules.player.Teams;
import cn.Aurora.module.modules.player.Zoot;
import cn.Aurora.module.modules.render.Chams;
import cn.Aurora.module.modules.render.ChestESP;
import cn.Aurora.module.modules.render.ESP;
import cn.Aurora.module.modules.render.FullBright;
import cn.Aurora.module.modules.render.HUD;
import cn.Aurora.module.modules.render.Nametags;
import cn.Aurora.module.modules.render.NoRender;
import cn.Aurora.module.modules.render.Tracers;
import cn.Aurora.module.modules.render.Xray;
import cn.Aurora.module.modules.world.AntiVoid;
import cn.Aurora.module.modules.world.AutoArmor;
import cn.Aurora.module.modules.world.Banwave;
import cn.Aurora.module.modules.world.Blink;
import cn.Aurora.module.modules.world.ChestStealer;
import cn.Aurora.module.modules.world.Deathclip;
import cn.Aurora.module.modules.world.FastPlace;
import cn.Aurora.module.modules.world.NoRotate;
import cn.Aurora.module.modules.world.Phase;
import cn.Aurora.module.modules.world.PinCracker;
import cn.Aurora.module.modules.world.PingSpoof;
import cn.Aurora.module.modules.world.SafeWalk;
import cn.Aurora.module.modules.world.StaffAlerts;
import cn.Aurora.utils.render.gl.GLUtils;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;

public class ModuleManager
implements Manager {
    public static List<Module> modules = new ArrayList<Module>();
    private boolean enabledNeededMod = true;
    public boolean nicetry = true;

    @Override
    public void init() {
        modules.add(new HUD());
        modules.add(new Audio());        
        modules.add(new Sprint());
        modules.add(new Killaura());
        modules.add(new AntiVelocity());
        modules.add(new Criticals());
        modules.add(new Speed());
        modules.add(new Longjump());
        modules.add(new Flight());
        modules.add(new NoFall());
        modules.add(new Invplus());
        modules.add(new NoSlow());
        modules.add(new FastBow());
        modules.add(new AntiBot());
        modules.add(new Freecam());
        modules.add(new MCF());
        modules.add(new Nametags());
        modules.add(new Tracers());
        modules.add(new ESP());
        modules.add(new Regen());
        modules.add(new FastPlace());
        modules.add(new NoRender());
        modules.add(new FullBright());
        modules.add(new ChestStealer());
        modules.add(new AutoArmor());
        modules.add(new AntiVoid());
        modules.add(new AutoHeal());
        modules.add(new NoRotate());
        modules.add(new Scaffold());
        modules.add(new Sneak());
        modules.add(new SafeWalk());
        modules.add(new Zoot());
        modules.add(new Jesus());
        modules.add(new Phase());
        modules.add(new Chams());
        modules.add(new Deathclip());
        modules.add(new NoStrike());
        modules.add(new SkinFlash());
        modules.add(new AutoAccept());
        modules.add(new Blink());
        modules.add(new Banwave());
        modules.add(new FastUse());
        modules.add(new PingSpoof());
        modules.add(new BowAimBot());
        modules.add(new Xray());
        modules.add(new ChestESP());
        modules.add(new InvCleaner());
        modules.add(new Step());
        modules.add(new Dab());
        modules.add(new Teleport());
        modules.add(new AutoSword());
        modules.add(new Boost());
        modules.add(new Bobbing());
        modules.add(new PinCracker());
        modules.add(new TPAura());
        modules.add(new StaffAlerts());
        
        modules.add(new InvMove());
        modules.add(new Teams());
        this.readSettings();
        for (Module m : modules) {
            m.makeCommand();
        }
        EventBus.getInstance().register(this);
    }

    public static List<Module> getModules() {
        return modules;
    }

    public Module getModuleByClass(Class<? extends Module> cls) {
        for (Module m : modules) {
            if (m.getClass() != cls) continue;
            return m;
        }
        return null;
    }

    public static Module getModuleByName(String name) {
        for (Module m : modules) {
            if (!m.getName().equalsIgnoreCase(name)) continue;
            return m;
        }
        return null;
    }

    public Module getAlias(String name) {
        for (Module f : modules) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
            String[] alias = f.getAlias();
            int length = alias.length;
            int i = 0;
            while (i < length) {
                String s = alias[i];
                if (s.equalsIgnoreCase(name)) {
                    return f;
                }
                ++i;
            }
        }
        return null;
    }

    public List<Module> getModulesInType(ModuleType t) {
        ArrayList<Module> output = new ArrayList<Module>();
        for (Module m : modules) {
            if (m.getType() != t) continue;
            output.add(m);
        }
        return output;
    }

    @EventHandler
    private void onKeyPress(EventKey e) {
        for (Module m : modules) {
            if (m.getKey() != e.getKey()) continue;
            m.setEnabled(!m.isEnabled());
        }
    }

    @EventHandler
    private void onGLHack(EventRender3D e) {
        GlStateManager.getFloat(2982, (FloatBuffer)GLUtils.MODELVIEW.clear());
        GlStateManager.getFloat(2983, (FloatBuffer)GLUtils.PROJECTION.clear());
        GlStateManager.glGetInteger(2978, (IntBuffer)GLUtils.VIEWPORT.clear());
    }

    @EventHandler
    private void on2DRender(EventRender2D e) {
        if (this.enabledNeededMod) {
            this.enabledNeededMod = false;
            for (Module m : modules) {
                if (!m.enabledOnStartup) continue;
                m.setEnabled(true);
            }
        }
    }

    private void readSettings() {
        List<String> binds = FileManager.read("Binds.txt");
        for (String v : binds) {
            String name = v.split(":")[0];
            String bind = v.split(":")[1];
            Module m = ModuleManager.getModuleByName(name);
            if (m == null) continue;
            m.setKey(Keyboard.getKeyIndex((String)bind.toUpperCase()));
        }
        List<String> enabled = FileManager.read("Enabled.txt");
        for (String v : enabled) {
            Module m = ModuleManager.getModuleByName(v);
            if (m == null) continue;
            m.enabledOnStartup = true;
        }
        List<String> vals = FileManager.read("Values.txt");
        for (String v : vals) {
            String name = v.split(":")[0];
            String values = v.split(":")[1];
            Module m = ModuleManager.getModuleByName(name);
            if (m == null) continue;
            for (Value value : m.getValues()) {
                if (!value.getName().equalsIgnoreCase(values)) continue;
                if (value instanceof Option) {
                    value.setValue(Boolean.parseBoolean(v.split(":")[2]));
                    continue;
                }
                if (value instanceof Numbers) {
                    value.setValue(Double.parseDouble(v.split(":")[2]));
                    continue;
                }
                ((Mode)value).setMode(v.split(":")[2]);
            }
        }
    }
}

