package com.junfeng.platform.payment.bank.citicbankmini.request;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniQueryParam;
import com.junfeng.platform.payment.bank.citicbankmini.model.CiticBankMiniQueryResult;
import com.junfeng.platform.payment.common.AsXmlMapMapper;

public class CiticBankMiniQueryRequest extends CiticBankMiniBaseRequest{

    private CiticBankMiniQueryParam param;


    public CiticBankMiniQueryRequest(CiticBankMiniQueryParam param) {
        this.service = "unified.trade.query";
        this.param = param;
        this.key = param.getKey();
        this.mchId = param.getMchId();
        this.outTradeNo = param.getOutTradeNo();
    }

    @Override
    public Map<String, String> getParams() {

        return null;
    }

    /**
     * 返回结果
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @author:xionghui
     * @createTime:2018年8月23日 下午4:55:24
     */
    public CiticBankMiniQueryResult doPostForObject() throws IOException, URISyntaxException {
        String result = doRequest();
        return AsXmlMapMapper.fromXml("CiticBankMiniQueryResult",result, CiticBankMiniQueryResult.class);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        CiticBankMiniQueryParam param = new CiticBankMiniQueryParam();
        String key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC3Q6/K0k+TVgudSY59kfpeQKy3M2o4I5V8mkQzEOTdmhgWT6KtsImGrf1ck8dezbrPxb6xkpXvoJ4GAUr+8mKdRM440lZXmNqIw1SRIeI+br/g2CURnur80gUlzrKPL7R0ZJtfc1D3qA3xvBdb5YzQJxtt/bO5J+JGwdLdAxFr0vSWJPTRmJsDaiO11xJI5DjboNcQwSCJGi1Veew5B53H0A5Ab/2quE7ceXAYPuSYJYtY1AQBmgXqg3KvM9WGZaDaqnI3G+JczmiRY0JSAzBl8QbbTscpwfpHwMQdCLToWfqGjC4kANfo6B2pHzRUqIdDLNf8dyCpQG6JSF7fwlCjAgMBAAECggEAEwa/9XqoME+6nPXKCVdieu6T/+Eioflvx6PseLDEUg1xer+B0C6H2KevqjDdfgY14uRQy/bAAkTWoyD3PEjF3DYMgvGzbRy9c310Bi1SY9DzstA96Ppqwxy61BYFi8AUKBFZ4CsEuPGFoRc6BZxrZiDpRB4Wzp5Ja7crwS5UXLE5FnovJGvxVzJK8VrrVit6puh0hZcPbxMS5P1oQCUSuD8uUzqJAm0+QZcjMN7m11hsoFuEFXMxLLBFwOpsyCKw+BDrHDEjuxHRIto+PnLNBZY+DZxA9v+pWF7sMOpKWtxJ3DRJ9PjcsvIIybm00MTJyrS9zSLB0RdpbKEtpVMtIQKBgQDiaFj9MnyFHXIMpCWytIX5xO6N8Ytm1GBkc2v9JwC1ndKQbF4oCSHaUocO7ahqD/mWtdJRpnuoEMJFXvEskmVPsqMFuVHBybBLACEu5YTQci10K2PUbxZtylIfVxuZs7PsGaRnGi5EvTbVOFmvueYBKkTcYb03kgTeDPmjI84ATQKBgQDPN8HSpqtLgjObhah3xN+lMCoa12/uJmi95OcO6v9stGDgA5hfTV+ly9AvzVmJf1Hh8gN8cNFczTVr4G6xSOckWq0nq9VEYr++k7CAXAzEeK7RTPPhma/mfNHzyqYZDiv5Vj/jlftyq4OxC/5Pr551QqZYiucZjq7nK/5H596MrwKBgHx8J0HAOpruX8D3mAq0G14Dm67L+nvllcJlfCDLCybQye/Cxdp5evWRntTkc08tzWvCOQvP3/m+jTKbSzvYmn/BlsUU6hEFFjuGrLgeVnJN9RQEr3UkR07CiXjV6kUOe4T7bFuMF5iP+NAvlZB7RC2r0gTicKlXTfnRZIkArMmZAoGAcUN60BeF3boHV2TRVtXnoLUY7p7/cV7RNaqemUrd3p8hrJCKgt0aANfMrbRC0qMjMKtGOIi6B02re5GrL4A3x2lA6ERGSCDpydOUUDUATYvhkknLrG6C9SsY33bPHzbd1mXMeiuKy3zweJ+jnA7UQCf0IMWb2B5jf7xOOyOEQ8UCgYBaNw/z+Tpc0NTufkQFYRCeiO6Rw4xZ4O0e0f9kIBuzSs/Lhq6HUMvFeudXMz8KHZJCoWtE17CyL+CdKOnmeqrwxm05cV/A/GjZkGaEce9l5g2f8sIeqWMrYp2ZrHMB/GUX50L/FEJTPFtTNBN2OscofZ8mvwl9q8JvNJXIzbt+Pw==";
        param.setMchId("102566450643");
        param.setKey(key);
        param.setOutTradeNo("wx198212220013154");

        CiticBankMiniQueryRequest request = new CiticBankMiniQueryRequest(param);
        CiticBankMiniQueryResult result = request.doPostForObject();

        System.out.print(result.toString());
    }

}
