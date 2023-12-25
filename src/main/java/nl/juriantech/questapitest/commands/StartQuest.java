package nl.juriantech.questapitest.commands;

import nl.juriantech.questapi.Quest_API;
import nl.juriantech.questapi.objects.Quest;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

public class StartQuest {

    private final Quest_API api;

    public StartQuest(Quest_API api) {
        this.api = api;
    }

    @Command("startquest")
    public void onStartQuest(Player player, String questId) {
        Quest quest = api.getQuestManager().getQuest(questId);

        if (quest == null) {
            player.sendMessage("That quest doesn't exist!");
            return;
        }

        if (quest.getPlayerProgress().containsKey(player.getUniqueId())) {
            player.sendMessage("You've already started that quest!");
            return;
        }

        quest.levelUp(player, 1)
                .thenAccept(result -> {
                    player.sendMessage("Quest '" + questId + "' started!");
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    player.sendMessage("Failed to start the quest. Please check your console for details.");
                    return null;
                });
    }
}
