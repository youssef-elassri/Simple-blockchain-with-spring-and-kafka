package elassri.controleblockchain.blockchaincontrole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Transaction {
    @Id
    private String id;
    private Date date_creation;
    private String address_src;
    private String address_dst;
    Double montant;
}
