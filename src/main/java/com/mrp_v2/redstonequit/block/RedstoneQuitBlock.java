package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.util.MessageHelper;
import com.mrp_v2.redstonequit.util.ObjectHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneQuitBlock extends RedstoneQuitBlockBase
{
    public static final String ID = "redstone_quit_block";

    public RedstoneQuitBlock()
    {
        super(ID);
    }

    @Override
    boolean doBlockActivated(BlockState state, Level worldIn, BlockPos pos)
    {
        worldIn.setBlock(pos, this.changeBlock(state, ObjectHolder.REDSTONE_QUIT_TEST_BLOCK.get()), 1 | 2);
        return true;
    }

    @Override
    public void doPlayerAction(Player player, ServerLevel worldIn, BlockPos pos)
    {
        if (!worldIn.isClientSide)
        {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.connection.disconnect(MessageHelper.constructTranslation(ID, "disconnect_message"));
        }
    }
}
