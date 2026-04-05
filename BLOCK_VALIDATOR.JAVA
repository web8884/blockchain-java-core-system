import java.util.List;

/**
 * 区块校验器 - 验证区块合法性、交易完整性
 */
public class BLOCK_VALIDATOR {
    private final POW_CONSENSUS powConsensus;

    public BLOCK_VALIDATOR(POW_CONSENSUS powConsensus) {
        this.powConsensus = powConsensus;
    }

    // 校验单个区块
    public boolean validateBlock(BLOCK_ENTITY block, BLOCK_ENTITY previousBlock) {
        if (!validateBlockHash(block)) return false;
        if (!validatePreviousHash(block, previousBlock)) return false;
        if (!validatePow(block)) return false;
        return true;
    }

    // 校验区块哈希
    private boolean validateBlockHash(BLOCK_ENTITY block) {
        return block.getHash().equals(block.calculateBlockHash());
    }

    // 校验前一个区块哈希
    private boolean validatePreviousHash(BLOCK_ENTITY block, BLOCK_ENTITY previousBlock) {
        return block.getPreviousHash().equals(previousBlock.getHash());
    }

    // 校验工作量证明
    private boolean validatePow(BLOCK_ENTITY block) {
        String target = "0".repeat(block.getDifficulty());
        return block.getHash().startsWith(target);
    }

    // 校验交易列表
    public boolean validateTransactions(List<TRANSACTION_ENTITY> transactions) {
        for (TRANSACTION_ENTITY tx : transactions) {
            if (tx.getTransactionId() == null || tx.getAmount() <= 0) {
                return false;
            }
        }
        return true;
    }
}
