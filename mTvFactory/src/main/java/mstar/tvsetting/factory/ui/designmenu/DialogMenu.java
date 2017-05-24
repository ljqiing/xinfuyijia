/**
 * MStar Software
 * Copyright (c) 2011 - 2012 MStar Semiconductor, Inc. All rights reserved.
 *
 * All software, firmware and related documentation herein (“MStar Software�? are
 * intellectual property of MStar Semiconductor, Inc. (“MStar�? and protected by
 * law, including, but not limited to, copyright law and international treaties.
 * Any use, modification, reproduction, retransmission, or republication of all
 * or part of MStar Software is expressly prohibited, unless prior written
 * permission has been granted by MStar.
 *
 * By accessing, browsing and/or using MStar Software, you acknowledge that you
 * have read, understood, and agree, to be bound by below terms (“Terms�? and to
 * comply with all applicable laws and regulations:
 *
 * 1. MStar shall retain any and all right, ownership and interest to MStar
 * Software and any modification/derivatives thereof.  No right, ownership,
 * or interest to MStar Software and any modification/derivatives thereof is
 * transferred to you under Terms.
 *
 * 2. You understand that MStar Software might include, incorporate or be supplied
 * together with third party’s software and the use of MStar Software may require
 * additional licenses from third parties.  Therefore, you hereby agree it is your
 * sole responsibility to separately obtain any and all third party right and
 * license necessary for your use of such third party’s software.
 *
 * 3. MStar Software and any modification/derivatives thereof shall be deemed as
 * MStar’s confidential information and you agree to keep MStar’s confidential
 * information in strictest confidence and not disclose to any third party.
 *
 * 4. MStar Software is provided on an “AS IS�?basis without warranties of any kind.
 * Any warranties are hereby expressly disclaimed by MStar, including without
 * limitation, any warranties of merchantability, non-infringement of intellectual
 * property rights, fitness for a particular purpose, error free and in conformity
 * with any international standard.  You agree to waive any claim against MStar for
 * any loss, damage, cost or expense that you may incur related to your use of MStar
 * Software.  In no event shall MStar be liable for any direct, indirect, incidental
 * or consequential damages, including without limitation, lost of profit or revenues,
 * lost or damage of data, and unauthorized system use.  You agree that this Section 4
 * shall still apply without being affected even if MStar Software has been modified
 * by MStar in accordance with your request or instruction for your use, except
 * otherwise agreed by both parties in writing.
 *
 * 5. If requested, MStar may from time to time provide technical supports or
 * services in relation with MStar Software to you for your use of MStar Software
 * in conjunction with your or your customer’s product (“Services�?.  You understand
 * and agree that, except otherwise agreed by both parties in writing, Services are
 * provided on an “AS IS�?basis and the warranty disclaimer set forth in Section 4
 * above shall apply.
 *
 * 6. Nothing contained herein shall be construed as by implication, estoppels or
 * otherwise: (a) conferring any license or right to use MStar name, trademark,
 * service mark, symbol or any other identification; (b) obligating MStar or any
 * of its affiliates to furnish any person, including without limitation, you and
 * your customers, any assistance of any kind whatsoever, or any information; or
 * (c) conferring any license or right under any intellectual property right.
 *
 * 7. These terms shall be governed by and construed in accordance with the laws
 * of Taiwan, R.O.C., excluding its conflict of law rules.  Any and all dispute
 * arising out hereof or related hereto shall be finally settled by arbitration
 * referred to the Chinese Arbitration Association, Taipei in accordance with
 * the ROC Arbitration Law and the Arbitration Rules of the Association by three (3)
 * arbitrators appointed in accordance with the said Rules.  The place of
 * arbitration shall be in Taipei, Taiwan and the language shall be English.
 * The arbitration award shall be final and binding to both parties.
 */

package mstar.tvsetting.factory.ui.designmenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.mstar.android.tv.TvCommonManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.mstar.android.tvapi.common.listener.OnEventListener;
import android.os.Message;

