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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.mstar.android.tvapi.common.vo.ProgramInfo;

import mstar.factorymenu.ui.R;
import mstar.tvsetting.factory.desk.FactoryDB;
import mstar.tvsetting.factory.desk.IFactoryDesk;
import mstar.tvsetting.factory.ui.factorymenu.FactoryMenuActivity;
import mstar.tvsetting.factory.ui.FactoryIntent;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Environment;
import android.content.Intent;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.storage.MStorageManager;
import com.mstar.android.tv.TvFactoryManager;
import com.mstar.android.tv.TvAudioManager;

@SuppressWarnings("unused")
public class OtherOptionAdjustViewHolder {

    private DesignMenuActivity otheroptionActivity;

    private IFactoryDesk factoryManager;

    private MStorageManager stm;

    protected TextView text_factory_otheroption_watchdog_val;

    protected TextView text_factory_otheroption_upgrademboot_val;

    protected TextView text_factory_otheroption_upgrademain_val;

    protected TextView text_factory_otheroption_upgrade6m30_val;

    protected TextView text_factory_otheroption_upgradedualursa_val;

    protected TextView text_factory_otheroption_restoretodefault_val;

    protected TextView text_factory_otheroption_poweronmode_val;

    protected TextView text_factory_otheroption_uartenable_val;

    protected TextView text_factory_otheroption_pvrrecordall_val;

    protected TextView text_factory_otheroption_dtvavdelay_val;

    protected TextView text_factory_otheroption_3d_selfadaptive_detectlevel_val;

    protected TextView text_factory_otheroption_pq_updagte_state;

    protected TextView text_factory_otheroption_mstv_tool_state;

    protected TextView text_factory_otheroption_dtv_preset_state;

    protected TextView text_factory_otheroption_str_enable_val;

    protected TextView text_factory_otheroption_xvycc;

    protected TextView text_factory_otheroption_xvycc_val;

    protected LinearLayout linear_factory_otheroption_mstv_tool;

    protected LinearLayout linear_factory_otheroption_pq_update;

    protected LinearLayout linear_factory_otheroption_dtv_preset;

    protected TextView text_factory_otheroption_wake_on_lan_val;

    protected TextView text_factory_otheroption_audiodelay_val;

    private EditText editText;

    private Builder editDialog;

    private int watchdogindex = 0;

    private int testpartternindex = 0;

    private int restoretodefaultindex = 0;

    private int poweronmodeindex = 0;

    private int uartenableindex = 0;

    private int dtvavdelayindex = 0;

    private int initialchannelindex = 0;

    private int wakeonlanindex = 0;

    private int enableSTRidex = 0;

    private int threeDselfadaptivelevelindex = 0;

    private int upgrademboot = 0;

    private int upgrade6m30 = 0;

    private int upgradedualursa = 0;

    private int audiodelayvalue = 0;

    private static final byte DialogType = 0;

    private boolean pq_update_state = false;

    private String[] watchdogenable = {
            "Off", "On"
    };

    private String[] upgradeStatus = {
            "Error!", "Sucess!", "Fail!", "File Not Found!", "Please Plug USB!"
    };

    private String[] powerOnMode = {
            "Secondary", "Memory", "Direct"
    };

    private String[] dtvavdelayenable = {
            "On", "Off"
    };

    private String[] restoretodefenable = {
            "On", "Off"
    };

    private String[] uartenable = {
            "Off", "On"
    };

    private String[] threeDselfadaptivelevel;

    private String[] wakeonlan = {
            "Off", "On"
    };

    private String[] enableSTR = {
            "On", "Off"
    };

    private String[] dtvType = {
            "dtmb", "dvbc"
    };

    private float[] rgbwxyVals = new float[8];

    private String[] rgbwxyStrings;

    private int rgbwxyInputIdx = 0;

    private String inputString;

    private boolean CheckUsbIsExist() {
        boolean ret = true;
        // TODO check usb exit function
        return ret;
    }

    private int UpgradeMbootFun() {
        int ret = 1;
        // TODO UpgradeMboot function
        return ret;
    }

    private boolean UpgradeMainFun() {
        boolean ret = false;
        // TODO UpgradeMain function
        return ret;
    }

    private int Upgrade6M30Fun() {
        int ret = 1;
        // TODO Upgrade6M30 function
        return ret;
    }

    private int UpgradeDualUrsaFun() {
        int ret = 1;
        // TODO UpgradeDualUrsaFun function
        return ret;
    }
    private void openLayoutDialog() {
        DialogMenu dlg = new DialogMenu(otheroptionActivity,
                R.id.textview_factory_otheroption_upgrademboot_val);
        /*
         * dlg.setOnDismissListener(new OnDismissListener() { public void
         * onDismiss(DialogInterface dialog) { dialog.dismiss(); } });
         */
        dlg.show();
    }

