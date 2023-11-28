package coffeecraft.mod.items;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.block.AddedBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class CoffeeBeanItem extends Item {
    private static final Map<Item, Block> BEAN_TO_BAG = new HashMap<>();

    public CoffeeBeanItem() {
        super(new Properties().tab(CoffeeCraft.CreativeModeTab));
    }

    public static Block getBag(Item item) {
        return BEAN_TO_BAG.get(item);
    }

    public static void registerBeanToBag() {
        BEAN_TO_BAG.put(AddedItems.GREEN_COFFEE_BEAN.get(), AddedBlocks.GREEN_COFFEE_BAG.get());
        BEAN_TO_BAG.put(AddedItems.LIGHT_COFFEE_BEAN.get(), AddedBlocks.LIGHT_COFFEE_BAG.get());
        BEAN_TO_BAG.put(AddedItems.MEDIUM_COFFEE_BEAN.get(), AddedBlocks.MEDIUM_COFFEE_BAG.get());
        BEAN_TO_BAG.put(AddedItems.DARK_COFFEE_BEAN.get(), AddedBlocks.DARK_COFFEE_BAG.get());
        BEAN_TO_BAG.put(AddedItems.BURNT_COFFEE_BEAN.get(), AddedBlocks.BURNT_COFFEE_BAG.get());
    }
}
