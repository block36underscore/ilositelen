package gay.block36.ilositelen.mixin;

import gay.block36.ilositelen.Sitelen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OutgoingChatMessage.class)
public interface OutgoingChatMessageMixin {

    @Inject(method = "create", at=@At("HEAD"), cancellable = true)
    private static void create(PlayerChatMessage playerChatMessage, CallbackInfoReturnable<OutgoingChatMessage> cir) {
        var content = playerChatMessage.signedContent();
        if (content.startsWith("!t ")) {
            System.out.println("chat sent!");
            var message = PlayerChatMessage.unsigned(
                    Minecraft.getInstance().player.getUUID(),
                    Sitelen.transliterate(content.substring(3))
            );
            cir.setReturnValue(new OutgoingChatMessage.Player(message));
            cir.cancel();
        }
    }
}
