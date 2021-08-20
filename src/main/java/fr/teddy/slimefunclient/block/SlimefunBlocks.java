package fr.teddy.slimefunclient.block;

import fr.teddy.slimefunclient.block.override.*;
import fr.teddy.slimefunclient.machine.PropertyCondition;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class SlimefunBlocks {

    public static class OutputChest {
        public static CustomBlock<SlimefunChestBlock> CHEST;
    }

    public static class Grindstone {
        public static CustomBlock<SlimefunDispenserBlock> DISPENSER;
        public static CustomBlock<SlimefunFenceBlock> FENCE;
    }

    public static class EnhancedCraftingTable {
        public static CustomBlock<SlimefunDispenserBlock> DISPENSER;
        public static CustomBlock<SlimefunCraftingTableBlock> CRAFTING_TABLE;
    }

    public static class ArmorForge {
        public static CustomBlock<SlimefunDispenserBlock> DISPENSER;
        public static CustomBlock<SlimefunAnvilBlock> ANVIL;
    }

    public static void register() {
        OutputChest.CHEST = register(new SlimefunChestBlock(), "output_chest");
        Grindstone.DISPENSER = register(new SlimefunDispenserBlock(), "grindstone_dispenser",
                new PropertyCondition<>(DispenserBlock.FACING, Direction.UP));
        Grindstone.FENCE = register(new SlimefunFenceBlock(), "grindstone_fence");
        EnhancedCraftingTable.DISPENSER = register(new SlimefunDispenserBlock(), "enhanced_crafting_table_dispenser");
        EnhancedCraftingTable.CRAFTING_TABLE = register(new SlimefunCraftingTableBlock(), "enhanced_crafting_table_crafting_table");
        ArmorForge.DISPENSER = register(new SlimefunDispenserBlock(), "armor_forge_dispenser");
        ArmorForge.ANVIL = register(new SlimefunAnvilBlock(), "armor_forge_anvil");
    }

    private static <T extends Block> CustomBlock<T> register(T block, String name, PropertyCondition<?>... propertyConditions) {
        Registry.register(Registry.BLOCK, new Identifier("slimefunclient", name), block);
        return new CustomBlock<>(block, propertyConditions);
    }

}
