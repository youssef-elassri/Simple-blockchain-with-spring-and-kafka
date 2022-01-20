package elassri.controleblockchain.blockchaincontrole.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class TransactionRequestDTO {
    private String address_src;
    private String address_dst;
    Double montant;
}
