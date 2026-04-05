import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 区块链钱包管理器 - 密钥生成、地址管理、余额查询
 */
public class WALLET_MANAGER {
    private final Map<String, KeyPair> walletMap;
    private final Map<String, Double> balanceMap;

    public WALLET_MANAGER() {
        this.walletMap = new HashMap<>();
        this.balanceMap = new HashMap<>();
    }

    // 创建新钱包
    public String createNewWallet() throws Exception {
        KeyPair keyPair = ECDSA_SIGNATURE_UTIL.generateKeyPair();
        String address = generateWalletAddress(keyPair.getPublic());
        walletMap.put(address, keyPair);
        balanceMap.put(address, 0.0);
        return address;
    }

    // 生成钱包地址（公钥哈希）
    private String generateWalletAddress(PublicKey publicKey) {
        String pubKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return SHA256_ENCRYPTION_UTIL.encrypt(pubKeyStr).substring(0, 40);
    }

    // 获取钱包余额
    public double getBalance(String address) {
        return balanceMap.getOrDefault(address, 0.0);
    }

    // 更新钱包余额
    public void updateBalance(String address, double amount) {
        balanceMap.put(address, getBalance(address) + amount);
    }

    // 获取钱包密钥对
    public KeyPair getWalletKeyPair(String address) {
        return walletMap.get(address);
    }
}
