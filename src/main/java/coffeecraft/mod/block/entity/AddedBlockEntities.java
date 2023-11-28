package coffeecraft.mod.block.entity;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.block.AddedBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AddedBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CoffeeCraft.ID);
    public static final RegistryObject<BlockEntityType<CoffeePotTileEntity>> COFFEE_POT = REGISTRY.register("coffee_pot", () -> BlockEntityType.Builder.of(CoffeePotTileEntity::new, AddedBlocks.COFFEE_POT.get()).build(null));
    public static final RegistryObject<BlockEntityType<EspressoMachineTileEntity>> ESPRESSO_MACHINE = REGISTRY.register("espresso_machine", () -> BlockEntityType.Builder.of(EspressoMachineTileEntity::new, AddedBlocks.ESPRESSO_MACHINE.get()).build(null));
    public static final RegistryObject<BlockEntityType<GrinderTileEntity>> GRINDER = REGISTRY.register("grinder", () -> BlockEntityType.Builder.of(GrinderTileEntity::new, AddedBlocks.GRINDER.get()).build(null));
    public static final RegistryObject<BlockEntityType<RoasterTileEntity>> ROASTER = REGISTRY.register("roaster", () -> BlockEntityType.Builder.of(RoasterTileEntity::new, AddedBlocks.ROASTER.get()).build(null));
}
