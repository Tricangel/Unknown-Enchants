package bee.unknownenchants.registry;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastManager;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

public class DiscoverEnchantToast implements Toast {
    private final String enchantmentName;
    private Visibility visibility;
    public DiscoverEnchantToast(String enchantmentName) {
        this.enchantmentName = enchantmentName;
        this.visibility = Visibility.SHOW;
    }

    @Override
    public Visibility getWantedVisibility() {
        return visibility;
    }

    @Override
    public void update(ToastManager manager, long fullyVisibleForMs) {
        if (fullyVisibleForMs > 5000) visibility = Visibility.HIDE;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, Font font, long fullyVisibleForMs) {
        int alpha = Math.max( 5000 - Math.toIntExact(fullyVisibleForMs), 255);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("toast/advancement"), 0, 0, this.width(), this.height());

        Component component = Component.translatable(enchantmentName);
        graphics.text(font, "Enchantment Discovered!", 30, 7, DyeColor.MAGENTA.getTextColor(), false);
        graphics.text(font, component, 30, 18, ARGB.white(255), false);
        graphics.fakeItem(Items.ENCHANTED_BOOK.getDefaultInstance(), 8, 8);
    }
}
