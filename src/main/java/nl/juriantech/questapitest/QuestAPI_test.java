package nl.juriantech.questapitest;

import nl.juriantech.questapi.QuestAPI;
import nl.juriantech.questapitest.commands.*;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

public final class QuestAPI_test extends JavaPlugin {

    private QuestAPI api;

    @Override
    public void onEnable() {
        api = QuestAPI.getPlugin(QuestAPI.class);
        BukkitCommandHandler commandHandler = BukkitCommandHandler.create(this);

        commandHandler.register(new CreateQuest(api));
        commandHandler.register(new StartQuest(api));
        commandHandler.register(new LevelUp(api));
        commandHandler.register(new GetStats(api));
        commandHandler.register(new ListQuests(api));
        commandHandler.registerBrigadier();
    }
}