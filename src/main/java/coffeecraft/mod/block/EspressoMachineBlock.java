package coffeecraft.mod.block;

import coffeecraft.mod.block.entity.EspressoMachineTileEntity;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class EspressoMachineBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty BREWING = BooleanProperty.create("brewing");
    public static final BooleanProperty PORTAFILTER = BooleanProperty.create("portafilter");
    public static final BooleanProperty CUP = BooleanProperty.create("cup");

    public EspressoMachineBlock() {
        super(Properties.of(Material.METAL, MaterialColor.METAL)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(FACING, Direction.NORTH)
                .setValue(BREWING, false)
                .setValue(PORTAFILTER, false)
                .setValue(CUP, false));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EspressoMachineTileEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, BREWING, PORTAFILTER, CUP);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case SOUTH:
                return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
            case WEST:
                return Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
            case EAST:
                return Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
            default:
                return Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getCollisionShape(state, world, pos, context);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
