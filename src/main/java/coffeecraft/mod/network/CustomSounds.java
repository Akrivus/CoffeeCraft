package coffeecraft.mod.network;

import coffeecraft.mod.CoffeeCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CoffeeCraft.ID);
    public static final RegistryObject<SoundEvent> ENTITY_VILLAGER_WORK_BARISTA = REGISTRY.register("entity.villager.work_barista", () -> new SoundEvent(new ResourceLocation(CoffeeCraft.ID, "entity.villager.work_barista")));
    public static final RegistryObject<SoundEvent> BLOCK_COFFEE_BAG = REGISTRY.register("block.coffee_bag", () -> new SoundEvent(new ResourceLocation(CoffeeCraft.ID, "block.coffee_bag")));
}
