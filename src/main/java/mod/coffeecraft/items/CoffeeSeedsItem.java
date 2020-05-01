package mod.coffeecraft.items;

import mod.coffeecraft.init.CoffeeCraft;
import net.minecraft.item.BlockNamedItem;

public class CoffeeSeedsItem extends BlockNamedItem {
	public CoffeeSeedsItem() {
		super(CoffeeCraft.Blocks.COFFEE_SEEDLING.get(), new Properties().group(CoffeeCraft.TAB));
	}
}
