package com.sbmg_india.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smbg_india.R;
import com.sbmg_india.dialog.SelectorDialog;
import com.sbmg_india.models.BlockDataModel;
import com.sbmg_india.models.DistrictDataModel;
import com.sbmg_india.models.GPDataModel;
import com.sbmg_india.models.StateDataModel;
import com.sbmg_india.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity {

    private HomeScreenActivity activity;

    private TextView txt_selected;
    private TextView txt_select;
    private TextView txt_num_gp;
    private TextView txt_num_hh;
    private TextView txt_num_hh_wt_toilet;
    private TextView txt_num_hh_wo_toilet;
    private TextView txt_coverage_wt_toilet;
    private TextView txt_num_gp_complete_coverage;
    private TextView txt_num_gp_declared_odf;
    private ImageView img_back;
    private Button btn_num_gp;
    private Button btn_num_hh;
    private Button btn_num_hh_wt_toilet;
    private Button btn_num_hh_wo_toilet;
    private Button btn_coverage_wt_toilet;
    private Button btn_num_gp_complete_coverage;
    private Button btn_num_gp_declared_odf;

    private LinearLayout ll_num_gp_complete_coverage;
    private LinearLayout ll_num_gp;

    private String selectedState, selectedDistrict, selectedBlock, selectedGP;
    private int selectedStateId, selectedDistrictId, selectedBlockId, selectedGPId;

    private ArrayList<StateDataModel> stateDataModels;
    private ArrayList<DistrictDataModel> districtDataModels;
    private ArrayList<BlockDataModel> blockDataModels;
    private ArrayList<GPDataModel> gpDataModels;
    private String selectionFor;
    private String selectedString;

    private StateDataModel stateDataModel;
    private DistrictDataModel districtDataModel;
    private BlockDataModel blockDataModel;
    private GPDataModel gpDataModel;
    private SelectorDialog selectorDialog;
    private String currentSelection;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        activity = this;
        initUI();
        initData();
    }

    private void initData() {
        selectedState = "";
        selectedDistrict = "";
        selectedBlock = "";
        selectedGP = "";
        selectionFor = "State";
        currentSelection = "nothing";
        fetchData();
    }

    private void fetchData() {
        String url = "http://35.161.237.232/sbmg/appservices/fetchData.php";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("state", selectedStateId);
            jsonObject.put("district", selectedDistrictId);
            jsonObject.put("block", selectedBlockId);
            jsonObject.put("gp", selectedGPId);
            if(NetworkUtils.isNetworkAvailable(activity)){
                new FetchDataAsyncTask(jsonObject,url).execute();
            }
            else{
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Please check your network connection!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setListeners() {
        txt_select.setOnClickListener(new TxtSelectOnClickListener());
        img_back.setOnClickListener(new ImgBackOnClickListener());
    }

    private void initUI() {

        txt_selected = (TextView) findViewById(R.id.txt_selected);
        txt_select = (TextView) findViewById(R.id.txt_select);
        txt_num_gp = (TextView) findViewById(R.id.txt_num_gp);
        txt_num_hh = (TextView) findViewById(R.id.txt_num_hh);
        txt_num_hh_wt_toilet = (TextView) findViewById(R.id.txt_num_hh_wt_toilet);
        txt_num_hh_wo_toilet = (TextView) findViewById(R.id.txt_num_hh_wo_toilet);
        txt_coverage_wt_toilet = (TextView) findViewById(R.id.txt_coverage_wt_toilet);
        txt_num_gp_complete_coverage = (TextView) findViewById(R.id.txt_num_gp_complete_coverage);
        txt_num_gp_declared_odf = (TextView) findViewById(R.id.txt_num_gp_declared_odf);

        btn_num_gp = (Button) findViewById(R.id.btn_num_gp);
        btn_num_hh = (Button) findViewById(R.id.btn_num_hh);
        btn_num_hh_wt_toilet = (Button) findViewById(R.id.btn_num_hh_wt_toilet);
        btn_num_hh_wo_toilet = (Button) findViewById(R.id.btn_num_hh_wo_toilet);
        btn_coverage_wt_toilet = (Button) findViewById(R.id.btn_coverage_wt_toilet);
        btn_num_gp_complete_coverage = (Button) findViewById(R.id.btn_num_gp_complete_coverage);
        btn_num_gp_declared_odf = (Button) findViewById(R.id.btn_num_gp_declared_odf);

        ll_num_gp_complete_coverage = (LinearLayout) findViewById(R.id.ll_num_gp_complete_coverage);
        ll_num_gp = (LinearLayout) findViewById(R.id.ll_num_gp);

        img_back = (ImageView) findViewById(R.id.img_back);

    }

    private class TxtSelectOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(selectionFor.equalsIgnoreCase("state"))
                selectedString = selectedState;
            else if(selectionFor.equalsIgnoreCase("district"))
                selectedString = selectedDistrict;
            else if(selectionFor.equalsIgnoreCase("block"))
                selectedString = selectedBlock;
            else if(selectionFor.equalsIgnoreCase("gp"))
                selectedString = selectedGP;

            selectorDialog = new SelectorDialog(activity,new SelectorDialogDelegate(),selectionFor,stateDataModels,districtDataModels,blockDataModels,gpDataModels,selectedString);
            selectorDialog.show();
        }

        private class SelectorDialogDelegate implements SelectorDialog.SelectorDialogDelegate {
            @Override
            public void setSelected(String Selector, int id, String nextSelectionFor) {
                selectedString = Selector;
                if(selectionFor.equalsIgnoreCase("state")) {
                    selectedState = selectedString;
                    selectedStateId = id;
                }
                else if(selectionFor.equalsIgnoreCase("district")) {
                    selectedDistrict = selectedString;
                    selectedDistrictId = id;
                }
                else if(selectionFor.equalsIgnoreCase("block")) {
                    selectedBlock = selectedString;
                    selectedBlockId = id;
                }
                else if(selectionFor.equalsIgnoreCase("gp")) {
                    selectedGP = selectedString;
                    selectedGPId = id;
                }
                selectionFor = nextSelectionFor;
                selectedString = "";
                String txtSelected = "";
                if(!NetworkUtils.isNull(selectedGP))
                    txtSelected = selectedGP;
                if(!NetworkUtils.isNull(selectedBlock))
                    if(!NetworkUtils.isNull(txtSelected))
                        txtSelected =  txtSelected + " - " + selectedBlock;
                    else
                        txtSelected = "" + selectedBlock;
                if(!NetworkUtils.isNull(selectedDistrict))
                    if(!NetworkUtils.isNull(txtSelected))
                        txtSelected =  txtSelected + " - " + selectedDistrict;
                    else
                        txtSelected = "" + selectedDistrict;
                if(!NetworkUtils.isNull(selectedState))
                    if(!NetworkUtils.isNull(txtSelected))
                        txtSelected =  txtSelected+ " - " + selectedState;
                    else
                        txtSelected = "" + selectedState;

                txt_selected.setText(txtSelected);
                txt_select.setText(selectionFor);
                fetchData();
            }
        }
    }

    private class FetchDataAsyncTask extends AsyncTask<Void, Void, String> {
        private JSONObject jsonObject;
        private String url;
        public FetchDataAsyncTask(JSONObject jsonObject, String url) {
            this.jsonObject = jsonObject;
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            return NetworkUtils.HitURL(jsonObject,url);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonResponse = new JSONObject(s);
                if (jsonResponse != null) {
                    if(jsonResponse.getString("statusCode").equalsIgnoreCase("100")){
                        if(jsonResponse.getJSONObject("response").getString("selection").equalsIgnoreCase("state")) {
                            stateDataModel = new StateDataModel();
                            ArrayList<StateDataModel> temp = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("data").toString(), new TypeToken<ArrayList<StateDataModel>>(){}.getType());
                            if(temp.size()>0)
                                stateDataModel = temp.get(0);
                            districtDataModels = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("children").toString(), new TypeToken<ArrayList<DistrictDataModel>>(){}.getType());
                            if(stateDataModel!=null){
                                btn_num_gp.setHint(stateDataModel.getActl_gp_offline()+"");
                                btn_num_hh.setHint(stateDataModel.getTotal_hh()+"");
                                btn_num_hh_wt_toilet.setHint(stateDataModel.getNum_hh_wt_toilets()+"");
                                btn_num_hh_wo_toilet.setHint(stateDataModel.getNum_hh_wo_toilets()+"");
                                btn_coverage_wt_toilet.setHint(stateDataModel.getCoverage_wt_toilet()+"");
                                btn_num_gp_complete_coverage.setHint(stateDataModel.getAip_achievement()+"");
                                btn_num_gp_declared_odf.setHint(stateDataModel.getGp_declared_aip_year()+"");
                                ll_num_gp_complete_coverage.setVisibility(View.VISIBLE);
                                ll_num_gp.setVisibility(View.VISIBLE);
                            }
                        }
                        else if(jsonResponse.getJSONObject("response").getString("selection").equalsIgnoreCase("district")) {
                            districtDataModel = new DistrictDataModel();
                            ArrayList<DistrictDataModel> temp = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("data").toString(), new TypeToken<ArrayList<DistrictDataModel>>(){}.getType());
                            if(temp.size()>0)
                                districtDataModel = temp.get(0);
                            blockDataModels = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("children").toString(), new TypeToken<ArrayList<BlockDataModel>>(){}.getType());
                            if(districtDataModel!=null){
                                btn_num_gp.setHint(districtDataModel.getActl_gp_offline()+"");
                                btn_num_hh.setHint(districtDataModel.getTotal_hh()+"");
                                btn_num_hh_wt_toilet.setHint(districtDataModel.getNum_hh_wt_toilets()+"");
                                btn_num_hh_wo_toilet.setHint(districtDataModel.getNum_hh_wo_toilets()+"");
                                btn_coverage_wt_toilet.setHint(districtDataModel.getCoverage_wt_toilet()+"");
                                btn_num_gp_complete_coverage.setHint(districtDataModel.getAip_achievement()+"");
                                btn_num_gp_declared_odf.setHint(districtDataModel.getGp_declared_aip_year()+"");
                                ll_num_gp_complete_coverage.setVisibility(View.VISIBLE);
                                ll_num_gp.setVisibility(View.VISIBLE);
                            }
                        }
                        else if(jsonResponse.getJSONObject("response").getString("selection").equalsIgnoreCase("block")) {
                            blockDataModel = new BlockDataModel();
                            ArrayList<BlockDataModel> temp = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("data").toString(), new TypeToken<ArrayList<BlockDataModel>>(){}.getType());
                            if(temp.size()>0)
                                blockDataModel = temp.get(0);
                            gpDataModels = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("children").toString(), new TypeToken<ArrayList<GPDataModel>>(){}.getType());
                            if(blockDataModel!=null){
                                btn_num_gp.setHint(blockDataModel.getActl_gp_offline()+"");
                                btn_num_hh.setHint(blockDataModel.getTotal_hh()+"");
                                btn_num_hh_wt_toilet.setHint(blockDataModel.getNum_hh_wt_toilets()+"");
                                btn_num_hh_wo_toilet.setHint(blockDataModel.getNum_hh_wo_toilets()+"");
                                btn_coverage_wt_toilet.setHint(blockDataModel.getCoverage_wt_toilet()+"");
                                btn_num_gp_complete_coverage.setHint(blockDataModel.getAip_achievement()+"");
                                btn_num_gp_declared_odf.setHint(blockDataModel.getGp_declared_aip_year()+"");
                                ll_num_gp_complete_coverage.setVisibility(View.VISIBLE);
                                ll_num_gp.setVisibility(View.VISIBLE);
                            }
                        }
                        else if(jsonResponse.getJSONObject("response").getString("selection").equalsIgnoreCase("gp")){
                            gpDataModel = new GPDataModel();
                            ArrayList<GPDataModel> temp = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("data").toString(), new TypeToken<ArrayList<GPDataModel>>(){}.getType());
                            if(temp.size()>0)
                                gpDataModel = temp.get(0);
                            if(gpDataModel!=null){
                                btn_num_gp.setHint(gpDataModel.getActl_gp_offline()+"");
                                btn_num_hh.setHint(gpDataModel.getTotal_hh()+"");
                                btn_num_hh_wt_toilet.setHint(gpDataModel.getNum_hh_wt_toilets()+"");
                                btn_num_hh_wo_toilet.setHint(gpDataModel.getNum_hh_wo_toilets()+"");
                                btn_coverage_wt_toilet.setHint(gpDataModel.getCoverage_wt_toilet()+"");
                                ll_num_gp_complete_coverage.setVisibility(View.GONE);
                                ll_num_gp.setVisibility(View.GONE);
                                txt_num_gp_declared_odf.setText("Is Gram Panchayat Declared ODF");
                                btn_num_gp_declared_odf.setHint(gpDataModel.getDeclared_odf()+"");
                            }
                        }
                        else if(jsonResponse.getJSONObject("response").getString("selection").equalsIgnoreCase("nothing")){
                            stateDataModels = new Gson().fromJson(jsonResponse.getJSONObject("response").getJSONArray("children").toString(), new TypeToken<ArrayList<StateDataModel>>(){}.getType());
                            if(stateDataModel==null){
                                btn_num_gp.setHint(0+"");
                                btn_num_hh.setHint(0+"");
                                btn_num_hh_wt_toilet.setHint(0+"");
                                btn_num_hh_wo_toilet.setHint(0+"");
                                btn_coverage_wt_toilet.setHint(0+"");
                                btn_num_gp_complete_coverage.setHint(0+"");
                                btn_num_gp_declared_odf.setHint(0+"");
                                ll_num_gp_complete_coverage.setVisibility(View.VISIBLE);
                                ll_num_gp.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage(jsonResponse.getString("statusMessage"))
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            progressDialog.dismiss();
            setListeners();
        }
    }

    private class ImgBackOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imgBackPressed();
        }
    }

    private void imgBackPressed() {
        if(!NetworkUtils.isNull(selectedGP)){
            selectedGPId = 0;
            selectedGP = "";
            gpDataModel = new GPDataModel();
            gpDataModels = new ArrayList<>();
            selectionFor = "Block";
            selectedString = selectedBlock;
            currentSelection = "Block";
        }
        else if(!NetworkUtils.isNull(selectedBlock)){
            selectedBlockId = 0;
            selectedBlock = "";
            blockDataModel = new BlockDataModel();
            blockDataModels = new ArrayList<>();
            selectionFor = "District";
            selectedString = selectedDistrict;
            currentSelection = "District";
        }
        else if(!NetworkUtils.isNull(selectedDistrict)){
            selectedDistrictId = 0;
            selectedDistrict = "";
            districtDataModel = new DistrictDataModel();
            districtDataModels = new ArrayList<>();
            selectionFor = "State";
            selectedString = selectedState;
            currentSelection = "State";
        }
        else if(!NetworkUtils.isNull(selectedState)){
            selectedStateId = 0;
            selectedState = "";
            stateDataModel = new StateDataModel();
            stateDataModels = new ArrayList<>();
            selectionFor = "State";
            selectedString = "";
            currentSelection = "nothing";
        }
        String txtSelected = "";
        if(!NetworkUtils.isNull(selectedGP))
            txtSelected = selectedGP;
        if(!NetworkUtils.isNull(selectedBlock))
            if(!NetworkUtils.isNull(txtSelected))
                txtSelected =  txtSelected + " - " + selectedBlock;
            else
                txtSelected = "" + selectedBlock;
        if(!NetworkUtils.isNull(selectedDistrict))
            if(!NetworkUtils.isNull(txtSelected))
                txtSelected =  txtSelected + " - " + selectedDistrict;
            else
                txtSelected = "" + selectedDistrict;
        if(!NetworkUtils.isNull(selectedState))
            if(!NetworkUtils.isNull(txtSelected))
                txtSelected =  txtSelected+ " - " + selectedState;
            else
                txtSelected = "" + selectedState;

        txt_selected.setText(txtSelected);
        txt_select.setText(selectionFor);
        fetchData();
    }

    @Override
    public void onBackPressed() {
        if(currentSelection.equalsIgnoreCase("nothing"))
            super.onBackPressed();
        else
            imgBackPressed();
    }
}
