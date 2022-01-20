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
        };
    }
}
