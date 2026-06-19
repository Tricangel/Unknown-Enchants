package bee.unknownenchants;

import bee.unknownenchants.config.EnchantDiscoveryMode;
import bee.unknownenchants.config.UnknownEnchantsConfig;
import bee.unknownenchants.registry.DiscoverEnchantToast;
import bee.unknownenchants.registry.ModEntityComponents;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.AdvancementToast;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.slf4j.LoggerFactory;

public class UnknownEnchants implements ModInitializer {
	public static final String MOD_ID = "unknown-enchants";

	@Override
	public void onInitialize() {
		LoggerFactory.getLogger("Unknown Enchants").info("Whats an enchant?");
		MidnightConfig.init(MOD_ID, UnknownEnchantsConfig.class);
	}

	public static boolean discoverEnchants(ItemStack stack) {
		if (Minecraft.getInstance().player != null) {
			boolean newEnchant = false;
			LocalPlayer player = Minecraft.getInstance().player;
			if (stack.has(DataComponents.ENCHANTMENTS) && UnknownEnchantsConfig.discoveryMode.equals(EnchantDiscoveryMode.ENCHANTED_ITEM)) {
				ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);

				for (Holder<Enchantment> enchantment : enchantments.keySet()) {
					if (!ModEntityComponents.DISCOVERED_ENCHANTS.get(player).hasEnchantment(enchantment)) {
						ModEntityComponents.DISCOVERED_ENCHANTS.get(player).addEnchantment(enchantment);
						Minecraft.getInstance().getToastManager().addToast(new DiscoverEnchantToast(enchantment));
						newEnchant = true;
					}
				}
			} else if (stack.has(DataComponents.STORED_ENCHANTMENTS)) {
				ItemEnchantments enchantments = stack.get(DataComponents.STORED_ENCHANTMENTS);

				for (Holder<Enchantment> enchantment : enchantments.keySet()) {
					if (!ModEntityComponents.DISCOVERED_ENCHANTS.get(player).hasEnchantment(enchantment)) {
						ModEntityComponents.DISCOVERED_ENCHANTS.get(player).addEnchantment(enchantment);
						Minecraft.getInstance().getToastManager().addToast(new DiscoverEnchantToast(enchantment));
						newEnchant = true;
					}
				}
			}

            return newEnchant;

		}
		return false;
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}