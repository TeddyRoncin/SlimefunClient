package fr.teddy.slimefunclient.machine;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;

public class PropertyCondition<T extends Comparable<T>> {

    private final T[] acceptedValues;
    private final Property<T> property;

    public PropertyCondition(Property<T> property, T... acceptedValues) {
        this.acceptedValues = acceptedValues;
        this.property = property;
    }

    public boolean isMatchingPropertyCondition(BlockState blockState) {
        T property = blockState.get(this.property);
        for (Object value : this.acceptedValues) {
            if (property == value) {
                return true;
            }
        }
        return false;
    }

}
