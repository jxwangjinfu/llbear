package com.junfeng.platform.payment.bank.abcbank.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package poscomm;

//import com.chase.utils.RsaUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cly
 */
public class test {


    public static void main(String[] args) {



        HttpClient c = new HttpClient("http://202.108.144.34:9000/upay/UPTP.ebf",30000, 30000);
        try {
            Map map = new HashMap();
            map.put("os","android");
            map.put("appVerison","1.0.4.0");
            map.put("reqDate","20190122");
            map.put("reqTime","204454");
            map.put("provCode","14");
            map.put("srcAreaCode","14");
            map.put("sysId","httpSite");
            map.put("accessType","application/json");
            map.put("httpWebPath","abcah/ycbmp/vineSpeaker.action");
            map.put("charset","GB2312");
            map.put("retCharset","GB2312");
            map.put("sendPackage","{\"sendData\":\"MDAwNjg2eyJjaGFyc2V0IjoiVVRGLTgiLCJlbmNyeXB0X3R5cGUiOiJSU0EiLCJiaXpfY29udGVudCI6IlZQbVY1eG5KRlFVUFJuNW1nZGdFaUFXR09BVHZMZzRJZUVJZVVHa2liRjAyTUFXTjh5R1M2enVDN1BnSEExdmIxRXBPY0x1c0dXTktzdlNsTks1Tlh5MDBEdll3cHVONlJJU295RTNDRHlTVmJpem9JaVNEUGV2RTRHOG9QdE5QWjZpMzZZMXVYZ1ZFbWEvdzBMVGdCYjRTb0NNU1p3UTFxVW9jZVdCeWlFMklKRlNJd3p2VUNHc3dTRStPcWhQSmM1b01TRThRTjhhV01JNUR5bHJvdEZFUUplTnJGellUc1FkL2Z2a0FjeWNKdzhsSHBMT2VtenlncW5tMzc5VWZOUGhWNnRRNnY4TVB0MmdyMlZuRFlBYlc1ZFFQU2tCN2lWc1pFTDdVOVVWaVNNZG1TZGs1ZkdhNFhKMGJuK01zM3ZUamVtK1Vtd2YxMFFPNS9WNUdwZz09IiwiZm9ybWF0IjoianNvbiIsInNpZ24iOiJCaTB0UTl0c1ErcHdTbVFiWHpiQWVCNTVISm10UnoveHg1TmJiZFZnNHBEQ3Ftb3Ewa1lNZ2hNUnBldHYwWnAwdzY1Y25rNStKc3dDWmV2MkRTM0NLSVVKK0xia0ttSVJSQmlRZVRSeURVWkRWczR6clJ5NXozMisraHVibGZjbDJZSm5DdWVadWVlUVEzZVczcEhaVXVONnRscEVkbWF1ZXF4enIwNk1JT3M9IiwiYXBwX2lkIjoiMTAwMSIsInNpZ25fdHlwZSI6IlJTQTIiLCJ0cmFuc190eXBlIjoiMDEiLCJ0aW1lc3RhbXAiOiIyMDE5LTA1LTA2IDEwOjQxOjM2In0=\",\"sendpacktype\":\"base64\"}");


            c.send(map, "UTF-8");

            System.out.println(c.getResult());
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
