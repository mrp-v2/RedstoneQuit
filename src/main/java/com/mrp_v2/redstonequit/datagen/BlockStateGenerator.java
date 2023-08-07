package com.mrp_v2.redstonequit.datagen;

import com.mrp_v2.redstonequit.util.ObjectHolder;
import mrp_v2.mrplibrary.datagen.providers.BlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGenerator extends BlockStateProvider {
    public BlockStateGenerator(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ObjectHolder.REDSTONE_QUIT_BLOCK.get());
        simpleBlock(ObjectHolder.REDSTONE_QUIT_TEST_BLOCK.get());
    }
}
