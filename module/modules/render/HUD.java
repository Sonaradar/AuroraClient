/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.render;

import cn.Aurora.Client;
import cn.Aurora.api.AALAPI;
import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.rendering.EventRender2D;
import cn.Aurora.api.events.rendering.EventRenderCape;
import cn.Aurora.api.value.Option;
import cn.Aurora.api.value.Value;
import cn.Aurora.management.FriendManager;
import cn.Aurora.management.ModuleManager;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.module.modules.render.UI.TabUI;
import cn.Aurora.ui.font.CFontRenderer;
import cn.Aurora.ui.font.FontLoaders;
import cn.Aurora.utils.Helper;
import cn.Aurora.utils.math.RotationUtil;
import cn.Aurora.utils.render.RenderUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class HUD
extends Module {
    public TabUI tabui;
    private Option<Boolean> info = new Option<Boolean>("Information", "information", true);
    private Option<Boolean> rainbow = new Option<Boolean>("Rainbow", "rainbow", true);
    private Option<Boolean> customlogo = new Option<Boolean>("Logo", "logo", false);
    private Option<Boolean> customfont = new Option<Boolean>("Font", "font", true);
    private Option<Boolean> capes = new Option<Boolean>("Capes", "capes", true);
    public static boolean shouldMove;
    public static boolean useFont;
    private String[] directions = new String[]{"S", "SW", "W", "NW", "N", "NE", "E", "SE"};

    public HUD() {
        super("HUD", new String[]{"gui"}, ModuleType.Render);
        this.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
        this.setEnabled(true);
        this.setRemoved(true);
        this.addValues(this.rainbow, this.capes);
    }

    @EventHandler
    private void renderHud(EventRender2D event) {
        CFontRenderer font = FontLoaders.kiona24;
            String name;
            String direction;
             this.useFont = true;
             final CFontRenderer cFontRenderer3 = FontLoaders.kiona22;
             final CFontRenderer cFontRenderer4 = FontLoaders.kiona20;
             ///icon
             GlStateManager.disableAlpha();
             GlStateManager.disableBlend();
             RenderUtil.drawCustomImage(4, (int) (new ScaledResolution(this.mc).getScaledHeight() - 12.0 - cFontRenderer3.getStringHeight("X") * 2 ) - 28 , 32, 32, new ResourceLocation("Aurora/logo.png"));		 
             GlStateManager.disableAlpha();
             GlStateManager.disableBlend();
             ///client title
            Client.instance.getClass();
            cFontRenderer3.drawStringWithShadow("A", 4.0, 3.0, new Color(102, 172, 255).getRGB());
            cFontRenderer3.drawStringWithShadow("urora", cFontRenderer3.getStringWidth("A") + 4.0 + 1.0, 3.0, new Color(255, 255, 255).getRGB());    
            ArrayList<Module> sorted = new ArrayList<Module>();
            Client.instance.getModuleManager();
            ///position
            Client info = new Client();
            String text = (Object)((Object)EnumChatFormatting.GRAY) + "X" + (Object)((Object)EnumChatFormatting.WHITE) + ": " + MathHelper.floor_double(this.mc.thePlayer.posX) + " " + (Object)((Object)EnumChatFormatting.GRAY) + "Y" + (Object)((Object)EnumChatFormatting.WHITE) + ": " + MathHelper.floor_double(this.mc.thePlayer.posY) + " " + (Object)((Object)EnumChatFormatting.GRAY) + "Z" + (Object)((Object)EnumChatFormatting.WHITE) + ": " + MathHelper.floor_double(this.mc.thePlayer.posZ);
            cFontRenderer4.drawStringWithShadow( text, 4.0 , new ScaledResolution(this.mc).getScaledHeight() - 12.0 - 1.0, new Color(255, 255, 255).getRGB());
            cFontRenderer3.drawStringWithShadow( info.name + " " + info.version, 4.0 , new ScaledResolution(this.mc).getScaledHeight() - 12.0 - cFontRenderer3.getStringHeight("X") - 2.0 -2.0 , new Color(255, 255, 255).getRGB());
           

            
            for (Module m : ModuleManager.getModules()) {
                if (!m.isEnabled() || m.wasRemoved()) continue;
                sorted.add(m);
            }
            if (useFont) {
                sorted.sort((o1, o2) -> font.getStringWidth(o2.getSuffix().isEmpty() ? o2.getName() : String.format("%s %s", o2.getName(), o2.getSuffix())) - font.getStringWidth(o1.getSuffix().isEmpty() ? o1.getName() : String.format("%s %s", o1.getName(), o1.getSuffix())));
            } else {
                sorted.sort((o1, o2) -> this.mc.fontRendererObj.getStringWidth(o2.getSuffix().isEmpty() ? o2.getName() : String.format("%s %s", o2.getName(), o2.getSuffix())) - this.mc.fontRendererObj.getStringWidth(o1.getSuffix().isEmpty() ? o1.getName() : String.format("%s %s", o1.getName(), o1.getSuffix())));
            }
            int y = 1;
            int rainbowTick = 0;
  
                for (Module m : sorted) {
                    name = m.getSuffix().isEmpty() ? m.getName() : String.format("%s %s", m.getName(), m.getSuffix());
                    float x = RenderUtil.width() - font.getStringWidth(name);
                    Color rainbow = new Color(Color.HSBtoRGB((float)((double)this.mc.thePlayer.ticksExisted / 50.0 + Math.sin((double)rainbowTick / 50.0 * 1.6)) % 1.0f, 0.5f, 1.0f));
                    font.drawStringWithShadow(name, x - 3.0f, y + 1, this.rainbow.getValue() != false ? rainbow.getRGB() : m.getColor());
                    if (++rainbowTick > 50) {
                        rainbowTick = 0;
                    }
                    y += 12;
                }


           
            
        }


    private void drawPotionStatus(ScaledResolution sr) {
        CFontRenderer font = FontLoaders.kiona18;
        int y = 0;
        for (PotionEffect effect : this.mc.thePlayer.getActivePotionEffects()) {
            int ychat;
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            String PType = I18n.format(potion.getName(), new Object[0]);
            switch (effect.getAmplifier()) {
                case 1: {
                    PType = String.valueOf(PType) + " II";
                    break;
                }
                case 2: {
                    PType = String.valueOf(PType) + " III";
                    break;
                }
                case 3: {
                    PType = String.valueOf(PType) + " IV";
                    break;
                }
            }
            if (effect.getDuration() < 600 && effect.getDuration() > 300) {
                PType = String.valueOf(PType) + "\u00a77:\u00a76 " + Potion.getDurationString(effect);
            } else if (effect.getDuration() < 300) {
                PType = String.valueOf(PType) + "\u00a77:\u00a7c " + Potion.getDurationString(effect);
            } else if (effect.getDuration() > 600) {
                PType = String.valueOf(PType) + "\u00a77:\u00a77 " + Potion.getDurationString(effect);
            }
            int n = ychat = this.mc.ingameGUI.getChatGUI().getChatOpen() ? 5 : -10;
            if (useFont) {
                font.drawStringWithShadow(PType, sr.getScaledWidth() - font.getStringWidth(PType) - 2, sr.getScaledHeight() - font.getHeight() + y - 12 - ychat, potion.getLiquidColor());
            } else {
                this.mc.fontRendererObj.drawStringWithShadow(PType, sr.getScaledWidth() - this.mc.fontRendererObj.getStringWidth(PType) - 2, sr.getScaledHeight() - this.mc.fontRendererObj.FONT_HEIGHT + y - 12 - ychat, potion.getLiquidColor());
            }
            y -= 10;
        }
    }

    @EventHandler
    public void onRender(EventRenderCape event) {
        if (this.capes.getValue().booleanValue() && this.mc.theWorld != null && FriendManager.isFriend(event.getPlayer().getName())) {
            event.setLocation(Client.CLIENT_CAPE);
            event.setCancelled(true);
        }
    }
}

