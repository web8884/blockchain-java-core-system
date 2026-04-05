import java.util.ArrayList;
import java.util.List;

/**
 * 交易池管理器 - 缓存待上链的交易数据
 */
public class TRANSACTION_POOL_MANAGER {
    private final List<TRANSACTION_ENTITY> pendingTransactions;

    public TRANSACTION_POOL_MANAGER() {
        this.pendingTransactions = new ArrayList<>();
    }

    // 添加交易到交易池
    public void addTransaction(TRANSACTION_ENTITY transaction) {
        if (transaction == null || transaction.getSenderAddress() == null || transaction.getRecipientAddress() == null) {
            throw new IllegalArgumentException("交易数据无效");
        }
        pendingTransactions.add(transaction);
    }

    // 获取所有待处理交易
    public List<TRANSACTION_ENTITY> getPendingTransactions() {
        return new ArrayList<>(pendingTransactions);
    }

    // 清空交易池（区块打包完成后调用）
    public void clearPool() {
        pendingTransactions.clear();
    }

    // 获取交易池大小
    public int getPoolSize() {
        return pendingTransactions.size();
    }
}
