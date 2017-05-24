//<MStar Software>
//******************************************************************************
// MStar Software
// Copyright (c) 2010 - 2014 MStar Semiconductor, Inc. All rights reserved.
// All software, firmware and related documentation herein ("MStar Software") are
// intellectual property of MStar Semiconductor, Inc. ("MStar") and protected by
// law, including, but not limited to, copyright law and international treaties.
// Any use, modification, reproduction, retransmission, or republication of all
// or part of MStar Software is expressly prohibited, unless prior written
// permission has been granted by MStar.
//
// By accessing, browsing and/or using MStar Software, you acknowledge that you
// have read, understood, and agree, to be bound by below terms ("Terms") and to
// comply with all applicable laws and regulations:
//
// 1. MStar shall retain any and all right, ownership and interest to MStar
//    Software and any modification/derivatives thereof.
//    No right, ownership, or interest to MStar Software and any
//    modification/derivatives thereof is transferred to you under Terms.
//
// 2. You understand that MStar Software might include, incorporate or be
//    supplied together with third party's software and the use of MStar
//    Software may require additional licenses from third parties.
//    Therefore, you hereby agree it is your sole responsibility to separately
//    obtain any and all third party right and license necessary for your use of
//    such third party's software.
//
// 3. MStar Software and any modification/derivatives thereof shall be deemed as
//    MStar's confidential information and you agree to keep MStar's
//    confidential information in strictest confidence and not disclose to any
//    third party.
//
// 4. MStar Software is provided on an "AS IS" basis without warranties of any
//    kind. Any warranties are hereby expressly disclaimed by MStar, including
//    without limitation, any warranties of merchantability, non-infringement of
//    intellectual property rights, fitness for a particular purpose, error free
//    and in conformity with any international standard.  You agree to waive any
//    claim against MStar for any loss, damage, cost or expense that you may
//    incur related to your use of MStar Software.
//    In no event shall MStar be liable for any direct, indirect, incidental or
//    consequential damages, including without limitation, lost of profit or
//    revenues, lost or damage of data, and unauthorized system use.
//    You agree that this Section 4 shall still apply without being affected
//    even if MStar Software has been modified by MStar in accordance with your
//    request or instruction for your use, except otherwise agreed by both
//    parties in writing.
//
// 5. If requested, MStar may from time to time provide technical supports or
//    services in relation with MStar Software to you for your use of
//    MStar Software in conjunction with your or your customer's product
//    ("Services").
//    You understand and agree that, except otherwise agreed by both parties in
//    writing, Services are provided on an "AS IS" basis and the warranty
//    disclaimer set forth in Section 4 above shall apply.
//
// 6. Nothing contained herein shall be construed as by implication, estoppels
//    or otherwise:
//    (a) conferring any license or right to use MStar name, trademark, service
//        mark, symbol or any other identification;
//    (b) obligating MStar or any of its affiliates to furnish any person,
//        including without limitation, you and your customers, any assistance
//        of any kind whatsoever, or any information; or
//    (c) conferring any license or right under any intellectual property right.
//
// 7. These terms shall be governed by and construed in accordance with the laws
//    of Taiwan, R.O.C., excluding its conflict of law rules.
//    Any and all dispute arising out hereof or related hereto shall be finally
//    settled by arbitration referred to the Chinese Arbitration Association,
//    Taipei in accordance with the ROC Arbitration Law and the Arbitration
//    Rules of the Association by three (3) arbitrators appointed in accordance
//    with the said Rules.
//    The place of arbitration shall be in Taipei, Taiwan and the language shall
//    be English.
//    The arbitration award shall be final and binding to both parties.
//
//******************************************************************************
//<MStar Software>
package mstar.tvsetting.factory.ui.designmenu;

import mstar.factorymenu.ui.R;
import android.app.Activity;
import android.util.Log;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.mstar.android.tv.TvFactoryManager;

public class VD extends Activity {
    private TvFactoryManager mTvFactoryManager = null;

    protected TextView text_factory_nonstandard_afecd4_val;

    protected TextView text_factory_nonstandard_aefcd5_val;

