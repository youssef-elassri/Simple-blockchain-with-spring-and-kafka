package elassri.controleblockchain.blockchaincontrole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class Block {
    @Id
    private String id;
    private Date dateCreation;
    private String blockHash;
    private String prvBlockHash;
    @OneToMany
    List<Transaction> transactions;
    int nonce;

}
