package com.fox2code.examplemod.server.mixins;

import com.fox2code.examplemod.ExampleMod;
import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.BlockCheeseWheel;
import net.minecraft.src.game.block.Material;
import net.minecraft.src.game.entity.other.EntityItem;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.item.Item;
import net.minecraft.src.game.item.ItemStack;
import net.minecraft.src.game.level.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockCheeseWheel.class)
public class MixinBlockCheeseWheel extends Block {

    protected MixinBlockCheeseWheel(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "blockActivated", at = @At("HEAD"), cancellable = true)
    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockId(x, y - 1, z) == ExampleMod.ratBlock.getRegisteredBlockId()) {
            int loot = ExampleMod.randomLootId();
            if (loot != 0) {
                EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(loot, 1));
                world.entityJoinedWorld(item);
            }
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
        if (world.getBlockId(x, y - 1, z) == ExampleMod.ratBlock.getRegisteredBlockId()) {
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
