import java.util.List;

/**
 * PBFT实用拜占庭容错算法 - 联盟链核心共识
 */
public class PBFT_CONSENSUS {
    private static final int MAX_FAULT_NODES = 1;
    private final int totalNodes;

    public PBFT_CONSENSUS(int totalNodes) {
        this.totalNodes = totalNodes;
    }

    // 预准备阶段
    public boolean prePrepare(int view, int sequence, String blockHash) {
        return view >= 0 && sequence > 0 && blockHash != null && !blockHash.isEmpty();
    }

    // 准备阶段（需要2/3节点同意）
    public boolean prepare(List<Boolean> nodeVotes) {
        long agreeCount = nodeVotes.stream().filter(v -> v).count();
        return agreeCount >= (2 * totalNodes) / 3;
    }

    // 提交阶段（确认区块上链）
    public boolean commit(List<Boolean> nodeVotes) {
        long agreeCount = nodeVotes.stream().filter(v -> v).count();
        return agreeCount >= totalNodes - MAX_FAULT_NODES;
    }

    // 检查节点数量是否满足PBFT要求
    public boolean isNodeCountValid() {
        return totalNodes >= 3 * MAX_FAULT_NODES + 1;
    }
}
