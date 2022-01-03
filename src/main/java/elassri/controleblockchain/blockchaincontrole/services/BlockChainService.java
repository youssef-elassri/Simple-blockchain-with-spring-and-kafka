package elassri.controleblockchain.blockchaincontrole.services;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.entities.BlockChain;

public interface BlockChainService {

    public BlockChain createBlockChain(String nom, int diff, double miningReward);
    public void mineBlock(String addressMiner);
    public Block getLastMinedBlock();
    public boolean blockChainIsValid();
    public double getBalance(String walletAddress);
}
