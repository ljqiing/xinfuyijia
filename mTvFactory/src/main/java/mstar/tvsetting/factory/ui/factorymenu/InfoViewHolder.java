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

package mstar.tvsetting.factory.ui.factorymenu;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import android.os.SystemProperties;


import com.mstar.android.tvapi.dtv.vo.DtvDemodVersion;
import com.mstar.android.tv.TvChannelManager;
import com.mstar.android.tv.TvFactoryManager;
import mstar.factorymenu.ui.R;

import mstar.tvsetting.factory.desk.FactoryDeskImpl;
import mstar.tvsetting.factory.desk.IFactoryDesk;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;





public class InfoViewHolder {
    private FactoryMenuActivity mstarActivity;

    protected TextView swTv;
    protected TextView boardTv;
    protected TextView panelTv;

    protected TextView mainPqTV;
    protected TextView subPqTv;
	
    protected TextView AndroidBuildTv;
    protected TextView SupernovaBuildTv;
    protected TextView MbootBuildTv;
	protected TextView BacklightVoltageTv;
    protected TextView BacklightCurrentTv;

    private String str_version = new String();
	private String str_board = new String();
	private String str_panel = new String();
		
    private String str_android_build = new String();
    private String str_supernova_build = new String();
    private String str_mboot_build = new String();

	private String str_backlight_voltage = new String();
    private String str_backlight_Current = new String();
	
    private String str_main_pq_version = new String();
    private String str_sub_pq_version = new String();
    private TvFactoryManager mTvFactoryManager = null;
	private FactoryDeskImpl mFactoryDeskImpl;

    public InfoViewHolder(FactoryMenuActivity mstarActivity) {
        this.mstarActivity = mstarActivity;
    }

    public void findView() {
        swTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_sw_version_val);
        boardTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_board_val);
        panelTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_panel_val);
        mainPqTV = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_main_pq_val);
        subPqTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_sub_pq_val);

		AndroidBuildTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_android_build_val);
        SupernovaBuildTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_supernova_build_val);
        MbootBuildTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_mboot_build_val);

		BacklightVoltageTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_backlight_voltage_val);
        BacklightCurrentTv = (TextView) mstarActivity
			.findViewById(R.id.textview_factory_otheroption_backlight_current_val);
		
    }

    public void onCreate() {
		mFactoryDeskImpl  = FactoryDeskImpl.getInstance(mstarActivity);
        mTvFactoryManager = TvFactoryManager.getInstance();
        str_version = mTvFactoryManager.getSoftWareVersion();
        str_board = mTvFactoryManager.getBoardType();
        str_panel = mTvFactoryManager.getPanelType();
		
		//smc 20161010
		str_android_build = SystemProperties.get("ro.build.date", "");
        str_supernova_build = mTvFactoryManager.getCompileTime();
        str_mboot_build = mFactoryDeskImpl.getEnvironment("MBOOT_BUILD_TIME");
		str_backlight_Current = mFactoryDeskImpl.GetBackLightProperty(0);
		str_backlight_voltage = mFactoryDeskImpl.GetBackLightProperty(1);
		
        str_main_pq_version = mTvFactoryManager.getPQVersion(0);
        str_sub_pq_version = mTvFactoryManager.getPQVersion(1);

        swTv.setText(str_version);
		boardTv.setText(str_board);
        panelTv.setText(str_panel);
		
        AndroidBuildTv.setText(str_android_build);
        SupernovaBuildTv.setText(str_supernova_build);
        MbootBuildTv.setText(str_mboot_build);

		BacklightVoltageTv.setText(str_backlight_voltage);
		BacklightCurrentTv.setText(str_backlight_Current);
     
        subPqTv.setText(str_sub_pq_version);
        mainPqTV.setText(str_main_pq_version);
		
		timer.schedule(task, 1000, 1000); 

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
		int currentid = mstarActivity.getCurrentFocus().getId();
		Log.e("InfoViewHolder","onKeyDown---KEYCODE_ENTER");
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_MENU:
                this.mstarActivity.returnRoot(4);
                break;
            default:
                return false;
        }
        return true;
    }
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg){
			if(msg.what == 1) {
				str_backlight_Current = mFactoryDeskImpl.GetBackLightProperty(0);
				str_backlight_voltage = mFactoryDeskImpl.GetBackLightProperty(1);
				BacklightVoltageTv.setText(str_backlight_voltage);
				BacklightCurrentTv.setText(str_backlight_Current);
			}
			super.handleMessage(msg);
		}
	};
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask(){
		@Override
		public void run(){
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);
		}
	};

}
