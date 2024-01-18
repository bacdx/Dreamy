package com.example.dreamy.UI.Fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dreamy.InterfaceRetrofit.IUser;
import com.example.dreamy.InterfaceRetrofit.RetrofitService;
import com.example.dreamy.Manager.ProfileManager;
import com.example.dreamy.Model.User;
import com.example.dreamy.MyApplication;
import com.example.dreamy.R;
import com.example.dreamy.UI.Activity.FeedBackActivity;
import com.example.dreamy.UI.Activity.LoginActivity;
import com.example.dreamy.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    private  ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    User gUser;
    private byte[] bytes;
    private ImageView imgAvatar,imgChangeAvatar;
    private EditText edEmail,edName,edSDT;
    private AppCompatButton btnSave;
    private View.OnClickListener onClickChangeImg=new View.OnClickListener() {
        @Override
        public void onClick(View view) {


// Include only one of the following calls to launch(), depending on the types
// of media that you want to let the user choose from.

// Launch the photo picker and let the user choose images and videos.


// Launch the photo picker and let the user choose only images.
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());


        }
    };
    private View.OnClickListener saveOnclick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            postData();
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gUser= ProfileManager.getInstant().getMyAccount();

            pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        bytes=getByteToURI(uri);



                        Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                       imgAvatar.setImageBitmap(Bitmap.createScaledBitmap(bitmap,100,100,false));
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        gUser= ProfileManager.getInstant().getMyAccount();
        View viewRoot=binding.getRoot();
        imgAvatar=viewRoot.findViewById(R.id.img_avatar_fragmentProfile);
        imgChangeAvatar=viewRoot.findViewById(R.id.img_changeAvatar_fragmentProfile);
        edEmail=viewRoot.findViewById(R.id.ed_email_frgProfile);
        edName=viewRoot.findViewById(R.id.ed_name_frgProfile);
        edSDT=viewRoot.findViewById(R.id.ed_sdt_frgProfile);
        btnSave=viewRoot.findViewById(R.id.btn_save_frgProfile);
        btnSave.setOnClickListener(saveOnclick);

        setImg();

        edEmail.setText(gUser.getEmail());
        edName.setText(gUser.getName());
        edSDT.setText(gUser.getNumberphone());

        imgChangeAvatar.setOnClickListener(onClickChangeImg);
//        binding.btnDanhGia.setOnClickListener(view -> {
//            startActivity(new Intent(requireActivity(), FeedBackActivity.class));
//        });
        binding.btnDangXuat.setOnClickListener(view ->{
            startActivity(new Intent(requireActivity(), LoginActivity.class));
            
        });

        return viewRoot;
    }
    private byte[] getByteToURI(Uri uri) {
        byte[] bytes;
        ContentResolver contentResolver = getContext().getContentResolver();


        try {
         InputStream inputStream= contentResolver.openInputStream(uri);
         bytes=new byte[inputStream.available()];
        inputStream.read(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            return bytes;
    }
    private void postData(){
        String base64=Base64.encodeToString(bytes,Base64.DEFAULT);
        User data;
        if(bytes==null||bytes.length<=0){
             data=new User(gUser.getId(),
                    edName.getText().toString(),
                    edEmail.getText().toString(),
                    gUser.getPassword(),
                    edSDT.getText().toString(),
                    gUser.getAvatar());
        }
        else {
             data=new User(gUser.getId(),
                    edName.getText().toString(),
                    edEmail.getText().toString(),
                    gUser.getPassword(),
                    edSDT.getText().toString(),
                    base64);

        }


       if(validate()==true){
           callAPI(data);
       }
    }
    private boolean validate(){
        return true;
    }

    private void callAPI(User user){
        Retrofit retrofit= RetrofitService.getClient();
        IUser iUser=retrofit.create(IUser.class);
        Call<User> call =iUser.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ProfileManager.getInstant().setMyAccount(response.body());
                gUser=ProfileManager.getInstant().getMyAccount();
                setImg();
                Toast.makeText(getContext(),"Thay Doi Thanh Cong",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    private void setImg(){
        String base64Image = gUser.getAvatar();
        byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);
        imgAvatar.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
    }

}