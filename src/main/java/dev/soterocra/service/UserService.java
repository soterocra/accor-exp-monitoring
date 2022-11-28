package dev.soterocra.service;

import dev.soterocra.entity.UserEntity;
import dev.soterocra.model.User;
import org.jboss.logging.Logger;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class.getSimpleName());

    private final DynamoDbTable<UserEntity> userTable;

    public UserService(DynamoDbEnhancedClient dynamo) {
        this.userTable = dynamo.table(UserEntity.TABLE_NAME, TableSchema.fromBean(UserEntity.class));
    }

    public List<User> findAll() {
        LOG.info("Início das buscas de User");
        return userTable.scan().items().stream()
                .map(userEntity -> {
                    var user = new User(userEntity.getChatId(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getUsername());
                    userEntity.getPreferredRegions().forEach(user.getPreferredRegions()::add);
                    return user;
                })
                .toList();
    }
}
