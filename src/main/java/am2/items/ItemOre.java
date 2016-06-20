package am2.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import am2.defs.CreativeTabsDefs;

public class ItemOre extends ItemArsMagica2 {
	
	public static final String[] names = {
			"vinteum",
			"purified_vinteum",
			"chimerite",
			"moonstone",
			"sunstone",
			"bluetopaz",
			"arcaneash"};
	public static final int META_VINTEUM = 0;
	public static final int META_PURIFIED_VINTEUM = 1;
	public static final int META_CHIMERITE = 2;
	public static final int META_MOONSTONE = 3;
	public static final int META_SUNSTONE = 4;	
	public static final int META_BLUE_TOPAZ = 5;
	public static final int META_ARCANEASH = 6;
	
	public ItemOre() {
		 setHasSubtypes(true);
		 setMaxDamage(0);
		 setCreativeTab(CreativeTabsDefs.tabAM2Items);
	}
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (int i = 0; i < names.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item.ore." + names[MathHelper.clamp_int(stack.getItemDamage(), 0, names.length - 1)];
	}
}
