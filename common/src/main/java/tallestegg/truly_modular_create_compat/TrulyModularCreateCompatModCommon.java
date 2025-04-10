package tallestegg.truly_modular_create_compat;

import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import smartin.miapi.item.modular.items.ModularHelmet;
import smartin.miapi.modules.properties.NameProperty;

public final class TrulyModularCreateCompatModCommon {
    public static final String MOD_ID = "truly_modular_create_compat";

    public static void init() {

    }

    public static ItemStack getWornItem(Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (!(stack.getItem() instanceof ModularHelmet) && !NameProperty.KEY.contains("scuba") && !(stack.getItem() instanceof DivingHelmetItem)) {
            return ItemStack.EMPTY;
        }
        return stack;
    }
}
