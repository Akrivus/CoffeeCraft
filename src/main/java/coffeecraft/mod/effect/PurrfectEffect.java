package coffeecraft.mod.effect;

import coffeecraft.mod.CoffeeCraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CoffeeCraft.ID)
public class PurrfectEffect extends CaffeinatedEffect {
    public PurrfectEffect() {
        super(0xFDFF76);
    }

    @SubscribeEvent
    public static void onCreeperSpawn(LivingSpawnEvent e) {
        LivingEntity entity = e.getEntityLiving();
        if (entity instanceof Creeper creeper)
            creeper.goalSelector.addGoal(2, new AvoidEntityGoal<>(creeper, Player.class,
                    16.0F, 1.0, 1.2, PurrfectEffect::isPurrfect));
    }

    private static boolean isPurrfect(LivingEntity entity) {
        return entity.hasEffect(AddedEffects.PURRFECT.get());
    }
}
