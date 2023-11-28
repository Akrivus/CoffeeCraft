package coffeecraft.mod.block.inventory;

import coffeecraft.mod.block.CoffeeBagBlock;
import coffeecraft.mod.items.AddedItems;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class CoffeeBagInventory extends SimpleContainer implements WorldlyContainer {
    private final Item item;
    private final BlockState state;
    private final LevelAccessor level;
    private final BlockPos pos;
    private final int count;
    private ItemStack stack;
    private int rateOfChange = 0;

    public CoffeeBagInventory(Item item, BlockState state, LevelAccessor level, BlockPos pos) {
        super(new ItemStack(item, state.getValue(CoffeeBagBlock.COUNT)));
        this.count = state.getValue(CoffeeBagBlock.COUNT);
        this.item = item;
        this.state = state;
        this.level = level;
        this.pos = pos;
    }

    @Override
    public int getMaxStackSize() {
        return 8;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[this.isVertical(side) ? 1 : 0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction side) {
        if (this.rateOfChange == 0 && this.count < 8 && side == Direction.UP) {
            if (AddedItems.Tags.COFFEE_BEANS.contains(stack.getItem())) {
                if (this.count == 0 || this.item == stack.getItem()) {
                    this.stack = stack;
                    this.rateOfChange += 1;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction side) {
        if (this.rateOfChange == 0 && this.count > 0 && side == Direction.DOWN) {
            if (stack.getItem() == this.item) {
                this.rateOfChange -= 1;
                return true;
            }
        }
        return false;
    }

    @Override
    public void setChanged() {
        if (this.rateOfChange > 0)
            CoffeeBagBlock.give(this.stack, this.state, this.level, this.pos);
        if (this.rateOfChange < 0)
            CoffeeBagBlock.take(this.item, this.state, this.level, this.pos);
    }

    private boolean isVertical(Direction side) {
        return !this.isEmpty() && side == Direction.DOWN;
    }
}
