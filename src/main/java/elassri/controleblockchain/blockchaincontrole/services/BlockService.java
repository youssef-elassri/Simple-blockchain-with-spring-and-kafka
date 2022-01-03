package elassri.controleblockchain.blockchaincontrole.services;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.entities.Transaction;

import java.util.List;

public interface BlockService {
    public Block createBlock(List<Transaction> transactions, String prv_hash);
    public String hashBlock(Block block);
    public Block blockMine(List<Transaction> transactions, String prv_hash, int diff);

}
