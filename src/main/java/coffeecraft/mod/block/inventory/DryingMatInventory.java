package coffeecraft.mod.block.inventory;

import coffeecraft.mod.block.DryingMatBlock;
import coffeecraft.mod.items.AddedItems;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class DryingMatInventory extends SimpleContainer implements WorldlyContainer {
    private final BlockState state;
    private final LevelAccessor level;
    private final BlockPos pos;
    private boolean isNotEmpty;

    public DryingMatInventory(BlockState state, LevelAccessor level, BlockPos pos, boolean isNotEmpty) {
        super(isNotEmpty ? new ItemStack(AddedItems.GREEN_COFFEE_BEAN.get()) : ItemStack.EMPTY);
        this.state = state;
        this.level = level;
        this.pos = pos;
        this.isNotEmpty = isNotEmpty;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[this.isVertical(side) ? 1 : 0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction side) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction side) {
        return this.isNotEmpty && side == Direction.DOWN && stack.getItem() == AddedItems.GREEN_COFFEE_BEAN.get();
    }

    @Override
    public void setChanged() {
        this.level.setBlock(this.pos, this.state.setValue(DryingMatBlock.STAGE, 0), 3);
        this.isNotEmpty = false;
    }

    private boolean isVertical(Direction side) {
        return this.isNotEmpty && side == Direction.DOWN;
    }
}
