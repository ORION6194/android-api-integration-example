package com.sbmg_india.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ISRO on 12/25/2016.
 */

public class GPDataModel implements Parcelable{
    private int id;
    private int block_id;
    private String gp_name;
    private int actl_gp_offline;
    private int total_hh;
    private int num_hh_wt_toilets;
    private int num_hh_wo_toilets;
    private int coverage_wt_toilet;
    private int coverage_wo_toilet;
    private String declared_odf;
    private long created_at;
    private long updated_at;

    public GPDataModel() {
    }

    protected GPDataModel(Parcel in) {
        id = in.readInt();
        block_id = in.readInt();
        gp_name = in.readString();
        actl_gp_offline = in.readInt();
        total_hh = in.readInt();
        num_hh_wt_toilets = in.readInt();
        num_hh_wo_toilets = in.readInt();
        coverage_wt_toilet = in.readInt();
        coverage_wo_toilet = in.readInt();
        declared_odf = in.readString();
        created_at = in.readLong();
        updated_at = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(block_id);
        dest.writeString(gp_name);
        dest.writeInt(actl_gp_offline);
        dest.writeInt(total_hh);
        dest.writeInt(num_hh_wt_toilets);
        dest.writeInt(num_hh_wo_toilets);
        dest.writeInt(coverage_wt_toilet);
        dest.writeInt(coverage_wo_toilet);
        dest.writeString(declared_odf);
        dest.writeLong(created_at);
        dest.writeLong(updated_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GPDataModel> CREATOR = new Creator<GPDataModel>() {
        @Override
        public GPDataModel createFromParcel(Parcel in) {
            return new GPDataModel(in);
        }

        @Override
        public GPDataModel[] newArray(int size) {
            return new GPDataModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public String getGp_name() {
        return gp_name;
    }

    public void setGp_name(String gp_name) {
        this.gp_name = gp_name;
    }

    public int getActl_gp_offline() {
        return actl_gp_offline;
    }

    public void setActl_gp_offline(int actl_gp_offline) {
        this.actl_gp_offline = actl_gp_offline;
    }

    public int getTotal_hh() {
        return total_hh;
    }

    public void setTotal_hh(int total_hh) {
        this.total_hh = total_hh;
    }

    public int getNum_hh_wt_toilets() {
        return num_hh_wt_toilets;
    }

    public void setNum_hh_wt_toilets(int num_hh_wt_toilets) {
        this.num_hh_wt_toilets = num_hh_wt_toilets;
    }

    public int getNum_hh_wo_toilets() {
        return num_hh_wo_toilets;
    }

    public void setNum_hh_wo_toilets(int num_hh_wo_toilets) {
        this.num_hh_wo_toilets = num_hh_wo_toilets;
    }

    public int getCoverage_wt_toilet() {
        return coverage_wt_toilet;
    }

    public void setCoverage_wt_toilet(int coverage_wt_toilet) {
        this.coverage_wt_toilet = coverage_wt_toilet;
    }

    public int getCoverage_wo_toilet() {
        return coverage_wo_toilet;
    }

    public void setCoverage_wo_toilet(int coverage_wo_toilet) {
        this.coverage_wo_toilet = coverage_wo_toilet;
    }

    public String getDeclared_odf() {
        return declared_odf;
    }

    public void setDeclared_odf(String declared_odf) {
        this.declared_odf = declared_odf;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }
}
