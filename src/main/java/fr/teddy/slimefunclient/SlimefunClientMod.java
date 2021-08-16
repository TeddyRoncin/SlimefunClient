package fr.teddy.slimefunclient;

import fr.teddy.slimefunclient.block.grindstone.GrindstoneDispenserBlock;
import fr.teddy.slimefunclient.block.grindstone.GrindstoneFenceBlock;
import fr.teddy.slimefunclient.block.outputChest.OutputChestBlock;
import fr.teddy.slimefunclient.machine.Machine;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public class SlimefunClientMod implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final OutputChestBlock OUTPUT_CHEST_CHEST = new OutputChestBlock(AbstractBlock.Settings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD), () -> BlockEntityType.CHEST);
    public static final GrindstoneDispenserBlock GRINDSTONE_DISPENSER = new GrindstoneDispenserBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.5F));
    public static final GrindstoneFenceBlock GRINDSTONE_FENCE = new GrindstoneFenceBlock(AbstractBlock.Settings.of(Material.WOOD, Blocks.OAK_PLANKS.getDefaultMaterialColor()).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("slimefunclient", "output_chest"), OUTPUT_CHEST_CHEST);
        Registry.register(Registry.BLOCK, new Identifier("slimefunclient", "grindstone_dispenser"), GRINDSTONE_DISPENSER);
        Registry.register(Registry.BLOCK, new Identifier("slimefunclient", "grindstone_fence"), GRINDSTONE_FENCE);
        Machine.init();

        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("slimefunclient", "my_resources");
            }

            @Override
            public void apply(ResourceManager manager) {
                // Clear Caches Here

                for(Identifier id : manager.findResources("my_resource_folder", path -> path.endsWith(".json"))) {
                    try(InputStream stream = manager.getResource(id).getInputStream()) {
                        // Consume the stream however you want, medium, rare, or well done.
                    } catch(Exception e) {
                            SlimefunClientMod.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
                    }
                }
            }
        });
    }

}
