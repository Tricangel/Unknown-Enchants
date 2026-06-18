	package bee.unknownenchants.mixin;

import bee.unknownenchants.registry.ModEntityComponents;
import moriyashiine.enchancement.client.event.config.EnchantmentDescriptionsClientEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

	@Mixin(EnchantmentDescriptionsClientEvent.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "getDescription", cancellable = true)
	private static void init(Component component, Holder<Enchantment> enchantment, CallbackInfoReturnable<List<Component>> cir) {

		if (Minecraft.getInstance().player != null) {
			LocalPlayer player = Minecraft.getInstance().player;

			if (!ModEntityComponents.DISCOVERED_ENCHANTS.get(player).hasEnchantment(enchantment)) {
				cir.setReturnValue(List.of(Component.literal("wawa")));
			}

		}

	}
}