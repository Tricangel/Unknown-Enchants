	package bee.unknownenchants.mixin;

import bee.unknownenchants.UnknownEnchants;
import bee.unknownenchants.config.EnchantDiscoveryMode;
import bee.unknownenchants.config.UnknownEnchantsConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

    @Mixin(Item.class)
public abstract class ItemMixin {


        @Inject(at = @At("HEAD"), method = "use", cancellable = true)
        private void init(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {

            if (UnknownEnchantsConfig.discoveryMode.equals(EnchantDiscoveryMode.ENCHANTED_BOOK) && player.getItemInHand(hand).is(Items.ENCHANTED_BOOK))
                if (UnknownEnchants.discoverEnchants(player.getItemInHand(hand))) cir.setReturnValue(InteractionResult.SUCCESS);

        }


}