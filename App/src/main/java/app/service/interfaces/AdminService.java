package app.service.interfaces;
/**
 *
 * @author Publicidad
 */
import app.dto.PartnerDto;

public interface AdminService {

    public void createPartner(PartnerDto partnerDto) throws Exception;

}
