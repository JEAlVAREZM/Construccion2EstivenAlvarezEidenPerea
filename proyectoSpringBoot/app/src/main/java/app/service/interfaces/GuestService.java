package app.service.interfaces;

import app.dto.GuestDto;

import java.util.List;

public interface GuestService {
    void createGuest(GuestDto guestDto) throws Exception;
    GuestDto findGuestById(Long guestId) throws Exception;
    List<GuestDto> findGuestsByPartnerId(Long partnerId) throws Exception;
    void updateGuest(GuestDto guestDto) throws Exception;
    void deleteGuest(Long guestId) throws Exception;
    void updateInvitationStatus(Long guestId, String newStatus) throws Exception;
    void registerGuest(GuestDto guestDto) throws Exception;
}

