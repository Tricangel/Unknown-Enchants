	package bee.unknownenchants.mixin;

import bee.unknownenchants.UnknownEnchants;
import bee.unknownenchants.config.EnchantDiscoveryMode;
import bee.unknownenchants.config.UnknownEnchantsConfig;
import bee.unknownenchants.registry.ModEntityComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

    @Mixin(Inventory.class)
public abstract class InventoryMixin {


        @Inject(at = @At("HEAD"), method = "add(ILnet/minecraft/world/item/ItemStack;)Z")
        private void init(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

            if (UnknownEnchantsConfig.discoveryMode.equals(EnchantDiscoveryMode.ENCHANTED_ITEM)) UnknownEnchants.discoverEnchants(stack);

        }


}