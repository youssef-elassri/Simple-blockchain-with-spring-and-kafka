package elassri.controleblockchain.blockchaincontrole.repositories;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, String> {
}
