import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 *
 * @author ct
 *
 */
@Component
public class RSAUtil {

	private static String privateKey_prod = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKwX1YM+yYfSGvty5B1jWoTTyIEGnN9t2a+HUzQ95E3D9gmnz8IruNyl424ZktrKzRQzoiUMjnJ7YZNJGGeTcoP+nKjRCFJSMsuHEA8FK+acP7Wp9UVqjMR5cAqRNvji+FYKF12efYd9fI7UVQjfN3+KBzAZssK6uHnvEFUHFnoNAgMBAAECgYBSMsQp/yh1dlaq+dKZcuY+gYXIkycE1Uxfucx1LIbK2im/rObgQM/4nEfIQ/DQDoPr/UmaVqgHaIVPu18rwdK1DSZbZsc8eFC/ZUMRcI7VYgXpZfLiPTiopm9d71p5+ZsiqlS1XNDIIxwvbXWDhTU947csRqSAaoo1hSOXD+GuxQJBAO3vvQ7cWHz+WI+STtr4/95HM07mcDnPgJjHCg1eDOyVEiDXLdH2vPonova3cCTeh+YmdHD3otRAvfcU34y5ib8CQQC5KHBbignqKeS7jt6yZSojo4sM2SI2DlHUJcGgo6FeemQMIJ0L2T5Mw70hbdxrqu2B/s/xP412W1bcD6xweDczAkEAiMVOP8SZeQAbmCvZ+DU5n2P+MlR0HkcQ/MEtHhH6C+SEzyvhwte6OXGfU4KOjCL+q2VfebBfgPLWP7lX52SfsQJALHazlKfvfZYV4ZyLiheVKP3LFIjIGGcT63gtqV/iDoghom/qhr/ioBiZQH/bYNepNYB1ex+h3vxMPYheBpw31QJAS35R3nasS67Vvm7KLviozE9VE7mch6L6OwLyiXYc3v5w6iB14Yo7zCzoMEw+JlyuqvcBEJZeiwmwWWBrsS34ZQ==";
	private static String publicKey_prod = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsF9WDPsmH0hr7cuQdY1qE08iBBpzfbdmvh1M0PeRNw/YJp8/CK7jcpeNuGZLays0UM6IlDI5ye2GTSRhnk3KD/pyo0QhSUjLLhxAPBSvmnD+1qfVFaozEeXAKkTb44vhWChddnn2HfXyO1FUI3zd/igcwGbLCurh57xBVBxZ6DQIDAQAB";

	// 加解密算法
	public static final String KEY_ALGORITHM = "RSA";
	// 签名算法 MD5withRSA SHA256withRSA SHA1withRSA NONEwithRSA
	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	private String privateKeyStr;
	private String publicKeyStr;
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;

	/**
	 * Description：公钥加密
	 **/
	public String publicKeyEncrypt(String sourceData) {
		Cipher cipher = null;
		try {
			byte[] data = sourceData.getBytes("UTF-8");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
			byte[] output = cipher.doFinal(data);
			return byte2hex(output);// byte2hex(output);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Description：私钥解密
	 *
	 * @param encodeData
	 *            需要解密的数据 hex
	 * @return 解密后的数据
	 */
	public String privateKeyDecrypt(String encodeData) {
		Cipher cipher;
		try {
			byte[] data = hexStr2byte(encodeData);
			cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
			byte[] output = cipher.doFinal(data);
			return new String(output, "UTF-8");
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Description：生成公私钥对
	 *
	 * @return
	 * @throws Exception
	 * @return RSA
	 **//*
	public static TestRSA genKeyPair() throws Exception {

		KeyPairGenerator keyPairGen = null;
		keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String privateKey64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		String publicKey64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		TestRSA rsa = new TestRSA();
		rsa.setPrivateKey(privateKey);
		rsa.setPublicKey(publicKey);
		rsa.setPrivateKeyStr(privateKey64);
		rsa.setPublicKeyStr(publicKey64);
		return rsa;

	}*/

	public String getPrivateKeyStr() {
		return privateKeyStr;
	}

	public void setPrivateKeyStr(String privateKeyStr) {
		this.privateKeyStr = privateKeyStr;
	}

	public String getPublicKeyStr() {
		return publicKeyStr;
	}

	public void setPublicKeyStr(String publicKeyStr) {
		this.publicKeyStr = publicKeyStr;
	}

	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	/**
	 *
	 * @param sourceData
	 * @param pKey
	 * @return
	 */
	public static String publicKeyEncrypt(String sourceData, RSAPublicKey pKey) {
		Cipher cipher = null;
		try {
			byte[] data = sourceData.getBytes("UTF-8");
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, pKey);
			byte[] output = cipher.doFinal(data);
			return byte2hex(output);// byte2hex(output);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Description：私钥解密
	 *
	 * @param encodeData
	 *            需要解密的数据 hex
	 * @return 解密后的数据
	 * @author 拜力文
	 */
	public static String privateKeyDecrypt(String encodeData, PrivateKey privatekey) {
		Cipher cipher = null;
		try {
			byte[] data = hexStr2byte(encodeData);
			// cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
			cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privatekey);
			byte[] output = cipher.doFinal(data);
			return new String(output, "UTF-8");// byte2hex(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生产RSA公钥
	 *
	 * @param publickey
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey getPublicKeyBykey(String publickey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		byte[] publicKeyByte = Base64.getDecoder().decode(publickey);
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(publicKeyByte);
		RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
		return publicKey;
	}

	/**
	 * 生产RSA私钥
	 */
	public static RSAPrivateKey getprivateKeyBykey(String privatekey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		byte[] privateKeyByte = Base64.getDecoder().decode(privatekey);
		PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
		RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(priKeySpec);
		return privateKey;
	}

	/**
	 * Description：签名验证
	 *
	 * @param sourceData
	 * @param signData
	 * @return
	 * @return boolean
	 * @author 拜力文：
	 **/
	public static boolean signVerify(String sourceData, String signData, RSAPublicKey publicKey) {
		Signature signature;
		try {
			signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey);
			byte[] srcData = sourceData.getBytes("UTF-8");
			signature.update(srcData);
			byte[] hexbyte = hexStr2byte(signData);
			if (hexbyte == null) {
				return false;
			}
			return signature.verify(hexbyte);
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Description：将二进制转换成16进制字符串
	 *
	 * @param b
	 * @return
	 * @return String
	 * @author name：
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	/**
	 * Description：将十六进制的字符串转换成字节数据
	 *
	 * @param strhex
	 * @return
	 * @return byte[]
	 * @author name：
	 */
	public static byte[] hexStr2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
		}
		return b;
	}

	public static String newString(byte[] bytes, String charsetName) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, charsetName);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
//		String mobile = "15113867387";
		String mobile = "15626155833";
		RSAPublicKey publicKey = getPublicKeyBykey(publicKey_prod);
		mobile = publicKeyEncrypt(mobile, publicKey);
		System.out.println(mobile);
	}
}
