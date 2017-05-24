//<MStar Software>
//******************************************************************************
// MStar Software
// Copyright (c) 2010 - 2013 MStar Semiconductor, Inc. All rights reserved.
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DesignMenuViewHolder {
    private DesignMenuActivity factoryactivity;

    protected LinearLayout linear_factory_menu;

    protected LinearLayout linear_factory_picturemode;

	//qwh add mac 
	protected LinearLayout linear_factory_macwrite;
    //qwh add end 
	
    protected LinearLayout linear_factory_nonstandard;

    protected LinearLayout linear_factory_non_linear;

    protected LinearLayout linear_factory_ssc;

    protected LinearLayout linear_factory_peq;

    protected LinearLayout linear_factory_ursa_info;

    protected LinearLayout linear_factory_panel_info;

    protected LinearLayout linear_factory_pqtableupdate;

    protected LinearLayout linear_factory_mountconfig;

    protected LinearLayout linear_design_otheroption;

    protected LinearLayout linear_design_ipenablemapping;

    protected LinearLayout linear_factory_ci_setting;

    protected TextView text_factory_mountconfig_val;

    protected TextView text_factory_pqtableupdate_val;

    protected int mountConfigIndex = 0;

    protected int pqTableUpdateIndex = 0;

    private String[] mountConfigStrs;

    private String[] pqTableUpdateStrs;

    private static final int MOUNTCONFIGNUM = 2;

    private static final int PQTABLEUPDATENUM = 4;

    public DesignMenuViewHolder(DesignMenuActivity activity) {
        factoryactivity = activity;
    }

    void findView() {
        linear_factory_menu = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factorymenu);
		//qwh add for mac write 
		linear_factory_macwrite = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_macwrite);
		//qwh add end
        linear_factory_picturemode = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_picturemode);
        linear_factory_nonstandard = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_nonstandard);
        linear_factory_non_linear = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_non_linear);
        linear_factory_ssc = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_ssc);
        linear_factory_peq = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_peq);
        linear_factory_ursa_info = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_ursa_info);
        linear_factory_panel_info = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_panel_info);
        linear_factory_mountconfig = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_mountconfig);
        linear_factory_pqtableupdate = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_pqtableupdate);
        linear_design_otheroption = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_design_otheroption);
        linear_design_ipenablemapping = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_IP_Enable_Mapping);
        linear_factory_ci_setting = (LinearLayout) factoryactivity
                .findViewById(R.id.linearlayout_factory_ci_setting);
        text_factory_mountconfig_val = (TextView) factoryactivity
                .findViewById(R.id.textview_factory_mountconfig_val);
        text_factory_pqtableupdate_val = (TextView) factoryactivity
                .findViewById(R.id.textview_factory_pqtableupdate_val);
        mountConfigStrs = factoryactivity.getResources().getStringArray(
                R.array.str_factory_mountconfig_val);
        pqTableUpdateStrs = factoryactivity.getResources().getStringArray(
                R.array.str_factory_pqtableupdate_val);
        text_factory_mountconfig_val.setText(mountConfigStrs[mountConfigIndex]);
        text_factory_pqtableupdate_val.setText(pqTableUpdateStrs[pqTableUpdateIndex]);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int currentid = -1;
        View view = factoryactivity.getCurrentFocus();
        if (null != view) {
            currentid = view.getId();
        }
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_mountconfig:
                        mountConfigIndex = (mountConfigIndex + 1) % MOUNTCONFIGNUM;
                        text_factory_mountconfig_val.setText(mountConfigStrs[mountConfigIndex]);
                        break;
                    case R.id.linearlayout_factory_pqtableupdate:
                        pqTableUpdateIndex = (pqTableUpdateIndex + 1) % PQTABLEUPDATENUM;
                        text_factory_pqtableupdate_val
                                .setText(pqTableUpdateStrs[pqTableUpdateIndex]);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_mountconfig:
                        if (mountConfigIndex != 0)
                            mountConfigIndex--;
                        else
                            mountConfigIndex = MOUNTCONFIGNUM - 1;
                        text_factory_mountconfig_val.setText(mountConfigStrs[mountConfigIndex]);
                        break;
                    case R.id.linearlayout_factory_pqtableupdate:
                        if (pqTableUpdateIndex != 0)
                            pqTableUpdateIndex--;
                        else
                            pqTableUpdateIndex = PQTABLEUPDATENUM - 1;
                        text_factory_pqtableupdate_val
                                .setText(pqTableUpdateStrs[pqTableUpdateIndex]);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
