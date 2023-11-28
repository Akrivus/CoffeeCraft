package coffeecraft.mod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;

public class CoffeePotItem extends BlockItem {
    public CoffeePotItem(Block block, Item.Properties properties) {
        super(block, properties
                .stacksTo(1)
                .durability(24));
    }
}
