package coffeecraft.mod.block;

import coffeecraft.mod.block.inventory.DryingMatInventory;
import coffeecraft.mod.items.AddedItems;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.Map;
import java.util.Random;

import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class DryingMatBlock extends Block implements WorldlyContainerHolder {
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 4);
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    protected static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (p_55164_) -> {
        p_55164_.put(Direction.NORTH, NORTH);
        p_55164_.put(Direction.EAST, EAST);
        p_55164_.put(Direction.SOUTH, SOUTH);
        p_55164_.put(Direction.WEST, WEST);
    }));

    public DryingMatBlock() {
        super(Properties.of(Material.WOOL)
                .randomTicks()
                .strength(0.1F)
                .sound(SoundType.WOOL));
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(STAGE, 0)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false));
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        if (state.getValue(STAGE) > 0 && state.getValue(STAGE) < 4 && level.getMaxLocalRawBrightness(pos) > 8) {
            level.setBlockAndUpdate(pos, state.cycle(STAGE));
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);
        if (state.getValue(STAGE) == 0 && stack.getItem() == AddedItems.COFFEE_CHERRIES.get()) {
            level.setBlockAndUpdate(pos, state.setValue(STAGE, 1));
            stack.shrink(player.getAbilities().instabuild ? 0 : 1);
            return InteractionResult.SUCCESS;
        } else if (state.getValue(STAGE) == 4) {
            popResource(level, pos, new ItemStack(AddedItems.GREEN_COFFEE_BEAN.get(), 1));
            level.setBlockAndUpdate(pos, state.setValue(STAGE, 0));
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float distance) {
        super.fallOn(level, state, pos, entity, distance);
        state = state.cycle(STAGE);
        if (state.getValue(STAGE) == 4) {
            popResource(level, pos, new ItemStack(AddedItems.GREEN_COFFEE_BEAN.get(), 1));
            level.setBlockAndUpdate(pos, state.setValue(STAGE, 0));
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        level.setBlockAndUpdate(pos, state
                .setValue(NORTH, canConnectTo(level, pos.north()))
                .setValue(EAST, canConnectTo(level, pos.east()))
                .setValue(SOUTH, canConnectTo(level, pos.south()))
                .setValue(WEST, canConnectTo(level, pos.west())));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE, NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return super.getStateForPlacement(context).setValue(STAGE, 0)
                .setValue(NORTH, this.canConnectTo(level, pos.north()))
                .setValue(EAST, this.canConnectTo(level, pos.east()))
                .setValue(SOUTH, this.canConnectTo(level, pos.south()))
                .setValue(WEST, this.canConnectTo(level, pos.west()));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return facing.getAxis().getPlane() == Direction.Plane.HORIZONTAL ? state.setValue(FACING_TO_PROPERTY_MAP.get(facing), canConnectTo(world, facingPos)) : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getCollisionShape(state, world, pos, context);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(STAGE);
    }

    @Override
    public WorldlyContainer getContainer(BlockState state, LevelAccessor world, BlockPos pos) {
        return new DryingMatInventory(state, world, pos, state.getValue(STAGE) == 4);
    }

    private boolean canConnectTo(BlockGetter world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.is(this);
    }
}
