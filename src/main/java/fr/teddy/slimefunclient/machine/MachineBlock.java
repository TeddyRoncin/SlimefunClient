package fr.teddy.slimefunclient.machine;

import fr.teddy.slimefunclient.block.CustomBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

public class MachineBlock {

    public final BlockPos pos;
    public final CustomBlock<? extends Block> block;

    public MachineBlock(int x, int y, int z, CustomBlock<? extends Block> block) {
        this.pos = new BlockPos(x, y, z);
        this.block = block;
    }
}
