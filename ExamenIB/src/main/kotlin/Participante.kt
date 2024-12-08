import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Participante(
    val id: Int,
    var nombre: String,
    @Contextual var fechaNacimiento: LocalDate,
    var ranking: Double,
    var activo: Boolean
)