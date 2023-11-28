package coffeecraft.mod.effect;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.network.CustomMessenger;
import coffeecraft.mod.network.message.SJitters;
import coffeecraft.mod.network.message.SRemoveJitters;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Player.BedSleepingProblem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CaffeinatedEffect extends MobEffect {
    public static final ConcurrentHashMap<UUID, Integer> EFFECTED = new ConcurrentHashMap();

    public CaffeinatedEffect() {
        this(0x603A27);
    }

    protected CaffeinatedEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "8018FE4F-D5B7-46CE-AB54-619CADC2B9F8", 1.5D,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_SPEED,
                "1295B42D-A0BD-4BD9-B3BC-4E4A4C06E02F", 0.2D,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                "994D829C-043F-4397-904F-8F1BDF56EB7E", 0.2D,
                AttributeModifier.Operation.MULTIPLY_TOTAL);
        MinecraftForge.EVENT_BUS.register(this);
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
    public void applyJumpBoost(LivingEvent.LivingJumpEvent e) {
        LivingEntity entity = e.getEntityLiving();
        if (entity.hasEffect(this)) {
            Vec3 vec = entity.getDeltaMovement();
            entity.setDeltaMovement(vec.x, vec.y * 1.2, vec.z);
        }
    }

    @SubscribeEvent
    public void onSleepAttempt(PlayerSleepInBedEvent e) {
        Player player = e.getPlayer();
        if (player.hasEffect(this)) {
            player.displayClientMessage(new TranslatableComponent("effect.coffeecraft.caffeinated.bed"), true);
            e.setResult(BedSleepingProblem.OTHER_PROBLEM);
        }
    }

    @SubscribeEvent
    public void onExpiry(PotionEvent.PotionExpiryEvent e) {
        MobEffectInstance effect = e.getPotionEffect();
        if (effect.getEffect() != this)
            return;
        LivingEntity entity = e.getEntityLiving();
        entity.addEffect(new MobEffectInstance(AddedEffects.WITHDRAWAL.get(),
                effect.getAmplifier() * 200));
        entity.level.players().forEach(player ->
                CoffeeCraft.send(player, new SRemoveJitters(entity)));
        entity.removeEffect(this);
    }

    @SubscribeEvent
    public void onViewRender(EntityViewRenderEvent.FOVModifier e) {
        Player player = Minecraft.getInstance().player;
        int magnitude = EFFECTED.getOrDefault(player.getUUID(), 0);
        if (magnitude < 1)
            return;
        double jitter = Math.sin(player.tickCount * magnitude / 9) * magnitude;
        e.setFOV(e.getFOV() + jitter);
    }
    
    @SubscribeEvent
    public void onHandRender(RenderHandEvent e) {
        Player player = Minecraft.getInstance().player;
        int magnitude = EFFECTED.getOrDefault(player.getUUID(), 0);
        if (magnitude < 1)
            return;
        float jitter = (float)(player.getRandom().nextGaussian() / (10 - magnitude));
        e.getMatrixStack().mulPose(Vector3f.ZP.rotationDegrees(jitter));
    }

    @SubscribeEvent
    public void onRender(RenderLivingEvent.Pre e) {
        LivingEntity entity = e.getEntity();
        int magnitude = EFFECTED.getOrDefault(entity.getUUID(), 0);
        if (magnitude < 1)
            return;
        float jitter = (float)(entity.getRandom().nextGaussian() / (10 - magnitude));
        e.getMatrixStack().mulPose(Vector3f.ZP.rotationDegrees(jitter));
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent e) {
        LivingEntity entity = e.getEntityLiving();
        if (entity.hasEffect(this))
            entity.level.players().forEach(player ->
                    CoffeeCraft.send(player, new SRemoveJitters(entity)));
    }
}
