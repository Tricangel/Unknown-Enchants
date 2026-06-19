package bee.unknownenchants.registry;

import bee.unknownenchants.UnknownEnchants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.List;

public record ToastS2CPacket(ToastPacket packet) implements CustomPacketPayload {
    public static final Identifier packetId = UnknownEnchants.id("discovered_enchants_sync");
    public static final Type<ToastS2CPacket> TYPE = new Type<>(packetId);



public static final StreamCodec<RegistryFriendlyByteBuf, ToastS2CPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.fromCodec(ToastPacket.CODEC), ToastS2CPacket::packet, ToastS2CPacket::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public record ToastPacket(String uuid, String enchantmentName) {
        public static final Codec<ToastPacket> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("uuid").forGetter(ToastPacket::uuid),
                Codec.STRING.fieldOf("enchantmentName").forGetter(ToastPacket::enchantmentName)
        ).apply(instance, ToastPacket::new));
    }

}
