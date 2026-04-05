import java.util.HashMap;
import java.util.Map;

/**
 * 通证合约 - 区块链自定义代币发行（ERC20标准简化版）
 */
public class TOKEN_CONTRACT extends SMART_CONTRACT_BASE {
    private final String tokenName;
    private final String tokenSymbol;
    private final long totalSupply;
    private final Map<String, Long> balanceMap;

    public TOKEN_CONTRACT(String tokenName, String tokenSymbol, long totalSupply) {
        super();
        this.tokenName = tokenName;
        this.tokenSymbol = tokenSymbol;
        this.totalSupply = totalSupply;
        this.balanceMap = new HashMap<>();
        this.balanceMap.put("OWNER", totalSupply);
    }

    // 代币转账
    public boolean transfer(String from, String to, long amount) {
        if (!isActive || balanceMap.getOrDefault(from, 0L) < amount || amount <= 0) {
            return false;
        }
        balanceMap.put(from, balanceMap.get(from) - amount);
        balanceMap.put(to, balanceMap.getOrDefault(to, 0L) + amount);
        return true;
    }

    // 查询余额
    public long balanceOf(String address) {
        return balanceMap.getOrDefault(address, 0L);
    }

    @Override
    public Object executeContract(String method, Object... params) {
        return switch (method) {
            case "transfer" -> transfer((String) params[0], (String) params[1], (Long) params[2]);
            case "balanceOf" -> balanceOf((String) params[0]);
            case "getTokenInfo" -> tokenName + "|" + tokenSymbol + "|" + totalSupply;
            default -> "不支持的合约方法";
        };
    }

    // Getter
    public String getTokenName() { return tokenName; }
    public String getTokenSymbol() { return tokenSymbol; }
    public long getTotalSupply() { return totalSupply; }
}
