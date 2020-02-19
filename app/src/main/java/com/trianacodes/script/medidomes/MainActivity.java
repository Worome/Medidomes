package com.trianacodes.script.medidomes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trianacodes.script.medidomes.entidades.General;
import com.trianacodes.script.medidomes.ui.Mensajes;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, uso, fecha_inicial, duracion,posologia, lugares;
    private Button siguiente;
    private General objetoGeneral = new General();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.etMedicamento);
        uso = findViewById(R.id.etUso);
        fecha_inicial = findViewById(R.id.et_Fecha_Inicio);
        duracion = findViewById(R.id.etDuracion);
        posologia = findViewById(R.id.etPosologia);
        lugares = findViewById(R.id.et_LugaresAplicacion);
        siguiente = findViewById(R.id.btnSiguiente);

        GuardaDatos();

    }

    private void GuardaDatos() {

        try{

            objetoGeneral.setMedicina("");
            objetoGeneral.setUso("");
            objetoGeneral.setFechaInicio("");
            objetoGeneral.setDuracion(0);
            objetoGeneral.setPosologia(0);
            objetoGeneral.setAplicaciones(0);

            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (validaCampos()){



                    } else {

                        muestraMensaje("\n\nNo puede haber ningún campo vacío. Por favor, revísalos.");

                    }

                }

            });

        } catch (Exception e){

            muestraMensaje(e.getMessage().toString());

        }

    }

    private void muestraMensaje(String literal){

 /*        Guardo den el SharedPreferences los datos necesarios que hay que mostrar
        en el cuadro de diálogo. Parece que como estoy dentro de un Fragment hay
        que anteponer al getSharedpreferences un objeto de tipo Context (en este
                caso lo he llamado getContext aunque también puede ser this.getActivity())*/
        SharedPreferences preferencias =
                this.getSharedPreferences("Dialogos", Context.MODE_PRIVATE);
        SharedPreferences.Editor datosEnviados = preferencias.edit();
        datosEnviados.putString("Titulo",getString(R.string.Errores));
        datosEnviados.putString("Mensaje", getString(R.string.mensaje_error_campo_vacio) + " \n" +
                literal);
        datosEnviados.apply();
        //Creo un objeto de la clase en la que defino el cuadro de diálogo
        Mensajes dialogoPersonalizado = new Mensajes();
            /*Muestro el cuadro de diálogo pasándo como parámetros el manejador de
        fragmentos y una etiqueta que se va a usar para localizar el cuadro de
        diálogo para hacer tareas con el cuadro de diálogo. He tenido que sustituir
        el getSupportFragmentManager por getFragmentManager ya que estoy llamando a
        un Fragment desde otro Fragment.*/
            /* En esta actividad se ha sustituido el getFragmentManager por getSupportFragmentManager*/
        dialogoPersonalizado.show(getSupportFragmentManager(), "personalizado");
        // Creo un objeto de tipo Fragment para almacenar en él el cuadro de diálogo
        Fragment fragmento = getSupportFragmentManager().findFragmentByTag("personalizado");

        // Borro el cuadro de diálogo si no se está mostrando
        if (fragmento != null){

            //getFragmentManager().beginTransaction().remove(fragmento).commit();
            getSupportFragmentManager().beginTransaction().remove(fragmento).commit();

        }

    }

    private Boolean validaCampos(){

        boolean resultado = false;

        // Incorporo a un Array de tipo EditText todos los campos del activity
        final EditText[] Campos = {nombre, uso, fecha_inicial, duracion, posologia, lugares};

        /*
        Recorro el array que he creado para comprobar que no hay ningún campo vacío.
         */
        for (EditText vista : Campos){

            if(vista.getText().toString().isEmpty()){
                return resultado;
            }

        }

        resultado = true;
        return resultado;

    }

}
