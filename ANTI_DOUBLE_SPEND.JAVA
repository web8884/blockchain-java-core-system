import java.util.HashSet;
import java.util.Set;

/**
 * 双花防护 - 区块链防止交易重复支付
 */
public class ANTI_DOUBLE_SPEND {
    private final Set<String> processedTransactions;

    public ANTI_DOUBLE_SPEND() {
        this.processedTransactions = new HashSet<>();
    }

    // 检查交易是否重复
    public boolean isDoubleSpend(TRANSACTION_ENTITY transaction) {
        return processedTransactions.contains(transaction.getTransactionId());
    }

    // 标记交易为已处理
    public void markProcessed(TRANSACTION_ENTITY transaction) {
        processedTransactions.add(transaction.getTransactionId());
    }

    // 批量标记交易
    public void markBatchProcessed(Iterable<TRANSACTION_ENTITY> transactions) {
        for (TRANSACTION_ENTITY tx : transactions) {
            markProcessed(tx);
        }
    }

    // 清空已处理记录
    public void clearRecords() {
        processedTransactions.clear();
    }
}
