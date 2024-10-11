package app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDetailDto {

    private long id;
    private InvoiceDto invoice;
    private int item;
    private String description;
    private double amount;
}
