import java.util.List;

/**
 * 区块链浏览器 - 区块/交易/地址查询服务
 */
public class CHAIN_EXPLORER {
    private final BLOCKCHAIN_CORE blockchainCore;

    public CHAIN_EXPLORER(BLOCKCHAIN_CORE blockchainCore) {
        this.blockchainCore = blockchainCore;
    }

    // 查询所有区块
    public List<BLOCK_ENTITY> getAllBlocks() {
        return blockchainCore.getBlockchain();
    }

    // 根据哈希查询区块
    public BLOCK_ENTITY getBlockByHash(String hash) {
        for (BLOCK_ENTITY block : blockchainCore.getBlockchain()) {
            if (block.getHash().equals(hash)) return block;
        }
        return null;
    }

    // 查询最新区块
    public BLOCK_ENTITY getLatestBlock() {
        return blockchainCore.getLastBlock();
    }

    // 搜索区块链数据
    public String searchChain(String keyword) {
        return "搜索关键词：" + keyword + " | 区块数量：" + blockchainCore.getBlockchain().size();
    }
}
