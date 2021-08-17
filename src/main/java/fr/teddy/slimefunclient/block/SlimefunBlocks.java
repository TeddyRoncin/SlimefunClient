package fr.teddy.slimefunclient.block;

import fr.teddy.slimefunclient.block.override.SlimefunChestBlock;
import fr.teddy.slimefunclient.block.override.SlimefunDispenserBlock;
import fr.teddy.slimefunclient.block.override.SlimefunFenceBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SlimefunBlocks {

    public static class OutputChest {
        public static CustomBlock<SlimefunChestBlock> CHEST;
    }

    public static class Grindstone {
        public static CustomBlock<SlimefunDispenserBlock> DISPENSER;
        public static CustomBlock<SlimefunFenceBlock> FENCE;
    }

    public static void register() {
        OutputChest.CHEST = register(new SlimefunChestBlock(), "output_chest");
        Grindstone.DISPENSER = register(new SlimefunDispenserBlock(), "grindstone_dispenser");
        Grindstone.FENCE = register(new SlimefunFenceBlock(), "grindstone_fence");
    }

    private static <T extends Block> CustomBlock<T> register(T block, String name) {
        Registry.register(Registry.BLOCK, new Identifier("slimefunclient", name), block);
        return new CustomBlock<>(block);
    }

}
