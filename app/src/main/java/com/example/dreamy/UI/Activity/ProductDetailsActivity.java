package com.example.dreamy.UI.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dreamy.Controller.CommentController;
import com.example.dreamy.Database.SQL.Dao.FavoriteDAO;
import com.example.dreamy.InterfaceRetrofit.ProductsInterface;
import com.example.dreamy.Manager.CommentManager;
import com.example.dreamy.Manager.ProductManager;
import com.example.dreamy.Model.Color;
import com.example.dreamy.Model.Comment;
import com.example.dreamy.Model.ProductDetal;
import com.example.dreamy.ProductDetalController;
import com.example.dreamy.R;
import com.example.dreamy.UI.Adapter.ColorAdapter;
import com.example.dreamy.UI.Adapter.CommentAdapter;
import com.example.dreamy.UI.Adapter.ProductAdapter;
import com.example.dreamy.UI.Fragment.ImageSlideFragment;
import com.example.dreamy.InterfaceRetrofit.ColorInterface;
import com.example.dreamy.InterfaceRetrofit.ImgProductInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.InterfaceRetrofit.SizeInterface;
import com.example.dreamy.Model.ImageProduct;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.Size;
import com.example.dreamy.UI.Dialog.BotomSheetDiaLog;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailsActivity extends AppCompatActivity implements PropertyChangeListener , ProductDetalController.Res {
    //tool
    DecimalFormat df = new DecimalFormat("#.##");
    public static final int BYLOAI=0;
    public static final int BYNSX=1;


    // view
private TextView title;
private Button btnOpenBottomSheet;
private RatingBar ratingBar;
    private ViewPager2 mViewPager2;
    private ImageView btnAddCart;
    private RecyclerView mRecyclerViewColor;
    private RecyclerView mRecyclerViewSize;
    private RecyclerView mRecyclerViewlistProduct;
    private RecyclerView mRecyclerViewlistProductByKind;
    private RecyclerView recyclerViewComment;
    private CircleIndicator3 mCircleIndicator3;
    private TextView tvName,tvPrice;
    private AppCompatImageView imgFov;
    private  AdapterImageSlide adapterImageSlide;

    private CommentAdapter commentAdapter;
    private CommentController commentController;
    private ProductManager productManager;

    private List<ImageProduct> mPhotoList;

    private Product product;
    private FavoriteDAO favoriteDAO;
    private ColorAdapter adapterColor;
    private AdapterSize adapterSize;

    BotomSheetDiaLog fragobj ;
    private Handler handler = new Handler(Looper.getMainLooper());
    private  Retrofit service=RetrofitService.getClient();
    private CommentManager commentManager;


    FragmentManager fragmentManager = getSupportFragmentManager();
    LinearLayout bottomSheet;
    View.OnClickListener addCardOnClick= new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            fragobj=new BotomSheetDiaLog(BotomSheetDiaLog.TYPE_CART);
            Bundle bundle=new Bundle();

            bundle.putSerializable("Product",product );
            fragobj.setArguments(bundle);
            fragobj.show(fragmentManager,"bottom_sheet_addCart");


        }
    };
    View.OnClickListener btnBottonSheetOnClick=new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            fragobj=new BotomSheetDiaLog(BotomSheetDiaLog.TYPE_BUY);

                Bundle bundle=new Bundle();

                bundle.putSerializable("Product",product );
                fragobj.setArguments(bundle);
                fragobj.show(fragmentManager,"bottom_sheet");



        }
    };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent=getIntent();
        product=(Product) intent.getSerializableExtra("Product");
        init();
        setlistProduct(BYLOAI,mRecyclerViewlistProduct);
        setlistProduct(BYNSX,mRecyclerViewlistProductByKind);

        commentManager=CommentManager.getInstance();
        favoriteDAO =new FavoriteDAO(this);
        //setup  productManager
        productManager=ProductManager.getInstance(this);
        productManager.addListener(this);
        commentManager.addListener(this);
        productManager.getColor(product);
        productManager.getSize(product);
        commentManager.getCommentbySP(product);
