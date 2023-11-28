package coffeecraft.mod.items;

import coffeecraft.mod.CoffeeCraft;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class CoffeeCherryItem extends Item {
    public CoffeeCherryItem() {
        super(new Item.Properties().tab(CoffeeCraft.CreativeModeTab).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).alwaysEat().build()));
    }
}
