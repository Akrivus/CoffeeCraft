package coffeecraft.mod.block;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class CoffeePlantBlock extends BushBlock implements BonemealableBlock {

    public CoffeePlantBlock() {
        super(Properties.of(Material.PLANT)
                .randomTicks()
                .noCollission()
                .strength(0.0F)
                .sound(SoundType.GRASS)
                .dynamicShape());
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random rand, BlockPos pos, BlockState state) {
        BlockState plant = AddedBlocks.COFFEE_TREE.get().defaultBlockState();
        if (plant.canSurvive(level, pos) && level.isEmptyBlock(pos.above())) {
            level.setBlockAndUpdate(pos.above(0), plant.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));
            level.setBlockAndUpdate(pos.above(1), plant.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    @Override
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D).move(state.getOffset(world, pos).x, state.getOffset(world, pos).y, state.getOffset(world, pos).z);
    }
}
