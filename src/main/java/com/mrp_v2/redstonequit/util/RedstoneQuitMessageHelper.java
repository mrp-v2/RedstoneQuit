package com.mrp_v2.redstonequit.util;

import java.util.UUID;

import com.mrp_v2.redstonequit.RedstoneQuit;

import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class RedstoneQuitMessageHelper {

	public static void sendMessage(Entity player, ITextComponent messageTranslation) {
		player.sendMessage(messageTranslation, UUID.randomUUID());
	}

	public static void sendTranslatedMessage(Entity player, String originID, String messageID) {
		sendMessage(player, constructTranslation(originID, messageID));
	}

	public static TranslationTextComponent constructTranslation(String originID, String messageID) {
		return new TranslationTextComponent(RedstoneQuit.TRANSLATION_KEY_STEM + originID + "." + messageID);
	}
}
