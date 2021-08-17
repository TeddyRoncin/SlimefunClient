package fr.teddy.slimefunclient.block.override;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class SlimefunFenceBlock extends FenceBlock implements ISlimefunBlock {

    public SlimefunFenceBlock() {
        super(AbstractBlock.Settings.of(Material.WOOD, Blocks.OAK_PLANKS.getDefaultMaterialColor()).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.OAK_FENCE.getDefaultState();
    }

    @Override
    public BlockState replaceBlock(BlockState vanillaBlock) {
        return this.getDefaultState();
    }
}
