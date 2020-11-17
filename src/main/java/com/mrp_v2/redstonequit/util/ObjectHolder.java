package com.mrp_v2.redstonequit.util;

import com.mrp_v2.redstonequit.RedstoneQuit;
import com.mrp_v2.redstonequit.block.RedstoneQuitBlock;
import com.mrp_v2.redstonequit.block.RedstoneQuitTestBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = RedstoneQuit.ID, bus = EventBusSubscriber.Bus.MOD) public class ObjectHolder
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RedstoneQuit.ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RedstoneQuit.ID);
    public static final RegistryObject<RedstoneQuitBlock> REDSTONE_QUIT_BLOCK;
    public static final RegistryObject<Item> REDSTONE_QUIT_BLOCK_ITEM;
    public static final RegistryObject<RedstoneQuitTestBlock> REDSTONE_QUIT_TEST_BLOCK;
    public static final RegistryObject<Item> REDSTONE_QUIT_TEST_BLOCK_ITEM;

    static
    {
        REDSTONE_QUIT_BLOCK = BLOCKS.register(RedstoneQuitBlock.ID, RedstoneQuitBlock::new);
        REDSTONE_QUIT_TEST_BLOCK = BLOCKS.register(RedstoneQuitTestBlock.ID, RedstoneQuitTestBlock::new);
        REDSTONE_QUIT_BLOCK_ITEM =
                ITEMS.register(RedstoneQuitBlock.ID, () -> REDSTONE_QUIT_BLOCK.get().createBlockItem());
        REDSTONE_QUIT_TEST_BLOCK_ITEM =
                ITEMS.register(RedstoneQuitTestBlock.ID, () -> REDSTONE_QUIT_TEST_BLOCK.get().createBlockItem());
    }

    public static void registerListeners(IEventBus bus)
    {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
