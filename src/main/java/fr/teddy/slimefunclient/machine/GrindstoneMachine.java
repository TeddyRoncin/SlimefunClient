package fr.teddy.slimefunclient.machine;

import fr.teddy.slimefunclient.SlimefunClientMod;
import net.minecraft.block.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class GrindstoneMachine extends Machine {

    @Override
    protected BlockPos getMachineControllerPos(BlockState block, BlockPos pos, ClientWorld world) {
        if (block.getBlock() instanceof DispenserBlock
                && block.get(DispenserBlock.FACING) == Direction.UP) {
            if (world.getBlockState(pos.up()).getBlock() instanceof FenceBlock) {
                return pos;
            }
        }
        if (block.getBlock() instanceof FenceBlock) {
            BlockState bottomBlock = world.getBlockState(pos.down());
            if (bottomBlock.getBlock() instanceof DispenserBlock
                    && bottomBlock.get(DispenserBlock.FACING) == Direction.UP) {
                return pos.down();
            }
        }
        return null;
    }

    @Override
    protected void replaceBlocks(BlockPos pos, ClientWorld world) {
        world.setBlockStateWithoutNeighborUpdates(pos, SlimefunClientMod.GRINDSTONE_DISPENSER.replaceBlock(world.getBlockState(pos)));
        world.setBlockStateWithoutNeighborUpdates(pos.up(), SlimefunClientMod.GRINDSTONE_FENCE.getDefaultState());
    }

}
