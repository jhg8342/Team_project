package com.gachon.mapping;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiaryTitle extends AppCompatActivity implements View.OnClickListener
{
    private Activity activity;

    private EditText et_item;

   // public static Context context;

    private Button btn_add, btn_load;

    private RecyclerView rv_click_apply;
    private DatabaseReference mDatabase;
    private ClickApplyAdapter clickApplyAdapter;

    private List<String> itemNameList;

    private ImageButton btn_back;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        try
        {
            setContentView(R.layout.activity_diary_title);

            init();
            load_btn_listener();
            setting();

            addListener();


        }
        catch(Exception ex)
        {
            LogService.error(this, ex.getMessage(), ex);
        }



    }


    private void init()
    {
        btn_load = findViewById(R.id.btn_load);

        activity = this;

        et_item = findViewById(R.id.et_item);

        btn_add = findViewById(R.id.btn_add);

        btn_load = findViewById(R.id.btn_load);

        btn_back = findViewById(R.id.btn_back);

        rv_click_apply = findViewById(R.id.rv_click_apply);

        itemNameList = new ArrayList<>();

        clickApplyAdapter = new ClickApplyAdapter(activity, itemNameList, this, this);
    }

    private void setting()
    {
        rv_click_apply.setAdapter(clickApplyAdapter);

        // 리니어 레이아웃 매니저로 수직으로 배치 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv_click_apply.setLayoutManager(linearLayoutManager);
    }

    private void addListener()
    {

        btn_add.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.btn_add)
        {
            String item = et_item.getText().toString();

            if(item.isEmpty())
            {
                Toast.makeText(activity, "제목을 써주세요", Toast.LENGTH_SHORT).show();
            }
            else
            {
                itemNameList.add(item);

                clickApplyAdapter.notifyDataSetChanged();
            }
        }
        else if(view.getId() == R.id.btn_click_item)
        {
            //context = this;
            Intent intent =new Intent(getApplicationContext(), Diary.class);
            intent.putExtra("title",et_item.getText().toString());
            startActivity(intent);
        }

        else if(view.getId() == R.id.btn_delete_item){
            int position = (int) view.getTag();

            itemNameList.remove(position);

            clickApplyAdapter.notifyDataSetChanged();

        }
        else if (view.getId() == R.id.btn_back){
            finish();
        }




    }

    public void load_btn_listener(){

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid = firebaseAuth.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                Query lastQuery = mDatabase.child("다이어리").child(uid).orderByKey();

                System.out.println(lastQuery + "테스트");

            }
        });

    }





}

