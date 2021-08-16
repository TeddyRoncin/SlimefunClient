package fr.teddy.slimefunclient.block.outputChest;

import fr.teddy.slimefunclient.block.ISlimefunBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;

import java.util.function.Supplier;

public class OutputChestBlock extends ChestBlock implements ISlimefunBlock {

    public OutputChestBlock(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(settings, supplier);
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.CHEST.getDefaultState();
    }
}
