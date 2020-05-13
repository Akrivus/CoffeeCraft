package coffeecraft.mod.items;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.item.Item;

public class AbstractCoffeeItem extends Item {
	public AbstractCoffeeItem() {
		super(new Properties().group(CoffeeCraft.TAB));
	}
}
