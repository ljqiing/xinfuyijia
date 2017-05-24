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
import mstar.tvsetting.factory.desk.IFactoryDesk.EN_MS_PICTURE;
import mstar.tvsetting.factory.desk.IFactoryDesk.EN_MS_VIDEOITEM;
import mstar.tvsetting.factory.ui.designmenu.DesignMenuActivity;
import com.mstar.android.tv.TvCommonManager;
import android.text.format.Time;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mstar.android.tv.TvPictureManager;

@SuppressWarnings("unused")
public class PictureModeAdjustViewHolder {
    private DesignMenuActivity picModeActivity;

    private IFactoryDesk factoryManager;

    private TvCommonManager mTvCommonManager = null;

    private TvPictureManager mTvPictureManager = null;

    protected TextView text_factory_picmode_picmode_val;

    protected TextView text_factory_picmode_brightness_val;

    protected TextView text_factory_picmode_contrast_val;

    protected TextView text_factory_picmode_saturation_val;

    protected TextView text_factory_picmode_hue_val;

    protected TextView text_factory_picmode_sharpness_val;

    protected TextView text_factory_picmode_backlight_val;

    protected ProgressBar progress_factory_picmode_brightness;

    protected ProgressBar progress_factory_picmode_contrast;

    protected ProgressBar progress_factory_picmode_saturation;

    protected ProgressBar progress_factory_picmode_hue;

    protected ProgressBar progress_factory_picmode_sharpness;

    protected ProgressBar progress_factory_picmode_backlight;

    private int picmodeindex = 0;

    private int brightnessval = 50;

    private int contrastval = 50;

    private int saturationval = 50;

    private int hueval = 50;

    private int sharpnessval = 50;

    private int backlightval = 50;

    private long prevTime = 0;

    private String[] mPicModeStrArray;

    public long timeDiffMs() {
        long curTime = 0;
        long diffTime = 0xffffffff;
        Time time = new Time();

        time.setToNow();
        curTime = time.toMillis(true);

        if (curTime >= prevTime) {
            diffTime = curTime - prevTime;
        }

        prevTime = curTime;

        return diffTime;
    }

    public PictureModeAdjustViewHolder(DesignMenuActivity activity, IFactoryDesk factoryManager) {
        picModeActivity = activity;
        this.factoryManager = factoryManager;
        mTvCommonManager = TvCommonManager.getInstance();
        mTvPictureManager = TvPictureManager.getInstance();
        mPicModeStrArray = picModeActivity.getResources().getStringArray(
                R.array.str_arr_pic_mode_vals);
    }

    void findView() {
        text_factory_picmode_picmode_val = (TextView) picModeActivity
                .findViewById(R.id.textview_factory_picmode_picmode_val);
        text_factory_picmode_brightness_val = (TextView) picModeActivity
                .findViewById(R.id.textview_factory_picmode_brightness_val);
        text_factory_picmode_contrast_val = (TextView) picModeActivity
                .findViewById(R.id.textview_factory_picmode_contrast_val);
        text_factory_picmode_saturation_val = (TextView) picModeActivity
                .findViewById(R.id.textview_factory_picmode_saturation_val);
        text_factory_picmode_hue_val = (TextView) picModeActivity
                .findViewById(R.id.textview_factory_picmode_hue_val);
        text_factory_picmode_sharpness_val = (TextView) picModeActivity
                .findViewById(R.id.textview_factory_picmode_sharpness_val);
        text_factory_picmode_backlight_val = (TextView) picModeActivity
                .findViewById(R.id.textview_factory_picmode_backlight_val);
        progress_factory_picmode_brightness = (ProgressBar) picModeActivity
                .findViewById(R.id.progressbar_facroty_picmode_brightness);
        progress_factory_picmode_contrast = (ProgressBar) picModeActivity
                .findViewById(R.id.progressbar_facroty_picmode_contrast);
        progress_factory_picmode_saturation = (ProgressBar) picModeActivity
                .findViewById(R.id.progressbar_facroty_picmode_saturation);
        progress_factory_picmode_hue = (ProgressBar) picModeActivity
                .findViewById(R.id.progressbar_facroty_picmode_hue);
        progress_factory_picmode_sharpness = (ProgressBar) picModeActivity
                .findViewById(R.id.progressbar_facroty_picmode_sharpness);
        progress_factory_picmode_backlight = (ProgressBar) picModeActivity
                .findViewById(R.id.progressbar_facroty_picmode_backlight);
    }

