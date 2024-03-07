package com.example.locationtrack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.locationtrack.databinding.ActivityFirestoreBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FirestoreActivity extends AppCompatActivity {
    ActivityFirestoreBinding binding;
    FirebaseFirestore firestore;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth,mAuth;
    BeginSignInRequest signInRequest;
    FirebaseUser user;
     CallbackManager mCallbackManager;
    String data;
    LoginManager loginManager;
    private static final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFirestoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firestore=FirebaseFirestore.getInstance();

        FacebookSdk.sdkInitialize(getApplicationContext());







//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("com.example.locationtrack", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
       // binding.fbButton.setReadPermissions(Arrays.asList(EMAIL));
        binding.fbButton.setPermissions("email", "public_profile");
      //  FacebookSdk.sdkInitialize(getApplicationContext());
   //     AppEventsLogger.activateApp(this);
      //  Log.d("Accesstoken", "onCreate: "+  AccessToken.getCurrentAccessToken());
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(FirestoreActivity.this, "Successsfulllyyy", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        binding.fbButton.setOnClickListener(v -> {
            LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile"));
        });
      //  faceBookMeth();

        binding.save.setOnClickListener(v -> {
            data=binding.name.getText().toString();
            if(TextUtils.isEmpty(data)){
                Toast.makeText(this, "Fill the data", Toast.LENGTH_SHORT).show();
            }else{
                SaveDataInFireStore(data);
          }
        });

//        googleDev();
    }
    public void faceBookMeth(){
        binding.fbButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("successMode", "onSuccess: "+loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("Cancel", "onCancel: ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("error", "onError: "+error);
            }
        });


    }
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("handleFacbook", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("handleFacbook1", "signInWithCredential:success");
                             user = mAuth.getCurrentUser();
                              Intent intent =new Intent(FirestoreActivity.this, MainActivity.class);
                              startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("handleFacbook2", "signInWithCredential:failure", task.getException());
                            Toast.makeText(FirestoreActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }
                    }
                });
    }
//    public void googleDev(){
//        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("123098627167-je7qpr3gekv23ftkg5moatg6ok8d52jk.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//
//        googleSignInClient= GoogleSignIn.getClient(FirestoreActivity.this,googleSignInOptions);
//
//        binding.logWithGoogle.setOnClickListener(v -> {
//            Intent intent= googleSignInClient.getSignInIntent();
//            startActivityForResult(intent,100);
//        });
//        firebaseAuth= FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
//
//        if(firebaseUser!=null){
//            startActivity(new Intent(FirestoreActivity.this, MainActivity.class));
//        }
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==100){
//            Task<GoogleSignInAccount> signInAccountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
//            if(signInAccountTask.isSuccessful()){
//                Toast.makeText(this, "Successfully ", Toast.LENGTH_SHORT).show();
//                try {
//                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
//                    // Check condition
//                    if (googleSignInAccount != null) {
//                        // When sign in account is not equal to null initialize auth credential
//                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
//                        // Check credential
//                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                if (task.isSuccessful()) {
//
//                                    startActivity(new Intent(FirestoreActivity.this, MainActivity.class));
//                                    Toast.makeText(FirestoreActivity.this, "Firebase authentication successful", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(FirestoreActivity.this, "Authentication Failed :", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                } catch (ApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
      //  else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
    //    }
    }
    private void SaveDataInFireStore(String name){
          CollectionReference dbCourses = firestore.collection("Courses");
          FireStoreModel courses = new FireStoreModel();
          courses.setSubject(name);
            dbCourses.add(courses)
                  .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                      @Override
                      public void onSuccess(DocumentReference documentReference) {
                          Toast.makeText(FirestoreActivity.this, "add ", Toast.LENGTH_SHORT).show();

                      }

      })
            .addOnCanceledListener(new OnCanceledListener() {
                      @Override
                      public void onCanceled() {
                          Toast.makeText(FirestoreActivity.this, "Nottt", Toast.LENGTH_SHORT).show();
                      }
                  });
      }


//      public void onClickOfFacbook(){
//        binding.fbButton.setOnClickListener(v -> {
//       //     facebookLoging();
//        });
//      }
//      public void facebookLoging(){
//
//
//        mCallbackManager=CallbackManager.Factory.create();
//         // binding.fbButton.setReadPermissions("email", "public_profile");
//
//          loginManager=LoginManager.getInstance();
//          loginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//              @Override
//              public void onSuccess(LoginResult loginResult) {
//                  //  Log.d(TAG, "facebook:onSuccess:" + loginResult);
//                  if (AccessToken.getCurrentAccessToken() != null) {
//                      GraphRequest request = GraphRequest.newMeRequest(
//                              loginResult.getAccessToken(),
//                              new GraphRequest.GraphJSONObjectCallback() {
//                                  @Override
//                                  public void onCompleted(JSONObject object, GraphResponse response) {
//                                      if (object != null) {
//                                          try {
//                                              AppEventsLogger logger = AppEventsLogger.newLogger(FirestoreActivity.this);
//                                              logger.logEvent("Facebook login suceess");
//
//                                                handleFacebookAccessToken(loginResult.getAccessToken());
//
//                                          } catch (Exception e) {
//                                              e.printStackTrace();
//                                          }
//                                      }
//                                  }
//                              });
//                      Bundle parameters = new Bundle();
//                      parameters.putString("fields", "id,name,email,gender, birthday, about");
//                      request.setParameters(parameters);
//                      request.executeAsync();
//                  }
//              }
//
//              @Override
//              public void onCancel() {
//                  Log.d("CancelCase", "onCancel: ");
//              }
//
//              @Override
//              public void onError(FacebookException error) {
//                  Log.d("errorCase", "onError: ");
//              }
//          });
//
//         loginManager.logInWithReadPermissions(FirestoreActivity.this, Arrays.asList("email", "public_profile"));
//
//}
//
//    private void handleFacebookAccessToken(AccessToken token) {
//        Log.d("EnterHandle", "handleFacebookAccessToken:" + token);
//        mAuth = FirebaseAuth.getInstance();
//
//        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("sign1", "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            if (user != null) {
//                                Log.i("sign2", "email" + user.getEmail());
//                            }
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("sign3", "signInWithCredential:failure", task.getException());
//                        }
//
//                    }
//                });
//    }


}