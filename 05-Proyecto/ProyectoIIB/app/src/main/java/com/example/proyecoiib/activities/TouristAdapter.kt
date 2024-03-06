import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// La clase TouristAdapter es un adaptador para el RecyclerView que muestra datos de lugares turísticos.
class TouristAdapter(private val places: List<TouristPlace>) :
    RecyclerView.Adapter<TouristAdapter.ViewHolder>() {

    // ViewHolder mantiene referencias a las vistas dentro de cada elemento de la lista.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
    }

    // onCreateViewHolder crea nuevas instancias de ViewHolder cuando se necesitan.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla el diseño de elemento de la lista desde el archivo XML.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tourist_place, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder vincula los datos en la posición dada con las vistas dentro de ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Obtiene el lugar turístico en la posición actual.
        val place = places[position]

        // Establece los datos en las vistas correspondientes.
        holder.nameTextView.text = place.name
        holder.locationTextView.text = place.location
        holder.descriptionTextView.text = place.description
    }

    // getItemCount devuelve el número total de elementos en el conjunto de datos.
    override fun getItemCount(): Int {
        return places.size
    }
}
