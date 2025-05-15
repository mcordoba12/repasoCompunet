package co.icesi.taskManager.services.interfaces;

import co.icesi.taskManager.model.User;

public interface UserService {


    User findByEmail(String username);

    User createUser (User user);

}
