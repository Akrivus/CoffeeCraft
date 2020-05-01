package mod.coffeecraft.block;

import java.util.Random;

import mod.coffeecraft.init.CoffeeCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CoffeePlantBlock extends DoublePlantBlock implements IGrowable {
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);
	
	public CoffeePlantBlock() {
		super(Properties.create(Material.TALL_PLANTS).tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
	      this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(AGE, 0));
	}
	
	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (this.canGrow(world, pos, state, world.isRemote())) {
			this.grow(world, rand, pos, state);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		if (state.get(AGE) == 3) {
			spawnAsEntity(world, pos, new ItemStack(CoffeeCraft.Items.COFFEE_CHERRIES.get(), world.rand.nextInt(5) + 5));
			world.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
			world.setBlockState(pos, state.with(AGE, 0));
			return ActionResultType.SUCCESS;
		} else {
			return super.onBlockActivated(state, world, pos, player, hand, result);
		}
	}
	
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		BlockState fromState = world.getBlockState(fromPos);
		if (fromState.getBlock() == CoffeeCraft.Blocks.COFFEE_PLANT.get() && ((state.get(HALF) == DoubleBlockHalf.UPPER && fromPos.equals(pos.down())) || (state.get(HALF) == DoubleBlockHalf.LOWER && fromPos.equals(pos.up())))) {
			world.setBlockState(pos, state.with(AGE, fromState.get(AGE)));
		}
	}
	
	@Override
	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
		return state.get(AGE) != 1 && state.get(AGE) != 3;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, BlockState state) {
		return state.get(AGE) != 1 && state.get(AGE) != 3;
	}

	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		world.setBlockState(pos, state.cycle(AGE));
	}
	
	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		return false;
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, AGE);
    }
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		Vec3d offset = state.getOffset(world, pos);
		return Block.makeCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D).withOffset(offset.x, offset.y, offset.z);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		Vec3d offset = state.getOffset(world, pos);
		return Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D).withOffset(offset.x, offset.y, offset.z);
	}
}
