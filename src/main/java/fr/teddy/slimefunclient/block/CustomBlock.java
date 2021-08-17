package fr.teddy.slimefunclient.block;

import fr.teddy.slimefunclient.block.override.ISlimefunBlock;
import net.minecraft.block.*;

public class CustomBlock<T extends Block> {

    private final T block;

    public CustomBlock(T block) {
        this.block = block;
    }

    public Class<? extends Block> getVanillaBlockClass() {
        return (Class<? extends Block>) this.block.getClass().getSuperclass();
    }

    public BlockState createBlockState(BlockState blockState) {
        return ((ISlimefunBlock) this.block).replaceBlock(blockState);
    }

}
