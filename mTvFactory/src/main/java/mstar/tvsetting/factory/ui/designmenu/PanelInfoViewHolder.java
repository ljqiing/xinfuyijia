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

import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.view.View;
import android.view.View.*;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.CheckBox;
import android.widget.*;

import com.mstar.android.tv.TvFactoryManager;
import com.mstar.android.tvapi.factory.vo.PanelVersionInfo;
import mstar.tvsetting.factory.ui.designmenu.DesignMenuActivity;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.EnumPowerOnLogoMode;
import com.mstar.android.tvapi.common.exception.TvCommonException;
import mstar.factorymenu.ui.R;
import android.view.KeyEvent;
import java.util.*;
import java.io.*;

public class PanelInfoViewHolder {
    private static final String TAG = "PanelInfoViewHolder";

    protected TextView text_panel_info_changelist_val;

    protected CheckBox btn_panel_info_inverted_screen;

    protected CheckBox btn_panel_info_logo;

    protected CheckBox btn_panel_info_lvds;

    protected CheckBox btn_panel_info_lvds_ab;

    private DesignMenuActivity mPanelInfoActivity;

    private String mPanelInfoChangelistVal = "0F0F0F";

    private TvFactoryManager mTvFactoryManager = null;

    static Process process = null;
    DataOutputStream os = null;
    private IniUtils utils;
    public PanelInfoViewHolder(DesignMenuActivity activity) {
        mPanelInfoActivity = activity;
    }

    public void findView() {
        text_panel_info_changelist_val = (TextView) mPanelInfoActivity
                .findViewById(R.id.textview_panel_info_changelist_val);
        btn_panel_info_inverted_screen = (CheckBox)mPanelInfoActivity
                .findViewById(R.id.imagebutton_panel_info_inverted_screen_val);
        btn_panel_info_logo = (CheckBox)mPanelInfoActivity
                .findViewById(R.id.imagebutton_panel_info_logo_val);
        btn_panel_info_lvds = (CheckBox)mPanelInfoActivity
                .findViewById(R.id.imagebutton_panel_info_lvds_val);
        btn_panel_info_lvds_ab = (CheckBox)mPanelInfoActivity
                .findViewById(R.id.imagebutton_panel_info_lvds_a_b_val);
        utils = new IniUtils(mPanelInfoActivity);
        if ( utils.getInverted()) {
            btn_panel_info_inverted_screen.setChecked(true);
        }else{
            btn_panel_info_inverted_screen.setChecked(false);
        }
        if ( utils.getLvds86()) {
            btn_panel_info_lvds.setChecked(true);
        }else{
            btn_panel_info_lvds.setChecked(false);
        }
        if ( utils.getLvdsAB()) {
            btn_panel_info_lvds_ab.setChecked(true);
        }else{
            btn_panel_info_lvds_ab.setChecked(false);
        }
        try{

           if(TvManager.getInstance().getEnvironmentPowerOnLogoMode() != EnumPowerOnLogoMode.E_POWERON_LOGO_OFF){
               btn_panel_info_logo.setChecked(true);
               utils.setLogo(true);
               Log.i("adfas true ","fadfdsas");
           }else{
               btn_panel_info_logo.setChecked(false);
               utils.setLogo(false);
               Log.i("adfas false","fadfdsas");
           }

        }catch (Exception e){
            e.printStackTrace();
        }
        setListeners();
    }

