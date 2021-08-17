package fr.teddy.slimefunclient.mixin;

import fr.teddy.slimefunclient.machine.Machine;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Shadow private ClientWorld world;

    @Inject(method = "onChunkData", at = @At("TAIL"))
    public void loadFromPacket(ChunkDataS2CPacket packet, CallbackInfo ci) {
        int chunkX = packet.getX();
        int chunkZ = packet.getZ();
        WorldChunk chunk = this.world.getChunk(chunkX, chunkZ);
        BlockPos startPos = chunk.getPos().getStartPos();
        BlockPos endPos = new BlockPos(chunk.getPos().getEndX(), 255, chunk.getPos().getEndZ());
        BlockPos.iterate(startPos, endPos).forEach(Machine::replaceBlocksByMachines);
    }

    @Inject(method = "onBlockUpdate", at = @At("TAIL"))
    public void onBlockUpdate(BlockUpdateS2CPacket packet, CallbackInfo ci) {

    }

}
