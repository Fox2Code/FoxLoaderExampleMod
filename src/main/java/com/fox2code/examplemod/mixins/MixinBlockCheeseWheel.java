package com.fox2code.examplemod.mixins;

import com.fox2code.examplemod.ExampleMod;
import net.minecraft.common.block.Block;
import net.minecraft.common.block.children.BlockCheeseWheel;
import net.minecraft.common.block.data.Material;

import net.minecraft.common.entity.other.EntityItem;
import net.minecraft.common.entity.player.EntityPlayer;
import net.minecraft.common.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockCheeseWheel.class)
public class MixinBlockCheeseWheel extends Block {
    protected MixinBlockCheeseWheel(String id, Material material) {
        super(id, material);
    }

    @Inject(method = "blockActivated", at = @At("HEAD"), cancellable = true)
    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (ExampleMod.CONFIG.eatCheese && !world.isRemote &&
                world.getBlockId(x, y - 1, z) == ExampleMod.RAT_BLOCK.blockID) {
            EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ,  ExampleMod.makeRatLoot());
            world.entityJoinedWorld(item);
            int i = world.getBlockMetadata(x, y, z);
            if (i >= 6) {
                world.setBlockWithNotify(x, y, z, 0);
            } else {
                world.setBlockMetadataWithNotify(x, y, z, i + 1);
                world.markBlockAsNeedsUpdate(x, y, z);
            }
            world.playSoundEffect(x + 0.5f, y + 0.5f, z + 0.5f, "random.pop", 1f, 1f);
            cir.setReturnValue(Boolean.TRUE);
        }
    }

    @Override
    public void onBlockPlaced(World world, int x, int y, int z, int blockFace) {
        if (ExampleMod.CONFIG.eatCheese && !world.isRemote &&
                world.getBlockId(x, y - 1, z) == ExampleMod.RAT_BLOCK.blockID) {
            int i = world.getBlockMetadata(x, y, z);
            if (i >= 6) {
                world.setBlockWithNotify(x, y, z, 0);
            } else {
                world.setBlockMetadataWithNotify(x, y, z, i + 1);
                world.markBlockAsNeedsUpdate(x, y, z);
            }
            world.playSoundEffect(x + 0.5f, y + 0.5f, z + 0.5f, "random.pop", 1f, 1f);
        }
    }
}