package app.dao;

import app.dao.interfaces.PartnerDao;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.helper.Helper;
import app.model.Partner;
import app.dao.repository.PartnerRepository;
import app.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
public class PartnerDaoImplemetation implements PartnerDao {
@Autowired
PartnerRepository partnerRepository;
    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        partnerRepository.save(partner);

    }

    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        partnerRepository.delete(partner);

    }

    @Override
    public boolean existByUserId(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        return partnerRepository.existsByUserId(user);
    }


    @Override
    public PartnerDto getMoneyByPartner(double getMoney) throws Exception {

    }

    @Override
    public void updateMoney(PartnerDto partnerDto) throws Exception {

    }

    @Override
    public PartnerDto getTypeByPartner(PartnerDto partnerDto) throws Exception {

    }

    @Override
    public int countVipPartners() throws Exception {

    }

    @Override
    public void updatePartnerType(PartnerDto partnerDto) throws Exception {


    }
}
