package coffeecraft.mod.block;

import coffeecraft.mod.items.AddedItems;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class CoffeeTreeBlock extends DoublePlantBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public CoffeeTreeBlock() {
        super(Properties.of(Material.REPLACEABLE_PLANT)
                .randomTicks()
                .strength(0.0F)
                .sound(SoundType.GRASS)
                .dynamicShape());
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(AGE, 0));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        if (this.isValidBonemealTarget(level, pos, state, level.isClientSide())) {
            this.performBonemeal(level, rand, pos, state);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (state.getValue(AGE) == 3) {
            popResource(level, pos, new ItemStack(AddedItems.COFFEE_CHERRIES.get(), level.random.nextInt(4) + 2));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlockAndUpdate(pos, state.setValue(AGE, 0));
            return InteractionResult.SUCCESS;
        } else {
            return super.use(state, level, pos, player, hand, result);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        BlockState fromState = level.getBlockState(fromPos);
        if (fromState.getBlock() == AddedBlocks.COFFEE_TREE.get() && ((state.getValue(HALF) == DoubleBlockHalf.UPPER && fromPos.equals(pos.below())) || (state.getValue(HALF) == DoubleBlockHalf.LOWER && fromPos.equals(pos.above())))) {
            level.setBlockAndUpdate(pos, state.setValue(AGE, fromState.getValue(AGE)));
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter world, BlockPos pos, BlockState state, boolean isClient) {
        if (state.getValue(AGE) == 1) {
            for (int x = -3; x < 4; ++x) {
                for (int z = -3; z < 4; ++z) {
                    if (x != 0 && z != 0) {
                        BlockState check = world.getBlockState(pos.offset(x, 0, z));
                        if (check.getBlock() instanceof CoffeeTreeBlock && check.getValue(AGE) == 1) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            return state.getValue(AGE) != 3;
        }
    }

    @Override
    public boolean isBonemealSuccess(Level level, Random rand, BlockPos pos, BlockState state) {
        return state.getValue(AGE) != 1 && state.getValue(AGE) != 3;
    }

    @Override
    public void performBonemeal(ServerLevel level, Random rand, BlockPos pos, BlockState state) {
        if (state.getValue(AGE) > 0 || rand.nextInt(20) == 0) {
            level.setBlockAndUpdate(pos, state.cycle(AGE));
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(world, pos);
        return Block.box(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D).move(offset.x, offset.y, offset.z);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(world, pos);
        return Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D).move(offset.x, offset.y, offset.z);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, AGE);
    }
}
