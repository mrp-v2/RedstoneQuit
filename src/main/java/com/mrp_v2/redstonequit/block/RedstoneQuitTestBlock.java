package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.registry.RegistryHandler;
import com.mrp_v2.redstonequit.util.MessageHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RedstoneQuitTestBlock extends RedstoneQuitBlockBase {

	public static final String ID = "redstone_quit_test_block";

	public static final String TEST_QUIT_MESSAGE = "(Test Quit Block) You have been kicked!";

	public RedstoneQuitTestBlock() {
		super(ID);
	}

	@Override
	void doPlayerAction(PlayerEntity player, ServerWorld worldIn, BlockPos pos) {
		MessageHelper.SendMessage(player, TEST_QUIT_MESSAGE);
	}

	@Override
	boolean doBlockActivated(World worldIn, BlockPos pos) {
		worldIn.setBlockState(pos, RegistryHandler.REDSTONE_QUIT_BLOCK.getDefaultState(), 1 | 2);
		return true;
	}
}
