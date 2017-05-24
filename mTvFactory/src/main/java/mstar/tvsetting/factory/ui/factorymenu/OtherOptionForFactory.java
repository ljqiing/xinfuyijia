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

package mstar.tvsetting.factory.ui.factorymenu;

import java.io.ByteArrayOutputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  


import android.util.Log;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.View;
import android.view.Gravity;


import com.mstar.android.tv.TvFactoryManager;
import mstar.factorymenu.ui.R;

import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import mstar.factorymenu.ui.R;
import mstar.tvsetting.factory.desk.FactoryDeskImpl;
import mstar.tvsetting.factory.desk.IFactoryDesk;
import mstar.tvsetting.factory.desk.IFactoryDesk.E_ADC_SET_INDEX;
import mstar.tvsetting.factory.ui.designmenu.DesignMenuActivity;

//qwh add
import android.content.Context;
import android.os.Handler;
import android.util.Log;
//qwh add end


public class OtherOptionForFactory {
    private FactoryMenuActivity mstarActivity;

    private TextView textVideoMuteColorVal;

    protected TextView text_factory_otheroption_testpattern_val;

    protected TextView text_factory_otheroption_initialfunction_val;

    private int mTestpartternindex = 0;

    private int mInitialchannelindex = 0;	
		
	//qwh add for Burn In RGB	
	private FactoryDeskImpl mFactoryDeskImpl;
	private static final String TAG = "OtherOptionForFactory";
	private int mBurnInRGBindex = 0;
	protected TextView text_factory_otheroption_BurnInRGB_val;
	private String[] mBurnInRGB = {
            "off", "on"
    };
	//qwh add end
	
    private String[] mTestPatternType = null;

    private String[] mStrArrayMuteColors = null;

    private String[] mInitialchannel = {
            "DTV", "ATV"
    };

    /*
     * Video Mute Color index
     *
     * @see com.mstar.android.tv.TvFactoryManager#VIDEO_MUTE_COLOR_BLACK
     * @see com.mstar.android.tv.TvFactoryManager#VIDEO_MUTE_COLOR_WHITE
     * @see com.mstar.android.tv.TvFactoryManager#VIDEO_MUTE_COLOR_RED
     * @see com.mstar.android.tv.TvFactoryManager#VIDEO_MUTE_COLOR_BLUE
     * @see com.mstar.android.tv.TvFactoryManager#VIDEO_MUTE_COLOR_GREEN
     * @see com.mstar.android.tv.TvFactoryManager#VIDEO_MUTE_COLOR_NUMBER
     */
    private int mVideoMuteColor = TvFactoryManager.VIDEO_MUTE_COLOR_BLACK;

    private TvFactoryManager mTvFactoryManager = null;

    public OtherOptionForFactory(FactoryMenuActivity mstarActivity) {
        this.mstarActivity = mstarActivity;
    }

    public void findView() {
        text_factory_otheroption_testpattern_val = (TextView) mstarActivity
                .findViewById(R.id.textview_factory_otheroptionfactory_testpattern_val);
        text_factory_otheroption_initialfunction_val = (TextView) mstarActivity
                .findViewById(R.id.textview_factory_otheroptionfactory_initialfunction_val);
        textVideoMuteColorVal = (TextView) mstarActivity
                .findViewById(R.id.linearlayout_factory_otheroptionfactory_video_mute_color_val);
		//qwh add for Burn In RGB					
		text_factory_otheroption_BurnInRGB_val = (TextView) mstarActivity
                .findViewById(R.id.textview_factory_otheroptionfactory_burnInRGB_val);
		//qwh add end      
        mTestpartternindex = mTvFactoryManager.getVideoTestPattern();

        if ((0 <= mVideoMuteColor) && (mStrArrayMuteColors.length > mVideoMuteColor)) {
            textVideoMuteColorVal.setText(mStrArrayMuteColors[mVideoMuteColor]);
        } else {
            textVideoMuteColorVal.setText(mStrArrayMuteColors[TvFactoryManager.VIDEO_MUTE_COLOR_BLACK]);
        }
    }

