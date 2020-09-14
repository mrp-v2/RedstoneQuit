package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.registry.RegistryHandler;
import com.mrp_v2.redstonequit.util.MessageHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RedstoneQuitBlock extends RedstoneQuitBlockBase
{
    public static final String ID = "redstone_quit_block";

    public RedstoneQuitBlock()
    {
        super(ID);
    }

    @Override boolean doBlockActivated(BlockState state, World worldIn, BlockPos pos)
    {
        worldIn.setBlockState(pos, this.changeBlock(state, RegistryHandler.REDSTONE_QUIT_TEST_BLOCK), 1 | 2);
        return true;
    }

    @Override public void doPlayerAction(PlayerEntity player, ServerWorld worldIn, BlockPos pos)
    {
        if (!worldIn.isRemote)
        {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            serverPlayer.connection.disconnect(MessageHelper.constructTranslation(ID, "disconnect_message"));
        }
    }
}
