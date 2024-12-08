import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Serializador para LocalDate
object LocalDateSerializer : KSerializer<LocalDate> {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        encoder.encodeString(value.format(formatter))
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString(), formatter)
    }
}

// Configuración del Json con SerializersModule
val serializersModuleDates = SerializersModule {
    contextual(LocalDate::class, LocalDateSerializer) // Registramos el serializador para LocalDate
}

val json = Json { serializersModule = serializersModuleDates } // Configuración del Json

// Clase que maneja la lectura y escritura de los torneos en un archivo JSON
class ManejadorArchivo {

    companion object {
        // Obtiene el directorio donde se encuentra el archivo main.kt
        private val directorioActual = File("src/main/kotlin").absolutePath

        // Ruta del archivo JSON relativo al directorio de trabajo
        private val archivoRuta = "$directorioActual/torneos.json"

        // Guarda los torneos en el archivo JSON
        fun guardarTorneos(torneos: List<Torneo>) {
            val jsonString = json.encodeToString(torneos) // Usamos la configuración de Json con el SerializersModule
            File(archivoRuta).writeText(jsonString)
        }

        // Carga los torneos desde el archivo JSON
        fun cargarTorneos(): MutableList<Torneo> {
            val file = File(archivoRuta)
            return if (file.exists()) {
                val jsonString = file.readText()
                json.decodeFromString(jsonString) // Deserializa el JSON con el SerializersModule
            } else {
                mutableListOf()
            }
        }
    }
}