    private void openLayoutDialogMain() {
        DialogMenu dlg = new DialogMenu(otheroptionActivity,
                R.id.textview_factory_otheroption_upgrademain_val);
        /*
         * dlg.setOnDismissListener(new OnDismissListener() { public void
         * onDismiss(DialogInterface dialog) { dialog.dismiss(); } });
         */
        dlg.show();
    }

    private void openLayoutDialog6M30() {
        DialogMenu dlg = new DialogMenu(otheroptionActivity,
                R.id.textview_factory_otheroption_upgrade6m30_val);
        /*
         * dlg.setOnDismissListener(new OnDismissListener() { public void
         * onDismiss(DialogInterface dialog) { dialog.dismiss(); } });
         */
        dlg.show();
    }

    private void openLayoutDialogDualUrsa() {
        DialogMenu dlg = new DialogMenu(otheroptionActivity,
                R.id.textview_factory_otheroption_upgradedualursa_val);
        /*
         * dlg.setOnDismissListener(new OnDismissListener() { public void
         * onDismiss(DialogInterface dialog) { dialog.dismiss(); } });
         */
        dlg.show();
    }

    private void openAndriodDialog(boolean flag) {
        AlertDialog.Builder builder = new Builder(otheroptionActivity);
        if (flag == true) {
            builder.setMessage("Are You Sure Upgrade Mboot?");
        } else {
            builder.setMessage("Please Plug USB!");
        }
        // builder.setTitle("???");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (CheckUsbIsExist() == true) {
                    upgrademboot = UpgradeMbootFun();
                    text_factory_otheroption_upgrademboot_val.setText(upgradeStatus[upgrademboot]);
                    // otheroptionActivity.finish();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                upgrademboot = 0;
                dialog.dismiss();
                text_factory_otheroption_upgrademboot_val.setText(upgradeStatus[upgrademboot]);
            }
        });
        builder.create().show();
    }

    private void openAndriodDialogMain(boolean flag) {
        AlertDialog.Builder builder = new Builder(otheroptionActivity);
        if (flag == true) {
            builder.setMessage("Are You Sure Upgrade Main?");
        } else {
            builder.setMessage("Please Plug USB!");
        }
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (CheckUsbIsExist() == true) {
                    if (UpgradeMainFun()) {
                        text_factory_otheroption_upgrademain_val.setText("Please Reboot the TV!");
                    } else {
                        text_factory_otheroption_upgrademain_val.setText("Faild!");
                    }
                    // otheroptionActivity.finish();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void openAndriodDialog6M30(boolean flag) {
        AlertDialog.Builder builder = new Builder(otheroptionActivity);
        if (flag == true) {
            builder.setMessage("Are You Sure Upgrade 6M30?");
        } else {
            builder.setMessage("Please Plug USB!");
        }
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (CheckUsbIsExist() == true) {
                    upgrade6m30 = Upgrade6M30Fun();
                    text_factory_otheroption_upgrade6m30_val.setText(upgradeStatus[upgrade6m30]);
                    // otheroptionActivity.finish();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                upgrade6m30 = 0;
                dialog.dismiss();
                text_factory_otheroption_upgrade6m30_val.setText(upgradeStatus[upgrade6m30]);
            }
        });
        builder.create().show();
    }

    private void openAndriodDialogDualUrsa(boolean flag) {
        AlertDialog.Builder builder = new Builder(otheroptionActivity);
        if (flag == true) {
            builder.setMessage("Are You Sure Upgrade Dual Ursa?");
        } else {
            builder.setMessage("Please Plug USB!");
        }
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (CheckUsbIsExist() == true) {
                    upgradedualursa = UpgradeDualUrsaFun();
                    text_factory_otheroption_upgradedualursa_val.setText(upgradeStatus[upgradedualursa]);
                    // otheroptionActivity.finish();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                upgradedualursa = 0;
                dialog.dismiss();
                text_factory_otheroption_upgradedualursa_val.setText(upgradeStatus[upgradedualursa]);
            }
        });
        builder.create().show();
    }

    private void openAndriodDialogDvbtDvbcInfo() {

    }

    private OnClickListener listener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            int currentid = otheroptionActivity.getCurrentFocus().getId();
            switch (currentid) {
                case R.id.linearlayout_factory_otheroption_upgrademboot:
                    if (DialogType == 1) {
                        openAndriodDialog(CheckUsbIsExist());
                    } else {
                        openLayoutDialog();
                    }
                    break;
                case R.id.linearlayout_factory_otheroption_upgrademain:
                    if (DialogType == 1) {
                        openAndriodDialogMain(CheckUsbIsExist());
                    } else {
                        openLayoutDialogMain();
                    }
                    break;
                case R.id.linearlayout_factory_otheroption_upgrade6m30:
                    if (DialogType == 1) {
                        openAndriodDialog6M30(CheckUsbIsExist());
                    } else {
                        openLayoutDialog6M30();
                    }
                    break;
                case R.id.linearlayout_factory_otheroption_upgradedualursa:
                    if (DialogType == 1) {
                        openAndriodDialogDualUrsa(CheckUsbIsExist());
                    } else {
                        openLayoutDialogDualUrsa();
                    }
                    break;
                case R.id.linearlayout_factory_otheroption_mstv_tool:
                    if (factoryManager.enableUartDebug()) {
                        text_factory_otheroption_mstv_tool_state.setText("success!");
                    } else {
                        text_factory_otheroption_mstv_tool_state.setText("failed!");
                    }
                    break;
                case R.id.linearlayout_factory_otheroption_pq_update:
                    if (pq_update_state) {
                        text_factory_otheroption_pq_updagte_state.setText("Please Click!");
                        pq_update_state = false;
                    } else {
                        updatePqFile();
                        pq_update_state = true;
                    }
                    break;
                case R.id.linearlayout_factory_otheroption_dtv_preset: {
                    new AlertDialog.Builder(otheroptionActivity)
                            // build AlertDialog
                            .setTitle("Choose type please")
                            // title
                            .setItems(dtvType, new DialogInterface.OnClickListener() { // content
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            doDtvPreset(which);
                                        }
                                    })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss(); // 关闭alertDialog
                                }
                            }).show();
                }
                    break;
                case R.id.linearlayout_factory_otheroption_xvycc:
                    rgbwxyInputIdx = 0;
                    inputString = "";
                    showXvyccInput(rgbwxyInputIdx);
                    break;
                default:
                    break;
            }
        }
    };

    private void doDtvPreset(int which) {// 1 is for dvbc, 0 is for dtmb
        System.out.println("\n==>restoreFactoryDtvProgramTable from java");
        text_factory_otheroption_dtv_preset_state
                .setText(R.string.str_factory_otheroption_dtv_preset_hint_msg1);
        factoryManager.restoreFactoryDtvProgramTable((short) 0, which);
        text_factory_otheroption_dtv_preset_state
                .setText(R.string.str_factory_otheroption_dtv_preset_hint_msg2);
    }

    private void showXvyccInput(int index) {
        final EditText editTextXy = new EditText(otheroptionActivity);
        editTextXy.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        new AlertDialog.Builder(otheroptionActivity)
                .setTitle("请输入" + rgbwxyStrings[index] + "小数点后的整数，最多九位")
                .setIcon(android.R.drawable.ic_dialog_info).setView(editTextXy)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (editTextXy.getText().toString().isEmpty()
                                || editTextXy.getText().toString().length() > 9) {
                            arg0.dismiss();
                            Toast.makeText(otheroptionActivity, "Your input is invalite!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String str = editTextXy.getText().toString();
                            inputString += ("0." + str + ";");
                            rgbwxyVals[rgbwxyInputIdx] = Float.parseFloat("0." + str);
                            Toast.makeText(otheroptionActivity,
                                    "Your input is :\n" + rgbwxyVals[rgbwxyInputIdx],
                                    Toast.LENGTH_SHORT).show();
                            if (rgbwxyInputIdx == 7) {
                                Toast.makeText(otheroptionActivity,
                                        "Your all inputs are :\n" + inputString, Toast.LENGTH_LONG)
                                        .show();
                                try {
                                    TvManager
                                            .getInstance()
                                            .getFactoryManager()
                                            .setXvyccDataFromPanel(rgbwxyVals[0], rgbwxyVals[1],
                                                    rgbwxyVals[2], rgbwxyVals[3], rgbwxyVals[4],
                                                    rgbwxyVals[5], rgbwxyVals[6], rgbwxyVals[7], 0);
                                } catch (TvCommonException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                rgbwxyInputIdx++;
                                showXvyccInput(rgbwxyInputIdx);
                            }

                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                }).show();
    }

    private void registerListeners() {
        text_factory_otheroption_upgrademboot_val.setOnClickListener(listener);
        text_factory_otheroption_upgrademain_val.setOnClickListener(listener);
        text_factory_otheroption_upgrade6m30_val.setOnClickListener(listener);
        text_factory_otheroption_upgradedualursa_val.setOnClickListener(listener);
        linear_factory_otheroption_mstv_tool.setOnClickListener(listener);
        linear_factory_otheroption_pq_update.setOnClickListener(listener);
        linear_factory_otheroption_dtv_preset.setOnClickListener(listener);

    }

    public OtherOptionAdjustViewHolder(DesignMenuActivity mstarActivity, IFactoryDesk factoryManager) {
        otheroptionActivity = mstarActivity;
        this.factoryManager = factoryManager;
    }

    public void findView() {

        text_factory_otheroption_watchdog_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_watchdog_val);
        text_factory_otheroption_upgrademboot_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_upgrademboot_val);
        text_factory_otheroption_upgrademain_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_upgrademain_val);
        text_factory_otheroption_upgrade6m30_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_upgrade6m30_val);
        text_factory_otheroption_upgradedualursa_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_upgradedualursa_val);
        text_factory_otheroption_restoretodefault_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_restoretodefault_val);
        text_factory_otheroption_poweronmode_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_poweronmode_val);
        text_factory_otheroption_uartenable_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_uartenable_val);
        text_factory_otheroption_pvrrecordall_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_pvrrecordall_val);
        text_factory_otheroption_dtvavdelay_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_dtvavdelay_val);

        text_factory_otheroption_3d_selfadaptive_detectlevel_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_3d_selfadaptive_detectlevel_val);
        text_factory_otheroption_pq_updagte_state = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_pq_update_state);
        text_factory_otheroption_mstv_tool_state = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_mstv_tool_state);
        text_factory_otheroption_dtv_preset_state = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_dtv_preset_state);
        text_factory_otheroption_xvycc = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_xvycc);
        text_factory_otheroption_xvycc_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_xvycc_val);
        linear_factory_otheroption_mstv_tool = (LinearLayout) otheroptionActivity
                .findViewById(R.id.linearlayout_factory_otheroption_mstv_tool);
        linear_factory_otheroption_pq_update = (LinearLayout) otheroptionActivity
                .findViewById(R.id.linearlayout_factory_otheroption_pq_update);
        linear_factory_otheroption_dtv_preset = (LinearLayout) otheroptionActivity
                .findViewById(R.id.linearlayout_factory_otheroption_dtv_preset);
        text_factory_otheroption_str_enable_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_str_enable_val);
        text_factory_otheroption_wake_on_lan_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_wake_on_lan_val);
        text_factory_otheroption_audiodelay_val = (TextView) otheroptionActivity
                .findViewById(R.id.textview_factory_otheroption_audiodelay_val);

        rgbwxyStrings = otheroptionActivity.getResources().getStringArray(R.array.xvycc_array);
        threeDselfadaptivelevel = otheroptionActivity.getResources().getStringArray(R.array.str_arr_3d_self_adaptive);
    }

    void restoreToDefault() {
        File dir = new File("/tvdatabase/Database");
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdir();
        }
        // close DB before recovery db
        FactoryDB.getInstance(otheroptionActivity).closeDB();
        File fileFactory = new File(dir, "factory.db");
        loadDbFile(R.raw.factory, fileFactory);
        File fileUserSetting = new File(dir, "user_setting.db");
        loadDbFile(R.raw.user_setting, fileUserSetting);
        // reopen the db after recovery db
        FactoryDB.getInstance(otheroptionActivity).openDB();
        factoryManager.loadEssentialDataFromDB();
    }

    private void loadDbFile(int rawId, File file) {
        InputStream dbInputStream = otheroptionActivity.getResources().openRawResource(rawId);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = dbInputStream.read(bytes)) > 0) {
                fos.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                dbInputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public boolean onCreate() {
        watchdogindex = factoryManager.getWatchDogMode();
        testpartternindex = factoryManager.getTestPattern();
        restoretodefaultindex = 1;
        poweronmodeindex = factoryManager.getPowerOnMode();
        uartenableindex = factoryManager.getUartOnOff() ? 1 : 0;
        dtvavdelayindex = factoryManager.getDtvAvAbnormalDelay() ? 1 : 0;
        initialchannelindex = factoryManager.getFactoryPreSetFeature();
        wakeonlanindex = factoryManager.getWOLEnableStatus() ? 1 : 0;
        audiodelayvalue = TvAudioManager.getInstance().getSoundSpeakerDelay();

        threeDselfadaptivelevelindex = TvFactoryManager.getInstance().get3DSelfAdaptiveLevel();
        text_factory_otheroption_watchdog_val.setText(watchdogenable[watchdogindex]);
        enableSTRidex = FactoryDB.getInstance(otheroptionActivity).queryEnableSTR();

        text_factory_otheroption_3d_selfadaptive_detectlevel_val
                .setText(threeDselfadaptivelevel[threeDselfadaptivelevelindex]);
        text_factory_otheroption_poweronmode_val.setText(powerOnMode[poweronmodeindex]);
        text_factory_otheroption_uartenable_val.setText(uartenable[uartenableindex]);
        if (true == TvFactoryManager.getInstance().getPvrRecordAll()) {
            text_factory_otheroption_pvrrecordall_val.setText(otheroptionActivity
                    .getString(R.string.str_textview_factory_otheroption_text_on));
        } else {
            text_factory_otheroption_pvrrecordall_val.setText(otheroptionActivity
                    .getString(R.string.str_textview_factory_otheroption_text_off));
        }
        text_factory_otheroption_restoretodefault_val
                .setText(restoretodefenable[restoretodefaultindex]);

        text_factory_otheroption_audiodelay_val.setText(Integer.toString(audiodelayvalue) + "ms");

        if (enableSTRidex <= 1) {
            text_factory_otheroption_str_enable_val.setText(enableSTR[enableSTRidex]);
        } else {
            text_factory_otheroption_str_enable_val.setText(enableSTR[1] + "  " + enableSTRidex
                    + "times");
        }
        text_factory_otheroption_wake_on_lan_val.setText(wakeonlan[wakeonlanindex]);
        registerListeners();
        return true;
    }

    private void showEditDialog() {
        if (editDialog == null) {
            editDialog = new AlertDialog.Builder(otheroptionActivity);
            editDialog.setTitle("Enter times to STR(canel is 1)");
            editDialog.setIcon(android.R.drawable.ic_dialog_info);
            editDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    if (editText.getText().toString().isEmpty()) {
                        enableSTRidex = 1;
                    } else {
                        enableSTRidex = Integer.parseInt(editText.getText().toString());
                    }
                    if (enableSTRidex <= 1) {
                        text_factory_otheroption_str_enable_val.setText(enableSTR[enableSTRidex]);
                    } else {
                        text_factory_otheroption_str_enable_val.setText(enableSTR[1] + "  "
                                + enableSTRidex + "times");
                    }
                    setSTR(enableSTRidex);
                }
            });
            editDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    enableSTRidex = 1;
                    text_factory_otheroption_str_enable_val.setText(enableSTR[enableSTRidex]);
                    setSTR(enableSTRidex);

                }
            });
        }
        editText = new EditText(otheroptionActivity);
        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editDialog.setView(editText);
        editDialog.show();
    }

    private void setSTR(int times) {
        FactoryDB.getInstance(otheroptionActivity).updateEnableSTR(enableSTRidex);
        try {
            TvManager.getInstance().sendStrCommand(TvManager.STR_CMD_SET_MAX_CNT, times,
                    0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean bRet = true;
        int currentid = otheroptionActivity.getCurrentFocus().getId();
        String str_val = new String();
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroption_watchdog:
                        if (watchdogindex != 1)
                            watchdogindex++;
                        else
                            watchdogindex = 0;
                        text_factory_otheroption_watchdog_val
                                .setText(watchdogenable[watchdogindex]);
                        factoryManager.setWatchDogMode((short) (watchdogindex));
                        break;
                    case R.id.linearlayout_factory_otheroption_restoretodefault:
                        if (restoretodefaultindex != 1) {
                            restoretodefaultindex++;
                            // factoryManager.restoreToDefault();
                        } else {
                            restoretodefaultindex = 0;
                            factoryManager.restoreToDefault();
                        }
                        text_factory_otheroption_restoretodefault_val
                                .setText(restoretodefenable[restoretodefaultindex]);
                        break;
                    case R.id.linearlayout_factory_otheroption_poweronmode:
                        if (poweronmodeindex != IFactoryDesk.EN_POWER_MODE_DIRECT)
                            poweronmodeindex++;
                        else
                            poweronmodeindex = 0;
                        text_factory_otheroption_poweronmode_val
                                .setText(powerOnMode[poweronmodeindex]);
                        factoryManager.setPowerOnMode(poweronmodeindex);
                        break;
                    case R.id.linearlayout_factory_otheroption_uartenable:
                        if (uartenableindex == 0) {
                            uartenableindex = 1;
                            factoryManager.setUartOnOff(true);
                        } else {
                            uartenableindex = 0;
                            factoryManager.setUartOnOff(false);
                        }
                        text_factory_otheroption_uartenable_val
                                .setText(uartenable[uartenableindex]);
                        break;
                    case R.id.linearlayout_factory_otheroption_pvrrecordall:
                        changePVRRecordAll();
                        break;
                    case R.id.linearlayout_factory_otheroption_dtvavdelay:
                        if (dtvavdelayindex != 1) {
                            dtvavdelayindex++;
                            factoryManager.setDtvAvAbnormalDelay(true);
                        } else {
                            dtvavdelayindex = 0;
                            factoryManager.setDtvAvAbnormalDelay(false);
                        }
                        text_factory_otheroption_dtvavdelay_val
                                .setText(dtvavdelayenable[dtvavdelayindex]);
                        break;
                    case R.id.linearlayout_factory_otheroption_3d_selfadaptive_detectlevel:
                        if (threeDselfadaptivelevelindex != TvFactoryManager.THREE_DIMENSION_SELFADAPTIVE_LEVEL_HIGH)
                            threeDselfadaptivelevelindex++;
                        else
                            threeDselfadaptivelevelindex = 0;
                        text_factory_otheroption_3d_selfadaptive_detectlevel_val
                                .setText(threeDselfadaptivelevel[threeDselfadaptivelevelindex]);
                        TvFactoryManager.getInstance().set3DSelfAdaptiveLevel(threeDselfadaptivelevelindex);
                        break;
                    case R.id.linearlayout_factory_otheroption_str_enable:
                        if (enableSTRidex == 0) {
                            showEditDialog();
                        }
                        break;
                    case R.id.linearlayout_factory_otheroption_wake_on_lan:
                        if (wakeonlanindex != 1)
                            wakeonlanindex++;
                        else
                            wakeonlanindex = 0;
                        text_factory_otheroption_wake_on_lan_val.setText(wakeonlan[wakeonlanindex]);
                        factoryManager.setWOLEnableStatus(wakeonlanindex == 1);
                        break;
                    // case R.id.linearlayout_factory_otheroption_xvycc:
                    // if (xvyccVal != 1)
                    // xvyccVal++;
                    // else
                    // xvyccVal = 0;
                    // text_factory_otheroption_xvycc_val
                    // .setText(wakeonlan[xvyccVal]);
                    // try {
                    // TvManager.getInstance().getPictureManager().enableXvyccCompensation(xvyccVal==1,
                    // 0);
                    // } catch (TvCommonException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // }
                    // break;
                    case R.id.linearlayout_factory_otheroption_audiodelay:
                        if (audiodelayvalue != 0xFFFF) {
                            audiodelayvalue += 10;
                        } else {
                            audiodelayvalue = 0;
                        }
                        str_val = Integer.toString(audiodelayvalue);
                        text_factory_otheroption_audiodelay_val.setText(str_val + "ms");
                        TvAudioManager.getInstance().setSoundSpeakerDelay(audiodelayvalue);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroption_watchdog:
                        if (watchdogindex != 0)
                            watchdogindex--;
                        else
                            watchdogindex = 1;
                        text_factory_otheroption_watchdog_val
                                .setText(watchdogenable[watchdogindex]);
                        factoryManager.setWatchDogMode((short) (watchdogindex));
                        break;
                    case R.id.linearlayout_factory_otheroption_restoretodefault:
                        if (restoretodefaultindex != 0) {
                            restoretodefaultindex--;
                            factoryManager.restoreToDefault();
                        } else {
                            restoretodefaultindex = 1;
                            // factoryManager.restoreToDefault();
                        }
                        text_factory_otheroption_restoretodefault_val
                                .setText(restoretodefenable[restoretodefaultindex]);
                        break;
                    case R.id.linearlayout_factory_otheroption_poweronmode:
                        if (poweronmodeindex != 0)
                            poweronmodeindex--;
                        else
                            poweronmodeindex = IFactoryDesk.EN_POWER_MODE_DIRECT;
                        text_factory_otheroption_poweronmode_val
                                .setText(powerOnMode[poweronmodeindex]);
                        factoryManager.setPowerOnMode(poweronmodeindex);
                        break;
                    case R.id.linearlayout_factory_otheroption_uartenable:
                        if (uartenableindex == 1) {
                            uartenableindex = 0;
                            factoryManager.setUartOnOff(false);
                        } else {
                            uartenableindex = 1;
                            factoryManager.setUartOnOff(true);
                        }
                        text_factory_otheroption_uartenable_val
                                .setText(uartenable[uartenableindex]);
                        break;
                    case R.id.linearlayout_factory_otheroption_pvrrecordall:
                        changePVRRecordAll();
                        break;
                    case R.id.linearlayout_factory_otheroption_dtvavdelay:
                        if (dtvavdelayindex != 0) {
                            dtvavdelayindex--;
                            factoryManager.setDtvAvAbnormalDelay(false);
                        } else {
                            dtvavdelayindex = 1;
                            factoryManager.setDtvAvAbnormalDelay(true);
                        }
                        text_factory_otheroption_dtvavdelay_val
                                .setText(dtvavdelayenable[dtvavdelayindex]);
                        break;
                    case R.id.linearlayout_factory_otheroption_3d_selfadaptive_detectlevel:
                        if (threeDselfadaptivelevelindex != 0)
                            threeDselfadaptivelevelindex--;
                        else
                            threeDselfadaptivelevelindex = TvFactoryManager.THREE_DIMENSION_SELFADAPTIVE_LEVEL_HIGH;
                        text_factory_otheroption_3d_selfadaptive_detectlevel_val
                                .setText(threeDselfadaptivelevel[threeDselfadaptivelevelindex]);
                        TvFactoryManager.getInstance().set3DSelfAdaptiveLevel(threeDselfadaptivelevelindex);
                        break;
                    case R.id.linearlayout_factory_otheroption_str_enable:
                        if (enableSTRidex != 0) {
                            enableSTRidex = 0;
                            text_factory_otheroption_str_enable_val
                                    .setText(enableSTR[enableSTRidex]);
                            setSTR(enableSTRidex);
                        }
                        break;
                    case R.id.linearlayout_factory_otheroption_wake_on_lan:
                        if (wakeonlanindex != 0)
                            wakeonlanindex--;
                        else
                            wakeonlanindex = 1;
                        text_factory_otheroption_wake_on_lan_val.setText(wakeonlan[wakeonlanindex]);
                        factoryManager.setWOLEnableStatus(wakeonlanindex == 1);
                        break;
                    // case R.id.linearlayout_factory_otheroption_xvycc:
                    // if (xvyccVal != 0)
                    // xvyccVal--;
                    // else
                    // xvyccVal = 1;
                    // text_factory_otheroption_xvycc_val
                    // .setText(wakeonlan[xvyccVal]);
                    // try {
                    // TvManager.getInstance().getPictureManager().enableXvyccCompensation(xvyccVal==1,
                    // 0);
                    // } catch (TvCommonException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // }
                    // break;
                    case R.id.linearlayout_factory_otheroption_audiodelay:
                        if (audiodelayvalue >= 10) {
                            audiodelayvalue -= 10;
                        } else {
                            audiodelayvalue = 0;
                        }
                        str_val = Integer.toString(audiodelayvalue);
                        text_factory_otheroption_audiodelay_val.setText(str_val + "ms");
                        TvAudioManager.getInstance().setSoundSpeakerDelay(audiodelayvalue);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_ENTER:
                switch (currentid) {
                    case R.id.linearlayout_factory_otheroption_upgrademboot:
                        if (DialogType == 1) {
                            openAndriodDialog(CheckUsbIsExist());
                        } else {
                            openLayoutDialog();
                        }
                        break;
                    case R.id.linearlayout_factory_otheroption_upgrademain:
                        if (DialogType == 1) {
                            openAndriodDialogMain(CheckUsbIsExist());
                        } else {
                            openLayoutDialogMain();
                        }
                        break;
                    case R.id.linearlayout_factory_otheroption_upgrade6m30:
                        if (DialogType == 1) {
                            openAndriodDialog6M30(CheckUsbIsExist());
                        } else {
                            openLayoutDialog6M30();
                        }
                        break;
                    case R.id.linearlayout_factory_otheroption_upgradedualursa:
                        if (DialogType == 1) {
                            openAndriodDialogDualUrsa(CheckUsbIsExist());
                        } else {
                            openLayoutDialogDualUrsa();
                        }
                        break;
                    case R.id.linearlayout_factory_otheroption_xvycc:
                        rgbwxyInputIdx = 0;
                        inputString = "";
                        showXvyccInput(rgbwxyInputIdx);
                        break;
                    case R.id.linearlayout_factory_otheroption_factoryprogrampresetfunction:
                        Intent intent = new Intent();
                        intent.setAction(FactoryIntent.ACTION_FACTORY_PROGRAM_PRESET);
                        otheroptionActivity.startActivity(intent);
                        break;
                    default:
                        break;
                }
                break;
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_MENU:
                text_factory_otheroption_pq_updagte_state.setText("Please Click!");
                return false;
            default:
                bRet = false;
                break;
        }
        return bRet;
    }

    public void updatePqFile() {
        int i = 0;
        File srcFile;
        File destFile;
        boolean ret = false;
        stm = MStorageManager.getInstance(otheroptionActivity);
        String destPath = new String();
        String[] volumes = stm.getVolumePaths();
        String usbPath = new String();
        if (volumes == null) {
            return;
        }
        /* Find the first available mounted disk*/
        boolean flag = false;
        for (i = 0; i < volumes.length; ++i) {
            String state = stm.getVolumeState(volumes[i]);
            if (state == null || !state.equals(Environment.MEDIA_MOUNTED)) {
                continue;
            }
            text_factory_otheroption_pq_updagte_state.setText("OK!!!");
            /* Copy all pq update files to destPath*/
            File file = new File(volumes[i] + "/pqbin");
            if (file != null && file.exists()) {
                usbPath = volumes[i];
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_DLC_FILE);
                destPath = "/Customer/DLC/DLC.ini";
                Log.i("OtherOption", "....1....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/DLC.ini");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_COLOR_MATRiX_FILE);
                destPath = "/Customer/ColorMatrix/ColorMatrix.ini";
                Log.i("OtherOption", "....2....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/ColorMatrix.ini");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_BANDWIDTH_REG_TABLE_FILE);
                destPath = "/Customer/pq/Bandwidth_RegTable.bin";
                Log.i("OtherOption", "....3....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Bandwidth_RegTable.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_MAIN_FILE);
                destPath = "/Customer/pq/Main.bin";
                Log.i("OtherOption", "....4....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Main.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_MAIN_TEXT_FILE);
                destPath = "/Customer/pq/Main_Text.bin";
                Log.i("OtherOption", "....5....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Main_Text.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_MAIN_EX_FILE);
                destPath = "/Customer/pq/Main_Ex.bin";
                Log.i("OtherOption", "....6....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Main_Ex.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_MAIN_EX_TEXT_FILE);
                destPath = "/Customer/pq/Main_Ex_Text.bin";
                Log.i("OtherOption", "....7....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Main_Ex_Text.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_MAIN_EX_TEXT_FILE);
                destPath = "/Customer/pq/Main_Color.bin";
                Log.i("OtherOption", "....8....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Main_Color.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_MAIN_EX_TEXT_FILE);
                destPath = "/Customer/pq/Main_Color_Text.bin";
                Log.i("OtherOption", "....9....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Main_Color_Text.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_MSRV_PQ_SUB_FILE);
                destPath = "/Customer/pq/Sub.bin";
                Log.i("OtherOption", "....10....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Sub.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_SUB_TEXT_FILE);
                destPath = "/Customer/pq/Sub_Text.bin";
                Log.i("OtherOption", "....11....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Sub_Text.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_SUB_EX_FILE);
                destPath = "/Customer/pq/Sub_Ex.bin";
                Log.i("OtherOption", "....12....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Sub_Ex.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_PQ_SUB_EX_TEXT_FILE);
                destPath = "/Customer/pq/Sub_Ex_Text.bin";
                Log.i("OtherOption", "....13....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Sub_Ex_Text.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                destPath = "/Customer/pq/Sub_Color.bin";
                Log.i("OtherOption", "....14....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Sub_Color.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                destPath = "/Customer/pq/Sub_Color_Text.bin";
                Log.i("OtherOption", "....15....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/Sub_Color_Text.bin");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // destPath =
                // factoryManager.getUpdatePqFilePath(EnumPqUpdateFile.E_GAMMA0_FILE);
                destPath = "/Customer/pq/gamma0.ini";
                Log.i("OtherOption", "....16....destPath is " + destPath + "..........");
                srcFile = new File(usbPath + "/pqbin/gamma0.ini");
                destFile = new File(destPath);
                try {
                    if (copyFile(srcFile, destFile)) {
                        ret = true;
                    }
                    chmodFile(destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                factoryManager.updatePqIniFiles();
                Log.i("OtherOption", ".......updatePqIniFiles OK!!!!!!!!!!..........");
            } else {
                text_factory_otheroption_pq_updagte_state.setText("No File On U-Disk!");
            }
        }
    }

    private boolean copyFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            Log.i("OtherOption", "~src file not exits~");
            return false;
        }
        if (!srcFile.isFile()) {
            Log.i("OtherOption", "~src file is not a file~");
            return false;
        }
        if (!srcFile.canRead()) {
            Log.i("OtherOption", "~src file can  not read~");
            return false;
        }
        if (!destFile.exists()) {
            Log.i("OtherOption", "~dest file not exits~");
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
        }
        if (!destFile.canRead()) {
            Log.i("OtherOption", "~dest file can  not read~");
            return false;
        }
        Log.i("OtherOption", "~src file OK~");
        FileInputStream input = new FileInputStream(srcFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);
        FileOutputStream output = new FileOutputStream(destFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);
        byte[] b = new byte[1024 * 2000];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        outBuff.flush();
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
        // chmodFile(destFile);
        Log.i("OtherOption", "~chmod dest file OK~");
        return true;
    }

    private void chmodFile(File destFile) {
        try {
            String command = "chmod 666 " + destFile.getAbsolutePath();
            Log.i("OtherOptionAdjustViewHolder", "command = " + command);
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(command);
        } catch (IOException e) {
            Log.i("OtherOptionAdjustViewHolder", "chmod fail!!!!");
            e.printStackTrace();
        }
    }

    private void changePVRRecordAll() {
        if (true == TvFactoryManager.getInstance().getPvrRecordAll()) {
            TvFactoryManager.getInstance().setPvrRecordAll(false);
            text_factory_otheroption_pvrrecordall_val.setText(otheroptionActivity
                    .getString(R.string.str_textview_factory_otheroption_text_off));
        } else {
            TvFactoryManager.getInstance().setPvrRecordAll(true);
            text_factory_otheroption_pvrrecordall_val.setText(otheroptionActivity
                    .getString(R.string.str_textview_factory_otheroption_text_on));
        }
    }
}
