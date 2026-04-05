import java.security.PrivateKey;

/**
 * 交易签名器 - 对区块链交易进行数字签名
 */
public class TRANSACTION_SIGNER {
    // 签名交易
    public static boolean signTransaction(TRANSACTION_ENTITY transaction, PrivateKey privateKey) {
        try {
            if (transaction == null || privateKey == null) {
                return false;
            }
            String signData = transaction.getSignData();
            String signature = ECDSA_SIGNATURE_UTIL.signData(privateKey, signData);
            transaction.setSignature(signature);
            return true;
        } catch (Exception e) {
            System.err.println("交易签名失败：" + e.getMessage());
            return false;
        }
    }

    // 批量签名交易
    public static int signTransactionBatch(TRANSACTION_ENTITY[] transactions, PrivateKey privateKey) {
        int successCount = 0;
        for (TRANSACTION_ENTITY tx : transactions) {
            if (signTransaction(tx, privateKey)) {
                successCount++;
            }
        }
        return successCount;
    }
}
