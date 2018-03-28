package com.mk.college;

import act.Act;
import act.apidoc.Description;
import act.controller.Controller;
import org.osgl.$;
import org.osgl.http.H;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.result.Result;

import java.io.UnsupportedEncodingException;


@SuppressWarnings("unused")
public class Application extends Controller.Base{

    @GetAction("/validateCode.jpegx")
    @Description("通用-验证码-获取图片验证码")
    public Result validatePicCode() {
        String code = VerifyCodeUtils.outputVerifyImage(4);
        return binary($.visitor(out -> {
            VerifyCodeUtils.outputImage(100, 36, code, out.asOutputStream());
            return null;
        }));
    }

    @GetAction("/GlobalValues.jsx")
    public Result getConfigJs(H.Response response) {
        String Glovalues = "var GlobalValues = {};" +
            "var GlobalConfigs = {};" +
            "var GlobalValues2 = {};" +
            "var SysConfig = {};";
        response.addHeader("Access-Control-Allow-Methods", "GET");
        response.addHeader("Content-Type", "application/javascript;charset=UTF-8");
        return binary($.visitor(out -> {
            try {
                out.append(Glovalues.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }));
    }


    public static void main(String[] args) throws Exception {
        Act.start();
    }


}
