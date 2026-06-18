	package bee.unknownenchants.mixin;

import bee.unknownenchants.registry.ModEntityComponents;
import moriyashiine.enchancement.client.event.config.EnchantmentDescriptionsClientEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

    @Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private static void init(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {

        if (level.isClientSide()) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.has(DataComponents.ENCHANTMENTS)) {
                ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);

                enchantments.keySet().forEach(enchantmentHolder -> {
                    ModEntityComponents.DISCOVERED_ENCHANTS.get(player).addEnchantment(enchantmentHolder);
                });
            }

        }

    }
}