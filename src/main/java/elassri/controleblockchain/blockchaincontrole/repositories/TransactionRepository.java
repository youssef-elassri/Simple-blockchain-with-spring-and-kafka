package elassri.controleblockchain.blockchaincontrole.repositories;

import elassri.controleblockchain.blockchaincontrole.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
