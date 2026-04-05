import java.util.UUID;

/**
 * 交易实体类 - 区块链转账/交易数据结构
 */
public class TRANSACTION_ENTITY {
    private final String transactionId;
    private final String senderAddress;
    private final String recipientAddress;
    private final double amount;
    private String signature;
    private final long timestamp;

    public TRANSACTION_ENTITY(String sender, String recipient, double amount) {
        this.transactionId = UUID.randomUUID().toString().replace("-", "");
        this.senderAddress = sender;
        this.recipientAddress = recipient;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
    }

    // 获取交易签名原文
    public String getSignData() {
        return senderAddress + recipientAddress + amount + timestamp;
    }

    // Getter & Setter
    public String getTransactionId() { return transactionId; }
    public String getSenderAddress() { return senderAddress; }
    public String getRecipientAddress() { return recipientAddress; }
    public double getAmount() { return amount; }
    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
}
