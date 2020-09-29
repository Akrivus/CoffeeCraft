package coffeecraft.mod.init;

import coffeecraft.mod.entity.ai.goal.EatCoffeeBeansGoal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.HashMap;
import java.util.UUID;

@EventBusSubscriber(modid = CoffeeCraft.MODID)
public class VanillaTweaks {
    public static final HashMap<UUID, Long> OCELOT_BEAN_TRACKER = new HashMap<UUID, Long>();

    @SubscribeEvent
    public static void onOcelotSpawn(LivingSpawnEvent e) {
        LivingEntity entity = e.getEntityLiving();
        if (entity instanceof OcelotEntity) {
            OcelotEntity ocelot = (OcelotEntity) entity;
            ocelot.goalSelector.addGoal(3, new EatCoffeeBeansGoal(ocelot));
            if (ocelot.world.rand.nextFloat() < 0.1F) {
                OCELOT_BEAN_TRACKER.put(ocelot.getUniqueID(), ocelot.world.getGameTime() - ocelot.world.rand.nextInt(3600));
            }
        }
    }
}
