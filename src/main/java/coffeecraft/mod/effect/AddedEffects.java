package coffeecraft.mod.effect;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.effect.CaffeinatedEffect;
import coffeecraft.mod.effect.PurrfectEffect;
import coffeecraft.mod.effect.WithdrawalEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AddedEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CoffeeCraft.ID);
    public static final RegistryObject<CaffeinatedEffect> CAFFEINATED = REGISTRY.register("caffeinated", CaffeinatedEffect::new);
    public static final RegistryObject<PurrfectEffect> PURRFECT = REGISTRY.register("purrfect", PurrfectEffect::new);
    public static final RegistryObject<WithdrawalEffect> WITHDRAWAL = REGISTRY.register("withdrawal", WithdrawalEffect::new);
}
