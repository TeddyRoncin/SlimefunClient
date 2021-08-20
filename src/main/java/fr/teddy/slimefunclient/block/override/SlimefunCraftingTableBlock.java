package fr.teddy.slimefunclient.block.override;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;

public class SlimefunCraftingTableBlock extends CraftingTableBlock implements ISlimefunBlock {

    public SlimefunCraftingTableBlock() {
        super(AbstractBlock.Settings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD));
    }

    @Override
    public BlockState getVanillaBlockState(BlockState state) {
        return Blocks.CRAFTING_TABLE.getDefaultState();
    }

}
