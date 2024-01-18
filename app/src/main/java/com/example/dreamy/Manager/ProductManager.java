package com.example.dreamy.Manager;

import android.content.Context;

import com.example.dreamy.Database.SQL.Dao.CartDAO;
import com.example.dreamy.Database.SQL.Dao.FavoriteDAO;
import com.example.dreamy.InterfaceRetrofit.ColorInterface;
import com.example.dreamy.InterfaceRetrofit.ProductsInterface;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.InterfaceRetrofit.SizeInterface;
import com.example.dreamy.Model.Cart;
import com.example.dreamy.Model.Category;
import com.example.dreamy.Model.Color;
import com.example.dreamy.Model.Product;
import com.example.dreamy.Model.ProductTop;
import com.example.dreamy.Model.Size;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductManager  {

    private   static  ProductManager Instance;
    private TypeProductManage typeProductManage=TypeProductManage.getInstance();
    private final PropertyChangeSupport pys= new PropertyChangeSupport(this);
    private ArrayList<Product> allProduct;
    private  Retrofit retrofit;
   private ProductsInterface productsInterface;
   private SizeInterface sizeInterface;
   private ColorInterface colorInterface;
    private ArrayList<Product> products=new ArrayList<Product>();
    private  ArrayList<Product> favoriteList;
    private  ArrayList<Product> topList;
    private  ArrayList<Product> cartList;
    private FavoriteDAO favoriteDAO;
    private CartDAO cartDAO;
    private  Context context;



    private ProductManager(Context context) {
        favoriteDAO=new FavoriteDAO(context);
         retrofit= RetrofitService.getClient();
         productsInterface=retrofit.create(ProductsInterface.class);
         sizeInterface=retrofit.create(SizeInterface.class);
         colorInterface=retrofit.create((ColorInterface.class));
         allProduct=new ArrayList<>();
         allProduct.add(new Product("-1"));
         favoriteList=new ArrayList<>();
         topList=new ArrayList<>();
         this.context=context;


    }
    public void addListener(PropertyChangeListener listener){
        pys.addPropertyChangeListener(listener);
    }
    public void removeListener(PropertyChangeListener listener){
        pys.removePropertyChangeListener(listener);
    }


    public static ProductManager getInstance(Context context) {
        if(Instance==null){
            Instance=new ProductManager(context);
            Product product=new Product();
            ArrayList<Product> list=new ArrayList<>();
            ArrayList<Product> list1=new ArrayList<>();
            list.add(product);
            list1.add(list.get(0));
            product.setId("1");
            list1.get(0).getId();
        }
        return Instance;
    }
    public static ProductManager getInstance( ) {
        if(Instance==null){
            Product product=new Product();
            ArrayList<Product> list=new ArrayList<>();
            ArrayList<Product> list1=new ArrayList<>();
            list.add(product);
            list1.add(list.get(0));
            product.setId("1");
            list1.get(0).getId();
        }
        return Instance;
    }


     public void getProducts(String id) {

        Call<Product> productCall=productsInterface.getProductByID(id);
        productCall.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
               insertProduct(response.body());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }

    public ArrayList<Product> getProductsByMaSP(String loaiSp) {
        Call<ArrayList<Product>> call=productsInterface.getList(loaiSp);
        call.enqueue(  new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                products=response.body();
                pys.firePropertyChange("productById",null,products);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                products=null;
            }


        });
        return products;
    }
    public Product insertProduct(Product product){
        // kiem tra list co product hay ko neu ko them vao
        int check=-1;
        for(int i=0;i<allProduct.size();i++){
            if(allProduct.get(0).getId()==product.getId()){
                check=1;
                return allProduct.get(i);
            }
        }
        if (check!=1){
            allProduct.add(product);
        }
        return product;

    }

    public void getListFavorite(){
        ArrayList<Integer> listID= favoriteDAO.select();

        Call<ArrayList<Product>> productCall= productsInterface.getProductByIDs(listID);
        productCall.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                response.headers();
                favoriteList=response.body();
                for (int i = 0; i <favoriteList.size() ; i++) {
                    insertProduct(favoriteList.get(i));
                }
                pys.firePropertyChange("fav",null,null);
            }


            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });

    }
    public void addFavorrite(Product product){
       favoriteDAO.insert(Integer.valueOf(product.getId()));

    }
    public boolean checkFavor(Product product){
        ArrayList<Integer> listID= favoriteDAO.select();
        for (int i=0;i<listID.size();i++){
         if (product.getId().equals(String.valueOf(listID.get(i)))){
             return true;
         }
        }
        return false;

    }

    public ArrayList<Product> getProductsMaLoai(String maLoai) {

        // so sanh tung phan tu cua list sp theo loai vois list tong
        // neu trong listAll ko co add them
        //-> get list
      Category category=typeProductManage.getCategoryById(maLoai);
      ArrayList<Product> list=category.getList();
      int count =-1;
      if (list==null|| list.size()<=0){
          // call Api
      }

        for (int i=0;i<list.size();i++){
            Product product=list.get(i);
            for (int j =0;j<allProduct.size();j++){
                Product product1 =allProduct.get(j);
                if (product1.getId().equals(product.getId())){
                    count=1;
                    break;
                }
            }
            if (count!=1){
                allProduct.add(product);
                count=-1;
            }
        }
        return list;
    }

    public ArrayList<Product> getTopProducts() {
        // so sanh tung phan tu cua list sp theo loai vois list tong
        // neu trong listAll ko co add them
        //-> get list

        int count =-1;
        if (topList==null|| topList.size()<=0){
            Call<ArrayList<Product>> call=productsInterface.getTopList();
            call.enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    topList=response.body();

                    for (int i=0; i<topList.size();i++){
                        Product product=topList.get(i);
                        insertProduct(product);
                    }
                    pys.firePropertyChange("top",null,null);

                }

                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                }
            });
        }


        return topList;
    }






    public  void getRandomList(String id) {

        new Thread(new Runnable() {
            @Override
            public void run() {
// so sanh tung phan tu cua list sp theo loai vois list tong
                // neu trong listAll ko co add them
                //-> get list

                int count =-1;
                if (products==null|| products.size()<=0){
                    // call Api

                    Call<ArrayList<Product>> call=productsInterface.getListHome(id);
                    try {
                        Response<ArrayList<Product>> response=call.execute();
                        products=response.body();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

                for (int i=0;i<products.size();i++){
                    Product product=products.get(i);
                    for (int j=0;j<allProduct.size();j++){
                        Product product1 =allProduct.get(j);
                        if (product1.getId().equals(product.getId())){
                            count=1;
                            break;
                        }
                    }
                    if (count!=1){
                        allProduct.add(product);
                        count=-1;
                    }
                }
                pys.firePropertyChange("allProduct",null,products);

            }
        }).start();

    }

    public void getSize(Product product){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (product.getSizes()==null){
                    Call<ArrayList<Size>> call=sizeInterface.getSizes(product.getId());
                    try {
                        Response<ArrayList<Size>> response=call.execute();
                        product.setSizes(response.body());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                pys.firePropertyChange("size",null,null);
            }
        }).start();

    }

    public void getColor(Product product){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (product.getColors()==null){
                    Call<ArrayList<Color>> call=colorInterface.getColor(product.getId());
                    try {
                        Response<ArrayList<Color>> response=call.execute();
                        product.setColors(response.body());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                pys.firePropertyChange("color",null,null);
            }
        }).start();

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void getProduct(String id) {
        Call<Product> call=productsInterface.getProductByID(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product =response.body();
                pys.firePropertyChange("danhgiasp",null,product);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }

    private void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(ArrayList<Product> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public ArrayList<Product> getTopList() {
        return topList;
    }

    public void setTopList(ArrayList<Product> topList) {
        this.topList = topList;
    }
}
