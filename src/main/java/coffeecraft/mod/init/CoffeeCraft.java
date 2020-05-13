package coffeecraft.mod.init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.collect.ImmutableSet;

import coffeecraft.mod.block.CoffeeBagBlock;
import coffeecraft.mod.block.CoffeeCauldronBlock;
import coffeecraft.mod.block.CoffeePlantBlock;
import coffeecraft.mod.block.CoffeeSaplingBlock;
import coffeecraft.mod.block.CoffeeSeedlingBlock;
import coffeecraft.mod.block.DryingMatBlock;
import coffeecraft.mod.block.EspressoMachineBlock;
import coffeecraft.mod.block.GooseneckKettleBlock;
import coffeecraft.mod.block.GrinderBlock;
import coffeecraft.mod.block.RoasterBlock;
import coffeecraft.mod.effect.CaffeinatedEffect;
import coffeecraft.mod.effect.PurrfectEffect;
import coffeecraft.mod.effect.WithdrawalEffect;
import coffeecraft.mod.items.AbstractCoffeeItem;
import coffeecraft.mod.items.CoffeeCherriesItem;
import coffeecraft.mod.items.CoffeeSeedsItem;
import coffeecraft.mod.tileentity.CoffeeBagTileEntity;
import coffeecraft.mod.tileentity.CoffeeCauldronTileEntity;
import coffeecraft.mod.tileentity.DryingMatTileEntity;
import coffeecraft.mod.tileentity.EspressoMachineTileEntity;
import coffeecraft.mod.tileentity.GooseneckKettleTileEntity;
import coffeecraft.mod.tileentity.GrinderTileEntity;
import coffeecraft.mod.tileentity.RoasterTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(CoffeeCraft.MODID)
public class CoffeeCraft {
	public static final String MODID = "coffeecraft";
	public static final ItemGroup TAB = new ItemGroup("coffeecraft") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(Items.TASSE.get());
		}
	};

    public CoffeeCraft() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	bus.addListener(this::common);
    	bus.addListener(this::client);
    	Blocks.REGISTRY.register(bus);
    	Entities.REGISTRY.register(bus);
    	Features.REGISTRY.register(bus);
    	Items.REGISTRY.register(bus);
    	Particles.REGISTRY.register(bus);
    	POIs.REGISTRY.register(bus);
    	Potions.REGISTRY.register(bus);
    	Professions.REGISTRY.register(bus);
    	SoundEvents.REGISTRY.register(bus);
    	TileEntities.REGISTRY.register(bus);
    }

    private void common(final FMLCommonSetupEvent e) {
    	POIs.setup();
    }
    
    private void client(final FMLClientSetupEvent e) {
    	Entities.setup();
    	Blocks.setup();
    }
    
	public static class Blocks {
		public static final DeferredRegister<Block> REGISTRY = new DeferredRegister<Block>(ForgeRegistries.BLOCKS, CoffeeCraft.MODID);
		public static final RegistryObject<CoffeeBagBlock> BURNT_COFFEE_BAG = REGISTRY.register("burnt_coffee_bag", CoffeeBagBlock::new);
		public static final RegistryObject<CoffeeCauldronBlock> CAULDRON = REGISTRY.register("cauldron", CoffeeCauldronBlock::new);
		public static final RegistryObject<CoffeePlantBlock> COFFEE_PLANT = REGISTRY.register("coffee_plant", CoffeePlantBlock::new);
		public static final RegistryObject<CoffeeSaplingBlock> COFFEE_SAPLING = REGISTRY.register("coffee_sapling", CoffeeSaplingBlock::new);
		public static final RegistryObject<CoffeeSeedlingBlock> COFFEE_SEEDLING = REGISTRY.register("coffee_seedling", CoffeeSeedlingBlock::new);
		public static final RegistryObject<CoffeeBagBlock> DARK_ROASTED_COFFEE_BAG = REGISTRY.register("dark_roasted_coffee_bag", CoffeeBagBlock::new);
		public static final RegistryObject<DryingMatBlock> DRYING_MAT = REGISTRY.register("drying_mat", DryingMatBlock::new);
		public static final RegistryObject<EspressoMachineBlock> ESPRESSO_MACHINE = REGISTRY.register("espresso_machine", EspressoMachineBlock::new);
		public static final RegistryObject<GooseneckKettleBlock> GOOSENECK_KETTLE = REGISTRY.register("gooseneck_kettle", GooseneckKettleBlock::new);
		public static final RegistryObject<CoffeeBagBlock> GREEN_COFFEE_BAG = REGISTRY.register("green_coffee_bag", CoffeeBagBlock::new);
		public static final RegistryObject<GrinderBlock> GRINDER = REGISTRY.register("grinder", GrinderBlock::new);
		public static final RegistryObject<CoffeeBagBlock> LIGHT_ROASTED_COFFEE_BAG = REGISTRY.register("light_roasted_coffee_bag", CoffeeBagBlock::new);
		public static final RegistryObject<CoffeeBagBlock> MEDIUM_ROASTED_COFFEE_BAG = REGISTRY.register("medium_roasted_coffee_bag", CoffeeBagBlock::new);
		public static final RegistryObject<RoasterBlock> ROASTER = REGISTRY.register("roaster", RoasterBlock::new);
		
		public static void setup() {
			RenderTypeLookup.setRenderLayer(BURNT_COFFEE_BAG.get(), RenderType.getSolid());
	    	RenderTypeLookup.setRenderLayer(CAULDRON.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(COFFEE_PLANT.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(COFFEE_SAPLING.get(), RenderType.getCutoutMipped());
	    	RenderTypeLookup.setRenderLayer(COFFEE_SEEDLING.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(DARK_ROASTED_COFFEE_BAG.get(), RenderType.getSolid());
			RenderTypeLookup.setRenderLayer(DRYING_MAT.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(ESPRESSO_MACHINE.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(GOOSENECK_KETTLE.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(GREEN_COFFEE_BAG.get(), RenderType.getSolid());
			RenderTypeLookup.setRenderLayer(GRINDER.get(), RenderType.getCutoutMipped());
			RenderTypeLookup.setRenderLayer(LIGHT_ROASTED_COFFEE_BAG.get(), RenderType.getSolid());
			RenderTypeLookup.setRenderLayer(MEDIUM_ROASTED_COFFEE_BAG.get(), RenderType.getSolid());
			RenderTypeLookup.setRenderLayer(ROASTER.get(), RenderType.getCutoutMipped());
		}
	}
	public static class Entities {
    	public static final DeferredRegister<EntityType<?>> REGISTRY = new DeferredRegister<EntityType<?>>(ForgeRegistries.ENTITIES, CoffeeCraft.MODID);
    	
    	public static void setup() {
        	
    	}
	}
	public static class Features {
    	public static final DeferredRegister<Feature<?>> REGISTRY = new DeferredRegister<Feature<?>>(ForgeRegistries.FEATURES, CoffeeCraft.MODID);
	}
	public static class Items {
    	public static final DeferredRegister<Item> REGISTRY = new DeferredRegister<Item>(ForgeRegistries.ITEMS, CoffeeCraft.MODID);
    	public static final RegistryObject<BlockItem> BURNT_COFFEE_BAG = REGISTRY.register("burnt_coffee_bag", () -> new BlockItem(Blocks.BURNT_COFFEE_BAG.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<AbstractCoffeeItem> BURNT_COFFEE_BEANS = REGISTRY.register("burnt_coffee_beans", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> BURNT_COFFEE_GROUNDS = REGISTRY.register("burnt_coffee_grounds", AbstractCoffeeItem::new);
    	public static final RegistryObject<BlockItem> CAULDRON = REGISTRY.register("cauldron", () -> new BlockItem(Blocks.CAULDRON.get(), new Properties()));
    	public static final RegistryObject<AbstractCoffeeItem> COFFEE_CUP = REGISTRY.register("coffee_cup", AbstractCoffeeItem::new);
    	public static final RegistryObject<CoffeeCherriesItem> COFFEE_CHERRIES = REGISTRY.register("coffee_cherries", CoffeeCherriesItem::new);
    	public static final RegistryObject<BlockItem> COFFEE_SAPLING = REGISTRY.register("coffee_sapling", () -> new BlockItem(Blocks.COFFEE_SAPLING.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<BlockItem> COFFEE_PLANT = REGISTRY.register("coffee_plant", () -> new BlockItem(Blocks.COFFEE_PLANT.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<BlockItem> DARK_ROASTED_COFFEE_BAG = REGISTRY.register("dark_roasted_coffee_bag", () -> new BlockItem(Blocks.DARK_ROASTED_COFFEE_BAG.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<AbstractCoffeeItem> DARK_ROASTED_COFFEE_BEANS = REGISTRY.register("dark_roasted_coffee_beans", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> DARK_ROASTED_COFFEE_GROUNDS = REGISTRY.register("dark_roasted_coffee_grounds", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> DEMITASSE = REGISTRY.register("demitasse", AbstractCoffeeItem::new);
    	public static final RegistryObject<CoffeeCherriesItem> DRIED_COFFEE_CHERRIES = REGISTRY.register("dried_coffee_cherries", CoffeeCherriesItem::new);
    	public static final RegistryObject<BlockItem> DRYING_MAT = REGISTRY.register("drying_mat", () -> new BlockItem(Blocks.DRYING_MAT.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<BlockItem> ESPRESSO_MACHINE = REGISTRY.register("espresso_machine", () -> new BlockItem(Blocks.ESPRESSO_MACHINE.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<BlockItem> GOOSENECK_KETTLE = REGISTRY.register("gooseneck_kettle", () -> new BlockItem(Blocks.GOOSENECK_KETTLE.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<BlockItem> GREEN_COFFEE_BAG = REGISTRY.register("green_coffee_bag", () -> new BlockItem(Blocks.GREEN_COFFEE_BAG.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<CoffeeSeedsItem> GREEN_COFFEE_BEANS = REGISTRY.register("green_coffee_beans", CoffeeSeedsItem::new);
    	public static final RegistryObject<BlockItem> GRINDER = REGISTRY.register("grinder", () -> new BlockItem(Blocks.GRINDER.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<BlockItem> LIGHT_ROASTED_COFFEE_BAG = REGISTRY.register("light_roasted_coffee_bag", () -> new BlockItem(Blocks.LIGHT_ROASTED_COFFEE_BAG.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<AbstractCoffeeItem> LIGHT_ROASTED_COFFEE_BEANS = REGISTRY.register("light_roasted_coffee_beans", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> LIGHT_ROASTED_COFFEE_GROUNDS = REGISTRY.register("light_roasted_coffee_grounds", AbstractCoffeeItem::new);
    	public static final RegistryObject<BlockItem> MEDIUM_ROASTED_COFFEE_BAG = REGISTRY.register("medium_roasted_coffee_bag", () -> new BlockItem(Blocks.MEDIUM_ROASTED_COFFEE_BAG.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<AbstractCoffeeItem> MEDIUM_ROASTED_COFFEE_BEANS = REGISTRY.register("medium_roasted_coffee_beans", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> MEDIUM_ROASTED_COFFEE_GROUNDS = REGISTRY.register("medium_roasted_coffee_grounds", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> MUG = REGISTRY.register("mug", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> PORTAFILTER = REGISTRY.register("portafilter", AbstractCoffeeItem::new);
    	public static final RegistryObject<BlockItem> ROASTER = REGISTRY.register("roaster", () -> new BlockItem(Blocks.ROASTER.get(), new Properties().group(CoffeeCraft.TAB)));
    	public static final RegistryObject<AbstractCoffeeItem> TAMP = REGISTRY.register("tamp", AbstractCoffeeItem::new);
    	public static final RegistryObject<AbstractCoffeeItem> TASSE = REGISTRY.register("tasse", AbstractCoffeeItem::new);
	}
	public static class Particles {
    	public static final DeferredRegister<ParticleType<?>> REGISTRY = new DeferredRegister<ParticleType<?>>(ForgeRegistries.PARTICLE_TYPES, CoffeeCraft.MODID);
	}
	public static class POIs {
    	public static final DeferredRegister<PointOfInterestType> REGISTRY = new DeferredRegister<PointOfInterestType>(ForgeRegistries.POI_TYPES, CoffeeCraft.MODID);
    	public static final RegistryObject<PointOfInterestType> BARISTA = REGISTRY.register("barista", () -> new PointOfInterestType("barista", ImmutableSet.copyOf(Blocks.ESPRESSO_MACHINE.get().getStateContainer().getValidStates()), 1, 1));
	
    	private static final Method BLOCKSTATE_INJECTOR = ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class);
	    public static void setup() {
	        try {
	        	BLOCKSTATE_INJECTOR.invoke(null, BARISTA.get());
	        } catch (IllegalAccessException | InvocationTargetException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public static class Potions {
    	public static final DeferredRegister<Effect> REGISTRY = new DeferredRegister<Effect>(ForgeRegistries.POTIONS, CoffeeCraft.MODID);
    	public static final RegistryObject<CaffeinatedEffect> CAFFEINATED = REGISTRY.register("caffeinated", CaffeinatedEffect::new);
    	public static final RegistryObject<PurrfectEffect> PURRFECT = REGISTRY.register("purrfect", PurrfectEffect::new);
    	public static final RegistryObject<WithdrawalEffect> WITHDRAWAL = REGISTRY.register("withdrawal", WithdrawalEffect::new);
	}
	public static class Professions {
    	public static final DeferredRegister<VillagerProfession> REGISTRY = new DeferredRegister<VillagerProfession>(ForgeRegistries.PROFESSIONS, CoffeeCraft.MODID);
    	public static final RegistryObject<VillagerProfession> BARISTA = REGISTRY.register("barista", () -> new VillagerProfession("barista", POIs.BARISTA.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_BARISTA.get()));
	}
	public static class SoundEvents {
    	public static final DeferredRegister<SoundEvent> REGISTRY = new DeferredRegister<SoundEvent>(ForgeRegistries.SOUND_EVENTS, CoffeeCraft.MODID);
    	public static final RegistryObject<SoundEvent> ENTITY_VILLAGER_WORK_BARISTA = REGISTRY.register("entity.villager.work_barista", () -> new SoundEvent(new ResourceLocation(CoffeeCraft.MODID, "entity.villager.work_barista")));
	}
	public static class TileEntities {
    	public static final DeferredRegister<TileEntityType<?>> REGISTRY = new DeferredRegister<TileEntityType<?>>(ForgeRegistries.TILE_ENTITIES, CoffeeCraft.MODID);
    	public static final RegistryObject<TileEntityType<CoffeeBagTileEntity>> COFFEE_BAG = REGISTRY.register("coffee_bag", () -> TileEntityType.Builder.create(CoffeeBagTileEntity::new, Blocks.BURNT_COFFEE_BAG.get(), Blocks.DARK_ROASTED_COFFEE_BAG.get(), Blocks.GREEN_COFFEE_BAG.get(), Blocks.LIGHT_ROASTED_COFFEE_BAG.get(), Blocks.MEDIUM_ROASTED_COFFEE_BAG.get()).build(null));
    	public static final RegistryObject<TileEntityType<CoffeeCauldronTileEntity>> COFFEE_CAULDRON = REGISTRY.register("coffee_cauldron", () -> TileEntityType.Builder.create(CoffeeCauldronTileEntity::new, Blocks.CAULDRON.get()).build(null));
    	public static final RegistryObject<TileEntityType<DryingMatTileEntity>> DRYING_MAT = REGISTRY.register("drying_mat", () -> TileEntityType.Builder.create(DryingMatTileEntity::new, Blocks.DRYING_MAT.get()).build(null));
    	public static final RegistryObject<TileEntityType<EspressoMachineTileEntity>> ESPRESSO_MACHINE = REGISTRY.register("espresso_machine", () -> TileEntityType.Builder.create(EspressoMachineTileEntity::new, Blocks.ESPRESSO_MACHINE.get()).build(null));
    	public static final RegistryObject<TileEntityType<GooseneckKettleTileEntity>> GOOSENECK_KETTLE = REGISTRY.register("gooseneck_kettle", () -> TileEntityType.Builder.create(GooseneckKettleTileEntity::new, Blocks.GOOSENECK_KETTLE.get()).build(null));
    	public static final RegistryObject<TileEntityType<GrinderTileEntity>> GRINDER = REGISTRY.register("grinder", () -> TileEntityType.Builder.create(GrinderTileEntity::new, Blocks.GRINDER.get()).build(null));
    	public static final RegistryObject<TileEntityType<RoasterTileEntity>> ROASTER = REGISTRY.register("roaster", () -> TileEntityType.Builder.create(RoasterTileEntity::new, Blocks.ROASTER.get()).build(null));
	}
}