//        getProduct();

        btnAddCart.setOnClickListener(addCardOnClick);
        if (productManager.checkFavor(product)){
            imgFov.setBackgroundResource(R.drawable.heart);
        }else {
            imgFov.setBackgroundResource(R.drawable.icon_heart_outline);
        }
        imgFov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productManager.checkFavor(product)){
                  favoriteDAO.delete(Integer.valueOf(product.getId()));
                    imgFov.setBackgroundResource(R.drawable.icon_heart_outline);

                }else {
                    imgFov.setBackgroundResource(R.drawable.heart);
                    favoriteDAO.insert(Integer.valueOf(product.getId()));
                }
            }
        });
        btnOpenBottomSheet.setOnClickListener(btnBottonSheetOnClick);
        commentController=new CommentController();
        // get list comment
        commentManager.getCommentbySP(product);
        setUI();


        //shoppingcart




        setRcyComment();

//            getImg();

//        fragmentManager.beginTransaction()
//                .replace(R.id.title_fragment, fragobj, null)
//                .setReorderingAllowed(true)
//                .addToBackStack("name") // Name can be null
//                .commit();
        title=findViewById(R.id.title);
//        title.setText(product.set);
        //setImg
        setlistImgs();
        ImageView imageView;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {}
//            Base64.getEncoder().encode()

        //setcolor
        adapterColor = new ColorAdapter();
        setColor();
        //setSize
        adapterSize=new AdapterSize();
        setSize();
//        set list sp cung loai


        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);

            }
        });



        adapterColor.setOnClickListener(new ColorAdapter.OnClickListener() {
            @Override
            public void onClick(int position, Color color) {
                int pos =mPhotoList.size()-1;
                mPhotoList.add(new ImageProduct(color.getImg()));
                adapterImageSlide.createFragment(pos);
                adapterImageSlide.notifyDataSetChanged();
                mViewPager2.setCurrentItem(pos);

            }
        });

        mRecyclerViewColor.setAdapter(adapterColor);
        mRecyclerViewSize.setAdapter(adapterSize);





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productManager.removeListener(this);
        commentManager.removeListener(this);
    }

    private void init(){
        ratingBar=findViewById(R.id.rb);
        mViewPager2 = findViewById(R.id.view_pager2);
        mCircleIndicator3 = findViewById(R.id.circle_indicator3);
        mRecyclerViewColor = findViewById(R.id.rcvColor);
        mRecyclerViewSize = findViewById(R.id.rcvsize);
        mRecyclerViewlistProduct=findViewById(R.id.rcv_listsp);
        mRecyclerViewlistProductByKind=findViewById(R.id.rcv_listsp1);
        recyclerViewComment=findViewById(R.id.rcvComment_productActivity);
        imgFov=findViewById(R.id.fav);
        tvName=findViewById(R.id.tvName);
        tvPrice=findViewById(R.id.tvPrice);
        btnOpenBottomSheet=findViewById(R.id.btnMua);
        btnAddCart=findViewById(R.id.img_cart_productDetal);

    }
    private void setlistProduct(int loai,RecyclerView recyclerView){
        Retrofit retrofit=RetrofitService.getClient();
        ProductsInterface productsInterface=retrofit.create(ProductsInterface.class);
        LinearLayoutManager layoutManager=new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if(loai==BYLOAI){
           Call<ArrayList<Product>> call=productsInterface.getList(product.getMaloai());
            call.enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    recyclerView.setLayoutManager(layoutManager);
                    ProductAdapter productAdapter=new ProductAdapter(ProductDetailsActivity.this,response.body());
                    recyclerView.setAdapter(productAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                }
            });
        }else {
            Call<ArrayList<Product>> call=productsInterface.getListbyMaNhaSanSuat(product.getManhasanxuat());
            call.enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    recyclerView.setLayoutManager(layoutManager);
                    ProductAdapter productAdapter=new ProductAdapter(ProductDetailsActivity.this,response.body());
                    recyclerView.setAdapter(productAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                }
            });
        }


    }


    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if(propertyChangeEvent.getPropertyName().equals("commentByID")){


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    commentAdapter.setList(product.getComments());
                }
            });

        }
        if(propertyChangeEvent.getPropertyName().equals("productDetal")){

        }

        if(propertyChangeEvent.getPropertyName().equals("size")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapterSize.setSizes(product.getSizes());
                }
            });


        }
        if(propertyChangeEvent.getPropertyName().equals("color")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapterColor.setColors(product.getColors());
                }
            });

        }
    }

    @Override
    public void error(String t) {

    }

    @Override
    public void resOK(ArrayList<ProductDetal> list) {


    }


    public class AdapterImageSlide extends FragmentStateAdapter {

        private List<ImageProduct> photoList;

        public AdapterImageSlide(FragmentActivity fragmentActivity, List<ImageProduct> list) {
            super(fragmentActivity);
            this.photoList = list;
        }


        @Override
        public Fragment createFragment(int position) {

            ImageProduct photo = photoList.get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("photo",photo);

            ImageSlideFragment imageSlideFragment = new ImageSlideFragment();
            imageSlideFragment.setArguments(bundle);

            return imageSlideFragment;
        }

        @Override
        public int getItemCount() {
            if (photoList!=null){
                return photoList.size();
            }
            return 0;
        }
    }



    private void setlistImgs(){
        ImgProductInterface imgProductInterface=service.create(ImgProductInterface.class);
        Call<List<ImageProduct>> call=imgProductInterface.getImgProduct(product.getId());

        call.enqueue(new Callback<List<ImageProduct>>() {
            @Override
            public void onResponse(Call<List<ImageProduct>> call, Response<List<ImageProduct>> response) {
                if (response.isSuccessful()){
                    mPhotoList=response.body();
                    adapterImageSlide = new AdapterImageSlide(ProductDetailsActivity.this,mPhotoList);
                    mViewPager2.setAdapter(adapterImageSlide);
                }



            }

            @Override
            public void onFailure(Call<List<ImageProduct>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG);
            }
        });
    }

    private  void setSize(){

    }
    private  void setColor(){


    }

