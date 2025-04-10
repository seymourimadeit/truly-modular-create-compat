package tallestegg.truly_modular_create_compat.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.equipment.armor.BaseArmorItem;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tallestegg.truly_modular_create_compat.TrulyModularCreateCompatModCommon;
@Mixin(DivingHelmetItem.class)
public abstract class DivingHelmetMixin extends BaseArmorItem {

    public DivingHelmetMixin(ArmorMaterial armorMaterial, Type type, Properties properties, ResourceLocation textureLoc) {
        super(armorMaterial, type, properties, textureLoc);
    }

    @Inject(at = @At(value = "RETURN"), method = "getWornItem", cancellable = true)
    private static void getWornItem(Entity entity, CallbackInfoReturnable<ItemStack> cir) {
     //   cir.setReturnValue(TrulyModularCreateCompatModCommon.getWornItem(entity));
    }

    @ModifyVariable(method = "breatheUnderwater", at = @At(value = "STORE"), ordinal = -1)
    private static ItemStack breathe(ItemStack value, @Local(ordinal = 0) LivingEntity entity){
        return TrulyModularCreateCompatModCommon.getWornItem(entity);
    }

}
