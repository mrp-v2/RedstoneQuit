package com.mrp_v2.redstonequit.util;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.util.text.StringTextComponent;

public class MessageHelper {

	public static void SendMessage(Entity player, String message) {
		player.sendMessage(new StringTextComponent(message), UUID.randomUUID());
	}
}
