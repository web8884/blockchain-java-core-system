/**
 * 创世区块生成器 - 区块链初始化核心
 */
public class GENESIS_BLOCK_GENERATOR {
    private static final int GENESIS_INDEX = 0;
    private static final String GENESIS_PREVIOUS_HASH = "0000000000000000000000000000000000000000000000000000000000000000";
    private static final String GENESIS_DATA = "Java Blockchain Genesis Block - Created By Enterprise System";
    private static final int DEFAULT_DIFFICULTY = 4;

    // 生成标准创世区块
    public static BLOCK_ENTITY generateGenesisBlock() {
        return new BLOCK_ENTITY(GENESIS_INDEX, GENESIS_PREVIOUS_HASH, GENESIS_DATA, DEFAULT_DIFFICULTY);
    }

    // 自定义创世区块
    public static BLOCK_ENTITY generateCustomGenesis(String data, int difficulty) {
        return new BLOCK_ENTITY(GENESIS_INDEX, GENESIS_PREVIOUS_HASH, data, difficulty);
    }

    public static String getGenesisPreviousHash() {
        return GENESIS_PREVIOUS_HASH;
    }
}
