package com.example.retrofittutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL= "https://api-basketball.p.rapidapi.com/"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getMyData()



    }

    private fun getMyData() {
        val retrofitBuilder= Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()













        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {

                val responseBody = response.body()

                val textView = findViewById<TextView>(R.id.textView)
                responseBody?.let {
                    textView.text= it[0].id.toString()
                    Log.d("Response","T")
                }




            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {

            }
        })
    }



     private fun getData(){


     lifecycleScope.launch{
         //show loader
         val result = apiInterface.getListingCategories()

         if(result.isSuccessful){
             //success
             val response = result.body()
             response?.let {
                 //
                 //list.addAll(it.data)
                 //adapter.notify
               val propertyValuesJson = it.data?.get(0)?.categoryFieldData?.get(1)?.fieldValues

                 val propertiesList = propertyValuesJson?.toArrayList<PropertyTypeModelClass>()
                 if (propertiesList != null) {
                     for (propertyData in propertiesList){
                         propertyType.add(propertyData.name)
                     }
                 }

                 val termsValueJson =  it.data?.get(0)?.categoryFieldData?.get(10)?.fieldValues
                 val termsList = termsValueJson?.toArrayList<PropertyTypeModelClass>()
                 if (termsList != null) {
                     for (termsData in termsList){
                         terms.add(termsData.name)
                     }
                 }


             setUpSpinner()

                 showToast(requireContext(),propertyType.size.toString())

             }

         }else{
             //error
             //show error msg
             showToast(requireContext(),"Error")
         }
     }

 }
 }


}