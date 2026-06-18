	package bee.unknownenchants.mixin;

    import bee.unknownenchants.registry.ModEntityComponents;
    import net.minecraft.client.Minecraft;
    import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
    import net.minecraft.client.player.LocalPlayer;
    import net.minecraft.core.component.DataComponents;
    import net.minecraft.world.inventory.Slot;
    import net.minecraft.world.item.ItemStack;
    import net.minecraft.world.item.enchantment.ItemEnchantments;
    import org.jspecify.annotations.Nullable;
    import org.spongepowered.asm.mixin.Mixin;
    import org.spongepowered.asm.mixin.Shadow;
    import org.spongepowered.asm.mixin.injection.At;
    import org.spongepowered.asm.mixin.injection.Inject;
    import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

    @Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {


        @Shadow
        @Nullable
        protected Slot hoveredSlot;

        @Inject(at = @At("HEAD"), method = "tick")
        private void init(CallbackInfo ci) {

            if (Minecraft.getInstance().player != null && this.hoveredSlot != null) {
                LocalPlayer player = Minecraft.getInstance().player;
                ItemStack stack = this.hoveredSlot.getItem();
                if (stack.has(DataComponents.ENCHANTMENTS)) {
                    ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);
                    
                    enchantments.keySet().forEach(enchantmentHolder -> {
                        ModEntityComponents.DISCOVERED_ENCHANTS.get(player).addEnchantment(enchantmentHolder);
                    });
                }

            }

        }


}