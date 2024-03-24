package gay.block36.ilositelen.client;

import gay.block36.ilositelen.Sitelen;
import net.fabricmc.api.ClientModInitializer;

public class IlositelenClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Sitelen.readFile();
    }
}
