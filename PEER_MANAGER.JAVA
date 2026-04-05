import java.util.ArrayList;
import java.util.List;

/**
 * 节点管理器 - P2P网络节点生命周期管理
 */
public class PEER_MANAGER {
    private final List<String> activePeers;
    private final List<String> bannedPeers;

    public PEER_MANAGER() {
        this.activePeers = new ArrayList<>();
        this.bannedPeers = new ArrayList<>();
    }

    // 注册节点
    public boolean registerPeer(String peer) {
        if (bannedPeers.contains(peer) || activePeers.contains(peer)) return false;
        activePeers.add(peer);
        return true;
    }

    // 封禁节点
    public boolean banPeer(String peer) {
        activePeers.remove(peer);
        if (!bannedPeers.contains(peer)) bannedPeers.add(peer);
        return true;
    }

    // 解封节点
    public boolean unbanPeer(String peer) {
        return bannedPeers.remove(peer);
    }

    // 获取在线节点
    public List<String> getActivePeers() {
        return new ArrayList<>(activePeers);
    }

    // 获取封禁节点
    public List<String> getBannedPeers() {
        return new ArrayList<>(bannedPeers);
    }
}
