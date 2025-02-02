package com.example.unifynd_test.ui.homescreen.tabs.tab2

import com.example.unifynd_test.model.Country
import com.example.unifynd_test.utils.AppConstants
import org.json.JSONArray
import org.json.JSONObject

class Tab2Presenter(private val view: Tab2Contract.View) : Tab2Contract.Presenter(view) {

    override fun onHeaderViewCreated(jsonString: String) {
        view.loadHeaders(addItemsFromJSON(jsonString))
    }

    override fun onGridViewCreated(readJSONDataFromFile: String) {
        view.loadGridView(addItemsFromJSON(readJSONDataFromFile))
    }

    private fun addItemsFromJSON(jsonDataString: String): MutableList<Country> {
        val countryList = mutableListOf<Country>()
        try {
            val jsoObj = JSONObject(jsonDataString)
            val result = jsoObj.getString(AppConstants.RESULT)
            val jsonArray = JSONArray(result)
            for (i in 0 until jsonArray.length()) {
                val itemObj = jsonArray.getJSONObject(i)
                val countryName = itemObj.getString(AppConstants.COUNTRY_NAME)
                val capitalName = itemObj.getString(AppConstants.COUNTRY_CAPITAL)
                val country = Country(countryName, capitalName)
                countryList.add(country)
            }
        } catch (e: Exception) {

        }
        return countryList
    }

}