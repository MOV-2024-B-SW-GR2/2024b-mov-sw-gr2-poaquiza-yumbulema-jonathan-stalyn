import java.time.LocalDate

class ManejadorParticipante {
    fun agregarParticipante(torneoId: Int, participante: Participante) {
        val torneos = ManejadorArchivo.cargarTorneos()
        val torneo = torneos.find { it.id == torneoId }
        if (torneo != null) {
            torneo.participantes.add(participante)
            ManejadorArchivo.guardarTorneos(torneos)
            println("Participante agregado: $participante")
        } else {
            println("Torneo con ID $torneoId no encontrado.")
        }
    }
    fun leerParticipantes(torneoId: Int) {
        val torneos = ManejadorArchivo.cargarTorneos()
        val torneo = torneos.find { it.id == torneoId }
        if (torneo != null) {
            if (torneo.participantes.isNotEmpty()) {
                println("Participantes del Torneo ${torneo.nombre}:")
                torneo.participantes.forEach { participante ->
                    println("ID: ${participante.id}, Nombre: ${participante.nombre}, Fecha de Nacimiento: ${participante.fechaNacimiento}, Ranking: ${participante.ranking}, Activo: ${participante.activo}")
                }
            } else {
                println("No hay participantes registrados en el torneo ${torneo.nombre}.")
            }
        } else {
            println("Torneo con ID $torneoId no encontrado.")
        }
    }

    fun actualizarParticipante(torneoId: Int, participanteId: Int, nombre: String, fechaNacimiento: LocalDate, ranking: Double, activo: Boolean) {
        val torneos = ManejadorArchivo.cargarTorneos()
        val torneo = torneos.find { it.id == torneoId }
        if (torneo != null) {
            var participante = torneo.participantes.find { it.id == participanteId }
            if (participante != null) {
                participante.nombre = nombre
                participante.fechaNacimiento = fechaNacimiento
                participante.ranking = ranking
                participante.activo = activo
                ManejadorArchivo.guardarTorneos(torneos)
                println("Participante con ID $participanteId actualizado.")
            } else {
                println("Participante con ID $participanteId no encontrado.")
            }
        } else {
            println("Torneo con ID $torneoId no encontrado.")
        }
    }

    fun eliminarParticipante(torneoId: Int, participanteId: Int) {
        val torneos = ManejadorArchivo.cargarTorneos()
        val torneo = torneos.find { it.id == torneoId }
        if (torneo != null) {
            if (torneo.participantes.removeIf { it.id == participanteId }) {
                ManejadorArchivo.guardarTorneos(torneos)
                println("Participante con ID $participanteId eliminado.")
            } else {
                println("Participante con ID $participanteId no encontrado.")
            }
        } else {
            println("Torneo con ID $torneoId no encontrado.")
        }
    }

    fun obtenerParticipantePorId(torneoId: Int, participanteId: Int): Participante? {
        val torneos = ManejadorArchivo.cargarTorneos()
        val torneo = torneos.find { it.id == torneoId }
        return torneo?.participantes?.find {
            it.id == participanteId
        }
    }
}