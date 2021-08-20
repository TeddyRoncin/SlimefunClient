package fr.teddy.slimefunclient.machine;

import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

public class MachinePattern {

    public final MachineBlock[] blocks;

    public MachinePattern(MachineBlock... blocks) {
        this.blocks = blocks;
    }

    public BlockPos getMatchingBlockPos(BlockState block) {
        for (MachineBlock blockPos : this.blocks) {
            if (blockPos.block.getVanillaBlockClass().isInstance(block.getBlock())) {
                return blockPos.pos;
            }
        }
        return null;
    }

    public boolean isMachineCompleted(BlockPos controller, ClientWorld world) {
        for (MachineBlock block : this.blocks) {
            BlockState blockState = world.getBlockState(controller.add(block.pos));
            if (!block.block.getVanillaBlockClass().isInstance(blockState.getBlock()) || !block.block.arePropertiesMatching(blockState)) {
                return false;
            }
        }
        return true;
    }

}
