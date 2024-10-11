package app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PartnerDto {

    private long id;
    private UserDto userId;
    private double money;
    private String type;
    private Timestamp dateCreated;
}
