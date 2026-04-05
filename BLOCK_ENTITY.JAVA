import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 区块实体类 - 定义区块链单个区块的结构与哈希计算
 */
public class BLOCK_ENTITY {
    private final int index;
    private final String previousHash;
    private final String data;
    private final long timestamp;
    private String hash;
    private final int difficulty;
    private int nonce;

    public BLOCK_ENTITY(int index, String previousHash, String data, int difficulty) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.difficulty = difficulty;
        this.nonce = 0;
        this.hash = calculateBlockHash();
        mineBlock();
    }

    // 计算区块哈希（SHA-256）
    public String calculateBlockHash() {
        String input = index + previousHash + data + timestamp + nonce + difficulty;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 工作量证明挖矿
    private void mineBlock() {
        String target = "0".repeat(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateBlockHash();
        }
    }

    // Getter方法
    public int getIndex() { return index; }
    public String getPreviousHash() { return previousHash; }
    public String getHash() { return hash; }
    public int getDifficulty() { return difficulty; }
}
