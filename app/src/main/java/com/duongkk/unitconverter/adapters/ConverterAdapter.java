package com.duongkk.unitconverter.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duongkk.unitconverter.R;
import com.duongkk.unitconverter.libs.Config;
import com.duongkk.unitconverter.libs.Convert;
import com.duongkk.unitconverter.libs.Unit;
import com.duongkk.unitconverter.models.ConverterItem;
import com.duongkk.unitconverter.utils.CommomUtils;
import com.duongkk.unitconverter.utils.Constants;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 7/12/2016.
 */
public class ConverterAdapter extends RecyclerView.Adapter<ConverterAdapter.ViewHolder> {


    private final List<ConverterItem> data;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public ConverterAdapter(final List<ConverterItem> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_converter, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ConverterItem item = data.get(position);
        holder.mTvTitle.setText(item.getNameConverter());
//       holder.mLayoutRoot.setBackgroundColor(item.getColor2());
//        holder.expandableLayout.setBackgroundColor(item.getColor());
        holder.imgIcon.setImageResource(item.getDrawableIcon());
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                createRotateAnimator(holder.mBtnExp, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                createRotateAnimator(holder.mBtnExp, 180f, 0f).start();
                expandState.put(position, false);
            }
        });
        holder.mBtnExp.setRotation(expandState.get(position) ? 180f : 0f);
        holder.mBtnExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(holder.expandableLayout);
            }
        });
        ArrayAdapter<Unit> adapter = new ArrayAdapter<Unit>(context, android.R.layout.simple_list_item_1, item.getUnits());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(item.getId()==Constants.UNIT_COORDINATE){
           List<Unit> un= new ArrayList<>();
            un.add(Config.coordinates[2]);
            ArrayAdapter<Unit> adapterCoor = new ArrayAdapter<Unit>(context,android.R.layout.simple_list_item_1,un);
            adapterCoor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spLeft.setAdapter(adapterCoor);
        }else{
            holder.spLeft.setAdapter(adapter);
        }

        holder.spRigt.setAdapter(adapter);
        holder.spRigt.setSelection(1);
        holder.spLeft.setSelection(0);
        holder.mLayoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(holder.expandableLayout);
            }
        });
        holder.edtLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertUnit(s.toString(), holder, item);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.spLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertUnit(holder.edtLeft.getText().toString(), holder, item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.spRigt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertUnit(holder.edtLeft.getText().toString(), holder, item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.imgSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(item.getId()==Constants.UNIT_COORDINATE){
                    holder.edtLeft.setText("");
                    holder.edtRight.setText("");
                }else {
                    int pos = holder.spLeft.getSelectedItemPosition();
                    holder.spLeft.setSelection(holder.spRigt.getSelectedItemPosition());
                    holder.spRigt.setSelection(pos);
                    holder.edtLeft.setText(holder.edtRight.getText().toString().replace(",", ""));
                    convertUnit(holder.edtLeft.getText().toString(), holder, item);
                }

            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = holder.edtLeft.getText().toString() + "(" + ((Unit) holder.spLeft.getSelectedItem()).getName() +") = "+
                        holder.edtRight.getText().toString() + "(" + ((Unit) holder.spRigt.getSelectedItem()).getName() +")";
                String packageName = context.getPackageName();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Hi. I was successfull in converting\n "+ result +" . \nIt is an excellent Converter tool, you can get this app free for your android phone at:  https://play.google.com/store/apps/details?id="
                        + packageName;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.app_name));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        holder.edtRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.edtRight.getText().toString().length()>0)
                CommomUtils.copyToClipBoard(holder.edtRight.getText().toString(),context);
            }
        });
    }

    private void convertUnit(String s, ViewHolder holder, ConverterItem item) {
        Unit fromU = (Unit) holder.spLeft.getSelectedItem();
        Unit toU = (Unit) holder.spRigt.getSelectedItem();
        String pre ="";
        if (s.length() > 0 && s.charAt(s.length() - 1) != '.' ) {
            if(s.charAt(0)=='-'){
                pre+="-";
                s=s.replace('-','0');
              // s = s.substring(1);
            }
            if (item.getId() == Constants.UNIT_COORDINATE) {
                String result = Convert.convertCoordinateUnit(Double.parseDouble(s), toU);
                holder.edtRight.setText(pre+result);
            } else {
                String result = Convert.dinhDangData(Convert.convertUnit(Double.parseDouble(s), fromU, toU), toU);
                holder.edtRight.setText(pre+result + "");
            }
        }
    }

    private void onClickButton(final ExpandableLayout expandableLayout) {
        expandableLayout.toggle();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLayoutRoot;
        public TextView mTvTitle;
        public RelativeLayout mBtnExp;
        public ExpandableLinearLayout expandableLayout;
        AppCompatSpinner spLeft;
        AppCompatSpinner spRigt;
        ImageView imgSwap;
        EditText edtLeft;
        EditText edtRight;
        ImageView imgIcon;
        ImageView btnShare;

        public ViewHolder(View v) {
            super(v);
            mTvTitle = (TextView) v.findViewById(R.id.tv_converter);
            mBtnExp = (RelativeLayout) v.findViewById(R.id.triangle_ex);
            mLayoutRoot = (LinearLayout) v.findViewById(R.id.ll_ex);
            expandableLayout = (ExpandableLinearLayout) v.findViewById(R.id.expandableLayout);
            spLeft = (AppCompatSpinner) v.findViewById(R.id.sp_from_unit);
            spRigt = (AppCompatSpinner) v.findViewById(R.id.sp_to_unit);
            imgSwap = (ImageView) v.findViewById(R.id.btn_swap);
            imgIcon = (ImageView) v.findViewById(R.id.img_unit);
            edtLeft = (EditText) v.findViewById(R.id.edt_l);
            edtRight = (EditText) v.findViewById(R.id.edt_r);
            btnShare = (ImageView)v.findViewById(R.id.btn_share);
        }
    }

    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(100);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
}
