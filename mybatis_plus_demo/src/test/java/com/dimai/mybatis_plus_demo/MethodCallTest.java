package com.dimai.mybatis_plus_demo;

import com.dimai.mybatis_plus_demo.controller.CallController;
import com.dimai.mybatis_plus_demo.controller.CallUtils;
import org.junit.Test;

/**
 * Created by pijiang on 2019/9/19.
 */
public class MethodCallTest extends BaseTest{

    @Test
    public void main(){
        CallUtils.excuteProess("excuteAction", CallController.class);
    }

}