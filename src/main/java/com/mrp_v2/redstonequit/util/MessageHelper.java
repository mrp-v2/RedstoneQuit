package com.mrp_v2.redstonequit.util;

import com.mrp_v2.redstonequit.RedstoneQuit;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

public class MessageHelper
{
    public static void sendTranslatedMessage(Entity player, String originID, String messageID)
    {
        sendMessage(player, constructTranslation(originID, messageID));
    }

    public static void sendMessage(Entity player, Component messageTranslation)
    {
        player.sendMessage(messageTranslation, UUID.randomUUID());
    }

    public static TranslatableComponent constructTranslation(String originID, String messageID)
    {
        return new TranslatableComponent(RedstoneQuit.ID + "." + originID + "." + messageID);
    }
}
