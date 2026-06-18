	package bee.unknownenchants.mixin;

import bee.unknownenchants.registry.ModEntityComponents;
import moriyashiine.enchancement.client.event.config.EnchantmentDescriptionsClientEvent;
import moriyashiine.enchancement.common.util.EnchancementUtil;
import moriyashiine.strawberrylib.api.module.SLibClientUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentDescriptionsClientEvent.class)
public class EnchantmentDescriptionsClientEventMixin {

	@Inject(at = @At("RETURN"), method = "getDescription", cancellable = true)
	private static void init(Component component, Holder<Enchantment> enchantment, CallbackInfoReturnable<List<Component>> cir) {

		if (Minecraft.getInstance().player != null) {
			LocalPlayer player = Minecraft.getInstance().player;

			if (!ModEntityComponents.DISCOVERED_ENCHANTS.get(player).hasEnchantment(enchantment)) {
				String translationKey = EnchancementUtil.getTranslationKey(enchantment);
				ComponentContents var4 = component.getContents();
				if (var4 instanceof TranslatableContents translatable) {
					if (translatable.getKey().equals(translationKey)) {
						MutableComponent description = Component.translatable(translationKey + ".desc").withStyle(ChatFormatting.DARK_GRAY);
						if (!description.getString().isEmpty()) {
							cir.setReturnValue(SLibClientUtils.wrapText(Component.literal(" - ").withStyle(ChatFormatting.GRAY).append(description).withStyle(ChatFormatting.OBFUSCATED)));
						}
					}
				}
			}

		}

	}
}