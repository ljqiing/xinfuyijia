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

package mstar.tvsetting.factory.desk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import mstar.tvsetting.factory.desk.FactoryDB.USER_SETTING_FIELD;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.mstar.android.tv.TvCommonManager;
import com.mstar.android.tv.TvCiManager;
import com.mstar.android.tvapi.atv.AtvManager;
import com.mstar.android.tvapi.atv.AtvPlayer;
import com.mstar.android.tvapi.atv.AtvScanManager;
import com.mstar.android.tvapi.common.AudioManager;
import com.mstar.android.tvapi.common.PictureManager;
import com.mstar.android.tvapi.common.ThreeDimensionManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.TvPlayer;
import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.mstar.android.tvapi.common.vo.Detect3dFormatParameter;
import com.mstar.android.tvapi.common.vo.DtvSoundEffect;
import com.mstar.android.tvapi.common.vo.EnumAtvInfoType;
import com.mstar.android.tvapi.common.vo.EnumAudioVolumeSourceType;
import com.mstar.android.tvapi.common.vo.EnumColorTemperature;
import com.mstar.android.tvapi.common.vo.EnumScalerWindow;
import com.mstar.android.tvapi.common.vo.EnumSoundEffectType;
import com.mstar.android.tvapi.common.vo.EnumSoundHidevMode;
import com.mstar.android.tvapi.common.vo.EnumSoundSetParamType;
import com.mstar.android.tvapi.common.vo.SoundParameterPeq;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.android.tvapi.dtv.dvb.dvbc.DtvDemodDvbcInfo;
import com.mstar.android.tvapi.dtv.dvb.dvbt.DtvDemodDvbtInfo;
import com.mstar.android.tvapi.factory.FactoryManager;
import com.mstar.android.tvapi.factory.vo.EnumAcOnPowerOnMode;
import com.mstar.android.tvapi.factory.vo.EnumAdcSetIndexType;
import com.mstar.android.tvapi.factory.vo.EnumPqUpdateFile;
import com.mstar.android.tvapi.factory.vo.EnumScreenMute;
import com.mstar.android.tvapi.factory.vo.FactoryNsVdSet;
import com.mstar.android.tvapi.factory.vo.PqlCalibrationData;
import com.mstar.android.tvapi.impl.PlayerImpl;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;

public class FactoryDeskImpl implements IFactoryDesk {
    private static final String TAG = "FactoryDeskImpl";

    private static FactoryDeskImpl instance;

    private FactoryDB factoryDB;

    private PqlCalibrationData adcCalibData;

    private E_ADC_SET_INDEX eAdcIdx;

    private EnumInputSource eWBIdx;

    private T_MS_COLOR_TEMP_DATA wb_gainoffset;

    private MS_Factory_NS_VIF_SET vifSet;

    private FactoryNsVdSet facvdpara;

    private MS_FACTORY_EXTERN_SETTING factoryExternSetting;

    private MS_FACTORY_SSC_SET sscSet;

    private MS_FACTORY_CI_SET ciSet;

    private ST_FACTORY_PEQ_SETTING factoryPEQSet;

    private T_MS_COLOR_TEMPEX factoryColorTmpEx;

    private MS_NLA_SETTING mfactoryNLASet;

    // align to MAPI_INPUT_SOURCE_TYPE of SN
    private static final int MAPI_INPUT_SOURCE_NUM = 40;

    private ST_MAPI_VIDEO_WINDOW_INFO[][] factoryDTVOverscanSet;

    private ST_MAPI_VIDEO_WINDOW_INFO[][] factoryHDMIOverscanSet;

    private ST_MAPI_VIDEO_WINDOW_INFO[][] factoryYPbPrOverscanSet;

    private ST_MAPI_VIDEO_WINDOW_INFO[][] factoryVDOverscanSet;

    private ST_MAPI_VIDEO_WINDOW_INFO[][] factoryATVOverscanSet;

    private int f_phase;

    private AudioManager am = TvManager.getInstance().getAudioManager();

    private FactoryManager fm = TvManager.getInstance().getFactoryManager();

    private PictureManager pm = TvManager.getInstance().getPictureManager();

    private AtvPlayer matvplayer = AtvManager.getAtvPlayerManager();

    private ThreeDimensionManager s3m = TvManager.getInstance().getThreeDimensionManager();

    private TvPlayer tp = TvManager.getInstance().getPlayerManager();

    private AtvScanManager asm = AtvManager.getAtvScanManager();

    private TvPlayer mtvplayer = TvManager.getInstance().getPlayerManager();

    private TvCiManager tvciManager = null;

    private TvCommonManager tvCommonManager = null;

    private FactoryDeskImpl(Context context) {
        f_phase = 0x3B;
        factoryDB = FactoryDB.getInstance(context);
        tvCommonManager = TvCommonManager.getInstance();
        tvciManager = TvCiManager.getInstance();
    }

    public static FactoryDeskImpl getInstance(Context context) {
        if (instance == null) {
            instance = new FactoryDeskImpl(context);
        }
        return instance;
    }

    public T_MS_COLOR_TEMPEX_DATA getFactoryColorTempData(int colorid, int source) {
        return factoryColorTmpEx.astColorTempEx[colorid][source];
    }

    public void setFactoryColorTempData(T_MS_COLOR_TEMPEX_DATA vo, int colorid, int source) {
        factoryColorTmpEx.astColorTempEx[colorid][source].redgain = vo.redgain;
        factoryColorTmpEx.astColorTempEx[colorid][source].greengain = vo.greengain;
        factoryColorTmpEx.astColorTempEx[colorid][source].bluegain = vo.bluegain;
        factoryColorTmpEx.astColorTempEx[colorid][source].redoffset = vo.redoffset;
        factoryColorTmpEx.astColorTempEx[colorid][source].greenoffset = vo.greenoffset;
        factoryColorTmpEx.astColorTempEx[colorid][source].bluegain = vo.bluegain;

        factoryDB.updateFactoryColorTempExData(vo, source, colorid);
    }

