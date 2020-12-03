package com.mrp_v2.redstonequit.block;

import com.mrp_v2.redstonequit.config.ServerConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
        return new BlockItem(this, new Item.Properties().addToolType(TOOL_TYPE, 1).tab(ItemGroup.TAB_REDSTONE));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        builder.add(BlockStateProperties.POWER);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
            boolean isMoving)
    {
        if (!worldIn.isClientSide)
        {
            worldIn.getBlockTicks().scheduleTick(pos, this, 0);
        }
    }

    @Override
    public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        worldIn.getBlockTicks().scheduleTick(pos, this, 0);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
            Hand handIn, BlockRayTraceResult hit)
    {
        return worldIn.isClientSide ?
                ActionResultType.SUCCESS :
                doBlockActivated(state, worldIn, pos) ? ActionResultType.CONSUME : ActionResultType.PASS;
    }

    abstract boolean doBlockActivated(BlockState state, World worldIn, BlockPos pos);

    @Override public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        blockPowered(worldIn.getBestNeighborSignal(pos), worldIn, pos);
    }

    // Even numbers are all,
    // Odds are nearest
    protected void blockPowered(int redstonePower, ServerWorld worldIn, BlockPos pos)
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
            for (PlayerEntity pe2 : getNearbyPlayers(worldIn, pos, range))
            {
                doPlayerAction(pe2, worldIn, pos);
            }
        } else
        {
            PlayerEntity pe1 = worldIn.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), range, null);
            if (pe1 != null && playerWithinRange(pos, pe1, range))
            {
                doPlayerAction(pe1, worldIn, pos);
            }
        }
    }

    abstract void doPlayerAction(PlayerEntity player, ServerWorld worldIn, BlockPos pos);

    private ArrayList<PlayerEntity> getNearbyPlayers(ServerWorld worldIn, BlockPos pos, double range)
    {
        ArrayList<PlayerEntity> nearbyPlayers = new ArrayList<PlayerEntity>();
        for (PlayerEntity pe : worldIn.players())
        {
            if (playerWithinRange(pos, pe, range))
            {
                nearbyPlayers.add(pe);
            }
        }
        return nearbyPlayers;
    }

    private boolean playerWithinRange(BlockPos pos, PlayerEntity player, double range)
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
