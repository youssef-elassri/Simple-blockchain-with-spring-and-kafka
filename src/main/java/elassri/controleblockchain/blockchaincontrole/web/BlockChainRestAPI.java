package elassri.controleblockchain.blockchaincontrole.web;

import elassri.controleblockchain.blockchaincontrole.dto.BlockChainRequestDTO;
import elassri.controleblockchain.blockchaincontrole.dto.TransactionRequestDTO;
import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.entities.BlockChain;
import elassri.controleblockchain.blockchaincontrole.entities.Transaction;
import elassri.controleblockchain.blockchaincontrole.services.BlockChainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class BlockChainRestAPI {
    private BlockChainService blockChainService;

    public BlockChainRestAPI(BlockChainService blockChainService) {
        this.blockChainService = blockChainService;
    }

    @PostMapping("/createBlockChain")
    public String createBlockChain(@RequestBody BlockChainRequestDTO requestDTO){
        BlockChain blockChain = blockChainService.createBlockChain(
                requestDTO.getNom(),
                requestDTO.getDiff(),
                requestDTO.getMiningReward()
        );

        return blockChain.getId();
    }

    @PostMapping("/createTransaction")
    public String transaction(@RequestBody TransactionRequestDTO requestDTO){
        blockChainService.addTransaction(
                requestDTO.getAddress_src() ,
                requestDTO.getAddress_dst(),
                requestDTO.getMontant()
        );

        return "success !!";
    }

    @GetMapping("/mine/{wallet}")
    public String mine(@PathVariable String wallet){
        Block blk = blockChainService.mineBlock(wallet);

        return "Nonce = "+blk.getNonce();
    }

    @GetMapping("/blockchain")
    public BlockChain getBlockChain(){
        return blockChainService.getBlockChain();
    }

    @GetMapping(path = "/lastMinedBlock")
    public Block getLastMinedBlock(){

        return blockChainService.getLastMinedBlock();
    }

    @GetMapping(path = "/balance/{wallet}")
    public double getBalance(@PathVariable String wallet){

        return blockChainService.getBalance(wallet);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> response =
                new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
