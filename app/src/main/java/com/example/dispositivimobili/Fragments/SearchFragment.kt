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
import com.example.dispositivimobili.Fragments.FilmAdapter
import com.example.dispositivimobili.Movie
import com.example.dispositivimobili.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.json.JSONObject
import java.net.URL


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    fun searchMovies( par : String ){
        movies.clear()
        var jsonobject = JSONObject(URL("https://movieapi.magikarp.fun/search?query=" + par).readText())
        val movie_titles = jsonobject.getJSONArray("results")
        for( i in 0 until movie_titles.length()){
            val movieobj = movie_titles.getJSONObject(i)
            val movie_name = movieobj.getString("title")
            val movie_desc = movieobj.getString("overview")
            val movie_id = movieobj.getString("id")
            val movie_url = movieobj.getString("poster_path").replace("\\","")
            movies.add(Movie(movie_name,movie_desc,movie_url,movie_id))
            search_list?.adapter?.notifyDataSetChanged()
        }
    }

    private var movies = mutableListOf<Movie>()
    private lateinit var btn : ImageButton

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        var film_List = rootView.findViewById(R.id.search_list) as RecyclerView
        var parameter = rootView.findViewById(R.id.titleSearch) as TextView
        film_List.layoutManager = LinearLayoutManager(activity)
        film_List.adapter = AddFilmAdapter(movies)
        btn = rootView.findViewById(R.id.search_button)

        btn.setOnClickListener{
            if(movies.size > 0){
                movies.clear()
                search_list?.adapter?.notifyDataSetChanged()
                profile_list?.adapter?.notifyDataSetChanged()
            }
            searchMovies(parameter.text.toString())
            closeKeyboard()
        }
        return rootView
    }

    private fun closeKeyboard() {
        val activity = activity
        val view = activity?.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }



}
