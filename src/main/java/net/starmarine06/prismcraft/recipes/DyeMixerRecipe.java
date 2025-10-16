package net.starmarine06.prismcraft.recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class DyeMixerRecipe implements Recipe<RecipeInput> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;

    public DyeMixerRecipe(NonNullList<Ingredient> inputItems, ItemStack output) {
        this.inputItems = inputItems;
        this.output = output;
    }

    @Override
    public boolean matches(RecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        if (input.size() < 3) {
            return false;
        }
        return inputItems.get(0).test(input.getItem(0)) &&
                inputItems.get(1).test(input.getItem(1)) &&
                inputItems.get(2).test(input.getItem(2));
    }

    @Override
    public ItemStack assemble(RecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DYE_MIXER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.DYE_MIXER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<DyeMixerRecipe> {
        public static final MapCodec<DyeMixerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").forGetter(recipe -> recipe.inputItems),
                        ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output)
                ).apply(instance, (ingredients, output) -> {
                    NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);
                    for (int i = 0; i < Math.min(ingredients.size(), 3); i++) {
                        inputs.set(i, ingredients.get(i));
                    }
                    return new DyeMixerRecipe(inputs, output);
                }));

        public static final StreamCodec<RegistryFriendlyByteBuf, DyeMixerRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(StreamCodec.list()).map(
                        ingredients -> {
                            NonNullList<Ingredient> inputs = NonNullList.withSize(3, Ingredient.EMPTY);
                            for (int i = 0; i < Math.min(ingredients.size(), 3); i++) {
                                inputs.set(i, ingredients.get(i));
                            }
                            return inputs;
                        }, NonNullList::stream),
                DyeMixerRecipe::getIngredients,
                ItemStack.STREAM_CODEC, recipe -> recipe.output,
                DyeMixerRecipe::new
        );

        @Override
        public MapCodec<DyeMixerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DyeMixerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}