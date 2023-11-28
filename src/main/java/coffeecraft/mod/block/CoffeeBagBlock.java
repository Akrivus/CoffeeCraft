package coffeecraft.mod.block;

import coffeecraft.mod.block.inventory.CoffeeBagInventory;
import coffeecraft.mod.items.AddedItems;
import coffeecraft.mod.network.CustomSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.ForgeSoundType;

import java.util.List;
import java.util.function.Supplier;

public class CoffeeBagBlock extends FallingBlock implements WorldlyContainerHolder {
    public static final IntegerProperty COUNT = IntegerProperty.create("count", 0, 9);
    private final Supplier<Item> item;

    public CoffeeBagBlock(Supplier<Item> item) {
        super(Properties.of(Material.WOOL)
                .strength(0.6F)
                .sound(SoundType.WOOL)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(COUNT, 9));
        this.item = item;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> components, TooltipFlag flag) {
        components.add(new TranslatableComponent(String.format("block.coffeecraft.coffee_bag.%s", this.getRegistryName().getPath())).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public String getDescriptionId() {
        return "block.coffeecraft.coffee_bag";
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(AddedItems.Tags.COFFEE_BEANS) && !player.isCrouching()) {
            ItemStack gift = give(stack, state, level, pos);
            if (gift.getCount() < stack.getCount()) {
                player.setItemInHand(hand, gift);
                return InteractionResult.SUCCESS;
            }
        } else {
            ItemStack gift = take(this.item.get(), state, level, pos);
            if (gift.getCount() > 0) {
                player.addItem(gift);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public WorldlyContainer getContainer(BlockState state, LevelAccessor world, BlockPos pos) {
        return new CoffeeBagInventory(this.item.get(), state, world, pos);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader world, BlockPos pos, Entity entity) {
        return state.getValue(COUNT) > 1 ? new ForgeSoundType(1.0F, 1.0F, CustomSounds.BLOCK_COFFEE_BAG, CustomSounds.BLOCK_COFFEE_BAG, CustomSounds.BLOCK_COFFEE_BAG, CustomSounds.BLOCK_COFFEE_BAG, CustomSounds.BLOCK_COFFEE_BAG) : super.getSoundType(state, world, pos, entity);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COUNT);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D + state.getValue(COUNT) * 1.75D, 16.0D);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return state.getValue(COUNT) == 0;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(COUNT);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public static ItemStack take(Item item, BlockState state, LevelAccessor level, BlockPos pos) {
        int count = state.getValue(COUNT);
        if (count > 0) {
            level.setBlock(pos, state.setValue(COUNT, count - 1), 3);
            level.playSound(null, pos, CustomSounds.BLOCK_COFFEE_BAG.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            return new ItemStack(item);
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack give(ItemStack stack, BlockState state, LevelAccessor level, BlockPos pos) {
        int count = state.getValue(COUNT);
        if (count < 9) {
            level.setBlock(pos, state.setValue(COUNT, count + 1), 3);
            level.playSound(null, pos, CustomSounds.BLOCK_COFFEE_BAG.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            stack.shrink(1);
        }
        return stack;
    }
}
