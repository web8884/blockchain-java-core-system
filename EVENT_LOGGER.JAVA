import java.util.ArrayList;
import java.util.List;

/**
 * 事件日志器 - 区块链操作事件记录与追溯
 */
public class EVENT_LOGGER {
    private final List<String> eventLogs;
    private static final String LOG_FORMAT = "[%s] %s | %s";

    public EVENT_LOGGER() {
        this.eventLogs = new ArrayList<>();
    }

    // 记录普通事件
    public void logEvent(String module, String content) {
        String log = String.format(LOG_FORMAT, System.currentTimeMillis(), module, content);
        eventLogs.add(log);
        System.out.println(log);
    }

    // 记录错误事件
    public void logError(String module, String error) {
        String log = String.format(LOG_FORMAT + " [ERROR]", System.currentTimeMillis(), module, error);
        eventLogs.add(log);
        System.err.println(log);
    }

    // 获取所有日志
    public List<String> getEventLogs() {
        return new ArrayList<>(eventLogs);
    }

    // 清空日志
    public void clearLogs() {
        eventLogs.clear();
    }
}
