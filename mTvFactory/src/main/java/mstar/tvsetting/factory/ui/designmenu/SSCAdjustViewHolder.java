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
import mstar.tvsetting.factory.desk.IFactoryDesk;
import mstar.tvsetting.factory.ui.designmenu.DesignMenuActivity;
import android.app.Activity;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressWarnings("unused")
public class SSCAdjustViewHolder {
    private DesignMenuActivity sscActivity;

    private IFactoryDesk factoryManager;

    protected TextView text_factory_ssc_miuenable_val;

    protected TextView text_factory_ssc_miumodulation_val;

    protected TextView text_factory_ssc_miupercentage_val;

    protected TextView text_factory_ssc_lvdsenable_val;

    protected TextView text_factory_ssc_lvdsmodulation_val;

    protected TextView text_factory_ssc_lvdspercentage_val;

    // for nikeU
    protected TextView text_factory_ssc_u_miuenable_val;

    protected TextView text_factory_ssc_u_miumodulation_val;

    protected TextView text_factory_ssc_u_miupercentage_val;

    protected TextView text_factory_ssc_u_lvdsenable_val;

    protected TextView text_factory_ssc_u_lvdsmodulation_val;

    protected TextView text_factory_ssc_u_lvdspercentage_val;

    protected TextView text_factory_otheroption_panelswing_val;

    private int miuenableindex;

    private int miumodulation;

    private int miupercentage;

    private int lvdsenableindex;

    private int lvdsmodulation;

    private int lvdspercengtage;

    private double dis;

    private double lvdsModDouble;

    private double lvdsPerDouble;

    private int panelswingval = 0;

    private String[] miuenable = {
            "Off", "On"
    };

    private String[] lvdsenable = {
            "Off", "On"
    };

    // for nikeU
    private int miuenableindex_u;

    private int miumodulation_u;

    private int miupercentage_u;

    private int lvdsenableindex_u;

    private int lvdsmodulation_u;

    private int lvdspercengtage_u;

    private double dis_u;

    private double lvdsModDouble_u;

    private double lvdsPerDouble_u;

    private String[] miuenable_u = {
            "Off", "On"
    };

    private String[] lvdsenable_u = {
            "Off", "On"
    };

    public SSCAdjustViewHolder(DesignMenuActivity activity, IFactoryDesk factoryManager) {
        sscActivity = activity;
        this.factoryManager = factoryManager;
    }

