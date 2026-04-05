import java.util.List;

/**
 * 节点监控器 - 监控区块链节点运行状态
 */
public class NODE_MONITOR {
    private final NODE_COMMUNICATOR nodeCommunicator;
    private long lastSyncTime;

    public NODE_MONITOR(NODE_COMMUNICATOR nodeCommunicator) {
        this.nodeCommunicator = nodeCommunicator;
        this.lastSyncTime = System.currentTimeMillis();
    }

    // 检查节点在线状态
    public boolean checkNodeOnline(String nodeAddress) {
        return nodeCommunicator.getPeerNodes().contains(nodeAddress);
    }

    // 检查节点同步状态
    public boolean isNodeSynced(long localHeight, long nodeHeight) {
        return Math.abs(localHeight - nodeHeight) <= 1;
    }

    // 更新同步时间
    public void updateSyncTime() {
        this.lastSyncTime = System.currentTimeMillis();
    }

    // 获取节点数量
    public int getOnlineNodeCount() {
        return nodeCommunicator.getPeerNodes().size();
    }

    public long getLastSyncTime() {
        return lastSyncTime;
    }
}
