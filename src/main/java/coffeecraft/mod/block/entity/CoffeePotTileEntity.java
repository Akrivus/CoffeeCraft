package coffeecraft.mod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CoffeePotTileEntity extends BlockEntity {

    public CoffeePotTileEntity(BlockPos pos, BlockState state) {
        super(AddedBlockEntities.COFFEE_POT.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CoffeePotTileEntity entity) {

    }
}
