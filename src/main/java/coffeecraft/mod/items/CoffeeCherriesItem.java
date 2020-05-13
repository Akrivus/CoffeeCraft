package coffeecraft.mod.items;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CoffeeCherriesItem extends Item {
	public CoffeeCherriesItem() {
		super(new Properties().group(CoffeeCraft.TAB).food(new Food.Builder().hunger(1).saturation(0.1F).setAlwaysEdible().build()));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		return super.onItemUseFinish(stack, world, entity);
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 6;
	}
}