    void findView() {
        text_factory_ssc_miuenable_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_miuenable_val);
        text_factory_ssc_miumodulation_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_miumodulation_val);
        text_factory_ssc_miupercentage_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_miupercentage_val);
        text_factory_ssc_lvdsenable_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_lvdsenable_val);
        text_factory_ssc_lvdsmodulation_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_lvdsmodulation_val);
        text_factory_ssc_lvdspercentage_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_lvdspercentage_val);
        // for nikeU
        text_factory_ssc_u_miuenable_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_u_miuenable_val);
        text_factory_ssc_u_miumodulation_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_u_miumodulation_val);
        text_factory_ssc_u_miupercentage_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_u_miupercentage_val);
        text_factory_ssc_u_lvdsenable_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_u_lvdsenable_val);
        text_factory_ssc_u_lvdsmodulation_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_u_lvdsmodulation_val);
        text_factory_ssc_u_lvdspercentage_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_ssc_u_lvdspercentage_val);
        text_factory_otheroption_panelswing_val = (TextView) sscActivity
                .findViewById(R.id.textview_factory_otheroption_panelswing_val);
        checkIsNikeU();
    }

    private void checkIsNikeU() {
        Log.d("ro.product.chip    ", SystemProperties.get("ro.product.chip"));
        if (!SystemProperties.get("ro.product.chip").contains("u")) {// don't
                                                                     // show
                                                                     // option
                                                                     // for
                                                                     // nikeU
                                                                     // when is
                                                                     // not
                                                                     // nikeU
            sscActivity.findViewById(R.id.linearlayout_factory_ssc_u_miuenable).setVisibility(
                    View.GONE);
            sscActivity.findViewById(R.id.linearlayout_factory_ssc_u_miumodulation).setVisibility(
                    View.GONE);
            sscActivity.findViewById(R.id.linearlayout_factory_ssc_u_miupercentage).setVisibility(
                    View.GONE);
            sscActivity.findViewById(R.id.linearlayout_factory_ssc_u_lvdsenable).setVisibility(
                    View.GONE);
            sscActivity.findViewById(R.id.linearlayout_factory_ssc_u_lvdsmodulation).setVisibility(
                    View.GONE);
            sscActivity.findViewById(R.id.linearlayout_factory_ssc_u_lvdspercentage).setVisibility(
                    View.GONE);
        }
    }

    public boolean onCreate() {
        miuenableindex = factoryManager.getMIUenalbe() ? 1 : 0;
        lvdsenableindex = factoryManager.getLVDSenalbe() ? 1 : 0;
        miupercentage = factoryManager.getMIUpercentage();
        dis = miupercentage / 10.0;
        lvdspercengtage = factoryManager.getLVDSpercentage();
        miumodulation = factoryManager.getMIUmodulation();
        lvdsmodulation = factoryManager.getLVDSmodulation();
        //The purpose is to make the UI display 0x00~0x3f
        panelswingval = (factoryManager.getPanelSwing() - 40) / 10;
        lvdsModDouble = lvdsmodulation / 10.0;
        lvdsPerDouble = lvdspercengtage / 100.0;
        // for nikeU
        miuenableindex_u = factoryManager.getMIUenalbe_u() ? 1 : 0;
        lvdsenableindex_u = factoryManager.getLVDSenalbe_u() ? 1 : 0;
        miupercentage_u = factoryManager.getMIUpercentage_u();
        dis_u = miupercentage_u / 10.0;
        lvdspercengtage_u = factoryManager.getLVDSpercentage_u();
        miumodulation_u = factoryManager.getMIUmodulation_u();
        lvdsmodulation_u = factoryManager.getLVDSmodulation_u();
        lvdsModDouble_u = lvdsmodulation_u / 10.0;
        lvdsPerDouble_u = lvdspercengtage_u / 100.0;
        text_factory_ssc_miuenable_val.setText(miuenable[miuenableindex]);
        text_factory_ssc_lvdsenable_val.setText(miuenable[lvdsenableindex]);
        text_factory_ssc_lvdspercentage_val.setText("" + lvdsPerDouble);
        text_factory_ssc_miupercentage_val.setText("" + dis);
        text_factory_ssc_miumodulation_val.setText(Integer.toString(miumodulation));
        text_factory_ssc_lvdsmodulation_val.setText("" + lvdsModDouble);
        // for nikeU
        text_factory_ssc_u_miuenable_val.setText(miuenable_u[miuenableindex_u]);
        text_factory_ssc_u_lvdsenable_val.setText(miuenable_u[lvdsenableindex_u]);
        text_factory_ssc_u_lvdspercentage_val.setText("" + lvdsPerDouble_u);
        text_factory_ssc_u_miupercentage_val.setText("" + dis_u);
        text_factory_ssc_u_miumodulation_val.setText(Integer.toString(miumodulation_u));
        text_factory_ssc_u_lvdsmodulation_val.setText("" + lvdsModDouble_u);
        text_factory_otheroption_panelswing_val.setText(Integer.toString(panelswingval));
        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = sscActivity.getCurrentFocus().getId();
        String str_val = new String();
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroption_panelswing:
                        if (panelswingval < 63) {
                            panelswingval++;
                        } else if (panelswingval >= 63) {
                            panelswingval = 0;
                        }
                        text_factory_otheroption_panelswing_val.setText(Integer
                                .toString(panelswingval));
                        factoryManager.setPanelSwing((short) (panelswingval * 10 + 40));
                        break;
                    case R.id.linearlayout_factory_ssc_miuenable:
                        if (miuenableindex == 1) {
                            miuenableindex = 0;
                            factoryManager.setMIUenable(false);
                        } else {
                            miuenableindex++;
                            factoryManager.setMIUenable(true);
                        }
                        text_factory_ssc_miuenable_val.setText(miuenable[miuenableindex]);
                        break;
                    case R.id.linearlayout_factory_ssc_miumodulation:
                        if (miumodulation >= 40) {
                            miumodulation = 20;
                        } else {
                            miumodulation++;
                        }

                        text_factory_ssc_miumodulation_val.setText(Integer.toString(miumodulation));
                        factoryManager.setMIUmodulation(miumodulation);
                        break;
                    case R.id.linearlayout_factory_ssc_miupercentage:
                        if (miupercentage >= 10) {
                            miupercentage = 0;
                        } else {
                            miupercentage++;
                        }
                        dis = miupercentage / 10.0;
                        text_factory_ssc_miupercentage_val.setText("" + dis);
                        factoryManager.setMIUpercentage(miupercentage);
                        break;
                    case R.id.linearlayout_factory_ssc_lvdsenable:
                        if (lvdsenableindex == 1) {
                            lvdsenableindex = 0;
                            factoryManager.setLVDSenable(false);
                        } else {
                            lvdsenableindex++;
                            factoryManager.setLVDSenable(true);
                        }
                        text_factory_ssc_lvdsenable_val.setText(lvdsenable[lvdsenableindex]);
                        break;
                    case R.id.linearlayout_factory_ssc_lvdsmodulation:
                        if (lvdsmodulation >= 400) {
                            lvdsmodulation = 200;
                        } else {
                            lvdsmodulation++;
                        }
                        lvdsModDouble = lvdsmodulation / 10.0;
                        text_factory_ssc_lvdsmodulation_val.setText("" + lvdsModDouble);
                        factoryManager.setLVDSmodulation(lvdsmodulation);
                        break;
                    case R.id.linearlayout_factory_ssc_lvdspercentage:
                        if (lvdspercengtage >= 300) {
                            lvdspercengtage = 0;
                        } else {
                            lvdspercengtage += 10;
                        }
                        lvdsPerDouble = lvdspercengtage / 100.0;
                        text_factory_ssc_lvdspercentage_val.setText("" + lvdsPerDouble);
                        factoryManager.setLVDSpercentage(lvdspercengtage);
                        break;

                    // for nikeU
                    case R.id.linearlayout_factory_ssc_u_miuenable:
                        if (miuenableindex_u == 1) {
                            miuenableindex_u = 0;
                            factoryManager.setMIUenable_u(false);
                        } else {
                            miuenableindex_u++;
                            factoryManager.setMIUenable_u(true);
                        }
                        text_factory_ssc_u_miuenable_val.setText(miuenable_u[miuenableindex_u]);
                        break;
                    case R.id.linearlayout_factory_ssc_u_miumodulation:
                        if (miumodulation_u >= 40) {
                            miumodulation_u = 20;
                        } else {
                            miumodulation_u++;
                        }

                        text_factory_ssc_u_miumodulation_val.setText(Integer
                                .toString(miumodulation_u));
                        factoryManager.setMIUmodulation_u(miumodulation_u);
                        break;
                    case R.id.linearlayout_factory_ssc_u_miupercentage:
                        if (miupercentage_u >= 10) {
                            miupercentage_u = 0;
                        } else {
                            miupercentage_u++;
                        }
                        dis_u = miupercentage_u / 10.0;
                        text_factory_ssc_u_miupercentage_val.setText("" + dis_u);
                        factoryManager.setMIUpercentage_u(miupercentage_u);
                        break;
                    case R.id.linearlayout_factory_ssc_u_lvdsenable:
                        if (lvdsenableindex_u == 1) {
                            lvdsenableindex_u = 0;
                            factoryManager.setLVDSenable_u(false);
                        } else {
                            lvdsenableindex_u++;
                            factoryManager.setLVDSenable_u(true);
                        }
                        text_factory_ssc_u_lvdsenable_val.setText(lvdsenable_u[lvdsenableindex_u]);
                        break;
                    case R.id.linearlayout_factory_ssc_u_lvdsmodulation:
                        if (lvdsmodulation_u >= 400) {
                            lvdsmodulation_u = 200;
                        } else {
                            lvdsmodulation_u++;
                        }
                        lvdsModDouble_u = lvdsmodulation_u / 10.0;
                        text_factory_ssc_u_lvdsmodulation_val.setText("" + lvdsModDouble_u);
                        factoryManager.setLVDSmodulation_u(lvdsmodulation_u);
                        break;
                    case R.id.linearlayout_factory_ssc_u_lvdspercentage:
                        if (lvdspercengtage_u >= 300) {
                            lvdspercengtage_u = 0;
                        } else {
                            lvdspercengtage_u += 10;
                        }
                        lvdsPerDouble_u = lvdspercengtage_u / 100.0;
                        text_factory_ssc_u_lvdspercentage_val.setText("" + lvdsPerDouble_u);
                        factoryManager.setLVDSpercentage_u(lvdspercengtage_u);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroption_panelswing:
                        if (panelswingval > 0) {
                            panelswingval--;
                        } else if (panelswingval <= 0) {
                            panelswingval = 63;
                        }
                        text_factory_otheroption_panelswing_val.setText(Integer
                                .toString(panelswingval));
                        // (panelswingval * 10 + 40)--> 40~670 to Utopia interface,
                        // 40 for the register 0x1032 values for 0202
                        factoryManager.setPanelSwing((short) (panelswingval * 10 + 40));
                        break;
                    case R.id.linearlayout_factory_ssc_miuenable:
                        if (miuenableindex == 0) {
                            miuenableindex = 1;
                            factoryManager.setMIUenable(true);
                        } else {
                            miuenableindex--;
                            factoryManager.setMIUenable(false);
                        }
                        text_factory_ssc_miuenable_val.setText(miuenable[miuenableindex]);
                        break;
                    case R.id.linearlayout_factory_ssc_miumodulation:
                        if (miumodulation <= 20) {
                            miumodulation = 40;
                        } else {
                            miumodulation--;
                        }
                        text_factory_ssc_miumodulation_val.setText(Integer.toString(miumodulation));
                        factoryManager.setMIUmodulation(miumodulation);
                        break;
                    case R.id.linearlayout_factory_ssc_miupercentage:
                        if (miupercentage <= 0) {
                            miupercentage = 10;
                        } else {
                            miupercentage--;
                        }
                        dis = miupercentage / 10.0;
                        text_factory_ssc_miupercentage_val.setText("" + dis);
                        factoryManager.setMIUpercentage(miupercentage);
                        break;
                    case R.id.linearlayout_factory_ssc_lvdsenable:
                        if (lvdsenableindex == 0) {
                            lvdsenableindex = 1;
                            factoryManager.setLVDSenable(true);
                        } else {
                            lvdsenableindex--;
                            factoryManager.setLVDSenable(false);
                        }
                        text_factory_ssc_lvdsenable_val.setText(lvdsenable[lvdsenableindex]);
                        break;
                    case R.id.linearlayout_factory_ssc_lvdsmodulation:
                        if (lvdsmodulation <= 200) {
                            lvdsmodulation = 400;
                        } else {
                            lvdsmodulation--;
                        }
                        lvdsModDouble = lvdsmodulation / 10.0;
                        text_factory_ssc_lvdsmodulation_val.setText("" + lvdsModDouble);
                        factoryManager.setLVDSmodulation(lvdsmodulation);
                        break;
                    case R.id.linearlayout_factory_ssc_lvdspercentage:
                        if (lvdspercengtage <= 0) {
                            lvdspercengtage = 300;
                        } else {
                            lvdspercengtage -= 10;
                        }
                        lvdsPerDouble = lvdspercengtage / 100.0;
                        text_factory_ssc_lvdspercentage_val.setText("" + lvdsPerDouble);
                        factoryManager.setLVDSpercentage(lvdspercengtage);
                        break;
                    // for nikeU
                    case R.id.linearlayout_factory_ssc_u_miuenable:
                        if (miuenableindex_u == 0) {
                            miuenableindex_u = 1;
                            factoryManager.setMIUenable_u(true);
                        } else {
                            miuenableindex_u--;
                            factoryManager.setMIUenable_u(false);
                        }
                        text_factory_ssc_u_miuenable_val.setText(miuenable_u[miuenableindex_u]);
                        break;
                    case R.id.linearlayout_factory_ssc_u_miumodulation:
                        if (miumodulation_u <= 20) {
                            miumodulation_u = 40;
                        } else {
                            miumodulation_u--;
                        }
                        text_factory_ssc_u_miumodulation_val.setText(Integer
                                .toString(miumodulation_u));
                        factoryManager.setMIUmodulation_u(miumodulation_u);
                        break;
                    case R.id.linearlayout_factory_ssc_u_miupercentage:
                        if (miupercentage_u <= 0) {
                            miupercentage_u = 10;
                        } else {
                            miupercentage_u--;
                        }
                        dis_u = miupercentage_u / 10.0;
                        text_factory_ssc_u_miupercentage_val.setText("" + dis_u);
                        factoryManager.setMIUpercentage_u(miupercentage_u);
                        break;
                    case R.id.linearlayout_factory_ssc_u_lvdsenable:
                        if (lvdsenableindex_u == 0) {
                            lvdsenableindex_u = 1;
                            factoryManager.setLVDSenable_u(true);
                        } else {
                            lvdsenableindex_u--;
                            factoryManager.setLVDSenable_u(false);
                        }
                        text_factory_ssc_u_lvdsenable_val.setText(lvdsenable_u[lvdsenableindex_u]);
                        break;
                    case R.id.linearlayout_factory_ssc_u_lvdsmodulation:
                        if (lvdsmodulation_u <= 200) {
                            lvdsmodulation_u = 400;
                        } else {
                            lvdsmodulation_u--;
                        }
                        lvdsModDouble_u = lvdsmodulation_u / 10.0;
                        text_factory_ssc_u_lvdsmodulation_val.setText("" + lvdsModDouble_u);
                        factoryManager.setLVDSmodulation_u(lvdsmodulation_u);
                        break;
                    case R.id.linearlayout_factory_ssc_u_lvdspercentage:
                        if (lvdspercengtage_u <= 0) {
                            lvdspercengtage_u = 300;
                        } else {
                            lvdspercengtage_u -= 10;
                        }
                        lvdsPerDouble_u = lvdspercengtage_u / 100.0;
                        text_factory_ssc_u_lvdspercentage_val.setText("" + lvdsPerDouble_u);
                        factoryManager.setLVDSpercentage_u(lvdspercengtage_u);
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
