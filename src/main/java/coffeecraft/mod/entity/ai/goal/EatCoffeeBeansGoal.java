package coffeecraft.mod.entity.ai.goal;

import java.util.EnumSet;
import java.util.Optional;

import coffeecraft.mod.block.CoffeePlantBlock;
import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class EatCoffeeBeansGoal extends Goal {
	private final OcelotEntity ocelot;
	private BlockPos cherries;
	private int ticksAttempted = 0;
	private boolean eaten;

	public EatCoffeeBeansGoal(OcelotEntity ocelot) {
		 this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		 this.ocelot = ocelot;
	}
	
	@Override
	public boolean shouldExecute() {
		if (this.ocelot.getRevengeTarget() != null) {
			return false;
		} else {
			Optional<BlockPos> optional = this.findCoffeeCherries();
			if (optional.isPresent()) {
				this.setPosition(optional.get());
				return true;
			} else {
				return false;
			}
		}
	}
	
	@Override
	public void startExecuting() {
		this.ticksAttempted = 0;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return !this.eaten;
	}

	@Override
	public void tick() {
		++this.ticksAttempted;
		if (this.ticksAttempted > 600) {
			this.eaten = true;
		} else {
			double dist = this.cherries.distanceSq(this.ocelot.getPositionVec(), true);
			if (dist < 1.0D) {
				this.ocelot.getLookController().setLookPosition(this.cherries.getX(), this.cherries.getY(), this.cherries.getZ());
				this.ocelot.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0F, 1.0F);
				CoffeeCraft.Blocks.COFFEE_PLANT.get().grow((ServerWorld) this.ocelot.world, this.ocelot.world.rand, this.cherries, this.ocelot.world.getBlockState(this.cherries));
				this.eaten = true;
			} else {
				this.setPosition(this.cherries);
			} 
		}
	}

	@Override
	public void resetTask() {
		this.ocelot.getNavigator().clearPath();
		this.ticksAttempted = 0;
		this.eaten = false;
	}

	private Optional<BlockPos> findCoffeeCherries() {
		BlockPos.Mutable pos = new BlockPos.Mutable();
		for (int y = -5; y <= 5; ++y) {
			for (int x = -5; x <= 5; ++x) {
				for (int z = -5; z <= 5; ++z) {
					pos.setPos(this.ocelot).move(x, y, z);
					BlockState state = this.ocelot.world.getBlockState(pos);
					if (state.getBlock() == CoffeeCraft.Blocks.COFFEE_PLANT.get() && state.get(CoffeePlantBlock.HALF) == DoubleBlockHalf.UPPER && state.get(CoffeePlantBlock.AGE) == 3) {
						return Optional.of(pos);
					}
				}
			}
		}
		return Optional.empty();
	}
	private void setPosition(BlockPos pos) {
		this.ocelot.getNavigator().tryMoveToXYZ(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0.6F);
		this.cherries = pos;
	}
}
