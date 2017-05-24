
package mstar.tvsetting.factory.ui.designmenu;

import mstar.tvsetting.factory.ui.designmenu.DialogMenu.EnumUpgradeStatus;

public class USBUpgradeThread {
    static int UPGRATE_END_FAIL = 0;

    static int UPGRATE_END_SUCCESS = 1;

    static int UPGRATE_END_FILE_NOT_FOUND = 2;

    static int UPGRATE_END_SUCCESS_MAIN = 3;

    static int UPGRATE_START = 4;

    public static boolean UpgradeMboot() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (DialogMenu.getHandler() != null) {
                    int upgrate_status;
                    DialogMenu.getHandler().sendEmptyMessage(UPGRATE_START);

                    upgrate_status = DialogMenu.UpgradeMbootFun();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_SUCCESS);
                    } else if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND
                            .ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FILE_NOT_FOUND);
                    } else {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FAIL);
                    }
                }

            }
        }).start();
        return true;
    }

    public static boolean Upgrade6M30() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (DialogMenu.getHandler() != null) {
                    int upgrate_status;
                    DialogMenu.getHandler().sendEmptyMessage(UPGRATE_START);

                    upgrate_status = DialogMenu.Upgrade6M30Fun();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_SUCCESS);
                    } else if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND
                            .ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FILE_NOT_FOUND);
                    } else {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FAIL);
                    }
                }

            }

        }).start();
        return true;
    }

    public static boolean UpgradeDualUrsa() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (DialogMenu.getHandler() != null) {
                    int upgrate_status;
                    DialogMenu.getHandler().sendEmptyMessage(UPGRATE_START);

                    upgrate_status = DialogMenu.UpgradeDualUrsaFun();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_SUCCESS);
                    } else if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND
                            .ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FILE_NOT_FOUND);
                    } else {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FAIL);
                    }
                }

            }

        }).start();
        return true;
    }

    public static boolean UpgradeMain() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (DialogMenu.getHandler() != null) {
                    int upgrate_status;
                    DialogMenu.getHandler().sendEmptyMessage(UPGRATE_START);

                    upgrate_status = DialogMenu.UpgradeMainFun();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_SUCCESS.ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_SUCCESS_MAIN);
                    } else if (upgrate_status == EnumUpgradeStatus.E_UPGRADE_FILE_NOT_FOUND
                            .ordinal()) {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FILE_NOT_FOUND);
                    } else {
                        DialogMenu.getHandler().sendEmptyMessage(UPGRATE_END_FAIL);
                    }
                }

            }

        }).start();
        return true;
    }
}
