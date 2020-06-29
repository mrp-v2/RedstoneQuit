package com.mrp_v2.redstonequit.block;

import java.util.Random;

import com.mrp_v2.redstonequit.RedstoneQuit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

abstract public class RedstoneQuitBlockBase extends Block {

	public RedstoneQuitBlockBase(String blockID) {
		super(Properties.create(Material.IRON, MaterialColor.IRON).harvestTool(ToolType.PICKAXE));
		this.setRegistryName(RedstoneQuit.MODID, blockID);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (worldIn.isBlockPowered(pos)) {
			blockPowered(worldIn.getRedstonePowerFromNeighbors(pos));
		}
	}

	abstract protected void blockPowered(int redstonePower);
}
