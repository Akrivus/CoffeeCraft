package coffeecraft.mod.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.function.Supplier;

public class CoffeeJarBlock extends Block {
    public static final IntegerProperty FILL = IntegerProperty.create("fill", 0, 9);
    private final Supplier<Item> item;

    public CoffeeJarBlock(Supplier<Item> item) {
        super(Properties.of(Material.GLASS)
                .sound(SoundType.GLASS)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition
                .any()
                .setValue(FILL, 9));
        this.item = item;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> components, TooltipFlag flag) {
        components.add(new TranslatableComponent(String.format("block.coffeecraft.coffee_jar.%s", this.getRegistryName().getPath())).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public String getDescriptionId() {
        return "block.coffeecraft.coffee_jar";
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return this.getCollisionShape(state, world, pos, context);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILL);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState otherState, Direction facing) {
        return otherState.is(this) ? true : super.skipRendering(state, otherState, facing);
    }
}
