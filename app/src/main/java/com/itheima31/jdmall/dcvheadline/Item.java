package com.itheima31.jdmall.dcvheadline;

import android.view.View;

import com.itheima31.jdmall.R;

import java.util.ArrayList;

/**
 * Simple POJO model for example
 */
public class Item {

    private String price;
    private String pledgePrice;
    private String fromAddress;
    private String toAddress;
    private int requestsCount;
    private String date;
    private String time;
    private int  image;
    private  int image02;

    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }

    public Item(int image,int image02,String price, String pledgePrice, String fromAddress, String toAddress, int requestsCount, String date, String time) {
        this.price = price;
        this.pledgePrice = pledgePrice;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.requestsCount = requestsCount;
        this.date = date;
        this.time = time;
        this.image = image;
        this.image02= image02;
    }

    public String getPrice() {
        return price;
    }
    public int  getImage() {
        return image;
    }public int  getImage02() {
        return image02;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPledgePrice() {
        return pledgePrice;
    }

    public void setPledgePrice(String pledgePrice) {
        this.pledgePrice = pledgePrice;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public void setRequestsCount(int requestsCount) {
        this.requestsCount = requestsCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (requestsCount != item.requestsCount) return false;
        if (image != item.image) return false;
        if (image02 != item.image02) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (pledgePrice != null ? !pledgePrice.equals(item.pledgePrice) : item.pledgePrice != null)
            return false;
        if (fromAddress != null ? !fromAddress.equals(item.fromAddress) : item.fromAddress != null)
            return false;
        if (toAddress != null ? !toAddress.equals(item.toAddress) : item.toAddress != null)
            return false;
        if (date != null ? !date.equals(item.date) : item.date != null) return false;
        return !(time != null ? !time.equals(item.time) : item.time != null);

    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (pledgePrice != null ? pledgePrice.hashCode() : 0);
        result = 31 * result + (fromAddress != null ? fromAddress.hashCode() : 0);
        result = 31 * result + (toAddress != null ? toAddress.hashCode() : 0);
        result = 31 * result + requestsCount;
        result = 31 * result + image;
        result = 31 * result + image02;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.no1,R.drawable.no1_huawei_brand,"No1", "¥270", "品牌名☆华为荣耀8", "上榜理由☆很好很强大", 300300,  "TODAY", "05:10 PM"));
        items.add(new Item(R.drawable.no2,R.drawable.no1_huawei_brand,"No2", "¥116", "品牌名☆华为3c", "上榜理由☆没用过的人也说好", 10000, "TODAY", "11:10 AM"));
        items.add(new Item(R.drawable.no3_lenovoideapad310,R.drawable.no3_lianxiang_brand,"No3", "¥350", "品牌名☆联想IdeaPad310", "上榜理由☆好薄,好快",24440, "TODAY", "07:11 PM"));
        items.add(new Item(R.drawable.no4_beitaxie,R.drawable.no4_beitaxie_brand,"No4", "¥150", "品牌名☆beita鞋", "上榜理由☆非一般的感觉", 89999, "TODAY", "4:15 AM"));
        items.add(new Item(R.drawable.no5_qiaodanxie,R.drawable.no5_qiaodan_brand,"No5", "¥300", "品牌名☆乔丹鞋", "上榜理由☆火箭人一样的感觉", 40222, "TODAY", "06:15 PM"));

        items.add(new Item(R.drawable.no1,R.drawable.no1_huawei_brand,"No6", "¥221", "品牌名☆华为荣耀8", "上榜理由☆很好很强大", 300300, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no2,R.drawable.no1_huawei_brand,"No7", "¥300", "品牌名☆华为3c", "上榜理由☆没用过的人也说好", 10000, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no3_lenovoideapad310,R.drawable.no3_lianxiang_brand,"No8", "¥210", "品牌名☆联想IdeaPad310", "上榜理由☆好薄,好快", 89999, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no4_beitaxie,R.drawable.no4_beitaxie_brand,"No9", "¥330",  "品牌名☆beita鞋", "上榜理由☆非一般的感觉", 89999, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no5_qiaodanxie,R.drawable.no5_qiaodan_brand,"No10", "¥300","品牌名☆乔丹鞋", "上榜理由☆火箭人一样的感觉", 10000, "TODAY", "06:15 PM"));

        items.add(new Item(R.drawable.no1,R.drawable.no1_huawei_brand,"No11", "¥221","品牌名☆华为荣耀8", "上榜理由☆很好很强大", 89999, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no2,R.drawable.no1_huawei_brand,"No12", "¥300","品牌名☆华为3c", "上榜理由☆没用过的人也说好", 9980, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no3_lenovoideapad310,R.drawable.no3_lianxiang_brand,"No13", "¥210","品牌名☆联想IdeaPad310", "上榜理由☆好薄,好快", 6077, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no4_beitaxie,R.drawable.no4_beitaxie_brand,"No14", "¥330","品牌名☆beita鞋", "上榜理由☆非一般的感觉", 34550, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no5_qiaodanxie,R.drawable.no5_qiaodan_brand,"No15", "¥350","品牌名☆乔丹鞋", "上榜理由☆火箭人一样的感觉", 22330, "TODAY", "06:15 PM"));

        items.add(new Item(R.drawable.no1,R.drawable.no1_huawei_brand,"No16", "¥150","品牌名☆华为荣耀8", "上榜理由☆很好很强大", 4520, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no2,R.drawable.no1_huawei_brand,"No17", "¥300","品牌名☆华为3c", "上榜理由☆没用过的人也说好", 4320, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no3_lenovoideapad310,R.drawable.no3_lianxiang_brand,"No18", "¥221","品牌名☆联想IdeaPad310", "上榜理由☆好薄,好快", 4011, "TODAY", "06:15 PM"));
        items.add(new Item(R.drawable.no4_beitaxie,R.drawable.no4_beitaxie_brand,"No19", "¥300","品牌名☆beita鞋", "上榜理由☆非一般的感觉",67750, "TODAY", "06:15 PM"));
        return items;

    }

}
