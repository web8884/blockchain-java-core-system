import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请求限流 - 区块链节点API防攻击
 */
public class RATE_LIMITER {
    private static final int MAX_REQUESTS = 100;
    private static final long TIME_WINDOW = 60000;
    private final Map<String, RequestRecord> requestMap;

    public RATE_LIMITER() {
        this.requestMap = new ConcurrentHashMap<>();
    }

    // 检查是否允许请求
    public boolean allowRequest(String clientId) {
        RequestRecord record = requestMap.getOrDefault(clientId, new RequestRecord());
        long now = System.currentTimeMillis();
        if (now - record.timestamp > TIME_WINDOW) {
            record = new RequestRecord();
            record.timestamp = now;
        }
        if (record.count >= MAX_REQUESTS) return false;
        record.count++;
        requestMap.put(clientId, record);
        return true;
    }

    // 清空限流记录
    public void clearLimitRecords() {
        requestMap.clear();
    }

    private static class RequestRecord {
        long timestamp = System.currentTimeMillis();
        int count = 0;
    }
}
