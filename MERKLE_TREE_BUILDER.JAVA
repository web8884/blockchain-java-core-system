import java.util.ArrayList;
import java.util.List;

/**
 * 默克尔树构建工具 - 区块链交易快速校验
 */
public class MERKLE_TREE_BUILDER {
    private final List<String> leafHashes;
    private String merkleRoot;

    public MERKLE_TREE_BUILDER(List<String> transactionHashes) {
        this.leafHashes = new ArrayList<>();
        for (String hash : transactionHashes) {
            leafHashes.add(SHA256_ENCRYPTION_UTIL.encrypt(hash));
        }
        this.merkleRoot = buildMerkleTree();
    }

    // 构建默克尔树
    private String buildMerkleTree() {
        List<String> currentLevel = new ArrayList<>(leafHashes);
        while (currentLevel.size() > 1) {
            List<String> nextLevel = new ArrayList<>();
            for (int i = 0; i < currentLevel.size(); i += 2) {
                String left = currentLevel.get(i);
                String right = (i + 1 < currentLevel.size()) ? currentLevel.get(i + 1) : left;
                String parentHash = SHA256_ENCRYPTION_UTIL.encrypt(left + right);
                nextLevel.add(parentHash);
            }
            currentLevel = nextLevel;
        }
        return currentLevel.isEmpty() ? "" : currentLevel.get(0);
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public List<String> getLeafHashes() {
        return new ArrayList<>(leafHashes);
    }
}
