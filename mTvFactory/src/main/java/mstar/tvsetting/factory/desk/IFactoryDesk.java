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

import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.android.tvapi.dtv.dvb.dvbc.DtvDemodDvbcInfo;
import com.mstar.android.tvapi.dtv.dvb.dvbt.DtvDemodDvbtInfo;
import com.mstar.android.tvapi.factory.vo.EnumPqUpdateFile;

import android.os.Handler;

public interface IFactoryDesk {
    // /////////////////////////////////////////////////////////////////////////////////////////////////
    // All SQL DB table name
    // /////////////////////////////////////////////////////////////////////////////////////////////////
    // ------------------user_setting.db-----------------------------
    public final static short T_3DInfo_IDX = 0x00;

    public final static short T_3DSetting_IDX = 0x01;

    public final static short T_BlockSysSetting_IDX = 0x02;

    public final static short T_CECSetting_IDX = 0x03;

    public final static short T_CISettineUpInfo_IDX = 0x13;

    public final static short T_PicMode_Setting_IDX = 0x14;

    public final static short T_PipSetting_IDX = 0x15;

    public final static short T_SoundMode_Setting_IDX = 0x16;

    public final static short T_SoundSetting_IDX = 0x17;

    public final static short T_SubtitleSetting_IDX = 0x18;

    public final static short T_SystemSetting_IDX = 0x19;

    public final static short T_ThreeDVideoMode_IDX = 0x1A;

    public final static short T_TimeSetting_IDX = 0x1B;

    public final static short T_USER_COLORTEMP_IDX = 0x1C;

    public final static short T_USER_COLORTEMP_EX_IDX = 0x1D;

    public final static short T_UserLocationSetting_IDX = 0x1E;

    public final static short T_UserMMSetting_IDX = 0x1F;

    public final static short T_UserOverScanMode_IDX = 0x20;

    public final static short T_IsdbUserSetting_IDX = 0x0B;

    public final static short T_MediumSetting_IDX = 0x0C;

    public final static short T_MfcMode_IDX = 0x0D;

    public final static short T_NRMode_IDX = 0x0E;

    public final static short T_NitInfo_IDX = 0x0F;

    public final static short T_Nit_TSInfo_IDX = 0x10;

    public final static short T_OADInfo_IDX = 0x11;

    public final static short T_OADInfo_UntDescriptor_IDX = 0x12;

    public final static short T_CISetting_IDX = 0x04;

    public final static short T_DB_VERSION_IDX = 0x05;

    public final static short T_DvbtPresetting_IDX = 0x06;

    public final static short T_EpgTimer_IDX = 0x07;

    public final static short T_FavTypeName_IDX = 0x08;

    public final static short T_InputSource_Type_IDX = 0x09;

    public final static short T_IsdbSysSetting_IDX = 0x0A;

    public final static short T_UserPCModeSetting_IDX = 0x21;

    public final static short T_VideoSetting_IDX = 0x22;

    public final static short T_ThreeDVideoRouterSetting_IDX = 0x23;

    // ------------------factory.db-----------------------------
    public final static short T_ADCAdjust_IDX = 0x24;

    public final static short T_FacrotyColorTemp_IDX = 0x25;

    public final static short T_FacrotyColorTempEx_IDX = 0x26;

    public final static short T_FactoryExtern_IDX = 0x27;

    public final static short T_NonStarndardAdjust_IDX = 0x28;

    public final static short T_SSCAdjust_IDX = 0x29;

    public final static short T_NonLinearAdjust_IDX = 0x2A;

    public final static short T_OverscanAdjust_IDX = 0x2B;

    public final static short T_PEQAdjust_IDX = 0x2C;

    public final static short T_Factory_DB_VERSION_IDX = 0x2D;

    public final static short T_HDMIOverscanSetting_IDX = 0x2E;

    public final static short T_YPbPrOverscanSetting_IDX = 0x2F;

    public final static short T_DTVOverscanSetting_IDX = 0x30;

    public final static short T_FactoryAudioSetting_IDX = 0x31;

    public final static short T_ATVOverscanSetting_IDX = 0x32;

    // ------------------customer.db-----------------------------
    public final static short T_ATVDefaultPrograms_IDX = 0x33;

    public final static short T_DTVDefaultPrograms_IDX = 0x34;

    public final static short T_MAX_IDX = 0x35;

    // / OFF
    final int EN_AUDIO_HIDEV_OFF = 0x00;

    // / L1
    final int EN_AUDIO_HIDEV_BW_L1 = 0x01;

    // / L2
    final int EN_AUDIO_HIDEV_BW_L2 = 0x02;

    // / L3
    final int EN_AUDIO_HIDEV_BW_L3 = 0x03;

    // / the number of the setting
    final int EN_AUDIO_HIDEV_BW_MAX = 0x04;

    final int TEST_PATTERN_MODE_GRAY = 0x00;

    final int TEST_PATTERN_MODE_RED = 0x01;

    final int TEST_PATTERN_MODE_GREEN = 0x02;

    final int TEST_PATTERN_MODE_BLUE = 0x03;

    final int TEST_PATTERN_MODE_BLACK = 0x04;

    final int TEST_PATTERN_MODE_OFF = 0x05;

    final int EN_POWER_MODE_SECONDARY = 0x00;

    final int EN_POWER_MODE_MEMORY = 0x01;

    final int EN_POWER_MODE_DIRECT = 0x02;

    final int EN_POWER_MODE_MAX = 0x03;

    final static int SETIS_START = -100;

    final static int SETIS_END_COMPLETE = -101;

    final int EN_3D_SELFADAPTIVE_LEVEL_LOW = 0x00;

    final int EN_3D_SELFADAPTIVE_LEVEL_MIDDLE = 0x01;

    final int EN_3D_SELFADAPTIVE_LEVEL_HIGH = 0x02;

    final int EN_3D_SELFADAPTIVE_LEVEL_MAX = 0x03;

    public enum MAPI_AVD_VideoStandardType {
        // / Video standard PAL BGHI
        E_MAPI_VIDEOSTANDARD_PAL_BGHI,
        // / Video standard NTSC M
        E_MAPI_VIDEOSTANDARD_NTSC_M,
        // / Video standard SECAM
        E_MAPI_VIDEOSTANDARD_SECAM,
        // / Video standard NTSC 44
        E_MAPI_VIDEOSTANDARD_NTSC_44,
        // / Video standard PAL M
        E_MAPI_VIDEOSTANDARD_PAL_M,
        // / Video standard PAL N
        E_MAPI_VIDEOSTANDARD_PAL_N,
        // / Video standard PAL 60
        E_MAPI_VIDEOSTANDARD_PAL_60,
        // / NOT Video standard
        E_MAPI_VIDEOSTANDARD_NOTSTANDARD,
        // / Video standard AUTO
        E_MAPI_VIDEOSTANDARD_AUTO,
        // / Max Number
        E_MAPI_VIDEOSTANDARD_MAX
    }

