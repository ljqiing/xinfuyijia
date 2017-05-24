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

import mstar.factorymenu.ui.R;
import mstar.tvsetting.factory.desk.FactoryDeskImpl;
import mstar.tvsetting.factory.desk.IFactoryDesk;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mstar.android.tvapi.common.exception.TvCommonException;
import com.mstar.android.tvapi.dtv.dvb.dvbc.DtvDemodDvbcInfo;
import com.mstar.android.tvapi.dtv.dvb.dvbt.DtvDemodDvbtInfo;

public class DialogInfo extends Dialog {

    private static DesignMenuActivity dialogmenuactivity;

    private int parentItemId;

    private final static int TOTAL_INFO_LEN = 18;

    private final static int DVBT_INFO_LEN = 18;

    private final static int DVBC_INFO_LEN = 14;

    private final int MSG_SET_DVBT_VIEW = 0;

    private final int MSG_SET_DVBC_VIEW = 1;

    private final int MSG_SET_DATA_FAILED_VIEW = 2;

    private final int MSG_SHOW_DIALOG = 3;

    private final int MSG_DISMISS_DIALOG = 4;

    private final int TYPE_DVBC = 1;

    private final int TYPE_DVBT = 2;

    private static TextView[] textviewInfoArray = new TextView[TOTAL_INFO_LEN];

    private static TextView textViewTitle = null;

    private String[] dvbtStrArray;

    private String[] dvbcstrArray;

    private ProgressDialog progressDialog = null;

    public DialogInfo(Context context, int theme) {
        super(context, theme);
        parentItemId = theme;
        dialogmenuactivity = (DesignMenuActivity) context;
    }

    private void initResource() {

        Log.e("ljh", "here ...................");

        // TODO Auto-generated method stub
        textviewInfoArray[0] = (TextView) findViewById(R.id.textview_dvbt_dvbc_0);
        textviewInfoArray[1] = (TextView) findViewById(R.id.textview_dvbt_dvbc_1);
        textviewInfoArray[2] = (TextView) findViewById(R.id.textview_dvbt_dvbc_2);
        textviewInfoArray[3] = (TextView) findViewById(R.id.textview_dvbt_dvbc_3);
        textviewInfoArray[4] = (TextView) findViewById(R.id.textview_dvbt_dvbc_4);
        textviewInfoArray[5] = (TextView) findViewById(R.id.textview_dvbt_dvbc_5);
        textviewInfoArray[6] = (TextView) findViewById(R.id.textview_dvbt_dvbc_6);
        textviewInfoArray[7] = (TextView) findViewById(R.id.textview_dvbt_dvbc_7);
        textviewInfoArray[8] = (TextView) findViewById(R.id.textview_dvbt_dvbc_8);
        textviewInfoArray[9] = (TextView) findViewById(R.id.textview_dvbt_dvbc_9);
        textviewInfoArray[10] = (TextView) findViewById(R.id.textview_dvbt_dvbc_10);
        textviewInfoArray[11] = (TextView) findViewById(R.id.textview_dvbt_dvbc_11);
        textviewInfoArray[12] = (TextView) findViewById(R.id.textview_dvbt_dvbc_12);
        textviewInfoArray[13] = (TextView) findViewById(R.id.textview_dvbt_dvbc_13);
        textviewInfoArray[14] = (TextView) findViewById(R.id.textview_dvbt_dvbc_14);
        textviewInfoArray[15] = (TextView) findViewById(R.id.textview_dvbt_dvbc_15);
        textviewInfoArray[16] = (TextView) findViewById(R.id.textview_dvbt_dvbc_16);
        textviewInfoArray[17] = (TextView) findViewById(R.id.textview_dvbt_dvbc_17);

        textViewTitle = (TextView) findViewById(R.id.textview_factory_dialoginfo_title);

        dvbtStrArray = dialogmenuactivity.getResources()
                .getStringArray(R.array.str_dvbt_info_array);

        dvbcstrArray = dialogmenuactivity.getResources()
                .getStringArray(R.array.str_dvbc_info_array);

    }

    // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogInfo.this.setContentView(R.layout.dvbtdvbcinfo);
        initProgressDialog();
        initResource();
        judgeTypeThread.start();
    }

    private IFactoryDesk factoryDesk = FactoryDeskImpl.getInstance(dialogmenuactivity);

    private DtvDemodDvbcInfo dvbcInfo = null;

    private DtvDemodDvbtInfo dvbtInfo = null;

    Thread judgeTypeThread = new Thread() {
        public void run() {
            getHandler().sendEmptyMessage(MSG_SHOW_DIALOG);

            int type = factoryDesk.getCurrentDtvRoute();

            try {
                if (type == TYPE_DVBC) {
                    dvbcInfo = factoryDesk.getDemodDVBCInfo();
                    getHandler().sendEmptyMessage(MSG_SET_DVBC_VIEW);
                } else if (type == TYPE_DVBT) {
                    dvbtInfo = factoryDesk.getDemodDVBTInfo();
                    getHandler().sendEmptyMessage(MSG_SET_DVBT_VIEW);
                } else {
                    getHandler().sendEmptyMessage(MSG_SET_DATA_FAILED_VIEW);
                }

            } catch (TvCommonException e) {
                e.printStackTrace();
            }
            getHandler().sendEmptyMessage(MSG_DISMISS_DIALOG);
        };
    };

    public Handler getHandler() {
        return handler;
    }

    private void initDvbcView() {

        textViewTitle.setText(dialogmenuactivity
                .getString(R.string.str_textview_factory_otheroption_dvbc_info));
        if (dvbcInfo == null) {
            Toast.makeText(getContext(), "No valid info!", Toast.LENGTH_SHORT).show();
            dismiss();
            return;
        }

        String[] infos = {
                "" + dvbcInfo.u16Version, "" + dvbcInfo.u16SymbolRate, "" + dvbcInfo.eQamMode,
                "" + dvbcInfo.u32IFFreq, "" + dvbcInfo.bSpecInv, "" + dvbcInfo.bSerialTS,
                "" + dvbcInfo.u8SarValue, "" + dvbcInfo.u32ChkScanTimeStart,
                "" + dvbcInfo.eLockStatus, "" + dvbcInfo.u16Strength, "" + dvbcInfo.u16Quality,
                "" + dvbcInfo.u32Intp, "" + dvbcInfo.u32FcFs, "" + dvbcInfo.u16SymbolRateHal
        };

        for (int i = 0; i < DVBC_INFO_LEN; i++) {
            String text = "";
            if (infos[i] != null && !"".equals(infos[i])) {
                text = String.format(dvbcstrArray[i], infos[i]);
            } else {
                text = String.format(dvbcstrArray[i], "");
            }
            textviewInfoArray[i].setText(text);
        }
    }

    private void initDvbtView() {

        textViewTitle.setText(dialogmenuactivity
                .getString(R.string.str_textview_factory_otheroption_dvbt_info));

        String[] infos = {
                "" + dvbtInfo.u16Version, "" + dvbtInfo.u16DemodState, "" + dvbtInfo.SfoValue,
                "" + dvbtInfo.TotalCfo, "" + dvbtInfo.u16ChannelLength, "" + dvbtInfo.u8Fft,
                "" + dvbtInfo.u8Constel, "" + dvbtInfo.u8Gi, "" + dvbtInfo.u8HpCr,
                "" + dvbtInfo.u8LpCr, "" + dvbtInfo.u8Hiearchy, "" + dvbtInfo.u8Fd,
                "" + dvbtInfo.u8ChLen, "" + dvbtInfo.u8SnrSel, "" + dvbtInfo.u8PertoneNum,
                "" + dvbtInfo.u8DigAci, "" + dvbtInfo.u8FlagCi, "" + dvbtInfo.u8TdCoef
        };

        for (int i = 0; i < DVBT_INFO_LEN; i++) {
            String text = "";
            if (infos[i] != null && !"".equals(infos[i])) {
                text = String.format(dvbtStrArray[i], infos[i]);
            } else {
                text = String.format(dvbtStrArray[i], "");
            }
            textviewInfoArray[i].setText(text);
        }

    };

    private void initEmptyView() {
        // TODO Auto-generated method stub
        textViewTitle.setText(dialogmenuactivity
                .getString(R.string.str_textview_factory_otheroption_dvbc_info_empty));
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case MSG_SET_DVBT_VIEW:
                    initDvbtView();
                    break;

                case MSG_SET_DVBC_VIEW:
                    initDvbcView();
                    break;

                case MSG_SET_DATA_FAILED_VIEW:
                    initEmptyView();
                    break;

                case MSG_SHOW_DIALOG:
                    if (progressDialog != null) {
                        progressDialog.show();
                    }
                    break;

                case MSG_DISMISS_DIALOG:
                    if (progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    break;

                default:
                    break;
            }
        }

    };

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(dialogmenuactivity);
            progressDialog.setMessage(dialogmenuactivity
                    .getString(R.string.str_textview_factory_otheroption_waiting_val));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
        }
    }

}
