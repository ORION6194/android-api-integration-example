package com.sbmg_india.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.smbg_india.R;
import com.sbmg_india.adapter.SelectorListAdapter;
import com.sbmg_india.models.BlockDataModel;
import com.sbmg_india.models.DistrictDataModel;
import com.sbmg_india.models.GPDataModel;
import com.sbmg_india.models.StateDataModel;

import java.util.ArrayList;

/**
 * Created by ISRO on 12/24/2016.
 */

public class SelectorDialog extends Dialog {

    private Activity activity;
    private String selectionFor;
    private String selectionString;
    private SelectorDialogDelegate selectorDialogDelegate;
    private TextView txt_dialog_title;
    private EditText et_selector;
    private ListView lv_selector;
    private TextView txt_no_records;
    private ArrayList<StateDataModel> stateDataModels;
    private ArrayList<DistrictDataModel> districtDataModels;
    private ArrayList<BlockDataModel> blockDataModels;
    private ArrayList<GPDataModel> gpDataModels;
    private SelectorListAdapter selectorListAdapter;
    private SelectorDialog selectorDialog;
    public SelectorDialog(Activity activity, SelectorDialogDelegate selectorDialogDelegate, String selectionFor,
                          ArrayList<StateDataModel> stateDataModels,
                          ArrayList<DistrictDataModel> districtDataModels,
                          ArrayList<BlockDataModel> blockDataModels,
                          ArrayList<GPDataModel> gpDataModels, String selectedString) {
        super(activity);
        this.activity = activity;
        selectorDialog = this;
        this.selectorDialogDelegate = selectorDialogDelegate;
        this.selectionFor = selectionFor;
        this.stateDataModels = stateDataModels;
        this.districtDataModels = districtDataModels;
        this.blockDataModels = blockDataModels;
        this.gpDataModels = gpDataModels;
        this.selectionString = selectedString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setContentView(R.layout.dialog_selector);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initUI();
    }

    private void initUI() {
        txt_dialog_title = (TextView) selectorDialog.findViewById(R.id.txt_dialog_title);
        et_selector = (EditText) selectorDialog.findViewById(R.id.et_selector);
        lv_selector = (ListView) selectorDialog.findViewById(R.id.lv_selector);
        txt_no_records = (TextView) selectorDialog.findViewById(R.id.txt_no_records);
        txt_dialog_title.setText("Select "+ selectionFor);
        setListener();
        setAdapter();
    }

    private void setAdapter() {
        selectorListAdapter = new SelectorListAdapter(activity,selectionFor,selectionString,stateDataModels,districtDataModels,blockDataModels,gpDataModels);
        lv_selector.setAdapter(selectorListAdapter);
    }

    private void setListener() {
        et_selector.addTextChangedListener(new SearchTextWatcher());
        lv_selector.setOnItemClickListener(new SelectorItemClickListener());
    }

    public interface SelectorDialogDelegate{
        void setSelected(String Selector, int id, String nextSelectionFor);
    }

    private class SearchTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(selectionFor.equalsIgnoreCase("state")) {
                String constraint = s.toString();
                ArrayList<StateDataModel> nStateDataModels = null;
                if (constraint == null || constraint.length() == 0) {
                    nStateDataModels = stateDataModels;
                }else{
                    nStateDataModels = new ArrayList<>();
                    for(StateDataModel stateDataModel: stateDataModels){
                        if(stateDataModel.getState_name().toLowerCase().contains(constraint.toString().toLowerCase())){
                            nStateDataModels.add(stateDataModel);
                        }
                    }
                }
                stateDataModels.clear();
                stateDataModels.addAll(nStateDataModels);
                selectorListAdapter.notifyDataSetChanged();
            }
            else if(selectionFor.equalsIgnoreCase("district")) {
                String constraint = s.toString();
                ArrayList<DistrictDataModel> nStateDataModels = null;
                if (constraint == null || constraint.length() == 0) {
                    nStateDataModels = districtDataModels;
                }else{
                    nStateDataModels = new ArrayList<>();
                    for(DistrictDataModel stateDataModel: districtDataModels){
                        if(stateDataModel.getDistrict_name().toLowerCase().contains(constraint.toString().toLowerCase())){
                            nStateDataModels.add(stateDataModel);
                        }
                    }
                }
                districtDataModels.clear();
                districtDataModels.addAll(nStateDataModels);
                selectorListAdapter.notifyDataSetChanged();
            }
            else if(selectionFor.equalsIgnoreCase("block")) {

                String constraint = s.toString();
                ArrayList<BlockDataModel> nStateDataModels = null;
                if (constraint == null || constraint.length() == 0) {
                    nStateDataModels = blockDataModels;
                }else{
                    nStateDataModels = new ArrayList<>();
                    for(BlockDataModel stateDataModel: blockDataModels){
                        if(stateDataModel.getBlock_name().toLowerCase().contains(constraint.toString().toLowerCase())){
                            nStateDataModels.add(stateDataModel);
                        }
                    }
                }
                blockDataModels.clear();
                blockDataModels.addAll(nStateDataModels);
                selectorListAdapter.notifyDataSetChanged();
            }
            else if(selectionFor.equalsIgnoreCase("gp")){

                String constraint = s.toString();
                ArrayList<GPDataModel> nStateDataModels = null;
                if (constraint == null || constraint.length() == 0) {
                    nStateDataModels = gpDataModels;
                }else{
                    nStateDataModels = new ArrayList<>();
                    for(GPDataModel stateDataModel: gpDataModels){
                        if(stateDataModel.getGp_name().toLowerCase().contains(constraint.toString().toLowerCase())){
                            nStateDataModels.add(stateDataModel);
                        }
                    }
                }
                gpDataModels.clear();
                gpDataModels.addAll(nStateDataModels);
                selectorListAdapter.notifyDataSetChanged();
            }
        }
    }

    private class SelectorItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(selectionFor.equalsIgnoreCase("state")) {
                selectorDialogDelegate.setSelected(stateDataModels.get(position).getState_name(),stateDataModels.get(position).getId(),"District");
            }
            else if(selectionFor.equalsIgnoreCase("district")) {
                selectorDialogDelegate.setSelected(districtDataModels.get(position).getDistrict_name(),districtDataModels.get(position).getId(),"Block");
            }
            else if(selectionFor.equalsIgnoreCase("block")) {
                selectorDialogDelegate.setSelected(blockDataModels.get(position).getBlock_name(),blockDataModels.get(position).getId(),"GP");
            }
            else if(selectionFor.equalsIgnoreCase("gp")) {
                selectorDialogDelegate.setSelected(gpDataModels.get(position).getGp_name(),gpDataModels.get(position).getId(),"GP");
            }
            SelectorDialog.this.dismiss();
        }
    }
}
