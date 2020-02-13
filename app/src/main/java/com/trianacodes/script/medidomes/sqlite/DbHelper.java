package com.trianacodes.script.medidomes.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DbHelper extends SQLiteOpenHelper {

    /* Cada vez que haya una modificación en cualquiera de las tablas, hay que subir la versión en
    la constante DATABASE_VERSION para poder hacer las actualizaciones correspondientes. */
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "medicon.sqlite";
    /*Al crear la siguiente variable da un error porque hay que inicializarla en el Constructor
     del DbHelper.*/
    private final Context contexto;

    public DbHelper(Context contextoActual) {
        super(contextoActual, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = contextoActual;
    }

    @Override
    public void onOpen(SQLiteDatabase db){

        super.onOpen(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){

            db.setForeignKeyConstraintsEnabled(true);

        } else {

            db.execSQL("PRAGMA foreing_keys=ON");

        }

    }

    // En este método es en donde se crean las tablas.
    @Override
    public void onCreate(SQLiteDatabase db) {

        /* TODO Aquí se crean todas las tablas. Todas ellas menos la CabeceraHistorico y LineasHistorico
            se han creado usando la interfaz; las dos últimas se han creado usando la clase que
            implementa su interfaz. Si ambas funcionan, es mejor usar el método de las clases.
         */

        db.execSQL(String.format(DatosTablas.PLANTILLA_TABLA_DATOS_GENERALES, DatosTablas.DATOS_GENERALES,
                DatosTablas.CamposDatosGenerales.ID, DatosTablas.CamposDatosGenerales.NOMBRE_MEDICAMENTO,
                DatosTablas.CamposDatosGenerales.USO, DatosTablas.CamposDatosGenerales.FECHA_INICIO,
                DatosTablas.CamposDatosGenerales.DURACION, DatosTablas.CamposDatosGenerales.POSOLOGIA,
                DatosTablas.CamposDatosGenerales.NUMERO_LUGARES_APLICACION));
        db.execSQL(String.format(DatosTablas.PLANTILLA_TABLAS_DETALLES,DatosTablas.DETALLES,
                DatosTablas.CamposDetalles.ID, DatosTablas.DATOS_GENERALES,
                DatosTablas.CamposDatosGenerales.ID, DatosTablas.CamposDetalles.NUMERO_TABLA,
                DatosTablas.CamposDetalles.FECHA, DatosTablas.CamposDetalles.HORA,
                DatosTablas.CamposDetalles.SI_NO));

    }

    /* Aquí se encuentran las instrucciones para actualizar la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionActual, int versionNueva) {

        /* Cuando hay una nueva versión de la base de datos, siempre hay que preguntar si la versión
         actual es menor que la versión nueva, si es así, se ejecuta la sentencia de la
         actualización. Por cada nueva versión hay que preguntar con nuevos if y ejecutar la
         correspondiente actualización; los if anteriores deben permanecer (no se pueden borrar)
         tal y como se muestra en el siguiente ejemplo:
         */
        /* if (versionActual < 2){
            db.execSQL(DatosTabla.ACTUALIZA_BASEDATOS_VERSION_2);
           }
           if (versionActual < 3){
            db.execSQL(DatosTabla.ACTUALIZA_BASEDATOS_VERSION_3);
           }

         */

    }

}
