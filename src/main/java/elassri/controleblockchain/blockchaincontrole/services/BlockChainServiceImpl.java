package elassri.controleblockchain.blockchaincontrole.services;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.entities.BlockChain;
import elassri.controleblockchain.blockchaincontrole.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlockChainServiceImpl implements BlockChainService {

    private List<Transaction> pendingTransactions;
    private BlockChain blockChain;

    BlockService blockService;

    public BlockChainServiceImpl(BlockService blockService) {
        this.blockService = blockService;
        this.pendingTransactions = new ArrayList<>();

    }

    @Override
    public BlockChain createBlockChain(String nom, int diff, double miningReward) {
        List<Block> blocks = new ArrayList<>();
        BlockChain blockChain = new BlockChain(UUID.randomUUID().toString(), nom, diff, miningReward, blocks);
        blockChain.getBlocks().add(blockService.blockMine(pendingTransactions, "0", blockChain.getDiff()));
        this.blockChain = blockChain;
        return blockChain;
    }

    @Override
    public void mineBlock(String addressMiner) {
        Block block = blockService.blockMine(pendingTransactions,
                getLastMinedBlock().getBlockHash(),
                blockChain.getDiff());
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                new Date(),
                "MineReward",
                addressMiner,
                blockChain.getMiningReward()
        );
        blockChain.getBlocks().add(block);
        //System.out.println(blockChain);
        //Here, we should clear the pending transactions, I guess ............
        pendingTransactions = new LinkedList<>();
        pendingTransactions.add(transaction);
    }

    @Override
    public Block getLastMinedBlock() {

        return blockChain.getBlocks().get(blockChain.getBlocks().size()- 1);
    }

    @Override
    public boolean blockChainIsValid() {
        boolean is_valid = true;
        int i=0;
        while (is_valid && i < blockChain.getBlocks().size()){
            Block blk = blockChain.getBlocks().get(i);
            is_valid = blk.getBlockHash().equals(blockService.hashBlock(blk))
                    && i==0 ? blk.getPrvBlockHash().equals("0")
                    : blk.getPrvBlockHash().equals(blockChain.getBlocks().get(i-1).getBlockHash());
            i++;
        }
        return is_valid;
    }

    @Override
    public double getBalance(String walletAddress) {
        double balance = 0.0;
        for(Block block : blockChain.getBlocks()){
            for(Transaction transaction: block.getTransactions()){
                if (transaction.getAddress_dst().equals(walletAddress)) balance+=transaction.getMontant();
                if (transaction.getAddress_src().equals(walletAddress)) balance-=transaction.getMontant();
            }
        }
        System.out.println(balance);
        return balance;
    }
}
