package com.mrp_v2.redstonequit.util;

import com.mrp_v2.redstonequit.RedstoneQuit;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.UUID;

public class MessageHelper
{
    public static void sendTranslatedMessage(Entity player, String originID, String messageID)
    {
        sendMessage(player, constructTranslation(originID, messageID));
    }

    public static void sendMessage(Entity player, ITextComponent messageTranslation)
    {
        player.sendMessage(messageTranslation, UUID.randomUUID());
    }

    public static TranslationTextComponent constructTranslation(String originID, String messageID)
    {
        return new TranslationTextComponent(RedstoneQuit.ID + "." + originID + "." + messageID);
    }
}
