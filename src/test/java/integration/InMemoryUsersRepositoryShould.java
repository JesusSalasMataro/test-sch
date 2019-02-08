package integration;

import helpers.ExtendedInMemoryUserRepository;
import org.junit.Before;
import org.junit.Test;

public class InMemoryUserRepositoryShould {

    ExtendedInMemoryUserRepository usersRepository;

    @Before
    public void beforeEachTest() {
        usersRepository = new ExtendedInMemoryUserRepository();
    }

    @Test
    public void should_save_user() {

    }
}
