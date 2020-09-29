package coffeecraft.mod.block;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Predicate;

public class DryingMatBlock extends Block implements ISidedInventoryProvider {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 4);
    public static final Predicate<BlockState> DRYING_AMPLIFIER = (input) -> {
        if (input.getBlock().equals(Blocks.SCAFFOLDING) || input.getBlock().equals(Blocks.HOPPER) || input.getBlock().isIn(BlockTags.FENCES)) {
            return true;
        } else {
            return false;
        }
    };

    public DryingMatBlock() {
        super(Properties.create(Material.WOOL).tickRandomly().hardnessAndResistance(0.1F).sound(SoundType.CLOTH));
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (state.get(LEVEL) > 0 && state.get(LEVEL) < 4 && world.getLight(pos) > 8 && !world.isRaining()) {
            if (DRYING_AMPLIFIER.test(world.getBlockState(pos)) || rand.nextInt(3) == 0) {
                world.setBlockState(pos, state.cycle(LEVEL));
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack stack = player.getHeldItem(hand);
        if (state.get(LEVEL) == 0 && stack.getItem() == CoffeeCraft.Items.COFFEE_CHERRIES.get()) {
            world.playSound(null, pos, SoundEvents.BLOCK_BAMBOO_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
            world.setBlockState(pos, state.with(LEVEL, 1));
            if (!player.abilities.isCreativeMode) {
                stack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        } else if (state.get(LEVEL) == 4) {
            spawnAsEntity(world, pos, new ItemStack(CoffeeCraft.Items.DRIED_COFFEE_CHERRIES.get(), 1));
            world.setBlockState(pos, state.with(LEVEL, 0));
            return ActionResultType.SUCCESS;
        }
        return super.onBlockActivated(state, world, pos, player, hand, result);
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
        return state.get(LEVEL);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return this.getCollisionShape(state, world, pos, context);
    }

    @Override
    public ISidedInventory createInventory(BlockState state, IWorld world, BlockPos pos) {
        return new DryingMatInventory(state, world, pos, state.get(LEVEL) == 4);
    }

    static class DryingMatInventory extends Inventory implements ISidedInventory {
        private final BlockState state;
        private final IWorld world;
        private final BlockPos pos;
        private boolean hasPits;

        public DryingMatInventory(BlockState state, IWorld world, BlockPos pos, boolean hasPits) {
            super(hasPits ? new ItemStack(CoffeeCraft.Items.DRIED_COFFEE_CHERRIES.get()) : ItemStack.EMPTY);
            this.state = state;
            this.world = world;
            this.pos = pos;
            this.hasPits = hasPits;
        }

        @Override
        public int getInventoryStackLimit() {
            return 1;
        }

        @Override
        public int[] getSlotsForFace(Direction side) {
            if (this.hasPits && side == Direction.DOWN) {
                return new int[1];
            } else {
                return new int[0];
            }
        }

        @Override
        public boolean canInsertItem(int index, ItemStack stack, Direction side) {
            return false;
        }

        @Override
        public boolean canExtractItem(int index, ItemStack stack, Direction side) {
            return this.hasPits && side == Direction.DOWN && stack.getItem() == CoffeeCraft.Items.DRIED_COFFEE_CHERRIES.get();
        }

        @Override
        public void markDirty() {
            this.world.setBlockState(this.pos, this.state.with(LEVEL, 0), 3);
            this.hasPits = false;
        }
    }
}
