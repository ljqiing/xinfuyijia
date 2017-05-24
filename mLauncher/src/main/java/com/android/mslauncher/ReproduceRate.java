
package com.android.mslauncher;

import com.mstar.android.tv.TvPictureManager;

public class ReproduceRate {
    public reduceRate _reduceRate;

    public int scaleval;

    public Orientation direction;

    private TvPictureManager mTvPictureManager;

    public class reduceRate {
        public int topRate;

        public int leftRate;

        public int rightRate;

        public int bottomRate;

        public int interleave;

    };

    public enum Orientation {
        REPRODUCE_ADJUST_NONE, REPRODUCE_ADJUST_TOP, REPRODUCE_ADJUST_BOTTOM, REPRODUCE_ADJUST_LEFT, REPRODUCE_ADJUST_RIGHT, REPRODUCE_ADJUST_ALL,
    }

    private int hstart;

    private PictureSkin pictureSkin;

    ReproduceRate(int scalevalue) {
        _reduceRate = new reduceRate();
        _reduceRate.topRate = (byte) (0xFF & (scalevalue >> 24));
        _reduceRate.bottomRate = (byte) (0XFF & (scalevalue >> 16));
        _reduceRate.leftRate = (byte) (0xFF & (scalevalue >> 8));
        _reduceRate.rightRate = (byte) (0XFF & (scalevalue));
        scaleval = scalevalue;
        hstart = 192;// default 1080P
        direction = Orientation.REPRODUCE_ADJUST_ALL;
        mTvPictureManager = TvPictureManager.getInstance();
    }

    @SuppressWarnings("unused")
    ReproduceRate(int top, int bottom, int left, int right) {
        _reduceRate = new reduceRate();
        _reduceRate.topRate = top;
        _reduceRate.bottomRate = bottom;
        _reduceRate.leftRate = left;
        _reduceRate.rightRate = right;
        scaleval = _reduceRate.topRate << 24 + _reduceRate.bottomRate << 16 + _reduceRate.leftRate << 8 + _reduceRate.rightRate;
    }

    private void getResolutionInfo(int[] array_wh) {
        int resolution = mTvPictureManager.GetResloution();

        if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_1080P_50.getRetCode()) {
            array_wh[0] = 1920;
            array_wh[1] = 1080;
            hstart = 192;
            _reduceRate.interleave = 0;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_1080P_60.getRetCode()) {
            array_wh[0] = 1920;
            array_wh[1] = 1080;
            hstart = 192;
            _reduceRate.interleave = 0;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_1080I_50.getRetCode()) {
            array_wh[0] = 1920;
            array_wh[1] = 1080;
            hstart = 192;
            _reduceRate.interleave = 1;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_1080I_60.getRetCode()) {
            array_wh[0] = 1920;
            array_wh[1] = 1080;
            hstart = 192;
            _reduceRate.interleave = 1;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_720P_50.getRetCode()) {
            array_wh[0] = 1280;
            array_wh[1] = 720;
            hstart = 260;
            _reduceRate.interleave = 0;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_720P_60.getRetCode()) {
            array_wh[0] = 1280;
            array_wh[1] = 720;
            hstart = 260;
            _reduceRate.interleave = 0;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_576P.getRetCode()) {
            array_wh[0] = 720;
            array_wh[1] = 576;
            hstart = 132;
            _reduceRate.interleave = 0;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_576I.getRetCode()) {
            array_wh[0] = 720;
            array_wh[1] = 576;
            hstart = 132;
            _reduceRate.interleave = 1;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_480P.getRetCode()) {
            array_wh[0] = 720;
            array_wh[1] = 480;
            hstart = 122;
            _reduceRate.interleave = 0;
        } else if (resolution == EN_DISPLAY_RES_TYPE.DISPLAY_DACOUT_480I.getRetCode()) {
            array_wh[0] = 720;
            array_wh[1] = 480;
            hstart = 122;
            _reduceRate.interleave = 1;
        }

    }

    public void setReproduceRate() {
        this.scaleval = (int) (_reduceRate.topRate << 24) + (int) (_reduceRate.bottomRate << 16)
                + (int) (_reduceRate.leftRate << 8) + (int) (_reduceRate.rightRate);
        int hw[] = new int[2];
        getResolutionInfo(hw);
        pictureSkin.setSurfaceResolutionMode(hw[0], hw[1], hstart, _reduceRate.interleave,
                direction.ordinal(), this.scaleval);
    }

}
