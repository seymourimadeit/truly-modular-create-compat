package tallestred.truly_modular_create_compat.fabric;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
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
        LivingEntityEvents.LivingTickEvent.TICK.register(TrulyModularCreateCompatFabric::scubaHelmetModularTick);
        TrulyModularCreateCompatModCommon.init();
    }

    public static void scubaHelmetModularTick(LivingEntityEvents.LivingTickEvent livingTickEvent) {
        LivingEntity entity = livingTickEvent.getEntity();
        Level world = entity.level();
        boolean second = world.getGameTime() % 20 == 0;
        boolean drowning = entity.getAirSupply() == 0;

        if (world.isClientSide)
            entity.getCustomData()
                    .remove("VisualBacktankAir");

        ItemStack helmet = getWornItem(entity);
        if (helmet.isEmpty())
            return;

        boolean lavaDiving = entity.isInLava();
        if (!helmet.getItem()
                .isFireResistant() && lavaDiving)
            return;
        if (!entity.isEyeInFluid(AllTags.AllFluidTags.DIVING_FLUIDS.tag) && !lavaDiving)
            return;
        if (entity instanceof Player && ((Player) entity).isCreative())
            return;

        List<ItemStack> backtanks = BacktankUtil.getAllWithAir(entity);
        if (backtanks.isEmpty())
            return;

        if (lavaDiving) {
            if (entity instanceof ServerPlayer sp)
                AllAdvancements.DIVING_SUIT_LAVA.awardTo(sp);
            if (backtanks.stream()
                    .noneMatch(backtank -> backtank.getItem()
                            .isFireResistant()))
                return;
        }

        if (drowning)
            entity.setAirSupply(10);

        if (world.isClientSide)
            entity.getCustomData()
                    .putInt("VisualBacktankAir", Math.round(backtanks.stream()
                            .map(BacktankUtil::getAir)
                            .reduce(0f, Float::sum)));

        if (!second)
            return;

        BacktankUtil.consumeAir(entity, backtanks.get(0), 1);

        if (lavaDiving)
            return;

        if (entity instanceof ServerPlayer sp)
            AllAdvancements.DIVING_SUIT.awardTo(sp);

        entity.setAirSupply(Math.min(entity.getMaxAirSupply(), entity.getAirSupply() + 10));
        entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 30, 0, true, false, true));
    }

    public static ItemStack getWornItem(Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (!(stack.getItem() instanceof ModularHelmet) && !NameProperty.KEY.contains("scuba")) {
            return ItemStack.EMPTY;
        }
        return stack;
    }
}
