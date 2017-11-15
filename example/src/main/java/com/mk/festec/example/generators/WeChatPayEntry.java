package com.mk.festec.example.generators;

import com.example.annotations.PayEntryGenerator;
import com.mk.latte.wechat.templates.WXPayEntryTemplate;

/**
 * @author lenovo
 * @data 2017/11/14
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.mk.festec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public class WeChatPayEntry {
}
