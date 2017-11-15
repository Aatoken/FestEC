package com.mk.festec.example.generators;

import com.example.annotations.EntryGenerator;
import com.mk.latte.wechat.templates.WXEntryTemplate;

/**
 * @author lenovo
 * @data 2017/11/14
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.mk.festec.example",
        entryTemplate = WXEntryTemplate.class
)
public class WeChatEntry {
}
