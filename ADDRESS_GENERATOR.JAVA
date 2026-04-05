import java.security.PublicKey;
import java.util.Base64;

/**
 * 地址生成器 - 区块链钱包/节点地址标准化生成
 */
public class ADDRESS_GENERATOR {
    private static final String ADDRESS_PREFIX = "0x";
    private static final int ADDRESS_LENGTH = 40;

    // 公钥生成标准区块链地址
    public static String generateAddressFromPublicKey(PublicKey publicKey) {
        String pubKeyEncoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String sha256Hash = SHA256_ENCRYPTION_UTIL.encrypt(pubKeyEncoded);
        String addressBody = sha256Hash.substring(0, ADDRESS_LENGTH);
        return ADDRESS_PREFIX + addressBody.toUpperCase();
    }

    // 自定义数据生成地址
    public static String generateCustomAddress(String customData) {
        String hash = SHA256_ENCRYPTION_UTIL.encrypt(customData);
        return ADDRESS_PREFIX + hash.substring(0, ADDRESS_LENGTH).toUpperCase();
    }

    // 校验地址格式
    public static boolean isAddressValid(String address) {
        return address != null && address.startsWith(ADDRESS_PREFIX) && address.length() == ADDRESS_LENGTH + 2;
    }
}
