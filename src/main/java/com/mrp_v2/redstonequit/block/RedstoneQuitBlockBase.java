package com.mrp_v2.redstonequit.block;

import java.util.ArrayList;
import java.util.Random;

import com.mrp_v2.redstonequit.RedstoneQuit;
import com.mrp_v2.redstonequit.config.ConfigOptions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

abstract public class RedstoneQuitBlockBase extends Block {

	private static final ToolType tool = ToolType.PICKAXE;

	public RedstoneQuitBlockBase(String blockID) {
		super(Properties.create(Material.IRON, MaterialColor.IRON).harvestTool(tool));
		this.setRegistryName(RedstoneQuit.MODID, blockID);
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		if (!worldIn.isRemote) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 0);
		}
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (worldIn.isBlockPowered(pos)) {
			blockPowered(worldIn.getRedstonePowerFromNeighbors(pos), worldIn, pos);
		}
	}

	public Item createBlockItem() {
		return new BlockItem(this, new Item.Properties().addToolType(tool, 1).group(ItemGroup.REDSTONE))
				.setRegistryName(this.getRegistryName());
	}

	// Even numbers are all,
	// Odds are nearest
	protected void blockPowered(int redstonePower, ServerWorld worldIn, BlockPos pos) {
		if (redstonePower <= 0 || redstonePower >= 15) {
			return;
		}
		int range = ConfigOptions.getRangeForStrength(redstonePower);
		if (redstonePower % 2 == 0) {
			for (PlayerEntity pe2 : getNearbyPlayers(worldIn, pos, range)) {
				doPlayerAction(pe2, worldIn, pos);
			}
		} else {
			PlayerEntity pe1 = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), range, null);
			if (pe1 != null && playerWithinRange(pos, pe1, range)) {
				doPlayerAction(pe1, worldIn, pos);
			}
		}
	}

	abstract void doPlayerAction(PlayerEntity player, ServerWorld worldIn, BlockPos pos);

	private ArrayList<PlayerEntity> getNearbyPlayers(ServerWorld worldIn, BlockPos pos, double range) {
		ArrayList<PlayerEntity> nearbyPlayers = new ArrayList<PlayerEntity>();
		for (PlayerEntity pe : worldIn.getPlayers()) {
			if (playerWithinRange(pos, pe, range)) {
				nearbyPlayers.add(pe);
			}
		}
		return nearbyPlayers;
	}

	private boolean playerWithinRange(BlockPos pos, PlayerEntity player, double range) {
		if (range < 0) {
			return true;
		}
		if (range == 0) {
			return false;
		}
		return pos.withinDistance(player.getPositionVec(), range);
	}
}
