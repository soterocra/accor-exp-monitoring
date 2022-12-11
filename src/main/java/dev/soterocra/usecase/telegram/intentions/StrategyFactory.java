package dev.soterocra.usecase.telegram.intentions;

import dev.soterocra.model.Command;
import io.quarkus.arc.All;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class StrategyFactory {

    @All
    @Inject
    List<TelegramIntentionStrategy> strategies;

    @Inject
    DefaultStrategy defaultStrategy;

    public List<TelegramIntentionStrategy> getStrategies() {
        return strategies;
    }

    public TelegramIntentionStrategy getStrategy(Command command) {
//        TelegramIntentionStrategy strategy = null;
//
//        for (int i = 0; i < getStrategies().size(); i++) {
//            if (getStrategies().get(i).getCommand().equals(command.getCommand()))
//                strategy = getStrategies().get(i);
//            else
//                strategy = defaultStrategy;
//        }
//
//        return strategy;


        return getStrategies().stream().filter(s -> s.getCommand().equals(command.getCommand())).findFirst().orElse(defaultStrategy);
    }

}
