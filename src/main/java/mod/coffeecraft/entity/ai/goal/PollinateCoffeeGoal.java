package mod.coffeecraft.entity.ai.goal;

import java.util.EnumSet;
import java.util.Optional;

import mod.coffeecraft.block.CoffeePlantBlock;
import mod.coffeecraft.init.CoffeeCraft;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class PollinateCoffeeGoal extends Goal {
	private final BeeEntity bee;
	private int ticksAttempted = 0;
	private boolean pollinated;

	public PollinateCoffeeGoal(BeeEntity bee) {
		 this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		 this.bee = bee;
	}
	
	@Override
	public boolean shouldExecute() {
		if (this.bee.world.isRaining() || this.bee.getAttackTarget() != null || this.bee.func_226425_er_()) {
			return false;
		} else {
			Optional<BlockPos> optional = this.findCoffeeBlossom();
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
		this.bee.func_226426_eu_();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return !this.pollinated;
	}

	@Override
	public void tick() {
		++this.ticksAttempted;
		if (this.ticksAttempted > 600) {
			this.bee.func_226431_g_(null);
			this.pollinated = true;
		} else {
			BlockPos blossom = this.bee.func_226424_eq_();
			double dist = blossom.distanceSq(this.bee.getPositionVec(), true);
			if (dist < 1.0D) {
				this.bee.getLookController().setLookPosition(blossom.getX(), blossom.getY(), blossom.getZ());
				this.bee.playSound(SoundEvents.field_226129_ad_, 1.0F, 1.0F);
				CoffeeCraft.Blocks.COFFEE_PLANT.get().grow((ServerWorld) this.bee.world, this.bee.world.rand, blossom, this.bee.world.getBlockState(blossom));
				this.pollinated = true;
			} else {
				this.setPosition(blossom);
			} 
		}
	}

	@Override
	public void resetTask() {
		this.bee.getNavigator().clearPath();
		this.ticksAttempted = 0;
		this.pollinated = false;
	}

	private Optional<BlockPos> findCoffeeBlossom() {
		BlockPos.Mutable pos = new BlockPos.Mutable();
		for (int y = -5; y <= 5; ++y) {
			for (int x = -5; x <= 5; ++x) {
				for (int z = -5; z <= 5; ++z) {
					pos.setPos(this.bee).move(x, y, z);
					BlockState state = this.bee.world.getBlockState(pos);
					if (state.getBlock() == CoffeeCraft.Blocks.COFFEE_PLANT.get() && state.get(CoffeePlantBlock.HALF) == DoubleBlockHalf.UPPER && state.get(CoffeePlantBlock.AGE) == 1) {
						return Optional.of(pos);
					}
				}
			}
		}
		return Optional.empty();
	}
	private void setPosition(BlockPos pos) {
		this.bee.getNavigator().tryMoveToXYZ(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 1.2F);
		this.bee.func_226431_g_(pos);
	}
}
