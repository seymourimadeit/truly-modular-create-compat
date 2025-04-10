package tallestegg.truly_modular_create_compat.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import tallestegg.truly_modular_create_compat.TrulyModularCreateCompatModCommon;
import tallestegg.truly_modular_create_compat.forge.events.TMCCMEvents;

@Mod(TrulyModularCreateCompatModCommon.MOD_ID)
public final class TrulyModularCreateCompatModForge {
    public TrulyModularCreateCompatModForge() {
       // TMCCMEvents.init();
        EventBuses.registerModEventBus(TrulyModularCreateCompatModCommon.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        TrulyModularCreateCompatModCommon.init();
    }
}
