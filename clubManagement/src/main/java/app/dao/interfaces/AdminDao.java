package app.dao.interfaces;

import app.dto.UserDto;

public interface AdminDao {
    void createUser(UserDto userDto) throws Exception;
    void deleteUser(Long userId) throws Exception;
}
