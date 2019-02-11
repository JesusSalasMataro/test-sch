package com.schibsted.spain.friends.configuration;

import com.schibsted.spain.friends.application.repositoryInterfaces.FriendshipRepository;
import com.schibsted.spain.friends.application.FriendshipService;
import com.schibsted.spain.friends.application.repositoryInterfaces.UserRepository;
import com.schibsted.spain.friends.application.UserService;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;
import com.schibsted.spain.friends.repository.InMemoryFriendshipRepository;
import com.schibsted.spain.friends.repository.InMemoryUsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependenciesConfiguration {

    @Bean
    FieldValidatorService getValidatorService() {
        return new FieldValidatorService();
    }

    @Bean
    UserRepository getUserRepository() {
        return new InMemoryUsersRepository();
    }

    @Bean
    UserService getUserService(FieldValidatorService validatorService, UserRepository userRepository) {

        return new UserService(validatorService, userRepository);
    }

    @Bean
    FriendshipRepository getFriendshipRepository() {
        return new InMemoryFriendshipRepository();
    }

    @Bean
    FriendshipService getFriendshipService(UserService userService, FriendshipRepository friendshipRepository) {
        return new FriendshipService(userService, friendshipRepository);
    }
}
