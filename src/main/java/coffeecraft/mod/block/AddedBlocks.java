package coffeecraft.mod.block;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.block.entity.CoffeePotTileEntity;
import coffeecraft.mod.block.entity.EspressoMachineTileEntity;
import coffeecraft.mod.block.entity.GrinderTileEntity;
import coffeecraft.mod.block.entity.RoasterTileEntity;
import coffeecraft.mod.items.AddedItems;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AddedBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CoffeeCraft.ID);
    public static final RegistryObject<Block> TIP_JAR = REGISTRY.register("tip_jar", () -> new TipJarBlock());
    public static final RegistryObject<Block> FLAVOR_PUMP = REGISTRY.register("flavor_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> SIMPLE_SYRUP_PUMP = REGISTRY.register("simple_syrup_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> CARAMEL_PUMP = REGISTRY.register("caramel_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> CHOCOLATE_SYRUP_PUMP = REGISTRY.register("chocolate_syrup_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> VANILLA_SYRUP_PUMP = REGISTRY.register("vanilla_syrup_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> PUMPKIN_SPICE_PUMP = REGISTRY.register("pumpkin_spice_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> HONEY_PUMP = REGISTRY.register("honey_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> MAGMA_CREAM_PUMP = REGISTRY.register("magma_cream_pump", () -> new FlavorPumpBlock());
    public static final RegistryObject<Block> ACACIA_CHAIR = REGISTRY.register("acacia_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> ACACIA_STOOL = REGISTRY.register("acacia_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> ACACIA_TABLE = REGISTRY.register("acacia_table", () -> new TableBlock());
    public static final RegistryObject<Block> BIRCH_CHAIR = REGISTRY.register("birch_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> BIRCH_STOOL = REGISTRY.register("birch_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> BIRCH_TABLE = REGISTRY.register("birch_table", () -> new TableBlock());
    public static final RegistryObject<Block> BURNT_COFFEE_BAG = REGISTRY.register("burnt_coffee_bag", () -> new CoffeeBagBlock(AddedItems.BURNT_COFFEE_BEAN));
    public static final RegistryObject<Block> BURNT_COFFEE_JAR = REGISTRY.register("burnt_coffee_jar", () -> new CoffeeJarBlock(AddedItems.BURNT_COFFEE_GROUNDS));
    public static final RegistryObject<Block> COFFEE_PLANT = REGISTRY.register("coffee_plant", CoffeePlantBlock::new);
    public static final RegistryObject<Block> COFFEE_POT = REGISTRY.register("coffee_pot", CoffeePotBlock::new);
    public static final RegistryObject<Block> COFFEE_SEEDLING = REGISTRY.register("coffee_seedling", CoffeeSeedlingBlock::new);
    public static final RegistryObject<Block> COFFEE_TREE = REGISTRY.register("coffee_tree", CoffeeTreeBlock::new);
    public static final RegistryObject<Block> CRIMSON_CHAIR = REGISTRY.register("crimson_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> CRIMSON_STOOL = REGISTRY.register("crimson_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> CRIMSON_TABLE = REGISTRY.register("crimson_table", () -> new TableBlock());
    public static final RegistryObject<Block> DARK_OAK_CHAIR = REGISTRY.register("dark_oak_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> DARK_OAK_STOOL = REGISTRY.register("dark_oak_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> DARK_OAK_TABLE = REGISTRY.register("dark_oak_table", () -> new TableBlock());
    public static final RegistryObject<Block> DARK_COFFEE_BAG = REGISTRY.register("dark_coffee_bag", () -> new CoffeeBagBlock(AddedItems.DARK_COFFEE_BEAN));
    public static final RegistryObject<Block> DARK_COFFEE_JAR = REGISTRY.register("dark_coffee_jar", () -> new CoffeeJarBlock(AddedItems.DARK_COFFEE_GROUNDS));
    public static final RegistryObject<Block> DRYING_MAT = REGISTRY.register("drying_mat", DryingMatBlock::new);
    public static final RegistryObject<Block> ESPRESSO_MACHINE = REGISTRY.register("espresso_machine", EspressoMachineBlock::new);
    public static final RegistryObject<Block> GREEN_COFFEE_BAG = REGISTRY.register("green_coffee_bag", () -> new CoffeeBagBlock(AddedItems.GREEN_COFFEE_BEAN));
    public static final RegistryObject<Block> GRINDER = REGISTRY.register("grinder", GrinderBlock::new);
    public static final RegistryObject<Block> JUNGLE_CHAIR = REGISTRY.register("jungle_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> JUNGLE_STOOL = REGISTRY.register("jungle_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> JUNGLE_TABLE = REGISTRY.register("jungle_table", () -> new TableBlock());
    public static final RegistryObject<Block> LIGHT_COFFEE_BAG = REGISTRY.register("light_coffee_bag", () -> new CoffeeBagBlock(AddedItems.LIGHT_COFFEE_BEAN));
    public static final RegistryObject<Block> LIGHT_COFFEE_JAR = REGISTRY.register("light_coffee_jar", () -> new CoffeeJarBlock(AddedItems.LIGHT_COFFEE_GROUNDS));
    public static final RegistryObject<Block> MEDIUM_COFFEE_BAG = REGISTRY.register("medium_coffee_bag", () -> new CoffeeBagBlock(AddedItems.MEDIUM_COFFEE_BEAN));
    public static final RegistryObject<Block> MEDIUM_COFFEE_JAR = REGISTRY.register("medium_coffee_jar", () -> new CoffeeJarBlock(AddedItems.MEDIUM_COFFEE_GROUNDS));
    public static final RegistryObject<Block> OAK_CHAIR = REGISTRY.register("oak_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> OAK_STOOL = REGISTRY.register("oak_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> OAK_TABLE = REGISTRY.register("oak_table", () -> new TableBlock());
    public static final RegistryObject<Block> POTTED_COFFEE_PLANT = REGISTRY.register("potted_coffee_plant", () -> new FlowerPotBlock(AddedBlocks::getFlowerPot, COFFEE_PLANT, BlockBehaviour.Properties.copy(AddedBlocks.getFlowerPot())));
    public static final RegistryObject<Block> ROASTER = REGISTRY.register("roaster", RoasterBlock::new);
    public static final RegistryObject<Block> SPRUCE_CHAIR = REGISTRY.register("spruce_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> SPRUCE_STOOL = REGISTRY.register("spruce_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> SPRUCE_TABLE = REGISTRY.register("spruce_table", () -> new TableBlock());
    public static final RegistryObject<Block> WARPED_CHAIR = REGISTRY.register("warped_chair", () -> new SittableBlock(true));
    public static final RegistryObject<Block> WARPED_STOOL = REGISTRY.register("warped_stool", () -> new SittableBlock(false));
    public static final RegistryObject<Block> WARPED_TABLE = REGISTRY.register("warped_table", () -> new TableBlock());

    public static void registerPottedPlants() {
        getFlowerPot().addPlant(COFFEE_PLANT.get().getRegistryName(), POTTED_COFFEE_PLANT);
    }

    public static void registerRenderTypes() {
        ItemBlockRenderTypes.setRenderLayer(BURNT_COFFEE_BAG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(COFFEE_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(COFFEE_SEEDLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(COFFEE_TREE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DARK_COFFEE_BAG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DRYING_MAT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ESPRESSO_MACHINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(COFFEE_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GREEN_COFFEE_BAG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GRINDER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(LIGHT_COFFEE_BAG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MEDIUM_COFFEE_BAG.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(POTTED_COFFEE_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ROASTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TIP_JAR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(FLAVOR_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(SIMPLE_SYRUP_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CARAMEL_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(CHOCOLATE_SYRUP_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(VANILLA_SYRUP_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PUMPKIN_SPICE_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(HONEY_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MAGMA_CREAM_PUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BURNT_COFFEE_JAR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(DARK_COFFEE_JAR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(LIGHT_COFFEE_JAR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MEDIUM_COFFEE_JAR.get(), RenderType.translucent());
    }

    private static FlowerPotBlock getFlowerPot() {
        return (FlowerPotBlock) Blocks.FLOWER_POT;
    }

    public static class Tags {
        public static final Tag.Named<Block> CHAIRS = BlockTags.createOptional(new ResourceLocation(CoffeeCraft.ID, "chairs"));
        public static final Tag.Named<Block> COFFEE_BAGS = BlockTags.createOptional(new ResourceLocation(CoffeeCraft.ID, "coffee_bags"));
        public static final Tag.Named<Block> STOOLS = BlockTags.createOptional(new ResourceLocation(CoffeeCraft.ID, "stools"));
        public static final Tag.Named<Block> TABLE_CAN_CONNECT_TO = BlockTags.createOptional(new ResourceLocation(CoffeeCraft.ID, "table_can_connect_to"));
        public static final Tag.Named<Block> TABLES = BlockTags.createOptional(new ResourceLocation(CoffeeCraft.ID, "tables"));
    }

}
