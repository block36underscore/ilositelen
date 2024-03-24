package gay.block36.ilositelen.mixin;

import gay.block36.ilositelen.Sitelen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {

    @Inject(method = "handleChatInput", at=@At("HEAD"), cancellable = true)
    public void sendChat(String string, boolean bl, CallbackInfoReturnable<Boolean> cir) {
        if (string.startsWith("!t ")) {
            Minecraft.getInstance().player.connection.sendChat(
                    Sitelen.transliterate(((ChatScreen)(Object) this).
                            normalizeChatMessage(string.substring(3)))
            );
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
