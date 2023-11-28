package coffeecraft.mod.items;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.block.AddedBlocks;
import net.minecraft.world.item.ItemNameBlockItem;

public class CoffeeSeedItem extends ItemNameBlockItem {
    public CoffeeSeedItem() {
        super(AddedBlocks.COFFEE_SEEDLING.get(), new Properties().tab(CoffeeCraft.CreativeModeTab));
    }
}
