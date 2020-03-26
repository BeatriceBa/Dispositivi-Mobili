
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivimobili.Fragments.AddFilmAdapter
import com.example.dispositivimobili.Fragments.RemoveFilmAdapter
import com.example.dispositivimobili.Movie
import com.example.dispositivimobili.R
import com.example.dispositivimobili.sessionID
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.json.JSONObject
import java.net.URL


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private var movies = mutableListOf<Movie>()


    fun loadMovies(){
        movies.clear()
        Toast.makeText(activity, "Caricamento...", Toast.LENGTH_SHORT).show()
        var jsonobject = JSONObject(URL("https://movieapi.magikarp.fun/profile?session=" + sessionID).readText())
        val movie_titles = jsonobject.getJSONArray("towatch")
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

    private lateinit var btn : ImageButton

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        profile_list?.adapter?.notifyDataSetChanged()

        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        var film_List = rootView.findViewById(R.id.profile_list) as RecyclerView
        film_List.layoutManager = LinearLayoutManager(activity)
        film_List.adapter = RemoveFilmAdapter(movies)

        btn = rootView.findViewById(R.id.refresh_button)

        btn.setOnClickListener{

            if(movies.size > 0){
                movies.clear()

            }
            search_list?.adapter?.notifyDataSetChanged()
            profile_list?.adapter?.notifyDataSetChanged()
            loadMovies()
        }
        return rootView
    }




}
