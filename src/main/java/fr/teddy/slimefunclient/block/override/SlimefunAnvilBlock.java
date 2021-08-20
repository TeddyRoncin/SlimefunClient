package fr.teddy.slimefunclient.block.override;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class SlimefunAnvilBlock extends AnvilBlock implements ISlimefunBlock {

    public SlimefunAnvilBlock() {
        super(AbstractBlock.Settings.of(Material.REPAIR_STATION, MaterialColor.IRON).requiresTool().strength(5.0F, 1200.0F).sounds(BlockSoundGroup.ANVIL));
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.ANVIL.getDefaultState()
                .with(AnvilBlock.FACING, state.get(AnvilBlock.FACING));
    }

    @Override
    public BlockState replaceBlock(BlockState vanillaBlock) {
        return this.getDefaultState()
                .with(AnvilBlock.FACING, vanillaBlock.get(AnvilBlock.FACING));
    }
}
