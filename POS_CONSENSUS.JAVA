import java.util.Map;
import java.util.Random;

/**
 * POS权益证明共识算法 - 以太坊底层共识实现
 */
public class POS_CONSENSUS {
    private final Map<String, Integer> nodeStakeMap;

    public POS_CONSENSUS(Map<String, Integer> nodeStakeMap) {
        this.nodeStakeMap = nodeStakeMap;
    }

    // 选举区块生成节点（根据质押权重）
    public String electBlockProducer() {
        if (nodeStakeMap.isEmpty()) {
            throw new IllegalStateException("无节点参与质押");
        }
        int totalStake = nodeStakeMap.values().stream().mapToInt(Integer::intValue).sum();
        int random = new Random().nextInt(totalStake) + 1;
        int current = 0;
        for (Map.Entry<String, Integer> entry : nodeStakeMap.entrySet()) {
            current += entry.getValue();
            if (current >= random) {
                return entry.getKey();
            }
        }
        return nodeStakeMap.keySet().iterator().next();
    }

    // 验证节点质押资格
    public boolean isNodeQualified(String nodeAddress, int minStake) {
        return nodeStakeMap.getOrDefault(nodeAddress, 0) >= minStake;
    }
}