    public void onCreate() {
        mTvFactoryManager = TvFactoryManager.getInstance();

        PanelVersionInfo panelInfo = mTvFactoryManager.getPanelVersionInfo();

        if (null != panelInfo) {
            mPanelInfoChangelistVal = Long.toHexString(panelInfo.u32Changelist);
        }
        text_panel_info_changelist_val.setText(mPanelInfoChangelistVal);


    }
    private void setListeners(){
        btn_panel_info_inverted_screen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.i("Panel info","inverted true");
                    utils.setInverted(true);
                    //String command = "busybox mount -o remount,rw /tvconfig\n";
                    try {
                        //process = Runtime.getRuntime().exec("su");
                        //os = new DataOutputStream(process.getOutputStream());
                        //os.writeBytes("busybox chmod 777 "+mPanelInfoActivity.getPackageCodePath()+"\n");
                        //os.writeBytes(command);
                        //os.writeBytes("busybox chmod 777 /tvconfig\n");
                        //os.writeBytes("exit \n");
                        //os.flush();
                        //process.waitFor();
                       // TvManager.getInstance().updatePanelIniFile("panel:m_bPanelLVDS_TI_MODE","0");
                        TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_OSD","True");
                        TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_VIDEO","True");
                        TvManager.getInstance().setEnvironment("db_table","0");
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                       /* try {
                            Log.i("finally","finally");
                            if (os != null) {
                                os.close();
                            }
                            process.destroy();
                        } catch (Exception e) {
                        }*/
                    }

                }else{
                    Log.i("Panel info","inverted false");
                    utils.setInverted(false);
                    try {
                        TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_OSD","False");
                        TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_VIDEO","False");
                        TvManager.getInstance().setEnvironment("db_table","0");
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        btn_panel_info_logo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                utils.setLogo(isChecked);
                if(isChecked){
                    Log.i("Panel info","logo true");
                    try {
                        TvManager.getInstance().setEnvironmentPowerOnLogoMode(EnumPowerOnLogoMode.E_POWERON_LOGO_DEFAULT);
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.i("Panel info","logo true");
                    try {
                        TvManager.getInstance().setEnvironmentPowerOnLogoMode(EnumPowerOnLogoMode.E_POWERON_LOGO_OFF);
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btn_panel_info_lvds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                utils.setLvds86(isChecked);
                if(isChecked){
                    Log.i("Panel info","lvds8 true");
                    try {
                        //8bit
                        TvManager.getInstance().updatePanelIniFile("panel:m_ucTiBitMode","2");
                        TvManager.getInstance().setEnvironment("db_table","0");
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.i("Panel info","lvds6 true");
                    try {
                        //6bit
                        TvManager.getInstance().updatePanelIniFile("panel:m_ucTiBitMode","3");
                        TvManager.getInstance().setEnvironment("db_table","0");
                    } catch (TvCommonException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        btn_panel_info_lvds_ab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                utils.setLvdsAB(isChecked);
                if(isChecked){
                    Log.i("Panel info","lvds a true");
                    try {
                        TvManager.getInstance().updatePanelIniFile("panel:m_bPanelSwapPort","0");
                        TvManager.getInstance().setEnvironment("db_table","0");
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.i("Panel info","lvds b true");
                    try {
                        TvManager.getInstance().updatePanelIniFile("panel:m_bPanelSwapPort","1");
                        TvManager.getInstance().setEnvironment("db_table","0");
                    } catch (TvCommonException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = mPanelInfoActivity.getCurrentFocus().getId();
        Log.i(TAG,"onKeyDown = "+currentid+ "keycode ="+keyCode+"KeyEvent"+KeyEvent.KEYCODE_ENTER);
        Log.i(TAG,"R.id.linearlayout_panel_info_changelist="+R.id.linearlayout_panel_info_changelist);
        Log.i(TAG,"R.id.linearlayout_panel_info_inverted_screen"+R.id.linearlayout_panel_info_inverted_screen);
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                Log.i(TAG,"keycurret");
                switch (currentid) {
                    case R.id.linearlayout_panel_info_inverted_screen:
                        if ( utils.getInverted()) {
                            btn_panel_info_inverted_screen.setChecked(false);
                            utils.setInverted(false);
                            try {
                                TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_OSD","False");
                                TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_VIDEO","False");
                                TvManager.getInstance().setEnvironment("db_table","0");
                            } catch (TvCommonException e) {
                                e.printStackTrace();
                            }
                        }else{
                            btn_panel_info_inverted_screen.setChecked(true);
                            utils.setInverted(true);
                            //String command = "busybox mount -o remount,rw /tvconfig\n";
                            try {
                                //process = Runtime.getRuntime().exec("su");
                                //os = new DataOutputStream(process.getOutputStream());
                                //os.writeBytes("busybox chmod 777 "+mPanelInfoActivity.getPackageCodePath()+"\n");
                                //os.writeBytes(command);
                                //os.writeBytes("busybox chmod 777 /tvconfig\n");
                                //os.writeBytes("exit \n");
                                //os.flush();
                                //process.waitFor();
                                // TvManager.getInstance().updatePanelIniFile("panel:m_bPanelLVDS_TI_MODE","0");
                                TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_OSD","True");
                                TvManager.getInstance().updateCustomerIniFile("MISC_MIRROR_CFG:MIRROR_VIDEO","True");
                                TvManager.getInstance().setEnvironment("db_table","0");
                            } catch (TvCommonException e) {
                                e.printStackTrace();
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                       /* try {
                            Log.i("finally","finally");
                            if (os != null) {
                                os.close();
                            }
                            process.destroy();
                        } catch (Exception e) {
                        }*/
                            }
                        }
                        break;
                    case R.id.linearlayout_panel_info_logo:

                        try {
                            if (utils.getLogo()) {
                                btn_panel_info_logo.setChecked(false);
                                utils.setLogo(false);
                                TvManager.getInstance().setEnvironmentPowerOnLogoMode(EnumPowerOnLogoMode.E_POWERON_LOGO_OFF);

                                Log.i("adfas true ", "fadfdsas");
                            } else {
                                btn_panel_info_logo.setChecked(true);
                                utils.setLogo(true);
                                TvManager.getInstance().setEnvironmentPowerOnLogoMode(EnumPowerOnLogoMode.E_POWERON_LOGO_DEFAULT);
                                Log.i("adfas false", "fadfdsas");
                            }
                        }catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        break;
                    case R.id.linearlayout_panel_info_lvds:
                        if ( utils.getLvds86()) {
                            btn_panel_info_lvds.setChecked(false);
                            utils.setLvds86(false);
                            try {
                                //6bit
                                TvManager.getInstance().updatePanelIniFile("panel:m_ucTiBitMode","3");
                                TvManager.getInstance().setEnvironment("db_table","0");
                            } catch (TvCommonException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }else{
                            btn_panel_info_lvds.setChecked(true);
                            utils.setLvds86(true);
                            try {
                                //6bit
                                TvManager.getInstance().updatePanelIniFile("panel:m_ucTiBitMode","2");
                                TvManager.getInstance().setEnvironment("db_table","0");
                            } catch (TvCommonException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    break;
                    case R.id.linearlayout_panel_info_lvds_ab:
                        if ( !utils.getLvdsAB()) {
                            btn_panel_info_lvds_ab.setChecked(true);
                            utils.setLvdsAB(true);
                            try {
                                TvManager.getInstance().updatePanelIniFile("panel:m_bPanelSwapPort","0");
                                TvManager.getInstance().setEnvironment("db_table","0");
                            } catch (TvCommonException e) {
                                e.printStackTrace();
                            }
                        }else{
                            btn_panel_info_lvds_ab.setChecked(false);
                            utils.setLvdsAB(false);
                            try {
                                TvManager.getInstance().updatePanelIniFile("panel:m_bPanelSwapPort","1");
                                TvManager.getInstance().setEnvironment("db_table","0");
                            } catch (TvCommonException e) {
                                e.printStackTrace();
                            }
                        }

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
