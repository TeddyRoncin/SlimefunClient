package fr.teddy.slimefunclient.block;

import fr.teddy.slimefunclient.block.override.ISlimefunBlock;
import fr.teddy.slimefunclient.machine.PropertyCondition;
import net.minecraft.block.*;

public class CustomBlock<T extends Block> {

    private final T block;
    private final PropertyCondition<?>[] propertyConditions;

    public CustomBlock(T block, PropertyCondition<?>... propertyConditions) {
        this.block = block;
        this.propertyConditions = propertyConditions;
    }

    public Class<? extends Block> getVanillaBlockClass() {
        return (Class<? extends Block>) this.block.getClass().getSuperclass();
    }

    public BlockState createBlockState(BlockState blockState) {
        return ((ISlimefunBlock) this.block).replaceBlock(blockState);
    }

    public boolean arePropertiesMatching(BlockState blockState) {
        if (this.propertyConditions.length == 0) {
            return true;
        }
        for (PropertyCondition<?> propertyCondition : this.propertyConditions) {
            if (propertyCondition.isMatchingPropertyCondition(blockState)) {
                return true;
            }
        }
        return false;
    }

}
