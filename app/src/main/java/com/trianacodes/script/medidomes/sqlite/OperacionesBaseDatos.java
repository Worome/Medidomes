package com.trianacodes.script.medidomes.sqlite;

/* Creo un algoritmo con el patrón Singleton. Esto significa que creo un constructor principal como
 *  privado, defino un método estático de la clase y un método estático que permita la obtención del
 *  único miembro. */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.trianacodes.script.medidomes.entidades.General;

public final class OperacionesBaseDatos {

    private OperacionesBaseDatos() {
    }

    private static DbHelper baseDatos;
    private static OperacionesBaseDatos instancia = new OperacionesBaseDatos();

    /* Esto es un ejemplo más complicado que el de la línea de arriba en la que se indican varias
    tablas unidas por JOINs */
    /*private static final String CABECERA_PEDIDO_JOIN_CLIENTE_Y_FORMA_PAGO = "cabecera_pedido " +
            "INNER JOIN cliente " +
            "ON cabecera_pedido.id_cliente = cliente.id " +
            "INNER JOIN forma_pago " +
            "ON cabecera_pedido.id_forma_pago = forma_pago.id";*/

    /* Este variable de tipo array se usa dentro de la instrucción builder.query, que es el que
       obliga a que dicha variable sea un array. En ella se establece el contenido del SELECT de
       la consulta, es decir los campos que se quieren mostrar en una consulta.*/
    private final String[] consultaMedicacion = new String[]{
            DatosTablas.DATOS_GENERALES + "." + DatosTablas.DatosGenerales.NOMBRE_MEDICAMENTO,
            DatosTablas.DatosGenerales.USO, DatosTablas.DatosGenerales.FECHA_INICIO,
            DatosTablas.DatosGenerales.DURACION, DatosTablas.DatosGenerales.POSOLOGIA,
            DatosTablas.DatosGenerales.NUMERO_LUGARES_APLICACION};
    /*private final String[] consultaEstados = new String[]{
            DatosTablas.ESTADOS + "." + DatosTablas.ColumnasEstados.ID,
            DatosTablas.ColumnasEstados.DESCRIPCION};
    private final String[] consultaGastosFijos = new String[]{
            DatosTablas.GASTOS_FIJOS + "." + DatosTablas.ColumnasGastosFijos.ID,
            DatosTablas.ColumnasGastosFijos.DESCRIPCION, DatosTablas.ColumnasGastosFijos.ESTADO,
            DatosTablas.ColumnasGastosFijos.IMPORTE};
    private final String[] consultaGastosPeriodicos = new String[]{
            DatosTablas.GASTOS_PERIODICOS + "." + DatosTablas.ColumnasGastosPeriodicos.ID,
            DatosTablas.ColumnasGastosPeriodicos.DESCRIPCION,
            DatosTablas.ColumnasGastosPeriodicos.ESTADO,
            DatosTablas.ColumnasGastosPeriodicos.IMPORTE,
            DatosTablas.ColumnasGastosPeriodicos.MESES};
    private final String[] consultaIngresosFijos = new String[]{
            DatosTablas.INGRESOS_FIJOS + "." + DatosTablas.ColumnasIngresosFijos.ID,
            DatosTablas.ColumnasIngresosFijos.DESCRIPCION,
            DatosTablas.ColumnasIngresosFijos.ESTADO,
            DatosTablas.ColumnasIngresosFijos.IMPORTE};
    private final String[] consultaIngresosExtras = new String[]{
            DatosTablas.INGRESOS_EXTRAS + "." + DatosTablas.ColumnasIngresosExtras.ID,
            DatosTablas.ColumnasIngresosExtras.DESCRIPCION,
            DatosTablas.ColumnasIngresosExtras.ESTADO,
            DatosTablas.ColumnasIngresosExtras.IMPORTE,
            DatosTablas.ColumnasIngresosExtras.MESES};
*/

    public static OperacionesBaseDatos obtenerInstancia(Context contexto){
        if(baseDatos == null){

            baseDatos = new DbHelper(contexto);

        }

        return instancia;
    }

    // Este método obtiene todas los registros de la tablas
    public Cursor obtenerMedicacion(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        /* Se usa la clase SQLiteQueryBuilder para hacer consultas complejas que puedan implicar
           joins. Si la consulta afecta a una sola tabla se puede usar el método rawQuery().*/
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        // El método setTables establece sobre qué tabla se va a realizar la consulta.
        builder.setTables(DatosTablas.DATOS_GENERALES);
        return builder.query(db, consultaMedicacion, null, null, null,
                null, null);
    }

    /* Si se quiere obtener sólo un registro dependiendo del valor de alguno de sus campo
       (SELECT... WHERE) se haría de la siguiente forma: */
    public Cursor obtenerUnaMedicacion(String id){

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String where = String.format("%s=?", DatosTablas.DatosGenerales.NOMBRE_MEDICAMENTO);
        String[] argWhere = {id};
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DatosTablas.DATOS_GENERALES);
        return builder.query(db, consultaMedicacion, where, argWhere, null, null,
                null);

    }


    /* Para insertar registros en una tabla se haría como viene abajo. El parámetro que recibe el
       método insertarBanco es un objeto (Banco) de la clase Bancos*/

    public String insertarGeneral(General general){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Genero el id del banco, no sé si hace falta esta instrucción.
        String idDatosGenerales = DatosTablas.DatosGenerales.generarIdDatosGenerales();
        /* Creo el contenedor donde se almacenarán los valores de cada campo del regitro*/
        ContentValues valores = new ContentValues();
        valores.put(DatosTablas.DatosGenerales.ID, idDatosGenerales);
        valores.put(DatosTablas.DatosGenerales.NOMBRE_MEDICAMENTO, general.getMedicina());
        valores.put(DatosTablas.DatosGenerales.USO, general.getUso());
        valores.put(DatosTablas.DatosGenerales.FECHA_INICIO, general.getFechaInicio());
        valores.put(DatosTablas.DatosGenerales.DURACION, general.getDuracion());
        valores.put(DatosTablas.DatosGenerales.POSOLOGIA, general.getPosologia());
        valores.put(DatosTablas.DatosGenerales.NUMERO_LUGARES_APLICACION, general.getAplicaciones());
        db.insertOrThrow(DatosTablas.DATOS_GENERALES, null, valores);
        return idDatosGenerales;

    }


    public Boolean actualizarGeneral(General general){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DatosTablas.DatosGenerales.NOMBRE_MEDICAMENTO, general.getMedicina());
        valores.put(DatosTablas.DatosGenerales.USO, general.getUso());
        valores.put(DatosTablas.DatosGenerales.FECHA_INICIO, general.getFechaInicio());
        valores.put(DatosTablas.DatosGenerales.DURACION, general.getDuracion());
        valores.put(DatosTablas.DatosGenerales.POSOLOGIA, general.getPosologia());
        valores.put(DatosTablas.DatosGenerales.NUMERO_LUGARES_APLICACION, general.getAplicaciones());
        String where = String.format("%s=?", DatosTablas.DatosGenerales.ID);
        String[] argWhere = {general.getId()};
        int resultado = db.update(DatosTablas.DATOS_GENERALES,valores, where, argWhere);
        return resultado > 0;

    }

    public Boolean eliminarGeneral(String id){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String where = DatosTablas.DatosGenerales.ID + " =? ";
        String[] argWhere = {id};
        int resultado = db.delete(DatosTablas.DATOS_GENERALES, where, argWhere);
        return resultado > 0;

    }

    public SQLiteDatabase getDb(){

        return baseDatos.getWritableDatabase();

    }

}