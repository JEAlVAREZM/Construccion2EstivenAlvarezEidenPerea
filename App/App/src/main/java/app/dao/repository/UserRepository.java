package app.dao.repository;

import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByDocument(String userName);

    public User findByUserName(String UserName);

}