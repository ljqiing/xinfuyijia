//<MStar Software>
//******************************************************************************
// MStar Software
// Copyright (c) 2010 - 2015 MStar Semiconductor, Inc. All rights reserved.
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
import mstar.tvsetting.factory.ui.designmenu.DesignMenuActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.mstar.android.tv.TvFactoryManager;

@SuppressWarnings("unused")
public class PEQAdjustViewHolder {
    private TvFactoryManager mTvFactoryManager = null;

    protected TextView text_factory_peq_fo1coarse_val;

    protected TextView text_factory_peq_fo1fine_val;

    protected TextView text_factory_peq_fo2coarse_val;

    protected TextView text_factory_peq_fo2fine_val;

    protected TextView text_factory_peq_fo3coarse_val;

    protected TextView text_factory_peq_fo3fine_val;

    protected TextView text_factory_peq_fo4coarse_val;

    protected TextView text_factory_peq_fo4fine_val;

    protected TextView text_factory_peq_fo5coarse_val;

    protected TextView text_factory_peq_fo5fine_val;

    protected TextView text_factory_peq_gain1_val;

    protected TextView text_factory_peq_gain2_val;

    protected TextView text_factory_peq_gain3_val;

    protected TextView text_factory_peq_gain4_val;

    protected TextView text_factory_peq_gain5_val;

    protected TextView text_factory_peq_q1_val;

    protected TextView text_factory_peq_q2_val;

    protected TextView text_factory_peq_q3_val;

    protected TextView text_factory_peq_q4_val;

    protected TextView text_factory_peq_q5_val;

    private DesignMenuActivity peqActivity;

    private int mFo1CoarseVal = TvFactoryManager.PEQ_ADJUST_COARSE_MAX;

    private int mFo2CoarseVal = TvFactoryManager.PEQ_ADJUST_COARSE_MAX;

    private int mFo3CoarseVal = TvFactoryManager.PEQ_ADJUST_COARSE_MAX;

    private int mFo4CoarseVal = TvFactoryManager.PEQ_ADJUST_COARSE_MAX;

    private int mFo5CoarseVal = TvFactoryManager.PEQ_ADJUST_COARSE_MAX;

    private int mFo1FineVal = TvFactoryManager.PEQ_ADJUST_FINE_MAX;

    private int mFo2FineVal = TvFactoryManager.PEQ_ADJUST_FINE_MAX;

    private int mFo3FineVal = TvFactoryManager.PEQ_ADJUST_FINE_MAX;

    private int mFo4FineVal = TvFactoryManager.PEQ_ADJUST_FINE_MAX;

    private int mFo5FineVal = TvFactoryManager.PEQ_ADJUST_FINE_MAX;

    private int mGain1Val = TvFactoryManager.PEQ_ADJUST_GAIN_MAX;

    private int mGain2Val = TvFactoryManager.PEQ_ADJUST_GAIN_MAX;

    private int mGain3Val = TvFactoryManager.PEQ_ADJUST_GAIN_MAX;

    private int mGain4Val = TvFactoryManager.PEQ_ADJUST_GAIN_MAX;

    private int mGain5Val = TvFactoryManager.PEQ_ADJUST_GAIN_MAX;

    private int mQ1Val = TvFactoryManager.PEQ_ADJUST_QVALUE_MAX;

    private int mQ2Val = TvFactoryManager.PEQ_ADJUST_QVALUE_MAX;

    private int mQ3Val = TvFactoryManager.PEQ_ADJUST_QVALUE_MAX;

    private int mQ4Val = TvFactoryManager.PEQ_ADJUST_QVALUE_MAX;

    private int mQ5Val = TvFactoryManager.PEQ_ADJUST_QVALUE_MAX;

    public PEQAdjustViewHolder(DesignMenuActivity activity) {
        peqActivity = activity;
    }

