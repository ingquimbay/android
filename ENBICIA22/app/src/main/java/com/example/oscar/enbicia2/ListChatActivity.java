package com.example.oscar.enbicia2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListChatActivity extends AppCompatActivity {

    // obtener JSON de chats de firebase y listarlos
    // al seleccionar un chat, abrir actividad ChatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat);
    }
}
