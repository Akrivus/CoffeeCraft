package coffeecraft.mod.items;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.block.AddedBlocks;
import coffeecraft.mod.items.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;

public class AddedItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CoffeeCraft.ID);
    public static final RegistryObject<BlockItem> FLAVOR_PUMP = REGISTRY.register("flavor_pump", () -> new BlockItem(AddedBlocks.FLAVOR_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> SIMPLE_SYRUP_PUMP = REGISTRY.register("simple_syrup_pump", () -> new BlockItem(AddedBlocks.SIMPLE_SYRUP_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> CARAMEL_PUMP = REGISTRY.register("caramel_pump", () -> new BlockItem(AddedBlocks.CARAMEL_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> CHOCOLATE_SYRUP_PUMP = REGISTRY.register("chocolate_syrup_pump", () -> new BlockItem(AddedBlocks.CHOCOLATE_SYRUP_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> VANILLA_SYRUP_PUMP = REGISTRY.register("vanilla_syrup_pump", () -> new BlockItem(AddedBlocks.VANILLA_SYRUP_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> PUMPKIN_SPICE_PUMP = REGISTRY.register("pumpkin_spice_pump", () -> new BlockItem(AddedBlocks.PUMPKIN_SPICE_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> HONEY_PUMP = REGISTRY.register("honey_pump", () -> new BlockItem(AddedBlocks.HONEY_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> MAGMA_CREAM_PUMP = REGISTRY.register("magma_cream_pump", () -> new BlockItem(AddedBlocks.MAGMA_CREAM_PUMP.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> BURNT_COFFEE_JAR = REGISTRY.register("burnt_coffee_jar", () -> new BlockItem(AddedBlocks.BURNT_COFFEE_JAR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> DARK_COFFEE_JAR = REGISTRY.register("dark_coffee_jar", () -> new BlockItem(AddedBlocks.DARK_COFFEE_JAR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> LIGHT_COFFEE_JAR = REGISTRY.register("light_coffee_jar", () -> new BlockItem(AddedBlocks.LIGHT_COFFEE_JAR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> MEDIUM_COFFEE_JAR = REGISTRY.register("medium_coffee_jar", () -> new BlockItem(AddedBlocks.MEDIUM_COFFEE_JAR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> ACACIA_CHAIR = REGISTRY.register("acacia_chair", () -> new BlockItem(AddedBlocks.ACACIA_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> ACACIA_STOOL = REGISTRY.register("acacia_stool", () -> new BlockItem(AddedBlocks.ACACIA_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> ACACIA_TABLE = REGISTRY.register("acacia_table", () -> new BlockItem(AddedBlocks.ACACIA_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> BIRCH_CHAIR = REGISTRY.register("birch_chair", () -> new BlockItem(AddedBlocks.BIRCH_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> BIRCH_STOOL = REGISTRY.register("birch_stool", () -> new BlockItem(AddedBlocks.BIRCH_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> BIRCH_TABLE = REGISTRY.register("birch_table", () -> new BlockItem(AddedBlocks.BIRCH_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> BURNT_COFFEE_BAG = REGISTRY.register("burnt_coffee_bag", () -> new CoffeeBagItem(AddedBlocks.BURNT_COFFEE_BAG.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> COFFEE_PLANT = REGISTRY.register("coffee_plant", () -> new BlockItem(AddedBlocks.COFFEE_PLANT.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> COFFEE_POT = REGISTRY.register("coffee_pot", () -> new BlockItem(AddedBlocks.COFFEE_POT.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> COFFEE_TREE = REGISTRY.register("coffee_tree", () -> new BlockItem(AddedBlocks.COFFEE_TREE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> CRIMSON_CHAIR = REGISTRY.register("crimson_chair", () -> new BlockItem(AddedBlocks.CRIMSON_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> CRIMSON_STOOL = REGISTRY.register("crimson_stool", () -> new BlockItem(AddedBlocks.CRIMSON_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> CRIMSON_TABLE = REGISTRY.register("crimson_table", () -> new BlockItem(AddedBlocks.CRIMSON_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> DARK_OAK_CHAIR = REGISTRY.register("dark_oak_chair", () -> new BlockItem(AddedBlocks.DARK_OAK_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> DARK_OAK_STOOL = REGISTRY.register("dark_oak_stool", () -> new BlockItem(AddedBlocks.DARK_OAK_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> DARK_OAK_TABLE = REGISTRY.register("dark_oak_table", () -> new BlockItem(AddedBlocks.DARK_OAK_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> DARK_COFFEE_BAG = REGISTRY.register("dark_coffee_bag", () -> new CoffeeBagItem(AddedBlocks.DARK_COFFEE_BAG.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> DRYING_MAT = REGISTRY.register("drying_mat", () -> new BlockItem(AddedBlocks.DRYING_MAT.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> ESPRESSO_MACHINE = REGISTRY.register("espresso_machine", () -> new BlockItem(AddedBlocks.ESPRESSO_MACHINE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> GREEN_COFFEE_BAG = REGISTRY.register("green_coffee_bag", () -> new CoffeeBagItem(AddedBlocks.GREEN_COFFEE_BAG.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> GRINDER = REGISTRY.register("grinder", () -> new BlockItem(AddedBlocks.GRINDER.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> JUNGLE_CHAIR = REGISTRY.register("jungle_chair", () -> new BlockItem(AddedBlocks.JUNGLE_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> JUNGLE_STOOL = REGISTRY.register("jungle_stool", () -> new BlockItem(AddedBlocks.JUNGLE_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> JUNGLE_TABLE = REGISTRY.register("jungle_table", () -> new BlockItem(AddedBlocks.JUNGLE_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> LIGHT_COFFEE_BAG = REGISTRY.register("light_coffee_bag", () -> new CoffeeBagItem(AddedBlocks.LIGHT_COFFEE_BAG.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> MEDIUM_COFFEE_BAG = REGISTRY.register("medium_coffee_bag", () -> new CoffeeBagItem(AddedBlocks.MEDIUM_COFFEE_BAG.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> OAK_CHAIR = REGISTRY.register("oak_chair", () -> new BlockItem(AddedBlocks.OAK_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> OAK_STOOL = REGISTRY.register("oak_stool", () -> new BlockItem(AddedBlocks.OAK_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> OAK_TABLE = REGISTRY.register("oak_table", () -> new BlockItem(AddedBlocks.OAK_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> ROASTER = REGISTRY.register("roaster", () -> new BlockItem(AddedBlocks.ROASTER.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> SPRUCE_CHAIR = REGISTRY.register("spruce_chair", () -> new BlockItem(AddedBlocks.SPRUCE_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> SPRUCE_STOOL = REGISTRY.register("spruce_stool", () -> new BlockItem(AddedBlocks.SPRUCE_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> SPRUCE_TABLE = REGISTRY.register("spruce_table", () -> new BlockItem(AddedBlocks.SPRUCE_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> TIP_JAR = REGISTRY.register("tip_jar", () -> new BlockItem(AddedBlocks.TIP_JAR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> WARPED_CHAIR = REGISTRY.register("warped_chair", () -> new BlockItem(AddedBlocks.WARPED_CHAIR.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> WARPED_STOOL = REGISTRY.register("warped_stool", () -> new BlockItem(AddedBlocks.WARPED_STOOL.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<BlockItem> WARPED_TABLE = REGISTRY.register("warped_table", () -> new BlockItem(AddedBlocks.WARPED_TABLE.get(), new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<Item> BURNT_COFFEE_BEAN = REGISTRY.register("burnt_coffee_bean", CoffeeBeanItem::new);
    public static final RegistryObject<Item> BURNT_COFFEE_GROUNDS = REGISTRY.register("burnt_coffee_grounds", () -> new BrewableCoffeeItem(0x1E1D23));
    public static final RegistryObject<Item> COFFEE_CHERRIES = REGISTRY.register("coffee_cherries", () -> new CoffeeCherryItem());
    public static final RegistryObject<Item> COFFEE_CUP = REGISTRY.register("coffee_cup", () -> new Item(new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<Item> DARK_COFFEE_BEAN = REGISTRY.register("dark_coffee_bean",  CoffeeBeanItem::new);
    public static final RegistryObject<Item> DARK_COFFEE_GROUNDS = REGISTRY.register("dark_coffee_grounds", () -> new BrewableCoffeeItem(0x302019));
    public static final RegistryObject<Item> DEMITASSE = REGISTRY.register("demitasse", () -> new Item(new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<Item> GREEN_COFFEE_BEAN = REGISTRY.register("green_coffee_bean", CoffeeSeedItem::new);
    public static final RegistryObject<Item> ICE_CUBES = REGISTRY.register("ice_cubes", () -> new Item(new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<Item> LIGHT_COFFEE_BEAN = REGISTRY.register("light_coffee_bean",  CoffeeBeanItem::new);
    public static final RegistryObject<Item> LIGHT_COFFEE_GROUNDS = REGISTRY.register("light_coffee_grounds", () -> new BrewableCoffeeItem(0x7B4D22));
    public static final RegistryObject<Item> MEDIUM_COFFEE_BEAN = REGISTRY.register("medium_coffee_bean",  CoffeeBeanItem::new);
    public static final RegistryObject<Item> MEDIUM_COFFEE_GROUNDS = REGISTRY.register("medium_coffee_grounds", () -> new BrewableCoffeeItem(0x4C2E1F));
    public static final RegistryObject<Item> MUG = REGISTRY.register("mug", () -> new Item(new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<Item> OCELOT_COFFEE = REGISTRY.register("ocelot_coffee", () -> new BrewableCoffeeItem(0x3A2513));
    public static final RegistryObject<Item> PORTAFILTER = REGISTRY.register("portafilter", () -> new Item(new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<Item> TAMP = REGISTRY.register("tamp", () -> new Item(new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));
    public static final RegistryObject<Item> TASSE = REGISTRY.register("tasse", () -> new Item(new Item.Properties().tab(CoffeeCraft.CreativeModeTab)));

    public static void registerDispenserBehaviors() {
        //DispenserBlock.registerDispenseBehavior(CoffeeItems.COFFEE_CHERRIES.get(), CoffeeCherryItem.DISPENSER_BEHAVIOR);
    }

    public static class Tags {
        public static final Tag.Named<Item> COFFEE_BAGS = ItemTags.createOptional(new ResourceLocation(CoffeeCraft.ID, "coffee_bags"));
        public static final Tag.Named<Item> COFFEE_BEANS = ItemTags.createOptional(new ResourceLocation(CoffeeCraft.ID, "coffee_beans"));
    }
}
