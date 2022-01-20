package elassri.controleblockchain.blockchaincontrole.services;

import elassri.controleblockchain.blockchaincontrole.entities.Block;
import elassri.controleblockchain.blockchaincontrole.entities.Transaction;
import org.springframework.stereotype.Service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BlockServiceImpl implements BlockService {
    @Override
    public Block createBlock(List<Transaction> transactions, String prv_hash) {
        Block block = new Block(UUID.randomUUID().toString(), Date.from(Instant.now()),null, prv_hash, transactions, 0);
        block.setBlockHash(hashBlock(block));
        return block;
    }

    @Override
    public String hashBlock(Block block) {
        String tmp = block.getTransactions() != null ? "00":block.getTransactions().toString();
        String hashData = block.getPrvBlockHash() + tmp + block.getNonce();

        MessageDigest messageDigest = null;
        byte[] hashBytes = null;
        try{
            messageDigest = MessageDigest.getInstance("SHA-256");
            hashBytes = messageDigest.digest(hashData.getBytes());

        }catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
        }

        StringBuffer hashcode = new StringBuffer();

        for (byte b : hashBytes) {
            hashcode.append(String.format("%02x", b));
        }
        return hashcode.toString();
    }

    @Override
    public Block blockMine(List<Transaction> transactions, String prv_hash, int diff) {
        Block block = createBlock(transactions, prv_hash);
        String diffZeros = new String(new char[diff]).replace('\0', '0');
        String hash = hashBlock(block);
        while (!hash.startsWith(diffZeros)){
            block.setNonce(block.getNonce()+1);
            hash = hashBlock(block);
        }
        block.setBlockHash(hash);
        return block;

    }
}
