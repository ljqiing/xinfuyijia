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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mstar.android.tv.TvChannelManager;
import com.mstar.android.tv.TvFactoryManager;

public class VIF extends Activity {
    private TvFactoryManager mTvFactoryManager = null;

    private TvChannelManager mTvChannelManager = null;

    private static final int BASE_SCRAMBLE_BOX_DELAY_VAL = 418;

    private static final int MAX_SCRAMBLE_BOX_DELAY_VAL = 468;

    private static final int MIN_SCRAMBLE_BOX_DELAY_VAL = 368;

    private static final int DEFAULT_AGC_REF_VAL = 0x60;

    private static final int MIN_AGC_REF_VAL = 0x20;

    private static final int MAX_AGC_REF_VAL = 0x80;

    private static final int DEFAULT_CLAMPGAIN_NEG_VAL = 0x600;

    private static final int MIN_CLAMPGAIN_NEG_VAL = 0;

    private static final int MAX_CLAMPGAIN_NEG_VAL = 0x7ff;

    private static final int DEFAULT_CR_THR_VAL = 0x500;

    private static final int MIN_CR_THR_VAL = 0x70;

    private static final int MAX_CR_THR_VAL = 0x500;

    private static final int DEFAULT_DELAY_VAL = 0;

    private static final int MIN_DELAY_VAL = 0;

    private static final int MAX_DELAY_VAL = 100;

    protected TextView text_factory_nonstandard_viftop_val;

    protected TextView text_factory_nonstandard_vifcpkp_val;

    protected TextView text_factory_nonstandard_vifcpki_val;

    protected TextView text_factory_nonstandard_vifasiasignaloption_val;

    protected TextView text_factory_nonstandard_vifcpkpkiadjust_val;

    protected TextView text_factory_nonstandard_vifovermodulation_val;

    protected TextView text_factory_nonstandard_vifclampgain_val;

    protected TextView text_factory_nonstandard_chinadescrambler_val;

    protected TextView text_factory_nonstandard_chinadescrambler_delay_val;

    protected TextView text_factory_nonstandard_delay_val;

    protected TextView text_factory_nonstandard_vifcrthr_val;

    protected TextView text_factory_nonstandard_vifversion_val;

    protected TextView text_factory_nonstandard_vifagcref_val;

    private int mVifTopVal = 128;

    private int mVifCpKpVal = 128;

    private int mVifCpKiVal = 128;

    private int mVifAsiaVal = 128;

    private int mVifCpKpKiVal = 128;

    private int mViFoverVal = 128;

    private int mVifClampVal = DEFAULT_CLAMPGAIN_NEG_VAL;

    private int mChinaAdeDeLayVal = BASE_SCRAMBLE_BOX_DELAY_VAL;

    private double mChinaAdeDeLayUiVal = 0;

    private int mDelayVal = DEFAULT_DELAY_VAL;

    private int mVifCrThreshold = DEFAULT_CR_THR_VAL;

    private int mVifVersionVal = 128;

    private int mVifAgcVal = DEFAULT_AGC_REF_VAL;