    public static enum MAPI_VIDEO_ARC_Type {
        // / Default
        E_AR_DEFAULT,
        // / 16x9
        E_AR_16x9,
        // / 4x3
        E_AR_4x3,
        // / Auto
        E_AR_AUTO,
        // / Panorama
        E_AR_Panorama,
        // / Just Scan
        E_AR_JustScan,
        // / Zoom 1
        E_AR_Zoom1,
        // / Zoom 2
        E_AR_Zoom2,
        // / 14:9
        E_AR_14x9,
        // / point to point
        E_AR_DotByDot,
        // / Subtitle
        E_AR_Subtitle,
        // / movie
        E_AR_Movie,
        // / Personal
        E_AR_Personal,

        // / 4x3 Panorama
        E_AR_4x3_PanScan,
        // / 4x3 Letter Box
        E_AR_4x3_LetterBox,
        // / 16x9 PillarBox
        E_AR_16x9_PillarBox,
        // / 16x9 PanScan
        E_AR_16x9_PanScan,
        // / 4x3 Combind
        E_AR_4x3_Combind,
        // / 16x9 Combind
        E_AR_16x9_Combind,
        // / Zoom 2X
        E_AR_Zoom_2x,
        // / Zoom 3X
        E_AR_Zoom_3x,
        // / Zoom 4X
        E_AR_Zoom_4x,

        // / Maximum value of this enum
        E_AR_MAX,
    }

    // /Define DTV resolution
    public static enum MAX_DTV_Resolution_Info {
        // /480i_60
        E_DTV480i_60,
        // /480p_60
        E_DTV480p_60,
        // /576i_50
        E_DTV576i_50,
        // /576p_50
        E_DTV576p_50,
        // /720p_60
        E_DTV720p_60,
        // /720p_50
        E_DTV720p_50,
        // /1080i_60
        E_DTV1080i_60,
        // /1080i_50
        E_DTV1080i_50,
        // /1080p_60
        E_DTV1080p_60,
        // /1080p_50
        E_DTV1080p_50,
        // /1080p_30
        E_DTV1080p_30,
        // /1080p_24
        E_DTV1080p_24,
        // /resolution number
        E_DTV_MAX
    }

    // /Define HDMI resolution type
    public static enum MAX_HDMI_Resolution_Info {
        // /480i_60
        E_HDMI480i_60,
        // /480p_60
        E_HDMI480p_60,
        // /576i_50
        E_HDMI576i_50,
        // /576p_50
        E_HDMI576p_50,
        // /720p_60
        E_HDMI720p_60,
        // /720p_50
        E_HDMI720p_50,
        // /1080i_60
        E_HDMI1080i_60,
        // /1080i_50
        E_HDMI1080i_50,
        // /1080p_60
        E_HDMI1080p_60,
        // /1080p_50
        E_HDMI1080p_50,
        // /1080p_30
        E_HDMI1080p_30,
        // /1080p_24
        E_HDMI1080p_24,
        // /1440x480i_60
        E_HDMI1440x480i_60,
        // /1440x480p_60
        E_HDMI1440x480p_60,
        // /1440x576i_50
        E_HDMI1440x576i_50,
        // /1440x576p_50
        E_HDMI1440x576p_50,
        // /MAX
        E_HDMI_MAX,
    }

    // /Define component resolution type
    public static enum MAX_YPbPr_Resolution_Info {
        // /480i_60
        E_YPbPr480i_60,
        // /480p_60
        E_YPbPr480p_60,
        // /576i_50
        E_YPbPr576i_50,
        // /576p_50
        E_YPbPr576p_50,
        // /720p_60
        E_YPbPr720p_60,
        // /720p_50
        E_YPbPr720p_50,
        // /1080i_60
        E_YPbPr1080i_60,
        // /1080i_50
        E_YPbPr1080i_50,
        // /1080p_60
        E_YPbPr1080p_60,
        // /1080p_50
        E_YPbPr1080p_50,
        // /1080p_30
        E_YPbPr1080p_30,
        // /1080p_24
        E_YPbPr1080p_24,
        // /1080p_25
        E_YPbPr1080p_25,
        // /Max
        E_YPbPr_MAX,
    }

    // /vd signal type
    public static enum EN_VD_SIGNALTYPE {
        // /NTSC
        SIG_NTSC,
        // /PAL
        SIG_PAL,
        // /SECAM
        SIG_SECAM,
        // /NTSC443
        SIG_NTSC_443,
        // /PAL_M
        SIG_PAL_M,
        // /PAL_NC
        SIG_PAL_NC,
        // /Signal number
        SIG_NUMS,
        // /Signal none
        SIG_NONE
    }
	
	public enum MS_NLA_OSD_POINT {
        OSD_POINT_0,
        OSD_POINT_25,
        OSD_POINT_50,
        OSD_POINT_75,
        OSD_POINT_100,
        OSD_POINT_NUMS
    }
	
    public class ST_MAPI_VIDEO_WINDOW_INFO {
        public int u16H_CapStart; // /< Capture window H start

        public int u16V_CapStart; // /< Capture window V start

        public short u8HCrop_Left; // /< H Crop Left

        public short u8HCrop_Right; // /< H crop Right

        public short u8VCrop_Up; // /< V Crop Up

        public short u8VCrop_Down; // /< V Crop Down

        public ST_MAPI_VIDEO_WINDOW_INFO() {
            u16H_CapStart = 0;
            u16V_CapStart = 0;
            u8HCrop_Left = 50;
            u8HCrop_Right = 50;
            u8VCrop_Up = 50;
            u8VCrop_Down = 50;
        }
    }

    // / define items which use no-linear adjust
    public enum MS_NLA_SET_INDEX {
        // / volume
        EN_NLA_VOLUME,
        // / picture brightness
        EN_NLA_BRIGHTNESS,
        // / picture contrast
        EN_NLA_CONTRAST,
        // / picture saturation
        EN_NLA_SATURATION,
        // / picture sharpness
        EN_NLA_SHARPNESS,
        // / picture hue
        EN_NLA_HUE,
        // / back light
        EN_NLA_BACKLIGHT,
        // / the number of the index
        EN_NLA_NUMS
    }

    public String[] MAPI_HASH_IP = {
        "BBE Digital", "BBE ViVA",  "SRS TSXT", "SRS TSHD ", "VIQ", "VDS", "VSPK", "DTS Surround Sensation", "QSound QSurround", "ADV",
        "CV3", "DD", "DD+", "DDCO", "Dolby pulse", "DTS 2.0 ", "DTS TC", "Dolby_THD", "DTS_HD", "MPEG2",
        "MPEG2_HD", "MPEG4", "MPEG4_SD", "MPEG4_HD", "DivX HD", "DivX_DRM", "DivX Plus HD", "H.264", "RM/RMVB", "VC-1",
        "WMV", "WMDRM_PD", "WMDRM_ND", "AVS", "FLV", "DivX qMobile", "DivX Mobile", "DivX HT", "DivX HD", "DVB-C",
        "MVC", "PlayReady", "Leanback", "facebook", "SC1.2", "", "Netflix2.1", "Netflix3.1", "VUDU", "CN - CinemaNow(BestBuy)",
        "CN - BlockBuster", "CN - Film Fresh", "CN - Sears Alphaline", "CN - RealD 3D", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "", "", "",
        "", "", "", "", "", "", "", "Dolby Demo"
    };

