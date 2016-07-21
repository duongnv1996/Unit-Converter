package com.duongkk.unitconverter.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.duongkk.unitconverter.R;
import com.duongkk.unitconverter.adapters.ConverterAdapter;
import com.duongkk.unitconverter.libs.Config;
import com.duongkk.unitconverter.models.ConverterItem;
import com.duongkk.unitconverter.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerConverter;
    private ConverterAdapter mAdapter;
    private List<ConverterItem> mListConverters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mRecyclerConverter =(RecyclerView)findViewById(R.id.rcv_convert);
        mListConverters = new ArrayList<>();

        addConverterItem();
        mAdapter= new ConverterAdapter(mListConverters);
        mRecyclerConverter.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerConverter.setHasFixedSize(true);
        mRecyclerConverter.setAdapter(mAdapter);

    }

    private void addConverterItem() {
        ConverterItem item  = new ConverterItem();
        item.setNameConverter(getString(R.string.length));
        item.setId(Constants.UNIT_LENGTH);
        item.setUnits(Config.linears);
        item.setDrawableIcon(R.drawable.ic_length);
        item.setColor(R.color.material_red_700);
        item.setColor2(R.color.material_red_500);
        ConverterItem itemArea  = new ConverterItem();
        itemArea.setNameConverter(getString(R.string.area));
        itemArea.setUnits(Config.squares);
        itemArea.setId(Constants.UNIT_AREA);
        itemArea.setDrawableIcon(R.drawable.ic_area);
        itemArea.setColor(R.color.material_indigo_500);
        itemArea.setColor2(R.color.material_indigo_700);
        ConverterItem itemCoordinate  = new ConverterItem();
        itemCoordinate.setNameConverter(getString(R.string.coordinate));
        itemCoordinate.setUnits(Config.coordinates);
        itemCoordinate.setId(Constants.UNIT_COORDINATE);
        itemCoordinate.setColor(R.color.material_teal_500);
        itemCoordinate.setColor2(R.color.material_teal_700);
        itemCoordinate.setDrawableIcon(R.drawable.ic_compass);
        ConverterItem itemVolumes  = new ConverterItem();
        itemVolumes.setNameConverter(getString(R.string.volume));
        itemVolumes.setUnits(Config.volumes);
        itemVolumes.setId(Constants.UNIT_VOLUME);
        itemVolumes.setColor(R.color.material_teal_500);
        itemVolumes.setColor2(R.color.material_teal_700);
        itemVolumes.setDrawableIcon(R.drawable.ic_volume);
        mListConverters.add(item);
        mListConverters.add(itemArea);
        mListConverters.add(itemVolumes);
        mListConverters.add(itemCoordinate);
    }
}
