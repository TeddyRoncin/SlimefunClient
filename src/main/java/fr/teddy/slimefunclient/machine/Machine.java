package fr.teddy.slimefunclient.machine;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.math.BlockPos;

public abstract class Machine {

    private static Machine[] machines;

    public static void init() {
        Machine.machines = new Machine[] {
                new OutputChestMachine(),
                new GrindstoneMachine()
        };
    }

    public static boolean replaceBlocksByMachines(BlockPos pos) {
        ClientWorld world = MinecraftClient.getInstance().world;
        BlockState block = world.getBlockState(pos);
        for (Machine machine : Machine.machines) {
            BlockPos machineControllerPos = machine.getMachineControllerPos(block, pos, world);
            if (machineControllerPos != null) {
                machine.replaceBlocks(machineControllerPos, world);
                return true;
            }
        }
        return false;
    }

    protected abstract BlockPos getMachineControllerPos(BlockState block, BlockPos pos, ClientWorld world);

    protected abstract void replaceBlocks(BlockPos pos, ClientWorld world);

}
