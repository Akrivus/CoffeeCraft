package coffeecraft.mod.effect;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.network.CustomMessenger;
import coffeecraft.mod.network.message.SJitters;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = CoffeeCraft.ID)
public class WithdrawalEffect extends MobEffect {
    public WithdrawalEffect() {
        super(MobEffectCategory.HARMFUL, 0x3A6F1A);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "A8875C1A-863F-4797-BEFE-23D8E3D4A995", -1.5D,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED,
                "57C35873-D1D5-4778-841B-24B0648C6DA2", -0.2D,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                "E4202982-9C13-4320-9134-2155B025E0B4", -0.2D,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.level.players().forEach(player -> CoffeeCraft.send(player, new SJitters(entity, amplifier + 1)));
        if (entity instanceof Player player) {
            FoodData data = player.getFoodData();
            data.addExhaustion((float) Math.pow(0.02F * amplifier, 2) - 0.5F);
            if (data.getExhaustionLevel() < 0.0)
                data.setExhaustion(0.0F);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }

    @SubscribeEvent
    public static void onPlayerAttemptToSleep(PlayerSleepInBedEvent e) {
        Player player = e.getPlayer();
        if (player.hasEffect(AddedEffects.WITHDRAWAL.get()))
            player.removeEffect(AddedEffects.WITHDRAWAL.get());
    }
}