    protected TextView text_factory_nonstandard_aefcd8_val;

    protected TextView text_factory_nonstandard_aefcd9_val;

    protected TextView text_factory_nonstandard_aefcd7_val;

    protected TextView text_factory_nonstandard_aefca0_val;

    protected TextView text_factory_nonstandard_aefca1_val;

    protected TextView text_factory_nonstandard_aefc66_val;

    protected TextView text_factory_nonstandard_aefc6e_val;

    protected TextView text_factory_nonstandard_aefc6elow_val;

    protected TextView text_factory_nonstandard_aefc43_val;

    protected TextView text_factory_nonstandard_aefc44_val;

    protected TextView text_factory_nonstandard_aefccb_val;

    protected TextView text_factory_nonstandard_afeccf_atv_val;

    protected TextView text_factory_nonstandard_afeccf_av_val;

    protected TextView text_factory_nonstandard_audiohidev_val;

    protected TextView text_factory_nonstandard_audionr_val;

    protected TextView text_factory_nonstandard_aefcd7high_val;

    private int afecd4val = 128;

    private int afecd5ival = 128;

    private int afecd8ival = 128;

    private int afecd9ival = 128;

    private int afecd7ival = 128;

    private int afeca0ival = 128;

    private int afeca1ival = 128;

    private int afec66ival = 128;

    private int afec6eival = 128;

    private int afec6elowival = 128;

    private int afec43val = 0;

    private int afec44val = 128;

    private int afeccbval = 128;

    private int afeccfatvval = 128;

    private int afeccfavval = 128;

    private int aefcd7highval = 128;

    private String[] autoAGCMode = {
            "OFF", "ON"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vd);
        findView();
        mTvFactoryManager = TvFactoryManager.getInstance();
        afecd4val = mTvFactoryManager.getAefcD4();
        afecd5ival = mTvFactoryManager.getAefcD5Bit2() >> 2;
        afecd8ival = mTvFactoryManager.getAefcD8Bit3210();
        afecd9ival = mTvFactoryManager.getAefcD9Bit0();
        aefcd7highval = mTvFactoryManager.getAefcD7HighBound();
        afecd7ival = mTvFactoryManager.getAefcD7LowBound();
        afeca0ival = mTvFactoryManager.getAefcA0();
        afeca1ival = mTvFactoryManager.getAefcA1();
        afec66ival = mTvFactoryManager.getAefc66Bit76() >> 6;
        afec6eival = mTvFactoryManager.getAefc6EBit7654() >> 4;
        afec6elowival = mTvFactoryManager.getAefc6EBit3210();
        afec43val = mTvFactoryManager.getAefc43();
        afec44val = mTvFactoryManager.getAefc44();
        afeccbval = mTvFactoryManager.getAefcCb();
        afeccfatvval = mTvFactoryManager.getAefcCfBit2Atv() >> 2;
        afeccfavval = mTvFactoryManager.getAefcCfBit2Av() >> 2;

