package com.example.demoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demoapp.R;
import com.example.demoapp.model.DataModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<DataModel> DataModelList;


    public DataAdapter(@NonNull Context context, int resource,  List<DataModel> dataModelList) {
        super(context, resource, dataModelList);
        this.context = context;
        this.resource = resource;
        this.DataModelList = dataModelList;
    }

    @Nullable
    @Override
    public DataModel getItem(int position) {
        return DataModelList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null);
        TextView name = view.findViewById(R.id.txt_list_data_name);
        TextView desc = view.findViewById(R.id.txt_list_data_desc);
//        ImageView imgPath = view.findViewById(R.id.img_dataImage);

        DataModel model = getItem(position);
        name.setText(model.getName());
        desc.setText(model.getDesc());

//        Glide.with(context).load(model.getImg_path()).override(100, 100).error(R.drawable.imageupload).into(imgPath);


        return view;


    }
}
