package net.starmarine06.prismcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;

import java.util.function.Consumer;

import static com.electronwill.nightconfig.core.io.IndentStyle.TABS;

public class ModCreativeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
		DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrismCraftMod.MOD_ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PRISMCRAFT_TAB =
		CREATIVE_MODE_TABS.register("prismcraft_tab", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.prismcraft.prismcraft_tab"))
			.withSearchBar()
			.icon(() -> new ItemStack(ModItems.PRISMCRAFT_ICON.get()))
			.displayItems((parameters, output) -> {
				// Dye Mixer
				output.accept(new ItemStack(ModItems.DYE_MIXER.get()));
				output.accept(new ItemStack(ModItems.TITANIUM_DYE.get()));
				// Wood
				output.accept(new ItemStack(ModItems.PRISM_LOG.get()));
				output.accept(new ItemStack(ModItems.PRISM_STRIPPED_LOG.get()));
				output.accept(new ItemStack(ModItems.PRISM_PLANKS.get()));
				output.accept(new ItemStack(ModItems.PRISM_STAIRS.get()));
				output.accept(new ItemStack(ModItems.PRISM_SLAB.get()));
				output.accept(new ItemStack(ModItems.PRISM_WOOD_WALL.get()));
				output.accept(new ItemStack(ModItems.PRISM_FENCE.get()));
				output.accept(new ItemStack(ModItems.PRISM_FENCE_GATE.get()));
					// Oak
					output.accept(new ItemStack(ModItems.PRISM_OAK_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_OAK_TRAPDOOR.get()));
					// Spruce
					output.accept(new ItemStack(ModItems.PRISM_SPRUCE_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_SPRUCE_TRAPDOOR.get()));
					// Birch
					output.accept(new ItemStack(ModItems.PRISM_BIRCH_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_BIRCH_TRAPDOOR.get()));
					// Jungle
					output.accept(new ItemStack(ModItems.PRISM_JUNGLE_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_JUNGLE_TRAPDOOR.get()));
					// Acacia
					output.accept(new ItemStack(ModItems.PRISM_ACACIA_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_ACACIA_TRAPDOOR.get()));
					// Dark Oak
					output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_TRAPDOOR.get()));
					// Mangrove
					output.accept(new ItemStack(ModItems.PRISM_MANGROVE_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_MANGROVE_TRAPDOOR.get()));
					// Cherry
					output.accept(new ItemStack(ModItems.PRISM_CHERRY_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_CHERRY_TRAPDOOR.get()));
					// Pale Oak
					output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_TRAPDOOR.get()));
					// Bamboo
					output.accept(new ItemStack(ModItems.PRISM_BAMBOO_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_BAMBOO_TRAPDOOR.get()));
					// Crimson
					output.accept(new ItemStack(ModItems.PRISM_CRIMSON_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_CRIMSON_TRAPDOOR.get()));
					// Warped
					output.accept(new ItemStack(ModItems.PRISM_WARPED_DOOR.get()));
					output.accept(new ItemStack(ModItems.PRISM_WARPED_TRAPDOOR.get()));
				output.accept(new ItemStack(ModItems.PRISM_PRESSURE_PLATE.get()));
				output.accept(new ItemStack(ModItems.PRISM_BUTTON.get()));
				output.accept(new ItemStack(ModItems.PRISM_SIGN_ITEM.get()));
				output.accept(new ItemStack(ModItems.PRISM_HANGING_SIGN_ITEM.get()));
				output.accept(new ItemStack(ModItems.PRISM_BARREL.get()));
				output.accept(new ItemStack(ModItems.PRISM_LADDER.get()));
				// Items
				output.accept(new ItemStack(ModItems.PRISM_FLOWER_POT.get()));
				output.accept(new ItemStack(ModItems.PRISM_DECORATED_POT.get()));
				output.accept(new ItemStack(ModItems.PRISM_CANDLE.get()));
				output.accept(new ItemStack(ModItems.PRISM_SLIME_BLOCK.get()));
				// Honey
				output.accept(new ItemStack(ModItems.PRISM_HONEY_BLOCK.get()));
				output.accept(new ItemStack(ModItems.PRISM_HONEYCOMB_BLOCK.get()));
				// Stone
				output.accept(new ItemStack(ModItems.PRISM_STONE.get()));
				output.accept(new ItemStack(ModItems.PRISM_STONE_SLAB.get()));
				output.accept(new ItemStack(ModItems.PRISM_STONE_STAIRS.get()));
				output.accept(new ItemStack(ModItems.PRISM_STONE_WALL.get()));
				// Cobble
				output.accept(new ItemStack(ModItems.PRISM_COBBLESTONE.get()));
				// Smooth
				output.accept(new ItemStack(ModItems.PRISM_SMOOTH_STONE.get()));
				// Stonebrick / cracked
				output.accept(new ItemStack(ModItems.PRISM_STONE_BRICKS.get()));
				output.accept(new ItemStack(ModItems.PRISM_CRACKED_STONE_BRICKS.get()));
				// Granite
				// Diorite
				// Andesite
				output.accept(new ItemStack(ModItems.PRISM_ANDESITE.get()));
				output.accept(new ItemStack(ModItems.PRISM_ANDESITE_SLAB.get()));
				output.accept(new ItemStack(ModItems.PRISM_ANDESITE_STAIRS.get()));
				output.accept(new ItemStack(ModItems.PRISM_ANDESITE_WALL.get()));
				// Deepslate
				output.accept(new ItemStack(ModItems.PRISM_DEEPSLATE.get()));
				output.accept(new ItemStack(ModItems.PRISM_COBBLED_DEEPSLATE.get()));
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_DEEPSLATE.get()));
				output.accept(new ItemStack(ModItems.PRISM_DEEPSLATE_TILES.get()));
				// Tuff
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_TUFF.get()));
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_TUFF_BRICKS.get()));
				// Brick
				// Resin
				output.accept(new ItemStack(ModItems.PRISM_RESIN_BLOCK.get()));
				output.accept(new ItemStack(ModItems.PRISM_RESIN_BRICKS.get()));
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_RESIN_BRICKS.get()));
				// Sand + Sandstone
				output.accept(new ItemStack(ModItems.PRISM_SAND.get()));
				output.accept(new ItemStack(ModItems.PRISM_SANDSTONE.get()));
				output.accept(new ItemStack(ModItems.PRISM_CUT_SANDSTONE.get()));
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_SANDSTONE.get()));
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_RED_SANDSTONE.get()));
				output.accept(new ItemStack(ModItems.PRISM_WART_BLOCK.get()));
				// Prismarine
				output.accept(new ItemStack(ModItems.PRISM_PRISMARINE_BRICKS.get()));
				output.accept(new ItemStack(ModItems.PRISM_DARK_PRISMARINE.get()));
				// Purpur
				output.accept(new ItemStack(ModItems.PRISM_PURPUR_BLOCK.get()));
				output.accept(new ItemStack(ModItems.PRISM_PURPUR_PILLAR.get()));
				// Quartz
				output.accept(new ItemStack(ModItems.PRISM_QUARTZ_BLOCK.get()));
				output.accept(new ItemStack(ModItems.PRISM_QUARTZ_PILLAR.get()));
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_QUARTZ.get()));
				// Copper
				output.accept(new ItemStack(ModItems.PRISM_COPPER_BLOCK.get()));
				output.accept(new ItemStack(ModItems.PRISM_CHISELED_COPPER.get()));
				output.accept(new ItemStack(ModItems.PRISM_CUT_COPPER.get()));
				output.accept(new ItemStack(ModItems.PRISM_COPPER_GRATE.get()));
				output.accept(new ItemStack(ModItems.PRISM_CUT_COPPER_SLAB.get()));
				output.accept(new ItemStack(ModItems.PRISM_CUT_COPPER_STAIRS.get()));
				output.accept(new ItemStack(ModItems.PRISM_COPPER_DOOR.get()));
				output.accept(new ItemStack(ModItems.PRISM_COPPER_TRAPDOOR.get()));
				output.accept(new ItemStack(ModItems.PRISM_COPPER_BULB.get()));
				// Wool
				output.accept(new ItemStack(ModItems.PRISM_WOOL.get()));
				output.accept(new ItemStack(ModItems.PRISM_CARPET.get()));
				// Terracotta
				output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA.get()));
				output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA_SLAB.get()));
				output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA_STAIRS.get()));
				output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA_WALL.get()));
				// Concrete
				output.accept(new ItemStack(ModItems.PRISM_CONCRETE.get()));
				output.accept(new ItemStack(ModItems.PRISM_CONCRETE_POWDER.get()));
				output.accept(new ItemStack(ModItems.PRISM_CONCRETE_SLAB.get()));
				output.accept(new ItemStack(ModItems.PRISM_CONCRETE_STAIRS.get()));
				output.accept(new ItemStack(ModItems.PRISM_CONCRETE_WALL.get()));
				// Glass
			})
			.build());

	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TABS.register(eventBus);
	}
}