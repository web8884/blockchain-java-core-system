import java.io.*;
import java.security.KeyPair;

/**
 * 钱包备份服务 - 区块链钱包密钥备份与恢复
 */
public class WALLET_BACKUP_SERVICE {
    private static final String BACKUP_DIR = "wallet_backup/";

    static {
        File dir = new File(BACKUP_DIR);
        if (!dir.exists()) dir.mkdirs();
    }

    // 备份钱包密钥
    public boolean backupWallet(String address, KeyPair keyPair) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BACKUP_DIR + address + ".dat"))) {
            oos.writeObject(keyPair);
            return true;
        } catch (IOException e) {
            System.err.println("钱包备份失败：" + e.getMessage());
            return false;
        }
    }

    // 恢复钱包密钥
    public KeyPair restoreWallet(String address) {
        File file = new File(BACKUP_DIR + address + ".dat");
        if (!file.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (KeyPair) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("钱包恢复失败：" + e.getMessage());
            return null;
        }
    }
}
