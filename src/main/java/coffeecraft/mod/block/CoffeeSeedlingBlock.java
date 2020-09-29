package coffeecraft.mod.block;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CoffeeSeedlingBlock extends BushBlock implements IGrowable {

    public CoffeeSeedlingBlock() {
        super(Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (canGrow(world, pos, state, world.isRemote())) {
            grow(world, rand, pos, state);
        }
    }

    @Override
    public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        world.setBlockState(pos, CoffeeCraft.Blocks.COFFEE_SAPLING.get().getDefaultState());
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        return false;
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        Vec3d offset = state.getOffset(world, pos);
        return Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 12.0D, 6.0D, 12.0D).withOffset(offset.x, offset.y, offset.z);
    }

}
