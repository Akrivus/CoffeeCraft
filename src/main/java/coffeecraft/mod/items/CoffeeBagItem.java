package coffeecraft.mod.items;

import coffeecraft.mod.block.CoffeeBagBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CoffeeBagItem extends BlockItem {
    public CoffeeBagItem(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public BlockItemUseContext getBlockItemUseContext(BlockItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state;
        int supply = 8;
        for (int y = 0; y < context.getPos().getY(); ++y) {
            pos = pos.down(y);
            state = world.getBlockState(pos);
            if (this.getBlock() == state.getBlock()) {
                int level = state.get(CoffeeBagBlock.LEVEL);
                int demand = 8 - level;
                if (supply < demand) {
                    demand = supply;
                }
                world.setBlockState(pos, state.with(CoffeeBagBlock.LEVEL, level + demand));
                supply -= demand;
                if (supply <= 0) {
                    break;
                }
            }
        }
        state = this.getBlock().getDefaultState().with(CoffeeBagBlock.LEVEL, supply);
        if (this.placeBlock(context, state)) {
            world.setBlockState(context.getPos(), state);
        }
        return null;
    }

    @Override
    protected boolean checkPosition() {
        return false;
    }
}
