package dev.soterocra.usecase.telegram.intentions;

import dev.soterocra.model.Command;
import io.quarkus.arc.All;
import io.quarkus.arc.InstanceHandle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StrategyFactory {

//    void startup(@Observes StartupEvent eventbus) {}

    @All
    @Inject
    List<TelegramIntentionStrategy> strategies;

    @Inject
    DefaultStrategy defaultStrategy;

    public List<TelegramIntentionStrategy> getStrategies() {
        return strategies;
    }

    public TelegramIntentionStrategy getStrategy(Command command) {
        return getStrategies().stream().filter(s -> s.getCommand().equals(command.getCommand())).findFirst().orElse(defaultStrategy);
    }

}
