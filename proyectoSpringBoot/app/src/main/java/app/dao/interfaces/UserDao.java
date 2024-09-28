
package app.dao.interfaces;

import app.dto.UserDto;

public interface UserDao {
    void createUser(UserDto userDto) throws Exception;
    UserDto findById(long id) throws Exception;
    UserDto findByUserName(String username )throws Exception;
    boolean existsByUserName(String username) throws Exception;
}
