package coffeecraft.mod.block;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class CoffeeBagBlock extends Block implements ISidedInventoryProvider {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 0, 8);

    public CoffeeBagBlock() {
        super(Properties.create(Material.WOOL).hardnessAndResistance(0.6F).sound(SoundType.CLOTH).notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(LEVEL, 8));
    }

    public static ItemStack getBeanItem(BlockState state) {
        Item item = CoffeeCraft.Items.GREEN_COFFEE_BEANS.get();
        Block block = state.getBlock();
        if (block == CoffeeCraft.Blocks.LIGHT_ROASTED_COFFEE_BAG.get()) {
            item = CoffeeCraft.Items.LIGHT_ROASTED_COFFEE_BEANS.get();
        }
        if (block == CoffeeCraft.Blocks.MEDIUM_ROASTED_COFFEE_BAG.get()) {
            item = CoffeeCraft.Items.MEDIUM_ROASTED_COFFEE_BEANS.get();
        }
        if (block == CoffeeCraft.Blocks.DARK_ROASTED_COFFEE_BAG.get()) {
            item = CoffeeCraft.Items.DARK_ROASTED_COFFEE_BEANS.get();
        }
        if (block == CoffeeCraft.Blocks.BURNT_COFFEE_BAG.get()) {
            item = CoffeeCraft.Items.BURNT_COFFEE_BEANS.get();
        }
        return new ItemStack(item);
    }

    public static BlockState getBagBlock(Item item) {
        Block block = CoffeeCraft.Blocks.GREEN_COFFEE_BAG.get();
        if (item == CoffeeCraft.Items.LIGHT_ROASTED_COFFEE_BEANS.get()) {
            block = CoffeeCraft.Blocks.LIGHT_ROASTED_COFFEE_BAG.get();
        }
        if (item == CoffeeCraft.Items.MEDIUM_ROASTED_COFFEE_BEANS.get()) {
            block = CoffeeCraft.Blocks.MEDIUM_ROASTED_COFFEE_BAG.get();
        }
        if (item == CoffeeCraft.Items.DARK_ROASTED_COFFEE_BEANS.get()) {
            block = CoffeeCraft.Blocks.DARK_ROASTED_COFFEE_BAG.get();
        }
        if (item == CoffeeCraft.Items.BURNT_COFFEE_BEANS.get()) {
            block = CoffeeCraft.Blocks.BURNT_COFFEE_BAG.get();
        }
        return block.getDefaultState();
    }

    public static BlockState addItem(BlockState state, ItemStack stack, PlayerEntity player) {
        int level = state.get(LEVEL);
        Item item = stack.getItem();
        if (level == 0 && CoffeeCraft.Tags.COFFEE_BEANS.is(item)) {
            if (player != null && !player.abilities.isCreativeMode) {
                stack.shrink(1);
            }
            return CoffeeBagBlock.getBagBlock(item).with(LEVEL, 1);
        } else if (level < 8) {
            if (player != null && !player.abilities.isCreativeMode) {
                stack.shrink(1);
            }
            return state.with(LEVEL, level + 1);
        } else {
            return state;
        }
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, Entity entity) {
        if (state.get(LEVEL) > 1) {
            return new SoundType(1.0F, 1.0F, CoffeeCraft.SoundEvents.BLOCK_COFFEE_BAG.get(), CoffeeCraft.SoundEvents.BLOCK_COFFEE_BAG.get(), CoffeeCraft.SoundEvents.BLOCK_COFFEE_BAG.get(), CoffeeCraft.SoundEvents.BLOCK_COFFEE_BAG.get(), CoffeeCraft.SoundEvents.BLOCK_COFFEE_BAG.get());
        } else {
            return super.getSoundType(state, world, pos, entity);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack stack = player.getHeldItem(hand);
        if (!CoffeeCraft.Tags.COFFEE_BAGS.is(stack.getItem())) {
            if (!player.isSneaking()) {
                int level = state.get(LEVEL);
                if (CoffeeCraft.Tags.COFFEE_BEANS.is(stack.getItem())) {
                    world.setBlockState(pos, CoffeeBagBlock.addItem(state, stack, player));
                    world.playSound(null, pos, CoffeeCraft.SoundEvents.BLOCK_COFFEE_BAG.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return ActionResultType.SUCCESS;
                } else if (level > 0 && level <= 8) {
                    world.setBlockState(pos, state.with(LEVEL, level - 1));
                    player.addItemStackToInventory(CoffeeBagBlock.getBeanItem(state));
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return super.onBlockActivated(state, world, pos, player, hand, result);
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext context) {
        return state.get(LEVEL) == 0;
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
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D + state.get(LEVEL) * 1.75D, 16.0D);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Override
    public ISidedInventory createInventory(BlockState state, IWorld world, BlockPos pos) {
        return new CoffeeBagBlock.CoffeeBagInventory(this.getBeanItem(state).getItem(), state, world, pos);
    }

    static class CoffeeBagInventory extends Inventory implements ISidedInventory {
        private final Item item;
        private final BlockState state;
        private final IWorld world;
        private final BlockPos pos;
        private final int level;
        private ItemStack stack;
        private int rateOfChange = 0;

        public CoffeeBagInventory(Item item, BlockState state, IWorld world, BlockPos pos) {
            super(new ItemStack(item, state.get(LEVEL)));
            this.level = state.get(LEVEL);
            this.item = item;
            this.state = state;
            this.world = world;
            this.pos = pos;
        }

        @Override
        public int getInventoryStackLimit() {
            return 8;
        }

        @Override
        public int[] getSlotsForFace(Direction side) {
            if (side == Direction.DOWN || side == Direction.UP) {
                return new int[1];
            } else {
                return new int[0];
            }
        }

        @Override
        public boolean canInsertItem(int index, ItemStack stack, Direction side) {
            if (this.rateOfChange == 0 && this.level < 8 && side == Direction.UP) {
                if (CoffeeCraft.Tags.COFFEE_BEANS.is(stack.getItem())) {
                    if (this.level == 0 || this.item == stack.getItem()) {
                        this.stack = stack;
                        this.rateOfChange += 1;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canExtractItem(int index, ItemStack stack, Direction side) {
            if (this.rateOfChange == 0 && this.level > 0 && side == Direction.DOWN) {
                if (stack.getItem() == this.item) {
                    this.rateOfChange -= 1;
                    return true;
                }
            }
            return false;
        }

        @Override
        public void markDirty() {
            BlockState state = this.state;
            System.out.println(this.rateOfChange);
            if (this.rateOfChange > 0) {
                state = CoffeeBagBlock.addItem(this.state, this.stack, null);
            } else if (this.rateOfChange < 0) {
                state = this.state.with(LEVEL, this.level - 1);
            }
            this.world.setBlockState(this.pos, state, 3);
        }
    }
}