import mstar.factorymenu.ui.R;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DialogMenu extends Dialog {
    private static final int DIALOG_UPGRADE_NONE = 0;

    private static final int DIALOG_UPGRADE_MBOOT = 1;

    private static final int DIALOG_UPGRADE_MAIN = 2;

    private static final int DIALOG_UPGRADE_6M30 = 3;

    private static final int DIALOG_UPGRADE_DUAL_URSA = 4;

    private static final String MBOOT = "mboot.bin";

    private static final String MSTARUPGRADE = "MstarUpgrade.bin";

    private static final String MERGE_URSA_BIN = "6m30.bin";

    private static final String DUAL_URSA_BIN = "ursa.bin";

    private static DesignMenuActivity dialogmenuactivity;

    private boolean bUSBUpgradeFlag;

    private static int m_dialog_upgrade_type = DIALOG_UPGRADE_NONE;

    protected TextView textview_dialog_confirm;

    protected TextView textview_dialog_cancel;

    protected static TextView textview_dialog_hint;

    protected static TextView textview_dialog_status;

    public int parentItemId;

    private static final String[] upgradeStatus = {
            "Fail!", "Success!", "File Not Found!", "Please Plug USB!"
    };

    private static String port = null;

    private static String exhub = null;

    public enum EnumUpgradeStatus {
        // status fail
        E_UPGRADE_FAIL,
        // status success
        E_UPGRADE_SUCCESS,
        // file not found
        E_UPGRADE_FILE_NOT_FOUND,
    }

    public DialogMenu(Context context, int theme) {
        super(context, theme);
        parentItemId = theme;
        dialogmenuactivity = (DesignMenuActivity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (parentItemId == R.id.textview_factory_otheroption_upgrademboot_val) {
            m_dialog_upgrade_type = DIALOG_UPGRADE_MBOOT;
            setContentView(R.layout.dialogmenu);
        }
        if (parentItemId == R.id.textview_factory_otheroption_upgrademain_val) {
            m_dialog_upgrade_type = DIALOG_UPGRADE_MAIN;
            setContentView(R.layout.dialogmenu);
        }
        if (parentItemId == R.id.textview_factory_otheroption_upgrade6m30_val) {
            m_dialog_upgrade_type = DIALOG_UPGRADE_6M30;
            setContentView(R.layout.dialogmenu);
        }
        if (parentItemId == R.id.textview_factory_otheroption_upgradedualursa_val) {
            m_dialog_upgrade_type = DIALOG_UPGRADE_DUAL_URSA;
            setContentView(R.layout.dialogmenu);
        }
        textview_dialog_confirm = (TextView) findViewById(R.id.textview_factory_dialogmenu_confirm);
        textview_dialog_cancel = (TextView) findViewById(R.id.textview_factory_dialogmenu_cancel);
        textview_dialog_hint = (TextView) findViewById(R.id.textview_factory_dialogmenu_title);
        textview_dialog_status = (TextView) findViewById(R.id.textview_factory_dialogmenu_status);
        registerListeners();
        bUSBUpgradeFlag = true;
    }

    private void registerListeners() {
        textview_dialog_confirm.setOnClickListener(listener);
        textview_dialog_cancel.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_factory_dialogmenu_confirm:
                    if (bUSBUpgradeFlag) {
                        DialogMenuConfirm(parentItemId);
                    } else {
                        DialogMenuCancel(parentItemId);
                    }
                    break;
                case R.id.textview_factory_dialogmenu_cancel:
                    DialogMenuCancel(parentItemId);
                    break;
                default:
                    break;
            }
        }
    };

    private boolean CheckUsbIsExist() {
        boolean ret = false;
        // TODO check USB exist function
        ret = DesignMenuActivity.CheckUsbIsExist();
        return ret;
    }

    private static String FindFileOnUSB(String filename) {
        // TODO Find File On USB function
        String filepath = "";
        File usbroot = new File("/mnt/usb/");
        File targetfile;
        if (usbroot != null && usbroot.exists()) {
            File[] usbitems = usbroot.listFiles();
            int sdx = 0;
            for (; sdx < usbitems.length; sdx++) {
                if (usbitems[sdx].isDirectory()) {
                    targetfile = new File(usbitems[sdx].getPath() + "/" + filename);
                    if (targetfile != null && targetfile.exists()) {
                        filepath = usbitems[sdx].getPath() + "/" + filename;
                        break;
                    }
                }
            }
        }
        return filepath;
    }

    static int UpgradeMbootFun() {
        int ret = 0;
        // TODO UpgradeMboot function
        String mbootpath;
        mbootpath = FindFileOnUSB(MBOOT);
        if ("" == mbootpath) {
            ret = EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND.ordinal();
        } else {
            try {
                if (TvManager.getInstance().resetForMbootUpgrade(mbootpath)) {
                    ret = EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal();
                } else {
                    ret = EnumUpgradeStatus.E_UPGRADE_FAIL.ordinal();
                }
            } catch (TvCommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return ret;
    }

    static int UpgradeMainFun() {
        int ret = 0;
        // TODO UpgradeMain function
        String mainpath;
        mainpath = FindFileOnUSB(MSTARUPGRADE);// should not change this
                                                     // file name
        if ("" == mainpath) {
            ret = EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND.ordinal();
        } else {
            try {
                if (TvManager.getInstance().setEnvironment("upgrade_mode", "usb")) {
                    if (!changeToPhyPath(mainpath)) {
                        Log.d("UpgradeMainFun:", "changeToPhyPath Failed!");
                        ret = EnumUpgradeStatus.E_UPGRADE_FAIL.ordinal();
                    }

                    ret = EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal();
                } else {
                    Log.d("UpgradeMainFun:", "setEnvironment Failed!");
                    ret = EnumUpgradeStatus.E_UPGRADE_FAIL.ordinal();
                }
            } catch (TvCommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return ret;
    }

    static boolean changeToPhyPath(String filepath) {
        boolean ret = false;
        Log.d("changeToPhyPort:", "filepath = " + filepath);
        String sdindex;
        sdindex = filepath.substring(9, 13); // "/mnt/usb/sdx1" ==> "sdx1"
        Log.d("changeToPhyPort:", "sdindex = " + sdindex);
        ret = getPhyPortInfo(sdindex);
        Log.d("changeToPhyPort:", "port = " + port);
        Log.d("changeToPhyPort:", "exhub = " + exhub);
        if (ret) {
            try {
                ret = TvManager.getInstance().setEnvironment("usb_upgrade_port", port);
            } catch (TvCommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                ret = TvManager.getInstance().setEnvironment("usb_upgrade_exhub_port ", exhub);
            } catch (TvCommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!ret) {
                Log.d("changeToPhyPort:", "setEnvironment failed!");
            }
        } else {
            Log.d("changeToPhyPort:", "getPhyPortInfo failed!");
        }

        return ret;
    }

    static boolean getPhyPortInfo(String sdx) {
        boolean ret = false;
        FileReader filereader = null;
        try {
            filereader = new FileReader("/proc/partitions");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (filereader == null) {
            Log.d("getPhyPortInfo:", "Can't find the file /proc/partitions");
        }
        BufferedReader bufferedreader = new BufferedReader(filereader);
        String lineString = bufferedreader.toString();
        while (lineString != null) {
            try {
                lineString = bufferedreader.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // Log.d("sdx = ", sdx);
            if (lineString == null) {
                Log.d("getPhyPortInfo:", "Can't find " + sdx + "on partitions");
                break;
            }

            if (lineString.indexOf(sdx) >= 0) {
                if (lineString.indexOf("s=") == -1 || lineString.indexOf("p=") == -1
                        || lineString.indexOf("b=") == -1) {
                    Log.d("getPhyPortInfo:", "Can't get the right infomation of " + sdx);
                    break;
                }

                char sss = lineString.charAt(lineString.indexOf("s=") + 2);
                char ppp = lineString.charAt(lineString.indexOf("p=") + 2);
                char bbb = lineString.charAt(lineString.indexOf("b=") + 2);
                port = "" + bbb;
                exhub = "" + ppp;
                if (sss == '3') // not hub
                {
                    exhub = "0";
                    Log.d("getPhyPortInfo:", "No hub on port" + port);
                }
                ret = true;
                break;
            }
        }

        return ret;
    }

    static int Upgrade6M30Fun() {
        TvManager tvManager = TvManager.getInstance();
        TvManagerOnEventListener aaa = new TvManagerOnEventListener();
        tvManager.setOnEventListener(aaa);
        int ret = 0;
        // TODO Upgrade6M30 function
        String ursapath;
        ursapath = FindFileOnUSB(MERGE_URSA_BIN);
        if ("" == ursapath) {
            ret = EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND.ordinal();
        } else {
            try {
                if (0 == tvManager.startUrsaFirmwareUpgrade(ursapath).ordinal()) {
                    ret = EnumUpgradeStatus.E_UPGRADE_FAIL.ordinal();
                } else {
                    ret = EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal();
                }
            } catch (TvCommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ret;
    }

    static int UpgradeDualUrsaFun() {
        TvManager tvManager = TvManager.getInstance();
        TvManagerOnEventListener dualUrsaUpgradeListener = new TvManagerOnEventListener();
        tvManager.setOnEventListener(dualUrsaUpgradeListener);
        int ret = 0;
        // TODO Upgrade6M30 function
        String ursapath;
        ursapath = FindFileOnUSB(DUAL_URSA_BIN);
        if ("" == ursapath) {
            ret = EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND.ordinal();
        } else {
            try {
                if (0 == tvManager.startUrsaFirmwareUpgrade(ursapath).ordinal()) {
                    ret = EnumUpgradeStatus.E_UPGRADE_FAIL.ordinal();
                } else {
                    ret = EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal();
                }
            } catch (TvCommonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ret;
    }

    public void DialogMenuConfirm(int which) {
        switch (which) {
            case R.id.textview_factory_otheroption_upgrademboot_val:
                if (CheckUsbIsExist() == true) {
                    USBUpgradeThread.UpgradeMboot();
                    bUSBUpgradeFlag = false;
                } else {
                    textview_dialog_hint.setText(upgradeStatus[3]);
                }
                break;
            case R.id.textview_factory_otheroption_upgrademain_val:
                if (CheckUsbIsExist() == true) {
                    USBUpgradeThread.UpgradeMain();
                    bUSBUpgradeFlag = false;
                } else {
                    textview_dialog_hint.setText("Please Plug USB!");
                }
                break;
            case R.id.textview_factory_otheroption_upgrade6m30_val:
                if (CheckUsbIsExist() == true) {
                    USBUpgradeThread.Upgrade6M30();
                    bUSBUpgradeFlag = false;
                } else {
                    textview_dialog_hint.setText(upgradeStatus[3]);
                }
                break;
            case R.id.textview_factory_otheroption_upgradedualursa_val:
                if (CheckUsbIsExist() == true) {
                    USBUpgradeThread.UpgradeDualUrsa();
                    bUSBUpgradeFlag = false;
                } else {
                    textview_dialog_hint.setText(upgradeStatus[3]);
                }
                break;
            default:
                break;
        }
    }

    private void DialogMenuCancel(int which) {
        switch (which) {
            case R.id.textview_factory_otheroption_upgrademboot_val:
                ;
                break;
            default:
                break;
        }
        this.dismiss();
    }

    private static ProgressDialog progressDialog = null;

    protected static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == USBUpgradeThread.UPGRATE_START) {
                progressDialog = getProgressDialog();
                progressDialog.show();
            } else if (msg.what == USBUpgradeThread.UPGRATE_END_SUCCESS) {
                progressDialog.dismiss();
                textview_dialog_hint.setText(upgradeStatus[1]);
            } else if (msg.what == USBUpgradeThread.UPGRATE_END_SUCCESS_MAIN)// UpgradeMain
                                                                             // Prepare
                                                                             // Success
            {
                progressDialog.dismiss();
                textview_dialog_hint.setText("Rebooting! Please Wait...");
                TvCommonManager.getInstance().rebootSystem("reboot");
            } else if (msg.what == USBUpgradeThread.UPGRATE_END_FILE_NOT_FOUND) {
                progressDialog.dismiss();
                String strFileNotFound;
                switch (m_dialog_upgrade_type) {
                    case DIALOG_UPGRADE_MBOOT:
                        strFileNotFound = upgradeStatus[2] + "(" + MBOOT + ")";
                        break;
                    case DIALOG_UPGRADE_MAIN:
                        strFileNotFound = upgradeStatus[2] + "(" + MSTARUPGRADE+ ")";
                        break;
                    case DIALOG_UPGRADE_6M30:
                        strFileNotFound = upgradeStatus[2] + "(" + MERGE_URSA_BIN+ ")";
                        break;
                    case DIALOG_UPGRADE_DUAL_URSA:
                        strFileNotFound = upgradeStatus[2] + "(" + DUAL_URSA_BIN+ ")";
                        break;
                    default:
                        strFileNotFound = upgradeStatus[2];
                }
                textview_dialog_hint.setText(strFileNotFound);
            } else {
                progressDialog.dismiss();
                textview_dialog_hint.setText(upgradeStatus[0]);
            }
        };
    };

    protected static Handler statusHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("DialogMenu", "  -- statusHandler");
            super.handleMessage(msg);
            switch (msg.arg1) {
                case 0:
                    textview_dialog_status.setText("Init...");
                    break;
                case 1:
                    textview_dialog_status.setText("EnterIsp...");
                    break;
                case 2:
                    textview_dialog_status.setText("Erase...");
                    break;
                case 3:
                    textview_dialog_status.setText("Blank...");
                    break;
                case 4:
                    textview_dialog_status.setText("Program...");
                    break;
                case 5:
                    textview_dialog_status.setText("Verify...");
                    break;
                case 6:
                    textview_dialog_status.setText("Exit...");
                    break;
                case 7:
                    textview_dialog_status.setText("Idle...");
                    break;
                case 8:
                    textview_dialog_status.setText("Error...");
                    break;
                default:
                    textview_dialog_status.setText("...");
                    break;
            }
        };
    };

    private static ProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(dialogmenuactivity);
            progressDialog.setMessage(dialogmenuactivity
                    .getString(R.string.str_textview_factory_otheroption_waiting_val));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
        }
        return progressDialog;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static Handler getStatusHandler() {
        return statusHandler;
    }

    private static class TvManagerOnEventListener implements OnEventListener {
        public boolean onEvent(Message msg) {
            Log.d("TvManagerOnEventListener", "----" + msg.arg1);
            switch (msg.arg1) {
                case 0:
                    textview_dialog_status.setText("Init...");
                    break;
                case 1:
                    textview_dialog_status.setText("EnterIsp...");
                    break;
                case 2:
                    textview_dialog_status.setText("Erase...");
                    break;
                case 3:
                    textview_dialog_status.setText("Blank...");
                    break;
                case 4:
                    textview_dialog_status.setText("Program...");
                    break;
                case 5:
                    textview_dialog_status.setText("Verify...");
                    break;
                case 6:
                    textview_dialog_status.setText("Exit...");
                    break;
                case 7:
                    textview_dialog_status.setText("Idle...");
                    break;
                case 8:
                    textview_dialog_status.setText("Error...");
                    break;
                default:
                    textview_dialog_status.setText("...");
                    break;
            }

            return true;
        }
    }

}
