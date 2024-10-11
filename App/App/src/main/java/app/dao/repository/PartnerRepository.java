package app.dao.repository;

import app.dto.PartnerDto;
import app.dto.UserDto;
import app.model.Partner;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    public boolean existsByUserId(User user);

    public Partner findByUserName(String UserName);

}