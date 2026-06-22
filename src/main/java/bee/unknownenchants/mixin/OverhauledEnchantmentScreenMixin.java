	package bee.unknownenchants.mixin;

import bee.unknownenchants.config.EnchantDiscoveryMode;
import bee.unknownenchants.config.UnknownEnchantsConfig;
import bee.unknownenchants.registry.ModEntityComponents;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import moriyashiine.enchancement.client.gui.screens.inventory.OverhauledEnchantmentScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

    @Mixin(OverhauledEnchantmentScreen.class)
    public class OverhauledEnchantmentScreenMixin {

        @WrapOperation(at = @At(value = "INVOKE", target = "Lmoriyashiine/enchancement/common/util/EnchancementUtil;getTranslationKey(Lnet/minecraft/core/Holder;)Ljava/lang/String;"), method = "extractMain")
        private static String init(Holder<Enchantment> enchantment, Operation<String> original) {

            if (!UnknownEnchantsConfig.discoveryMode.equals(EnchantDiscoveryMode.NONE) && Minecraft.getInstance().player != null) {
                LocalPlayer player = Minecraft.getInstance().player;

                if (ModEntityComponents.DISCOVERED_ENCHANTS.get(player).hasEnchantment(enchantment)) {

                    return original.call(enchantment);

                }

                else {
                    return original.call(enchantment) + "overrideThis";
                }

            }

            return original.call(enchantment);
        }

        @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;"), method = "extractMain")
        private static MutableComponent wawa(String key, Operation<MutableComponent> original) {

            if (!UnknownEnchantsConfig.discoveryMode.equals(EnchantDiscoveryMode.NONE)) {

                if (key.contains("overrideThis")) {
                    return original.call(key.replace("overrideThis", "")).withStyle(ChatFormatting.OBFUSCATED);
                }
            }

            return original.call(key);
        }
    }