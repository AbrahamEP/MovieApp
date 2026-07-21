package database;

import java.sql.Connection;

/**
 * Crea conexiones JDBC para la base de datos SQLite.
 *
 * Mantén esta clase enfocada. No debe crear tablas, ejecutar consultas,
 * mapear filas de ResultSet ni saber nada sobre Swing.
 */
public class DatabaseManager {

    /*
     * EJERCICIO PARA ESTUDIANTES
     *
     * Reemplaza la cadena vacía con la URL JDBC de SQLite para este proyecto.
     *
     * Practica:
     * - Formato de URL de conexión SQLite
     * - Entender dónde se guarda el archivo de base de datos
     * - Mantener la configuración de conexión en un solo lugar
     *
     * Pista:
     * El proyecto original guardaba su base de datos en un archivo llamado movieapp.db.
     */
    private static final String DATABASE_URL = "";

    public Connection getConnection() {
        /*
         * EJERCICIO PARA ESTUDIANTES
         *
         * Crea y devuelve una conexión JDBC usando DriverManager.
         *
         * Practica:
         * - Llamar a DriverManager.getConnection(DATABASE_URL)
         * - Decidir dónde debe manejarse SQLException
         * - Entender por qué la apertura de conexiones está centralizada
         *
         * Piensa en:
         * - ¿Este método debería capturar SQLException o declararla?
         * - ¿Qué ocurre si DATABASE_URL está vacía o tiene un formato incorrecto?
         */
        return null;
    }
}
