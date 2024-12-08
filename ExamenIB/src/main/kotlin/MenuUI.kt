import java.time.LocalDate
import java.util.*

class MenuUI {
    private val manejadorTorneo = ManejadorTorneo()
    private val manejadorParticipante = ManejadorParticipante()

    fun mostrarMenuPrincipal() {
        while (true) {
            try {
                println("\n===== Menú Principal =====")
                println("1. Menú Torneos")
                println("2. Menú Participantes")
                println("3. Salir")
                print("Seleccione una opción: ")

                when (readLine()?.toIntOrNull()) {
                    1 -> mostrarMenuTorneos()
                    2 -> mostrarMenuParticipantes()
                    3 -> {
                        println("Saliendo del programa...")
                        break
                    }
                    else -> println("Opción no válida, intente nuevamente.")
                }
            } catch (e: Exception) {
                println("Error: ${e.message}. Intente nuevamente.")
            }
        }
    }

    private fun mostrarMenuTorneos() {
        while (true) {
            try {
                println("\n===== Menú Torneos =====")
                println("1. Crear Torneo")
                println("2. Leer Torneos")
                println("3. Actualizar Torneo")
                println("4. Eliminar Torneo")
                println("5. Volver al Menú Principal")
                print("Seleccione una opción: ")

                when (readLine()?.toIntOrNull()) {
                    1 -> crearTorneo()
                    2 -> manejadorTorneo.leerTorneos()
                    3 -> actualizarTorneo()
                    4 -> eliminarTorneo()
                    5 -> return
                    else -> println("Opción no válida, intente nuevamente.")
                }
            } catch (e: Exception) {
                println("Error: ${e.message}. Intente nuevamente.")
            }
        }
    }

    private fun mostrarMenuParticipantes() {
        while (true) {
            try {
                println("\n===== Menú Participantes =====")
                println("1. Agregar Participante a Torneo")
                println("2. Leer Participantes de un Torneo")
                println("3. Actualizar Participante")
                println("4. Eliminar Participante")
                println("5. Lista de Torneos")
                println("6. Volver al Menú Principal")
                print("Seleccione una opción: ")

                when (readLine()?.toIntOrNull()) {
                    1 -> agregarParticipante()
                    2 -> leerParticipantes()
                    3 -> actualizarParticipante()
                    4 -> eliminarParticipante()
                    5 -> manejadorTorneo.leerTorneos()
                    6 -> return
                    else -> println("Opción no válida, intente nuevamente.")
                }
            } catch (e: Exception) {
                println("Error: ${e.message}. Intente nuevamente.")
            }
        }
    }

    private fun leerEntrada(prompt: String): String? {
        print(prompt)
        val input = readLine()
        if (input.equals("0", ignoreCase = true)) {
            println("Regresando al menú anterior...")
            return null
        }
        return input
    }

    private fun crearTorneo() {
        try {
            println("\nIngrese los datos del torneo o cero(0) para volver al menú:")
            val id = leerEntrada("ID:")?.toIntOrNull() ?: return
            val torneo = manejadorTorneo.obtenerTorneoPorId(id)
            if (torneo != null) {
                println("Torneo con ID $id ya existe.")
                println("Datos actuales del torneo: $torneo")
                return
            }else {
                val nombre = leerEntrada("Nombre:") ?: return
                val fecha = leerEntrada("Fecha (YYYY-MM-DD):")?.let { LocalDate.parse(it) } ?: return
                val lugar = leerEntrada("Lugar:") ?: return
                val costoInscripcion = leerEntrada("Costo de Inscripción:")?.toDoubleOrNull() ?: return
                val activo = leerEntrada("Activo (si/no):")?.lowercase(Locale.getDefault()) == "si"

                manejadorTorneo.crearTorneo(Torneo(id, nombre, fecha, lugar, costoInscripcion, activo, mutableListOf()))

                // Menú para agregar participantes
                var opcion: Int
                do {
                    println("\n¿Desea agregar participantes al torneo?")
                    println("1. Agregar participante")
                    println("2. Volver al menú anterior")
                    opcion = leerEntrada("Seleccione una opción:")?.toIntOrNull() ?: 2

                    when (opcion) {
                        1 -> agregarParticipante()
                        2 -> println("Volviendo al menú anterior...")
                        else -> println("Opción no válida. Intente nuevamente.")
                    }
                } while (opcion != 2)
            }
        } catch (e: Exception) {
            println("Error al crear torneo: ${e.message}")
        }
    }

