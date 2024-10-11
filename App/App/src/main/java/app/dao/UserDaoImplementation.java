package app.dao;

import app.dao.interfaces.UserDao;
import app.dao.repository.UserRepository;
import app.dto.UserDto;
import app.helper.Helper;
import app.model.User;


public class UserDaoImplementation implements UserDao {
    UserRepository userRepository;

    @Override
    public UserDto findByUserName(UserDto userDto) throws Exception {
        User user = userRepository.findByUserName(userDto.getUserName());
        return Helper.parse(user);

    }


    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        return userRepository.existsByDocument(userDto.getUserName());

    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        userRepository.save(user);

    }

    @Override
    public void deleteUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        userRepository.delete(user);


    }

    @Override
    public void updateUserRole(UserDto userDto) throws Exception {


   }
}