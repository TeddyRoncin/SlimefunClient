package fr.teddy.slimefunclient;

import fr.teddy.slimefunclient.block.SlimefunBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public class SlimefunClientMod implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        SlimefunBlocks.register();

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
