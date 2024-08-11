package ovh.sapientes.example.neoforge;

import net.neoforged.fml.common.Mod;

import ovh.sapientes.example.ExampleMod;

@SuppressWarnings("unused")
@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