        text_factory_nonstandard_afecd4_val.setText(Integer.toHexString(afecd4val));
        text_factory_nonstandard_aefcd5_val.setText(Integer.toHexString(afecd5ival));
        text_factory_nonstandard_aefcd8_val.setText(Integer.toHexString(afecd8ival));
        text_factory_nonstandard_aefcd9_val.setText(Integer.toHexString(afecd9ival));
        text_factory_nonstandard_aefcd7_val.setText(Integer.toHexString(afecd7ival));
        text_factory_nonstandard_aefca0_val.setText(Integer.toHexString(afeca0ival));
        text_factory_nonstandard_aefca1_val.setText(Integer.toHexString(afeca1ival));
        text_factory_nonstandard_aefc66_val.setText(Integer.toHexString(afec66ival));
        text_factory_nonstandard_aefc6e_val.setText(Integer.toHexString(afec6eival));
        text_factory_nonstandard_aefc6elow_val.setText(Integer.toHexString(afec6elowival));
        text_factory_nonstandard_aefc43_val.setText(autoAGCMode[afec43val]);
        text_factory_nonstandard_aefc44_val.setText(Integer.toHexString(afec44val));
        text_factory_nonstandard_aefccb_val.setText(Integer.toHexString(afeccbval));
        text_factory_nonstandard_afeccf_atv_val.setText(Integer.toHexString(afeccfatvval));
        text_factory_nonstandard_afeccf_av_val.setText(Integer.toHexString(afeccfavval));
        text_factory_nonstandard_aefcd7high_val.setText(Integer.toHexString(aefcd7highval));

    }

    void findView() {
        text_factory_nonstandard_afecd4_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_afecd4_val);
        text_factory_nonstandard_aefcd5_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefcd5_val);
        text_factory_nonstandard_aefcd8_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefcd8_val);
        text_factory_nonstandard_aefcd9_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefcd9_val);
        text_factory_nonstandard_aefcd7_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefcd7_val);
        text_factory_nonstandard_aefca0_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefca0_val);
        text_factory_nonstandard_aefca1_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefca1_val);
        text_factory_nonstandard_aefc66_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefc66_val);
        text_factory_nonstandard_aefc6e_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefc6e_val);
        text_factory_nonstandard_aefc6elow_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefc6elow_val);
        text_factory_nonstandard_aefc43_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefc43_val);
        text_factory_nonstandard_aefc44_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefc44_val);
        text_factory_nonstandard_aefccb_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefccb_val);
        text_factory_nonstandard_afeccf_atv_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_afeccf_atv_val);
        text_factory_nonstandard_afeccf_av_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_afeccf_av_val);
        text_factory_nonstandard_audiohidev_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_audiohidev_val);
        text_factory_nonstandard_audionr_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_audionr_val);
        text_factory_nonstandard_aefcd7high_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_aefcd7high_val);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = this.getCurrentFocus().getId();
        boolean bValue;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_nonstandard_afecd4:
                        if (afecd4val == 0)
                            afecd4val = 255;
                        else
                            afecd4val--;
                        text_factory_nonstandard_afecd4_val.setText(Integer.toHexString(afecd4val));
                        mTvFactoryManager.setAefcD4((short) afecd4val);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd5:
                        if (afecd5ival == 0)
                            afecd5ival = 1;
                        else
                            afecd5ival--;
                        text_factory_nonstandard_aefcd5_val
                                .setText(Integer.toHexString(afecd5ival));
                        mTvFactoryManager.setAefcD5Bit2((short) (afecd5ival << 2));
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd8:
                        if (afecd8ival == 0)
                            afecd8ival = 15;
                        else
                            afecd8ival--;
                        text_factory_nonstandard_aefcd8_val
                                .setText(Integer.toHexString(afecd8ival));
                        mTvFactoryManager.setAefcD8Bit3210((short) afecd8ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd9:
                        if (afecd9ival == 0)
                            afecd9ival = 1;
                        else
                            afecd9ival--;
                        text_factory_nonstandard_aefcd9_val
                                .setText(Integer.toHexString(afecd9ival));
                        mTvFactoryManager.setAefcD9Bit0((short) afecd9ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd7:
                        if (afecd7ival == 0)
                            afecd7ival = 255;
                        else
                            afecd7ival--;
                        text_factory_nonstandard_aefcd7_val
                                .setText(Integer.toHexString(afecd7ival));
                        mTvFactoryManager.setAefcD7LowBound((short) afecd7ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefca0:
                        if (afeca0ival == 0)
                            afeca0ival = 255;
                        else
                            afeca0ival--;
                        text_factory_nonstandard_aefca0_val
                                .setText(Integer.toHexString(afeca0ival));
                        mTvFactoryManager.setAefcA0((short) afeca0ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefca1:
                        if (afeca1ival == 0)
                            afeca1ival = 255;
                        else
                            afeca1ival--;
                        text_factory_nonstandard_aefca1_val
                                .setText(Integer.toHexString(afeca1ival));
                        mTvFactoryManager.setAefcA1((short) afeca1ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc66:
                        if (afec66ival == 0)
                            afec66ival = 3;
                        else
                            afec66ival--;
                        text_factory_nonstandard_aefc66_val
                                .setText(Integer.toHexString(afec66ival));
                        mTvFactoryManager.setAefc66Bit76((short) (afec66ival << 6));
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc6e:
                        if (afec6eival == 0)
                            afec6eival = 15;
                        else
                            afec6eival--;
                        text_factory_nonstandard_aefc6e_val
                                .setText(Integer.toHexString(afec6eival));
                        mTvFactoryManager.setAefc6EBit7654((short) (afec6eival << 4));
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc6elow:
                        if (afec6elowival == 0)
                            afec6elowival = 15;
                        else
                            afec6elowival--;
                        text_factory_nonstandard_aefc6elow_val.setText(Integer
                                .toHexString(afec6elowival));
                        mTvFactoryManager.setAefc6EBit3210((short) afec6elowival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc43:
                        if (afec43val == 0)
                            afec43val = 1;
                        else
                            afec43val--;
                        text_factory_nonstandard_aefc43_val.setText(autoAGCMode[afec43val]);
                        mTvFactoryManager.setAefc43((short) afec43val);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc44:
                        if (afec44val == 0)
                            afec44val = 255;
                        else
                            afec44val--;
                        text_factory_nonstandard_aefc44_val.setText(Integer.toHexString(afec44val));
                        mTvFactoryManager.setAefc44((short) afec44val);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefccb:
                        if (afeccbval == 0)
                            afeccbval = 255;
                        else
                            afeccbval--;
                        text_factory_nonstandard_aefccb_val.setText(Integer.toHexString(afeccbval));
                        mTvFactoryManager.setAefcCB((short) afeccbval);
                        break;
                    case R.id.linearlayout_factory_nonstandard_afeccf_atv:
                        if (afeccfatvval == 0)
                            afeccfatvval = 1;
                        else
                            afeccfatvval--;
                        text_factory_nonstandard_afeccf_atv_val.setText(Integer
                                .toHexString(afeccfatvval));
                        mTvFactoryManager.setAefcCfBit2Atv((short) (afeccfatvval << 2));
                        break;
                    case R.id.linearlayout_factory_nonstandard_afeccf_av:
                        if (afeccfavval == 0)
                            afeccfavval = 1;
                        else
                            afeccfavval--;
                        text_factory_nonstandard_afeccf_av_val.setText(Integer
                                .toHexString(afeccfavval));
                        mTvFactoryManager.setAefcCfBit2Av((short) (afeccfavval << 2));
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd7high:
                        if (aefcd7highval == 0)
                            aefcd7highval = 255;
                        else
                            aefcd7highval--;
                        text_factory_nonstandard_aefcd7high_val.setText(Integer
                                .toHexString(aefcd7highval));
                        mTvFactoryManager.setAefcD7HighBound((short) aefcd7highval);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_nonstandard_afecd4:
                        if (afecd4val == 255)
                            afecd4val = 0;
                        else
                            afecd4val++;
                        text_factory_nonstandard_afecd4_val.setText(Integer.toHexString(afecd4val));
                        mTvFactoryManager.setAefcD4((short) afecd4val);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd5:
                        if (afecd5ival == 1)// D5[2]
                            afecd5ival = 0;
                        else
                            afecd5ival++;
                        text_factory_nonstandard_aefcd5_val
                                .setText(Integer.toHexString(afecd5ival));
                        mTvFactoryManager.setAefcD5Bit2((short) (afecd5ival << 2));
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd8:
                        if (afecd8ival == 15)// D8[3:0]
                            afecd8ival = 0;
                        else
                            afecd8ival++;
                        text_factory_nonstandard_aefcd8_val
                                .setText(Integer.toHexString(afecd8ival));
                        mTvFactoryManager.setAefcD8Bit3210((short) afecd8ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd9:
                        if (afecd9ival == 1)// D9[0]
                            afecd9ival = 0;
                        else
                            afecd9ival++;
                        text_factory_nonstandard_aefcd9_val
                                .setText(Integer.toHexString(afecd9ival));
                        mTvFactoryManager.setAefcD9Bit0((short) afecd9ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefcd7:
                        if (afecd7ival == 255)
                            afecd7ival = 0;
                        else
                            afecd7ival++;
                        text_factory_nonstandard_aefcd7_val
                                .setText(Integer.toHexString(afecd7ival));
                        mTvFactoryManager.setAefcD7LowBound((short) afecd7ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefca0:
                        if (afeca0ival == 255)
                            afeca0ival = 0;
                        else
                            afeca0ival++;
                        text_factory_nonstandard_aefca0_val
                                .setText(Integer.toHexString(afeca0ival));
                        mTvFactoryManager.setAefcA0((short) afeca0ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefca1:
                        if (afeca1ival == 255)
                            afeca1ival = 0;
                        else
                            afeca1ival++;
                        text_factory_nonstandard_aefca1_val
                                .setText(Integer.toHexString(afeca1ival));
                        mTvFactoryManager.setAefcA1((short) afeca1ival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc66:
                        if (afec66ival == 3)// 66[7:6]
                            afec66ival = 0;
                        else
                            afec66ival++;
                        text_factory_nonstandard_aefc66_val
                                .setText(Integer.toHexString(afec66ival));
                        mTvFactoryManager.setAefc66Bit76((short) (afec66ival << 6));
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc6e:
                        if (afec6eival == 15)// 6E[7:4]
                            afec6eival = 0;
                        else
                            afec6eival++;
                        text_factory_nonstandard_aefc6e_val
                                .setText(Integer.toHexString(afec6eival));
                        mTvFactoryManager.setAefc6EBit7654((short) (afec6eival << 4));
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc6elow:
                        if (afec6elowival == 15)// 6E[3:0]
                            afec6elowival = 0;
                        else
                            afec6elowival++;
                        text_factory_nonstandard_aefc6elow_val.setText(Integer
                                .toHexString(afec6elowival));
                        mTvFactoryManager.setAefc6EBit3210((short) afec6elowival);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc43:
                        if (afec43val == 1)
                            afec43val = 0;
                        else
                            afec43val++;
                        text_factory_nonstandard_aefc43_val.setText(autoAGCMode[afec43val]);
                        mTvFactoryManager.setAefc43((short) afec43val);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefc44:
                        if (afec44val == 255)
                            afec44val = 0;
                        else
                            afec44val++;
                        text_factory_nonstandard_aefc44_val.setText(Integer.toHexString(afec44val));
                        mTvFactoryManager.setAefc44((short) afec44val);
                        break;
                    case R.id.linearlayout_factory_nonstandard_aefccb:
                        if (afeccbval == 255)
                            afeccbval = 0;
                        else
                            afeccbval++;
                        text_factory_nonstandard_aefccb_val.setText(Integer.toHexString(afeccbval));
                        mTvFactoryManager.setAefcCB((short) afeccbval);
                        break;
                    case R.id.linearlayout_factory_nonstandard_afeccf_atv:
                        if (afeccfatvval == 1)
                            afeccfatvval = 0;
                        else
                            afeccfatvval++;
                        text_factory_nonstandard_afeccf_atv_val.setText(Integer
                                .toHexString(afeccfatvval));
                        mTvFactoryManager.setAefcCfBit2Atv((short) (afeccfatvval << 2));
                        break;
                    case R.id.linearlayout_factory_nonstandard_afeccf_av:
                        if (afeccfavval == 1)
                            afeccfavval = 0;
                        else
                            afeccfavval++;
                        text_factory_nonstandard_afeccf_av_val.setText(Integer
                                .toHexString(afeccfavval));
                        mTvFactoryManager.setAefcCfBit2Av((short) (afeccfavval << 2));
                        break;

                    case R.id.linearlayout_factory_nonstandard_aefcd7high:
                        if (aefcd7highval == 255)
                            aefcd7highval = 0;
                        else
                            aefcd7highval++;
                        text_factory_nonstandard_aefcd7high_val.setText(Integer
                                .toHexString(aefcd7highval));
                        mTvFactoryManager.setAefcD7HighBound((short) aefcd7highval);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_MENU:
                finish();
                break;
            default:
                bRet = false;
                break;
        }
        return bRet;
    }
}
