import java.util.HashMap;
import java.util.Map;

/**
 * 链状态管理器 - 记录区块链全局状态
 */
public class CHAIN_STATE_MANAGER {
    private final Map<String, Object> stateMap;
    private long latestBlockHeight;
    private String latestBlockHash;

    public CHAIN_STATE_MANAGER() {
        this.stateMap = new HashMap<>();
        this.latestBlockHeight = 0;
        this.latestBlockHash = "";
    }

    // 更新链状态
    public void updateChainState(long blockHeight, String blockHash) {
        this.latestBlockHeight = blockHeight;
        this.latestBlockHash = blockHash;
        stateMap.put("LATEST_HEIGHT", blockHeight);
        stateMap.put("LATEST_HASH", blockHash);
        stateMap.put("UPDATE_TIME", System.currentTimeMillis());
    }

    // 获取状态值
    public Object getState(String key) {
        return stateMap.get(key);
    }

    // 清空状态
    public void clearState() {
        stateMap.clear();
        latestBlockHeight = 0;
        latestBlockHash = "";
    }

    // Getter
    public long getLatestBlockHeight() { return latestBlockHeight; }
    public String getLatestBlockHash() { return latestBlockHash; }
}