    private LinearLayout linearlayout_chinadescrambler_delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vif);
        findView();
        mTvFactoryManager = TvFactoryManager.getInstance();
        mTvChannelManager = TvChannelManager.getInstance();
        mVifTopVal = mTvFactoryManager.getVifTop();
        mVifCpKpVal = mTvFactoryManager.getVifCrKp();
        mVifCpKiVal = mTvFactoryManager.getVifCrKi();
        mVifAsiaVal = mTvFactoryManager.getVifAsiaSignalOption() ? 1 : 0;
        mVifCpKpKiVal = mTvFactoryManager.getVifCrKpKiAdjust() ? 1 : 0;
        mViFoverVal = mTvFactoryManager.getVifOverModulation() ? 1 : 0;
        mVifClampVal = mTvFactoryManager.getVifClampGainOvNegative();
        mChinaAdeDeLayVal = (int)mTvFactoryManager.getChinaDescramblerBoxDelay();
        mChinaAdeDeLayUiVal = mChinaAdeDeLayVal - BASE_SCRAMBLE_BOX_DELAY_VAL;
        mDelayVal = mTvFactoryManager.getDelayReduce();
        mVifCrThreshold = mTvFactoryManager.getVifCrThreshold();
        mVifVersionVal = mTvFactoryManager.getVifVersion();
        mVifAgcVal = mTvFactoryManager.getVifAgcRef();

        text_factory_nonstandard_viftop_val.setText(Integer.toHexString(mVifTopVal));
        text_factory_nonstandard_vifcpkp_val.setText(Integer.toHexString(mVifCpKpVal));
        text_factory_nonstandard_vifcpki_val.setText(Integer.toHexString(mVifCpKiVal));
        text_factory_nonstandard_vifasiasignaloption_val.setText(mVifAsiaVal == 0 ? "OFF" : "ON");
        text_factory_nonstandard_vifcpkpkiadjust_val.setText(Integer.toHexString(mVifCpKpKiVal));
        text_factory_nonstandard_vifovermodulation_val.setText(Integer.toHexString(mViFoverVal));
        text_factory_nonstandard_vifclampgain_val.setText(Integer.toHexString(mVifClampVal));
        if (mChinaAdeDeLayVal > MAX_SCRAMBLE_BOX_DELAY_VAL || mChinaAdeDeLayVal < MIN_SCRAMBLE_BOX_DELAY_VAL) {
            mChinaAdeDeLayVal = BASE_SCRAMBLE_BOX_DELAY_VAL;
            mChinaAdeDeLayUiVal = (double)(mChinaAdeDeLayVal - BASE_SCRAMBLE_BOX_DELAY_VAL);
            mTvFactoryManager.setChinaDescramblerBoxDelay(mChinaAdeDeLayVal);
        } else {
            mTvFactoryManager.setChinaDescramblerBoxDelay(mChinaAdeDeLayVal);
        }
        mTvChannelManager.selectProgram(mTvChannelManager.getCurrentChannelNumber(),
                TvChannelManager.SERVICE_TYPE_ATV);
        text_factory_nonstandard_chinadescrambler_delay_val.setText(split((Double.toString(mChinaAdeDeLayUiVal/10))));
        text_factory_nonstandard_delay_val.setText(Integer.toHexString(mDelayVal));
        text_factory_nonstandard_vifcrthr_val.setText(Integer.toHexString(mVifCrThreshold));
        text_factory_nonstandard_vifversion_val.setText(Integer.toHexString(mVifVersionVal));
        text_factory_nonstandard_vifagcref_val.setText(Integer.toHexString(mVifAgcVal));
    }

    private String split(String params) {
        if (params != null && params.indexOf(".") != -1) {
            params = params.substring(0, params.indexOf(".") + 2);
        }
        return params;
    }

    void findView() {
        linearlayout_chinadescrambler_delay = (LinearLayout) findViewById(R.id.linearlayout_factory_nonstandard_chinadescrambler_delay);
        text_factory_nonstandard_viftop_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_viftop_val);
        text_factory_nonstandard_vifcpkp_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifcpkp_val);
        text_factory_nonstandard_vifcpki_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifcpki_val);
        text_factory_nonstandard_vifasiasignaloption_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifasiasignaloption_val);
        text_factory_nonstandard_vifcpkpkiadjust_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifcpkpkiadjust_val);
        text_factory_nonstandard_vifovermodulation_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifovermodulation_val);
        text_factory_nonstandard_vifclampgain_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifclampgain_val);
        text_factory_nonstandard_chinadescrambler_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_chinadescrambler_val);
        text_factory_nonstandard_chinadescrambler_delay_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_chinadescrambler_delay_val);
        text_factory_nonstandard_delay_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_delay_val);
        text_factory_nonstandard_vifcrthr_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifcrthr_val);
        text_factory_nonstandard_vifversion_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifversion_val);
        text_factory_nonstandard_vifagcref_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_vifagcref_val);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = this.getCurrentFocus().getId();
        boolean bValue;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_nonstandard_viftop:
                        if (mVifTopVal != 0)
                            mVifTopVal--;
                        text_factory_nonstandard_viftop_val.setText(Integer.toHexString(mVifTopVal));
                        mTvFactoryManager.setVifTop((short) mVifTopVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcpkp:
                        if (mVifCpKpVal != 0)
                            mVifCpKpVal--;
                        text_factory_nonstandard_vifcpkp_val.setText(Integer
                                .toHexString(mVifCpKpVal));
                        mTvFactoryManager.setVifCrKp((short) mVifCpKpVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcpki:
                        if (mVifCpKiVal != 0)
                            mVifCpKiVal--;
                        text_factory_nonstandard_vifcpki_val.setText(Integer
                                .toHexString(mVifCpKiVal));
                        mTvFactoryManager.setVifCrKi((short) mVifCpKiVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifasiasignaloption:
                        if (mVifAsiaVal == 0)
                            mVifAsiaVal = 1;
                        else
                            mVifAsiaVal = 0;
                        text_factory_nonstandard_vifasiasignaloption_val
                                .setText(mVifAsiaVal == 0 ? "OFF" : "ON");
                        bValue = (mVifAsiaVal > 0) ? true : false;
                        mTvFactoryManager.setVifAsiaSignalOption(bValue);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcpkpkiadjust:
                        if (mVifCpKpKiVal == 0)
                            mVifCpKpKiVal = 1;
                        else
                            mVifCpKpKiVal = 0;
                        text_factory_nonstandard_vifcpkpkiadjust_val.setText(Integer
                                .toHexString(mVifCpKpKiVal));
                        bValue = (mVifCpKpKiVal > 0) ? true : false;
                        mTvFactoryManager.setVifCrKpKiAdjust(bValue);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifovermodulation:
                        if (mViFoverVal == 0)
                            mViFoverVal = 1;
                        else
                            mViFoverVal = 0;
                        text_factory_nonstandard_vifovermodulation_val.setText(Integer
                                .toHexString(mViFoverVal));
                        bValue = (mViFoverVal > 0) ? true : false;
                        mTvFactoryManager.setVifOverModulation(bValue);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifclampgain:
                        if (mVifClampVal > MIN_CLAMPGAIN_NEG_VAL)
                            mVifClampVal--;
                        text_factory_nonstandard_vifclampgain_val.setText(Integer
                                .toHexString(mVifClampVal));
                        mTvFactoryManager.setVifClampGainOvNegative(mVifClampVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_chinadescrambler:
                        break;
                    case R.id.linearlayout_factory_nonstandard_chinadescrambler_delay:
                        if (mChinaAdeDeLayVal > MIN_SCRAMBLE_BOX_DELAY_VAL) {
                            mChinaAdeDeLayVal -= 1;
                            mChinaAdeDeLayUiVal -= 1;
                            text_factory_nonstandard_chinadescrambler_delay_val.setText(split((Double.toString(mChinaAdeDeLayUiVal/10))));
                            mTvFactoryManager.setChinaDescramblerBoxDelay(mChinaAdeDeLayVal);
                            mTvChannelManager.selectProgram(mTvChannelManager.getCurrentChannelNumber(),
                                    TvChannelManager.SERVICE_TYPE_ATV);
                        }
                        break;
                    case R.id.linearlayout_factory_nonstandard_delay:
                        if (mDelayVal > MIN_DELAY_VAL)
                            mDelayVal--;
                        text_factory_nonstandard_delay_val.setText(Integer.toHexString(mDelayVal));
                        mTvFactoryManager.setDelayReduce((short) mDelayVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcrthr:
                        if (mVifCrThreshold > MIN_CR_THR_VAL)
                            mVifCrThreshold--;
                        this.text_factory_nonstandard_vifcrthr_val.setText(Integer
                                .toHexString(mVifCrThreshold));
                        mTvFactoryManager.setVifCrThreshold(mVifCrThreshold);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifversion:
                        if (mVifVersionVal != 0)
                            mVifVersionVal--;
                        text_factory_nonstandard_vifversion_val.setText(Integer
                                .toHexString(mVifVersionVal));
                        mTvFactoryManager.setVifVersion((short) mVifVersionVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifagcref:
                        if (mVifAgcVal > MIN_AGC_REF_VAL)
                            mVifAgcVal--;
                        this.text_factory_nonstandard_vifagcref_val.setText(Integer
                                .toHexString(mVifAgcVal));
                        mTvFactoryManager.setVifAgcRef((short) mVifAgcVal);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_nonstandard_viftop:
                        if (mVifTopVal != 255)
                            mVifTopVal++;
                        text_factory_nonstandard_viftop_val.setText(Integer.toHexString(mVifTopVal));
                        mTvFactoryManager.setVifTop((short) mVifTopVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcpkp:
                        if (mVifCpKpVal != 255)
                            mVifCpKpVal++;
                        text_factory_nonstandard_vifcpkp_val.setText(Integer
                                .toHexString(mVifCpKpVal));
                        mTvFactoryManager.setVifCrKp((short) mVifCpKpVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcpki:
                        if (mVifCpKiVal != 255)
                            mVifCpKiVal++;
                        text_factory_nonstandard_vifcpki_val.setText(Integer
                                .toHexString(mVifCpKiVal));
                        mTvFactoryManager.setVifCrKi((short) mVifCpKiVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifasiasignaloption:
                        if (mVifAsiaVal == 0)
                            mVifAsiaVal = 1;
                        else
                            mVifAsiaVal = 0;
                        text_factory_nonstandard_vifasiasignaloption_val
                                .setText(mVifAsiaVal == 0 ? "OFF" : "ON");
                        bValue = ((mVifAsiaVal > 0) ? true : false);
                        mTvFactoryManager.setVifAsiaSignalOption(bValue);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcpkpkiadjust:
                        if (mVifCpKpKiVal == 0)
                            mVifCpKpKiVal = 1;
                        else
                            mVifCpKpKiVal = 0;
                        text_factory_nonstandard_vifcpkpkiadjust_val.setText(Integer
                                .toHexString(mVifCpKpKiVal));
                        bValue = (mVifCpKpKiVal > 0) ? true : false;
                        mTvFactoryManager.setVifCrKpKiAdjust(bValue);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifovermodulation:
                        if (mViFoverVal == 0)
                            mViFoverVal = 1;
                        else
                            mViFoverVal = 0;
                        text_factory_nonstandard_vifovermodulation_val.setText(Integer
                                .toHexString(mViFoverVal));
                        bValue = ((mViFoverVal > 0) ? true : false);
                        mTvFactoryManager.setVifOverModulation(bValue);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifclampgain:
                        if (mVifClampVal < MAX_CLAMPGAIN_NEG_VAL)
                            mVifClampVal++;
                        text_factory_nonstandard_vifclampgain_val.setText(Integer
                                .toHexString(mVifClampVal));
                        mTvFactoryManager.setVifClampGainOvNegative(mVifClampVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_chinadescrambler:
                        break;
                    case R.id.linearlayout_factory_nonstandard_chinadescrambler_delay:
                        if (mChinaAdeDeLayVal < MAX_SCRAMBLE_BOX_DELAY_VAL) {
                            mChinaAdeDeLayVal += 1;
                            mChinaAdeDeLayUiVal += 1;
                            text_factory_nonstandard_chinadescrambler_delay_val.setText(split((Double.toString(mChinaAdeDeLayUiVal/10))));
                            mTvFactoryManager.setChinaDescramblerBoxDelay(mChinaAdeDeLayVal);
                            mTvChannelManager.selectProgram(mTvChannelManager.getCurrentChannelNumber(),
                                    TvChannelManager.SERVICE_TYPE_ATV);
                        }
                        break;
                    case R.id.linearlayout_factory_nonstandard_delay:
                        if (mDelayVal < MAX_DELAY_VAL)
                            mDelayVal++;
                        text_factory_nonstandard_delay_val.setText(Integer.toHexString(mDelayVal));
                        mTvFactoryManager.setDelayReduce((short) mDelayVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifcrthr:
                        if (mVifCrThreshold < MAX_CR_THR_VAL)
                            mVifCrThreshold++;
                        text_factory_nonstandard_vifcrthr_val
                                .setText(Integer.toHexString(mVifCrThreshold));
                        mTvFactoryManager.setVifCrThreshold(mVifCrThreshold);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifversion:
                        if (mVifVersionVal != 255)
                            mVifVersionVal++;
                        text_factory_nonstandard_vifversion_val.setText(Integer
                                .toHexString(mVifVersionVal));
                        mTvFactoryManager.setVifVersion((short) mVifVersionVal);
                        break;
                    case R.id.linearlayout_factory_nonstandard_vifagcref:
                        if (mVifAgcVal < MAX_AGC_REF_VAL)
                            mVifAgcVal++;
                        text_factory_nonstandard_vifagcref_val.setText(Integer
                                .toHexString(mVifAgcVal));
                        mTvFactoryManager.setVifAgcRef((short) mVifAgcVal);
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
