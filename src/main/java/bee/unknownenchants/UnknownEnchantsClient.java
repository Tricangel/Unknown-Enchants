package bee.unknownenchants;

import bee.unknownenchants.registry.DiscoverEnchantToast;
import bee.unknownenchants.registry.ToastS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

public class UnknownEnchantsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ClientPlayNetworking.registerGlobalReceiver(ToastS2CPacket.TYPE, (packet, context) -> {
            LocalPlayer player = context.player();

            if (player.getStringUUID().equals(packet.packet().uuid())) {

                Minecraft.getInstance().getToastManager().addToast(new DiscoverEnchantToast(packet.packet().enchantmentName()));

            }

        });

    }
}
