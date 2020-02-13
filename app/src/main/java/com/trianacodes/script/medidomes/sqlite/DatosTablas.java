package com.trianacodes.script.medidomes.sqlite;

import java.util.UUID;

public class DatosTablas {

    private DatosTablas() {
    }

    public static String DATOS_GENERALES = "datos_generales";
    public static String DETALLES = "detalles";


    /* Las actualizaciones de bases de datos siempre deben ir como esta instrucción  de ejemplo de abajo: */
    /*public static final String ACTUALIZA_BASEDATOS_VERSION_2 = "ALTER TABLE " + TABLA_AROMA + " ADD COLUMN " +
            AROMA_OBSERVACIONES + " TEXT";
      public static final String ACTUALIZA_BASEDATOS_VERSION_3 = "ALTER TABLE " + TABLA_AROMA + " ADD COLUMN " +
            AROMA_HASTA_PORCENTAJE + INTEGER_TYPE + COMMA_SEP +
            AROMA_VALORACION + REAL_TYPE + COMMA_SEP + AROMA_IMAGEN + TEXT_TYPE;
     */

    /* Constantes que contienen las sentencias de creación de las tablas (Cada vez que añada, borre
     o modifique un campo de una tabla debo añadir aquí el cambio. Esto es para que si la base de
     datos se crea nueva, vaya con todas las modificaciones hechas)*/
    public static String PLANTILLA_TABLA_DATOS_GENERALES = "CREATE TABLE %s (%s TEXT PRIMARY KEY, " +
            "%s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER)";
    public static String PLANTILLA_TABLAS_DETALLES = "CREATE TABLE %s (%s TEXT NOT NULL REFERENCES %s (%s)" +
            " ON DELETE CASCADE, %s INTEGER PRIMARY KEY, %s INTEGER NOT" +
            " NULL, %s INTEGER NOT NULL, % INTEGER NOT NULL, UNIQUE(%s,%s))";

    /*La última plantilla sustituyendo los %s debe quedar así:
        CREATE TABLE Lineas Historico (Id_Cabecera TEXT NOT NULL REFERENCES
            Cabecera Historico (Id_Cabecera) ON DELETE CASCADE, Id INTEGER PRIMARY KEY
            AUTOINCREMENT, Concepto TEXT NOT NULL, Importe REAL NOT NULL, UNIQUE (Id_Cabecera, Id)
     */

    interface CamposDatosGenerales{

        String ID = "Id";
        String NOMBRE_MEDICAMENTO = "Nombre_Medicamento";
        String USO = "Uso";
        String FECHA_INICIO = "Fecha_Inicio";
        String DURACION = "Duracion";
        String POSOLOGIA = "Posologia";
        String NUMERO_LUGARES_APLICACION = "Numero_Lugares_Aplicacion";
    }

    interface CamposDetalles{

        String ID = "Id";
        String NUMERO_TABLA = "Numero_Tabla";
        String FECHA = "Fecha";
        String HORA = "Hora";
        String SI_NO = "Si_No";

    }

    /* ahora vienen las clases que van a ayudar a la administración de las tablas */

    public static class DatosGenerales implements CamposDatosGenerales{

        public static String generarIdDatosGenerales(){

            return "DG-" + UUID.randomUUID().toString();

        }

    }

    public static class Detalle implements CamposDetalles{

        public static String generarIdDetalle(){

            return "DE-" + UUID.randomUUID().toString();

        }

    }

}
