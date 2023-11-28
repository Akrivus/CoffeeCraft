package coffeecraft.mod.entity;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.block.AddedBlocks;
import coffeecraft.mod.network.CustomSounds;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class CustomVillagers {
    public static class Professions {
        public static final DeferredRegister<VillagerProfession> REGISTRY = DeferredRegister.create(ForgeRegistries.PROFESSIONS, CoffeeCraft.ID);
        public static final RegistryObject<VillagerProfession> BARISTA = REGISTRY.register("barista", () -> new VillagerProfession("barista", CustomVillagers.POI.BARISTA.get(), ImmutableSet.of(), ImmutableSet.of(), CustomSounds.ENTITY_VILLAGER_WORK_BARISTA.get()));
    }

    public static class POI {
        public static final DeferredRegister<PoiType> REGISTRY = DeferredRegister.create(ForgeRegistries.POI_TYPES, CoffeeCraft.ID);
        public static final RegistryObject<PoiType> BARISTA = REGISTRY.register("barista", () -> new PoiType("barista", define(AddedBlocks.ESPRESSO_MACHINE), 1, 2));
        public static final RegistryObject<PoiType> COFFEE_POT = REGISTRY.register("coffee_pot", () -> new PoiType("coffee_pot", define(AddedBlocks.COFFEE_POT), 4, 16));
        public static final RegistryObject<PoiType> TIP_JAR = REGISTRY.register("tip_jar", () -> new PoiType("tip_jar", define(AddedBlocks.TIP_JAR), 1, 2));
        public static final RegistryObject<PoiType> CAFE_CHAIRS = REGISTRY.register("cafe_chairs", () -> new PoiType("cafe_chairs", define(
                AddedBlocks.ACACIA_CHAIR, AddedBlocks.ACACIA_STOOL,
                AddedBlocks.BIRCH_CHAIR, AddedBlocks.BIRCH_STOOL,
                AddedBlocks.CRIMSON_CHAIR, AddedBlocks.CRIMSON_STOOL,
                AddedBlocks.DARK_OAK_CHAIR, AddedBlocks.DARK_OAK_STOOL,
                AddedBlocks.JUNGLE_CHAIR, AddedBlocks.JUNGLE_STOOL,
                AddedBlocks.OAK_CHAIR, AddedBlocks.OAK_STOOL,
                AddedBlocks.SPRUCE_CHAIR, AddedBlocks.SPRUCE_STOOL,
                AddedBlocks.WARPED_CHAIR, AddedBlocks.WARPED_STOOL
        ), 0, 1));
        public static final RegistryObject<PoiType> CAFE_TABLES = REGISTRY.register("cafe_tables", () -> new PoiType("cafe_tables", define(
                AddedBlocks.ACACIA_TABLE,
                AddedBlocks.BIRCH_TABLE,
                AddedBlocks.CRIMSON_TABLE,
                AddedBlocks.DARK_OAK_TABLE,
                AddedBlocks.JUNGLE_TABLE,
                AddedBlocks.OAK_TABLE,
                AddedBlocks.SPRUCE_TABLE,
                AddedBlocks.WARPED_TABLE
        ), 0, 1));

        private static Set<BlockState> define(Supplier<Block> firstBlock, Supplier<Block>... blocks) {
            List<Supplier<Block>> suppliers = new ArrayList<>();
            suppliers.add(firstBlock);
            for (Supplier<Block> block : blocks)
                suppliers.add(block);
            return suppliers.stream().flatMap((block) -> block.get().getStateDefinition().getPossibleStates().stream()).collect(ImmutableSet.toImmutableSet());
        }
    }

    /**
     *

     public void test() {
     VillagerRenderer renderer = (VillagerRenderer) Minecraft.getInstance().getEntityRenderDispatcher().renderers.get(EntityType.VILLAGER);
     renderer.addL
     }
     */
}
