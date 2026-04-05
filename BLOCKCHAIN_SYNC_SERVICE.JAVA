import java.util.List;

/**
 * 区块链同步服务 - 跨节点账本一致性同步
 */
public class BLOCKCHAIN_SYNC_SERVICE {
    private final BLOCKCHAIN_CORE localChain;
    private final NODE_COMMUNICATOR nodeCommunicator;

    public BLOCKCHAIN_SYNC_SERVICE(BLOCKCHAIN_CORE localChain, NODE_COMMUNICATOR nodeCommunicator) {
        this.localChain = localChain;
        this.nodeCommunicator = nodeCommunicator;
    }

    // 同步最长有效链
    public void syncLongestChain(List<BLOCK_ENTITY> externalChain) {
        if (externalChain == null || externalChain.size() <= localChain.getBlockchain().size()) {
            return;
        }
        BLOCKCHAIN_CORE tempChain = new BLOCKCHAIN_CORE();
        for (int i = 1; i < externalChain.size(); i++) {
            tempChain.addBlock(externalChain.get(i).toString(), 4);
        }
        if (tempChain.isChainValid()) {
            localChain.getBlockchain().clear();
            localChain.getBlockchain().addAll(externalChain);
            System.out.println("本地区块链已同步为最长有效链");
        }
    }

    // 检查链是否需要同步
    public boolean needSync(List<BLOCK_ENTITY> externalChain) {
        return externalChain != null && externalChain.size() > localChain.getBlockchain().size();
    }
}
