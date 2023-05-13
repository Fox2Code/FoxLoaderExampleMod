package com.fox2code.examplemod;

import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.registry.*;

import java.awt.*;
import java.util.Random;

public class ExampleMod extends Mod {
    private static final Random random = new Random();
    private static final int[] loots = new int[]{
            280, // Stick
            287, // String
            288, // Feather
            289, // Gunpowder
            295, // Seeds
            318, // Flint
            405, // Ash
    };
    public static RegisteredBlock ratBlock;

    @Override
    public void onPreInit() {
        ratBlock = registerNewBlock("rat_block", new BlockBuilder()
                .setBlockMaterial(GameRegistry.BuiltInMaterial.CLOTH)
                .setBlockStepSounds(GameRegistry.BuiltInStepSounds.CLOTH)
                .setBlockName("rat_block").setBurnRate(30, 60).setBurnTime(300)
                .setBlockHardness(0.8F).setTooltipColor(Color.GRAY)
                .setEffectiveTool(RegisteredToolType.SWORD));
        RegisteredItem coal = GameRegistry.getInstance().getRegisteredItem(263);
        RegisteredItem wool = GameRegistry.getInstance().getRegisteredItem(35);
        RegisteredItemStack grayWool = wool.newRegisteredItemStack();
        grayWool.setRegisteredDamage(1); // This is for light gray wool.
        registerShapelessRecipe(ratBlock.newRegisteredItemStack(), grayWool, coal);
    }

    public static int randomLootId() {
        if (random.nextInt(256) == 0)
            return 264; // Diamond
        return loots[random.nextInt(loots.length)];
    }
}
