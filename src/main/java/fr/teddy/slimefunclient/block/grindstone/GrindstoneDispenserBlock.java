package fr.teddy.slimefunclient.block.grindstone;

import fr.teddy.slimefunclient.block.ISlimefunBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;

public class GrindstoneDispenserBlock extends DispenserBlock implements ISlimefunBlock {

    public GrindstoneDispenserBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.DISPENSER.getDefaultState()
                .with(DispenserBlock.FACING, state.get(DispenserBlock.FACING))
                .with(DispenserBlock.TRIGGERED, state.get(DispenserBlock.TRIGGERED));
    }

    public BlockState replaceBlock(BlockState dispenser) {
        return this.getDefaultState()
                .with(DispenserBlock.FACING, dispenser.get(DispenserBlock.FACING))
                .with(DispenserBlock.TRIGGERED, dispenser.get(DispenserBlock.TRIGGERED));
    }
}
