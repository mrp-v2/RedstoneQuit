package com.mrp_v2.redstonequit.datagen;

import com.mrp_v2.redstonequit.block.RedstoneQuitBlock;
import com.mrp_v2.redstonequit.block.RedstoneQuitTestBlock;
import com.mrp_v2.redstonequit.util.ObjectHolder;
import mrp_v2.mrplibrary.datagen.providers.LanguageProvider;
import net.minecraft.data.DataGenerator;

public class EN_USTranslationGenerator extends LanguageProvider {
    public EN_USTranslationGenerator(DataGenerator gen, String modid) {
        super(gen, modid, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ObjectHolder.REDSTONE_QUIT_BLOCK.get(), "Quit Block");
        add(ObjectHolder.REDSTONE_QUIT_TEST_BLOCK.get(), "Test Quit Block");
        add(RedstoneQuitBlock.DISCONNECT_MESSAGE.getKey(), "Disconnected by Quit Block");
        add(RedstoneQuitTestBlock.TEST_QUIT_MESSAGE.getKey(), "(Test Quit Block) You have been disconnected!");
    }
}
