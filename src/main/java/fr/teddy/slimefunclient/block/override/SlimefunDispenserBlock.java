package fr.teddy.slimefunclient.block.override;

import net.minecraft.block.*;

public class SlimefunDispenserBlock extends DispenserBlock implements ISlimefunBlock {

    public SlimefunDispenserBlock() {
        super(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.5F));
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.DISPENSER.getDefaultState()
                .with(DispenserBlock.FACING, state.get(DispenserBlock.FACING))
                .with(DispenserBlock.TRIGGERED, state.get(DispenserBlock.TRIGGERED));
    }

    @Override
    public BlockState replaceBlock(BlockState vanillaBlock) {
        return this.getDefaultState()
                .with(DispenserBlock.FACING, vanillaBlock.get(DispenserBlock.FACING))
                .with(DispenserBlock.TRIGGERED, vanillaBlock.get(DispenserBlock.TRIGGERED));
    }

}
