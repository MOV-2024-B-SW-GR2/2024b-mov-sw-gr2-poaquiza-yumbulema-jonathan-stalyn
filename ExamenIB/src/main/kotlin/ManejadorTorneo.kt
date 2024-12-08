class ManejadorTorneo {

    fun crearTorneo(torneo: Torneo) {
        val torneos = ManejadorArchivo.cargarTorneos()
        torneos.add(torneo)
        ManejadorArchivo.guardarTorneos(torneos)
        println("Torneo creado: $torneo")
    }

    fun leerTorneos() {
        val torneos = ManejadorArchivo.cargarTorneos()
        if (torneos.isEmpty()) {
            println("No hay torneos disponibles.")
        } else {
            println("\n===== Lista de Torneos Actualizada =====")
            torneos.forEach { println(it) }
        }
    }

    fun actualizarTorneo(id: Int, nuevoTorneo: Torneo) {
        val torneos = ManejadorArchivo.cargarTorneos()
        val indice = torneos.indexOfFirst { it.id == id }
        if (indice != -1) {
            torneos[indice] = nuevoTorneo
            ManejadorArchivo.guardarTorneos(torneos)
            println("Torneo actualizado: $nuevoTorneo")
        } else {
            println("Torneo con ID $id no encontrado.")
        }
    }

    fun eliminarTorneo(id: Int) {
        val torneos = ManejadorArchivo.cargarTorneos()
        if (torneos.removeIf { it.id == id }) {
            ManejadorArchivo.guardarTorneos(torneos)
            println("Torneo con ID $id eliminado.")
        } else {
            println("Torneo con ID $id no encontrado.")
        }
    }

    fun obtenerTorneoPorId(id: Int): Torneo? {
        val torneos = ManejadorArchivo.cargarTorneos()
        return torneos.find { it.id == id }
    }

}