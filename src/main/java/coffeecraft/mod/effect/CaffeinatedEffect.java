package coffeecraft.mod.effect;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CoffeeCraft.MODID)
public class CaffeinatedEffect extends Effect {
	public CaffeinatedEffect() {
		super(EffectType.HARMFUL, 0xCCE1EB);
	}
	
	@Override
	public void performEffect(LivingEntity entity, int amplifier) {
		double level = amplifier + 1; double wave = Math.sin(entity.ticksExisted) / (50 / level);
		double x = ((entity.world.rand.nextFloat() - 0.5) * level) * wave;
		double y = ((entity.world.rand.nextFloat() * 2.0) * level) * wave;
		double z = ((entity.world.rand.nextFloat() - 0.5) * level) * wave;
		entity.setMotion(entity.getMotion().add(x, y, z));
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
	
	@SubscribeEvent
	public static void onPlayerAttemptToSleep(PlayerSleepInBedEvent e) {
		PlayerEntity player = e.getPlayer();
		if (player.getActivePotionEffect(CoffeeCraft.Potions.CAFFEINATED.get()) != null) {
			player.sendStatusMessage(new TranslationTextComponent("block.coffeecraft.bed.caffeinated"), true);
			e.setResult(SleepResult.OTHER_PROBLEM);
		}
	}
	@SubscribeEvent
	public static void onPotionRemove(PotionRemoveEvent e) {
		e.setCanceled(e.getPotion() == CoffeeCraft.Potions.CAFFEINATED.get());
	}
}
