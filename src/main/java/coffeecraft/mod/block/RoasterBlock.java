package coffeecraft.mod.block;

import coffeecraft.mod.tileentity.RoasterTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class RoasterBlock extends ContainerBlock {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty ANIMATED = BooleanProperty.create("animated");

    public RoasterBlock() {
        super(Properties.create(Material.WOOL, MaterialColor.WOOL).notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(ANIMATED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (this.isValidPosition(this.getDefaultState(), context.getWorld(), context.getPos())) {
            return this.getDefaultState().with(FACING, context.getWorld().getBlockState(context.getPos().down()).get(FACING));
        } else {
            return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if (state.isValidPosition(world, currentPos)) {
            state = state.with(FACING, world.getBlockState(currentPos.down()).get(FACING));
            if (world.getBlockState(currentPos.down()).get(AbstractFurnaceBlock.LIT)) {
                return state.with(ANIMATED, true);
            } else {
                return state.with(ANIMATED, false);
            }
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() instanceof AbstractFurnaceBlock;
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = world.getTileEntity(pos);
            if (tileentity instanceof RoasterTileEntity) {
                InventoryHelper.dropInventoryItems(world, pos, (RoasterTileEntity) tileentity);
                world.updateComparatorOutputLevel(pos, this);
            }
            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ANIMATED);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new RoasterTileEntity();
    }

    @Override
    public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        if (state.get(FACING).getAxis() == Axis.Z) {
            return Block.makeCuboidShape(1.0D, 0.0D, 3.0D, 15.0D, 10.0D, 13.0D);
        } else {
            return Block.makeCuboidShape(3.0D, 0.0D, 1.0D, 13.0D, 10.0D, 15.0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return this.getCollisionShape(state, world, pos, context);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
