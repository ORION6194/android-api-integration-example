package com.sbmg_india.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ISRO on 12/25/2016.
 */

public class StateDataModel implements Parcelable{
    private int id;
    private String state_name;
    private int actl_gp_offline;
    private int total_hh;
    private int num_hh_wt_toilets;
    private int num_hh_wo_toilets;
    private int coverage_wt_toilet;
    private int coverage_wo_toilet;
    private int aip_target;
    private int aip_achievement;
    private int total_gp_become_odf;
    private int total_gp_declare_odf;
    private int gp_included_aip_year;
    private int gp_declared_aip_year;
    private long created_at;
    private long updated_at;

    public StateDataModel() {
    }

    protected StateDataModel(Parcel in) {
        id = in.readInt();
        state_name = in.readString();
        actl_gp_offline = in.readInt();
        total_hh = in.readInt();
        num_hh_wt_toilets = in.readInt();
        num_hh_wo_toilets = in.readInt();
        coverage_wt_toilet = in.readInt();
        coverage_wo_toilet = in.readInt();
        aip_target = in.readInt();
        aip_achievement = in.readInt();
        total_gp_become_odf = in.readInt();
        total_gp_declare_odf = in.readInt();
        gp_included_aip_year = in.readInt();
        gp_declared_aip_year = in.readInt();
        created_at = in.readLong();
        updated_at = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(state_name);
        dest.writeInt(actl_gp_offline);
        dest.writeInt(total_hh);
        dest.writeInt(num_hh_wt_toilets);
        dest.writeInt(num_hh_wo_toilets);
        dest.writeInt(coverage_wt_toilet);
        dest.writeInt(coverage_wo_toilet);
        dest.writeInt(aip_target);
        dest.writeInt(aip_achievement);
        dest.writeInt(total_gp_become_odf);
        dest.writeInt(total_gp_declare_odf);
        dest.writeInt(gp_included_aip_year);
        dest.writeInt(gp_declared_aip_year);
        dest.writeLong(created_at);
        dest.writeLong(updated_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StateDataModel> CREATOR = new Creator<StateDataModel>() {
        @Override
        public StateDataModel createFromParcel(Parcel in) {
            return new StateDataModel(in);
        }

        @Override
        public StateDataModel[] newArray(int size) {
            return new StateDataModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
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

    public int getAip_target() {
        return aip_target;
    }

    public void setAip_target(int aip_target) {
        this.aip_target = aip_target;
    }

    public int getAip_achievement() {
        return aip_achievement;
    }

    public void setAip_achievement(int aip_achievement) {
        this.aip_achievement = aip_achievement;
    }

    public int getTotal_gp_become_odf() {
        return total_gp_become_odf;
    }

    public void setTotal_gp_become_odf(int total_gp_become_odf) {
        this.total_gp_become_odf = total_gp_become_odf;
    }

    public int getTotal_gp_declare_odf() {
        return total_gp_declare_odf;
    }

    public void setTotal_gp_declare_odf(int total_gp_declare_odf) {
        this.total_gp_declare_odf = total_gp_declare_odf;
    }

    public int getGp_included_aip_year() {
        return gp_included_aip_year;
    }

    public void setGp_included_aip_year(int gp_included_aip_year) {
        this.gp_included_aip_year = gp_included_aip_year;
    }

    public int getGp_declared_aip_year() {
        return gp_declared_aip_year;
    }

    public void setGp_declared_aip_year(int gp_declared_aip_year) {
        this.gp_declared_aip_year = gp_declared_aip_year;
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
