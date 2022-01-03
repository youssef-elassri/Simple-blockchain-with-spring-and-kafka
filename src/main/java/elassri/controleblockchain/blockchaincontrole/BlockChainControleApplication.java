package elassri.controleblockchain.blockchaincontrole;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.entities.BlockChain;
import elassri.controleblockchain.blockchaincontrole.entities.Transaction;
import elassri.controleblockchain.blockchaincontrole.repositories.BlockChainRepository;
import elassri.controleblockchain.blockchaincontrole.repositories.BlockRepository;
import elassri.controleblockchain.blockchaincontrole.repositories.TransactionRepository;
import elassri.controleblockchain.blockchaincontrole.services.BlockChainService;
import elassri.controleblockchain.blockchaincontrole.services.BlockChainServiceImpl;
import elassri.controleblockchain.blockchaincontrole.services.BlockService;
import elassri.controleblockchain.blockchaincontrole.services.BlockServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;


@SpringBootApplication
public class BlockChainControleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockChainControleApplication.class, args);
    }

    @Bean
    CommandLineRunner lineRunner(TransactionRepository transactionRepository, BlockRepository blockRepository
            , BlockChainRepository blockChainRepository, BlockChainService blockChainService){
        return args -> {
            String wallet1="wallet1", wallet2="wallet2", wallet3="wallet3";

            BlockChain blockChain = blockChainService.createBlockChain("YSF", 4, 12.5);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);
            blockChainService.mineBlock(wallet1);


//            blockChainService.getBalance(wallet1);
//            blockChain.getBlocks().forEach(block -> {
//                transactionRepository.saveAll(block.getTransactions());
//            });



//                Transaction tr1 = new Transaction(UUID.randomUUID().toString(), Date.from(Instant.now()), wallet2, wallet1, 1000.0);
//                Transaction tr2 = new Transaction(UUID.randomUUID().toString(), Date.from(Instant.now()), wallet1, wallet2, 50.0);
//                Transaction tr3 = new Transaction(UUID.randomUUID().toString(), Date.from(Instant.now()), wallet1, wallet3, 50.0);
//                Transaction tr4 = new Transaction(UUID.randomUUID().toString(), Date.from(Instant.now()), wallet3, wallet1, 13.0);
//
//                List<Transaction> lstTransaction = new ArrayList<>();
//                lstTransaction.add(tr1);
//                lstTransaction.add(tr2);
//                lstTransaction.add(tr3);
//                lstTransaction.add(tr4);







        };
    }
}
