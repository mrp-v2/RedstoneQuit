package com.mrp_v2.redstonequit.block;

import java.util.Random;

import com.mrp_v2.redstonequit.RedstoneQuit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

abstract public class RedstoneQuitBlockBase extends Block {

	private static final ToolType tool = ToolType.PICKAXE;

	public RedstoneQuitBlockBase(String blockID) {
		super(Properties.create(Material.IRON, MaterialColor.IRON).harvestTool(tool));
		this.setRegistryName(RedstoneQuit.MODID, blockID);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (worldIn.isBlockPowered(pos)) {
			blockPowered(worldIn.getRedstonePowerFromNeighbors(pos));
		}
	}

	public Item createBlockItem() {
		return new BlockItem(this, new Item.Properties().addToolType(tool, 1).group(ItemGroup.REDSTONE))
				.setRegistryName(this.getRegistryName());
	}

	abstract protected void blockPowered(int redstonePower);
}
