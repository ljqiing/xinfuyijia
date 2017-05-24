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
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

@SuppressWarnings("unused")
public class CISettingViewHolder {
    private DesignMenuActivity ciActivity;

    private IFactoryDesk factoryManager;

    protected LinearLayout linear_factory_ci_debug;

    protected TextView text_factory_ci_credential_mode_val;

    protected TextView text_factory_ci_performance_monitor_val;

    protected TextView text_factory_ci_resource_manager_val;

    protected TextView text_factory_ci_application_infomation_val;

    protected TextView text_factory_ci_conditional_access_support_val;

    protected TextView text_factory_ci_host_control_val;

    protected TextView text_factory_ci_date_time_val;

    protected TextView text_factory_ci_man_machine_interface_val;

    protected TextView text_factory_ci_low_speed_communication_val;

    protected TextView text_factory_ci_content_control_val;

    protected TextView text_factory_ci_host_language_country_val;

    protected TextView text_factory_ci_cam_upgrade_val;

    protected TextView text_factory_ci_operator_profile_val;

    protected TextView text_factory_ci_specific_application_support_val;

    protected TextView text_factory_ci_app_man_machine_interface_val;

    protected TextView text_factory_ci_program_map_table_val;

    protected TextView text_factory_ci_host_service_shunning_val;

    protected TextView text_factory_ci_canal_ready_authentication_val;

    protected TextView text_factory_ci_default_tx_rx_val;

    private int monitorenableindex;

    private TextView[] cidebugviewlist = {
            null, null, null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null
    };

    private int[] cidebuglevel = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    private String[] monitornable = {
            "Off", "On"
    };

    private final int CI_FUNCTION_DEBUG_COUNT = 17;

    public CISettingViewHolder(DesignMenuActivity activity, IFactoryDesk factoryManager) {
        ciActivity = activity;
        this.factoryManager = factoryManager;
    }

