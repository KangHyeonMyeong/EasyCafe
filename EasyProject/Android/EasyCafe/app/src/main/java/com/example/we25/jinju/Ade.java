package com.example.we25.jinju;

import com.example.we25.easycafe.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-06-25.
 */

// 메뉴 -> 대분류 (액션바 탭 메뉴 에이드 -> 프래그먼트)

public class Ade extends Fragment {

    ListView listView;
    CafeAdapter adapter;


    // 프래그먼트 안에 리스트뷰 추가

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ade, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);

        adapter = new CafeAdapter();

        ArrayList<CafeItem> adelist =(ArrayList<CafeItem>) getArguments().getSerializable("list");
        for(int i = 0 ; i < adelist.size(); i++) {
            adapter.addItem(adelist.get(i));
        }


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                CafeItem cafeItem =(CafeItem) adapter.getItem(position);
                Intent intent = new Intent(getContext() , MenuInfo.class);
                intent.putExtra("MenuData", cafeItem);
                startActivity(intent);

            }
        });
        return rootView;
    }

    class CafeAdapter extends BaseAdapter {
        ArrayList<CafeItem> items = new ArrayList<CafeItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CafeItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            CafeItemView view = new CafeItemView(getContext());

            CafeItem item = items.get(position);
            view.setName(item.getMenu_name());
            view.setImage(R.drawable.go);

            return view;
        }
    }

}

