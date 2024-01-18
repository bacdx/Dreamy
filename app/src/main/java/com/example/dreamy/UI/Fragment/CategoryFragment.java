package com.example.dreamy.UI.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.CategoryAdapter;
import com.example.dreamy.InterfaceRetrofit.CategoryInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CategoryAdapter adapter;
    private RecyclerView recyclerView;
    View view ;
    List<Category> list ;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.rcv_category);
        setRecyclerView();
        list = new ArrayList<Category>();

            getList();
        return view;
    }

//    private List<Category> getListCung() {
//        List<Category> list = new ArrayList<>();
//        list.add(new Category("1","Ao Nu","https://taoanhdep.com/wp-content/uploads/2022/08/65d0d901c19d92bded2e1a0defa3b95e_original-350x265.jpeg","kkk"));
//        list.add(new Category("2","Quan Nu","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTO0A94uzQPEKw42s3TrWP8vkmrHPpJAoaOzA&usqp=CAU","kkk"));
//        list.add(new Category("3","Vay Nu","https://pos.nvncdn.net/792dd7-10067/ps/20230524_0jiamAyCsD.jpeg","kkk"));
//        list.add(new Category("4","Do ngu","https://storage.googleapis.com/ops-shopee-files-live/live/shopee-blog/2022/01/d416d677-do-ngu-thumb.jpg","kkk"));
//        return list;
//    }



    private void getList(){
        Retrofit retrofit = RetrofitService.getClient();
        CategoryInterface categoryInterface = retrofit.create(CategoryInterface.class);
        Call<List<Category>> call = categoryInterface.getList();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    list.clear();
                    list.addAll(response.body());
                    Log.d("zzzzzzzz", "onResponse: "+list);
                    Log.d("zzzzzzzzzzzz", "onResponse: "+ response.body());
                    adapter = new CategoryAdapter(getContext(), list);

                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("RetrofitError", "onFailure: ", t);
                Toast.makeText(getActivity(), "Lỗi khi gọi API: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRecyclerView() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setItemAnimator(itemAnimator);
    }
}