package com.mrp_v2.redstonequit;

import com.mrp_v2.redstonequit.config.ConfigOptions;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(RedstoneQuit.MODID)
public class RedstoneQuit {

	public static final String MODID = "redstonequit";
	public static final String TRANSLATION_KEY_STEM = "mrp_v2." + MODID + ".";

	public RedstoneQuit() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigOptions.serverSpec);
	}
}
