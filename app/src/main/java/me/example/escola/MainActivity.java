package me.example.escola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtN1, edtN2;
    TextView txtM, txtSit;
    ImageView imgSit;
    LinearLayout layResult;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtN1 = findViewById(R.id.edtN1);
        edtN2 = findViewById(R.id.edtN2);
        txtM = findViewById(R.id.txtM);
        txtSit = findViewById(R.id.txtSit);
        imgSit = findViewById(R.id.imgSit);
        layResult = findViewById(R.id.layResult);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        layResult.setVisibility(View.INVISIBLE);
    }

    public void calcular(View view) {
        boolean ok = true;
        if (edtN1.getText().toString().trim().isEmpty()){
            ok = false;
            edtN1.setError(getString(R.string.msgErro));
        }
        if (edtN2.getText().toString().trim().isEmpty()){
            ok = false;
            edtN2.setError(getString(R.string.msgErro));
        }
        if (ok) {
            imm.hideSoftInputFromWindow(edtN1.getWindowToken(),0);
            layResult.setVisibility(View.VISIBLE);
            float n1 = Float.parseFloat(edtN1.getText().toString());
            float n2 = Float.parseFloat(edtN2.getText().toString());
            float m = (n1 + n2) / 2;
            txtM.setText(String.format("%.1f", m));
            if (m < 5) {
                //reprovado
                txtSit.setText(getString(R.string.strSitRp));
                txtSit.setTextColor(getResources().getColor(R.color.corReprovado));
                Toast.makeText(getApplicationContext(),getString(R.string.strMsgRp), Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojireprovado);
            } else if (m < 7) {
                //recuperacao
                txtSit.setText(getString(R.string.strSitRc));
                txtSit.setTextColor(getResources().getColor(R.color.corRecuperacao));
                Toast.makeText(getApplicationContext(),getString(R.string.strMsgRc), Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojirecuperacao);
            } else {
                //aprovado
                txtSit.setText(getString(R.string.strSitAp));
                txtSit.setTextColor(getResources().getColor(R.color.corAprovado));
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgAp), Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojiaprovado);
            }
        }
    }
}