package bee.unknownenchants;

import bee.unknownenchants.config.EnchantDiscoveryMode;
import bee.unknownenchants.config.UnknownEnchantsConfig;
import bee.unknownenchants.registry.DiscoverEnchantToast;
import bee.unknownenchants.registry.ToastS2CPacket;
import bee.unknownenchants.registry.ModEntityComponents;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.slf4j.LoggerFactory;

public class UnknownEnchants implements ModInitializer {
	public static final String MOD_ID = "unknown-enchants";

	@Override
	public void onInitialize() {
		LoggerFactory.getLogger("Unknown Enchants").info("Whats an enchant?");
		MidnightConfig.init(MOD_ID, UnknownEnchantsConfig.class);

		PayloadTypeRegistry.clientboundPlay().register(ToastS2CPacket.TYPE, ToastS2CPacket.STREAM_CODEC);

	}

	public static boolean discoverEnchants(ItemStack stack, Player player) {
		boolean newEnchant = false;
			if (stack.has(DataComponents.ENCHANTMENTS) && UnknownEnchantsConfig.discoveryMode.equals(EnchantDiscoveryMode.ENCHANTED_ITEM)) {
				ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);

				for (Holder<Enchantment> enchantment : enchantments.keySet()) {
					if (!ModEntityComponents.DISCOVERED_ENCHANTS.get(player).hasEnchantment(enchantment)) {
						ModEntityComponents.DISCOVERED_ENCHANTS.get(player).addEnchantment(enchantment);
						ServerPlayNetworking.send((ServerPlayer) player, new ToastS2CPacket(new ToastS2CPacket.ToastPacket(player.getStringUUID(), "enchantment." + enchantment.getRegisteredName().replace("/", ".").replace(":", "."))));
						newEnchant = true;
					}
				}
			}
			if (stack.has(DataComponents.STORED_ENCHANTMENTS)) {
				ItemEnchantments enchantments = stack.get(DataComponents.STORED_ENCHANTMENTS);
				for (Holder<Enchantment> enchantment : enchantments.keySet()) {
					if (!ModEntityComponents.DISCOVERED_ENCHANTS.get(player).hasEnchantment(enchantment)) {
						ModEntityComponents.DISCOVERED_ENCHANTS.get(player).addEnchantment(enchantment);
						ServerPlayNetworking.send((ServerPlayer) player, new ToastS2CPacket(new ToastS2CPacket.ToastPacket(player.getStringUUID(), "enchantment." + enchantment.getRegisteredName().replace("/", ".").replace(":", "."))));
						newEnchant = true;
					}
				}
			}
		return newEnchant;
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}