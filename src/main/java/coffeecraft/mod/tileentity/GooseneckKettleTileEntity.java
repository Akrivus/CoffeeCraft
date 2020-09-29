package coffeecraft.mod.tileentity;

import coffeecraft.mod.init.CoffeeCraft;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class GooseneckKettleTileEntity extends TileEntity implements ITickableTileEntity {

    public GooseneckKettleTileEntity() {
        super(CoffeeCraft.TileEntities.GOOSENECK_KETTLE.get());
    }

    @Override
    public void tick() {

    }
}
