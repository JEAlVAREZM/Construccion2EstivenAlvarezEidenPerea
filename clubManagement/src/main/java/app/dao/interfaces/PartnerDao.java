package app.dao.interfaces;

import app.model.Partner;
import java.util.List;

public interface PartnerDao {
    void createPartner(Partner partner) throws Exception;
    Partner findPartnerById(long partnerId) throws Exception;
    void updatePartner(Partner partner) throws Exception;
    void deletePartner(long partnerId) throws Exception;
    List<Partner> findAllPartners() throws Exception;
}

