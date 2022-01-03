package elassri.controleblockchain.blockchaincontrole.repositories;

import elassri.controleblockchain.blockchaincontrole.entities.BlockChain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockChainRepository extends JpaRepository<BlockChain, String> {
}
