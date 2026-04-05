import java.util.Base64;

/**
 * 数据编码器 - 区块链数据Base64/十六进制编码
 */
public class DATA_ENCODER {
    // Base64编码
    public static String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    // Base64解码
    public static String decodeBase64(String input) {
        return new String(Base64.getDecoder().decode(input));
    }

    // 十六进制编码
    public static String encodeHex(String input) {
        StringBuilder hex = new StringBuilder();
        for (char c : input.toCharArray()) {
            hex.append(Integer.toHexString(c));
        }
        return hex.toString();
    }

    // 十六进制解码
    public static String decodeHex(String input) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            String sub = input.substring(i, i + 2);
            str.append((char) Integer.parseInt(sub, 16));
        }
        return str.toString();
    }
}
