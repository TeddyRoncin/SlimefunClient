package fr.teddy.slimefunclient.mixin;

import com.mojang.authlib.GameProfile;
import fr.teddy.slimefunclient.option.Options;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    @Shadow @Final protected MinecraftClient client;
    private int stage = 0;
    private DefaultedList<ItemStack> currentInventory;
    private int currentSlot;

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (Options.antiBreakMode == 0) {
            return;
        }
        if (this.stage != 0) {
            this.replaceItem(false);
            return;
        }
        if (this.needToWarn(this.getMainHandStack())) {
            this.currentInventory = this.inventory.main;
            this.currentSlot = this.inventory.selectedSlot;
            this.replaceItem(true);
        }
        if (this.needToWarn(this.getOffHandStack())) {
            this.currentInventory = this.inventory.offHand;
            this.currentSlot = 0;
            this.replaceItem(true);
        }
        AtomicInteger i = new AtomicInteger(0);
        this.getArmorItems().forEach(armorStack -> {
            if (this.needToWarn(armorStack)) {
                this.currentInventory = this.inventory.armor;
                this.currentSlot = i.getAndIncrement();
                this.replaceItem(false);
            }
        });
    }

    private void replaceItem(boolean canSwap) {
        switch (this.stage) {
            case 0:
                this.stage = 1;
                this.client.openScreen(new InventoryScreen(this));
                return;
            case 1:
                this.stage = 2;
                if (this.inventory.getEmptySlot() >= 9) {
                    //this.client.interactionManager.clickSlot(((HandledScreen<?>) this.client.currentScreen).getScreenHandler().syncId, currentSlot, 0, SlotActionType.QUICK_MOVE, this);
                    //return;
                }
                if (canSwap) {
                    for (int i = 9; i < 36; i++) {
                        if (this.inventory.main.get(i).getMaxDamage() == 0) {
                            this.client.interactionManager.clickSlot(((HandledScreen<?>) this.client.currentScreen).getScreenHandler().syncId, i, currentSlot, SlotActionType.SWAP, this);
                            return;
                        }
                    }
                }
                this.client.world.disconnect();
                this.client.disconnect();
                this.client.openScreen(new TitleScreen());
                return;
            case 2:
                if (this.needToWarn(this.currentInventory.get(this.currentSlot)) && this.client.currentScreen instanceof InventoryScreen) {
                    return;
                }
                this.stage = 0;
                this.client.openScreen(null);
                return;
            default:
                this.stage = 0;
        }
        /*Screen screen = this.client.currentScreen;
        if (screen != null && !(screen instanceof InventoryScreen)) {
            return;
        }
        int emptySlot = this.inventory.getEmptySlot();
        if (emptySlot >= 9) {
            if (screen == null) {
                this.client.openScreen(new InventoryScreen(this));
            } else {
                this.client.interactionManager.clickSlot(((HandledScreen<?>) this.client.currentScreen).getScreenHandler().syncId, slot, 0, SlotActionType.QUICK_MOVE, this);
                this.inventory.main.set(emptySlot, inventory.get(slot));
                inventory.set(slot, ItemStack.EMPTY);
                this.client.openScreen(null);
            }
            return;
        }
        if (canSwap) {
            for (int i = 9; i < 36; i++) {
                ItemStack stack = this.inventory.main.get(i);
                if (stack.getMaxDamage() == 0) {
                    this.client.openScreen(new InventoryScreen(this));
                    this.client.interactionManager.clickSlot(((HandledScreen<?>) this.client.currentScreen).getScreenHandler().syncId, i, slot, SlotActionType.SWAP, this);
                    this.client.openScreen(null);
                    this.inventory.main.set(i, inventory.get(slot));
                    inventory.set(slot, stack);
                    return;
                }
            }
        }*/
        //this.client.world.disconnect();
        //this.client.disconnect();
        //this.client.openScreen(new TitleScreen());
    }

    private boolean needToWarn(ItemStack stack) {
        return stack.getMaxDamage() != 0 && stack.getDamage() > stack.getMaxDamage() - 3;
    }

}
