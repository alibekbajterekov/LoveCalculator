package com.example.lovecalculator.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.lovecalculator.remote.LoveApi
import com.example.lovecalculator.model.LoveModel
import com.example.lovecalculator.room.local.LoveDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
class Repository @Inject constructor(private val api:LoveApi, val loveDao: LoveDao) {
    fun getLove(firstName: String, secondName: String): MutableLiveData<LoveModel> {
        var liveLove = MutableLiveData<LoveModel>()
        api.calculateLove(firstName, secondName).enqueue(
            object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    if (response.isSuccessful) {
                        liveLove.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    Log.e("ololo", "OnFailure ${t.message}")
                }
            }
        )
        return liveLove
    }
    fun historyLove(loveModel : List<LoveModel>) {
        return loveDao.insert(loveModel)
    }
}