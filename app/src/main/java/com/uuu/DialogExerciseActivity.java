package com.uuu;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DialogExerciseActivity extends Activity {
	final CharSequence[] items = {"選項1","選項2","選項3","選項4","選項5"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerForContextMenu(findViewById(R.id.textView));
        registerForContextMenu(findViewById(R.id.imageView));
        findViewById(R.id.button).setOnClickListener(v->displayDialog1());
//        displayDialog1();

//        displayDialog2();


    }
    private static final int IMAGE_VIEW_CONTEXT_MENU = 1;
    private static final int TEXT_VIEW_CONTEXT_MENU = 2;
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.imageView:
                menu.setHeaderTitle("menu for image view");
                menu.add(0, IMAGE_VIEW_CONTEXT_MENU, 0, "context menu1");
                break;
            case R.id.textView:
                menu.setHeaderTitle("menu for text view");
                menu.add(0, TEXT_VIEW_CONTEXT_MENU, 0, "context menu1");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView textView = findViewById(R.id.textView);
        switch (item.getItemId()){
            case IMAGE_VIEW_CONTEXT_MENU:
                textView.setText("影像本文選單被選取");
                break;
            case TEXT_VIEW_CONTEXT_MENU:
                textView.setText("文字本文選單被選取");
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"menu 1 is selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this,"menu 2 is selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(this,"sub menu 1 is selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item5:
                Toast.makeText(this,"sub menu 2 is selected",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayDialog2() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        ClickAction clickAction = new ClickAction(this);
        builder2.setTitle("多重選擇");
        //     builder2.setItems(items, clickAction);
        //builder2.setSingleChoiceItems(items, -1,null);
        builder2.setSingleChoiceItems(items, 2,null);
        //     builder2.setSingleChoiceItems(arg0, arg1, arg2)
        AlertDialog dialog2 = builder2.create();
        dialog2.show();
    }

    private void displayDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("請問你要繼續嗎?");
        builder.setMessage("如果不繼續會離開");
        builder.setPositiveButton("Yes",(dialog, which) -> {
            Toast.makeText(this, "#"+which+" is clicked", Toast.LENGTH_SHORT).show();
            displayDialog2();
        });
        builder.setNegativeButton("No",(dialog, which) -> {
            Toast.makeText(this, "#"+which+" is clicked", Toast.LENGTH_SHORT).show();
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}