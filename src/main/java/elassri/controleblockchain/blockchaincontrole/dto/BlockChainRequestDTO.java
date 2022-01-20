package elassri.controleblockchain.blockchaincontrole.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class BlockChainRequestDTO {

    private String nom;
    int diff;
    double miningReward;
}