    // / VID setting
    public class MS_Factory_NS_VIF_SET {
        // / top
        public short VifTop;

        // / VGA max
        public int VifVgaMaximum;

        // / Gain distribution threshold
        public int GainDistributionThr;

        // / VIF AGC VGA base
        public short VifAgcVgaBase;

        // / china descrambler box mode: A1(0~5) J2(0~11) usefull
        public short ChinaDescramblerBox;

        public int ChinaDescramblerBoxDelay;

        // / CRKP1
        public short VifCrKp1;

        // / CRKI1
        public short VifCrKi1;

        // / CRKP2
        public short VifCrKp2;

        // / CRKI2
        public short VifCrKi2;

        // / CRKP
        public short VifCrKp;

        // / CRKI
        public short VifCrKi;

        // / CR lock threshold
        public int VifCrLockThr;

        // / CR threshold
        public int VifCrThr;

        // / flag to indicate CR KPKI
        public boolean VifCrKpKiAdjust;

        // / delay reduce
        public short VifDelayReduce;

        // / over modulation
        public boolean VifOverModulation;

        // / clamping values
        public int VifClampgainClampOvNegative;

        // / clamping gain values
        public int VifClampgainGainOvNegative;

        // / VIF AGC REF VALUE
        public short VifACIAGCREF;

        // / VIF AGC REF NEGATIVE VALUE
        public short VifAgcRefNegative;

        // / VIF_ASIA_SIGNAL_OPTION
        public boolean VifAsiaSignalOption;

        // / Vif version
        public short VifVersion;

        public MS_Factory_NS_VIF_SET() {
            this.VifTop = 0x00;
            this.VifVgaMaximum = 0x00;
            this.GainDistributionThr = 0x00;
            this.VifAgcVgaBase = 0x00;
            this.ChinaDescramblerBox = 0x01;
            this.ChinaDescramblerBoxDelay = 496;
            this.VifCrKp1 = 0x00;
            this.VifCrKi1 = 0x00;
            this.VifCrKp2 = 0x00;
            this.VifCrKi2 = 0x00;
            this.VifCrKp = 0x00;
            this.VifCrKi = 0x00;
            this.VifCrLockThr = 0x00;
            this.VifCrThr = 0x00;
            this.VifCrKpKiAdjust = false;
            this.VifDelayReduce = 0x00;
            this.VifOverModulation = false;
            this.VifClampgainClampOvNegative = 0x00;
            this.VifClampgainGainOvNegative = 0x00;
            this.VifACIAGCREF = 0x00;
            this.VifAgcRefNegative = 0x60;
            this.VifAsiaSignalOption = true;
            this.VifVersion = 0x00;
        }
    }

    public class MS_FACTORY_EXTERN_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        public static String softVersion = "0.0.1";

        public static String boardType = "A3";

        public static String panelType = "Full-HD";

        public static String dayAndTime = "2011.9.22 12:00";

        public int testPatternMode; // EN_TEST_PATTERN_MODE

        public boolean dtvAvAbnormalDelay;

        public int factoryPreset; // EN_FACTORY_PRE_SET

        public short panelSwingVal;

        public short audioPreScale;

        // / 3Dselfadaptive level setting
        public int st3DSelfAdaptiveLevel;

        // / power mode setting
        public int stPowerMode; // MS_FACTORY_POWER_MODE

        public int eHidevMode; // MS_FACTORY_HIDEV_INDEX

        // / factory aging mode on/off
        public int m_bAgingMode;

        // / factory Dsp version
        public short vdDspVersion;

        // / factory audio nr threshold
        public short audioNrThr;

        // / factory audio sif threshold
        public short audioSifThreshold;

        // / factory audio dsp version
        public short audioDspVersion;

