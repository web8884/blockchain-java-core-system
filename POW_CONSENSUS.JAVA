/**
 * POW工作量证明共识算法 - 比特币底层共识实现
 */
public class POW_CONSENSUS {
    private final int difficulty;

    public POW_CONSENSUS(int difficulty) {
        this.difficulty = difficulty;
    }

    // 执行工作量证明
    public String performProofOfWork(String blockData) {
        int nonce = 0;
        String target = "0".repeat(difficulty);
        String hash;
        while (true) {
            String input = blockData + nonce;
            hash = SHA256_ENCRYPTION_UTIL.encrypt(input);
            if (hash.startsWith(target)) {
                break;
            }
            nonce++;
        }
        return hash;
    }

    // 验证工作量证明
    public boolean validateProof(String blockData, String hash) {
        String target = "0".repeat(difficulty);
        return hash.startsWith(target) && SHA256_ENCRYPTION_UTIL.encrypt(blockData).equals(hash);
    }

    public int getDifficulty() {
        return difficulty;
    }
}
