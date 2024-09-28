package app.service.interfaces;

import app.dto.UserDto;

public interface LoginService {
    public void login(UserDto userDto) throws Exception;
    public void logout();
    void createUser(UserDto userDto) throws Exception;
    boolean isUserPresent(String userName) throws Exception;
}
