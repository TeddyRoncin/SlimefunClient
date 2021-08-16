package fr.teddy.slimefunclient.mixin;

import fr.teddy.slimefunclient.screen.SlimefunOptionsScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    protected void init(CallbackInfo ci) {
        this.addButton(new ButtonWidget(this.width / 2 - 100, 0, 200, 20, new TranslatableText("slimefun.options"), button -> this.client.openScreen(new SlimefunOptionsScreen())));
        //this.addButton(new ButtonWidget(this.width / 2 - 100, 0, 200, 20, new TranslatableText("slimefun.options"), button -> this.client.openScreen(new SlimefunOptionsScreen())));
    }

}
