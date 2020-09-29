package coffeecraft.mod.effect;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WithdrawalEffect extends Effect {
    public WithdrawalEffect() {
        super(EffectType.HARMFUL, 0x331E14);
        this.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7E4A2966-06F5-4EF2-AAFE-996D0DBDB891", -0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "AF4A6C76-BF7A-4BC0-8A9E-803DC9FB5E90", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "1EEFAE80-931A-468D-ACEF-81BE410E6B33", -0.1D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @SubscribeEvent
    public static void onPlayerAttemptToSleep(PlayerSleepInBedEvent e) {
        PlayerEntity player = e.getPlayer();
        if (player.getActivePotionEffect(CoffeeCraft.Potions.WITHDRAWAL.get()) != null) {
            player.removeActivePotionEffect(CoffeeCraft.Potions.WITHDRAWAL.get());
        }
    }

    @SubscribeEvent
    public static void onPotionRemove(PotionRemoveEvent e) {
        e.setCanceled(e.getPotion() == CoffeeCraft.Potions.WITHDRAWAL.get());
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
