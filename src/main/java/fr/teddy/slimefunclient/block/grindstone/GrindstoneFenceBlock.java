package fr.teddy.slimefunclient.block.grindstone;

import fr.teddy.slimefunclient.block.ISlimefunBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;

public class GrindstoneFenceBlock extends FenceBlock implements ISlimefunBlock {

    public GrindstoneFenceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.OAK_FENCE.getDefaultState();
    }
}