private void setRcyComment(){
        if(commentController.isReadyData()){

        }else {
            commentAdapter=new CommentAdapter(ProductDetailsActivity.this, commentController.getList());
        }
    commentAdapter=new CommentAdapter(ProductDetailsActivity.this);
    recyclerViewComment.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    recyclerViewComment.setHasFixedSize(true);
    LinearLayoutManager layoutManager=new LinearLayoutManager(ProductDetailsActivity.this);
        recyclerViewComment.setLayoutManager(layoutManager);

        recyclerViewComment.setAdapter(commentAdapter);

}

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mViewPager2.getCurrentItem();
            if (currentPosition == mPhotoList.size()-1){
                mViewPager2.setCurrentItem(0);
            }else {
                mViewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };


    public class AdapterSize extends RecyclerView.Adapter<AdapterSize.ViewHolder>{
        List<Size> sizes=new ArrayList<>();

        public List<Size> getSizes() {
            return sizes;
        }

        public void setSizes(List<Size> sizes){
            this.sizes = sizes;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_size,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Size size=sizes.get(position);
            holder.view.setText(size.getTitle());
        }

        @Override
        public int getItemCount() {
            if (sizes != null) {
                return sizes.size();
            }
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private TextView view;

            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView.findViewById(R.id.bntXs);
            }
        }

    }
    public void getImg(){
        ActivityResultLauncher<PickVisualMediaRequest> pickMultipleMedia =
                registerForActivityResult(new ActivityResultContracts.PickMultipleVisualMedia(5), uris -> {
                    // Callback is invoked after the user selects media items or closes the
                    // photo picker.
                    if (!uris.isEmpty()) {
                        Log.d("PhotoPicker", "Number of items selected: " + uris.size());
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });

// For this example, launch the photo picker and let the user choose images
// and videos. If you want the user to select a specific type of media file,
// use the overloaded versions of launch(), as shown in the section about how
// to select a single media item.
        pickMultipleMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE)
                .build());
    }


    private void  getProduct(){
        Retrofit retrofit=RetrofitService.getClient();
        ProductsInterface productsInterface=retrofit.create(ProductsInterface.class);
        Call<Product> productCall=productsInterface.getProductByID(product.getId());
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product=response.body();
                setlistProduct(BYLOAI,mRecyclerViewlistProduct);
                setlistProduct(BYNSX,mRecyclerViewlistProductByKind);

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
    private void setUI(){
        tvName.setText(product.getTen());
        tvPrice.setText(df.format(product.getGia())+ "VND");
        ratingBar.setRating(product.getDanhGia());

    }
    private void setFavorite(String masp){
        Retrofit retrofit=RetrofitService.getClient();
        ProductsInterface productsInterface=retrofit.create(ProductsInterface.class);
        Call<Integer> call=productsInterface.changeFavorite(masp);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.body()==0){

                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }

}