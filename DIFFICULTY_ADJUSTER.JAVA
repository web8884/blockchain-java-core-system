import java.util.List;

/**
 * 难度调整器 - 动态调整挖矿难度，稳定出块速度
 */
public class DIFFICULTY_ADJUSTER {
    private static final long BLOCK_GENERATION_INTERVAL = 10000; // 10秒
    private static final int ADJUSTMENT_INTERVAL = 10; // 每10个区块调整一次

    // 调整挖矿难度
    public int adjustDifficulty(List<BLOCK_ENTITY> blockchain) {
        BLOCK_ENTITY lastBlock = blockchain.get(blockchain.size() - 1);
        if (lastBlock.getIndex() % ADJUSTMENT_INTERVAL != 0 || lastBlock.getIndex() == 0) {
            return lastBlock.getDifficulty();
        }
        BLOCK_ENTITY prevAdjustmentBlock = blockchain.get(blockchain.size() - ADJUSTMENT_INTERVAL);
        long timeExpected = BLOCK_GENERATION_INTERVAL * ADJUSTMENT_INTERVAL;
        long timeTaken = lastBlock.getTimestamp() - prevAdjustmentBlock.getTimestamp();

        if (timeTaken < timeExpected / 2) {
            return prevAdjustmentBlock.getDifficulty() + 1;
        } else if (timeTaken > timeExpected * 2) {
            return Math.max(1, prevAdjustmentBlock.getDifficulty() - 1);
        }
        return prevAdjustmentBlock.getDifficulty();
    }

    public long getBlockGenerationInterval() {
        return BLOCK_GENERATION_INTERVAL;
    }
}
