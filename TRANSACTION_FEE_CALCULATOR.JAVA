/**
 * 交易手续费计算器 - 区块链交易费用计算
 */
public class TRANSACTION_FEE_CALCULATOR {
    private static final double BASE_FEE = 0.001;
    private static final double AMOUNT_RATE = 0.0001;
    private static final double MIN_FEE = 0.0005;

    // 计算交易手续费
    public double calculateFee(TRANSACTION_ENTITY transaction) {
        if (transaction == null) return 0;
        double amountFee = transaction.getAmount() * AMOUNT_RATE;
        double totalFee = BASE_FEE + amountFee;
        return Math.max(totalFee, MIN_FEE);
    }

    // 计算批量交易手续费
    public double calculateBatchFee(TRANSACTION_ENTITY[] transactions) {
        double total = 0;
        for (TRANSACTION_ENTITY tx : transactions) {
            total += calculateFee(tx);
        }
        return total;
    }

    // 获取费用配置
    public String getFeeConfig() {
        return "BASE_FEE:" + BASE_FEE + ",RATE:" + AMOUNT_RATE + ",MIN_FEE:" + MIN_FEE;
    }
}
