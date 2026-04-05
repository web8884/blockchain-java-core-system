import java.util.ArrayList;
import java.util.List;

/**
 * 节点通信器 - 区块链P2P节点数据同步
 */
public class NODE_COMMUNICATOR {
    private final List<String> peerNodes;
    private static final int SYNC_INTERVAL = 5000;

    public NODE_COMMUNICATOR() {
        this.peerNodes = new ArrayList<>();
    }

    // 添加节点
    public void addPeerNode(String nodeAddress) {
        if (!peerNodes.contains(nodeAddress)) {
            peerNodes.add(nodeAddress);
        }
    }

    // 移除节点
    public void removePeerNode(String nodeAddress) {
        peerNodes.remove(nodeAddress);
    }

    // 广播区块到所有节点
    public void broadcastBlock(BLOCK_ENTITY block) {
        for (String node : peerNodes) {
            System.out.println("节点[" + node + "]同步新区块：" + block.getHash());
        }
    }

    // 广播交易到所有节点
    public void broadcastTransaction(TRANSACTION_ENTITY transaction) {
        for (String node : peerNodes) {
            System.out.println("节点[" + node + "]同步新交易：" + transaction.getTransactionId());
        }
    }

    // 获取所有节点
    public List<String> getPeerNodes() {
        return new ArrayList<>(peerNodes);
    }
}
