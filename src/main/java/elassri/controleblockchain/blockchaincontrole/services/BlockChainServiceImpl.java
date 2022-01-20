package elassri.controleblockchain.blockchaincontrole.services;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.entities.BlockChain;
import elassri.controleblockchain.blockchaincontrole.entities.Transaction;
import elassri.controleblockchain.blockchaincontrole.repositories.BlockChainRepository;
import elassri.controleblockchain.blockchaincontrole.repositories.BlockRepository;
import elassri.controleblockchain.blockchaincontrole.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BlockChainServiceImpl implements BlockChainService {

    private List<Transaction> pendingTransactions;
    private BlockService blockService;
    private BlockChain blockChain;

    private TransactionRepository transactionRepository;
    private BlockRepository blockRepository;
    private BlockChainRepository blockChainRepository;



    public BlockChainServiceImpl(BlockService blockService, TransactionRepository transactionRepository,
                                 BlockRepository blockRepository, BlockChainRepository blockChainRepository)

    {
        this.blockService = blockService;
        this.transactionRepository = transactionRepository;
        this.blockRepository = blockRepository;
        this.blockChainRepository = blockChainRepository;
        this.pendingTransactions = new ArrayList<>();

    }

    @Override
    public BlockChain createBlockChain(String nom, int diff, double miningReward) {
        List<Block> blocks = new ArrayList<>();
        BlockChain blockChain = new BlockChain(UUID.randomUUID().toString(), nom, diff, miningReward, blocks);
        Block genesisBlock = blockService.blockMine(pendingTransactions, "0", blockChain.getDiff());
        blockChain.getBlocks().add(genesisBlock);


        blockRepository.save(genesisBlock);
        blockChainRepository.save(blockChain);
        this.blockChain = blockChain;
        return blockChain;
    }


    @Override
    public Block mineBlock(String addressMiner){
        Block block = blockService.blockMine(pendingTransactions,
                this.getLastMinedBlock().getBlockHash(),
                this.blockChain.getDiff());
        log.info("Block is mined ==> Nonce is "+block.getNonce());
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                new Date(),
                "MineReward",
                addressMiner,
                blockChain.getMiningReward()
            );
        blockRepository.save(block);
        blockChain.getBlocks().add(block);

        blockChainRepository.save(blockChain);

        transactionRepository.save(transaction);

        pendingTransactions.add(transaction);

        return block;
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
        return balance;
    }

    @Override
    public void addTransaction(String srcWallet, String distWallet, double amount) {
        double srcBalance = this.getBalance(srcWallet);
        if(srcBalance > amount){
            Transaction transaction = new Transaction(
                    UUID.randomUUID().toString(),
                    new Date(),
                    srcWallet,
                    distWallet,
                    amount
            );

            transactionRepository.save(transaction);
            this.pendingTransactions.add(transaction);

        }
        else throw new RuntimeException("your balance is not sufficient");
    }

    @Override
    public BlockChain getBlockChain() {
        return blockChainRepository.findAll().get(0);
    }
}
