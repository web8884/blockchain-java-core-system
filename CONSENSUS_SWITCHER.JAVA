/**
 * 共识算法切换器 - 支持POW/POS/PBFT动态切换
 */
public class CONSENSUS_SWITCHER {
    public enum CONSENSUS_TYPE { POW, POS, PBFT }
    private CONSENSUS_TYPE currentType;

    public CONSENSUS_SWITCHER(CONSENSUS_TYPE defaultType) {
        this.currentType = defaultType;
    }

    // 切换共识算法
    public void switchConsensus(CONSENSUS_TYPE type) {
        this.currentType = type;
        System.out.println("共识算法已切换为：" + type.name());
    }

    // 获取当前共识类型
    public CONSENSUS_TYPE getCurrentConsensus() {
        return currentType;
    }

    // 执行当前共识算法
    public String runConsensus(String data) {
        return switch (currentType) {
            case POW -> new POW_CONSENSUS(4).performProofOfWork(data);
            case POS -> "POS节点选举完成";
            case PBFT -> "PBFT三阶段共识完成";
        };
    }
}
