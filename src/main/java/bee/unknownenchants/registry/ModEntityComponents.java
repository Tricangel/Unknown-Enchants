package bee.unknownenchants.registry;

import bee.unknownenchants.UnknownEnchants;
import bee.unknownenchants.cca.DiscoveredEnchantsComponent;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class ModEntityComponents implements EntityComponentInitializer {

    public static ComponentKey<DiscoveredEnchantsComponent> DISCOVERED_ENCHANTS =
            ComponentRegistry.getOrCreate(UnknownEnchants.id("discovered_enchants"), DiscoveredEnchantsComponent.class);


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {

        entityComponentFactoryRegistry.registerForPlayers(DISCOVERED_ENCHANTS, DiscoveredEnchantsComponent::new, RespawnCopyStrategy.ALWAYS_COPY);

    }
}
