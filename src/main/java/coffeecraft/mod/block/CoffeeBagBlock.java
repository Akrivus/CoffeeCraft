package coffeecraft.mod.block;

import coffeecraft.mod.init.CoffeeCraft;
import coffeecraft.mod.tileentity.CoffeeBagTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class CoffeeBagBlock extends ContainerBlock {
	public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 8);
	
	public CoffeeBagBlock() {
		super(Properties.create(Material.WOOL).hardnessAndResistance(0.6F).sound(SoundType.CLOTH));
		this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, 8));
	}
	
	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state) {
		return new ItemStack(CoffeeCraft.Items.GREEN_COFFEE_BEANS.get(), 1);
	}

	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader world) {
		return new CoffeeBagTileEntity();
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D + state.get(LEVEL) * 1.75D, 16.0D);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
}
