package fr.teddy.slimefunclient.machine;

import fr.teddy.slimefunclient.block.CustomBlock;
import fr.teddy.slimefunclient.block.SlimefunBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;

public class Machine {

    private static final MachinePattern CHEST = new MachinePattern(
            new MachineBlock(0, 0, 0, SlimefunBlocks.OutputChest.CHEST)
    );

    private static final MachinePattern GRINDSTONE = new MachinePattern(
            new MachineBlock(0, 0, 0, SlimefunBlocks.Grindstone.DISPENSER),
            new MachineBlock(0, 1, 0, SlimefunBlocks.Grindstone.FENCE)
    );

    private static final MachinePattern[] machinePatterns = new MachinePattern[] {
            CHEST, GRINDSTONE
    };

    public static boolean replaceBlocksByMachines(BlockPos pos) {
        ClientWorld world = MinecraftClient.getInstance().world;
        BlockState block = world.getBlockState(pos);
        Machine machine = Machine.getMachine(block, pos, world);
        if (machine != null) {
            machine.replaceBlocks(world);
            return true;
        }
        return false;
    }

    protected static Machine getMachine(BlockState block, BlockPos pos, ClientWorld world) {
        for (MachinePattern machinePattern : machinePatterns) {
            BlockPos matchingBlock = machinePattern.getMatchingBlockPos(block);
            if (matchingBlock != null) {
                BlockPos controller = pos.add(-matchingBlock.getX(), -matchingBlock.getY(), -matchingBlock.getZ());
                if (machinePattern.isMachineCompleted(controller, world)) {
                    return new Machine(controller, machinePattern);
                }
            }
        }
        return null;
    }

    private final BlockPos controller;
    private final MachinePattern machinePattern;

    public Machine(BlockPos controller, MachinePattern machinePattern) {
        this.controller = controller;
        this.machinePattern = machinePattern;
    }

    protected void replaceBlocks(ClientWorld world) {
        for (MachineBlock block : this.machinePattern.blocks) {
            BlockPos pos = this.controller.add(block.pos);
            world.setBlockStateWithoutNeighborUpdates(pos, block.block.createBlockState(world.getBlockState(pos)));
        }
    }

}
