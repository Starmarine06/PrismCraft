package net.starmarine06.prismcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(PrismCraftMod.MOD_ID);

    public static final DeferredItem<Item> PRISMCRAFT_ICON = ITEMS.registerSimpleItem(
            "prismcraft_icon",
            new Item.Properties()
    );

}