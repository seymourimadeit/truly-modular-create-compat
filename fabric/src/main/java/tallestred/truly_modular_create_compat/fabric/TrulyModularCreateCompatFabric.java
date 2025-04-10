package tallestred.truly_modular_create_compat.fabric;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import smartin.miapi.item.modular.items.ModularHelmet;
import smartin.miapi.modules.properties.NameProperty;
import tallestegg.truly_modular_create_compat.TrulyModularCreateCompatModCommon;

import java.util.List;


public final class TrulyModularCreateCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TrulyModularCreateCompatModCommon.init();
    }
}
