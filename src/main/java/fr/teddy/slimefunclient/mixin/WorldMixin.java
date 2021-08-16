package fr.teddy.slimefunclient.mixin;

import fr.teddy.slimefunclient.block.ISlimefunBlock;
import fr.teddy.slimefunclient.machine.Machine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin {

    @Shadow public abstract boolean setBlockState(BlockPos pos, BlockState state);

    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Inject(method = "onBlockChanged", at = @At("TAIL"))
    public void onBlockChanged(BlockPos pos, BlockState oldBlock, BlockState newBlock, CallbackInfo ci) {
        if (newBlock.getBlock() instanceof ISlimefunBlock) {
            return;
        }
        if (Machine.replaceBlocksByMachines(pos)) {
            return;
        }
        if (oldBlock.getBlock() instanceof ISlimefunBlock) {
            BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1)).forEach(position -> {
                BlockState state = this.getBlockState(position);
                Block block = state.getBlock();
                if (block instanceof ISlimefunBlock) {
                    this.setBlockState(position, ((ISlimefunBlock) block).getVanillaBlockState(state));
                }
            });
        }
    }

}
