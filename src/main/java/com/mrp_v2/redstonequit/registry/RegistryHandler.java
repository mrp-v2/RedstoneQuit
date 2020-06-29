package com.mrp_v2.redstonequit.registry;

import com.mrp_v2.redstonequit.RedstoneQuit;
import com.mrp_v2.redstonequit.block.RedstoneQuitBlock;

import net.minecraft.block.Block;
//import net.minecraft.item.BlockItem;
//import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = RedstoneQuit.MODID)
public class RegistryHandler {
	
	private static final Block redstoneQuitBlock = new RedstoneQuitBlock();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(redstoneQuitBlock);
	}

	/*@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new BlockItem(redstoneQuitBlock).setRegistryName(redstoneQuitBlock.getRegistryName()));
	}*/
}
