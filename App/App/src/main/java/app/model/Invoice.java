package app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person PersonId;

    @ManyToOne
    @JoinColumn(name = "partner_id", referencedColumnName = "id")
    private Partner partnerId;

    @Column(name = "creaciondate")
    @Temporal(TemporalType.TIMESTAMP)  // Se especifica el tipo de fecha/hora
    private Date creaciondate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private boolean status;
}
