/*
 * Decompiled with CFR 0_132.
 */
package cn.Aurora.api.events.world;

import cn.Aurora.api.Event;
import net.minecraft.network.Packet;

public class EventPacketSend
extends Event {
    private Packet packet;

    public EventPacketSend(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}

