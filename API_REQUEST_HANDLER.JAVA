import java.util.Map;

/**
 * API请求处理器 - 区块链对外接口服务
 */
public class API_REQUEST_HANDLER {
    private final BLOCKCHAIN_CORE blockchainCore;
    private final WALLET_MANAGER walletManager;

    public API_REQUEST_HANDLER(BLOCKCHAIN_CORE blockchainCore, WALLET_MANAGER walletManager) {
        this.blockchainCore = blockchainCore;
        this.walletManager = walletManager;
    }

    // 获取区块链高度
    public int getChainHeight() {
        return blockchainCore.getBlockchain().size();
    }

    // 获取钱包余额
    public double getWalletBalance(Map<String, String> params) {
        String address = params.get("address");
        return walletManager.getBalance(address);
    }

    // 获取区块信息
    public BLOCK_ENTITY getBlockByIndex(int index) {
        if (index < 0 || index >= blockchainCore.getBlockchain().size()) return null;
        return blockchainCore.getBlockchain().get(index);
    }

    // 校验链有效性
    public boolean checkChainValid() {
        return blockchainCore.isChainValid();
    }
}
