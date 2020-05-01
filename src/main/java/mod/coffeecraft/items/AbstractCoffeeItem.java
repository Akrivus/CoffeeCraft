package mod.coffeecraft.items;

import mod.coffeecraft.init.CoffeeCraft;
import net.minecraft.item.Item;

public class AbstractCoffeeItem extends Item {
	public AbstractCoffeeItem() {
		super(new Properties().group(CoffeeCraft.TAB));
	}
}
