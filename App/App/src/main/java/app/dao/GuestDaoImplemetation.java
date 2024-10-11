package app.dao;

import app.dao.interfaces.GuestDao;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.helper.Helper;
import app.model.Guest;
import app.model.Partner;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GuestDaoImplemetation implements GuestDao {
 @Autowired
 GuestRepostory guestRepostory;
    @Override
    public void createGuest(GuestDto guestDto) throws SQLException {
        Guest guest = Helper.parse(guestDto);
        guestRepostory.save(guest);
        guestDto.setId(guest.getId());
    }

    @Override
    public void deleteGuest(GuestDto guestDto) throws Exception {
        Guest guest = Helper.parse(guestDto);


    }

    @Override
    public GuestDto existByGuest(UserDto userDto) throws Exception {
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE USERID = ?";

    }

    @Override
    public List<GuestDto> statusGuest(PartnerDto partnerDto) throws Exception {
        List<GuestDto> guests = new ArrayList<>();
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE PARTNERID = ?";

    }

    @Override
    public void changeStatus(GuestDto guestDto) throws Exception {
        String query = "UPDATE GUEST SET STATUS = ? WHERE USERID = ?";

    }

    @Override
    public GuestDto getGuestById(long guestId) throws Exception {
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE ID = ?";

    }

    @Override
    public List<GuestDto> getGuestsByPartnerId(long partnerId) throws Exception {
        List<GuestDto> guests = new ArrayList<>();
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE PARTNERID = ?";

    }

    @Override
    public int countGuestsByPartnerId(long partnerId) throws Exception {
         String query = "SELECT COUNT(*) AS count FROM GUEST WHERE PARTNERID = ? AND STATUS = 'activo'";

    }


    }


   
