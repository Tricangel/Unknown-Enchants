	package bee.unknownenchants.mixin;

import bee.unknownenchants.registry.ModEntityComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

    @Mixin(Inventory.class)
public abstract class InventoryMixin {


        @Inject(at = @At("HEAD"), method = "add(ILnet/minecraft/world/item/ItemStack;)Z")
        private void init(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

            if (Minecraft.getInstance().player != null) {
                LocalPlayer player = Minecraft.getInstance().player;
                if (stack.has(DataComponents.ENCHANTMENTS)) {
                    ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);
                    
                    enchantments.keySet().forEach(enchantmentHolder -> ModEntityComponents.DISCOVERED_ENCHANTS.get(player).addEnchantment(enchantmentHolder));
                }

            }

        }


}