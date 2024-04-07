package com.wyj.cloudopen.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * <p>
 * AES encrypt util
 * </p>
 */
public class AES {
	private static final Logger logger = LoggerFactory.getLogger(AES.class);

	// 默认秘钥，用于普通参数加解密
	public static final String DEFAULT_ENCRYPT = "5f6db7ec8325a5e4";

	private static final Encoder ENCODER = new Encoder();

	private SecretKeySpec secretKey;

	/*
	 * 单例初始化，一般用于比较大，复杂的对象，只初始化一次，应该还有一个private的构造函数，使得不能用new来实例化对象，只能调用getInstance方法来得到对象，而getInstance保证了每次调用都返回相同的对象。
	 */
	public static AES getInstance() {
		return new AES();
	}

	/*
	 * private的构造函数，使得不能用new来实例化对象
	 */
	private AES() {}

	public AES(String str) {
		setKey(str);// generate secret key
	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	/**
	 * generate KEY
	 */
	public void setKey(String strKey) {
		try {
			byte[] bk = strKey.getBytes(StandardCharsets.UTF_8);
			this.secretKey = new SecretKeySpec(bk, Algorithm.AES.getKey());
		} catch (Exception e) {
			logger.error("Encrypt setKey is exception.", e);
		}
	}


	/**
	 * @Description AES encrypt 直接调用会找不到SecretKey getSecretKey()方法需先调用setKey 现变更为私有方法
	 * @param str
	 * @return
	 */
	private String encryptAES(String str) {
		byte[] encryptBytes = null;
		String encryptStr = null;
		try {
			Cipher cipher = Cipher.getInstance(Algorithm.AES.getKey());
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
			encryptBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
			if (encryptBytes != null) {
				encryptStr = encodeToString(encryptBytes);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return encryptStr;
	}

	/**
	 * @Description AES encrypt getSecretKey()方法需先调用setKey 现变更为私有方法
	 * @param bytes
	 * @return
	 */
	private byte[] encryptAES(byte[] bytes) {
		try {
			Cipher cipher = Cipher.getInstance(Algorithm.AES.getKey());
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description AES decrypt 直接调用会找不到 SecretKey getSecretKey()方法需先调用setKey 现变更为私有方法
	 * @param str
	 * @return
	 */
	public String decryptAES(String str) {
		byte[] decryptBytes = null;
		String decryptStr = null;
		try {
			Cipher cipher = Cipher.getInstance(Algorithm.AES.getKey());
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
			byte[] scrBytes = decode(str);
			decryptBytes = cipher.doFinal(scrBytes);
			if (decryptBytes != null) {
				decryptStr = new String(decryptBytes,StandardCharsets.UTF_8);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return decryptStr;
	}

	/**
	 * @Description AES decrypt getSecretKey()方法需先调用setKey 现变更为私有方法
	 * @param bytes
	 * @return
	 */
	private byte[] decryptAES(byte[] bytes) {
		try {
			Cipher cipher = Cipher.getInstance(Algorithm.AES.getKey());
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
			return cipher.doFinal(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * AES encrypt
	 * @param value 待加密字符串
	 * @param key 加密密钥
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String value, String key) throws Exception {
		setKey(key);
		return encryptAES(value);
	}

	/**
	 * AES decrypt
	 * @param value 待解密字符串
	 * @param key 解密密钥
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String value, String key) throws Exception {
		setKey(key);
		return decryptAES(value);
	}

	/**
	 * 生成随机密钥
	 * @return
	 */
	public static String generateKey() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
	}


	public static byte[] encode(byte[] data) {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try {
			ENCODER.encode(data, 0, data.length, bOut);
		} catch (IOException e) {
			throw new RuntimeException("encoding base64 exception: " + e);
		}
		return bOut.toByteArray();
	}

	/**
	 * 二进制数据编码为BASE64字符串
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static String encodeToString(byte[] bytes) throws Exception {
		return new String(encode(bytes), StandardCharsets.UTF_8);
	}

	public static byte[] decode(String data) {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		try {
			ENCODER.decode(data, bOut);
		} catch (IOException e) {
			throw new RuntimeException("decoding base64 exception: " + e);
		}

		return bOut.toByteArray();
	}
}

enum Algorithm {
	MD5("MD5"), SHA1("SHA-1"),SHA256("SHA-256"), AES("AES"), RSA("RSA");

	/** 主键 */
	private final String key;

	Algorithm(final String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

}

class Encoder {
	protected final byte[] encodingTable = { 65, 66, 67, 68, 69, 70, 71, 72,
			73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
			90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109,
			110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122,
			48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
	protected byte padding = 61;
	protected final byte[] decodingTable = new byte[128];

	protected void initialiseDecodingTable() {
		for (int i = 0; i < this.encodingTable.length; ++i) {
			this.decodingTable[this.encodingTable[i]] = (byte) i;
		}
	}

	public Encoder() {
		initialiseDecodingTable();
	}

	public int encode(byte[] data, int off, int length, OutputStream out)
			throws IOException {
		int b1;
		int b2;
		int d1;
		int modulus = length % 3;
		int dataLength = length - modulus;

		for (int i = off; i < off + dataLength; i += 3) {
			int a1 = data[i] & 0xFF;
			int a2 = data[(i + 1)] & 0xFF;
			int a3 = data[(i + 2)] & 0xFF;

			out.write(this.encodingTable[(a1 >>> 2 & 0x3F)]);
			out.write(this.encodingTable[((a1 << 4 | a2 >>> 4) & 0x3F)]);
			out.write(this.encodingTable[((a2 << 2 | a3 >>> 6) & 0x3F)]);
			out.write(this.encodingTable[(a3 & 0x3F)]);
		}

		switch (modulus) {
			case 0:
				break;
			case 1:
				d1 = data[(off + dataLength)] & 0xFF;
				b1 = d1 >>> 2 & 0x3F;
				b2 = d1 << 4 & 0x3F;

				out.write(this.encodingTable[b1]);
				out.write(this.encodingTable[b2]);
				out.write(this.padding);
				out.write(this.padding);
				break;
			case 2:
				d1 = data[(off + dataLength)] & 0xFF;
				int d2 = data[(off + dataLength + 1)] & 0xFF;

				b1 = d1 >>> 2 & 0x3F;
				b2 = (d1 << 4 | d2 >>> 4) & 0x3F;
				int b3 = d2 << 2 & 0x3F;

				out.write(this.encodingTable[b1]);
				out.write(this.encodingTable[b2]);
				out.write(this.encodingTable[b3]);
				out.write(this.padding);
			default:
				break;
		}

		return (dataLength / 3 * 4 + ((modulus == 0) ? 0 : 4));
	}

	private boolean ignore(char c) {
		return ((c == '\n') || (c == '\r') || (c == '\t') || (c == ' '));
	}

	public int decode(byte[] data, int off, int length, OutputStream out)
			throws IOException {
		int outLen = 0;

		int end = off + length;

		while (end > off) {
			if (!(ignore((char) data[(end - 1)]))) {
				break;
			}

			--end;
		}

		int i = off;
		int finish = end - 4;

		i = nextI(data, i, finish);

		while (i < finish) {
			byte b1 = this.decodingTable[data[(i++)]];

			i = nextI(data, i, finish);

			byte b2 = this.decodingTable[data[(i++)]];

			i = nextI(data, i, finish);

			byte b3 = this.decodingTable[data[(i++)]];

			i = nextI(data, i, finish);

			byte b4 = this.decodingTable[data[(i++)]];

			out.write(b1 << 2 | b2 >> 4);
			out.write(b2 << 4 | b3 >> 2);
			out.write(b3 << 6 | b4);

			outLen += 3;

			i = nextI(data, i, finish);
		}

		outLen += decodeLastBlock(out, (char) data[(end - 4)],
				(char) data[(end - 3)], (char) data[(end - 2)],
				(char) data[(end - 1)]);

		return outLen;
	}

	private int nextI(byte[] data, int i, int finish) {
		while ((i < finish) && (ignore((char) data[i]))) {
			++i;
		}
		return i;
	}

	public int decode(String data, OutputStream out) throws IOException {
		int length = 0;

		int end = data.length();

		while (end > 0) {
			if (!(ignore(data.charAt(end - 1)))) {
				break;
			}

			--end;
		}

		int i = 0;
		int finish = end - 4;

		i = nextI(data, i, finish);

		while (i < finish) {
			byte b1 = this.decodingTable[data.charAt(i++)];

			i = nextI(data, i, finish);

			byte b2 = this.decodingTable[data.charAt(i++)];

			i = nextI(data, i, finish);

			byte b3 = this.decodingTable[data.charAt(i++)];

			i = nextI(data, i, finish);

			byte b4 = this.decodingTable[data.charAt(i++)];

			out.write(b1 << 2 | b2 >> 4);
			out.write(b2 << 4 | b3 >> 2);
			out.write(b3 << 6 | b4);

			length += 3;

			i = nextI(data, i, finish);
		}

		length += decodeLastBlock(out, data.charAt(end - 4), data
				.charAt(end - 3), data.charAt(end - 2), data.charAt(end - 1));

		return length;
	}

	private int decodeLastBlock(OutputStream out, char c1, char c2, char c3,
								char c4) throws IOException {
		byte b1, b2, b3, b4;
		if (c3 == this.padding) {
			b1 = this.decodingTable[c1];
			b2 = this.decodingTable[c2];

			out.write(b1 << 2 | b2 >> 4);

			return 1;
		}
		if (c4 == this.padding) {
			b1 = this.decodingTable[c1];
			b2 = this.decodingTable[c2];
			b3 = this.decodingTable[c3];

			out.write(b1 << 2 | b2 >> 4);
			out.write(b2 << 4 | b3 >> 2);

			return 2;
		}

		b1 = this.decodingTable[c1];
		b2 = this.decodingTable[c2];
		b3 = this.decodingTable[c3];
		b4 = this.decodingTable[c4];

		out.write(b1 << 2 | b2 >> 4);
		out.write(b2 << 4 | b3 >> 2);
		out.write(b3 << 6 | b4);

		return 3;
	}

	private int nextI(String data, int i, int finish) {
		while ((i < finish) && (ignore(data.charAt(i)))) {
			++i;
		}
		return i;
	}
}