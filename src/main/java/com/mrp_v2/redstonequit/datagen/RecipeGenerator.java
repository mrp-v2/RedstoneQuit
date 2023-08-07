package com.mrp_v2.redstonequit.datagen;

import com.mrp_v2.redstonequit.block.RedstoneQuitBlock;
import com.mrp_v2.redstonequit.block.RedstoneQuitTestBlock;
import com.mrp_v2.redstonequit.util.ObjectHolder;
import mrp_v2.mrplibrary.datagen.providers.RecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    protected RecipeGenerator(DataGenerator dataGeneratorIn, String modId) {
        super(dataGeneratorIn, modId);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ObjectHolder.REDSTONE_QUIT_BLOCK.get())
                .pattern("TST").pattern("CRC").pattern("TST")
                .define('T', Ingredient.of(Items.TRIPWIRE_HOOK))
                .define('S', Ingredient.of(Tags.Items.STRING))
                .define('C', Ingredient.of(Items.COMPARATOR))
                .define('R', Ingredient.of(Tags.Items.STORAGE_BLOCKS_REDSTONE))
                .unlockedBy("has_redstone", has(Tags.Items.DUSTS_REDSTONE))
                .group(RedstoneQuitBlock.ID)
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(ObjectHolder.REDSTONE_QUIT_TEST_BLOCK.get())
                .requires(ObjectHolder.REDSTONE_QUIT_BLOCK.get())
                .unlockedBy("has_quit_block", has(ObjectHolder.REDSTONE_QUIT_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(ObjectHolder.REDSTONE_QUIT_BLOCK.get())
                .requires(ObjectHolder.REDSTONE_QUIT_TEST_BLOCK.get())
                .unlockedBy("has_test_quit_block", has(ObjectHolder.REDSTONE_QUIT_TEST_BLOCK.get()))
                .group(RedstoneQuitBlock.ID)
                .save(consumer, modLoc(RedstoneQuitTestBlock.ID + "_switch"));
    }
}