    public boolean onCreate() {
        picmodeindex = factoryManager.getPictureModeIdx();
        brightnessval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS);
        contrastval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST);
        saturationval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION);
        hueval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE);
        sharpnessval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS);
        backlightval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT);
        ;
        text_factory_picmode_brightness_val.setText(Integer.toString(brightnessval));
        text_factory_picmode_contrast_val.setText(Integer.toString(contrastval));
        text_factory_picmode_saturation_val.setText(Integer.toString(saturationval));
        text_factory_picmode_hue_val.setText(Integer.toString(hueval));
        text_factory_picmode_sharpness_val.setText(Integer.toString(sharpnessval));
        text_factory_picmode_backlight_val.setText(Integer.toString(backlightval));

        progress_factory_picmode_brightness.setProgress(brightnessval);
        progress_factory_picmode_contrast.setProgress(contrastval);
        progress_factory_picmode_saturation.setProgress(saturationval);
        progress_factory_picmode_hue.setProgress(hueval);
        progress_factory_picmode_sharpness.setProgress(sharpnessval);
        progress_factory_picmode_backlight.setProgress(backlightval);
        text_factory_picmode_picmode_val.setText(mPicModeStrArray[picmodeindex]);

        prevTime = 0;
        return true;
    }

    private void freshDataToUIWhenPicModChange() {
        brightnessval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS);
        contrastval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST);
        saturationval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION);
        hueval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE);
        sharpnessval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS);
        backlightval = factoryManager.getVideoItem(EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT);
        // update progress bar
        progress_factory_picmode_brightness.setProgress(brightnessval);
        progress_factory_picmode_contrast.setProgress(contrastval);
        progress_factory_picmode_hue.setProgress(hueval);
        progress_factory_picmode_saturation.setProgress(saturationval);
        progress_factory_picmode_sharpness.setProgress(sharpnessval);
        progress_factory_picmode_backlight.setProgress(backlightval);
        // update values
        text_factory_picmode_brightness_val.setText(Integer.toString(brightnessval));
        text_factory_picmode_contrast_val.setText(Integer.toString(contrastval));
        text_factory_picmode_saturation_val.setText(Integer.toString(saturationval));
        text_factory_picmode_hue_val.setText(Integer.toString(hueval));
        text_factory_picmode_sharpness_val.setText(Integer.toString(sharpnessval));
        text_factory_picmode_backlight_val.setText(Integer.toString(backlightval));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = picModeActivity.getCurrentFocus().getId();
        int currentInputSrc = mTvCommonManager.getCurrentTvInputSource();
        String str_val = new String();
        int maxValue = 0;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_picmode_picmode:
                        if (timeDiffMs() < 300) {
                            break;
                        }
                        maxValue = EN_MS_PICTURE.PICTURE_NUMS.ordinal();
                        if (picmodeindex < (maxValue - 1)) {
                            if (currentInputSrc == mTvCommonManager.INPUT_SOURCE_VGA
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_VGA2
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_VGA3
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_HDMI
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_HDMI2
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_HDMI3) {
                            picmodeindex++;
                            } else {
                                if (picmodeindex == EN_MS_PICTURE.PICTURE_GAME.ordinal()) {
                                    picmodeindex = EN_MS_PICTURE.PICTURE_VIVID.ordinal();
                                } else {
                                    picmodeindex++;
                                }
                            }
                        } else {
                            picmodeindex = 0;
                        }
                        text_factory_picmode_picmode_val.setText(mPicModeStrArray[picmodeindex]);
                        mTvPictureManager.setPictureMode(picmodeindex);
                        freshDataToUIWhenPicModChange();

                        break;
                    case R.id.linearlayout_factory_picmode_brightness:
                        if (brightnessval != 100) {
                            brightnessval++;
                        } else {
                            brightnessval = 0;
                        }
                        str_val = Integer.toString(brightnessval);
                        text_factory_picmode_brightness_val.setText(str_val);
                        progress_factory_picmode_brightness.setProgress(brightnessval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_BRIGHTNESS, brightnessval);
                        break;
                    case R.id.linearlayout_factory_picmode_contrast:
                        if (contrastval != 100) {
                            contrastval++;
                        } else {
                            contrastval = 0;
                        }
                        str_val = Integer.toString(contrastval);
                        text_factory_picmode_contrast_val.setText(str_val);
                        progress_factory_picmode_contrast.setProgress(contrastval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_CONTRAST, contrastval);
                        break;
                    case R.id.linearlayout_factory_picmode_saturation:
                        if (saturationval != 100) {
                            saturationval++;
                        } else {
                            saturationval = 0;
                        }
                        str_val = Integer.toString(saturationval);
                        text_factory_picmode_saturation_val.setText(str_val);
                        progress_factory_picmode_saturation.setProgress(saturationval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_SATURATION, saturationval);
                        break;
                    case R.id.linearlayout_factory_picmode_hue:
                        if (hueval != 100) {
                            hueval++;
                        } else {
                            hueval = 0;
                        }
                        str_val = Integer.toString(hueval);
                        text_factory_picmode_hue_val.setText(str_val);
                        progress_factory_picmode_hue.setProgress(hueval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_HUE, hueval);
                        break;
                    case R.id.linearlayout_factory_picmode_sharpness:
                        if (sharpnessval != 100) {
                            sharpnessval++;
                        } else {
                            sharpnessval = 0;
                        }
                        str_val = Integer.toString(sharpnessval);
                        text_factory_picmode_sharpness_val.setText(str_val);
                        progress_factory_picmode_sharpness.setProgress(sharpnessval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_SHARPNESS, sharpnessval);
                        break;
                    case R.id.linearlayout_factory_picmode_backlight:
                        if (backlightval != 100) {
                            backlightval++;
                        } else {
                            backlightval = 0;
                        }
                        str_val = Integer.toString(backlightval);
                        text_factory_picmode_backlight_val.setText(str_val);
                        progress_factory_picmode_backlight.setProgress(backlightval);
                        mTvPictureManager.setBacklight(backlightval);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_picmode_picmode:
                        if (timeDiffMs() < 300) {
                            break;
                        }
                        maxValue = EN_MS_PICTURE.PICTURE_NUMS.ordinal();
                        if (picmodeindex > 0) {
                            if (currentInputSrc == mTvCommonManager.INPUT_SOURCE_VGA
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_VGA2
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_VGA3
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_HDMI
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_HDMI2
                                    || currentInputSrc == mTvCommonManager.INPUT_SOURCE_HDMI3) {
                                picmodeindex--;
                            } else {
                                if (picmodeindex == EN_MS_PICTURE.PICTURE_VIVID.ordinal()) {
                                    picmodeindex = EN_MS_PICTURE.PICTURE_GAME.ordinal();
                                } else {
                                    picmodeindex--;
                                }
                            }
                        } else {
                            picmodeindex = maxValue - 1;
                        }
                        text_factory_picmode_picmode_val.setText(mPicModeStrArray[picmodeindex]);
                        mTvPictureManager.setPictureMode(picmodeindex);
                        freshDataToUIWhenPicModChange();
                        break;
                    case R.id.linearlayout_factory_picmode_brightness:
                        if (brightnessval != 0) {
                            brightnessval--;
                        } else {
                            brightnessval = 100;
                        }
                        str_val = Integer.toString(brightnessval);
                        text_factory_picmode_brightness_val.setText(str_val);
                        progress_factory_picmode_brightness.setProgress(brightnessval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_BRIGHTNESS, brightnessval);
                        break;
                    case R.id.linearlayout_factory_picmode_contrast:
                        if (contrastval != 0) {
                            contrastval--;
                        } else {
                            contrastval = 100;
                        }
                        str_val = Integer.toString(contrastval);
                        text_factory_picmode_contrast_val.setText(str_val);
                        progress_factory_picmode_contrast.setProgress(contrastval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_CONTRAST, contrastval);
                        break;
                    case R.id.linearlayout_factory_picmode_saturation:
                        if (saturationval != 0) {
                            saturationval--;
                        } else {
                            saturationval = 100;
                        }
                        str_val = Integer.toString(saturationval);
                        text_factory_picmode_saturation_val.setText(str_val);
                        progress_factory_picmode_saturation.setProgress(saturationval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_SATURATION, saturationval);
                        break;
                    case R.id.linearlayout_factory_picmode_hue:
                        if (hueval != 0) {
                            hueval--;
                        } else {
                            hueval = 100;
                        }
                        str_val = Integer.toString(hueval);
                        text_factory_picmode_hue_val.setText(str_val);
                        progress_factory_picmode_hue.setProgress(hueval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_HUE, hueval);
                        break;
                    case R.id.linearlayout_factory_picmode_sharpness:
                        if (sharpnessval != 0) {
                            sharpnessval--;
                        } else {
                            sharpnessval = 100;
                        }
                        str_val = Integer.toString(sharpnessval);
                        text_factory_picmode_sharpness_val.setText(str_val);
                        progress_factory_picmode_sharpness.setProgress(sharpnessval);
                        mTvPictureManager.setVideoItem(TvPictureManager.PICTURE_SHARPNESS, sharpnessval);
                        break;
                    case R.id.linearlayout_factory_picmode_backlight:
                        if (backlightval != 0) {
                            backlightval--;
                        } else {
                            backlightval = 100;
                        }
                        str_val = Integer.toString(backlightval);
                        text_factory_picmode_backlight_val.setText(str_val);
                        progress_factory_picmode_backlight.setProgress(backlightval);
                        mTvPictureManager.setBacklight(backlightval);
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
