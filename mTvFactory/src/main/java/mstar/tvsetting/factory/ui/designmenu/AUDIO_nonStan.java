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
import android.widget.TextView;

import com.mstar.android.tv.TvFactoryManager;

public class AUDIO_nonStan extends Activity {
    private TvFactoryManager mTvFactoryManager = null;

    protected TextView text_factory_nonstandard_audiohidev_val;

    protected TextView text_factory_nonstandard_audionr_val;

    protected TextView text_factory_otheroption_audioprescale_val;

    private int audioprescaleval = 0;

    private int audiohidevval = 0;

    private int audionrval = 128;

    private String[] AudioHiDevMode = {
            "OFF", "LV1", "LV2", "LV3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_nonstan);
        mTvFactoryManager = TvFactoryManager.getInstance();
        audiohidevval = mTvFactoryManager.getAudioHiDevMode();
        audionrval = mTvFactoryManager.getAudioNrThreshold();
        findView();
        text_factory_nonstandard_audiohidev_val.setText(AudioHiDevMode[audiohidevval]);
        text_factory_nonstandard_audionr_val.setText(Integer.toHexString(audionrval));
        audioprescaleval = mTvFactoryManager.getAudioPrescale();
        text_factory_otheroption_audioprescale_val.setText("0x" + Integer.toHexString(audioprescaleval));
    }

    void findView() {
        text_factory_nonstandard_audiohidev_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_audiohidev_val);
        text_factory_nonstandard_audionr_val = (TextView) this
                .findViewById(R.id.textview_factory_nonstandard_audionr_val);
        text_factory_otheroption_audioprescale_val = (TextView) this
                .findViewById(R.id.textview_factory_otheroption_audioprescale_val);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = this.getCurrentFocus().getId();
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroption_audioprescale:
                        if (audioprescaleval > 0) {
                            audioprescaleval--;
                        } else {
                            audioprescaleval = 255;
                        }
                        text_factory_otheroption_audioprescale_val.setText("0x" + Integer
                                .toHexString(audioprescaleval));
                        mTvFactoryManager.setAudioPrescale(audioprescaleval);
                        break;
                    case R.id.linearlayout_factory_nonstandard_audiohidev:
                        if (audiohidevval != TvFactoryManager.AUDIO_HIDEV_OFF)
                            audiohidevval--;
                        else
                            audiohidevval = TvFactoryManager.AUDIO_HIDEV_BW_LV3;
                        text_factory_nonstandard_audiohidev_val
                                .setText(AudioHiDevMode[audiohidevval]);
                        mTvFactoryManager.setAudioHiDevMode(audiohidevval);
                        break;
                    case R.id.linearlayout_factory_nonstandard_audionr:
                        if (audionrval != 0)
                            audionrval--;
                        text_factory_nonstandard_audionr_val.setText(Integer
                                .toHexString(audionrval));
                        mTvFactoryManager.setAudioNrThreshold((short) audionrval);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroption_audioprescale:
                        if (audioprescaleval < 255) {
                            audioprescaleval++;
                        } else {
                            audioprescaleval = 0;
                        }
                        text_factory_otheroption_audioprescale_val.setText("0x" + Integer
                                .toHexString(audioprescaleval));
                        mTvFactoryManager.setAudioPrescale(audioprescaleval);
                        break;
                    case R.id.linearlayout_factory_nonstandard_audiohidev:
                        if (audiohidevval != TvFactoryManager.AUDIO_HIDEV_BW_LV3)
                            audiohidevval++;
                        else
                            audiohidevval = TvFactoryManager.AUDIO_HIDEV_OFF;
                        text_factory_nonstandard_audiohidev_val
                                .setText(AudioHiDevMode[audiohidevval]);
                        mTvFactoryManager.setAudioHiDevMode(audiohidevval);
                        break;
                    case R.id.linearlayout_factory_nonstandard_audionr:
                        if (audionrval != 255)
                            audionrval++;
                        text_factory_nonstandard_audionr_val.setText(Integer
                                .toHexString(audionrval));
                        mTvFactoryManager.setAudioNrThreshold((short) audionrval);
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
