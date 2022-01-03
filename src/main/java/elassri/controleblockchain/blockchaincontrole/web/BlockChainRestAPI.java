package elassri.controleblockchain.blockchaincontrole.web;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.services.BlockChainService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class BlockChainRestAPI {
    private BlockChainService blockChainService;

    public BlockChainRestAPI(BlockChainService blockChainService) {
        this.blockChainService = blockChainService;
    }

    @GetMapping(path = "/lastMinedBlock")
    public Block getBlockChain(){

        return blockChainService.getLastMinedBlock();
    }

    @GetMapping(path = "/balance/{wallet}")
    public double getBalance(@PathVariable String wallet){

        return blockChainService.getBalance(wallet);
    }
}
