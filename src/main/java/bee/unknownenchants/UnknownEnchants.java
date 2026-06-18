package bee.unknownenchants;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.LoggerFactory;

public class UnknownEnchants implements ModInitializer {
	public static final String MOD_ID = "unknown-enchants";

	@Override
	public void onInitialize() {
		LoggerFactory.getLogger("Unknown Enchants").info("Whats an enchant?");
	}

	public static Identifier id(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}