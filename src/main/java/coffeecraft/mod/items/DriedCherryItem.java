package coffeecraft.mod.items;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;

public class DriedCherryItem extends Item {
    public DriedCherryItem() {
        super(new Properties().group(CoffeeCraft.TAB));
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if (stack.getItem() == CoffeeCraft.Items.DRIED_COFFEE_CHERRIES.get() && entity.getAge() > 5 * stack.getCount() && entity.getMotion().y < -0.2F) {
            entity.setItem(new ItemStack(CoffeeCraft.Items.GREEN_COFFEE_BEANS.get(), stack.getCount()));
            for (int i = 0; i < 18 && entity.world.isRemote; ++i) {
                entity.world.addParticle(ParticleTypes.ENTITY_EFFECT, entity.getPosXRandom(0.5D), entity.getPosYRandom() - 0.25D, entity.getPosZRandom(0.5D), 0.18F, 0.09F, 0.12F);
            }
        }
        return super.onEntityItemUpdate(stack, entity);
    }
}
