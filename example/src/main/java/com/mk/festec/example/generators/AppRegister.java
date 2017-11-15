package com.mk.festec.example.generators;

import com.example.annotations.AppRegisterGenerator;
import com.mk.latte.wechat.templates.AppRegisterTemplate;

/**
 * @author lenovo
 * @data 2017/11/14
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.mk.festec.example",
        registerTemplate = AppRegisterTemplate.class
)
public class AppRegister {
}
