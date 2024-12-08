import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate
@Serializable
data class Torneo(
    val id: Int,
    val nombre: String,
    @Contextual val fecha: LocalDate, // Usamos LocalDate para la fecha
    val lugar: String,
    val costoInscripcion: Double,
    val activo: Boolean, // Campo booleano que puede indicar si el torneo está activo
    val participantes: MutableList<Participante>
)
