package fr.teddy.slimefunclient.screen;

import fr.teddy.slimefunclient.option.Options;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class SlimefunOptionsScreen extends Screen {

    private final ButtonWidget antiBreakModeButton = new ButtonWidget(0, 0, 220, 20, LiteralText.EMPTY, (button) -> {
        Options.antiBreakMode = (Options.antiBreakMode + 1) % 2;
        this.updateAntiBreakButton();
    });

    private final ButtonWidget useMachineModelsButton = new ButtonWidget(0, 0, 220, 20, LiteralText.EMPTY, (button) -> {
        Options.useMachineModels = !Options.useMachineModels;
        this.updateUseMachineModelsButton();
    });

    public SlimefunOptionsScreen() {
        super(LiteralText.EMPTY);
    }

    @Override
    protected void init() {
        super.init();
        //this.addButton(this.antiBreakModeButton);
        this.addButton(this.useMachineModelsButton);
        this.updateAntiBreakButton();
        this.updateUseMachineModelsButton();
    }

    private void updateAntiBreakButton() {
        this.antiBreakModeButton.setMessage(new TranslatableText("slimefun.options.anti_break").append(" : ").append(this.getAntiBreakModeName()));
    }

    private void updateUseMachineModelsButton() {
        this.useMachineModelsButton.setMessage(new TranslatableText("slimefun.options.use_machine_models").append(" : ").append(new TranslatableText(Options.useMachineModels ? "gui.yes" : "gui.no")));
    }

    private TranslatableText getAntiBreakModeName() {
        switch (Options.antiBreakMode) {
            case 0:
                return new TranslatableText("slimefun.options.disabled");
            case 1:
                return new TranslatableText("slimefun.options.anti_break.swap_with_inventory_stack");
        }
        System.err.println("Anti break mode " + Options.antiBreakMode + " is not known");
        Options.antiBreakMode = 0;
        return this.getAntiBreakModeName();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
