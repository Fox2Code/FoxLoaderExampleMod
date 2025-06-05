package com.fox2code.examplemod;

import com.fox2code.foxloader.config.ConfigEntry;
import com.fox2code.foxloader.loader.Mod;
import net.minecraft.common.block.Blocks;
import net.minecraft.common.item.ItemStack;
import net.minecraft.common.item.Items;
import net.minecraft.common.recipe.CraftingManager;

import java.util.Random;

public class ExampleMod extends Mod {
    public static final ExampleModConfig CONFIG = new ExampleModConfig();
    public static RatBlock RAT_BLOCK;
    private static final Random random = new Random();
    private static ItemStack[] RAT_LOOT;

    @Override
    public void onPreInit() {
        this.setConfigObject(CONFIG);
        RAT_BLOCK = new RatBlock("rat_block");
        // light gray wool + coal = rat block
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(RAT_BLOCK),
                new ItemStack(Blocks.WOOL, 1, 1), new ItemStack(Items.COAL));
        RAT_LOOT = new ItemStack[]{
                new ItemStack(Items.STICK),
                new ItemStack(Items.STRING),
                new ItemStack(Items.FEATHER),
                new ItemStack(Items.GUNPOWDER),
                new ItemStack(Items.SEEDS),
                new ItemStack(Items.FLINT),
                new ItemStack(Items.ASH),
        };
    }

    public static ItemStack makeRatLoot() {
        if (random.nextInt(256) == 0)
            return new ItemStack(Items.DIAMOND);
        return RAT_LOOT[random.nextInt(RAT_LOOT.length)].copy();
    }

    public static class ExampleModConfig {
        @ConfigEntry(configName = "Rat eat cheese")
        public boolean eatCheese = true;
    }
}
