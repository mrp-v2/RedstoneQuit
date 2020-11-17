package com.mrp_v2.redstonequit;

import com.mrp_v2.redstonequit.config.ServerConfig;
import com.mrp_v2.redstonequit.util.ObjectHolder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RedstoneQuit.ID) public class RedstoneQuit
{
    public static final String ID = "redstone" + "quit";

    public RedstoneQuit()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.serverSpec);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.register(ServerConfig.class);
        ObjectHolder.registerListeners(modEventBus);
    }
}