    public void onCreate() {
		//qwh add	
		mFactoryDeskImpl  = FactoryDeskImpl.getInstance(mstarActivity);
		mBurnInRGBindex = mFactoryDeskImpl.getBurnInRGB();			
		//qwh add end
        mTvFactoryManager = TvFactoryManager.getInstance();
        mTestPatternType = mstarActivity.getResources().getStringArray(
                R.array.str_arr_test_pattern_type);
        mStrArrayMuteColors = mstarActivity.getResources().getStringArray(
                R.array.str_arr_mute_colors);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        int currentid = mstarActivity.getCurrentFocus().getId();
		text_factory_otheroption_BurnInRGB_val.setText(mBurnInRGB[mBurnInRGBindex]);	
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
				Log.e("OtherOptionForFactory","onKeyDown---KEYCODE_DPAD_RIGHT");
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroptionfactory_video_mute_color:
                        int newVideoMuteColor = mVideoMuteColor + 1;
                        if ((0 > newVideoMuteColor) || (mStrArrayMuteColors.length <= newVideoMuteColor)) {
                            newVideoMuteColor = 0;
                        }
                        if (true == mTvFactoryManager.setVideoMuteColor(newVideoMuteColor)) {
                            textVideoMuteColorVal.setText(mStrArrayMuteColors[newVideoMuteColor]);
                            mVideoMuteColor = newVideoMuteColor;
                        }
                        break;
                    case R.id.linearlayout_factory_otheroptionfactory_testpattern:
                        if (mTestpartternindex != TvFactoryManager.SCREEN_MUTE_BLACK)
                            mTestpartternindex++;
                        else
                            mTestpartternindex = TvFactoryManager.SCREEN_MUTE_OFF;

                        text_factory_otheroption_testpattern_val
                                .setText(mTestPatternType[mTestpartternindex]);
                        mTvFactoryManager.setVideoTestPattern(mTestpartternindex);
                        break;
                    case R.id.linearlayout_factory_otheroptionfactory_initialfunction:
                        if (mInitialchannelindex != 1)
                            mInitialchannelindex++;
                        else
                            mInitialchannelindex = 0;
                        text_factory_otheroption_initialfunction_val
                                .setText(mInitialchannel[mInitialchannelindex]);
                        mTvFactoryManager.setFactoryPreSetFeature(mInitialchannelindex);
                        break;
					//qwh add for Burn In RGB	
					case R.id.linearlayout_factory_otheroptionfactory_burnInRGB:
                        if (mBurnInRGBindex != 1)
                            mBurnInRGBindex++;
                        else
                            mBurnInRGBindex = 0;
                        text_factory_otheroption_BurnInRGB_val.setText(mBurnInRGB[mBurnInRGBindex]);                       
                        //Log.d(TAG, "qwh right mBurnInRGBindex = " + mBurnInRGBindex);
                        mFactoryDeskImpl.setBurnInRGB(mBurnInRGBindex);
                        break;	
					//qwh add end
					
					case R.id.linearlayout_factory_otheroptionfactory_export:
                       	do_exec("rm -rf /mnt/usb/sda1/backup_config");
						do_exec("sync");
						sleep(200);
                       	do_exec("cp -rf /skyworth/backup_config/  /mnt/usb/sda1/");
						do_exec("sync");
						sleep(4000);
                        break;
					case R.id.linearlayout_factory_otheroptionfactory_import:
						do_exec("cp -rf /mnt/usb/sda1/backup_config/  /skyworth/");
						do_exec("sync");
						sleep(4000);
						mFactoryDeskImpl.upgrade("db_table","0");
						do_exec("reboot");
                        break;
					case R.id.linearlayout_factory_otheroptionfactory_export_panel:
                       	do_exec("rm -rf /mnt/usb/sda1/panel");
						do_exec("sync");
						sleep(200);
                       	do_exec("cp -rf /skyworth/backup_config/panel/  /mnt/usb/sda1/");
						do_exec("sync");
						sleep(4000);
                        break;
					case R.id.linearlayout_factory_otheroptionfactory_import_panel:
						do_exec("cp -rf /mnt/usb/sda1/panel/  /skyworth/backup_config/");
						do_exec("sync");
						sleep(4000);
						mFactoryDeskImpl.upgrade("db_table","0");
						do_exec("reboot");
                        break;	
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroptionfactory_video_mute_color:
                        int newVideoMuteColor = mVideoMuteColor - 1;
                        if ((0 > newVideoMuteColor) || (mStrArrayMuteColors.length <= newVideoMuteColor)) {
                            newVideoMuteColor = mStrArrayMuteColors.length - 1;
                        }
                        if (true == mTvFactoryManager.setVideoMuteColor(newVideoMuteColor)) {
                            textVideoMuteColorVal.setText(mStrArrayMuteColors[newVideoMuteColor]);
                            mVideoMuteColor = newVideoMuteColor;
                        }
                        break;
                    case R.id.linearlayout_factory_otheroptionfactory_testpattern:
                        if (mTestpartternindex != TvFactoryManager.SCREEN_MUTE_OFF)
                            mTestpartternindex--;
                        else
                            mTestpartternindex = TvFactoryManager.SCREEN_MUTE_BLACK;
                        text_factory_otheroption_testpattern_val
                                .setText(mTestPatternType[mTestpartternindex]);
                        mTvFactoryManager.setVideoTestPattern(mTestpartternindex);
                        break;
                    case R.id.linearlayout_factory_otheroptionfactory_initialfunction:
                        if (mInitialchannelindex != 0)
                            mInitialchannelindex--;
                        else
                            mInitialchannelindex = 1;
                        text_factory_otheroption_initialfunction_val
                                .setText(mInitialchannel[mInitialchannelindex]);
                        mTvFactoryManager.setFactoryPreSetFeature(mInitialchannelindex);
                        break;
					//qwh add for Burn In RGB	
					case R.id.linearlayout_factory_otheroptionfactory_burnInRGB:
                        if (mBurnInRGBindex != 0)
                            mBurnInRGBindex--;
                        else
                            mBurnInRGBindex = 1;
                        text_factory_otheroption_BurnInRGB_val.setText(mBurnInRGB[mBurnInRGBindex]);                       
                        //Log.d(TAG, "qwh left mBurnInRGBindex = " + mBurnInRGBindex);
                        mFactoryDeskImpl.setBurnInRGB(mBurnInRGBindex);
                        break;
					//qwh add end
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_MENU:
                this.mstarActivity.returnRoot(3);
                break;
            default:
                return false;
        }
        return true;
    }
	
	public void do_exec(String cmd) {  
        try {  
            Process p = Runtime.getRuntime().exec(cmd);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
	
	public void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        }
    }
}
