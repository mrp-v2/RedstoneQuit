package com.mrp_v2.redstonequit.registry;

import com.mrp_v2.redstonequit.RedstoneQuit;
import com.mrp_v2.redstonequit.block.RedstoneQuitBlock;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = RedstoneQuit.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistryHandler {

	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitBlock.ID)
	public static final Block redstoneQuitBlock = null;
	@ObjectHolder(RedstoneQuit.MODID + ":" + RedstoneQuitBlock.ID)
	public static final Item redstoneQuitBlockItem = null;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new RedstoneQuitBlock());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry()
				.register(new BlockItem(redstoneQuitBlock,
						new Item.Properties().addToolType(ToolType.PICKAXE, 1).group(ItemGroup.REDSTONE))
								.setRegistryName(RedstoneQuit.MODID, RedstoneQuitBlock.ID));
	}
}
