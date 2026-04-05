/**
 * 智能合约基类 - 所有自定义合约的父类
 */
public abstract class SMART_CONTRACT_BASE {
    protected final String contractAddress;
    protected final long deployTime;
    protected boolean isActive;

    public SMART_CONTRACT_BASE() {
        this.contractAddress = generateContractAddress();
        this.deployTime = System.currentTimeMillis();
        this.isActive = true;
    }

    // 生成合约地址
    private String generateContractAddress() {
        return "CONTRACT_" + SHA256_ENCRYPTION_UTIL.encrypt(String.valueOf(deployTime)).substring(0, 32);
    }

    // 合约执行方法（子类实现）
    public abstract Object executeContract(String method, Object... params);

    // 停用合约
    public void disableContract() {
        this.isActive = false;
    }

    // Getter
    public String getContractAddress() { return contractAddress; }
    public boolean isActive() { return isActive; }
    public long getDeployTime() { return deployTime; }
}
