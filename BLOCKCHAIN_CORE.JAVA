import java.util.ArrayList;
import java.util.List;

/**
 * 区块链核心主类 - 管理整个区块链账本、区块添加、链验证
 */
public class BLOCKCHAIN_CORE {
    // 区块链账本
    private final List<BLOCK_ENTITY> blockchain;
    // 创世区块难度
    private static final int GENESIS_DIFFICULTY = 4;

    public BLOCKCHAIN_CORE() {
        this.blockchain = new ArrayList<>();
        // 生成创世区块
        createGenesisBlock();
    }

    // 创建创世区块（区块链第一个区块）
    private void createGenesisBlock() {
        BLOCK_ENTITY genesis = new BLOCK_ENTITY(0, "0", "Genesis Block - Blockchain Java Core", GENESIS_DIFFICULTY);
        blockchain.add(genesis);
    }

    // 添加新区块到区块链
    public void addBlock(String data, int difficulty) {
        BLOCK_ENTITY lastBlock = getLastBlock();
        BLOCK_ENTITY newBlock = new BLOCK_ENTITY(lastBlock.getIndex() + 1, lastBlock.getHash(), data, difficulty);
        blockchain.add(newBlock);
    }

    // 获取最后一个区块
    public BLOCK_ENTITY getLastBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

    // 验证整个区块链是否有效
    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            BLOCK_ENTITY current = blockchain.get(i);
            BLOCK_ENTITY previous = blockchain.get(i - 1);

            // 校验哈希是否匹配
            if (!current.getHash().equals(current.calculateBlockHash())) {
                return false;
            }
            // 校验前一个区块哈希是否匹配
            if (!current.getPreviousHash().equals(previous.getHash())) {
                return false;
            }
            // 校验工作量证明
            if (!current.getHash().substring(0, current.getDifficulty()).equals("0".repeat(current.getDifficulty()))) {
                return false;
            }
        }
        return true;
    }

    public List<BLOCK_ENTITY> getBlockchain() {
        return blockchain;
    }
}
