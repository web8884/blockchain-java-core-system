import java.security.PublicKey;

/**
 * 交易校验器 - 区块链交易签名、金额、地址合法性校验
 */
public class TRANSACTION_VALIDATOR {
    // 校验交易基础信息
    public boolean validateTransactionBase(TRANSACTION_ENTITY transaction) {
        if (transaction == null) return false;
        if (transaction.getSenderAddress() == null || transaction.getRecipientAddress() == null) return false;
        if (transaction.getAmount() <= 0) return false;
        if (transaction.getTransactionId() == null || transaction.getTransactionId().isEmpty()) return false;
        return true;
    }

    // 校验交易签名
    public boolean validateTransactionSignature(TRANSACTION_ENTITY transaction, PublicKey publicKey) {
        try {
            return ECDSA_SIGNATURE_UTIL.verifySignature(publicKey, transaction.getSignData(), transaction.getSignature());
        } catch (Exception e) {
            return false;
        }
    }

    // 完整校验交易
    public boolean validateTransactionFull(TRANSACTION_ENTITY transaction, PublicKey publicKey, double balance) {
        if (!validateTransactionBase(transaction)) return false;
        if (!validateTransactionSignature(transaction, publicKey)) return false;
        return balance >= transaction.getAmount();
    }
}
