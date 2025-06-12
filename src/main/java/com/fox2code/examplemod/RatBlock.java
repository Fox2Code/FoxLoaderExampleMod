package com.fox2code.examplemod;

import net.minecraft.client.gui.creative.CreativeTab;
import net.minecraft.client.gui.creative.CreativeTabs;
import net.minecraft.common.block.Block;
import net.minecraft.common.block.data.Materials;
import net.minecraft.common.block.sound.StepSounds;
import net.minecraft.common.item.data.EnumTools;

import java.awt.*;

public class RatBlock extends Block {
    public RatBlock(String name) {
        super(name, Materials.CLOTH);
        this.setSound(StepSounds.SOUND_CLOTH);
        this.setFlammable(30, 60);
        this.setBurnTime(0, 300);
        this.setHardness(0.8F);
        this.setTooltipColor(Color.GRAY.getRGB());
        this.setEffectiveTool(EnumTools.SWORD);
    }

    @Override
    public CreativeTab getRegisterFLTab() {
        return CreativeTabs.BUILDING_BLOCKS;
    }
}