    void findView() {
        linear_factory_ci_debug = (LinearLayout) ciActivity
                .findViewById(R.id.linearlayout_factory_ci_debug);
        text_factory_ci_credential_mode_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_credential_mode_val);
        text_factory_ci_performance_monitor_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_performance_monitor_val);
        cidebugviewlist[0] = text_factory_ci_resource_manager_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_resource_manager_val);
        cidebugviewlist[1] = text_factory_ci_application_infomation_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_application_infomation_val);
        cidebugviewlist[2] = text_factory_ci_conditional_access_support_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_conditional_access_support_val);
        cidebugviewlist[3] = text_factory_ci_host_control_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_host_control_val);
        cidebugviewlist[4] = text_factory_ci_date_time_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_date_time_val);
        cidebugviewlist[5] = text_factory_ci_man_machine_interface_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_man_machine_interface_val);
        cidebugviewlist[6] = text_factory_ci_low_speed_communication_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_low_speed_communication_val);
        cidebugviewlist[7] = text_factory_ci_content_control_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_content_control_val);
        cidebugviewlist[8] = text_factory_ci_host_language_country_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_host_language_country_val);
        cidebugviewlist[9] = text_factory_ci_cam_upgrade_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_cam_upgrade_val);
        cidebugviewlist[10] = text_factory_ci_operator_profile_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_operator_profile_val);
        cidebugviewlist[11] = text_factory_ci_specific_application_support_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_specific_application_support_val);
        cidebugviewlist[12] = text_factory_ci_app_man_machine_interface_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_app_man_machine_interface_val);
        cidebugviewlist[13] = text_factory_ci_program_map_table_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_program_map_table_val);
        cidebugviewlist[14] = text_factory_ci_host_service_shunning_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_host_service_shunning_val);
        cidebugviewlist[15] = text_factory_ci_canal_ready_authentication_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_canal_ready_authentication_val);
        cidebugviewlist[16] = text_factory_ci_default_tx_rx_val = (TextView) ciActivity
                .findViewById(R.id.textview_factory_ci_default_tx_rx_val);
    }

    public void onCreate() {
        monitorenableindex = factoryManager.getPerformanceMonitor() ? 1 : 0;
        text_factory_ci_performance_monitor_val.setText(monitornable[monitorenableindex]);
        for (int i = 0; i < CI_FUNCTION_DEBUG_COUNT; i++) {
            cidebuglevel[i] = factoryManager.getCIDebugLevel(i);
        }
        text_factory_ci_resource_manager_val.setText(Integer.toString(cidebuglevel[0]));
        text_factory_ci_application_infomation_val.setText(Integer.toString(cidebuglevel[1]));
        text_factory_ci_conditional_access_support_val.setText(Integer.toString(cidebuglevel[2]));
        text_factory_ci_host_control_val.setText(Integer.toString(cidebuglevel[3]));
        text_factory_ci_date_time_val.setText(Integer.toString(cidebuglevel[4]));
        text_factory_ci_man_machine_interface_val.setText(Integer.toString(cidebuglevel[5]));
        text_factory_ci_low_speed_communication_val.setText(Integer.toString(cidebuglevel[6]));
        text_factory_ci_content_control_val.setText(Integer.toString(cidebuglevel[7]));
        text_factory_ci_host_language_country_val.setText(Integer.toString(cidebuglevel[8]));
        text_factory_ci_cam_upgrade_val.setText(Integer.toString(cidebuglevel[9]));
        text_factory_ci_operator_profile_val.setText(Integer.toString(cidebuglevel[10]));
        text_factory_ci_specific_application_support_val
                .setText(Integer.toString(cidebuglevel[11]));
        text_factory_ci_app_man_machine_interface_val.setText(Integer.toString(cidebuglevel[12]));
        text_factory_ci_program_map_table_val.setText(Integer.toString(cidebuglevel[13]));
        text_factory_ci_host_service_shunning_val.setText(Integer.toString(cidebuglevel[14]));
        text_factory_ci_canal_ready_authentication_val.setText(Integer.toString(cidebuglevel[15]));
        text_factory_ci_default_tx_rx_val.setText(Integer.toString(cidebuglevel[16]));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = ciActivity.getCurrentFocus().getId();
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_ci_performance_monitor:
                        if (monitorenableindex == 1) {
                            monitorenableindex = 0;
                            factoryManager.enablePerformanceMonitor(false);
                        } else {
                            monitorenableindex++;
                            factoryManager.enablePerformanceMonitor(true);
                        }
                        text_factory_ci_performance_monitor_val
                                .setText(monitornable[monitorenableindex]);
                        break;
                    case R.id.linearlayout_factory_ci_resource_manager:
                    case R.id.linearlayout_factory_ci_application_infomation:
                    case R.id.linearlayout_factory_ci_conditional_access_support:
                    case R.id.linearlayout_factory_ci_host_control:
                    case R.id.linearlayout_factory_ci_date_time:
                    case R.id.linearlayout_factory_ci_man_machine_interface:
                    case R.id.linearlayout_factory_ci_low_speed_communication:
                    case R.id.linearlayout_factory_ci_content_control:
                    case R.id.linearlayout_factory_ci_host_language_country:
                    case R.id.linearlayout_factory_ci_cam_upgrade:
                    case R.id.linearlayout_factory_ci_operator_profile:
                    case R.id.linearlayout_factory_ci_specific_application_support:
                    case R.id.linearlayout_factory_ci_app_man_machine_interface:
                    case R.id.linearlayout_factory_ci_program_map_table:
                    case R.id.linearlayout_factory_ci_host_service_shunning:
                    case R.id.linearlayout_factory_ci_canal_ready_authentication:
                    case R.id.linearlayout_factory_ci_default_tx_rx:
                        View focusedChild = linear_factory_ci_debug.getFocusedChild();
                        int index = linear_factory_ci_debug.indexOfChild(focusedChild);
                        if (cidebuglevel[index] == 4) {
                            cidebuglevel[index] = 0;
                        } else {
                            cidebuglevel[index]++;
                        }
                        factoryManager.setCIDebugLevel(index, cidebuglevel[index]);
                        cidebugviewlist[index].setText(Integer.toString(cidebuglevel[index]));
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_ci_performance_monitor:
                        if (monitorenableindex == 0) {
                            monitorenableindex = 1;
                            factoryManager.enablePerformanceMonitor(true);
                        } else {
                            monitorenableindex--;
                            factoryManager.enablePerformanceMonitor(false);
                        }
                        text_factory_ci_performance_monitor_val
                                .setText(monitornable[monitorenableindex]);
                        break;
                    case R.id.linearlayout_factory_ci_resource_manager:
                    case R.id.linearlayout_factory_ci_application_infomation:
                    case R.id.linearlayout_factory_ci_conditional_access_support:
                    case R.id.linearlayout_factory_ci_host_control:
                    case R.id.linearlayout_factory_ci_date_time:
                    case R.id.linearlayout_factory_ci_man_machine_interface:
                    case R.id.linearlayout_factory_ci_low_speed_communication:
                    case R.id.linearlayout_factory_ci_content_control:
                    case R.id.linearlayout_factory_ci_host_language_country:
                    case R.id.linearlayout_factory_ci_cam_upgrade:
                    case R.id.linearlayout_factory_ci_operator_profile:
                    case R.id.linearlayout_factory_ci_specific_application_support:
                    case R.id.linearlayout_factory_ci_app_man_machine_interface:
                    case R.id.linearlayout_factory_ci_program_map_table:
                    case R.id.linearlayout_factory_ci_host_service_shunning:
                    case R.id.linearlayout_factory_ci_canal_ready_authentication:
                    case R.id.linearlayout_factory_ci_default_tx_rx:
                        View focusedChild = linear_factory_ci_debug.getFocusedChild();
                        int index = linear_factory_ci_debug.indexOfChild(focusedChild);
                        if (cidebuglevel[index] <= 0) {
                            cidebuglevel[index] = 4;
                        } else {
                            cidebuglevel[index]--;
                        }
                        factoryManager.setCIDebugLevel(index, cidebuglevel[index]);
                        cidebugviewlist[index].setText(Integer.toString(cidebuglevel[index]));
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
