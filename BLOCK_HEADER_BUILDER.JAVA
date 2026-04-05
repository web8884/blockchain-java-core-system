/**
 * 区块头构建器 - 标准化区块链区块头结构
 */
public class BLOCK_HEADER_BUILDER {
    private int index;
    private String previousHash;
    private String merkleRoot;
    private long timestamp;
    private int difficulty;
    private int nonce;

    public BLOCK_HEADER_BUILDER setIndex(int index) {
        this.index = index;
        return this;
    }

    public BLOCK_HEADER_BUILDER setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
        return this;
    }

    public BLOCK_HEADER_BUILDER setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
        return this;
    }

    public BLOCK_HEADER_BUILDER setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public BLOCK_HEADER_BUILDER setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public BLOCK_HEADER_BUILDER setNonce(int nonce) {
        this.nonce = nonce;
        return this;
    }

    // 构建区块头字符串
    public String buildHeader() {
        return index + "|" + previousHash + "|" + merkleRoot + "|" + timestamp + "|" + difficulty + "|" + nonce;
    }

    // 构建区块头哈希
    public String buildHeaderHash() {
        return SHA256_ENCRYPTION_UTIL.encrypt(buildHeader());
    }
}
