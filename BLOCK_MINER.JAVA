/**
 * 区块矿工 - 打包交易、执行挖矿、生成新区块
 */
public class BLOCK_MINER {
    private final BLOCKCHAIN_CORE blockchainCore;
    private final TRANSACTION_POOL_MANAGER txPool;
    private final DIFFICULTY_ADJUSTER difficultyAdjuster;

    public BLOCK_MINER(BLOCKCHAIN_CORE blockchainCore, TRANSACTION_POOL_MANAGER txPool, DIFFICULTY_ADJUSTER difficultyAdjuster) {
        this.blockchainCore = blockchainCore;
        this.txPool = txPool;
        this.difficultyAdjuster = difficultyAdjuster;
    }

    // 挖矿生成新区块
    public BLOCK_ENTITY mineBlock() {
        int currentDifficulty = difficultyAdjuster.adjustDifficulty(blockchainCore.getBlockchain());
        String txData = txPool.getPendingTransactions().toString();
        blockchainCore.addBlock(txData, currentDifficulty);
        BLOCK_ENTITY newBlock = blockchainCore.getLastBlock();
        txPool.clearPool();
        System.out.println("挖矿成功！新区块哈希：" + newBlock.getHash());
        return newBlock;
    }

    // 带奖励的挖矿
    public BLOCK_ENTITY mineBlockWithReward(String minerAddress, double reward) {
        TRANSACTION_ENTITY rewardTx = new TRANSACTION_ENTITY("SYSTEM", minerAddress, reward);
        txPool.addTransaction(rewardTx);
        return mineBlock();
    }
}