    void findView() {
        text_factory_peq_fo1coarse_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo1coarse_val);
        text_factory_peq_fo1fine_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo1fine_val);
        text_factory_peq_fo2coarse_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo2coarse_val);
        text_factory_peq_fo2fine_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo2fine_val);
        text_factory_peq_fo3coarse_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo3coarse_val);
        text_factory_peq_fo3fine_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo3fine_val);
        text_factory_peq_fo4coarse_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo4coarse_val);
        text_factory_peq_fo4fine_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo4fine_val);
        text_factory_peq_fo5coarse_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo5coarse_val);
        text_factory_peq_fo5fine_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_fo5fine_val);

        text_factory_peq_gain1_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_gain1_val);
        text_factory_peq_gain2_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_gain2_val);
        text_factory_peq_gain3_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_gain3_val);
        text_factory_peq_gain4_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_gain4_val);
        text_factory_peq_gain5_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_gain5_val);
        text_factory_peq_q1_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_q1_val);
        text_factory_peq_q2_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_q2_val);
        text_factory_peq_q3_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_q3_val);
        text_factory_peq_q4_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_q4_val);
        text_factory_peq_q5_val = (TextView) peqActivity
                .findViewById(R.id.textview_factory_peq_q5_val);
    }

    public void onCreate() {
        mTvFactoryManager = TvFactoryManager.getInstance();

        mFo1CoarseVal = mTvFactoryManager.getPeqFoCoarse(0);
        mFo2CoarseVal = mTvFactoryManager.getPeqFoCoarse(1);
        mFo3CoarseVal = mTvFactoryManager.getPeqFoCoarse(2);
        mFo4CoarseVal = mTvFactoryManager.getPeqFoCoarse(3);
        mFo5CoarseVal = mTvFactoryManager.getPeqFoCoarse(4);
        mFo1FineVal = mTvFactoryManager.getPeqFoFine(0);
        mFo2FineVal = mTvFactoryManager.getPeqFoFine(1);
        mFo3FineVal = mTvFactoryManager.getPeqFoFine(2);
        mFo4FineVal = mTvFactoryManager.getPeqFoFine(3);
        mFo5FineVal = mTvFactoryManager.getPeqFoFine(4);
        mGain1Val = mTvFactoryManager.getPeqGain(0);
        mGain2Val = mTvFactoryManager.getPeqGain(1);
        mGain3Val = mTvFactoryManager.getPeqGain(2);
        mGain4Val = mTvFactoryManager.getPeqGain(3);
        mGain5Val = mTvFactoryManager.getPeqGain(4);
        mQ1Val = mTvFactoryManager.getPeqQ(0);
        mQ2Val = mTvFactoryManager.getPeqQ(1);
        mQ3Val = mTvFactoryManager.getPeqQ(2);
        mQ4Val = mTvFactoryManager.getPeqQ(3);
        mQ5Val = mTvFactoryManager.getPeqQ(4);
        text_factory_peq_fo1coarse_val.setText(Integer.toString(mFo1CoarseVal));
        text_factory_peq_fo2coarse_val.setText(Integer.toString(mFo2CoarseVal));
        text_factory_peq_fo3coarse_val.setText(Integer.toString(mFo3CoarseVal));
        text_factory_peq_fo4coarse_val.setText(Integer.toString(mFo4CoarseVal));
        text_factory_peq_fo5coarse_val.setText(Integer.toString(mFo5CoarseVal));
        text_factory_peq_fo1fine_val.setText(Integer.toString(mFo1FineVal));
        text_factory_peq_fo2fine_val.setText(Integer.toString(mFo2FineVal));
        text_factory_peq_fo3fine_val.setText(Integer.toString(mFo3FineVal));
        text_factory_peq_fo4fine_val.setText(Integer.toString(mFo4FineVal));
        text_factory_peq_fo5fine_val.setText(Integer.toString(mFo5FineVal));
        text_factory_peq_gain1_val.setText(Integer.toString(mGain1Val));
        text_factory_peq_gain2_val.setText(Integer.toString(mGain2Val));
        text_factory_peq_gain3_val.setText(Integer.toString(mGain3Val));
        text_factory_peq_gain4_val.setText(Integer.toString(mGain4Val));
        text_factory_peq_gain5_val.setText(Integer.toString(mGain5Val));
        text_factory_peq_q1_val.setText(Integer.toString(mQ1Val));
        text_factory_peq_q2_val.setText(Integer.toString(mQ2Val));
        text_factory_peq_q3_val.setText(Integer.toString(mQ3Val));
        text_factory_peq_q4_val.setText(Integer.toString(mQ4Val));
        text_factory_peq_q5_val.setText(Integer.toString(mQ5Val));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = -1;
        View view = peqActivity.getCurrentFocus();
        if (null != view) {
            currentid = view.getId();
        }

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_peq_fo1coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MAX > mFo1CoarseVal)
                            mFo1CoarseVal++;
                        text_factory_peq_fo1coarse_val.setText(Integer.toString(mFo1CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(0, mFo1CoarseVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo2coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MAX > mFo2CoarseVal)
                            mFo2CoarseVal++;
                        text_factory_peq_fo2coarse_val.setText(Integer.toString(mFo2CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(1, mFo2CoarseVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo3coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MAX > mFo3CoarseVal)
                            mFo3CoarseVal++;
                        text_factory_peq_fo3coarse_val.setText(Integer.toString(mFo3CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(2, mFo3CoarseVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo4coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MAX > mFo4CoarseVal)
                            mFo4CoarseVal++;
                        text_factory_peq_fo4coarse_val.setText(Integer.toString(mFo4CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(3, mFo4CoarseVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo5coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MAX > mFo5CoarseVal)
                            mFo5CoarseVal++;
                        text_factory_peq_fo5coarse_val.setText(Integer.toString(mFo5CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(4, mFo5CoarseVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo1fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MAX > mFo1FineVal)
                            mFo1FineVal++;
                        text_factory_peq_fo1fine_val.setText(Integer.toString(mFo1FineVal));
                        mTvFactoryManager.setPeqFoFine(0, mFo1FineVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo2fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MAX > mFo2FineVal)
                            mFo2FineVal++;
                        text_factory_peq_fo2fine_val.setText(Integer.toString(mFo2FineVal));
                        mTvFactoryManager.setPeqFoFine(1, mFo2FineVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo3fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MAX > mFo3FineVal)
                            mFo3FineVal++;
                        text_factory_peq_fo3fine_val.setText(Integer.toString(mFo3FineVal));
                        mTvFactoryManager.setPeqFoFine(2, mFo3FineVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo4fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MAX > mFo4FineVal)
                            mFo4FineVal++;
                        text_factory_peq_fo4fine_val.setText(Integer.toString(mFo4FineVal));
                        mTvFactoryManager.setPeqFoFine(3, mFo4FineVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo5fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MAX > mFo5FineVal)
                            mFo5FineVal++;
                        text_factory_peq_fo5fine_val.setText(Integer.toString(mFo5FineVal));
                        mTvFactoryManager.setPeqFoFine(4, mFo5FineVal);
                        break;

                    case R.id.linearlayout_factory_peq_gain1:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MAX > mGain1Val)
                            mGain1Val++;
                        text_factory_peq_gain1_val.setText(Integer.toString(mGain1Val));
                        mTvFactoryManager.setPeqGain(0, mGain1Val);
                        break;
                    case R.id.linearlayout_factory_peq_gain2:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MAX > mGain2Val)
                            mGain2Val++;
                        text_factory_peq_gain2_val.setText(Integer.toString(mGain2Val));
                        mTvFactoryManager.setPeqGain(1, mGain2Val);
                        break;
                    case R.id.linearlayout_factory_peq_gain3:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MAX > mGain3Val)
                            mGain3Val++;
                        text_factory_peq_gain3_val.setText(Integer.toString(mGain3Val));
                        mTvFactoryManager.setPeqGain(2, mGain3Val);
                        break;

                    case R.id.linearlayout_factory_peq_gain4:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MAX > mGain4Val)
                            mGain4Val++;
                        text_factory_peq_gain4_val.setText(Integer.toString(mGain4Val));
                        mTvFactoryManager.setPeqGain(3, mGain4Val);
                        break;

                    case R.id.linearlayout_factory_peq_gain5:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MAX > mGain5Val)
                            mGain5Val++;
                        text_factory_peq_gain5_val.setText(Integer.toString(mGain5Val));
                        mTvFactoryManager.setPeqGain(4, mGain5Val);
                        break;

                    case R.id.linearlayout_factory_peq_q1:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MAX > mQ1Val)
                            mQ1Val++;
                        text_factory_peq_q1_val.setText(Integer.toString(mQ1Val));
                        mTvFactoryManager.setPeqQ(0, mQ1Val);
                        break;
                    case R.id.linearlayout_factory_peq_q2:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MAX > mQ2Val)
                            mQ2Val++;
                        text_factory_peq_q2_val.setText(Integer.toString(mQ2Val));
                        mTvFactoryManager.setPeqQ(1, mQ2Val);
                        break;
                    case R.id.linearlayout_factory_peq_q3:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MAX > mQ3Val)
                            mQ3Val++;
                        text_factory_peq_q3_val.setText(Integer.toString(mQ3Val));
                        mTvFactoryManager.setPeqQ(2, mQ3Val);
                        break;
                    case R.id.linearlayout_factory_peq_q4:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MAX > mQ4Val)
                            mQ4Val++;
                        text_factory_peq_q4_val.setText(Integer.toString(mQ4Val));
                        mTvFactoryManager.setPeqQ(3, mQ4Val);
                        break;
                    case R.id.linearlayout_factory_peq_q5:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MAX > mQ5Val)
                            mQ5Val++;
                        text_factory_peq_q5_val.setText(Integer.toString(mQ5Val));
                        mTvFactoryManager.setPeqQ(4, mQ5Val);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_peq_fo1coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MIN != mFo1CoarseVal)
                            mFo1CoarseVal--;
                        text_factory_peq_fo1coarse_val.setText(Integer.toString(mFo1CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(0, mFo1CoarseVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo2coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MIN != mFo2CoarseVal)
                            mFo2CoarseVal--;
                        text_factory_peq_fo2coarse_val.setText(Integer.toString(mFo2CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(1, mFo2CoarseVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo3coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MIN != mFo3CoarseVal)
                            mFo3CoarseVal--;
                        text_factory_peq_fo3coarse_val.setText(Integer.toString(mFo3CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(2, mFo3CoarseVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo4coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MIN != mFo4CoarseVal)
                            mFo4CoarseVal--;
                        text_factory_peq_fo4coarse_val.setText(Integer.toString(mFo4CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(3, mFo4CoarseVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo5coarse:
                        if (TvFactoryManager.PEQ_ADJUST_COARSE_MIN != mFo5CoarseVal)
                            mFo5CoarseVal--;
                        text_factory_peq_fo5coarse_val.setText(Integer.toString(mFo5CoarseVal));
                        mTvFactoryManager.setPeqFoCoarse(4, mFo5CoarseVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo1fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MIN != mFo1FineVal)
                            mFo1FineVal--;
                        text_factory_peq_fo1fine_val.setText(Integer.toString(mFo1FineVal));
                        mTvFactoryManager.setPeqFoFine(0, mFo1FineVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo2fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MIN != mFo2FineVal)
                            mFo2FineVal--;
                        text_factory_peq_fo2fine_val.setText(Integer.toString(mFo2FineVal));
                        mTvFactoryManager.setPeqFoFine(1, mFo2FineVal);
                        break;
                    case R.id.linearlayout_factory_peq_fo3fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MIN != mFo3FineVal)
                            mFo3FineVal--;
                        text_factory_peq_fo3fine_val.setText(Integer.toString(mFo3FineVal));
                        mTvFactoryManager.setPeqFoFine(2, mFo3FineVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo4fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MIN != mFo4FineVal)
                            mFo4FineVal--;
                        text_factory_peq_fo4fine_val.setText(Integer.toString(mFo4FineVal));
                        mTvFactoryManager.setPeqFoFine(3, mFo4FineVal);
                        break;

                    case R.id.linearlayout_factory_peq_fo5fine:
                        if (TvFactoryManager.PEQ_ADJUST_FINE_MIN != mFo5FineVal)
                            mFo5FineVal--;
                        text_factory_peq_fo5fine_val.setText(Integer.toString(mFo5FineVal));
                        mTvFactoryManager.setPeqFoFine(4, mFo5FineVal);
                        break;

                    case R.id.linearlayout_factory_peq_gain1:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MIN != mGain1Val)
                            mGain1Val--;
                        text_factory_peq_gain1_val.setText(Integer.toString(mGain1Val));
                        mTvFactoryManager.setPeqGain(0, mGain1Val);
                        break;
                    case R.id.linearlayout_factory_peq_gain2:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MIN != mGain2Val)
                            mGain2Val--;
                        text_factory_peq_gain2_val.setText(Integer.toString(mGain2Val));
                        mTvFactoryManager.setPeqGain(1, mGain2Val);
                        break;
                    case R.id.linearlayout_factory_peq_gain3:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MIN != mGain3Val)
                            mGain3Val--;
                        text_factory_peq_gain3_val.setText(Integer.toString(mGain3Val));
                        mTvFactoryManager.setPeqGain(2, mGain3Val);
                        break;

                    case R.id.linearlayout_factory_peq_gain4:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MIN != mGain4Val)
                            mGain4Val--;
                        text_factory_peq_gain4_val.setText(Integer.toString(mGain4Val));
                        mTvFactoryManager.setPeqGain(3, mGain4Val);
                        break;

                    case R.id.linearlayout_factory_peq_gain5:
                        if (TvFactoryManager.PEQ_ADJUST_GAIN_MIN != mGain5Val)
                            mGain5Val--;
                        text_factory_peq_gain5_val.setText(Integer.toString(mGain5Val));
                        mTvFactoryManager.setPeqGain(4, mGain5Val);
                        break;

                    case R.id.linearlayout_factory_peq_q1:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MIN != mQ1Val)
                            mQ1Val--;
                        text_factory_peq_q1_val.setText(Integer.toString(mQ1Val));
                        mTvFactoryManager.setPeqQ(0, mQ1Val);
                        break;
                    case R.id.linearlayout_factory_peq_q2:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MIN != mQ2Val)
                            mQ2Val--;
                        text_factory_peq_q2_val.setText(Integer.toString(mQ2Val));
                        mTvFactoryManager.setPeqQ(1, mQ2Val);
                        break;
                    case R.id.linearlayout_factory_peq_q3:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MIN != mQ3Val)
                            mQ3Val--;
                        text_factory_peq_q3_val.setText(Integer.toString(mQ3Val));
                        mTvFactoryManager.setPeqQ(2, mQ3Val);
                        break;

                    case R.id.linearlayout_factory_peq_q4:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MIN != mQ4Val)
                            mQ4Val--;
                        text_factory_peq_q4_val.setText(Integer.toString(mQ4Val));
                        mTvFactoryManager.setPeqQ(3, mQ4Val);
                        break;

                    case R.id.linearlayout_factory_peq_q5:
                        if (TvFactoryManager.PEQ_ADJUST_QVALUE_MIN != mQ5Val)
                            mQ5Val--;
                        text_factory_peq_q5_val.setText(Integer.toString(mQ5Val));
                        mTvFactoryManager.setPeqQ(4, mQ5Val);
                        break;

                    default:
                        break;
                }
                break;
            default:
                bRet = false;
                break;
        }
        return bRet;
    }
}
