package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.Random;

abstract public class RedstoneQuitBlockBase extends Block
{
    private static final ToolType TOOL_TYPE = ToolType.PICKAXE;

    public RedstoneQuitBlockBase(String blockID)
    {
        super(Properties.of(Material.METAL, MaterialColor.METAL).harvestTool(TOOL_TYPE));
        this.registerDefaultState(
                this.stateDefinition.any().setValue(BlockStateProperties.POWER, Integer.valueOf(0)));
    }

    protected BlockState changeBlock(BlockState oldState, RedstoneQuitBlockBase newBlock)
    {
        return newBlock.defaultBlockState()
                .setValue(BlockStateProperties.POWER, oldState.getValue(BlockStateProperties.POWER));
    }

    public BlockItem createBlockItem()
    {
        return new BlockItem(this, new Item.Properties().addToolType(TOOL_TYPE, 1).tab(CreativeModeTab.TAB_REDSTONE));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        builder.add(BlockStateProperties.POWER);
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
            boolean isMoving)
    {
        if (!worldIn.isClientSide)
        {
            worldIn.getBlockTicks().scheduleTick(pos, this, 0);
        }
    }

    @Override
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        worldIn.getBlockTicks().scheduleTick(pos, this, 0);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
                                 InteractionHand handIn, BlockHitResult hit)
    {
        return worldIn.isClientSide ?
                InteractionResult.SUCCESS :
                doBlockActivated(state, worldIn, pos) ? InteractionResult.CONSUME : InteractionResult.PASS;
    }

    abstract boolean doBlockActivated(BlockState state, Level worldIn, BlockPos pos);

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand)
    {
        blockPowered(worldIn.getBestNeighborSignal(pos), worldIn, pos);
    }

    // Even numbers are all,
    // Odds are nearest
    protected void blockPowered(int redstonePower, ServerLevel worldIn, BlockPos pos)
    {
        if (redstonePower == worldIn.getBlockState(pos).getValue(BlockStateProperties.POWER).intValue())
        {
            return;
        }
        worldIn.setBlock(pos,
                this.defaultBlockState().setValue(BlockStateProperties.POWER, Integer.valueOf(redstonePower)), 1 | 2);
        if (redstonePower <= 0 || redstonePower >= 15)
        {
            return;
        }
        int range = ServerConfig.getRangeForStrength(redstonePower);
        if (redstonePower % 2 == 0)
        {
            for (Player pe2 : getNearbyPlayers(worldIn, pos, range))
            {
                doPlayerAction(pe2, worldIn, pos);
            }
        } else
        {
            Player pe1 = worldIn.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), range, null);
            if (pe1 != null && playerWithinRange(pos, pe1, range))
            {
                doPlayerAction(pe1, worldIn, pos);
            }
        }
    }

    abstract void doPlayerAction(Player player, ServerLevel worldIn, BlockPos pos);

    private ArrayList<Player> getNearbyPlayers(ServerLevel worldIn, BlockPos pos, double range)
    {
        ArrayList<Player> nearbyPlayers = new ArrayList<Player>();
        for (Player pe : worldIn.players())
        {
            if (playerWithinRange(pos, pe, range))
            {
                nearbyPlayers.add(pe);
            }
        }
        return nearbyPlayers;
    }

    private boolean playerWithinRange(BlockPos pos, Player player, double range)
    {
        if (range < 0)
        {
            return true;
        }
        if (range == 0)
        {
            return false;
        }
        return pos.closerThan(player.position(), range);
    }
}
