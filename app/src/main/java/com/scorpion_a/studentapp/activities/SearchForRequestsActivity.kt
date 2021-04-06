package com.scorpion_a.studentapp.activities

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.scorpion_a.studentapp.R
import com.scorpion_a.studentapp.adapters.StaffRequestsListAdapter
import com.scorpion_a.studentapp.model.StaffRequestsListData
import kotlinx.android.synthetic.main.activity_profile_page.header
import kotlinx.android.synthetic.main.activity_search_for_requests.*


class SearchForRequestsActivity : AppCompatActivity(){
    lateinit var toolbar: Toolbar
    lateinit var simpleSearchView: SearchView
    lateinit var staffRequestsListData: ArrayList<StaffRequestsListData>
    lateinit var adapterSearch: StaffRequestsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_for_requests)
        toolbar=header.findViewById(R.id.toolbar)
        toolbar.title= getString(R.string.search_for_request)



        staffRequestsListData = ArrayList<StaffRequestsListData>()
        staffRequestsListData.add(
            StaffRequestsListData(
                "123"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "123"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "245"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "3567"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "44444"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "6666"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "333333"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "37252"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "12734"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "421098"
            )
        )
        staffRequestsListData.add(
            StaffRequestsListData(
                "245213"
            )
        )
        adapterSearch = StaffRequestsListAdapter(staffRequestsListData, this, "search")
        rvStaffRequestS.setHasFixedSize(true)
        rvStaffRequestS.layoutManager = LinearLayoutManager(this)
        rvStaffRequestS.adapter = adapterSearch
        etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })
//        etSearch.addTextChangedListener(object: TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                adapterSearch.filter.filter(s.toString())
//            }
//
//            override fun afterTextChanged(s: Editable?) {
////                Log.i("search", s.toString())
////                filter(s.toString())
//            }
//        })


    }
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<StaffRequestsListData> = ArrayList()

        // running a for loop to compare elements.
        for (item in staffRequestsListData) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.requestNumVal  .toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, getString(R.string.no_data_found), Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapterSearch.filterList(filteredlist)
        }
    }

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        return false
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        var text = newText!!
//        adapterSearch.filter(text)
//        return false
//    }

//    private fun filter(filterItem: String) {
//
//        var tempList: ArrayList<StaffRequestsListData> = ArrayList()
//
//        for (d in tempList) {
//
//            if (filterItem in d.requestNumVal.toString()) {
//                tempList.add(d)
//            }
//        }
//
//        adapterSearch.filterList(tempList)
//    }
}