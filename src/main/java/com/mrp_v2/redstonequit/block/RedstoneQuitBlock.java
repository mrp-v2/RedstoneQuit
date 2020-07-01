package com.mrp_v2.redstonequit.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class RedstoneQuitBlock extends RedstoneQuitBlockBase {

	public static final String ID = "redstone_quit_block";

	public RedstoneQuitBlock() {
		super(ID);
	}

	@Override
	void doPlayerAction(PlayerEntity player, ServerWorld worldIn, BlockPos pos) {
		// TODO Auto-generated method stub
		
	}
}
