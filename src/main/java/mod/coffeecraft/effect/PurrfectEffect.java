package mod.coffeecraft.effect;

import mod.coffeecraft.init.CoffeeCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CoffeeCraft.MODID)
public class PurrfectEffect extends Effect {
	public PurrfectEffect() {
		super(EffectType.BENEFICIAL, 0xFDFF76);
	}
	
	@SubscribeEvent
	public static void onCreeperSpawn(LivingSpawnEvent e) {
		LivingEntity entity = e.getEntityLiving();
		if (entity instanceof CreeperEntity) {
			CreeperEntity creeper = (CreeperEntity) entity;
			creeper.goalSelector.addGoal(2, new AvoidEntityGoal<>(creeper, PlayerEntity.class, 16.0F, 1.0D, 1.2D, (target) -> {
				return target.getActivePotionEffect(CoffeeCraft.Potions.PURRFECT.get()) != null;
			}));
		}
	}
}
