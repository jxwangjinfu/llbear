package com.junfeng.platform.payment.bank.constructionbank.untils;

import java.util.Map;

public class CcBankSignUtils {

    public static String getSign(Map<String, String> map,String strKey) {
        String strSrcParas = TanslateUtil.map2Serialization(map);

        COM.CCB.EnDecryptAlgorithm.MCipherEncryptor ccbEncryptor = new COM.CCB.EnDecryptAlgorithm.MCipherEncryptor(
                strKey);
        String ccbParam = "";
        try {
            ccbParam = ccbEncryptor.doEncrypt(strSrcParas);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("密文:" + ccbParam);
        return ccbParam;
    }

}
