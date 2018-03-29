package com.mk.college;

import act.Act;
import act.apidoc.Description;
import act.controller.Controller;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;
import org.osgl.storage.ISObject;
import org.osgl.storage.impl.SObject;


@SuppressWarnings("unused")
public class Application extends Controller.Base{

    @GetAction
    public void home() {
        render("/home.html");
    }

    @GetAction("/validateCode.jpegx")
    @Description("通用-验证码-获取图片验证码")
    public Result validatePicCode(H.Response response) {
        String code = VerifyCodeUtils.outputVerifyImage(4);
        byte[] ba = VerifyCodeUtils.outputImage(100, 36, code);
        ISObject sobj = SObject.of(ba);
        sobj.setAttribute(ISObject.ATTR_FILE_NAME, "validatecode.jpg");
        sobj.setAttribute(ISObject.ATTR_CONTENT_TYPE, "image/jpg");
        return binary(sobj);
    }

    @GetAction("/GlobalValues.jsx")
    public String getConfigJs(H.Response response) {
        String Glovalues = "var GlobalValues = {};" +
            "var GlobalConfigs = {};" +
            "var GlobalValues2 = {};" +
            "var SysConfig = {};";
        response.addHeader("Access-Control-Allow-Methods", "GET");
        response.addHeader("Content-Type", "application/javascript;charset=UTF-8");
        return Glovalues;
    }


    public static void main(String[] args) throws Exception {
        Act.start();
    }


}
