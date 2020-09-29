package coffeecraft.mod.block;

import net.minecraft.block.CauldronBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class CoffeeCauldronBlock extends CauldronBlock {

    public CoffeeCauldronBlock() {
        super(Properties.create(Material.IRON, MaterialColor.STONE));
    }
}
