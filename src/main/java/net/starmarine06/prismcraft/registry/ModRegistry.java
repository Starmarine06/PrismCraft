package net.starmarine06.prismcraft.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;
import net.starmarine06.prismcraft.PrismcraftMod;
import net.starmarine06.prismcraft.block.DyeMixerBlock;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;

public class ModRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, PrismcraftMod.MODID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, PrismcraftMod.MODID);
    public static final DeferredRegister<net.minecraft.world.inventory.MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, PrismcraftMod.MODID);

    public static final Supplier<Block> DYE_MIXER = BLOCKS.register("dye_mixer",
            () -> new DyeMixerBlock(net.minecraft.world.level.block.state.BlockBehaviour.Properties.of().strength(2.5F)));

    public static final Supplier<Item> DYE_MIXER_ITEM = ITEMS.register("dye_mixer",
            () -> new BlockItem(DYE_MIXER.get(), new Item.Properties()));

    public static final Supplier<net.minecraft.world.inventory.MenuType<DyeMixerMenu>> DYE_MIXER_MENU =
            MENUS.register("dye_mixer", () -> new net.minecraft.world.inventory.MenuType<>(DyeMixerMenu::new));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        MENUS.register(bus);
    }
}