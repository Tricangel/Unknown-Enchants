	package bee.unknownenchants.mixin;

    import bee.unknownenchants.UnknownEnchants;
    import bee.unknownenchants.config.EnchantDiscoveryMode;
    import bee.unknownenchants.config.UnknownEnchantsConfig;
    import bee.unknownenchants.registry.ModEntityComponents;
    import net.minecraft.client.Minecraft;
    import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
    import net.minecraft.client.player.LocalPlayer;
    import net.minecraft.core.component.DataComponents;
    import net.minecraft.world.inventory.Slot;
    import net.minecraft.world.item.ItemStack;
    import net.minecraft.world.item.Items;
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

            //if ( UnknownEnchantsConfig.discoveryMode.equals(EnchantDiscoveryMode.ENCHANTED_ITEM) && hoveredSlot != null) UnknownEnchants.discoverEnchants(hoveredSlot.getItem());

        }


}