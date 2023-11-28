package coffeecraft.mod.items;

import coffeecraft.mod.block.CoffeeBagBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;

public class CoffeeBagItem extends BlockItem {
    public CoffeeBagItem(Block block, Item.Properties properties) {
        super(block, properties
                .stacksTo(1)
                .durability(9));
    }

    @Override
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state;
        int supply = 8;
        for (int y = 0; y < context.getClickedPos().getY(); ++y) {
            pos = pos.below(y);
            state = level.getBlockState(pos);
            if (this.getBlock() == state.getBlock()) {
                int size = state.getValue(CoffeeBagBlock.COUNT);
                int demand = 8 - size;
                if (supply < demand) {
                    demand = supply;
                }
                level.setBlockAndUpdate(pos, state.setValue(CoffeeBagBlock.COUNT, size + demand));
                supply -= demand;
                if (supply <= 0) {
                    break;
                }
            }
        }
        state = this.getBlock().defaultBlockState().setValue(CoffeeBagBlock.COUNT, supply);
        if (this.placeBlock(context, state)) {
            level.setBlockAndUpdate(context.getClickedPos(), state);
        }
        return null;
    }
}
