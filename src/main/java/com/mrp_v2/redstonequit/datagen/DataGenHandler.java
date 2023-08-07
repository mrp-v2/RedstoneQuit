package com.mrp_v2.redstonequit.datagen;

import com.mrp_v2.redstonequit.RedstoneQuit;
import mrp_v2.mrplibrary.datagen.DataGeneratorHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = RedstoneQuit.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenHandler {
    @SubscribeEvent
    public static void gatherDataEvent(final GatherDataEvent event) {
        DataGeneratorHelper helper = new DataGeneratorHelper(event, RedstoneQuit.ID);
        helper.addBlockStateProvider(BlockStateGenerator::new);
        helper.addItemModelProvider(ItemModelGenerator::new);
        helper.addLanguageProvider(EN_USTranslationGenerator::new);
        helper.addRecipeProvider(RecipeGenerator::new);
        helper.addBlockTagsProvider(BlockTagsGenerator::new);
    }
}
