/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.module.modules.combat;

import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventTick;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.utils.TimerUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;

public class TPAura
extends Module {
    private int ticks;
    private List<EntityLivingBase> loaded = new ArrayList<EntityLivingBase>();
    private EntityLivingBase target;
    private int tpdelay;
    public boolean criticals;
    private TimerUtil timer = new TimerUtil();

    public TPAura() {
        super("TPAura", new String[]{"tpaura"}, ModuleType.Combat);
    }

    @EventHandler
    public void onUpdate(EventTick event) {
        this.setColor(new Color(255, 50, 70).getRGB());
        ++this.ticks;
        ++this.tpdelay;
        if (this.ticks >= 20 - this.speed()) {
            this.ticks = 0;
            for (Object object : this.mc.theWorld.loadedEntityList) {
                EntityLivingBase entity;
                if (!(object instanceof EntityLivingBase) || (entity = (EntityLivingBase)object) instanceof EntityPlayerSP || this.mc.thePlayer.getDistanceToEntity(entity) > 10.0f || !entity.isEntityAlive()) continue;
                if (this.tpdelay >= 4) {
                    this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(entity.posX, entity.posY, entity.posZ, false));
                }
                if (this.mc.thePlayer.getDistanceToEntity(entity) >= 10.0f) continue;
                this.attack(entity);
            }
        }
    }

    public void attack(EntityLivingBase entity) {
        this.attack(entity, false);
    }

    public void attack(EntityLivingBase entity, boolean crit) {
        this.mc.thePlayer.swingItem();
        float sharpLevel = EnchantmentHelper.func_152377_a(this.mc.thePlayer.getHeldItem(), entity.getCreatureAttribute());
        boolean vanillaCrit = this.mc.thePlayer.fallDistance > 0.0f && !this.mc.thePlayer.onGround && !this.mc.thePlayer.isOnLadder() && !this.mc.thePlayer.isInWater() && !this.mc.thePlayer.isPotionActive(Potion.blindness) && this.mc.thePlayer.ridingEntity == null;
        this.mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity((Entity)entity, C02PacketUseEntity.Action.ATTACK));
        if (crit || vanillaCrit) {
            this.mc.thePlayer.onCriticalHit(entity);
        }
        if (sharpLevel > 0.0f) {
            this.mc.thePlayer.onEnchantmentCritical(entity);
        }
    }

    private int speed() {
        return 8;
    }
}

