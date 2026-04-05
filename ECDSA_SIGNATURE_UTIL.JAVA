import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * ECDSA椭圆曲线签名工具类 - 区块链交易签名/验签核心组件
 */
public class ECDSA_SIGNATURE_UTIL {
    // 生成ECDSA密钥对
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyGen.initialize(ecSpec, new SecureRandom());
        return keyGen.generateKeyPair();
    }

    // 签名数据
    public static String signData(PrivateKey privateKey, String data) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signBytes);
    }

    // 验证签名
    public static boolean verifySignature(PublicKey publicKey, String data, String signatureStr) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        byte[] signBytes = Base64.getDecoder().decode(signatureStr);
        return signature.verify(signBytes);
    }
}
