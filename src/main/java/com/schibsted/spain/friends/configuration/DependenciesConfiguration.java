package com.schibsted.spain.friends.configuration;

import com.schibsted.spain.friends.application.FriendshipRepository;
import com.schibsted.spain.friends.application.FriendshipService;
import com.schibsted.spain.friends.application.UserRepository;
import com.schibsted.spain.friends.application.UserService;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;
import com.schibsted.spain.friends.domainservices.SecurityService;
import com.schibsted.spain.friends.repository.InMemoryFriendshipRepository;
import com.schibsted.spain.friends.repository.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependenciesConfiguration {

    @Bean
    FieldValidatorService getValidatorService() {
        return new FieldValidatorService();
    }

    @Bean
    SecurityService getSecurityService() {
        return new SecurityService();
    }

    @Bean
    UserRepository getUserRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    UserService getUserService(FieldValidatorService validatorService, SecurityService securityService,
      UserRepository userRepository) {

        return new UserService(validatorService, securityService, userRepository);
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
