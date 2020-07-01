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
public class RedstoneQuitRegistryHandler {

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitBlock.ID)
	public static final RedstoneQuitBlock REDSTONE_QUIT_BLOCK = null;

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitBlock.ID)
	public static final Item REDSTONE_QUIT_BLOCK_ITEM = null;

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitTestBlock.ID)
	public static final RedstoneQuitTestBlock REDSTONE_QUIT_TEST_BLOCK = null;

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitTestBlock.ID)
	public static final Item REDSTONE_QUIT_TEST_BLOCK_ITEM = null;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(new RedstoneQuitBlock(), new RedstoneQuitTestBlock());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(REDSTONE_QUIT_BLOCK.createBlockItem(),
				REDSTONE_QUIT_TEST_BLOCK.createBlockItem());
	}
}
