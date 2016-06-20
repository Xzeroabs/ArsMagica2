package am2.lore;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import am2.gui.GuiArcaneCompendium;
import am2.spell.SpellModifiers;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CompendiumEntrySpellShape extends CompendiumEntry{

	private int meta = -1;
	private SpellModifiers[] modifiedBy;

	public CompendiumEntrySpellShape(){
		super(CompendiumEntryTypes.instance.SPELL_SHAPE);
	}

	@Override
	protected void parseEx(Node node){
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i){
			Node child = childNodes.item(i);
			if (child.getNodeName().equals("meta")){
				meta = Integer.parseInt(child.getTextContent());
			}else if (child.getNodeName().equals("modifiedBy") && !child.getTextContent().isEmpty()){
				String[] modifierTypes = child.getTextContent().split(",");
				ArrayList<SpellModifiers> list = new ArrayList<SpellModifiers>();
				for (String s : modifierTypes){
					try{
						SpellModifiers modifier = Enum.valueOf(SpellModifiers.class, s);
						list.add(modifier);
					}catch (Throwable t){
						LogHelper.debug("Compendium Parsing Error - No modifiable constant exists with the name '%s'", s);
					}
				}
				this.modifiedBy = list.toArray(new SpellModifiers[list.size()]);
			}
		}
	}

	public SpellModifiers[] getModifiedBy(){
		return modifiedBy;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected GuiArcaneCompendium getCompendiumGui(String searchID, int meta){
		return new GuiArcaneCompendium(searchID, ItemsCommonProxy.spell_component, 0);
	}

	@Override
	public ItemStack getRepresentItemStack(String searchID, int meta){
		SkillTreeEntry entry = SkillTreeManager.instance.getSkillTreeEntry(SkillManager.instance.getSkill(searchID));
		if (entry != null){
			return new ItemStack(ItemsCommonProxy.spell_component, 1, SkillManager.instance.getShiftedPartID(entry.registeredItem));
		}
		return null;
	}

}
