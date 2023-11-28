package coffeecraft.mod.items;

import coffeecraft.mod.CoffeeCraft;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

import net.minecraft.world.item.Item.Properties;

public class BrewableCoffeeItem extends Item {
    private final int color;

    public BrewableCoffeeItem(int color) {
        super(new Properties().tab(CoffeeCraft.CreativeModeTab));
        this.color = color;
    }
}
