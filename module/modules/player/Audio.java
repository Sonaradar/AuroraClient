package cn.Aurora.module.modules.player;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import cn.Aurora.api.events.world.EventTick;
import cn.Aurora.api.value.Option;
import cn.Aurora.management.FileManager;
import cn.Aurora.utils.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;


import cn.Aurora.api.EventHandler;
import cn.Aurora.api.events.world.EventPreUpdate;
import cn.Aurora.management.FriendManager;
import cn.Aurora.module.Module;
import cn.Aurora.module.ModuleType;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.core.config.plugins.ResolverUtil.ClassTest;
import org.lwjgl.input.Mouse;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.spi.AudioFileReader;

import sun.audio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Audio

extends Module {

    public Audio() {
        super("Audio", new String[]{"MusicPlayer"}, ModuleType.Player);
        this.setColor(new Color(241, 175, 67).getRGB());
        this.setSuffix("AccelWorld");
    } 
	AudioClip ac;
	FileInputStream file;
	BufferedInputStream buf;
	
	@SuppressWarnings("restriction")
	public void onEnable() {

		{
			try
			{
			file = new FileInputStream(this.getClass().getResource("/assets/minecraft/Aurora/Music.mid").getPath());
			buf = new BufferedInputStream(file);
			AudioStream audio= new AudioStream(buf);
			AudioPlayer.player.start(audio);
			AudioPlayer.player.isInterrupted();
			}
			catch (Exception e) {}
		}
	}
    @SuppressWarnings("restriction")
    public void onTick() {
    	if (AudioPlayer.player.isInterrupted()==true) {
    		onEnable();
    	}
    }
	public void onDisable() {
    	AudioPlayer.player.stop();
    }

}



