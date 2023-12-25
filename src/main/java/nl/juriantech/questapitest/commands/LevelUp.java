package nl.juriantech.questapitest.commands;

import nl.juriantech.questapi.Quest_API;
import nl.juriantech.questapi.objects.Quest;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

public class LevelUp {

    private final Quest_API api;

    public LevelUp(Quest_API api) {
        this.api = api;
    }

    @Command("levelup")
    public void onLevelUp(Player player, String questId) {
        Quest quest = api.getQuestManager().getQuest(questId);

        if (quest == null) {
            player.sendMessage("That quest doesn't exist!");
            return;
        }

        if (!quest.getPlayerProgress().containsKey(player.getUniqueId())) {
            player.sendMessage("You didn't start that quest yet!");
            return;
        }

        int newLevel = quest.getPlayerProgress().get(player.getUniqueId()) + 1;
        quest.levelUp(player, newLevel)
                .thenAccept(result -> {
                    player.sendMessage("Quest '" + questId + "' leveled up to level " + newLevel);
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    player.sendMessage("Failed to level up the player. Please check your console for details.");
                    return null;
                });
    }
}
