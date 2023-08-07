package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.util.MessageHelper;
import com.mrp_v2.redstonequit.util.ObjectHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneQuitTestBlock extends RedstoneQuitBlockBase {
    public static final String ID = "redstone_quit_test_block";
    public static final String TEST_QUIT_MESSAGE_ID = "test_quit_message";
    public static final TranslatableComponent TEST_QUIT_MESSAGE = MessageHelper.constructTranslation(ID, TEST_QUIT_MESSAGE_ID);

    public RedstoneQuitTestBlock() {
        super(ID);
    }

    @Override
    boolean doBlockActivated(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlock(pos, this.changeBlock(state, ObjectHolder.REDSTONE_QUIT_BLOCK.get()), 1 | 2);
        return true;
    }

    @Override
    void doPlayerAction(Player player, ServerLevel worldIn, BlockPos pos) {
        MessageHelper.sendMessage(player, TEST_QUIT_MESSAGE);
    }
}
