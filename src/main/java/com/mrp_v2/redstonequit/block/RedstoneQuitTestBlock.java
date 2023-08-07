package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.util.MessageHelper;
import com.mrp_v2.redstonequit.util.ObjectHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneQuitTestBlock extends RedstoneQuitBlockBase
{
    public static final String ID = "redstone_quit_test_block";

    public RedstoneQuitTestBlock()
    {
        super(ID);
    }

    @Override
    boolean doBlockActivated(BlockState state, Level worldIn, BlockPos pos)
    {
        worldIn.setBlock(pos, this.changeBlock(state, ObjectHolder.REDSTONE_QUIT_BLOCK.get()), 1 | 2);
        return true;
    }

    @Override
    void doPlayerAction(Player player, ServerLevel worldIn, BlockPos pos)
    {
        MessageHelper.sendTranslatedMessage(player, ID, "test_quit_message");
    }
}
