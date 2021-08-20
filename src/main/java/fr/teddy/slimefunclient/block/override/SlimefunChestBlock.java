package fr.teddy.slimefunclient.block.override;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;

public class SlimefunChestBlock extends ChestBlock implements ISlimefunBlock {

    public SlimefunChestBlock() {
        super(AbstractBlock.Settings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD), () -> BlockEntityType.CHEST);
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.CHEST.getDefaultState();
    }


}
