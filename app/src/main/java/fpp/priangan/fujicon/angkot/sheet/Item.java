package fpp.priangan.fujicon.angkot.sheet;



public class Item {

    private int mDrawableRes;

    private String mTitle;

    public Item(int drawable, String title) {
        mDrawableRes = drawable;
        mTitle = title;
    }

    public int getDrawableResource() {
        return mDrawableRes;
    }

    public String getTitle() {
        return mTitle;
    }

}