        public MS_FACTORY_EXTERN_SETTING() {
            testPatternMode = 5;
            factoryPreset = 0;
            dtvAvAbnormalDelay = false;
            panelSwingVal = 0;
            audioPreScale = 0;
            stPowerMode = 1;
            eHidevMode = 0;
            m_bAgingMode = 5;
            vdDspVersion = 0;
            audioNrThr = 0;
            audioSifThreshold = 0;
            audioDspVersion = 0;
        }
    }

    // / SSC settings
    public class MS_FACTORY_SSC_SET {
        // / LVDS SSC span value
        public int Lvds_SscSpan;

        // / LVDS SSC step value
        public int Lvds_SscStep;

        // / MIU0 SSC span value
        public int Miu0_SscSpan;

        // / MIU0 SSC step value
        public int Miu0_SscStep;

        // / MIU1 SSC span value
        public int Miu1_SscSpan;

        // / MIU1 SSC step value
        public int Miu1_SscStep;

        // / MIU1 SSC span value
        public int Miu2_SscSpan;

        // / MIU1 SSC step value
        public int Miu2_SscStep;

        // / flag to indicate LVDS SSC enable
        public boolean Lvds_SscEnable;

        // / flag to indicate MIU SSC enable
        public boolean Miu_SscEnable;

        // for einsteinU
        // / LVDS SSC span value
        public int Lvds_u_SscSpan;

        // / LVDS SSC step value
        public int Lvds_u_SscStep;

        // / MIU0 SSC span value
        public int Miu0_u_SscSpan;

        // / MIU0 SSC step value
        public int Miu0_u_SscStep;

        // / MIU1 SSC span value
        public int Miu1_u_SscSpan;

        // / MIU1 SSC step value
        public int Miu1_u_SscStep;

        // / flag to indicate LVDS SSC enable
        public boolean Lvds_u_SscEnable;

        // / flag to indicate MIU SSC enable
        public boolean Miu_u_SscEnable;

        public MS_FACTORY_SSC_SET() {
            Lvds_SscEnable = false;
            Miu_SscEnable = false;
            Lvds_SscSpan = 128;
            Lvds_SscStep = 128;
            Miu0_SscSpan = 128;
            Miu0_SscStep = 128;
            Miu1_SscSpan = 128;
            Miu1_SscStep = 128;
        }
    }

    // / CI settings
    public class MS_FACTORY_CI_SET {
        // / CI credential mode
        public int Credential_Mode;

        // / flag to indicate performance monitor enable
        public boolean Performance_Monitor;

        // / CI debug level
        public int CIDebugLevel[];

        public MS_FACTORY_CI_SET() {
            Performance_Monitor = false;

            int CI_FUNCTION_DEBUG_COUNT = 17;
            CIDebugLevel = new int[CI_FUNCTION_DEBUG_COUNT];
            for (int i = 0; i < CI_FUNCTION_DEBUG_COUNT; i++) {
                CIDebugLevel[i] = 0;
            }
        }
    }

    // / PEQ Parameter Type
    public class AUDIO_PEQ_PARAM {
        // / PEQ Band parameter
        public int Band;

        // / PEQ Gain parameter
        public int Gain;

        // / PEQ Foh parameter
        public int Foh;

        // / PEQ Fol parameter
        public int Fol;

        // / PEQ Q-Value parameter
        public int QValue;

        public AUDIO_PEQ_PARAM() {
            Band = 3;
            Gain = 120;
            Foh = 80;
            Fol = 45;
            QValue = 80;
        }
    }

    // /define factory PEQ setting
    public class ST_FACTORY_PEQ_SETTING {
        // /check sum
        public int u16CheckSum;

        // /PEQ param
        public AUDIO_PEQ_PARAM stPEQParam[];

        public ST_FACTORY_PEQ_SETTING() {
            int i, PEQBandNum = 5;
            stPEQParam = new AUDIO_PEQ_PARAM[PEQBandNum];
            for (i = 0; i < PEQBandNum; i++) {
                stPEQParam[i] = new AUDIO_PEQ_PARAM();
            }
        }
    }

    final static int AUTOTUNE_START = 20111;

    final static int AUTOTUNE_END_SUCESSED = 20112;

    final static int AUTOTUNE_END_FAILED = 20113;

    final static int AUTOTUNE_TIMING_MODE_ERR = 20114;

    /*************************************************************************
     * ADC adjust setting
     ************************************************************************/
    public boolean setADCRedGain(int redGain);

    /**
     * get ADC red gain
     *
     * @param displayMode
     * @return
     */
    public int getADCRedGain();

    /**
     * set ADC green gain
     *
     * @param displayMode
     * @return
     */
    public boolean setADCGreenGain(int greenGain);

    /**
     * get ADC green gain
     *
     * @return
     */
    public int getADCGreenGain();

    /**
     * set ADC blue gain
     *
     * @param displayMode
     * @return
     */
    public boolean setADCBlueGain(int blueGain);

    /**
     * get ADC blue gain
     *
     * @return
     */
    public int getADCBlueGain();

    /**
     * set ADC red offset
     *
     * @param displayMode
     * @return
     */
    public boolean setADCRedOffset(int redOffset);

    /**
     * get ADC red offset
     *
     * @param displayMode
     * @return
     */
    public int getADCRedOffset();

    /**
     * set ADC green offset
     *
     * @param displayMode
     * @return
     */
    public boolean setADCGreenOffset(int greenOffset);

    /**
     * get ADC green offset
     *
     * @param displayMode
     * @return
     */
    public int getADCGreenOffset();

    /**
     * set ADC blue offset
     *
     * @param displayMode
     * @return
     */
    public boolean setADCBlueOffset(int blueOffset);

    /**
     * get ADC blue offset
     *
     * @param displayMode
     * @return
     */
    public int getADCBlueOffset();

    /**
     * set ADC phase
     *
     * @param displayMode
     * @return
     */
    public boolean setADCPhase(int phase);

    /**
     * get ADC phase
     *
     * @param displayMode
     * @return
     */
    public int getADCPhase();

    /**
     * set auto ADC mode, off or on
     *
     *@param currentSourceindexAdc current source index
     * @return Result Status
     */
    public boolean ExecAutoADC(int currentSourceindexAdc);

    /**
     * Set ADC index
     *
     * @param eIdx Adc index
     * @return Result status
     */
    public boolean setAdcIdx(E_ADC_SET_INDEX eIdx);

    /**
     * Get ADC Index
     *
     * @return Adc index
     */
    public E_ADC_SET_INDEX getAdcIdx();

    /*************************************************************************
     * White balance adjust
     ************************************************************************/
    public boolean changeWBParaWhenSourceChange();

    public EnumInputSource getWBIdx();

    public boolean setWBIdx(EnumInputSource eIdx);

    public boolean setWbRedGain(short redGain);

    /**
     * get white balance red gain
     *
     * @param displayMode
     * @return
     */
    public int getWbRedGain();

    /**
     * set white balance green gain
     *
     * @param displayMode
     * @return
     */
    public boolean setWbGreenGain(short greenGain);

    /**
     * get white balance green gain
     *
     * @return
     */
    public int getWbGreenGain();

    /**
     * set white balance blue gain
     *
     * @param displayMode
     * @return
     */
    public boolean setWbBlueGain(short blueGain);

    /**
     * get white balance blue gain
     *
     * @return
     */
    public int getWbBlueGain();

    /**
     * set white balance red offset
     *
     * @param displayMode
     * @return
     */
    public boolean setWbRedOffset(short redOffset);

    /**
     * get white balance red offset
     *
     * @param displayMode
     * @return
     */
    public int getWbRedOffset();

    /**
     * set white balance green offset
     *
     * @param displayMode
     * @return
     */
    public boolean setWbGreenOffset(short greenOffset);

    /**
     * get white balance green offset
     *
     * @param displayMode
     * @return
     */
    public int getWbGreenOffset();

    /**
     * set white balance blue offset
     *
     * @param displayMode
     * @return
     */
    public boolean setWbBlueOffset(short blueOffset);

    /**
     * get white balance blue offset
     *
     * @param displayMode
     * @return
     */
    public int getWbBlueOffset();

    /*************************************************************************
     * abnormal items setting
     ************************************************************************/
    /**
     * set vif tip value
     *
     * @param short
     * @return
     */
    public boolean setVifTop(short vifTop);

    /**
     * get vif tip value
     *
     * @return short
     */
    public short getVifTop();

    /**
     * set vif vga maximum value
     *
     * @param short
     * @return
     */
    public boolean setVifVgaMaximum(int vifVgaMaximum);

    /**
     * get vif vga maximum value
     *
     * @return short
     */
    public int getVifVgaMaximum();

    /**
     * set vif crkp value
     *
     * @param short
     * @return
     */
    public boolean setVifCrKp(short vifCrKp);

    /**
     * get vif cr kp value
     *
     * @return short
     */
    public short getVifCrKp();

    /**
     * set vif crki value
     *
     * @param short
     * @return
     */
    public boolean setVifCrKi(short vifCrKi);

    /**
     * get vif cr ki value
     *
     * @return short
     */
    public short getVifCrKi();

    /**
     * set vif asia signal option
     *
     * @param short
     * @return
     */
    public boolean setVifAsiaSignalOption(boolean vifAsiaSignalOption);

    /**
     * get vif asia signal option
     *
     * @return short
     */
    public boolean getVifAsiaSignalOption();

    /**
     * set vif cr kp ki adjust
     *
     * @param short
     * @return
     */
    public boolean setVifCrKpKiAdjust(boolean vifCrKpKiAdjust);

    /**
     * get vif cr kp ki adjust
     *
     * @return short
     */
    public boolean getVifCrKpKiAdjust();

    /**
     * set vif over modulation
     *
     * @param short
     * @return
     */
    public boolean setVifOverModulation(boolean vifOverModulation);

    /**
     * get vif over modulation
     *
     * @return short
     */
    public boolean getVifOverModulation();

    /**
     * set vif clampgain ov negative
     *
     * @param short
     * @return
     */
    public boolean setVifClampGainOvNegative(int vifClampGainOvNegative);

    /**
     * get vif clampgain ov negative
     *
     * @return short
     */
    public int getVifClampGainOvNegative();

    /**
     * set china descrable box
     *
     * @param short
     * @return
     */
    public boolean setChinaDescramblerBox(short chinaDescramblerBox);

    /**
     * get china descrable box
     *
     * @return short
     */
    public short getChinaDescramblerBox();

    /**
     * set delay reduce vlaue
     *
     * @param short
     * @return
     */
    public boolean setDelayReduce(short delayReduce);

    /**
     * get delay reduce value
     *
     * @return short
     */
    public short getDelayReduce();

    /**
     * set vif cr thr
     *
     * @param short
     * @return
     */
    public boolean setVifCrThr(int vifCrThr);

    /**
     * get vif cr thr
     *
     * @return short
     */
    public int getVifCrThr();

    /**
     * set vif version
     *
     * @param short
     * @return
     */
    public boolean setVifVersion(short vifVersion);

    /**
     * get vif version
     *
     * @return short
     */
    public short getVifVersion();

    /**
     * set vif agc ref
     *
     * @param short
     * @return
     */
    public boolean setVifAgcRef(short vifAgcRef);

    /**
     * get vif agc ref
     *
     * @return short
     */
    public short getVifAgcRef();

    /**
     * set gain distribution thr
     *
     * @param short
     * @return
     */
    public boolean setGainDistributionThr(int gainDistributionThr);

    /**
     * get gain distribution thr
     *
     * @return short
     */
    public int getGainDistributionThr();

    /**
     * set AEFC D4 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D4(short AEFC_D4);

    /**
     * get AEFC D4 register
     *
     * @return short
     */
    public short getAEFC_D4();

    /**
     * set AEFC D5 register Bit2
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D5Bit2(short AEFC_D5Bit2);

    /**
     * get AEFC D5 register Bit2
     *
     * @return short
     */
    public short getAEFC_D5Bit2();

    /**
     * set AEFC D8 register Bit3,2,1,0
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D8Bit3210(short AEFC_D8Bit3210);

    /**
     * get AEFC D8 register Bit3,2,1,0
     *
     * @return short
     */
    public short getAEFC_D8Bit3210();

    /**
     * set AEFC D9 register Bit0
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D9Bit0(short AEFC_D9Bit0);

    /**
     * get AEFC D9 register Bit0
     *
     * @return short
     */
    public short getAEFC_D9Bit0();

    /**
     * set AEFC D7 register high boun
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D7HighBoun(short AEFC_D7HighBoun);

    /**
     * get AEFC D7 register high boun
     *
     * @return short
     */
    public short getAEFC_D7HighBoun();

    /**
     * set AEFC D7 register low boun
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D7LowBoun(short AEFC_D7LowBoun);

    /**
     * get AEFC D7 register low boun
     *
     * @return short
     */
    public short getAEFC_D7LowBoun();

    /**
     * set AEFC A0 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_A0(short AEFC_A0);

    /**
     * get AEFC A0 register
     *
     * @return short
     */
    public short getAEFC_A0();

    /**
     * set AEFC A1 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_A1(short AEFC_A1);

    /**
     * get AEFC A1 register
     *
     * @return short
     */
    public short getAEFC_A1();

    /**
     * set AEFC 66 register Bit7,6
     *
     * @param short
     * @return
     */
    public boolean setAEFC_66Bit76(short AEFC_66Bit76);

    /**
     * get AEFC 66 register Bit7,6
     *
     * @return short
     */
    public short getAEFC_66Bit76();

    /**
     * set AEFC 6e register Bit7,6,5,4
     *
     * @param short
     * @return
     */
    public boolean setAEFC_6EBit7654(short AEFC_6EBit7654);

    /**
     * get AEFC 6e register Bit7,6,5,4
     *
     * @return short
     */
    public short getAEFC_6EBit7654();

    /**
     * set AEFC 6e register Bit3,2,1,0
     *
     * @param short
     * @return
     */
    public boolean setAEFC_6EBit3210(short AEFC_6EBit3210);

    /**
     * get AEFC 6e register Bit3,2,1,0
     *
     * @return short
     */
    public short getAEFC_6EBit3210();

    /**
     * set AEFC 43 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_43(short AEFC_43);

    /**
     * get AEFC 43 register
     *
     * @return short
     */
    public short getAEFC_43();

    /**
     * set AEFC 44 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_44(short AEFC_44);

    /**
     * get AEFC 44 register
     *
     * @return short
     */
    public short getAEFC_44();

    /**
     * set AEFC CB register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_CB(short AEFC_CB);

    /**
     * get AEFC CB register
     *
     * @return short
     */
    public short getAEFC_CB();

    /**
     * set AEFC CF register Bit2 ATV
     *
     * @param short
     * @return
     */
    public boolean setAEFC_CFBit2_ATV(short AEFC_CFBit2_ATV);

    /**
     * get AEFC CF register Bit2 ATV
     *
     * @return short
     */
    public short getAEFC_CFBit2_ATV();

    /**
     * set AEFC CF register Bit2 AV
     *
     * @param short
     * @return
     */
    public boolean setAEFC_CFBit2_AV(short AEFC_CFBit2_AV);

    /**
     * get AEFC CF register Bit2 AV
     *
     * @return short
     */
    public short getAEFC_CFBit2_AV();

    /**
     * set vd dsp version
     *
     * @param short
     * @return
     */
    public boolean setVdDspVersion(short vdDspVersion);

    /**
     * get vd dsp version
     *
     * @return short
     */
    public short getVdDspVersion();

    /**
     * set audio hidev mode
     *
     * @param short
     * @return
     */
    public boolean setAudioHiDevMode(int audioHiDevMode);

    /**
     * get audio hidev mode
     *
     * @return short
     */
    public int getAudioHiDevMode();

    /**
     * set audio nr thr
     *
     * @param short
     * @return
     */
    public boolean setAudioNrThr(short audioNrThr);

    /**
     * get audio nr thr
     *
     * @return short
     */
    public short getAudioNrThr();

    /**
     * set audio sif threshold
     *
     * @param short
     * @return
     */
    public boolean setAudioSifThreshold(short audioSifThreshold);

    /**
     * get audio sif threshold
     *
     * @return short
     */
    public short getAudioSifThreshold();

    /**
     * set audio dsp version
     *
     * @param short
     * @return
     */
    public boolean setAudioDspVersion(short aduioDspVersion);

    /**
     * get audio dsp version
     *
     * @return short
     */
    public short getAudioDspVersion();

    /*************************************************************************
     * Nonlinear setting
     ************************************************************************/
    /**
     * set curve type
     *
     * @param short
     * @return
     */
    public boolean setCurveType(MS_NLA_SET_INDEX curveTypeIndex);

    /**
     * get curve type
     *
     * @param short
     * @return
     */
    public MS_NLA_SET_INDEX getCurveType();

    /**
     * set osd v0 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV0Nonlinear(int nonlinearVal);

    /**
     * get osd v0 nonlinear
     *
     * @param short
     * @return
     */
    public int getOsdV0Nonlinear();

    /**
     * set osd v25 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV25Nonlinear(int nonlinearVal);

    /**
     * get osd v25 nonlinear
     *
     * @param short
     * @return
     */
    public int getOsdV25Nonlinear();

    /**
     * set osd v50 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV50Nonlinear(int nonlinearVal);

    /**
     * get osd v50 nonlinear
     *
     * @param short
     * @return
     */
    public int getOsdV50Nonlinear();

    /**
     * set osd v75 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV75Nonlinear(int nonlinearVal);

    /**
     * get osd v75 nonlinear
     *
     * @param short
     * @return
     */
    public int getOsdV75Nonlinear();

    /**
     * set osd v100 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV100Nonlinear(int nonlinearVal);

    /**
     * get osd v100 nonlinear
     *
     * @param short
     * @return
     */
    public int getOsdV100Nonlinear();

    /**************************************************************************
     * Overscan setting
     *************************************************************************/
    /**
     * set over scan source type
     *
     * @param EN_INPUT_SOURCE_TYPE
     * @return
     */
    public boolean setOverScanSourceType(EnumInputSource SourceType);

    /**
     * get over scan source type
     *
     * @return EN_INPUT_SOURCE_TYPE
     */
    public EnumInputSource getOverScanSourceType();

    /**
     * set over scan horizontal size
     *
     * @param short
     * @return
     */
    public boolean setOverScanHsize(short hSize);

    /**
     * get over scan horizontal size
     *
     * @param short
     * @return
     */
    public short getOverScanHsize();

    /**
     * set over scan horizontal position
     *
     * @param short
     * @return
     */
    public boolean setOverScanHposition(short hPosition);

    /**
     * get over scan horizontal position
     *
     * @param short
     * @return
     */
    public short getOverScanHposition();

    /**
     * set over scan vertical size
     *
     * @param short
     * @return
     */
    public boolean setOverScanVsize(short vSize);

    /**
     * get over scan vertical size
     *
     * @param short
     * @return
     */
    public short getOverScanVsize();

    /**
     * set over scan vertical position
     *
     * @param short
     * @return
     */
    public boolean setOverScanVposition(short vPosition);

    /**
     * get over scan vertical position
     *
     * @param short
     * @return
     */
    public short getOverScanVposition();

    /**
     * get enable ip information
     *
     * @return byte[]
     */
    public byte[] GetEnableIPInfo();
    /**************************************************************************
     * SSC setting
     *************************************************************************/
    /**
     * set MIU enable or disable
     *
     * @param boolean
     * @return
     */
    public boolean setMIUenable(boolean Miu_SscEnable);

    /**
     * set MIU enable or disable
     *
     * @return boolean
     */
    public boolean getMIUenalbe();

    /**
     * set MIU modulation span
     *
     * @param int
     * @return
     */
    public boolean setMIUmodulation(int Miu_SscSpan);

    /**
     * get MIU modulation span
     *
     * @return int
     */
    public int getMIUmodulation();

    /**
     * set MIU percentage
     *
     * @param int
     * @return
     */
    public boolean setMIUpercentage(int Miu_SscPercentage);

    /**
     * get MIU percentage
     *
     * @return int
     */
    public int getMIUpercentage();

    /**
     * set LVDS enable or disable
     *
     * @param boolean
     * @return
     */
    public boolean setLVDSenable(boolean Lvds_SscEnable);

    /**
     * set LVDS enable or disable
     *
     * @return boolean
     */
    public boolean getLVDSenalbe();

    /**
     * set LVDS modulation span
     *
     * @param int
     * @return
     */
    public boolean setLVDSmodulation(int Lvds_SscSpan);

    /**
     * get LVDS modulation span
     *
     * @return int
     */
    public int getLVDSmodulation();

    /**
     * set LVDS percentage
     *
     * @param int
     * @return
     */
    public boolean setLVDSpercentage(int Lvds_SscSpan);

    /**
     * get LVDS percentage
     *
     * @return int
     */
    public int getLVDSpercentage();

    /**************************************************************************
     * SSC setting
     *************************************************************************/
    /**
     * set PEQ FO Coarse value
     *
     * @param
     * @return
     */
    public boolean setPeqFoCoarse(int index, int coarseVal);

    /**
     * get PEQ FO Coarse value
     *
     * @return
     */
    public int getPeqFoCoarse(int index);

    /**
     * set PEQ FO Fine value
     *
     * @return
     */
    public boolean setPeqFoFine(int index, int fineVal);

    /**
     * set PEQ FO Fine value
     *
     * @return
     */
    public int getPeqFoFine(int index);

    /**
     * set PEQ gain value
     *
     * @param boolean
     * @return
     */
    public boolean setPeqGain(int index, int gainVal);

    /**
     * get PEQ gain value
     *
     * @param boolean
     * @return
     */
    public int getPeqGain(int index);

    /**
     * set PEQ Q value
     *
     * @param boolean
     * @return
     */
    public boolean setPeqQ(int index, int Qvalue);

    /**
     * get PEQ Q value
     *
     * @param boolean
     * @return
     */
    public int getPeqQ(int index);

    /**************************************************************************
     * Other setting
     *************************************************************************/
    /**
     * get software version
     *
     * @return
     */
    public String getSoftWareVersion();

    /**
     * get board type
     *
     * @return
     */
    public String getBoardType();

    /**
     * get panel type
     *
     * @return
     */
    public String getPanelType();

    /**
     * get the time of code compiled
     *
     * @return
     */
    public String GetBackLightProperty(int iPropertyType);
    public String getCompileTime();
	public boolean IsIniPWMNonLinearEnble();
	public int GetIniPWMNonLinear(int NonLinearOsdType);
	public void SetIniPWMNonLinear(int NonLinearOsdType,int NonLinearOsdValue);
	public int getBacklightMinValue();
	public int getBacklightMaxValue();
	
    /**
     * set watch dog mode
     */
    public boolean setWatchDogMode(short isEnable);

    /**
     * get watch dog mode
     */
    public short getWatchDogMode();

    /**
     * set test pattern
     */
    public boolean setTestPattern(int testPatternMode);

    /**
     * get test pattern
     */
    public int getTestPattern();

	//qwh add for Burn in RGB	
	/**
		* set factory pre set feature
		*/
	public boolean setBurnInRGB(int BurnInRGB);
	
	/**
		* get factory pre set feature
		*/
	public int getBurnInRGB();
	//qwh add end
	
    /**
     * restore the current factory setting to default
     */
    public boolean restoreToDefault();

    /**
     * set power on mode
     */
    public boolean setPowerOnMode(int factoryPowerMode);

    /**
     * get power on mode
     */
    public int getPowerOnMode();

    /**
     * set uart on off
     */
    public boolean setUartOnOff(boolean isEnable);

    /**
     * get uart enable
     */
    public boolean getUartOnOff();

    /**
     * Enable Uart Debug
     */
    public boolean enableUartDebug();

    /**
     * set Dtv Av abnormal delay
     */
    public boolean setDtvAvAbnormalDelay(boolean isEnable);

    /**
     * get Dtv Av abnormal delay
     */
    public boolean getDtvAvAbnormalDelay();

    /**
     * set factory pre set feature
     */
    public boolean setFactoryPreSetFeature(int factoryPreset);

    /**
     * get factory pre set feature
     */
    public int getFactoryPreSetFeature();

    /**
     * set panel swing
     */
    public boolean setPanelSwing(short panleSwingVal);

    /**
     * get panel swing
     */
    public short getPanelSwing();

    /**
     * set audio prescale
     */
    public boolean setAudioPrescale(int audioPrescaleVal);

    /**
     * get audio prescale
     */
    public int getAudioPrescale();

    /**
     * boolean setPEQ()
     */
    public boolean setPEQ();

    public class T_MS_COLOR_TEMP_DATA {
        public short redgain;

        public short greengain;

        public short bluegain;

        public short redoffset;

        public short greenoffset;

        public short blueoffset;

        public T_MS_COLOR_TEMP_DATA(short v1, short v2, short v3, short v4, short v5, short v6) {
            this.redgain = v1;
            this.greengain = v2;
            this.bluegain = v3;
            this.redoffset = v4;
            this.greenoffset = v5;
            this.blueoffset = v6;
        }
    }

    // / define color temperature mode setting
    public class T_MS_COLOR_TEMP {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / color temperature mode setting
        public T_MS_COLOR_TEMP_DATA astColorTemp[]; // 24Byte

        public T_MS_COLOR_TEMP() {
            int i;
            astColorTemp = new T_MS_COLOR_TEMP_DATA[EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM.ordinal()];
            for (i = 0; i < EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM.ordinal(); i++) {
                astColorTemp[i] = new T_MS_COLOR_TEMP_DATA((short) 0x80, (short) 0x80,
                        (short) 0x80, (short) 0x80, (short) 0x80, (short) 0x80);
            }
            u16CheckSum = 0xFFFF;
        }
    }

    /** color temperature */
    public static enum EN_MS_COLOR_TEMP {
        // /color temperature cool
        MS_COLOR_TEMP_COOL,
        // /color temperature medium
        MS_COLOR_TEMP_NATURE,
        // /color temperature warm
        MS_COLOR_TEMP_WARM,
        // /color temperature user
        MS_COLOR_TEMP_USER,
        // /color temperature user
        MS_COLOR_TEMP_USER2,
        // /color temperature
        MS_COLOR_TEMP_NUM
    }

    public class T_MS_COLOR_TEMPEX_DATA {
        public int redgain;

        public int greengain;

        public int bluegain;

        public int redoffset;

        public int greenoffset;

        public int blueoffset;

        public T_MS_COLOR_TEMPEX_DATA(int v1, int v2, int v3, int v4, int v5, int v6) {
            this.redgain = v1;
            this.greengain = v2;
            this.bluegain = v3;
            this.redoffset = v4;
            this.greenoffset = v5;
            this.blueoffset = v6;
        }
    }

    // / define color temperature mode setting
    public class T_MS_COLOR_TEMPEX {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / color temperature mode setting
        public T_MS_COLOR_TEMPEX_DATA astColorTempEx[][]; // 24Byte

        public T_MS_COLOR_TEMPEX() {
            int i;
            int j;
            astColorTempEx = new T_MS_COLOR_TEMPEX_DATA[EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM
                    .ordinal()][EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_NUM.ordinal()];
            for (i = 0; i < EN_MS_COLOR_TEMP.MS_COLOR_TEMP_NUM.ordinal(); i++) {
                for (j = 0; j < EN_MS_COLOR_TEMP_INPUT_SOURCE.E_INPUT_SOURCE_NUM.ordinal(); j++) {
                    astColorTempEx[i][j] = new T_MS_COLOR_TEMPEX_DATA(0x80, 0x80, 0x80, 0x80, 0x80,
                            0x80);
                }
            }
            u16CheckSum = 0xFFFF;
        }
    }

    /** color tempEx input source */
    public static enum EN_MS_COLOR_TEMP_INPUT_SOURCE {
        // /<VGA input
        E_INPUT_SOURCE_VGA,
        // /<ATV input
        E_INPUT_SOURCE_ATV,
        // /<AV
        E_INPUT_SOURCE_CVBS,
        // /<S-video
        E_INPUT_SOURCE_SVIDEO,
        // /<Component
        E_INPUT_SOURCE_YPBPR,
        // /<Scart
        E_INPUT_SOURCE_SCART,
        // /<HDMI
        E_INPUT_SOURCE_HDMI,
        // /<DTV <DTV2
        E_INPUT_SOURCE_DTV,
        // /<DVI <Storage <KTV <Storage2
        E_INPUT_SOURCE_OTHERS,

        // /<number of the source
        E_INPUT_SOURCE_NUM,
        // /<NULL input
        E_INPUT_SOURCE_NONE,
    }

    // / ADC setting index
    public static enum E_ADC_SET_INDEX {
        // / ADC setting VGA
        ADC_SET_VGA,
        // / ADC setting YPBPR_SD
        ADC_SET_YPBPR_SD,
        // / ADC setting YPBPR_HD
        ADC_SET_YPBPR_HD,
        // / ADC setting ADC_SET_SCART_RGB
        ADC_SET_SCART_RGB,
        // / ADC setting YPBPR2_SD
        ADC_SET_YPBPR2_SD,
        // / ADC setting YPBPR2_HD
        ADC_SET_YPBPR2_HD,
        // / ADC setting YPBPR3_SD
        ADC_SET_YPBPR3_SD,
        // / ADC setting YPBPR3_HD
        ADC_SET_YPBPR3_HD,
        // / ADC setting number
        ADC_SET_NUMS
    }

    public class T_MS_CALIBRATION_DATA {
        public int redgain;

        public int greengain;

        public int bluegain;

        public int redoffset;

        public int greenoffset;

        public int blueoffset;

        public T_MS_CALIBRATION_DATA(int v1, int v2, int v3, int v4, int v5, int v6) {
            this.redgain = v1;
            this.greengain = v2;
            this.bluegain = v3;
            this.redoffset = v4;
            this.greenoffset = v5;
            this.blueoffset = v6;
        }
    }

    public class MS_ADC_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / gain, offset setting for ADC
        public T_MS_CALIBRATION_DATA stAdcGainOffsetSetting[];

        public MS_ADC_SETTING() {
            int i;
            stAdcGainOffsetSetting = new T_MS_CALIBRATION_DATA[E_ADC_SET_INDEX.ADC_SET_NUMS
                    .ordinal()];
            for (i = 0; i < E_ADC_SET_INDEX.ADC_SET_NUMS.ordinal(); i++) {
                stAdcGainOffsetSetting[i] = new T_MS_CALIBRATION_DATA((short) 0x80, (short) 0x80,
                        (short) 0x80, (short) 0x80, (short) 0x80, (short) 0x80);
            }
        }
    }

    public class MS_NLA_SETTING {
        // / check sum <<checksum should be put at top of the struct, do not
        // move it to other place>>
        public int u16CheckSum;

        // / items which use no-linear adjust
        public MS_NLA_SET_INDEX msNlaSetIndex;

        // / Point 0,25,50,75,100
        public MS_NLA_POINT stNLASetting[];

        public MS_NLA_SETTING() {
            int i;
            stNLASetting = new MS_NLA_POINT[MS_NLA_SET_INDEX.EN_NLA_NUMS.ordinal()];
            for (i = 0; i < MS_NLA_SET_INDEX.EN_NLA_NUMS.ordinal(); i++) {
                stNLASetting[i] = new MS_NLA_POINT();
            }
            msNlaSetIndex = MS_NLA_SET_INDEX.EN_NLA_VOLUME;
        }
    }

    // / Usr-NLA setting
    public class MS_NLA_POINT {
        // / ponint 0
        public int u8OSD_V0;

        // / ponint 25
        public int u8OSD_V25;

        // / point 50
        public int u8OSD_V50;

        // / point 75
        public int u8OSD_V75;

        // / point 100
        public int u8OSD_V100;

        public MS_NLA_POINT() {
            u8OSD_V0 = 128;
            u8OSD_V25 = 128;
            u8OSD_V50 = 128;
            u8OSD_V75 = 128;
            u8OSD_V100 = 128;
        }
    }

    public enum EN_MS_VIDEOITEM {

        // / brightness
        MS_VIDEOITEM_BRIGHTNESS,
        // / contrast
        MS_VIDEOITEM_CONTRAST,
        // / saturation
        MS_VIDEOITEM_SATURATION,
        // / sharpness
        MS_VIDEOITEM_SHARPNESS,
        // / hue
        MS_VIDEOITEM_HUE,
        // / backlight
        MS_VIDEOITEM_BACKLIGHT,
        // num of video item
        MS_VIDEOITEM_NUM;
    }

    /** picture mode setting */
    public static enum EN_MS_PICTURE {
        // / picture mode lightness
        PICTURE_LIGHTNESS,
        // / picture mode standard
        PICTURE_STANDARD,
        // / picture mode soft
        PICTURE_SOFT,
        // / picture mode user
        PICTURE_USER,
        // / picture game mode
        PICTURE_GAME,
        // / picture auto mode
        PICTURE_AUTO,
        // / picture pc mode
        PICTURE_PC,
        // / picture mode vivid
        PICTURE_VIVID,
        // / picture mode natural
        PICTURE_NATURAL,
        // / picture mode sports
        PICTURE_SPORTS,
        // / picture mode number
        PICTURE_NUMS
    }

    public static class PanelProperty {

        public int width;

        public int height;

        public PanelProperty() {
            width = 0;
            height = 0;
        }

    }

    public EnumInputSource getCurrentInputSource();

    public void setInputSource(EnumInputSource st);

    public boolean execSetInputSource(EnumInputSource st);

    public boolean setHandler(Handler handler, int index);

    public Handler getHandler(int index);

    public void releaseHandler(int index);

    public void loadEssentialDataFromDB();

    public void loadCurAdcDataFromDB(int adcIdx);

    public int getPictureModeIdx();

    public void setPictureModeIdx(EN_MS_PICTURE picMode);

    public boolean execVideoItem(EN_MS_VIDEOITEM eIndex, int value);

    public int getVideoItem(EN_MS_VIDEOITEM eIndex);

    public int getColorTmpIdx();

    public void setColorTmpIdx(EN_MS_COLOR_TEMP colorTmp);

    public String getUpdatePqFilePath(EnumPqUpdateFile epqUpdateFile);

    public void updatePqIniFiles();

    public boolean upgrade(String name, String value);

    public short[] readFromEeprom(short index, int size);

    public boolean writeToEeprom(short index, short[] data);

    public boolean enableUart();

    public boolean switchUart();

    public boolean resetForMbootUpgrade(String pFilename);

    public String getEnvironment(String name);

    public PanelProperty getPanelWidthHeight();

    public short[] readBytesFromI2C(int deviceId, short[] address, short size);

    public boolean writeBytesToI2C(int deviceId, short[] address, short[] writeData);

    public void enableI2c(int i2cDeviceId);

    public void disableI2c(int i2cDeviceId);

    public void restoreFactoryAtvProgramTable(short cityIndex);

    public void restoreFactoryDtvProgramTable(short cityIndex, int which);

    public String getPQVersion(int escalerwindow);

    public DtvDemodDvbtInfo getDemodDVBTInfo() throws TvCommonException;

    public DtvDemodDvbcInfo getDemodDVBCInfo() throws TvCommonException;

    public int getCurrentDtvRoute();

    boolean setChinaDescramblerBoxDelay(int chinaDescramblerBoxDelay);

    double getChinaDescramblerBoxDelay();

    void setWOLEnableStatus(boolean flag);

    boolean getWOLEnableStatus();

    boolean saveChinaDescramblerBox(short chinaDescramblerBox);

    public boolean getMIUenalbe_u();

    public boolean getLVDSenalbe_u();

    public int getMIUpercentage_u();

    public int getLVDSpercentage_u();

    public int getMIUmodulation_u();

    public int getLVDSmodulation_u();

    public boolean setMIUenable_u(boolean b);

    public boolean setMIUmodulation_u(int miumodulation_u);

    public boolean setLVDSenable_u(boolean b);

    public boolean setMIUpercentage_u(int miupercentage_u);

    public boolean setLVDSmodulation_u(int lvdsmodulation_u);

    public boolean setLVDSpercentage_u(int lvdspercengtage_u);

    public int getCredentialMode();

    public void setCredentialMode(int credentialmode);

    public boolean getPerformanceMonitor();

    public void enablePerformanceMonitor(boolean enable);

    public int getCIDebugLevel(int index);

    public void setCIDebugLevel(int debugindex, int debuglevel);

    public int copyCmDb(String srcFile, String destPath);

    public boolean resetToFactoryDefault();
}
