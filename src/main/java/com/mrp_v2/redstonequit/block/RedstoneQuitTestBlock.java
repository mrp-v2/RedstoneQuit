package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.util.MessageHelper;
import com.mrp_v2.redstonequit.util.ObjectHolder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RedstoneQuitTestBlock extends RedstoneQuitBlockBase
{
    public static final String ID = "redstone_quit_test_block";

    public RedstoneQuitTestBlock()
    {
        super(ID);
    }

    @Override boolean doBlockActivated(BlockState state, World worldIn, BlockPos pos)
    {
        worldIn.setBlock(pos, this.changeBlock(state, ObjectHolder.REDSTONE_QUIT_BLOCK.get()), 1 | 2);
        return true;
    }

    @Override void doPlayerAction(PlayerEntity player, ServerWorld worldIn, BlockPos pos)
    {
        MessageHelper.sendTranslatedMessage(player, ID, "test_quit_message");
    }
}
