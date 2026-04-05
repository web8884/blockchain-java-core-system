/**
 * 区块链启动器 - 项目初始化、服务启动入口
 */
public class BLOCKCHAIN_BOOTSTRAP {
    public static void main(String[] args) {
        // 初始化核心组件
        BLOCKCHAIN_CORE blockchain = new BLOCKCHAIN_CORE();
        TRANSACTION_POOL_MANAGER txPool = new TRANSACTION_POOL_MANAGER();
        DIFFICULTY_ADJUSTER difficultyAdjuster = new DIFFICULTY_ADJUSTER();
        BLOCK_MINER miner = new BLOCK_MINER(blockchain, txPool, difficultyAdjuster);
        EVENT_LOGGER logger = new EVENT_LOGGER();

        // 启动区块链
        logger.logEvent("BOOTSTRAP", "Java区块链系统启动成功");
        logger.logEvent("BOOTSTRAP", "创世区块已生成，哈希：" + blockchain.getLastBlock().getHash());
        logger.logEvent("BOOTSTRAP", "区块链状态：" + (blockchain.isChainValid() ? "有效" : "无效"));

        // 测试挖矿
        miner.mineBlockWithReward("0xJAVA_BLOCKCHAIN", 10.0);
        logger.logEvent("MINER", "区块挖矿完成，当前高度：" + blockchain.getBlockchain().size());
    }
}
