package com.mrp_v2.redstonequit.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RedstoneQuitBlock extends Block {

	public RedstoneQuitBlock(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
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
