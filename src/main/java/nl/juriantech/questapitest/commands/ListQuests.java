package nl.juriantech.questapitest.commands;

import nl.juriantech.questapi.Quest_API;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

public class ListQuests {

    private final Quest_API api;

    public ListQuests(Quest_API api) {
        this.api = api;
    }

    @Command("listquests")
    public void onListQuests(Player player) {
        StringBuilder questList = new StringBuilder("Available quests:\n");

        for (String questId : api.getQuestManager().getAllQuests().keySet()) {
            questList.append("- ").append(questId).append("\n");
        }

        player.sendMessage(questList.toString());
    }
}
