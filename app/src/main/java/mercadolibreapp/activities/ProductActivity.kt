package mercadolibreapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.moises.mercadolibreapp.R
import com.moises.mercadolibreapp.model.ProductDescription
import com.moises.mercadolibreapp.model.imgSearchList
import com.moises.mercadolibreapp.service.ApiSearchImp
import com.moises.mercadolibreapp.service.ApiSearchInterface
import com.squareup.picasso.Picasso
import mercadolibreapp.tools.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private val apiSearchImp: ApiSearchImp = ApiSearchImp()
    private val apiSearchInterface: ApiSearchInterface = apiSearchImp.mainServiceCall()
    private var utils: Utils = Utils()

    private lateinit var imgView: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        tvDescription = findViewById(R.id.tvDetailDescription)
        imgView = findViewById(R.id.ivProductdetail)
        tvTitle = findViewById(R.id.tvTitleProdDetail)
        tvPrice = findViewById(R.id.tvPriceDetail)

        var id = intent.getStringExtra("id")
        tvTitle.text = intent.getStringExtra("title")
        tvPrice.text = intent.getStringExtra("price")

        getImagen(id)
        getDescription(id)


    }

    fun getImagen(id: String) {
        apiSearchInterface.getImage(id).enqueue(object : Callback<imgSearchList> {
            override fun onFailure(call: Call<imgSearchList>, t: Throwable) {
                Log.e(this@ProductActivity.getString(R.string.LOG_TAG), utils.evaluateFailure(this@ProductActivity, t))
            }
            override fun onResponse(call: Call<imgSearchList>, response: Response<imgSearchList>) {
                if (response.isSuccessful && response.code() == 200) {
                    var rta = response.body()

                    if (rta != null){
                        Picasso.get().load(rta.pictures[0].url).into(imgView)

                    }
                    Log.i(this@ProductActivity.getString(R.string.LOG_TAG), "La respuesta obtenida del servicio es: " + rta)

                } else {
                    Log.e(this@ProductActivity.getString(R.string.LOG_TAG), this@ProductActivity.getString(R.string.server_error_message))
                }
            }
        })
    }

    fun getDescription(id: String) {
        apiSearchInterface.getDescription(id).enqueue(object : Callback<ProductDescription> {
            override fun onFailure(call: Call<ProductDescription>, t: Throwable) {
                Log.e(this@ProductActivity.getString(R.string.LOG_TAG), utils.evaluateFailure(this@ProductActivity, t))
            }
            override fun onResponse(call: Call<ProductDescription>, response: Response<ProductDescription>) {
                if (response.isSuccessful && response.code() == 200) {
                    var rta = response.body()
                    if (rta != null){
                        tvDescription.text = rta.plain_text
                    }
                    Log.i(this@ProductActivity.getString(R.string.LOG_TAG), "La respuesta obtenida del servicio es: " + rta)

                } else {
                    Log.e(this@ProductActivity.getString(R.string.LOG_TAG), this@ProductActivity.getString(R.string.server_error_message))
                }
            }
        })
    }



}
