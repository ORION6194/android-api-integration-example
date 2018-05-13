package com.sbmg_india.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smbg_india.R;
import com.sbmg_india.models.BlockDataModel;
import com.sbmg_india.models.DistrictDataModel;
import com.sbmg_india.models.GPDataModel;
import com.sbmg_india.models.StateDataModel;

import java.util.ArrayList;

/**
 * Created by ISRO on 12/25/2016.
 */

public class SelectorListAdapter extends BaseAdapter {
    private Activity activity;
    private final LayoutInflater inflater;
    private ArrayList<StateDataModel> stateDataModels;
    private ArrayList<DistrictDataModel> districtDataModels;
    private ArrayList<BlockDataModel> blockDataModels;
    private ArrayList<GPDataModel> gpDataModels;
    private String selectionFor;
    private String selectedString;

    public SelectorListAdapter(Activity activity, String selectionFor, String selectedString,
                               ArrayList<StateDataModel> stateDataModels,
                               ArrayList<DistrictDataModel> districtDataModels,
                               ArrayList<BlockDataModel> blockDataModels,
                               ArrayList<GPDataModel> gpDataModels){
        this.activity = activity;
        this.inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.selectionFor = selectionFor;
        this.stateDataModels = stateDataModels;
        this.districtDataModels = districtDataModels;
        this.blockDataModels = blockDataModels;
        this.gpDataModels = gpDataModels;
        this.selectedString = selectedString;
    }

    @Override
    public int getCount() {
        if(selectionFor.equalsIgnoreCase("state"))
            return stateDataModels.size();
        else if(selectionFor.equalsIgnoreCase("district"))
            return districtDataModels.size();
        else if(selectionFor.equalsIgnoreCase("block"))
            return blockDataModels.size();
        else if(selectionFor.equalsIgnoreCase("gp"))
            return gpDataModels.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {

        if(selectionFor.equalsIgnoreCase("state"))
            return stateDataModels.get(position);
        else if(selectionFor.equalsIgnoreCase("district"))
            return districtDataModels.get(position);
        else if(selectionFor.equalsIgnoreCase("block"))
            return blockDataModels.get(position);
        else if(selectionFor.equalsIgnoreCase("gp"))
            return gpDataModels.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_name_image,
                    parent, false);

            holder.txtStateName = (TextView) convertView.findViewById(R.id.txt_name);
            holder.imgSelector = (ImageView) convertView.findViewById(R.id.img_tick);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setData(holder, position);
        return convertView;

    }

    private void setData(ViewHolder viewHolder, int position) {
        if(selectionFor.equalsIgnoreCase("state")) {
            viewHolder.txtStateName.setText(stateDataModels.get(position).getState_name());
            if (stateDataModels.get(position).getState_name().equalsIgnoreCase(selectedString)){
                viewHolder.imgSelector.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imgSelector.setVisibility(View.INVISIBLE);
            }
        }
        else if(selectionFor.equalsIgnoreCase("district")) {

            viewHolder.txtStateName.setText(districtDataModels.get(position).getDistrict_name());
            if (districtDataModels.get(position).getDistrict_name().equalsIgnoreCase(selectedString)){
                viewHolder.imgSelector.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imgSelector.setVisibility(View.INVISIBLE);
            }
        }
        else if(selectionFor.equalsIgnoreCase("block")) {

            viewHolder.txtStateName.setText(blockDataModels.get(position).getBlock_name());
            if (blockDataModels.get(position).getBlock_name().equalsIgnoreCase(selectedString)){
                viewHolder.imgSelector.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imgSelector.setVisibility(View.INVISIBLE);
            }
        }
        else if(selectionFor.equalsIgnoreCase("gp")){

            viewHolder.txtStateName.setText(gpDataModels.get(position).getGp_name());
            if (gpDataModels.get(position).getGp_name().equalsIgnoreCase(selectedString)){
                viewHolder.imgSelector.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imgSelector.setVisibility(View.INVISIBLE);
            }
        }
    }

    public class ViewHolder {
        TextView txtStateName;
        ImageView imgSelector;
    }

}