    private fun actualizarTorneo() {
        try {
            val id = leerEntrada("\nIngrese el ID del torneo a actualizar o cero(0) para volver al menú:")?.toIntOrNull() ?: return

            val torneo = manejadorTorneo.obtenerTorneoPorId(id) ?: run {
                println("Torneo no encontrado.")
                return
            }
            println("Datos actuales del torneo: $torneo")

            val nuevoNombre = leerEntrada("Nuevo Nombre (presione Enter para no cambiar):").takeIf { !it.isNullOrEmpty() }
            val nuevaFecha = leerEntrada("Nueva Fecha (YYYY-MM-DD, presione Enter para no cambiar):")
                ?.takeIf { it.isNotEmpty() }
                ?.let { LocalDate.parse(it) }
            val nuevoLugar = leerEntrada("Nuevo Lugar (presione Enter para no cambiar):").takeIf { !it.isNullOrEmpty() }
            val nuevoCosto = leerEntrada("Nuevo Costo de Inscripción (presione Enter para no cambiar):")
                ?.takeIf { it.isNotEmpty() }
                ?.toDoubleOrNull()
            val nuevoActivo = leerEntrada("Nuevo Estado Activo (si/no, presione Enter para no cambiar):")
                ?.takeIf { it.isNotEmpty() }
                ?.lowercase(Locale.getDefault())
                ?.let { it == "si" }

            manejadorTorneo.actualizarTorneo(
                id,
                torneo.copy(
                    nombre = nuevoNombre ?: torneo.nombre,
                    fecha = nuevaFecha ?: torneo.fecha,
                    lugar = nuevoLugar ?: torneo.lugar,
                    costoInscripcion = nuevoCosto ?: torneo.costoInscripcion,
                    activo = nuevoActivo ?: torneo.activo
                )
            )

            // Menú para actualizar participantes
            var opcion: Int
            do {
                println("\n¿Desea actualizar participantes del torneo?")
                println("1. Actualizar participante")
                println("2. Volver al menú anterior")
                opcion = leerEntrada("Seleccione una opción:")?.toIntOrNull() ?: 2

                when (opcion) {
                    1 -> actualizarParticipante()
                    2 -> println("Volviendo al menú anterior...")
                    else -> println("Opción no válida. Intente nuevamente.")
                }
            } while (opcion != 2)

        } catch (e: Exception) {
            println("Error al actualizar torneo: ${e.message}")
        }
    }


    private fun eliminarTorneo() {
        try {
            val id = leerEntrada("\nIngrese el ID del torneo a eliminar:")?.toIntOrNull() ?: return
            manejadorTorneo.eliminarTorneo(id)
        } catch (e: Exception) {
            println("Error al eliminar torneo: ${e.message}")
        }
    }

    private fun agregarParticipante() {
        try {
            val torneoId = leerEntrada("\nIngrese el ID del torneo para agregar un participante o cero(0) para volver al menú:")?.toIntOrNull() ?: return
            val id = leerEntrada("ID del Participante:")?.toIntOrNull() ?: return
            val participante = manejadorParticipante.obtenerParticipantePorId(torneoId, id)
            if (participante != null) {
                println("Participante ya existe: $participante")
                return
            }else {
                val nombre = leerEntrada("Nombre:") ?: return
                val fechaNacimiento =
                    leerEntrada("Fecha de Nacimiento (YYYY-MM-DD):")?.let { LocalDate.parse(it) } ?: return
                val ranking = leerEntrada("Ranking:")?.toDoubleOrNull() ?: return
                val activo = leerEntrada("Activo (si/no):")?.lowercase(Locale.getDefault()) == "si"

                manejadorParticipante.agregarParticipante(
                    torneoId,
                    Participante(id, nombre, fechaNacimiento, ranking, activo)
                )
            }
        } catch (e: Exception) {
            println("Error al agregar participante: ${e.message}")
        }
    }

    private fun leerParticipantes() {
        try {
            val torneoId = leerEntrada("\nIngrese el ID del torneo para leer los participantes o cero(0) para volver al menú:")?.toIntOrNull() ?: return
            manejadorParticipante.leerParticipantes(torneoId)
        } catch (e: Exception) {
            println("Error al leer participantes: ${e.message}")
        }
    }

    private fun actualizarParticipante() {
        try {
            val torneoId = leerEntrada("\nIngrese el ID del torneo para actualizar un participante o cero(0) para volver al menú:")?.toIntOrNull() ?: return
            val participanteId = leerEntrada("ID del Participante a actualizar:")?.toIntOrNull() ?: return

            val participante = manejadorParticipante.obtenerParticipantePorId(torneoId, participanteId) ?: run {
                println("Participante no encontrado.")
                return
            }

            println("Datos actuales del participante: $participante")
            val nuevoNombre = leerEntrada("Nuevo Nombre (presione Enter para no cambiar):").takeIf { !it.isNullOrEmpty() }
            val nuevaFechaNacimiento = leerEntrada("Nueva Fecha de Nacimiento (YYYY-MM-DD, presione Enter para no cambiar):")
                ?.takeIf { it.isNotEmpty() }
                ?.let { LocalDate.parse(it) }
            val nuevoRanking = leerEntrada("Nuevo Ranking (presione Enter para no cambiar):")
                ?.takeIf { it.isNotEmpty() }
                ?.toDoubleOrNull()
            val nuevoActivo = leerEntrada("Nuevo Estado Activo (si/no, presione Enter para no cambiar):")
                ?.takeIf { it.isNotEmpty() }
                ?.lowercase(Locale.getDefault())
                ?.let { it == "si" }

            manejadorParticipante.actualizarParticipante(
                torneoId,
                participanteId,
                nuevoNombre ?: participante.nombre,
                nuevaFechaNacimiento ?: participante.fechaNacimiento,
                nuevoRanking ?: participante.ranking,
                nuevoActivo ?: participante.activo
            )
        } catch (e: Exception) {
            println("Error al actualizar participante: ${e.message}")
        }
    }

    private fun eliminarParticipante() {
        try {
            val torneoId = leerEntrada("\nIngrese el ID del torneo para eliminar un participante o cero(0) para volver al menú:")?.toIntOrNull() ?: return
            val participanteId = leerEntrada("ID del Participante a eliminar:")?.toIntOrNull() ?: return
            manejadorParticipante.eliminarParticipante(torneoId, participanteId)
        } catch (e: Exception) {
            println("Error al eliminar participante: ${e.message}")
        }
    }
}