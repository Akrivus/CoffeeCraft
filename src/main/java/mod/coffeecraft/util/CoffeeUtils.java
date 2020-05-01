package mod.coffeecraft.util;

import java.util.List;

import com.google.common.collect.Lists;

import mod.coffeecraft.init.CoffeeCraft;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class CoffeeUtils {
	public enum Flavors {
		SWEET(false, Biome.Category.JUNGLE, Biome.Category.MUSHROOM, Biome.Category.FOREST), NUTTY(true, Biome.Category.JUNGLE, Biome.Category.MUSHROOM, Biome.Category.FOREST),
		FRUITY(false, Biome.Category.SAVANNA, Biome.Category.MESA, Biome.Category.DESERT), FLORAL(true, Biome.Category.SAVANNA, Biome.Category.MESA, Biome.Category.DESERT),
		SMOKY(false, Biome.Category.SWAMP, Biome.Category.NETHER), SPICY(true, Biome.Category.SWAMP, Biome.Category.NETHER),
		GREEN(false, Biome.Category.EXTREME_HILLS, Biome.Category.PLAINS), HERBAL(true, Biome.Category.EXTREME_HILLS, Biome.Category.PLAINS),
		FUNKY(false);
		
		private final boolean altitude;
		private final Biome.Category[] biomes;
		Flavors(boolean altitude, Biome.Category... biomes) {
			this.altitude = altitude;
			this.biomes = biomes;
		}
		public boolean getBiomeMatch(Biome biome) {
			for (int i = 0; i < this.biomes.length; ++i) {
				Biome.Category category = this.biomes[i];
				if (biome.getCategory() == category) {
					return true;
				}
			}
			return false;
		}
		public boolean canGrowAt(BlockPos pos, Biome biome) {
			if (this.altitude) {
				return pos.getY() > 92 && getBiomeMatch(biome);
			} else {
				return pos.getY() < 92 && getBiomeMatch(biome);
			}
		}
		public List<Effect> getEffects() {
			List<Effect> effects = Lists.newArrayList();
			Notes[] notes = Notes.values();
			for (int i = 0; i < notes.length; ++i) {
				Notes note = notes[i];
				if (note.getFlavor() == this) {
					effects.add(note.getEffect());
				}
			}
			return effects;
		}
		public static Flavors getFlavor(BlockPos pos, Biome biome) {
			Flavors[] flavors = Flavors.values();
			for (int i = 0; i < flavors.length; ++i) {
				Flavors flavor = flavors[i];
				if (flavor.canGrowAt(pos, biome)) {
					return flavor;
				}
			}
			return null;
		}
	}
	public enum Roasts {
		LIGHT, MEDIUM, DARK;
	}
	public enum Notes {
		HONEY(Flavors.SWEET, Effects.HASTE, Roasts.LIGHT), MOLASSES(Flavors.SWEET, Effects.MINING_FATIGUE, Roasts.MEDIUM), COCOA(Flavors.SWEET, Effects.INVISIBILITY, Roasts.DARK),
		HAZELNUT(Flavors.NUTTY, Effects.LEVITATION, Roasts.LIGHT), NUTMEG(Flavors.NUTTY, Effects.REGENERATION, Roasts.MEDIUM), ALMOND(Flavors.NUTTY, Effects.NAUSEA, Roasts.DARK),
		CITRUS(Flavors.FRUITY, Effects.JUMP_BOOST, Roasts.LIGHT), STRAWBERRY(Flavors.FRUITY, Effects.SPEED, Roasts.MEDIUM), POTATO(Flavors.FRUITY, Effects.SLOWNESS, Roasts.DARK),
		JASMINE(Flavors.FLORAL, Effects.SLOW_FALLING, Roasts.LIGHT), ROSE(Flavors.FLORAL, Effects.NIGHT_VISION, Roasts.MEDIUM), SOCKS(Flavors.FLORAL, Effects.BLINDNESS, Roasts.DARK),
		HAY(Flavors.SMOKY, Effects.WEAKNESS, Roasts.LIGHT), OAK(Flavors.SMOKY, Effects.STRENGTH, Roasts.MEDIUM), TOBACCO(Flavors.SMOKY, Effects.HUNGER, Roasts.DARK),
		FRESH(Flavors.SPICY, Effects.WATER_BREATHING, Roasts.LIGHT), CINNAMON(Flavors.SPICY, Effects.FIRE_RESISTANCE, Roasts.MEDIUM), ONION(Flavors.SPICY, Effects.POISON, Roasts.DARK),
		CELERY(Flavors.GREEN, Effects.BAD_OMEN, Roasts.LIGHT), LEAVES(Flavors.GREEN, Effects.UNLUCK, Roasts.MEDIUM), CLOVER(Flavors.GREEN, Effects.LUCK, Roasts.DARK),
		FENNEL(Flavors.HERBAL, Effects.HEALTH_BOOST, Roasts.LIGHT), BASIL(Flavors.HERBAL, Effects.ABSORPTION, Roasts.MEDIUM), SAGE(Flavors.HERBAL, Effects.RESISTANCE, Roasts.DARK),
		PAPER(Flavors.FUNKY, Effects.NAUSEA, Roasts.LIGHT), VINEGAR(Flavors.FUNKY, CoffeeCraft.Potions.PURRFECT.get(), Roasts.MEDIUM), POOP(Flavors.FUNKY, Effects.POISON, Roasts.DARK);
		
		private final Flavors flavor;
		private final Effect effect;
		private final Roasts roast;
		Notes(Flavors flavor, Effect effect, Roasts roast) {
			this.flavor = flavor;
			this.effect = effect;
			this.roast = roast;
		}
		public Flavors getFlavor() {
			return this.flavor;
		}
		public Effect getEffect() {
			return this.effect;
		}
		public Roasts getRoast() {
			return this.roast;
		}
	}
}