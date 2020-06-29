package com.mrp_v2.redstonequit.block;

import java.util.Random;

import com.mrp_v2.redstonequit.RedstoneQuit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RedstoneQuitBlock extends Block {
	
	public static final String ID = "redstoneQuitBlock";

	public RedstoneQuitBlock() {
		super(Properties.create(Material.IRON, MaterialColor.IRON));
		this.setRegistryName(RedstoneQuit.MODID, ID);
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		if (!worldIn.isRemote) {
			if (worldIn.isBlockPowered(pos)) {
				quitCheck(worldIn.getRedstonePowerFromNeighbors(pos));
			}
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (worldIn.isBlockPowered(pos)) {
			quitCheck(worldIn.getRedstonePowerFromNeighbors(pos));
		}
	}

	protected void quitCheck(int redstonePower) {

	}
}
