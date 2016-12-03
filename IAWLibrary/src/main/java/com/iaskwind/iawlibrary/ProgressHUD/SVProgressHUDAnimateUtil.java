package com.iaskwind.iawlibrary.ProgressHUD;

import android.view.Gravity;

/**
 * Created by winston on 16/12/3.
 */

public class SVProgressHUDAnimateUtil {
    private static final int INVALID = -1;
    static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.TOP:
                return isInAnimation ? com.bigkoo.svprogresshud.R.anim.svslide_in_top : com.bigkoo.svprogresshud.R.anim.svslide_out_top;
            case Gravity.BOTTOM:
                return isInAnimation ? com.bigkoo.svprogresshud.R.anim.svslide_in_bottom : com.bigkoo.svprogresshud.R.anim.svslide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? com.bigkoo.svprogresshud.R.anim.svfade_in_center : com.bigkoo.svprogresshud.R.anim.svfade_out_center;
            default:
                // This case is not implemented because we don't expect any other gravity at the moment
        }
        return INVALID;
    }
}
