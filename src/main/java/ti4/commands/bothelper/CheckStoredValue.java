package ti4.commands.bothelper;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import ti4.commands.GameStateSubcommand;
import ti4.helpers.Constants;
import ti4.map.Game;
import ti4.message.MessageHelper;

class CheckStoredValue extends GameStateSubcommand {

    public CheckStoredValue() {
        super(
                "check_stored_value",
                "Check a stored value.",
                false,
                false);
        addOption(OptionType.STRING, "stored_key", "Key to the value", true, false);
        addOption(OptionType.STRING, Constants.GAME_NAME, "Game to check", false, true);
    }

    public void execute(SlashCommandInteractionEvent event) {
        Game game = getGame();
        String key = event.getOption("stored_key").getAsString();
        String value = game.getStoredValue(key);
        String message;
        if (value.isEmpty()) {
            message = "Found no stored value for key `" + key + "`. Are you sure you typed the correct key?";
        } else {
            message = "Key `" + key + "` has the following stored value: `" + value + "`";
        }
        MessageHelper.sendMessageToChannel(event.getMessageChannel(), message);
    }
}
