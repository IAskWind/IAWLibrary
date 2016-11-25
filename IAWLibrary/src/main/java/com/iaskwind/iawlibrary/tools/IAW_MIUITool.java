package com.iaskwind.iawlibrary.tools;

import java.io.IOException;

/**
 * Created by winston on 16/11/25.
 * 小米特别工具类
 */
public class IAW_MIUITool {

    //    作者：Mariotaku
//    链接：http://www.zhihu.com/question/22102139/answer/24834510
//    来源：知乎
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    public static boolean isMIUI() {
        try {
            final IAW_BuildPropertiesTool prop = IAW_BuildPropertiesTool.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }
}
