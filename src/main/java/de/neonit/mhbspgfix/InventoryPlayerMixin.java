package de.neonit.mhbspgfix;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.player.InventoryPlayer;

@Mixin(InventoryPlayer.class)
public abstract class InventoryPlayerMixin implements IInventory
{
    private static int getHotbarSize()
    { return 36; }

    private static boolean isHotbar(int index)
    { return index >= 0 && index < InventoryPlayer.getHotbarSize(); }

    @Shadow public int currentItem;
    @Shadow @Final public NonNullList<ItemStack> mainInventory;

    public int getBestHotbarSlot()
    {
        final int size = InventoryPlayer.getHotbarSize();
        for (int i = 0; i < size; ++i)
        {
            int j = (this.currentItem + i) % size;

            if (((ItemStack)this.mainInventory.get(j)).isEmpty())
            { return j; }
        }

        for (int k = 0; k < size; ++k)
        {
            int l = (this.currentItem + k) % size;

            if (!((ItemStack)this.mainInventory.get(l)).isItemEnchanted())
            { return l; }
        }

        return this.currentItem;
    }
}
