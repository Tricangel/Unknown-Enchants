package bee.unknownenchants.cca;

import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v8.component.CardinalComponent;

import java.util.ArrayList;
import java.util.List;

public class DiscoveredEnchantsComponent implements CardinalComponent {
    private List<Holder<Enchantment>> enchantments = new ArrayList<>();

    public List<Holder<Enchantment>> getEnchantments() {
        return enchantments;
    }

    public void addEnchantment(Holder<Enchantment> enchantment) {
        enchantments.add(enchantment);
    }

    public boolean hasEnchantment(Holder<Enchantment> enchantment) {
        return enchantments.contains(enchantment);
    }

    @Override
    public void readData(ValueInput valueInput) {
        valueInput.read("enchantments", Enchantment.CODEC.listOf())
                .ifPresent(holders -> enchantments = holders);
    }

    @Override
    public void writeData(ValueOutput valueOutput) {
        valueOutput.store("enchantments", Enchantment.CODEC.listOf(), enchantments);
    }

}
