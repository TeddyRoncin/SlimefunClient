package fr.teddy.slimefunclient.machine;

import fr.teddy.slimefunclient.SlimefunClientMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

public class OutputChestMachine extends Machine {

    @Override
    protected BlockPos getMachineControllerPos(BlockState block, BlockPos pos, ClientWorld world) {
        return block.getBlock() instanceof ChestBlock ? pos : null;
    }

    @Override
    protected void replaceBlocks(BlockPos pos, ClientWorld world) {
        world.setBlockStateWithoutNeighborUpdates(pos, SlimefunClientMod.OUTPUT_CHEST_CHEST.getDefaultState());
    }

}
