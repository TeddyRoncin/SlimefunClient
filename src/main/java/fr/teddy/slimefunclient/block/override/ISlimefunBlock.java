package fr.teddy.slimefunclient.block.override;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public interface ISlimefunBlock {

    BlockState getVanillaBlockState(BlockState state);

    BlockState getDefaultState();

    default BlockState replaceBlock(BlockState vanillaBlock) {
        return this.getDefaultState();
    }

}
