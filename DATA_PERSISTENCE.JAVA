import java.io.*;
import java.util.List;

/**
 * 数据持久化 - 区块链账本本地文件存储
 */
public class DATA_PERSISTENCE {
    private static final String FILE_PATH = "blockchain_data.dat";

    // 保存区块链到文件
    public boolean saveBlockchain(List<BLOCK_ENTITY> blockchain) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(blockchain);
            return true;
        } catch (IOException e) {
            System.err.println("区块链保存失败：" + e.getMessage());
            return false;
        }
    }

    // 从文件加载区块链
    public List<BLOCK_ENTITY> loadBlockchain() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<BLOCK_ENTITY>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("区块链加载失败：" + e.getMessage());
            return null;
        }
    }

    // 删除数据文件
    public boolean deleteDataFile() {
        File file = new File(FILE_PATH);
        return file.exists() && file.delete();
    }
}
