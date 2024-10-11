package app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {

    private long id;
    private PersonDto personId;
    private PartnerDto partnerId;
    private Date creationDate;
    private double amount;
    private boolean status;
}
