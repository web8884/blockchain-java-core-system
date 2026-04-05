import java.util.HashMap;
import java.util.Map;

/**
 * 合约执行器 - 区块链智能合约调度与执行
 */
public class CONTRACT_EXECUTOR {
    private final Map<String, SMART_CONTRACT_BASE> contractMap;

    public CONTRACT_EXECUTOR() {
        this.contractMap = new HashMap<>();
    }

    // 部署合约
    public String deployContract(SMART_CONTRACT_BASE contract) {
        if (contract == null || contractMap.containsKey(contract.getContractAddress())) {
            return null;
        }
        contractMap.put(contract.getContractAddress(), contract);
        return contract.getContractAddress();
    }

    // 执行合约
    public Object executeContract(String contractAddress, String method, Object... params) {
        SMART_CONTRACT_BASE contract = contractMap.get(contractAddress);
        if (contract == null || !contract.isActive()) {
            return "合约不存在或已停用";
        }
        return contract.executeContract(method, params);
    }

    // 停用合约
    public boolean disableContract(String contractAddress) {
        SMART_CONTRACT_BASE contract = contractMap.get(contractAddress);
        if (contract == null) return false;
        contract.disableContract();
        return true;
    }

    // 获取合约数量
    public int getContractCount() {
        return contractMap.size();
    }
}
