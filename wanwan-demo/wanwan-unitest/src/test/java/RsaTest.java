import org.springzhisuan.core.tool.tuple.KeyPair;
import org.springzhisuan.core.tool.utils.RsaUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author wanwan 2022/4/15
 */
public class RsaTest {

	private static String privateKey_prod = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKwX1YM+yYfSGvty5B1jWoTTyIEGnN9t2a+HUzQ95E3D9gmnz8IruNyl424ZktrKzRQzoiUMjnJ7YZNJGGeTcoP+nKjRCFJSMsuHEA8FK+acP7Wp9UVqjMR5cAqRNvji+FYKF12efYd9fI7UVQjfN3+KBzAZssK6uHnvEFUHFnoNAgMBAAECgYBSMsQp/yh1dlaq+dKZcuY+gYXIkycE1Uxfucx1LIbK2im/rObgQM/4nEfIQ/DQDoPr/UmaVqgHaIVPu18rwdK1DSZbZsc8eFC/ZUMRcI7VYgXpZfLiPTiopm9d71p5+ZsiqlS1XNDIIxwvbXWDhTU947csRqSAaoo1hSOXD+GuxQJBAO3vvQ7cWHz+WI+STtr4/95HM07mcDnPgJjHCg1eDOyVEiDXLdH2vPonova3cCTeh+YmdHD3otRAvfcU34y5ib8CQQC5KHBbignqKeS7jt6yZSojo4sM2SI2DlHUJcGgo6FeemQMIJ0L2T5Mw70hbdxrqu2B/s/xP412W1bcD6xweDczAkEAiMVOP8SZeQAbmCvZ+DU5n2P+MlR0HkcQ/MEtHhH6C+SEzyvhwte6OXGfU4KOjCL+q2VfebBfgPLWP7lX52SfsQJALHazlKfvfZYV4ZyLiheVKP3LFIjIGGcT63gtqV/iDoghom/qhr/ioBiZQH/bYNepNYB1ex+h3vxMPYheBpw31QJAS35R3nasS67Vvm7KLviozE9VE7mch6L6OwLyiXYc3v5w6iB14Yo7zCzoMEw+JlyuqvcBEJZeiwmwWWBrsS34ZQ==";
	private static String publicKey_prod = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsF9WDPsmH0hr7cuQdY1qE08iBBpzfbdmvh1M0PeRNw/YJp8/CK7jcpeNuGZLays0UM6IlDI5ye2GTSRhnk3KD/pyo0QhSUjLLhxAPBSvmnD+1qfVFaozEeXAKkTb44vhWChddnn2HfXyO1FUI3zd/igcwGbLCurh57xBVBxZ6DQIDAQAB";

	public static void main(String[] args) {
		KeyPair keyPair = RsaUtil.genKeyPair();

		String privateBase64 = keyPair.getPrivateBase64();
		String publicBase64 = keyPair.getPublicBase64();

		System.out.println(privateBase64 +"\n" + publicBase64);

		byte[] encrypt = RsaUtil.encrypt(RsaUtil.getPublicKey(publicKey_prod), "15626155833".getBytes(StandardCharsets.UTF_8));
		byte[] decrypt = RsaUtil.decrypt(RsaUtil.getPrivateKey(privateKey_prod), encrypt);
		//System.out.println(new String(encrypt, StandardCharsets.UTF_8));
		//System.out.println(new String(decrypt, StandardCharsets.UTF_8));
	}
}