    /*************************************************************************
     * ADC adjust setting
     ************************************************************************/
    private void setADCGainOffset(EnumScalerWindow enWin, int iAdcIdx) {
        try {
            EnumAdcSetIndexType enAdcIndex = EnumAdcSetIndexType.values()[iAdcIdx];
            Log.d(TAG, "setADCGainOffset");
            fm.setAdcGainOffset(enWin, enAdcIndex, adcCalibData);
            factoryDB.updateADCAdjust(adcCalibData, iAdcIdx);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean setADCRedGain(int redGain) {
        int idx = eAdcIdx.ordinal();
        adcCalibData.redGain = redGain;
        setADCGainOffset(EnumScalerWindow.E_MAIN_WINDOW, idx);
        return true;
    }

    @Override
    public int getADCRedGain() {
        return adcCalibData.redGain;
    }

    @Override
    public boolean setADCGreenGain(int greenGain) {
        int idx = eAdcIdx.ordinal();
        adcCalibData.greenGain = greenGain;
        setADCGainOffset(EnumScalerWindow.E_MAIN_WINDOW, idx);
        return true;
    }

    @Override
    public int getADCGreenGain() {
        return adcCalibData.greenGain;
    }

    @Override
    public boolean setADCBlueGain(int blueGain) {
        int idx = eAdcIdx.ordinal();
        adcCalibData.blueGain = blueGain;
        setADCGainOffset(EnumScalerWindow.E_MAIN_WINDOW, idx);
        return true;
    }

    @Override
    public int getADCBlueGain() {
        return adcCalibData.blueGain;
    }

    @Override
    public boolean setADCRedOffset(int redOffset) {
        int idx = eAdcIdx.ordinal();
        adcCalibData.redOffset = redOffset;
        setADCGainOffset(EnumScalerWindow.E_MAIN_WINDOW, idx);
        return true;
    }

    @Override
    public int getADCRedOffset() {
        return adcCalibData.redOffset;
    }

    @Override
    public boolean setADCGreenOffset(int greenOffset) {
        int idx = eAdcIdx.ordinal();
        adcCalibData.greenOffset = greenOffset;
        setADCGainOffset(EnumScalerWindow.E_MAIN_WINDOW, idx);
        return true;
    }

    @Override
    public int getADCGreenOffset() {
        return adcCalibData.greenOffset;
    }

    @Override
    public boolean setADCBlueOffset(int blueOffset) {
        int idx = eAdcIdx.ordinal();
        adcCalibData.blueOffset = blueOffset;
        setADCGainOffset(EnumScalerWindow.E_MAIN_WINDOW, idx);
        return true;
    }

    @Override
    public int getADCBlueOffset() {
        return adcCalibData.blueOffset;
    }

    @Override
    public boolean setADCPhase(int phase) {
        f_phase = phase;

        try {
            tp.setPhase(f_phase);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getADCPhase() {
        return f_phase;
    }

    @Override
    public boolean ExecAutoADC(int currentSourceindexAdc) {
        boolean isCurrentYpbprHd = false;
        boolean isCurrentInputSourceHd = false;
        int currentInputSource = tvCommonManager.getCurrentTvInputSource();

        if ((TvCommonManager.INPUT_SOURCE_YPBPR <= currentInputSource)
                && (TvCommonManager.INPUT_SOURCE_YPBPR_MAX >= currentInputSource)) {
            if (tvCommonManager.isSignalStable(currentInputSource) == true) {
                int currentResolution = GetResolutionMapping(EnumInputSource.E_INPUT_SOURCE_YPBPR);
                if ((IFactoryDesk.MAX_YPbPr_Resolution_Info.E_YPbPr720p_60.ordinal() <= currentResolution)
                        && (IFactoryDesk.MAX_YPbPr_Resolution_Info.E_YPbPr1080p_25.ordinal() >= currentResolution)) {
                    isCurrentInputSourceHd = true;
                }

                switch (E_ADC_SET_INDEX.values()[currentSourceindexAdc]) {
                    case ADC_SET_YPBPR_HD:
                    case ADC_SET_YPBPR2_HD:
                    case ADC_SET_YPBPR3_HD:
                        isCurrentYpbprHd = true;
                        break;
                    default:
                        break;
                }
            }
        }

        if (isCurrentInputSourceHd != isCurrentYpbprHd) {
            FactoryDeskImpl.this.getHandler(1).sendEmptyMessage(AUTOTUNE_TIMING_MODE_ERR);
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (FactoryDeskImpl.this.getHandler(1) != null) {
                        boolean autotune_flag = false;
                        FactoryDeskImpl.this.getHandler(1).sendEmptyMessage(AUTOTUNE_START);
                        try {
                            autotune_flag = fm.autoAdc();
                        } catch (TvCommonException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (autotune_flag) {
                            FactoryDeskImpl.this.getHandler(1).sendEmptyMessage(
                                    AUTOTUNE_END_SUCESSED);
                        } else {
                            FactoryDeskImpl.this.getHandler(1)
                                    .sendEmptyMessage(AUTOTUNE_END_FAILED);
                        }
                    }

                }
            }).start();
        }
        return true;
    }

    @Override
    public boolean setAdcIdx(E_ADC_SET_INDEX eIdx) {
        eAdcIdx = eIdx;
        return true;
    }

    @Override
    public E_ADC_SET_INDEX getAdcIdx() {
        return eAdcIdx;
    }

    /*************************************************************************
     * WhiteBalance adjust setting
     ************************************************************************/
    public boolean changeWBParaWhenSourceChange() {

        return true;
    }

    public boolean setWBIdx(EnumInputSource eIdx) {
        eWBIdx = eIdx;
        return true;
    }

    public EnumInputSource getWBIdx() {
        return eWBIdx;
    }

    private void setWBGainOffset(int iColorTemp, int curInputSrc, int srcId) {
        try {
            EnumColorTemperature enColorTemp = EnumColorTemperature.values()[iColorTemp + 1];
            EnumInputSource enSrc = EnumInputSource.values()[curInputSrc];// EN_MS_COLOR_TEMP_INPUT_SOURCE
                                                                          // switch
                                                                          // to
                                                                          // EnumInputSource
            // /here
            fm.setWbGainOffsetEx(enColorTemp,
                    factoryColorTmpEx.astColorTempEx[iColorTemp][srcId].redgain,
                    factoryColorTmpEx.astColorTempEx[iColorTemp][srcId].greengain,
                    factoryColorTmpEx.astColorTempEx[iColorTemp][srcId].bluegain,
                    factoryColorTmpEx.astColorTempEx[iColorTemp][srcId].redoffset,
                    factoryColorTmpEx.astColorTempEx[iColorTemp][srcId].greenoffset,
                    factoryColorTmpEx.astColorTempEx[iColorTemp][srcId].blueoffset, enSrc);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        // /here
        factoryDB.updateFactoryColorTempExData(factoryColorTmpEx.astColorTempEx[iColorTemp][srcId],
                srcId, iColorTemp);
        // updateFactoryColorTempExData(vo, source, colorid);
        // factoryDB.updateFactoryColorTempExData(getFactoryColorTempData(), int
        // sourceId, int colorTmpId)
    }

    public EN_MS_COLOR_TEMP_INPUT_SOURCE InputSourceTransfer(EnumInputSource inputSrc) {
        EN_MS_COLOR_TEMP_INPUT_SOURCE outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_NONE;
        switch (inputSrc) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_VGA;
                break;
            case E_INPUT_SOURCE_ATV:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_ATV;
                break;
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_CVBS_MAX:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_CVBS;
                break;
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SVIDEO_MAX:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_SVIDEO;
                break;
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3:
            case E_INPUT_SOURCE_YPBPR_MAX:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_YPBPR;
                break;
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2:
            case E_INPUT_SOURCE_SCART_MAX:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_SCART;
                break;
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4:
            case E_INPUT_SOURCE_DVI:
            case E_INPUT_SOURCE_DVI2:
            case E_INPUT_SOURCE_DVI3:
            case E_INPUT_SOURCE_DVI4:
            case E_INPUT_SOURCE_DVI_MAX:
            case E_INPUT_SOURCE_HDMI_MAX:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_HDMI;
                break;
            case E_INPUT_SOURCE_DTV:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_DTV;
                break;
            case E_INPUT_SOURCE_STORAGE:
            case E_INPUT_SOURCE_KTV:
            case E_INPUT_SOURCE_JPEG:
            case E_INPUT_SOURCE_DTV2:
            case E_INPUT_SOURCE_STORAGE2:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_OTHERS;
                break;
            case E_INPUT_SOURCE_NUM:
            case E_INPUT_SOURCE_NONE:
                outputSrc = EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_NONE;
                break;
            default:
                break;
        }
        return outputSrc;
    }

    @Override
    public boolean setWbRedGain(short redGain) {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        factoryColorTmpEx.astColorTempEx[idxC][srcId].redgain = redGain;
        setWBGainOffset(idxC, curInputSrc, srcId);
        return true;
    }

    @Override
    public int getWbRedGain() {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        return factoryColorTmpEx.astColorTempEx[idxC][srcId].redgain;
    }

    @Override
    public boolean setWbGreenGain(short greenGain) {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        factoryColorTmpEx.astColorTempEx[idxC][srcId].greengain = greenGain;
        setWBGainOffset(idxC, curInputSrc, srcId);
        return true;
    }

    @Override
    public int getWbGreenGain() {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        return factoryColorTmpEx.astColorTempEx[idxC][srcId].greengain;
    }

    @Override
    public boolean setWbBlueGain(short blueGain) {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        factoryColorTmpEx.astColorTempEx[idxC][srcId].bluegain = blueGain;
        setWBGainOffset(idxC, curInputSrc, srcId);
        return true;
    }

    @Override
    public int getWbBlueGain() {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        return factoryColorTmpEx.astColorTempEx[idxC][srcId].bluegain;
    }

    @Override
    public boolean setWbRedOffset(short redOffset) {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        factoryColorTmpEx.astColorTempEx[idxC][srcId].redoffset = redOffset;
        setWBGainOffset(idxC, curInputSrc, srcId);
        return true;
    }

    @Override
    public int getWbRedOffset() {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        return factoryColorTmpEx.astColorTempEx[idxC][srcId].redoffset;
    }

    @Override
    public boolean setWbGreenOffset(short greenOffset) {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        factoryColorTmpEx.astColorTempEx[idxC][srcId].greenoffset = greenOffset;
        setWBGainOffset(idxC, curInputSrc, srcId);
        return true;
    }

    @Override
    public int getWbGreenOffset() {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        return factoryColorTmpEx.astColorTempEx[idxC][srcId].greenoffset;
    }

    @Override
    public boolean setWbBlueOffset(short blueOffset) {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        factoryColorTmpEx.astColorTempEx[idxC][srcId].blueoffset = blueOffset;
        setWBGainOffset(idxC, curInputSrc, srcId);
        return true;
    }

    @Override
    public int getWbBlueOffset() {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        return factoryColorTmpEx.astColorTempEx[idxC][srcId].blueoffset;
    }

    /**************************************************************************
     * abnormal items setting
     *************************************************************************/
    private void InitVif() {
        try {
            matvplayer.initAtvVif();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean setVifTop(short vifTop) {
        vifSet.VifTop = vifTop;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public short getVifTop() {
        return vifSet.VifTop;
    }

    @Override
    public boolean setVifVgaMaximum(int vifVgaMaximum) {
        vifSet.VifVgaMaximum = vifVgaMaximum;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public int getVifVgaMaximum() {
        return vifSet.VifVgaMaximum;
    }

    @Override
    public boolean setVifCrKp(short vifCrKp) {
        vifSet.VifCrKp = vifCrKp;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public short getVifCrKp() {
        return vifSet.VifCrKp;
    }

    @Override
    public boolean setVifCrKi(short vifCrKi) {
        vifSet.VifCrKi = vifCrKi;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public short getVifCrKi() {
        return vifSet.VifCrKi;
    }

    @Override
    public boolean setVifAsiaSignalOption(boolean vifAsiaSignalOption) {
        vifSet.VifAsiaSignalOption = vifAsiaSignalOption;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public boolean getVifAsiaSignalOption() {
        return vifSet.VifAsiaSignalOption;
    }

    @Override
    public boolean setVifCrKpKiAdjust(boolean vifCrKpKiAdjust) {
        vifSet.VifCrKpKiAdjust = vifCrKpKiAdjust;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public boolean getVifCrKpKiAdjust() {
        return vifSet.VifCrKpKiAdjust;
    }

    @Override
    public boolean setVifOverModulation(boolean vifOverModulation) {
        vifSet.VifOverModulation = vifOverModulation;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public boolean getVifOverModulation() {
        return vifSet.VifOverModulation;
    }

    @Override
    public boolean setVifClampGainOvNegative(int vifClampGainOvNegative) {
        vifSet.VifClampgainGainOvNegative = vifClampGainOvNegative;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public int getVifClampGainOvNegative() {
        return vifSet.VifClampgainGainOvNegative;
    }

    @Override
    public boolean setChinaDescramblerBox(short chinaDescramblerBox) {
        vifSet.ChinaDescramblerBox = chinaDescramblerBox;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public boolean saveChinaDescramblerBox(short chinaDescramblerBox) {
        vifSet.ChinaDescramblerBox = chinaDescramblerBox;
        factoryDB.updateNonStandardAdjust(vifSet);
        return true;
    }

    @Override
    public boolean setChinaDescramblerBoxDelay(int chinaDescramblerBoxDelay) {
        vifSet.ChinaDescramblerBoxDelay = chinaDescramblerBoxDelay;
        factoryDB.updateNonStandardAdjust(vifSet);
        try {
            TvManager
                    .getInstance()
                    .getChannelManager()
                    .selectProgram(
                            TvManager.getInstance().getChannelManager().getCurrChannelNumber(),
                            (short) 0, 0x00);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short getChinaDescramblerBox() {
        return vifSet.ChinaDescramblerBox;
    }

    @Override
    public double getChinaDescramblerBoxDelay() {
        return vifSet.ChinaDescramblerBoxDelay;
    }

    @Override
    public boolean setDelayReduce(short delayReduce) {
        vifSet.VifDelayReduce = delayReduce;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public short getDelayReduce() {
        return vifSet.VifDelayReduce;
    }

    @Override
    public boolean setVifCrThr(int vifCrThr) {
        vifSet.VifCrThr = vifCrThr;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public int getVifCrThr() {
        return vifSet.VifCrThr;
    }

    @Override
    public boolean setVifVersion(short vifVersion) {
        vifSet.VifVersion = vifVersion;
        factoryDB.updateNonStandardAdjust(vifSet);
        return true;
    }

    @Override
    public short getVifVersion() {
        return vifSet.VifVersion;
    }

    @Override
    public boolean setVifAgcRef(short vifAgcRef) {
        vifSet.VifAgcRefNegative = vifAgcRef;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public short getVifAgcRef() {
        return vifSet.VifAgcRefNegative;
    }

    @Override
    public boolean setGainDistributionThr(int gainDistributionThr) {
        vifSet.GainDistributionThr = gainDistributionThr;
        factoryDB.updateNonStandardAdjust(vifSet);
        InitVif();
        return true;
    }

    @Override
    public int getGainDistributionThr() {
        return vifSet.GainDistributionThr;
    }

    @Override
    public boolean setAEFC_D4(short AEFC_D4) {
        facvdpara.aFEC_D4 = AEFC_D4;

        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_D4() {
        return facvdpara.aFEC_D4;
    }

    @Override
    public boolean setAEFC_D5Bit2(short AEFC_D5Bit2) {
        facvdpara.aFEC_D5_Bit2 = AEFC_D5Bit2;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_D5Bit2() {
        return facvdpara.aFEC_D5_Bit2;
    }

    @Override
    public boolean setAEFC_D8Bit3210(short AEFC_D8Bit3210) {
        facvdpara.aFEC_D8_Bit3210 = AEFC_D8Bit3210;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_D8Bit3210() {
        return facvdpara.aFEC_D8_Bit3210;
    }

    @Override
    public boolean setAEFC_D9Bit0(short AEFC_D9Bit0) {
        facvdpara.aFEC_D9_Bit0 = AEFC_D9Bit0;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_D9Bit0() {
        return facvdpara.aFEC_D9_Bit0;
    }

    @Override
    public boolean setAEFC_D7HighBoun(short AEFC_D7HighBoun) {
        facvdpara.aFEC_D7_HIGH_BOUND = AEFC_D7HighBoun;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_D7HighBoun() {
        return facvdpara.aFEC_D7_HIGH_BOUND;
    }

    @Override
    public boolean setAEFC_D7LowBoun(short AEFC_D7LowBoun) {
        facvdpara.aFEC_D7_LOW_BOUND = AEFC_D7LowBoun;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_D7LowBoun() {
        return facvdpara.aFEC_D7_LOW_BOUND;
    }

    @Override
    public boolean setAEFC_A0(short AEFC_A0) {
        facvdpara.aFEC_A0 = AEFC_A0;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_A0() {
        return facvdpara.aFEC_A0;
    }

    @Override
    public boolean setAEFC_A1(short AEFC_A1) {
        facvdpara.aFEC_A1 = AEFC_A1;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_A1() {
        return facvdpara.aFEC_A1;
    }

    @Override
    public boolean setAEFC_66Bit76(short AEFC_66Bit76) {
        facvdpara.aFEC_66_Bit76 = AEFC_66Bit76;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_66Bit76() {
        return facvdpara.aFEC_66_Bit76;
    }

    @Override
    public boolean setAEFC_6EBit7654(short AEFC_6EBit7654) {
        facvdpara.aFEC_6E_Bit7654 = AEFC_6EBit7654;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_6EBit7654() {
        return facvdpara.aFEC_6E_Bit7654;
    }

    @Override
    public boolean setAEFC_6EBit3210(short AEFC_6EBit3210) {
        facvdpara.aFEC_6E_Bit3210 = AEFC_6EBit3210;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_6EBit3210() {
        return facvdpara.aFEC_6E_Bit3210;
    }

    @Override
    public boolean setAEFC_43(short AEFC_43) {
        facvdpara.aFEC_43 = AEFC_43;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_43() {
        return facvdpara.aFEC_43;
    }

    @Override
    public boolean setAEFC_44(short AEFC_44) {
        facvdpara.aFEC_44 = AEFC_44;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_44() {
        return facvdpara.aFEC_44;
    }

    @Override
    public boolean setAEFC_CB(short AEFC_CB) {
        facvdpara.aFEC_CB = AEFC_CB;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_CB() {
        return facvdpara.aFEC_CB;
    }

    @Override
    public boolean setAEFC_CFBit2_ATV(short AEFC_CFBit2_ATV) {
        facvdpara.aFEC_CF_Bit2_ATV = AEFC_CFBit2_ATV;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_CFBit2_ATV() {
        return facvdpara.aFEC_CF_Bit2_ATV;
    }

    @Override
    public boolean setAEFC_CFBit2_AV(short AEFC_CFBit2_AV) {
        facvdpara.aFEC_CF_Bit2_AV = AEFC_CFBit2_AV;
        try {
            fm.setFactoryVdInitParameter(facvdpara);
            fm.setFactoryVdParameter(facvdpara);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateNonStandardAdjust(facvdpara);
        return true;
    }

    @Override
    public short getAEFC_CFBit2_AV() {
        return facvdpara.aFEC_CF_Bit2_AV;
    }

    @Override
    public boolean setVdDspVersion(short vdDspVersion) {
        factoryExternSetting.vdDspVersion = vdDspVersion;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        return true;
    }

    @Override
    public short getVdDspVersion() {
        return factoryExternSetting.vdDspVersion;
    }

    @Override
    public boolean setAudioHiDevMode(int audioHiDevMode) {
        factoryExternSetting.eHidevMode = audioHiDevMode;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        try {
            am.setAtvInfo(EnumAtvInfoType.E_ATV_HIDEV_INFO,
                    EnumSoundHidevMode.values()[audioHiDevMode]);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getAudioHiDevMode() {
        return factoryExternSetting.eHidevMode;
    }

    @Override
    public boolean setAudioNrThr(short audioNrThr) {
        factoryExternSetting.audioNrThr = audioNrThr;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        am.setSoundParameter(EnumSoundSetParamType.E_SOUND_SET_PARAM_NR_THRESHOLD_, audioNrThr, 0);
        return true;
    }

    @Override
    public short getAudioNrThr() {
        return factoryExternSetting.audioNrThr;
    }

    @Override
    public boolean setAudioSifThreshold(short audioSifThreshold) {
        factoryExternSetting.audioSifThreshold = audioSifThreshold;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        return true;
    }

    @Override
    public short getAudioSifThreshold() {
        return factoryExternSetting.audioSifThreshold;
    }

    @Override
    public boolean setAudioDspVersion(short aduioDspVersion) {
        factoryExternSetting.audioDspVersion = aduioDspVersion;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        return true;
    }

    @Override
    public short getAudioDspVersion() {
        return factoryExternSetting.audioDspVersion;
    }

    /*************************************************************************
     * Nonlinear setting
     ************************************************************************/
    @Override
    public boolean setCurveType(MS_NLA_SET_INDEX curveTypeIndex) {
        mfactoryNLASet.msNlaSetIndex = curveTypeIndex;
        return true;
    }

    @Override
    public MS_NLA_SET_INDEX getCurveType() {
        return mfactoryNLASet.msNlaSetIndex;
    }

    @Override
    public boolean setOsdV0Nonlinear(int nonlinearVal) {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V0 = nonlinearVal;
        factoryDB.updateNonLinearAdjust(mfactoryNLASet.stNLASetting[typeIndex], typeIndex);
		if(typeIndex == MS_NLA_SET_INDEX.EN_NLA_BACKLIGHT.ordinal()){
			if(IsIniPWMNonLinearEnble()){
				SetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_0.ordinal(),nonlinearVal);
			}	
		}
        switch (mfactoryNLASet.msNlaSetIndex) {
            case EN_NLA_VOLUME:
                try {
                    am.setAudioVolume(EnumAudioVolumeSourceType.E_VOL_SOURCE_SPEAKER_OUT,
                            (byte) nonlinearVal);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BRIGHTNESS:
                try {
                    pm.setPictureModeBrightness((short) 0);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_CONTRAST:
                try {
                    pm.setPictureModeContrast((short) 0);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SATURATION:
                try {
                    pm.setPictureModeColor((short) 0);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SHARPNESS:
                try {
                    pm.setPictureModeSharpness((short) 0);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_HUE:
                try {
                    pm.setPictureModeTint((short) 0);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BACKLIGHT:
                try {
                    pm.setBacklight(0);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public int getOsdV0Nonlinear() {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        return mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V0;
    }

    @Override
    public boolean setOsdV25Nonlinear(int nonlinearVal) {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V25 = nonlinearVal;
        factoryDB.updateNonLinearAdjust(mfactoryNLASet.stNLASetting[typeIndex], typeIndex);
		if(typeIndex == MS_NLA_SET_INDEX.EN_NLA_BACKLIGHT.ordinal()){
			if(IsIniPWMNonLinearEnble()){
				SetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_25.ordinal(),nonlinearVal);
			}	
		}
        switch (mfactoryNLASet.msNlaSetIndex) {
            case EN_NLA_VOLUME:
                try {
                    am.setAudioVolume(EnumAudioVolumeSourceType.E_VOL_SOURCE_SPEAKER_OUT,
                            (byte) nonlinearVal);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BRIGHTNESS:
                try {
                    pm.setPictureModeBrightness((short) 25);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_CONTRAST:
                try {
                    pm.setPictureModeContrast((short) 25);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SATURATION:
                try {
                    pm.setPictureModeColor((short) 25);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SHARPNESS:
                try {
                    pm.setPictureModeSharpness((short) 25);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_HUE:
                try {
                    pm.setPictureModeTint((short) 25);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BACKLIGHT:
                try {
                    pm.setBacklight(25);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public int getOsdV25Nonlinear() {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        return mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V25;
    }

    @Override
    public boolean setOsdV50Nonlinear(int nonlinearVal) {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V50 = nonlinearVal;
        factoryDB.updateNonLinearAdjust(mfactoryNLASet.stNLASetting[typeIndex], typeIndex);
		if(typeIndex == MS_NLA_SET_INDEX.EN_NLA_BACKLIGHT.ordinal()){
			if(IsIniPWMNonLinearEnble()){
				SetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_50.ordinal(),nonlinearVal);
			}	
		}
        switch (mfactoryNLASet.msNlaSetIndex) {
            case EN_NLA_VOLUME:
                try {
                    am.setAudioVolume(EnumAudioVolumeSourceType.E_VOL_SOURCE_SPEAKER_OUT,
                            (byte) nonlinearVal);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BRIGHTNESS:
                try {
                    pm.setPictureModeBrightness((short) 50);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_CONTRAST:
                try {
                    pm.setPictureModeContrast((short) 50);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SATURATION:
                try {
                    pm.setPictureModeColor((short) 50);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SHARPNESS:
                try {
                    pm.setPictureModeSharpness((short) 50);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_HUE:
                try {
                    pm.setPictureModeTint((short) 50);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BACKLIGHT:
                try {
                    pm.setBacklight(50);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public int getOsdV50Nonlinear() {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        return mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V50;
    }

    @Override
    public boolean setOsdV75Nonlinear(int nonlinearVal) {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V75 = nonlinearVal;
        factoryDB.updateNonLinearAdjust(mfactoryNLASet.stNLASetting[typeIndex], typeIndex);
		if(typeIndex == MS_NLA_SET_INDEX.EN_NLA_BACKLIGHT.ordinal()){
			if(IsIniPWMNonLinearEnble()){
				SetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_75.ordinal(),nonlinearVal);
			}	
		}
        switch (mfactoryNLASet.msNlaSetIndex) {
            case EN_NLA_VOLUME:
                try {
                    am.setAudioVolume(EnumAudioVolumeSourceType.E_VOL_SOURCE_SPEAKER_OUT,
                            (byte) nonlinearVal);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BRIGHTNESS:
                try {
                    pm.setPictureModeBrightness((short) 75);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_CONTRAST:
                try {
                    pm.setPictureModeContrast((short) 75);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SATURATION:
                try {
                    pm.setPictureModeColor((short) 75);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SHARPNESS:
                try {
                    pm.setPictureModeSharpness((short) 75);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_HUE:
                try {
                    pm.setPictureModeTint((short) 75);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BACKLIGHT:
                try {
                    pm.setBacklight(75);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public int getOsdV75Nonlinear() {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        return mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V75;
    }

    @Override
    public boolean setOsdV100Nonlinear(int nonlinearVal) {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V100 = nonlinearVal;
        factoryDB.updateNonLinearAdjust(mfactoryNLASet.stNLASetting[typeIndex], typeIndex);
		if(typeIndex == MS_NLA_SET_INDEX.EN_NLA_BACKLIGHT.ordinal()){
			if(IsIniPWMNonLinearEnble()){
				SetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_100.ordinal(),nonlinearVal);
			}	
		}
        switch (mfactoryNLASet.msNlaSetIndex) {
            case EN_NLA_VOLUME:
                try {
                    am.setAudioVolume(EnumAudioVolumeSourceType.E_VOL_SOURCE_SPEAKER_OUT,
                            (byte) nonlinearVal);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BRIGHTNESS:
                try {
                    pm.setPictureModeBrightness((short) 100);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_CONTRAST:
                try {
                    pm.setPictureModeContrast((short) 100);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SATURATION:
                try {
                    pm.setPictureModeColor((short) 100);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_SHARPNESS:
                try {
                    pm.setPictureModeSharpness((short) 100);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_HUE:
                try {
                    pm.setPictureModeTint((short) 100);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            case EN_NLA_BACKLIGHT:
                try {
                    pm.setBacklight(100);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public int getOsdV100Nonlinear() {
        int typeIndex = mfactoryNLASet.msNlaSetIndex.ordinal();
        return mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V100;
    }

    /**************************************************************************
     * Overscan setting
     *************************************************************************/
    private EnumInputSource overSacnSourceType = EnumInputSource.E_INPUT_SOURCE_ATV;

    @Override
    public boolean setOverScanSourceType(EnumInputSource SourceType) {
        try {
            TvManager.getInstance().setInputSource(SourceType);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        overSacnSourceType = SourceType;

        return true;
    }

    @Override
    public EnumInputSource getOverScanSourceType() {
        int sourceTypeIndex = factoryDB.queryCurInputSrc();
        if (sourceTypeIndex > EnumInputSource.E_INPUT_SOURCE_DVI4.ordinal()) {
            overSacnSourceType = EnumInputSource.E_INPUT_SOURCE_ATV;
        } else {
            overSacnSourceType = EnumInputSource.values()[sourceTypeIndex];
        }
        return overSacnSourceType;
    }

    @Override
    public boolean setOverScanHsize(short hSize) {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int currentResolution = GetResolutionMapping(overSacnSourceType);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }
        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right = hSize;
                factoryDB.updateOverscanAdjust(eVDSinType.ordinal(), arcMode, factoryVDOverscanSet);
                try {
                    pm.setOverscan(
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                return true;
            }
            case E_INPUT_SOURCE_ATV:

                EN_VD_SIGNALTYPE eVDSinType;
                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right = hSize;
                factoryDB.updateATVOverscanAdjust(eVDSinType.ordinal(), arcMode,
                        factoryATVOverscanSet);
                try {
                    pm.setOverscan(
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                return true;

            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Right = hSize;
                factoryDB.updateYPbPrOverscanAdjust(currentResolution, arcMode,
                        factoryYPbPrOverscanSet);
                try {
                    pm.setOverscan(
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Right = hSize;
                factoryDB.updateHDMIOverscanAdjust(currentResolution, arcMode,
                        factoryHDMIOverscanSet);
                try {
                    pm.setOverscan(factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                return true;
            }
            case E_INPUT_SOURCE_DTV: {
                factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Right = hSize;
                factoryDB
                        .updateDTVOverscanAdjust(currentResolution, arcMode, factoryDTVOverscanSet);
                try {
                    pm.setOverscan(factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            default:
                break;
        }
        return false;
    }

    @Override
    public short getOverScanHsize() {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }
        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right;
            }
            case E_INPUT_SOURCE_ATV: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right;
            }
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                return factoryYPbPrOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8HCrop_Right;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                return factoryHDMIOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8HCrop_Right;
            }
            case E_INPUT_SOURCE_DTV: {
                return factoryDTVOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8HCrop_Right;
            }
            default:
                break;
        }
        return -1;
    }

    @Override
    public boolean setOverScanHposition(short hPosition) {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int currentResolution = GetResolutionMapping(overSacnSourceType);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }

        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left = hPosition;
                factoryDB.updateOverscanAdjust(eVDSinType.ordinal(), arcMode, factoryVDOverscanSet);
                try {
                    pm.setOverscan(
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                return true;
            }
            case E_INPUT_SOURCE_ATV: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left = hPosition;
                factoryDB.updateATVOverscanAdjust(eVDSinType.ordinal(), arcMode,
                        factoryATVOverscanSet);
                try {
                    pm.setOverscan(
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                return true;
            }
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Left = hPosition;
                factoryDB.updateYPbPrOverscanAdjust(currentResolution, arcMode,
                        factoryYPbPrOverscanSet);
                try {
                    pm.setOverscan(
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                return true;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Left = hPosition;
                factoryDB.updateHDMIOverscanAdjust(currentResolution, arcMode,
                        factoryHDMIOverscanSet);
                try {
                    pm.setOverscan(factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_DTV: {
                factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Left = hPosition;
                factoryDB
                        .updateDTVOverscanAdjust(currentResolution, arcMode, factoryDTVOverscanSet);
                try {
                    pm.setOverscan(factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            default:
                break;
        }
        return false;
    }

    @Override
    public short getOverScanHposition() {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }

        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left;
            }
            case E_INPUT_SOURCE_ATV: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left;
            }
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                return factoryYPbPrOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8HCrop_Left;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                return factoryHDMIOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8HCrop_Left;
            }
            case E_INPUT_SOURCE_DTV: {
                return factoryDTVOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8HCrop_Left;
            }
            default:
                break;
        }
        return -1;
    }

    @Override
    public boolean setOverScanVsize(short vSize) {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int currentResolution = GetResolutionMapping(overSacnSourceType);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }

        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up = vSize;
                factoryDB.updateOverscanAdjust(eVDSinType.ordinal(), arcMode, factoryVDOverscanSet);
                try {
                    pm.setOverscan(
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_ATV: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up = vSize;
                factoryDB.updateATVOverscanAdjust(eVDSinType.ordinal(), arcMode,
                        factoryATVOverscanSet);
                try {
                    pm.setOverscan(
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Up = vSize;
                factoryDB.updateYPbPrOverscanAdjust(currentResolution, arcMode,
                        factoryYPbPrOverscanSet);
                try {
                    pm.setOverscan(
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Up = vSize;
                factoryDB.updateHDMIOverscanAdjust(currentResolution, arcMode,
                        factoryHDMIOverscanSet);
                try {
                    pm.setOverscan(factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }
                return true;
            }
            case E_INPUT_SOURCE_DTV: {
                factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Up = vSize;
                factoryDB
                        .updateDTVOverscanAdjust(currentResolution, arcMode, factoryDTVOverscanSet);
                try {
                    pm.setOverscan(factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            default:
                break;
        }
        return false;
    }

    @Override
    public short getOverScanVsize() {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }

        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up;
            }
            case E_INPUT_SOURCE_ATV: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up;
            }
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                return factoryYPbPrOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8VCrop_Up;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                return factoryHDMIOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8VCrop_Up;
            }
            case E_INPUT_SOURCE_DTV: {
                return factoryDTVOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8VCrop_Up;
            }
            default:
                break;
        }
        return -1;
    }

    @Override
    public boolean setOverScanVposition(short vPosition) {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int currentResolution = GetResolutionMapping(overSacnSourceType);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }

        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down = vPosition;
                factoryDB.updateOverscanAdjust(eVDSinType.ordinal(), arcMode, factoryVDOverscanSet);
                try {
                    pm.setOverscan(
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_ATV: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down = vPosition;
                factoryDB.updateATVOverscanAdjust(eVDSinType.ordinal(), arcMode,
                        factoryATVOverscanSet);
                try {
                    pm.setOverscan(
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Up,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Right,
                            factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Down = vPosition;
                factoryDB.updateYPbPrOverscanAdjust(currentResolution, arcMode,
                        factoryYPbPrOverscanSet);
                try {
                    pm.setOverscan(
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryYPbPrOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Down = vPosition;
                factoryDB.updateHDMIOverscanAdjust(currentResolution, arcMode,
                        factoryHDMIOverscanSet);
                try {
                    pm.setOverscan(factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryHDMIOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            case E_INPUT_SOURCE_DTV: {
                factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Down = vPosition;
                factoryDB
                        .updateDTVOverscanAdjust(currentResolution, arcMode, factoryDTVOverscanSet);
                try {
                    pm.setOverscan(factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Down,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8VCrop_Up,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Right,
                            factoryDTVOverscanSet[currentResolution][arcMode].u8HCrop_Left);
                } catch (TvCommonException e) {
                    e.printStackTrace();
                }

                return true;
            }
            default:
                break;
        }
        return false;
    }

    @Override
    public short getOverScanVposition() {
        int inputSrc = factoryDB.queryCurInputSrc();
        int arcMode = factoryDB.queryArcMode(inputSrc);
        int videoStandard = 0;
        try {
            videoStandard = mtvplayer.getVideoStandard().ordinal();
        } catch (TvCommonException e1) {
            e1.printStackTrace();
        }
        MAPI_AVD_VideoStandardType lastVideoStandardMode = MAPI_AVD_VideoStandardType.values()[videoStandard];
        switch (overSacnSourceType) {
            case E_INPUT_SOURCE_VGA:
            case E_INPUT_SOURCE_VGA2:
            case E_INPUT_SOURCE_VGA3:
            case E_INPUT_SOURCE_CVBS:
            case E_INPUT_SOURCE_CVBS2:
            case E_INPUT_SOURCE_CVBS3:
            case E_INPUT_SOURCE_CVBS4:
            case E_INPUT_SOURCE_CVBS5:
            case E_INPUT_SOURCE_CVBS6:
            case E_INPUT_SOURCE_CVBS7:
            case E_INPUT_SOURCE_CVBS8:
            case E_INPUT_SOURCE_SVIDEO:
            case E_INPUT_SOURCE_SVIDEO2:
            case E_INPUT_SOURCE_SVIDEO3:
            case E_INPUT_SOURCE_SVIDEO4:
            case E_INPUT_SOURCE_SCART:
            case E_INPUT_SOURCE_SCART2: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryVDOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down;
            }
            case E_INPUT_SOURCE_ATV: {
                EN_VD_SIGNALTYPE eVDSinType;

                switch (lastVideoStandardMode) {
                    case E_MAPI_VIDEOSTANDARD_PAL_BGHI:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_SECAM:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_SECAM;
                        break;
                    case E_MAPI_VIDEOSTANDARD_NTSC_44:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_M:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_M;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_N:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL_NC;
                        break;
                    case E_MAPI_VIDEOSTANDARD_PAL_60:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_NTSC_443;
                        break;
                    default:
                        eVDSinType = EN_VD_SIGNALTYPE.SIG_PAL;
                        break;
                }
                return factoryATVOverscanSet[eVDSinType.ordinal()][arcMode].u8VCrop_Down;
            }
            case E_INPUT_SOURCE_YPBPR:
            case E_INPUT_SOURCE_YPBPR2:
            case E_INPUT_SOURCE_YPBPR3: {
                return factoryYPbPrOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8VCrop_Down;
            }
            case E_INPUT_SOURCE_HDMI:
            case E_INPUT_SOURCE_HDMI2:
            case E_INPUT_SOURCE_HDMI3:
            case E_INPUT_SOURCE_HDMI4: {
                return factoryHDMIOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8VCrop_Down;
            }
            case E_INPUT_SOURCE_DTV: {
                return factoryDTVOverscanSet[GetResolutionMapping(overSacnSourceType)][arcMode].u8VCrop_Down;
            }
            default:
                break;
        }
        return -1;
    }

    private int GetResolutionMapping(EnumInputSource enCurrentInputType) {
        int currentResolution = 0;

        try {
            currentResolution = fm.getResolutionMappingIndex(enCurrentInputType);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }

        return currentResolution;
    }

    /**************************************************************************
     * SSC setting
     *************************************************************************/
    private void UpdateSscPara() {
        try {
            fm.updateSscParameter();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    public byte[] GetEnableIPInfo() {
        byte[] midbytes = null;
        try {
            midbytes = fm.getEnableIPInfo();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }

        return midbytes;
    }

    @Override
    public boolean setMIUenable(boolean Miu_SscEnable) {
        sscSet.Miu_SscEnable = Miu_SscEnable;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public boolean getMIUenalbe() {
        return sscSet.Miu_SscEnable;
    }

    @Override
    public boolean setMIUmodulation(int Miu_SscSpan) {
        sscSet.Miu0_SscSpan = Miu_SscSpan;
        sscSet.Miu1_SscSpan = Miu_SscSpan;
        sscSet.Miu2_SscSpan = Miu_SscSpan;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getMIUmodulation() {
        return sscSet.Miu0_SscSpan;
    }

    @Override
    public boolean setMIUpercentage(int Miu_SscStep) {
        sscSet.Miu0_SscStep = Miu_SscStep;
        sscSet.Miu1_SscStep = Miu_SscStep;
        sscSet.Miu2_SscStep = Miu_SscStep;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getMIUpercentage() {
        return sscSet.Miu0_SscStep;
    }

    @Override
    public boolean setLVDSenable(boolean Lvds_SscEnable) {
        sscSet.Lvds_SscEnable = Lvds_SscEnable;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public boolean getLVDSenalbe() {
        return sscSet.Lvds_SscEnable;
    }

    @Override
    public boolean setLVDSmodulation(int Lvds_SscSpan) {
        sscSet.Lvds_SscSpan = Lvds_SscSpan;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getLVDSmodulation() {
        return sscSet.Lvds_SscSpan;
    }

    @Override
    public boolean setLVDSpercentage(int Lvds_SscStep) {
        sscSet.Lvds_SscStep = Lvds_SscStep;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getLVDSpercentage() {
        return sscSet.Lvds_SscStep;
    }

    // for einsteinU
    @Override
    public boolean setMIUenable_u(boolean Miu_SscEnable) {
        sscSet.Miu_u_SscEnable = Miu_SscEnable;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public boolean getMIUenalbe_u() {
        return sscSet.Miu_u_SscEnable;
    }

    @Override
    public boolean setMIUmodulation_u(int Miu_SscSpan) {
        sscSet.Miu0_u_SscSpan = Miu_SscSpan;
        sscSet.Miu1_u_SscSpan = Miu_SscSpan;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getMIUmodulation_u() {
        return sscSet.Miu0_u_SscSpan;
    }

    @Override
    public boolean setMIUpercentage_u(int Miu_SscStep) {
        sscSet.Miu0_u_SscStep = Miu_SscStep;
        sscSet.Miu1_u_SscStep = Miu_SscStep;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getMIUpercentage_u() {
        return sscSet.Miu0_u_SscStep;
    }

    @Override
    public boolean setLVDSenable_u(boolean Lvds_SscEnable) {
        sscSet.Lvds_u_SscEnable = Lvds_SscEnable;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public boolean getLVDSenalbe_u() {
        return sscSet.Lvds_u_SscEnable;
    }

    @Override
    public boolean setLVDSmodulation_u(int Lvds_SscSpan) {
        sscSet.Lvds_u_SscSpan = Lvds_SscSpan;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getLVDSmodulation_u() {
        return sscSet.Lvds_u_SscSpan;
    }

    @Override
    public boolean setLVDSpercentage_u(int Lvds_SscStep) {
        sscSet.Lvds_u_SscStep = Lvds_SscStep;
        factoryDB.updateSSCAdjust(sscSet);
        UpdateSscPara();
        return true;
    }

    @Override
    public int getLVDSpercentage_u() {
        return sscSet.Lvds_u_SscStep;
    }

    /**************************************************************************
     * PEG setting
     *************************************************************************/
    @Override
    public boolean setPeqFoCoarse(int index, int coarseVal) {
        factoryPEQSet.stPEQParam[index].Foh = coarseVal;
        factoryDB.updatePEQAdjust(factoryPEQSet.stPEQParam[index], index);
        return true;
    }

    @Override
    public int getPeqFoCoarse(int index) {
        return factoryPEQSet.stPEQParam[index].Foh;
    }

    @Override
    public boolean setPeqFoFine(int index, int fineVal) {
        factoryPEQSet.stPEQParam[index].Fol = fineVal;
        factoryDB.updatePEQAdjust(factoryPEQSet.stPEQParam[index], index);
        return true;
    }

    @Override
    public int getPeqFoFine(int index) {
        return factoryPEQSet.stPEQParam[index].Fol;
    }

    @Override
    public boolean setPeqGain(int index, int gainVal) {
        factoryPEQSet.stPEQParam[index].Gain = gainVal;
        factoryDB.updatePEQAdjust(factoryPEQSet.stPEQParam[index], index);
        return true;
    }

    @Override
    public int getPeqGain(int index) {
        return factoryPEQSet.stPEQParam[index].Gain;
    }

    @Override
    public boolean setPeqQ(int index, int Qvalue) {
        factoryPEQSet.stPEQParam[index].QValue = Qvalue;
        factoryDB.updatePEQAdjust(factoryPEQSet.stPEQParam[index], index);
        return true;
    }

    public boolean setPEQ() {
        DtvSoundEffect soundeffect = new DtvSoundEffect();
        for (int i = 0; i < 5; i++) {
            soundeffect.soundParameterPeqs[i] = new SoundParameterPeq();
        }
        for (int i = 0; i < 5; i++) {
            soundeffect.soundParameterPeqs[i].peqGc = factoryPEQSet.stPEQParam[i].Foh * 100
                    + (factoryPEQSet.stPEQParam[i].Fol * 100) / 255;
            soundeffect.soundParameterPeqs[i].peqGain = factoryPEQSet.stPEQParam[i].Gain;
            soundeffect.soundParameterPeqs[i].peqQvalue = factoryPEQSet.stPEQParam[i].QValue;
        }
        soundeffect.peqBandNumber = 5;
        try {
            am.enableBasicSoundEffect(EnumSoundEffectType.E_PEQ, true);
            am.setBasicSoundEffect(EnumSoundEffectType.E_PEQ, soundeffect);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getPeqQ(int index) {
        return factoryPEQSet.stPEQParam[index].QValue;
    }

    /**************************************************************************
     * Other setting
     *************************************************************************/
	//smc 20161010 
    @Override
    public String getSoftWareVersion() {
    	try {
            factoryExternSetting.softVersion = TvManager.getInstance().getSystemSoftwareVersion();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return factoryExternSetting.softVersion;
    }
	
    @Override
    public String getBoardType() {
        try {
            factoryExternSetting.boardType = TvManager.getInstance().getSystemBoardName();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return factoryExternSetting.boardType;
    }

    @Override
    public String getPanelType() {
        try {
            factoryExternSetting.panelType = TvManager.getInstance().getSystemPanelName();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return factoryExternSetting.panelType;
    }

	//smc 20161010
	@Override
    public String GetBackLightProperty(int iPropertyType) {
		String strProperty = new String();
		try {
            strProperty = TvManager.getInstance().GetBackLightProperty(iPropertyType);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return strProperty;
    }
	
    @Override
    public String getCompileTime() {
    	try {
            factoryExternSetting.dayAndTime = TvManager.getInstance().getSystemSoftwareBuildTime();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return factoryExternSetting.dayAndTime;
    }
	
	@Override
    public boolean IsIniPWMNonLinearEnble() {
    	try {
            return TvManager.getInstance().IsIniPWMNonLinearEnble();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }
	
	@Override
    public int GetIniPWMNonLinear(int NonLinearOsdType) {
    	int ret = -1;
    	try {
            ret = TvManager.getInstance().GetIniPWMNonLinear(NonLinearOsdType);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return ret;
    }

	@Override
    public void SetIniPWMNonLinear(int NonLinearOsdType,int NonLinearOsdValue) {
    	try {
            TvManager.getInstance().SetIniPWMNonLinear(NonLinearOsdType,NonLinearOsdValue);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

	@Override
	public int getBacklightMaxValue(){
        try {
			return TvManager.getInstance().getPictureManager().getBacklightMaxValue();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
		return -1;
    }

	@Override
	public int getBacklightMinValue(){
        try {
			return TvManager.getInstance().getPictureManager().getBacklightMinValue();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
		return -1;
    }
	
    /**
     * set watch dog mode
     */
    public boolean setWatchDogMode(short isEnable) {
        factoryDB.updateUserSysSetting(USER_SETTING_FIELD.bEnableWDT, isEnable);
        try {
            if (isEnable == 1) {
                fm.enableWdt();
            } else {
                fm.disableWdt();
            }
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short getWatchDogMode() {
        return (short) factoryDB.queryUserSysSetting(USER_SETTING_FIELD.bEnableWDT);
    }

    @Override
    public boolean setTestPattern(int testPatternMode) {
        factoryExternSetting.testPatternMode = testPatternMode;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        try {
            fm.setVideoTestPattern(EnumScreenMute.values()[testPatternMode]);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getTestPattern() {
        return factoryExternSetting.testPatternMode;
    }

	//qwh add for Burn in RGB		
	@Override
		public boolean setBurnInRGB(int BurnInRGB) {
			factoryExternSetting.m_bAgingMode = BurnInRGB;
			//Log.d(TAG, "qwh setBurnInRGB m_bAgingMode = " + factoryExternSetting.m_bAgingMode);
			factoryDB.updateFactoryExtern(factoryExternSetting);
			int m_bAgingMode = 0;
			try{
				m_bAgingMode = TvManager.getInstance().getFactoryManager().getBurnInRGB();
				Log.d("factory", "qwh factory get Burn In RGB : " + m_bAgingMode);
			} catch (TvCommonException e) {
            	e.printStackTrace();
        	}
			return true;
		}
	
		@Override
		public int getBurnInRGB() {
			factoryExternSetting = factoryDB.queryFactoryExtern();
			//Log.d(TAG, "qwh getBurnInRGB m_bAgingMode  = " + factoryExternSetting.m_bAgingMode);
			int m_bAgingMode = 0;
			try{				
				m_bAgingMode = TvManager.getInstance().getFactoryManager().getBurnInRGB();
				Log.d("factory", "qwh factory get Burn In RGB : " + m_bAgingMode);
			} catch (TvCommonException e) {
            	e.printStackTrace();
        	}
			return factoryExternSetting.m_bAgingMode;
		}
	//qwh add end
	
    @Override
    public boolean restoreToDefault() {
        boolean ret = true;
        boolean result = false;
        File srcFile = new File("/tvdatabase/DatabaseBackup/", "user_setting.db");
        File destFile = new File("/tvdatabase/Database/", "user_setting.db");
        result = copyFile(srcFile, destFile);
        result = false;
        if (result == false) {
            ret = false;
        }

        srcFile = new File("/tvdatabase/DatabaseBackup/", "factory.db");
        destFile = new File("/tvdatabase/Database/", "factory.db");
        result = copyFile(srcFile, destFile);
        result = false;
        if (result == false) {
            ret = false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tvCommonManager.rebootSystem("reboot");
        return ret;
    }

    /**
     * Copy data from a source stream to destFile. Return true if succeed,
     * return false if failed.
     */
    private boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            if (destFile.exists()) {
                destFile.delete();
            }
            FileOutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    Log.d(TAG, " out.write(buffer, 0, bytesRead);");
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.flush();
                try {
                    out.getFD().sync();
                } catch (IOException e) {
                }
                out.close();
            }
            return true;
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }
    }

    // copy a file from srcFile to destFile, return true if succeed, return
    // false if fail
    private boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        try {
            InputStream in = new FileInputStream(srcFile);
            try {
                result = copyToFile(in, destFile);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            result = false;
        }
        chmodFile(destFile);
        return result;
    }

    private void chmodFile(File destFile) {
        try {
            String command = "chmod 666 " + destFile.getAbsolutePath();
            Log.i(TAG, "command = " + command);
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(command);
        } catch (IOException e) {
            Log.i(TAG, "chmod fail!!!!");
            e.printStackTrace();
        }
    }

    @Override
    public boolean setPowerOnMode(int factoryPowerMode) {
        boolean ret = false;
        EnumAcOnPowerOnMode poweronmode = null;
        poweronmode = EnumAcOnPowerOnMode.values()[factoryPowerMode];
        try {
            ret = fm.setEnvironmentPowerMode(poweronmode);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int getPowerOnMode() {
        int ret = 0;
        try {
            ret = fm.getEnvironmentPowerMode().ordinal();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public boolean setUartOnOff(boolean isEnable) {
        try {
            fm.setUartEnv(isEnable);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean getUartOnOff() {
        boolean ret = false;
        try {
            ret = fm.getUartEnv();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public boolean enableUartDebug() {
        try {
            return fm.startUartDebug();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setDtvAvAbnormalDelay(boolean isEnable) {
        factoryExternSetting.dtvAvAbnormalDelay = isEnable;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        return true;
    }

    @Override
    public boolean getDtvAvAbnormalDelay() {
        return factoryExternSetting.dtvAvAbnormalDelay;
    }

    @Override
    public boolean setFactoryPreSetFeature(int factoryPreset) {
        factoryExternSetting.factoryPreset = factoryPreset;
        factoryDB.updateFactoryExtern(factoryExternSetting);
        return true;
    }

    @Override
    public int getFactoryPreSetFeature() {
        return factoryExternSetting.factoryPreset;
    }

    @Override
    public boolean setPanelSwing(short panelSwingVal) {
        factoryExternSetting.panelSwingVal = panelSwingVal;
        try {
            pm.setSwingLevel(panelSwingVal);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        factoryDB.updateFactoryExtern(factoryExternSetting);
        return true;
    }

    @Override
    public short getPanelSwing() {
        return factoryExternSetting.panelSwingVal;
    }

    @Override
    public boolean setAudioPrescale(int audioPrescaleVal) {
        int inputSrc = factoryDB.queryCurInputSrc();
        int len = 0;
        String[] strArray = new String[MAPI_INPUT_SOURCE_NUM];
        strArray = factoryDB.queryeSpeakerPreScale().split(",");
        DtvSoundEffect dtvSoundEff = null;
        strArray[inputSrc] = "0x" + Integer.toHexString(audioPrescaleVal);
        StringBuffer mAudioPreScaleStrBuf = new StringBuffer();
        String mAudioPreScaleStr = null;
        for (int i = 0; i < strArray.length; i++) {
            mAudioPreScaleStrBuf.append(strArray[i] + ",");
        }
        len = mAudioPreScaleStrBuf.length();
        if (len > 0) {
            mAudioPreScaleStrBuf.deleteCharAt(len - 1);
        }
        mAudioPreScaleStr = mAudioPreScaleStrBuf.toString();
        factoryDB.updateSpeakerPreScale(mAudioPreScaleStr);
        try {
            dtvSoundEff = new DtvSoundEffect();
            dtvSoundEff.preScale = audioPrescaleVal;
            am.setBasicSoundEffect(EnumSoundEffectType.E_PRESCALE, dtvSoundEff);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getAudioPrescale() {
        int inputSrc = factoryDB.queryCurInputSrc();
        int ret = 0;
        String[] strArray = new String[MAPI_INPUT_SOURCE_NUM];
        strArray = factoryDB.queryeSpeakerPreScale().split(",");
        try {
            ret = Integer.parseInt(strArray[inputSrc].substring(2), 16);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private final int max_handler = 4;

    Handler pHandler[] = new Handler[max_handler];

    public boolean setHandler(Handler handler, int index) {
        if (index < max_handler) {
            if (index > 0 && pHandler[index] != null) {
                Log.e(TAG, "Warning ,!!!Some Activity lose release activity");
            }
            pHandler[index] = handler;
            return true;
        } else {
            return false;
        }

    }

    public Handler getHandler(int index) {
        if (index < max_handler) {
            return pHandler[index];
        } else {
            return null;
        }

    }

    public void releaseHandler(int index) {
        if (index < max_handler) {
            pHandler[index] = null;
        }
    }

    @Override
    public EnumInputSource getCurrentInputSource() {
        int value = factoryDB.queryCurInputSrc();
        return EnumInputSource.values()[value];
    }

    @Override
    public boolean execSetInputSource(EnumInputSource st) {
        if (getCurrentInputSource() != st) {
            setInputSource(st);
        }
        return true;
    }

    @Override
    public void setInputSource(EnumInputSource st) {
        try {
            TvManager.getInstance().setInputSource(st);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadEssentialDataFromDB() {

        int idxP, idxC;
        int curInputSrc = factoryDB.queryCurInputSrc();
        setWBIdx(EnumInputSource.values()[curInputSrc]);
        EnumInputSource curSrc = getCurrentInputSource();
        if ((curSrc == EnumInputSource.E_INPUT_SOURCE_VGA)
            || (curSrc == EnumInputSource.E_INPUT_SOURCE_VGA2)
            || (curSrc == EnumInputSource.E_INPUT_SOURCE_VGA3)) {
            eAdcIdx = E_ADC_SET_INDEX.ADC_SET_VGA;
        } else {
            eAdcIdx = E_ADC_SET_INDEX.ADC_SET_YPBPR_SD;
        }
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        wb_gainoffset = factoryDB.queryFactoryColorTempData(idxC);
        vifSet = factoryDB.queryNoStandVifSet();
        facvdpara = factoryDB.queryNoStandSet();
        factoryExternSetting = factoryDB.queryFactoryExtern();
        sscSet = factoryDB.querySSCAdjust();
        ciSet = factoryDB.queryCIAdjust();
        factoryPEQSet = factoryDB.queryPEQAdjusts();
        factoryColorTmpEx = factoryDB.queryFactoryColorTempExData();
        mfactoryNLASet = factoryDB.queryNonLinearAdjusts();

		if(IsIniPWMNonLinearEnble()){
			int typeIndex = MS_NLA_SET_INDEX.EN_NLA_BACKLIGHT.ordinal();
			mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V0 = GetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_0.ordinal());
			mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V25 = GetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_25.ordinal());
			mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V50 = GetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_50.ordinal());
			mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V75 = GetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_75.ordinal());
			mfactoryNLASet.stNLASetting[typeIndex].u8OSD_V100 = GetIniPWMNonLinear(MS_NLA_OSD_POINT.OSD_POINT_100.ordinal());
		}
		
        factoryDTVOverscanSet = factoryDB.queryOverscanAdjusts(0);
        factoryHDMIOverscanSet = factoryDB.queryOverscanAdjusts(1);
        factoryYPbPrOverscanSet = factoryDB.queryOverscanAdjusts(2);
        factoryVDOverscanSet = factoryDB.queryOverscanAdjusts(3);
        factoryATVOverscanSet = factoryDB.queryOverscanAdjusts(4);
        adcCalibData = factoryDB.queryADCAdjust(eAdcIdx.ordinal());
    }

    @Override
    public void loadCurAdcDataFromDB(int adcIdx) {
        adcCalibData = factoryDB.queryADCAdjust(adcIdx);
    }

    @Override
    public int getPictureModeIdx() {
        int inputSrc = factoryDB.queryCurInputSrc();
        int picMode = factoryDB.queryePicMode(inputSrc);
        return picMode;
    }

    @Override
    public boolean execVideoItem(EN_MS_VIDEOITEM eIndex, int value) {
        int inputSrc = factoryDB.queryCurInputSrc();
        int picMode = factoryDB.queryePicMode(inputSrc);
        factoryDB.updatePicModeSetting(eIndex, inputSrc, picMode, value);
        if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS.ordinal()) {
            try {
                pm.setPictureModeBrightness((short) value);
            } catch (TvCommonException e) {
                    e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST.ordinal()) {
            try {
                pm.setPictureModeContrast((short) value);
            } catch (TvCommonException e) {
                    e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION.ordinal()) {
            try {
                pm.setPictureModeColor((short) value);
            } catch (TvCommonException e) {
                    e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS.ordinal()) {
            try {
                pm.setPictureModeSharpness((short) value);
            } catch (TvCommonException e) {
                    e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE.ordinal()) {
            try {
                pm.setPictureModeTint((short) value);
            } catch (TvCommonException e) {
                    e.printStackTrace();
            }
        } else if (eIndex.ordinal() == EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT.ordinal()) {
            try {
                pm.setBacklight((short) value);
            } catch (TvCommonException e) {
                    e.printStackTrace();
            }
        } else {

        }
        return true;
    }

    @Override
    public int getVideoItem(EN_MS_VIDEOITEM eIndex) {
        int inputSrc = factoryDB.queryCurInputSrc();
        int picMode = factoryDB.queryePicMode(inputSrc);
        return factoryDB.queryPicModeSetting(eIndex, inputSrc, picMode);
    }

    @Override
    public void setPictureModeIdx(EN_MS_PICTURE picMode) {
        int inputSrc = factoryDB.queryCurInputSrc();
        factoryDB.updatePictureMode(picMode.ordinal(), inputSrc);

        int brightness = factoryDB.queryPicModeSetting(EN_MS_VIDEOITEM.MS_VIDEOITEM_BRIGHTNESS,
                inputSrc, picMode.ordinal());
        int contrast = factoryDB.queryPicModeSetting(EN_MS_VIDEOITEM.MS_VIDEOITEM_CONTRAST,
                inputSrc, picMode.ordinal());
        int saturation = factoryDB.queryPicModeSetting(EN_MS_VIDEOITEM.MS_VIDEOITEM_SATURATION,
                inputSrc, picMode.ordinal());
        int sharpness = factoryDB.queryPicModeSetting(EN_MS_VIDEOITEM.MS_VIDEOITEM_SHARPNESS,
                inputSrc, picMode.ordinal());
        int hue = factoryDB.queryPicModeSetting(EN_MS_VIDEOITEM.MS_VIDEOITEM_HUE, inputSrc,
                picMode.ordinal());
        int backlight = factoryDB.queryPicModeSetting(EN_MS_VIDEOITEM.MS_VIDEOITEM_BACKLIGHT,
                inputSrc, picMode.ordinal());
        try {
            pm.setPictureModeBrightness((short) brightness);
            pm.setPictureModeContrast((short) contrast);
            pm.setPictureModeColor((short) saturation);
            pm.setPictureModeSharpness((short) sharpness);
            pm.setPictureModeTint((short) hue);
            pm.setBacklight((short) backlight);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getColorTmpIdx() {
        int idxP, idxC;
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        idxP = factoryDB.queryePicMode(curInputSrc);
        idxC = factoryDB.queryColorTmpIdx(curInputSrc, idxP);
        Log.v(TAG, "-----getColorTmpIdx--idxC--" + idxC);
        Log.v(TAG, "-getColorTmpIdx--curInputSrc---" + curInputSrc);
        return idxC;
    }

    @Override
    public void setColorTmpIdx(EN_MS_COLOR_TEMP colorTmp) {
        // int curInputSrc = factoryDB.queryCurInputSrc();
        int curInputSrc = getWBIdx().ordinal();
        int idxP = factoryDB.queryePicMode(curInputSrc);
        factoryDB.updateColorTempIdx(curInputSrc, idxP, colorTmp);

        int idxC = colorTmp.ordinal();// factoryDB.queryColorTmpIdx(curInputSrc,
                                      // idxP);
        int srcId = InputSourceTransfer(EnumInputSource.values()[curInputSrc]).ordinal();
        setWBGainOffset(idxC, curInputSrc, srcId);
        Log.v(TAG, "-setColorTmpIdx--curInputSrc---" + eWBIdx.ordinal());
        Log.v(TAG, "--setColorTmpIdx-----" + idxC);

    }

    @Override
    public String getUpdatePqFilePath(EnumPqUpdateFile epqUpdateFile) {
        String strPath = new String();
        try {
            strPath = fm.getUpdatePqFilePath(epqUpdateFile);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return strPath;
    }

    @Override
    public void updatePqIniFiles() {
        try {
            fm.updatePqIniFiles();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public short[] readFromEeprom(short index, int size) {
        try {
            return TvManager.getInstance().readFromEeprom(index, size);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean writeToEeprom(short bank, short[] data) {
        try {
            return TvManager.getInstance().writeToEeprom(bank, data);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean enableUart() {
        try {
            return fm.enableUart();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean switchUart() {
        try {
            return fm.uartSwitch();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean resetForMbootUpgrade(String pFilename) {
        try {
            return TvManager.getInstance().resetForMbootUpgrade(pFilename);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getEnvironment(String name) {
        try {
            return TvManager.getInstance().getEnvironment(name);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PanelProperty getPanelWidthHeight() {
        try {
            PanelProperty pp = new PanelProperty();
            pp.height = TvManager.getInstance().getPictureManager().getPanelWidthHeight().height;
            pp.width = TvManager.getInstance().getPictureManager().getPanelWidthHeight().width;
            return pp;
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean upgrade(String name, String value) {
        try {
            return TvManager.getInstance().setEnvironment(name, value);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short[] readBytesFromI2C(int deviceId, short[] address, short size) {
        try {
            return fm.readBytesFromI2C(deviceId, address, size);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean writeBytesToI2C(int deviceId, short[] address, short[] writeData) {
        try {

            return fm.writeBytesToI2C(deviceId, address, writeData);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void enableI2c(int i2cDeviceId) {
        try {

            TvManager.getInstance().enableI2c(i2cDeviceId);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disableI2c(int i2cDeviceId) {
        try {

            TvManager.getInstance().disableI2c(i2cDeviceId);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restoreFactoryAtvProgramTable(short cityIndex) {
        try {

            fm.restoreFactoryAtvProgramTable(cityIndex);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restoreFactoryDtvProgramTable(short cityIndex, int which) {
        try {
            // fm.restoreFactoryDtvProgramTable(cityIndex,which);
            fm.restoreFactoryDtvProgramTable(cityIndex);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPQVersion(int iescalerwindow) {
        String str = new String();
        if (iescalerwindow == 0 || iescalerwindow == 1) {
            try {
                return fm.getPQVersion(EnumScalerWindow.values()[iescalerwindow]);
            } catch (TvCommonException e) {
                    e.printStackTrace();
            }
        }
        return str;
    }

    @Override
    public DtvDemodDvbtInfo getDemodDVBTInfo() {
        EnumInputSource curSrc = getCurrentInputSource();
        if (curSrc != EnumInputSource.E_INPUT_SOURCE_DTV) {
            return null;
        }

        try {
            return ((PlayerImpl) TvManager.getInstance().getPlayerManager()).getDemodDVBTInfo();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DtvDemodDvbcInfo getDemodDVBCInfo() {
        EnumInputSource curSrc = getCurrentInputSource();
        if (curSrc != EnumInputSource.E_INPUT_SOURCE_DTV) {
            return null;
        }

        try {
            return ((PlayerImpl) TvManager.getInstance().getPlayerManager()).getDemodDVBCInfo();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCurrentDtvRoute() {
        EnumInputSource curSrc = getCurrentInputSource();
        if (curSrc != EnumInputSource.E_INPUT_SOURCE_DTV) {
            return 0;
        }

        try {
            return TvManager.getInstance().getCurrentDtvRoute();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setWOLEnableStatus(boolean flag) {
        Log.d(TAG, "setWOLEnableStatus " + flag);
        try {
            TvManager.getInstance().getFactoryManager().setWOLEnableStatus(flag);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getWOLEnableStatus() {
        try {
            return TvManager.getInstance().getFactoryManager().getWOLEnableStatus();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getCredentialMode() {
        return ciSet.Credential_Mode;
    }

    @Override
    public void setCredentialMode(int credentialmode) {
        ciSet.Credential_Mode = credentialmode;
        factoryDB.updateCIAdjust(ciSet);
    }

    @Override
    public boolean getPerformanceMonitor() {
        return ciSet.Performance_Monitor;
    }

    @Override
    public void enablePerformanceMonitor(boolean enable) {
        ciSet.Performance_Monitor = enable;
        factoryDB.updateCIAdjust(ciSet);
        tvciManager.enablePerformanceMonitor(enable);
    }

    @Override
    public int getCIDebugLevel(int index) {
        return ciSet.CIDebugLevel[index];
    }

    @Override
    public void setCIDebugLevel(int debugindex, int debuglevel) {
        ciSet.CIDebugLevel[debugindex] = debuglevel;
        factoryDB.updateCIAdjust(ciSet);
        tvciManager.setCIDebugLevel(debugindex, debuglevel);
    }

    @Override
    public int copyCmDb(String srcFile, String destPath) {
        try {
            return TvManager.getInstance().copyCmDb(srcFile, destPath);
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean resetToFactoryDefault() {
        try {
            return TvManager.getInstance().resetToFactoryDefault();
        } catch (TvCommonException e) {
            e.printStackTrace();
        }
        return false;
    }
}
