package elassri.controleblockchain.blockchaincontrole.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @ToString
public class BlockChain {
    @Id
    private String id;
    private String nom;
    int diff;
    double miningReward;
    @OneToMany
    List<Block> blocks;
}
