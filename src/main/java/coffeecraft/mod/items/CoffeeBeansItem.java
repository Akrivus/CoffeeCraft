package coffeecraft.mod.items;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class CoffeeBeansItem extends Item {
    public CoffeeBeansItem() {
        super(new Properties().group(CoffeeCraft.TAB));
    }

    public static class Green extends BlockNamedItem {
        public Green() {
            super(CoffeeCraft.Blocks.COFFEE_SEEDLING.get(), new Properties().group(CoffeeCraft.TAB));
        }
    }
}
