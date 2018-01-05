package subaraki.telepads.item;

import static lib.item.ItemRegistry.registerRender;

import java.util.List;

import akka.io.Tcp.Register;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.telepads.block.TelepadBlocks;
import subaraki.telepads.mod.Telepads;

public class TelepadItems {

	public TelepadItems() {
		MinecraftForge.EVENT_BUS.register(this);
		loadItems();
	}
	
	@SubscribeEvent
	public void register(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(
				toggler,
				transmitter,
				redstone_upgrade,
				ender_bead,
				ender_bead_necklace,
				tp_mod_upgrade,
				telepad_block);
	}
	
	public Item toggler;
	public Item transmitter;
	public Item redstone_upgrade;

	public Item ender_bead;
	public Item ender_bead_necklace;

	public Item tp_mod_upgrade;
	
	private ItemBlock telepad_block;
	
	private static String modid = Telepads.MODID;

	
	public void loadItems(){

		ender_bead = new ItemEnderBead().setMaxStackSize(16).setUnlocalizedName(modid+".ender_bead").setRegistryName("ender_bead").setCreativeTab(CreativeTabs.MATERIALS);
		ender_bead_necklace = new ItemEnderNecklace().setMaxStackSize(8).setUnlocalizedName(modid+".ender_bead_necklace").setRegistryName("ender_bead_necklace").setCreativeTab(CreativeTabs.MATERIALS);

		toggler = new Item().setUnlocalizedName(modid+".toggler").setRegistryName("toggler").setCreativeTab(CreativeTabs.REDSTONE);
		transmitter = new Item().setUnlocalizedName(modid+".transmitter").setRegistryName("transmitter").setCreativeTab(CreativeTabs.REDSTONE);
		redstone_upgrade = new Item().setUnlocalizedName(modid+".upgrade").setRegistryName("upgrade");
		
		tp_mod_upgrade = new Item(){
			public void addInformation(ItemStack stack, World worldIn, java.util.List<String> tooltip, ITooltipFlag flagIn) {
				tooltip.add("can be used by people with creative acces to enable telepads to teleport to a location defined in config");
			}
		}
			.setUnlocalizedName(modid+".tp_upgrade").setRegistryName("tp_upgrade").setCreativeTab(CreativeTabs.REDSTONE);
		
		telepad_block =  (ItemBlock) new ItemBlock(Telepads.blocks.blockTelepad){

			@Override
			public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
				super.addInformation(stack, worldIn, tooltip, flagIn);

				if(stack.hasTagCompound()){
					if (stack.getTagCompound().hasKey("colorFrame")){
						int color = stack.getTagCompound().getInteger("colorFrame");
						EnumDyeColor edc = null;
						for(EnumDyeColor dye : EnumDyeColor.values())
							if(dye.getColorValue() == color)
								edc = dye;	
						tooltip.add("Feet : "+ (edc == null ? new TextComponentTranslation("feet.color") : edc.getName()));
					}

					if (stack.getTagCompound().hasKey("colorBase")){
						int color = stack.getTagCompound().getInteger("colorBase");
						EnumDyeColor edc = null;
						for(EnumDyeColor dye : EnumDyeColor.values())
							if(dye.getColorValue() == color)
								edc = dye;	
						tooltip.add("Arrows : "+ (edc == null ? new TextComponentTranslation("arrow.color") : edc.getName()));
					}
				}
			}

		}.setRegistryName(Telepads.blocks.blockTelepad.getRegistryName());

//		register();
	}

	private void register(){
//		registerItem(redstone_upgrade);
//		registerItem(transmitter);
//		registerItem(toggler);
//		registerItem(telepad_block);
//		registerItem(ender_bead);
//		registerItem(ender_bead_necklace);
//		registerItem(tp_mod_upgrade);
	}

	public void registerRenders(){
		registerRender(toggler, "toggler", modid);
		registerRender(transmitter, "transmitter", modid);
		registerRender(redstone_upgrade, "redstone_upgrade", modid);
		registerRender(telepad_block, "telepad", modid);
		registerRender(ender_bead, "ender_bead", modid);
		registerRender(ender_bead_necklace, "ender_bead_necklace", modid);
		registerRender(tp_mod_upgrade, "mod_tp_upgrade", modid);
	}
}
