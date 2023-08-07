package com.mrp_v2.redstonequit.datagen;

import com.mrp_v2.redstonequit.block.RedstoneQuitBlock;
import com.mrp_v2.redstonequit.block.RedstoneQuitTestBlock;
import mrp_v2.mrplibrary.datagen.providers.ItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(RedstoneQuitBlock.ID, modLoc("block/" + RedstoneQuitBlock.ID));
        withExistingParent(RedstoneQuitTestBlock.ID, modLoc("block/" + RedstoneQuitTestBlock.ID));
    }
}
