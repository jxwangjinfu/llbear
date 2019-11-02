package com;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;

/**
 * 加解密测试类
 * @projectName:pig-upms-biz
 * @author:Wangjf
 * @date:2019年9月10日 上午10:04:29
 * @version 1.0
 */
public class JasyptTest {


    /**
     * 加密测试
     * @throws Exception
     * @author:Wangjf
     * @createTime:2019年9月10日 上午10:05:19
     */
    @Test
    public void testEncrypt() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");          // 加密的算法，这个算法是默认的
        config.setPassword("pig");                        // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "payment-center";
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);
    }

    /**
     * 解密测试
     * @throws Exception
     * @author:Wangjf
     * @createTime:2019年9月10日 上午10:05:28
     */
    @Test
    public void testDe() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("pig");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "imENTO7M8bLO38LFSIxnzw==";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
        System.out.println(standardPBEStringEncryptor.decrypt("i3cDFhs26sa2Ucrfz2hnQw=="));
        System.out.println(standardPBEStringEncryptor.decrypt("27v1agvAug87ANOVnbKdsw=="));
        System.out.println(standardPBEStringEncryptor.decrypt("78KHENwIGQhsTGQkWUD47EWBH6BqUe9r"));
        System.out.println(standardPBEStringEncryptor.decrypt("I6ukKL0FIADC59Nvxvpnw47IthWmyBW/"));

    }

}
