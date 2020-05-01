package mod.coffeecraft.tileentity;

import mod.coffeecraft.init.CoffeeCraft;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class CoffeeCauldronTileEntity extends TileEntity implements ITickableTileEntity {

	public CoffeeCauldronTileEntity() {
		super(CoffeeCraft.TileEntities.COFFEE_CAULDRON.get());
	}

	@Override
	public void tick() {
		
	}
}
