package es.iessaladillo.pedrojoya.pr02_greetimproved;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import es.iessaladillo.pedrojoya.pr02_greetimproved.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    private int contador;
    private MainActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViews();
    }

    private void setupViews() {
        contador=0;
        binding.nameValue.requestFocus();
        cambiarColorNombre();
        binding.progressBar.setMax(10);
        binding.icono.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_mr,0);
        binding.premiun.setOnClickListener(v -> usuarioPremiun());
        binding.botones.setOnCheckedChangeListener((group, checkedId) -> cambiarIcono());
        binding.greet.setOnClickListener(v -> check());
        binding.nameValue.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                cambiarColorNombre();
            }else{
                volverColorNombre();
            }
        });
        binding.nameValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularTamanoNombre();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<=0){
                    binding.nameValue.setError("Required");
                }
            }

        });
        binding.sirNameValue.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                cambiarColorApellido();
            }else{
                volverColorApellido();
            }
        });
        binding.sirNameValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculartamanoApell();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<=0){
                    binding.sirNameValue.setError("Required");
                }
            }
        });
        binding.sirNameValue.setOnEditorActionListener((v, actionId, event) -> {
            SoftInputUtils.hideSoftKeyboard(binding.sirNameValue);
            check();
            return true;
        });
    }

    private void usuarioPremiun() {

        if(binding.premiun.isChecked()){
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.progressBar.setProgress(0);
            binding.textProgres.setVisibility(View.INVISIBLE);
            binding.textFinal.setText("");
            contador=0;
        }else{
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.textProgres.setVisibility(View.VISIBLE);
            binding.textProgres.setText("");
        }
    }

    private void calculartamanoApell() {
        int numero=(20-binding.sirNameValue.getText().length());

        binding.tamanoApell.setText(numero+" "+getResources().getQuantityString(R.plurals.plural,numero));
    }

    private void cambiarColorApellido() {
        if(binding.sirNameValue.getText().length()==0){
            binding.tamanoApell.setText("20 "+getResources().getQuantityString(R.plurals.plural,binding.sirNameValue.getText().length()));

        }else{
            binding.tamanoApell.setText((20-binding.sirNameValue.getText().length())+" "+getResources().getQuantityString(R.plurals.plural,binding.sirNameValue.getText().length()));
        }

        binding.tamanoApell.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
    }

    private void volverColorApellido() {
        binding.tamanoApell.setTextColor(ContextCompat.getColor(this,R.color.negro));
    }

    private void volverColorNombre() {
        binding.tamanoNom.setTextColor(ContextCompat.getColor(this,R.color.negro));
    }

    private void cambiarColorNombre() {
        if(binding.nameValue.getText().length()==0){
            binding.tamanoNom.setText("20 "+getResources().getQuantityString(R.plurals.plural,binding.nameValue.getText().length()));
        }else{
            binding.tamanoNom.setText((20-binding.nameValue.getText().length())+" "+getResources().getQuantityString(R.plurals.plural,binding.nameValue.getText().length()));
        }

        binding.tamanoNom.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
    }

    private void calcularTamanoNombre() {
        int numero=(20-binding.nameValue.getText().length());
        binding.tamanoNom.setText(numero+" "+getResources().getQuantityString(R.plurals.plural,numero));
    }

    private void cambiarIcono() {
        int seletion=binding.botones.getCheckedRadioButtonId();


        if(seletion==2131165263){
            binding.icono.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_mr,0);
        }
        else if(seletion==2131165264){
            binding.icono.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_mrs,0);
        }
        else if(seletion==2131165265){
            binding.icono.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_ms,0);
        }

    }

    private void check() {


        if(binding.nameValue.getText().length()>0 && binding.sirNameValue.getText().length()>0){
            if(binding.premiun.isChecked()){
                sayHi();
            }else{

                if(contador<10){
                    sayHi();
                    contador++;
                    binding.progressBar.incrementProgressBy(1);
                    binding.textProgres.setText(contador+" de 10");
                }else{
                    binding.textFinal.setText("buy premiun subscription to go on greeting ");

                }


            }
        }else{
            if(binding.nameValue.length()<=0) {
                binding.nameValue.setError("Required");
                binding.nameValue.requestFocus();
            }else if(binding.sirNameValue.length()<=0){
                binding.sirNameValue.setError("Required");
                binding.sirNameValue.requestFocus();
            }

        }




    }

    private void sayHi() {
        Editable nombre= binding.nameValue.getText();
        Editable apellido= binding.sirNameValue.getText();
        String title="";
        int seletion=binding.botones.getCheckedRadioButtonId();

        if(binding.checkBox.isChecked()){
            if(seletion==2131165263){
                title="Mr";
            }
            else if(seletion==2131165264){
                title="Mrs";
            }
            else if(seletion==2131165265){
                title="Ms";
            }
            //binding.textFinal.setText("Good morning "+title+" "+nombre.toString()+" "+apellido.toString()+" Pleased to meet you ");
            Toast.makeText(this,getString(R.string.saludoFormal,title,nombre.toString(),apellido.toString()),Toast.LENGTH_SHORT).show();

            //saludo educado
        }else{
            // binding.textFinal.setText("Hello "+nombre.toString()+" "+apellido.toString()+" whats up? ");//saludo normal
            Toast.makeText(this,getString(R.string.saludo,nombre.toString(),apellido.toString()),Toast.LENGTH_SHORT).show();
        }


    }


}