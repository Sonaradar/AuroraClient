/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora;

import cn.Aurora.api.value.Value;
import cn.Aurora.management.CommandManager;
import cn.Aurora.management.FileManager;
import cn.Aurora.management.FriendManager;
import cn.Aurora.management.ModuleManager;
import cn.Aurora.module.Module;
import cn.Aurora.module.modules.render.UI.TabUI;
import cn.Aurora.ui.login.AltManager;
import java.io.PrintStream;
import java.time.OffsetDateTime;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public class Client {
    public final String name = "Aurora";
    public final double version = 1.2;
    public static boolean publicMode = false;
    public static Client instance = new Client();
    public final String copyright = "2019-2020 WaterFish Software Co.Ltd.";
    public final String mainpagesubtitle = "Hacking with Aurora Client"; ///plz not too long!
    public final String saying = "Hacking with Aurora Client maybe a good choice!";
    public final String onlinestore= "xxx.maikama.cn";
    private ModuleManager modulemanager;
    private CommandManager commandmanager;
    private AltManager altmanager;
    private FriendManager friendmanager;
    private TabUI tabui;
    public static ResourceLocation CLIENT_CAPE = new ResourceLocation("Aurora/cape.png");

    public void initiate() {
        this.commandmanager = new CommandManager();
        this.commandmanager.init();
        this.friendmanager = new FriendManager();
        this.friendmanager.init();
        this.modulemanager = new ModuleManager();
        this.modulemanager.init();
        this.tabui = new TabUI();
        this.tabui.init();
        this.altmanager = new AltManager();
        AltManager.init();
        AltManager.setupAlts();
        FileManager.init();
    }

    public ModuleManager getModuleManager() {
        return this.modulemanager;
    }

    public CommandManager getCommandManager() {
        return this.commandmanager;
    }

    public AltManager getAltManager() {
        return this.altmanager;
    }

    public void shutDown() {
        String values = "";
        instance.getModuleManager();
        for (Module m : ModuleManager.getModules()) {
            for (Value v : m.getValues()) {
                values = String.valueOf(values) + String.format("%s:%s:%s%s", m.getName(), v.getName(), v.getValue(), System.lineSeparator());
            }
        }
        FileManager.save("Values.txt", values, false);
        String enabled = "";
        instance.getModuleManager();
        for (Module m : ModuleManager.getModules()) {
            if (!m.isEnabled()) continue;
            enabled = String.valueOf(enabled) + String.format("%s%s", m.getName(), System.lineSeparator());
        }
        FileManager.save("Enabled.txt", enabled, false);
    }
}

