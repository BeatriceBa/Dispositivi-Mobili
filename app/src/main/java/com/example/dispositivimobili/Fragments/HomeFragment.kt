import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivimobili.Fragments.FilmAdapter
import com.example.dispositivimobili.Movie
import com.example.dispositivimobili.R
import org.json.JSONObject
import java.net.URL

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    fun loadMovies(){
        var jsonobject = JSONObject(URL("https://movieapi.magikarp.fun/movies").readText())
        val movie_titles = jsonobject.getJSONArray("results")
        for( i in 0 until movie_titles.length()){
                val movieobj = movie_titles.getJSONObject(i)
                var movie_name = movieobj.getString("title")
                var movie_desc = movieobj.getString("overview")
                var movie_id = movieobj.getString("id")
                if (movie_desc == "") {
                    movie_desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
                }
                val movie_url = movieobj.getString("poster_path").replace("\\","")
                movies.add(Movie(movie_name,movie_desc,movie_url,movie_id))
        }
    }

    private var movies = mutableListOf<Movie>()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        loadMovies()
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        var film_List = rootView.findViewById(R.id.filmList) as RecyclerView
        film_List.layoutManager = LinearLayoutManager(activity)
        film_List.adapter = FilmAdapter(movies)
        return rootView
    }


}
