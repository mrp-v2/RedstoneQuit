package com.mrp_v2.redstonequit.registry;

import com.mrp_v2.redstonequit.RedstoneQuit;
import com.mrp_v2.redstonequit.block.RedstoneQuitBlock;
import com.mrp_v2.redstonequit.block.RedstoneQuitTestBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = RedstoneQuit.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitBlock.ID)
	public static final RedstoneQuitBlock redstoneQuitBlock = null;

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitBlock.ID)
	public static final Item redstoneQuitBlockItem = null;

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitTestBlock.ID)
	public static final RedstoneQuitTestBlock redstoneQuitTestBlock = null;

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitTestBlock.ID)
	public static final Item redstoneQuitTestBlockItem = null;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(new RedstoneQuitBlock(), new RedstoneQuitTestBlock());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(redstoneQuitBlock.createBlockItem(), redstoneQuitTestBlock.createBlockItem());
	}
}
